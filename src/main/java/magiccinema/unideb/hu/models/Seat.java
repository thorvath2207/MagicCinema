package magiccinema.unideb.hu.models;

public class Seat {
    private int rowNumber;
    private int seatNumber;
    private boolean isAvailable;

    @Override
    public String toString() {
        return rowNumber + "-" + seatNumber;
    }
}