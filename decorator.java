// You get the request to be able log the name of each executed command.
// For some commands you should also measure and log the execution duration.
// Stopwatch and Logger are given by a framework/library.

package lecturing.decorator.exercise;

class Program {

    public static void main(String[] args) {
        new CreateUserCommand("hans", "muster").execute();
        new DeleteUserCommand(1).execute();
    }
}

final class Stopwatch {
    private Stopwatch() { }

    public static long duration() { return 10; }

    public static void start() { }
    
    public static void stop() { }
}

final class Logger {
    public Logger() { }

    public static void log(String message) { 
        System.out.println(message);
    }
}

interface ICommand {
    void execute();
}

class CreateUserCommand implements ICommand {
    public CreateUserCommand(String name, String passsword) {
    }

    @Override
    public void execute() {
        // Create User...
        System.out.println("Create User...");
    }
}

class DeleteUserCommand implements ICommand {
    public DeleteUserCommand(int id) {
    }

    @Override
    public void execute() {
        // Create User...
        System.out.println("Delete User...");
    }
}