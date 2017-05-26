package magiccinema.unideb.hu.models;

import magiccinema.unideb.hu.utility.interfaces.IEntity;

import java.util.ArrayList;

public class Theater implements IEntity {
    private ArrayList<Row> rows;

    public Theater(ArrayList<Row> rows) {
        this.rows = rows;
    }
}