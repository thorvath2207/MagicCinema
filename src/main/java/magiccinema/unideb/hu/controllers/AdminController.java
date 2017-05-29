package magiccinema.unideb.hu.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import magiccinema.unideb.hu.models.*;
import magiccinema.unideb.hu.services.interfaces.IDialogService;
import magiccinema.unideb.hu.services.interfaces.dao.*;
import magiccinema.unideb.hu.utility.ServiceLocator;
import magiccinema.unideb.hu.utility.constans.AdditionalParameters;
import magiccinema.unideb.hu.utility.exceptions.ServiceNotFoundException;
import magiccinema.unideb.hu.utility.interfaces.IController;
import magiccinema.unideb.hu.utility.interfaces.IEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AdminController implements IController {

    protected static Logger logger = LoggerFactory.getLogger(AdminController.class);

    private final IActorDao actorDao;
    private final IGenreDao genreDao;
    private final IMovieDao movieDao;
    private final ISeatDao seatDao;
    private final IShowTimeDao showTimeDao;
    private final ITheaterDao theaterDao;
    private final ITicketDao ticketDao;
    private final IReservationDao reservationDao;

    private final IDialogService dialogService;
    @FXML
    private TableView<Reservation> reservationTable;
    @FXML
    private TableColumn<Reservation, String> showTimeCol;
    @FXML
    private TableColumn<Reservation, String> movieCol;
    @FXML
    private TableColumn<Reservation, String> reservationNameCol;
    @FXML
    private TableColumn<Reservation, String> reservationDateCol;

    public AdminController() throws ServiceNotFoundException {
        this.actorDao = (IActorDao) ServiceLocator.getService("ActorDao");
        this.genreDao = (IGenreDao) ServiceLocator.getService("GenreDao");
        this.movieDao = (IMovieDao) ServiceLocator.getService("MovieDao");
        this.seatDao = (ISeatDao) ServiceLocator.getService("SeatDao");
        this.showTimeDao = (IShowTimeDao) ServiceLocator.getService("ShowTimeDao");
        this.theaterDao = (ITheaterDao) ServiceLocator.getService("TheaterDao");
        this.ticketDao = (ITicketDao) ServiceLocator.getService("TicketDao");
        this.reservationDao = (IReservationDao) ServiceLocator.getService("ReservationDao");
        this.dialogService = (IDialogService) ServiceLocator.getService("DialogService");
    }

    @FXML
    public void handleFillBtnClick(MouseEvent eventArgs) {
        try {
            this.generateSampleData();
            this.dialogService.showInformationPopup("Test data", "Generate some shitz.", "Done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void resetData() {
        ObservableList<Reservation> reservations = FXCollections.observableArrayList();
        reservations.addAll(this.reservationDao.getAll());
        this.reservationTable.setItems(reservations);
    }

    @Override
    public void setData(IEntity entity, HashMap<AdditionalParameters, Integer> addinParams) {

    }

    @Override
    public void setData(List<IEntity> entities) {

    }

    @FXML
    private void initialize() {
        this.showTimeCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getShowTime().getTime().toString()));
        this.movieCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getMovie().getTitle()));
        this.reservationNameCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getName()));
        this.reservationDateCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getReservationDate().toString()));

        this.resetData();
    }

    private void generateSampleData() throws IOException {
        logger.debug("create test data.");
        logger.debug("add genres.");
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
        genreToAdd.setName("Sci-Fi");
        this.genreDao.add(genreToAdd);
        logger.debug("add actors.");
        Actor actorToAdd;
        actorToAdd = new Actor();
        actorToAdd.setName("Keanu Reeves");

        this.actorDao.add(actorToAdd);

        actorToAdd = new Actor();
        actorToAdd.setName("Laurence Fishburne");

        this.actorDao.add(actorToAdd);

        actorToAdd = new Actor();
        actorToAdd.setName("Carrie-Anne Moss");

        this.actorDao.add(actorToAdd);
        logger.debug("add movies.");
        Movie movieToAdd;
        movieToAdd = new Movie();
        movieToAdd.setTitle("The Matrix");
        movieToAdd.setLenght(136);
        movieToAdd.setImage(this.getImageFromUrl("https://images-na.ssl-images-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX182_CR0,0,182,268_AL_.jpg"));
        Collection<Genre> genres = this.genreDao.getAll();
        Collection<Actor> actors = this.actorDao.getAll();
        movieToAdd.setGenresCollection(genres.stream().filter(g -> g.getGenreId() == 1 || g.getGenreId() == 4).collect(Collectors.toList()));
        movieToAdd.setActorsCollection(actors);

        this.movieDao.add(movieToAdd);

        movieToAdd = new Movie();
        movieToAdd.setTitle("The Matrix Reloaded");
        movieToAdd.setLenght(138);
        movieToAdd.setImage(this.getImageFromUrl("https://images-na.ssl-images-amazon.com/images/M/MV5BMjA0NDM5MDY2OF5BMl5BanBnXkFtZTcwNzg5OTEzMw@@._V1_UX182_CR0,0,182,268_AL_.jpg"));
        genres = this.genreDao.getAll();
        actors = this.actorDao.getAll();
        movieToAdd.setGenresCollection(genres.stream().filter(g -> g.getGenreId() == 1 || g.getGenreId() == 4).collect(Collectors.toList()));
        movieToAdd.setActorsCollection(actors);

        this.movieDao.add(movieToAdd);

        movieToAdd = new Movie();
        movieToAdd.setTitle("The Matrix Revolutions");
        movieToAdd.setLenght(129);
        movieToAdd.setImage(this.getImageFromUrl("https://images-na.ssl-images-amazon.com/images/M/MV5BMjA1MjM3NTE1Ml5BMl5BanBnXkFtZTYwODM4MDg3._V1_UX182_CR0,0,182,268_AL_.jpg"));
        genres = this.genreDao.getAll();
        actors = this.actorDao.getAll();
        movieToAdd.setGenresCollection(genres.stream().filter(g -> g.getGenreId() == 1 || g.getGenreId() == 4).collect(Collectors.toList()));
        movieToAdd.setActorsCollection(actors);
        this.movieDao.add(movieToAdd);

        logger.debug("generate theaters");
        Theater theaterToAdd;
        theaterToAdd = new Theater();
        theaterToAdd.setRowsCapacity(8);
        theaterToAdd.setRows(12);
        this.theaterDao.add(theaterToAdd);
        this.generateTestSeats(theaterToAdd.getRows(), theaterToAdd.getRowsCapacity(), theaterToAdd);

        theaterToAdd = new Theater();
        theaterToAdd.setRowsCapacity(5);
        theaterToAdd.setRows(7);
        this.theaterDao.add(theaterToAdd);
        this.generateTestSeats(theaterToAdd.getRows(), theaterToAdd.getRowsCapacity(), theaterToAdd);

        logger.debug("generate showtimes");
        // matrix 1 - 1. terem
        ShowTime showTimeToAdd;
        showTimeToAdd = new ShowTime();
        showTimeToAdd.setMovie(this.movieDao.getById(1));
        showTimeToAdd.setTime(LocalDateTime.of(2017, 05, 30, 12, 00));
        showTimeToAdd.setTheater(this.theaterDao.getById(1));

        this.showTimeDao.add(showTimeToAdd);

        showTimeToAdd = new ShowTime();
        showTimeToAdd.setMovie(this.movieDao.getById(1));
        showTimeToAdd.setTime(LocalDateTime.of(2017, 05, 31, 15, 00));
        showTimeToAdd.setTheater(this.theaterDao.getById(1));

        this.showTimeDao.add(showTimeToAdd);

        showTimeToAdd = new ShowTime();
        showTimeToAdd.setMovie(this.movieDao.getById(1));
        showTimeToAdd.setTime(LocalDateTime.of(2017, 06, 12, 12, 00));
        showTimeToAdd.setTheater(this.theaterDao.getById(1));

        this.showTimeDao.add(showTimeToAdd);

        // matrix 2 - 1. terem;
        showTimeToAdd = new ShowTime();
        showTimeToAdd.setMovie(this.movieDao.getById(2));
        showTimeToAdd.setTime(LocalDateTime.of(2017, 05, 30, 15, 00));
        showTimeToAdd.setTheater(this.theaterDao.getById(1));

        this.showTimeDao.add(showTimeToAdd);

        showTimeToAdd = new ShowTime();
        showTimeToAdd.setMovie(this.movieDao.getById(2));
        showTimeToAdd.setTime(LocalDateTime.of(2017, 05, 31, 18, 00));
        showTimeToAdd.setTheater(this.theaterDao.getById(1));

        this.showTimeDao.add(showTimeToAdd);

        showTimeToAdd = new ShowTime();
        showTimeToAdd.setMovie(this.movieDao.getById(2));
        showTimeToAdd.setTime(LocalDateTime.of(2017, 06, 12, 15, 00));
        showTimeToAdd.setTheater(this.theaterDao.getById(1));

        this.showTimeDao.add(showTimeToAdd);

        // matrix 2 - 2. terem
        showTimeToAdd = new ShowTime();
        showTimeToAdd.setMovie(this.movieDao.getById(2));
        showTimeToAdd.setTime(LocalDateTime.of(2017, 05, 30, 14, 00));
        showTimeToAdd.setTheater(this.theaterDao.getById(2));

        this.showTimeDao.add(showTimeToAdd);

        showTimeToAdd = new ShowTime();
        showTimeToAdd.setMovie(this.movieDao.getById(2));
        showTimeToAdd.setTime(LocalDateTime.of(2017, 05, 31, 17, 00));
        showTimeToAdd.setTheater(this.theaterDao.getById(2));

        this.showTimeDao.add(showTimeToAdd);

        showTimeToAdd = new ShowTime();
        showTimeToAdd.setMovie(this.movieDao.getById(2));
        showTimeToAdd.setTime(LocalDateTime.of(2017, 06, 12, 14, 00));
        showTimeToAdd.setTheater(this.theaterDao.getById(2));

        this.showTimeDao.add(showTimeToAdd);

        // matrix 3 - 1. terem
        showTimeToAdd = new ShowTime();
        showTimeToAdd.setMovie(this.movieDao.getById(3));
        showTimeToAdd.setTime(LocalDateTime.of(2017, 05, 31, 22, 00));
        showTimeToAdd.setTheater(this.theaterDao.getById(1));

        this.showTimeDao.add(showTimeToAdd);

        showTimeToAdd = new ShowTime();
        showTimeToAdd.setMovie(this.movieDao.getById(3));
        showTimeToAdd.setTime(LocalDateTime.of(2017, 05, 30, 22, 00));
        showTimeToAdd.setTheater(this.theaterDao.getById(1));

        this.showTimeDao.add(showTimeToAdd);

        showTimeToAdd = new ShowTime();
        showTimeToAdd.setMovie(this.movieDao.getById(3));
        showTimeToAdd.setTime(LocalDateTime.of(2017, 06, 12, 22, 00));
        showTimeToAdd.setTheater(this.theaterDao.getById(1));

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

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= seatPerRows; j++) {
                seatToAdd = new Seat();
                seatToAdd.setAvailable(true);
                seatToAdd.setSeatNumber(j);
                seatToAdd.setTheater(theater);
                seatToAdd.setRowNumber(i);
                this.seatDao.add(seatToAdd);
            }
        }
    }

    private byte[] getImageFromUrl(String url) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is = null;
        try {
            URL url_ = new URL(url);
            is = url_.openStream();
            byte[] byteChunk = new byte[4096]; // Or whatever size you want to read in at a time.
            int n;

            while ((n = is.read(byteChunk)) > 0) {
                baos.write(byteChunk, 0, n);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }

            return baos.toByteArray();
        }
    }
}
