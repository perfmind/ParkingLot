package exceptions;

public class InvalidParkingCommandException extends Exception {
    public InvalidParkingCommandException(String message) {
        super(message);
    }
}
