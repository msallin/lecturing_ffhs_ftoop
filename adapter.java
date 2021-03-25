// You get the request to be replace the SimplePasswordGenerator with the FancyPasswordGeneratorFromLibrary.
// To do this, you must not touch the CreateUserAccountCommand. However, the FancyPasswordGeneratorFromLibrary class
// is from a library and the interface IPasswordGenerator isn't implemented.

package lecturing.adapter.exercise;

class Program {
    public static void main(String[] args) {
        CreateUserAccountCommand command = new CreateUserAccountCommand(new SimplePasswordGenerator());
        command.execute();
    }
}

// ------------ Part of a library ------------ 
class FancyPasswordGeneratorFromLibrary {
    public String createPassword() {
        return "";
    }
}
// ------------------------------------------- 

class CreateUserAccountCommand {
    private IPasswordGenerator generator;

    public CreateUserAccountCommand(IPasswordGenerator generator) {
        this.generator = generator;
    }

    public void execute(){
        String password = this.generator.generatePassword();
        // Create User...
    }
}


interface IPasswordGenerator {
    String generatePassword();
}

class SimplePasswordGenerator implements IPasswordGenerator {
    @Override
    public String generatePassword(){
        return "";
    }
}