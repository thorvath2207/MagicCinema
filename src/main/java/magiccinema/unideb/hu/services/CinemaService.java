package magiccinema.unideb.hu.services;

import magiccinema.unideb.hu.models.Movie;
import magiccinema.unideb.hu.models.ShowTime;
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

    private final IGenreDao genreDao;
    private final IMovieDao movieDao;
    private final ISeatDao seatDao;
    private final IShowTimeDao showTimeDao;
    private final ITheaterDao theaterDao;
    private final ITicketDao ticketDao;

    public CinemaService() throws ServiceNotFoundException {
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
                                    .filter(s -> s.getTime().isBefore(LocalDateTime.now()) && s.getMovie().getId() == movieId)
                                    .collect(Collectors.toList());
        return upComingShowTimes;
    }

    public List<ShowTime> getUpComingShowTimesByMovieId() {
        List<ShowTime> upComingShowTimes = this.showTimeDao.getAll();
        upComingShowTimes = upComingShowTimes
                .stream()
                .filter(s -> s.getTime().isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());
        return upComingShowTimes;
    }

    public Collection<Movie> getUpcomingMovies() {
        List<Movie> movies;
        List<ShowTime> showTimes = this.getUpComingShowTimesByMovieId();
        movies = showTimes
                .stream()
                .map(ShowTime::getMovie)
                .distinct()
                .collect(Collectors.toList());
        return movies;
    }

    @Override
    public String getName() {
        return "CinemaService";
    }
}
