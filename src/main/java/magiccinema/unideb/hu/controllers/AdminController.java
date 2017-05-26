package magiccinema.unideb.hu.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import magiccinema.unideb.hu.models.*;
import magiccinema.unideb.hu.services.interfaces.dao.*;
import magiccinema.unideb.hu.utility.ServiceLocator;
import magiccinema.unideb.hu.utility.exceptions.ServiceNotFoundException;
import magiccinema.unideb.hu.utility.interfaces.IController;
import magiccinema.unideb.hu.utility.interfaces.IEntity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

public class AdminController implements IController {
    private final IActorDao actorDao;
    private final IGenreDao genreDao;
    private final IMovieDao movieDao;
    private final ISeatDao seatDao;
    private final IShowTimeDao showTimeDao;
    private final ITheaterDao theaterDao;
    private final ITicketDao ticketDao;

    public AdminController() throws ServiceNotFoundException {
        this.actorDao = (IActorDao) ServiceLocator.getService("ActorDao");
        this.genreDao = (IGenreDao) ServiceLocator.getService("GenreDao");
        this.movieDao = (IMovieDao) ServiceLocator.getService("MovieDao");
        this.seatDao = (ISeatDao) ServiceLocator.getService("SeatDao");
        this.showTimeDao = (IShowTimeDao) ServiceLocator.getService("ShowTimeDao");
        this.theaterDao = (ITheaterDao) ServiceLocator.getService("TheaterDao");
        this.ticketDao = (ITicketDao) ServiceLocator.getService("TicketDao");
    }

    @FXML
    public void handleFillBtnClick(MouseEvent eventArgs) {
        this.generateSampleData();
    }


    @Override
    public void resetData() {

    }

    @Override
    public void setData(IEntity entity) {

    }

    private void generateSampleData() {
        Genre genreToAdd = new Genre();
        genreToAdd.setName("Action");
        this.genreDao.add(genreToAdd);

        genreToAdd = new Genre();
        genreToAdd.setName("Drama");
        this.genreDao.add(genreToAdd);

        genreToAdd = new Genre();
        genreToAdd.setName("Comedy");
        this.genreDao.add(genreToAdd);

        genreToAdd = new Genre();
        genreToAdd.setName("Sci-fi");
        this.genreDao.add(genreToAdd);

        Actor actorToAdd;
        actorToAdd = new Actor();
        actorToAdd.setName("Kényaú Szívsz");

        this.actorDao.add(actorToAdd);

        actorToAdd = new Actor();
        actorToAdd.setName("Britni Szpírsz");

        this.actorDao.add(actorToAdd);

        actorToAdd = new Actor();
        actorToAdd.setName("Henifer Lhopez");

        this.actorDao.add(actorToAdd);

        Movie movieToAdd;
        movieToAdd = new Movie();
        movieToAdd.setTitle("Nem eszek több végbelet");
        Collection<Genre> genres = this.genreDao.getAll();
        Collection<Actor> actors = this.actorDao.getAll();
        movieToAdd.setGenresCollection(genres.stream().filter(g -> g.getGenreId() < 3).collect(Collectors.toList()));
        movieToAdd.setActorsCollection(actors.stream().filter(a -> a.getActorId() < 2).collect(Collectors.toList()));

        this.movieDao.add(movieToAdd);
        movieToAdd = this.movieDao.getById(movieToAdd.getId());

        Theater theaterToAdd;
        theaterToAdd = new Theater();
        theaterToAdd.setRowsCapacity(8);
        theaterToAdd.setRows(12);
        this.theaterDao.add(theaterToAdd);

        this.generateTestSeats(theaterToAdd.getRows(), theaterToAdd.getRowsCapacity(), theaterToAdd);

        ShowTime showTimeToAdd;
        showTimeToAdd = new ShowTime();
        showTimeToAdd.setMovie(movieToAdd);
        showTimeToAdd.setTime(LocalDateTime.of(2017, 05, 30, 12, 00));
        showTimeToAdd.setTheater(theaterToAdd);

        this.showTimeDao.add(showTimeToAdd);

        showTimeToAdd = new ShowTime();
        showTimeToAdd.setMovie(movieToAdd);
        showTimeToAdd.setTime(LocalDateTime.of(2017, 05, 31, 15, 00));
        showTimeToAdd.setTheater(theaterToAdd);

        this.showTimeDao.add(showTimeToAdd);

        showTimeToAdd = new ShowTime();
        showTimeToAdd.setMovie(movieToAdd);
        showTimeToAdd.setTime(LocalDateTime.of(2017, 06, 12, 12, 00));
        showTimeToAdd.setTheater(theaterToAdd);

        this.showTimeDao.add(showTimeToAdd);

        System.out.println("Movies:");
        System.out.println(this.movieDao.getAll().size());
        System.out.println("Genre:");
        System.out.println(this.genreDao.getAll().size());
        System.out.println("Actors:");
        System.out.println(this.actorDao.getAll().size());


    }

    public void generateTestSeats(int rows, int seatPerRows, Theater theater) {
        Seat seatToAdd;

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < seatPerRows; j++) {
                seatToAdd = new Seat();
                seatToAdd.setAvailable(true);
                seatToAdd.setSeatNumber(j);
                seatToAdd.setTheater(theater);
                this.seatDao.add(seatToAdd);
            }
        }
    }
}