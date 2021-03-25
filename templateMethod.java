// You have a program which parses a CSV file which contains users and creates them in a database.
// Change the program to support also other file formats.

package lecturing.templatemethod.exercise;

import java.util.ArrayList;

class Program {

    public static void main(String[] args) {
        new UserImporter("pathToFile").execute();
    }
}

/**
 * This class reads users from a file and imports them into a database.
 */
class UserImporter {

    private String filePath;

    public UserImporter(String string) {
        this.filePath = string;
    }

    public void execute() {
        // Imagine the users are imported from a CSV file here...
        ArrayList<User> users = new ArrayList<>(); 

        for (User user : users) {
            // Imagine now that every user is imported into a database
        }
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