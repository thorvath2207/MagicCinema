package magiccinema.unideb.hu.models;

import magiccinema.unideb.hu.utility.interfaces.IEntity;

import java.util.ArrayList;

public class Row implements IEntity {
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