package magiccinema.unideb.hu.testShared;

import magiccinema.unideb.hu.models.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class testData {
    public static Collection<Genre> getTestGenres() {
        ArrayList<Genre> genres = new ArrayList<>();
        Genre genreToAdd = new Genre();
        genreToAdd.setGenreId(1);
        genreToAdd.setName("Action");
        genres.add(genreToAdd);

        genreToAdd = new Genre();
        genreToAdd.setGenreId(2);
        genreToAdd.setName("Drama");
        genres.add(genreToAdd);

        genreToAdd = new Genre();
        genreToAdd.setGenreId(3);
        genreToAdd.setName("Comedy");
        genres.add(genreToAdd);

        genreToAdd = new Genre();
        genreToAdd.setGenreId(4);
        genreToAdd.setName("Sci-fi");
        genres.add(genreToAdd);

        return genres;
    }

    public static Collection<Actor> getTestActors() {
        ArrayList<Actor> actors = new ArrayList<>();

        Actor actorToAdd;

        actorToAdd = new Actor();
        actorToAdd.setActorId(1);
        actorToAdd.setName("Kényaú Szívsz");

        actors.add(actorToAdd);

        actorToAdd = new Actor();
        actorToAdd.setActorId(2);
        actorToAdd.setName("Britni Szpírsz");

        actors.add(actorToAdd);

        actorToAdd = new Actor();
        actorToAdd.setActorId(3);
        actorToAdd.setName("Henifer Lhopez");

        actors.add(actorToAdd);

        return actors;
    }

    public static Collection<Movie> getTestMovies() {
        ArrayList<Movie> movies = new ArrayList<>();

        Movie movieToAdd;
        movieToAdd = new Movie();
        movieToAdd.setId(1);
        movieToAdd.setGenresCollection(getTestGenres().stream().filter(g -> g.getGenreId() == 1).collect(Collectors.toList()));
        movieToAdd.setActorsCollection(getTestActors().stream().filter(a -> a.getActorId() == 1).collect(Collectors.toList()));
        movieToAdd.setTitle("Nem eszek több végbelet");

        movies.add(movieToAdd);

        movieToAdd = new Movie();
        movieToAdd.setId(2);
        movieToAdd.setGenresCollection(getTestGenres().stream().filter(g -> g.getGenreId() == 2).collect(Collectors.toList()));
        movieToAdd.setActorsCollection(getTestActors().stream().filter(a -> a.getActorId() == 2).collect(Collectors.toList()));
        movieToAdd.setTitle("Nézz napba. De ne abba.");

        movies.add(movieToAdd);

        movieToAdd = new Movie();
        movieToAdd.setId(3);
        movieToAdd.setGenresCollection(getTestGenres().stream().filter(g -> g.getGenreId() == 3).collect(Collectors.toList()));
        movieToAdd.setActorsCollection(getTestActors().stream().filter(a -> a.getActorId() == 3).collect(Collectors.toList()));
        movieToAdd.setTitle("Láttam az anyádat. Férfi volt.");

        movies.add(movieToAdd);
        return movies;
    }

    public static Collection<Ticket> getTestTickets() {
        ArrayList<Ticket> tickets = new ArrayList<>();

        return tickets;
    }

    public static Collection<Theater> getTestTheaters() {
        ArrayList<Theater> theaters = new ArrayList<>();

        Theater theaterToAdd = new Theater();
        theaterToAdd.setId(1);
        theaterToAdd.setRows(12);
        theaterToAdd.setRowsCapacity(8);
        Collection<Seat> seats = getTestSeats(12, 8, theaterToAdd);
        theaterToAdd.setSeatCollection(seats);

        theaters.add(theaterToAdd);

        return theaters;
    }

    public static Collection<Seat> getTestSeats(int rows, int seatPerRows, Theater theater) {
        ArrayList<Seat> seats = new ArrayList<>();
        Seat seatToAdd;
        int id = 1;
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < seatPerRows; j++) {
                seatToAdd = new Seat();
                seatToAdd.setId(id);
                seatToAdd.setAvailable(true);
                seatToAdd.setSeatNumber(j);
                seatToAdd.setTheater(theater);
                ++id;
            }
        }
        return seats;
    }

    public static Collection<Reservation> getTestReservations() {
        ArrayList<Reservation> reservations = new ArrayList<>();

        return reservations;
    }
}
