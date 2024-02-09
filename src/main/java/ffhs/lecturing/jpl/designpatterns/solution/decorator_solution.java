// The following code is given.
// You get the request to be able log the name of each executed command.
// For some commands you should also measure and log the execution duration.
// Stopwatch and Logger are given by a framework/library.

package ffhs.lecturing.jpl.designpatterns.solution;


class Main2 {

    public static void main(String[] args) {
        ICommand command1 = new DurationDecorator(new LoggerDecorator(new CreateUserCommand("test", "test1234")));
        ICommand command2 = new LoggerDecorator(new DeleteUserCommand(2));

        command1.execute();
        command2.execute();
    }
}

final class Stopwatch {
    private Stopwatch() { }

    public static long duration() { return 0; }

    public static void start() { }

    public static void stop() { }
}

final class Logger {
    public Logger() { }

    public static void log(String message) {
        System.out.println(message);
    }
}

class LoggerDecorator implements ICommand {
    private final ICommand command;

    public LoggerDecorator(ICommand command) {
        this.command = command;
    }

    public void execute() {
        Logger.log("Execute " + this.command.getClass().getName());
        this.command.execute();
    }
}


class DurationDecorator implements ICommand {
    private final ICommand command;

    public DurationDecorator(ICommand command) {
        this.command = command;
    }

    public void execute() {
        Stopwatch.start();
        this.command.execute();
        Stopwatch.stop();
        Logger.log("Duration: {}" + Stopwatch.duration() + " ms");
    }
}


interface ICommand {
    void execute();
}

class CreateUserCommand implements ICommand {
    public CreateUserCommand(String name, String passsword) {
    }

    public void execute() {
        // Create User...
    }
}

class DeleteUserCommand implements ICommand {
    public DeleteUserCommand(int id) {
    }

    public void execute() {
        // Create User...
    }
}