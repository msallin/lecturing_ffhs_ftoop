// You have a programm which parses a CSV file which contains users and creates them in a database.
// Change the program to support also other file formats.

package ffhs.lecturing.jpl.designpatterns.solution;

import java.util.ArrayList;

class Main3 {

    public static void main(String[] args) {
        new CsvUserImporter("pathToFile").execute();
        new XmlUserImporter("pathToFile").execute();
    }
}

/**
 * This class reads users from a file and imports them into a database.
 */
abstract class UserImporter {

    protected String filePath;

    protected UserImporter(String string) {
        this.filePath = string;
    }

    public void execute() {
        ArrayList<User> users = parseFile();

        for (User user : users) {
            // Imagine now that every user is imported into a database
        }
    }

    protected abstract ArrayList<User> parseFile();

}

class CsvUserImporter extends UserImporter {

    public CsvUserImporter(String string) {
        super(string);
    }

    @Override
    protected ArrayList<User> parseFile() {
        // Imagine the users are imported from a CSV file here...
        ArrayList<User> users = new ArrayList<>();
        return users;
    }
}

class XmlUserImporter extends UserImporter {

    public XmlUserImporter(String string) {
        super(string);
    }

    @Override
    protected ArrayList<User> parseFile() {
        // Imagine the users are imported from a XML file here...
        ArrayList<User> users = new ArrayList<>();
        return users;
    }
}

class User {
    private String firstname;
    private String lastname;

    public User(String firstname, String lastname){
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}