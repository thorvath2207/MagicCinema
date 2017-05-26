package magiccinema.unideb.hu.services.interfaces;

import magiccinema.unideb.hu.models.Movie;

import java.util.Collection;

// ide jon majd minden szar ami kell a mozi kezeleshez
// ez fogja meghivni a dao-kat, amik meg okosba megoldjak a cuccot
public interface ICinemaService extends IService {
    Collection<Movie> getUpcomingMovies();
}
