package magiccinema.unideb.hu.models;

import magiccinema.unideb.hu.utility.interfaces.IEntity;

import java.util.Date;

public class ShowTime implements IEntity {
    private Date time;
    private Theater theater;
    private Movie movie;

}
