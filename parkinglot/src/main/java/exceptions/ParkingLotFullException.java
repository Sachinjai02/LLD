package exceptions;

public class ParkingLotFullException extends RuntimeException {
    public ParkingLotFullException(String s) {
        super(s);
    }
}
