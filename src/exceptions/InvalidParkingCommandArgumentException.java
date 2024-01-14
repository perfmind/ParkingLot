package exceptions;

public class InvalidParkingCommandArgumentException extends Exception {
    public InvalidParkingCommandArgumentException(String message) {
        super("Argument salah, kembali ke menu awal.");
    }
}
