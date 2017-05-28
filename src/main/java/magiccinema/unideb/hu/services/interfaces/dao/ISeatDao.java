package magiccinema.unideb.hu.services.interfaces.dao;

import magiccinema.unideb.hu.models.Seat;

public interface ISeatDao extends IDao<Seat> {
    Seat getByRowColTheatreId(int theaterId, int rowId, int colId);
}
