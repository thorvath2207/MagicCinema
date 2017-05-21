package magiccinema.unideb.hu.models;

import java.util.ArrayList;

public class Row {
    private ArrayList<Row> seatsList;
    private int seatCapacity;
    private int rowNumber;

    public Row() {
        this.seatsList = new ArrayList<Row>(seatCapacity);



    }

    @Override
    public String toString() {
        return rowNumber + ". sor";
    }
}