package magiccinema.unideb.hu.services;

import magiccinema.unideb.hu.models.Movie;
import magiccinema.unideb.hu.models.Seat;
import magiccinema.unideb.hu.models.ShowTime;
import magiccinema.unideb.hu.models.Ticket;
import magiccinema.unideb.hu.services.interfaces.ICinemaService;
import magiccinema.unideb.hu.services.interfaces.dao.*;
import magiccinema.unideb.hu.utility.ServiceLocator;
import magiccinema.unideb.hu.utility.exceptions.ServiceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CinemaService implements ICinemaService {

    protected static Logger logger = LoggerFactory.getLogger(CinemaService.class);

    private final IActorDao actorDao;
    private final IGenreDao genreDao;
    private final IMovieDao movieDao;
    private final ISeatDao seatDao;
    private final IShowTimeDao showTimeDao;
    private final ITheaterDao theaterDao;
    private final ITicketDao ticketDao;

    public CinemaService() throws ServiceNotFoundException {
        this.actorDao = (IActorDao) ServiceLocator.getService("ActorDao");
        this.genreDao = (IGenreDao) ServiceLocator.getService("GenreDao");
        this.movieDao = (IMovieDao) ServiceLocator.getService("MovieDao");
        this.seatDao = (ISeatDao) ServiceLocator.getService("SeatDao");
        this.showTimeDao = (IShowTimeDao) ServiceLocator.getService("ShowTimeDao");
        this.theaterDao = (ITheaterDao) ServiceLocator.getService("TheaterDao");
        this.ticketDao = (ITicketDao) ServiceLocator.getService("TicketDao");

        logger.trace("Cinema service is initialized.");
    }

    public List<ShowTime> getUpComingShowTimesByMovieId(int movieId) {
        List<ShowTime> upComingShowTimes = this.showTimeDao.getAll();
        upComingShowTimes = upComingShowTimes
                .stream()
                .filter(s -> s.getTime().isAfter(LocalDateTime.now()) && s.getMovie().getId() == movieId)
                .collect(Collectors.toList());
        return upComingShowTimes;
    }

    public int getAvailableSeatsByShowTimeId(int showTimeId) {
        ShowTime showTime = this.showTimeDao.getById(showTimeId);
        int size = showTime.getTheater().getSeatCollection().size();
        int reservedTickets = (int) this.ticketDao.getAll()
                .stream()
                .filter(t -> t.getShowTime().getId() == showTimeId)
                .count();

        int result = size - reservedTickets;
        if (result < 0) {
            return 0;
        }

        return result;
    }

    public List<Seat> getSeatsToShowTime(int showTimeId) {
        ShowTime showTime = this.showTimeDao.getById(showTimeId);
        List<Seat> seats = (List<Seat>) showTime.getTheater().getSeatCollection();

        seats.stream().forEach(seat -> {
            seat.setAvailable(this.getSeatIsAvailableAtShowTime(seat.getId(), showTimeId));
        });

        return seats;
    }

    public ShowTime getShowTimeById(int showTimeId) {
        return this.showTimeDao.getById(showTimeId);
    }

    public boolean getSeatIsAvailableAtShowTime(int seatId, int showTimeId) {
        ShowTime showTime = this.getShowTimeById(showTimeId);

        if (showTime == null) {
            return false;
        }

        Collection<Seat> reservedSeats = showTime.getTicketCollection()
                .stream()
                .map(Ticket::getSeat)
                .collect(Collectors.toList());

        if (reservedSeats.stream().filter(s -> s.getId() == seatId).count() != 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<Seat> updateSeatListForSelector(List<Seat> selectedSeats, int showTimeid) {
        return null;
    }

    public List<ShowTime> getUpComingShowTimes() {
        List<ShowTime> upComingShowTimes = this.showTimeDao.getAll();
        upComingShowTimes = upComingShowTimes
                .stream()
                .filter(s -> s.getTime().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
        return upComingShowTimes;
    }

    public Collection<Movie> getUpcomingMovies() {
        List<Movie> movies;
        List<ShowTime> showTimes = this.getUpComingShowTimes();
        movies = showTimes
                .stream()
                .map(ShowTime::getMovie)
                .distinct()
                .collect(Collectors.toList());
        return movies;
    }

    public int upComingShowTimesCounter(int movieId) {
        return this.getUpComingShowTimesByMovieId(movieId).size();
    }

    public String getName() {
        return "CinemaService";
    }
}
