import java.math.BigInteger;

// Is this code thread-safe? Why/Why not? How to fix, if necessary?
final class MathUtils {

    private MathUtils() {
    }

    public static BigInteger factorial(int number) {
        BigInteger f = BigInteger.valueOf(1);
        for (int i = 2; i <= number; i++) {
            f = f.multiply(BigInteger.valueOf(i));
        }
        return f;
    }
}

// Is this code thread-safe? Why/Why not? How to fix, if necessary?
class MessageService {
    private final String message;

    public MessageService(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}

// Is this code thread-safe? Why/Why not? How to fix, if necessary?
class MessageServiceTwo {
    private String message;

    public MessageServiceTwo(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void addToMessage(String addition) {
        this.message = this.message + addition;
    }
}

// Is this code thread-safe? Why/Why not? How to fix, if necessary?
class Counter {

    private int currentValue = 0;

    public void incrementCounter() {
        currentValue += 1;
    }
    
    public int getCounter() {
        return currentValue;
    }
}
