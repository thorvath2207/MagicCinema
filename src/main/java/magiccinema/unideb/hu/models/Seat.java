package magiccinema.unideb.hu.models;

import magiccinema.unideb.hu.utility.interfaces.IEntity;

public class Seat implements IEntity {
    private int rowNumber;
    private int seatNumber;
    private boolean isAvailable;

    @Override
    public String toString() {
        return rowNumber + "-" + seatNumber;
    }
}