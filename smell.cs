
private static void Insert<T>(DbContext context, Type type, IList<T> entities, TableInfo tableInfo, Action<decimal> progress)
{
    string providerName = context.Database.ProviderName; // "Microsoft.EntityFrameworkCore.*****"
    // -- SQL Server --
    if (providerName.EndsWith(DbServer.SqlServer.ToString()))
    {
        var connection = OpenAndGetSqlConnection(context, tableInfo.BulkConfig);
        try
        {
            var transaction = context.Database.CurrentTransaction;
 
            // separate logic for System.Data.SqlClient and Microsoft.Data.SqlClient
            if (SqlClientHelper.IsSystemConnection(connection))
            {
                using (var sqlBulkCopy = GetSqlBulkCopy((System.Data.SqlClient.SqlConnection)connection, transaction, tableInfo.BulkConfig))
                {
                    bool setColumnMapping = false;
                    tableInfo.SetSqlBulkCopyConfig(sqlBulkCopy, entities, setColumnMapping, progress);
                    try
                    {
                        var dataTable = GetDataTable(context, type, entities, sqlBulkCopy, tableInfo);
                        sqlBulkCopy.WriteToServer(dataTable);
                    }
                    catch (InvalidOperationException ex)
                    {
                        if (ex.Message.Contains(ColumnMappingExceptionMessage))
                        {
                            if (!tableInfo.CheckTableExist(context, tableInfo))
                            {
                                context.Database.ExecuteSqlRaw(SqlQueryBuilder.CreateTableCopy(tableInfo.FullTableName, tableInfo.FullTempTableName, tableInfo)); // Will throw Exception specify missing db column: Invalid column name ''
                                context.Database.ExecuteSqlRaw(SqlQueryBuilder.DropTable(tableInfo.FullTempTableName, tableInfo.BulkConfig.UseTempDB));
                            }
                        }
                        throw;
                    }
                }
            }
            else
            {
                using (var sqlBulkCopy = GetSqlBulkCopy((Microsoft.Data.SqlClient.SqlConnection)connection, transaction, tableInfo.BulkConfig))
                {
                    bool setColumnMapping = false;
                    tableInfo.SetSqlBulkCopyConfig(sqlBulkCopy, entities, setColumnMapping, progress);
                    try
                    {
                        var dataTable = GetDataTable(context, type, entities, sqlBulkCopy, tableInfo);
                        sqlBulkCopy.WriteToServer(dataTable);
                    }
                    catch (InvalidOperationException ex)
                    {
                        if (ex.Message.Contains(ColumnMappingExceptionMessage))
                        {
                            if (!tableInfo.CheckTableExist(context, tableInfo))
                            {
                                context.Database.ExecuteSqlRaw(SqlQueryBuilder.CreateTableCopy(tableInfo.FullTableName, tableInfo.FullTempTableName, tableInfo)); // Will throw Exception specify missing db column: Invalid column name ''
                                context.Database.ExecuteSqlRaw(SqlQueryBuilder.DropTable(tableInfo.FullTempTableName, tableInfo.BulkConfig.UseTempDB));
                            }
                        }
                        throw;
                    }
                }
            }
        }
        finally
        {
            context.Database.CloseConnection();
        }
    }
    // -- SQLite --
    else if (providerName.EndsWith(DbServer.Sqlite.ToString()))
    {
        var connection = OpenAndGetSqliteConnection(context, tableInfo.BulkConfig);
        bool doExplicitCommit = false;
 
        try
        {
            if (context.Database.CurrentTransaction == null)
            {
                //context.Database.UseTransaction(connection.BeginTransaction());
                doExplicitCommit = true;
            }
            var transaction = (SqliteTransaction)(context.Database.CurrentTransaction == null ?
                                                    connection.BeginTransaction() :
                                                    context.Database.CurrentTransaction.GetUnderlyingTransaction(tableInfo.BulkConfig));
 
            var command = GetSqliteCommand(context, type, entities, tableInfo, connection, transaction);
 
            type = tableInfo.HasAbstractList ? entities[0].GetType() : type;
            int rowsCopied = 0;
            foreach (var item in entities)
            {
                LoadSqliteValues(tableInfo, item, command);
                command.ExecuteNonQuery();
                SetProgress(ref rowsCopied, entities.Count, tableInfo.BulkConfig, progress);
            }
            if (doExplicitCommit)
            {
                transaction.Commit();
            }
        }
        finally
        {
            context.Database.CloseConnection();
        }
    }
    else
    {
        throw new SqlProviderNotSupportedException(providerName);
    }
}
