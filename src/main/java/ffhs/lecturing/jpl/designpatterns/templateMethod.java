package ffhs.lecturing.jpl.designpatterns;// You have a program which parses a CSV file which contains users and creates them in a database.
// Change the program to support also other file formats.

import java.util.ArrayList;

class Main3 {

    public static void main(String[] args) {
        UserImporter userImporter = new UserImporter("pathToFile");
        userImporter.execute();
    }
}

/**
 * This class reads users from a file and imports them into a database.
 */
class UserImporter {

    private String filePath;

    public UserImporter(String filePath) {
        this.filePath = filePath;
    }

    public void execute() {
        // Imagine the users are imported from a CSV file here...
        // ...
        // ... Parse the csv and create the users
        // ...
        ArrayList<User> users = new ArrayList<>();


        for (User user : users) {
            // Imagine now that every user is imported into a database
        }
    }
}

class User {
    private String firstname;
    private String lastname;

    public User(String firstname, String lastname) {
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
