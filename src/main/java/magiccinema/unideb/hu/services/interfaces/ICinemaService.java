package magiccinema.unideb.hu.services.interfaces;

import magiccinema.unideb.hu.models.Movie;
import magiccinema.unideb.hu.models.ShowTime;

import java.util.Collection;
import java.util.List;

// ide jon majd minden szar ami kell a mozi kezeleshez
// ez fogja meghivni a dao-kat, amik meg okosba megoldjak a cuccot
public interface ICinemaService extends IService {
    Collection<Movie> getUpcomingMovies();

    int upComingShowTimesCounter(int movieId);

    List<ShowTime> getUpComingShowTimesByMovieId(int movieId);

    int getAvailableSeatsByShowTimeId(int showTimeId);
}
