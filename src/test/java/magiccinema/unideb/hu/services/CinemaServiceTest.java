package magiccinema.unideb.hu.services;

import magiccinema.unideb.hu.models.*;
import magiccinema.unideb.hu.services.interfaces.dao.*;
import magiccinema.unideb.hu.testShared.testData;
import magiccinema.unideb.hu.utility.ServiceLocator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CinemaServiceTest {

    @Mock
    private static IActorDao actorDaoMock;
    @Mock
    private static IGenreDao genreDaoMock;
    @Mock
    private static IMovieDao movieDaoMock;
    @Mock
    private static ITicketDao ticketDaoMock;
    @Mock
    private static ITheaterDao theaterDaoMock;
    @Mock
    private static IShowTimeDao showTimeDaoMock;
    @Mock
    private static ISeatDao seatDaoMock;
    private static CinemaService sut;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        setUpMockedServices();
        registerMockedServices();

        sut = new CinemaService();
    }

    private static void setUpMockedServices() {
        actorDaoMock = mock(IActorDao.class);
        when(actorDaoMock.getAll()).thenReturn((List<Actor>) testData.getTestActors());
        when(actorDaoMock.getById(anyInt()))
                .thenAnswer((Answer<Actor>) invocation -> testData.getTestActors()
                        .stream()
                        .filter(a -> a.getActorId() == (int) invocation.getArguments()[0])
                        .findFirst()
                        .get());
        when(actorDaoMock.getName()).thenReturn("ActorDao");

        genreDaoMock = mock(IGenreDao.class);
        when(genreDaoMock.getAll()).thenReturn((List<Genre>) testData.getTestGenres());
        when(genreDaoMock.getById(anyInt()))
                .thenAnswer((Answer<Genre>) invocation -> testData.getTestGenres()
                        .stream()
                        .filter(a -> a.getGenreId() == (int) invocation.getArguments()[0])
                        .findFirst()
                        .get());
        when(genreDaoMock.getName()).thenReturn("GenreDao");

        movieDaoMock = mock(IMovieDao.class);
        when(movieDaoMock.getAll()).thenReturn((List<Movie>) testData.getTestMovies());
        when(movieDaoMock.getById(anyInt()))
                .thenAnswer((Answer<Movie>) invocation -> testData.getTestMovies()
                        .stream()
                        .filter(a -> a.getId() == (int) invocation.getArguments()[0])
                        .findFirst()
                        .get());
        when(movieDaoMock.getName()).thenReturn("MovieDao");

        ticketDaoMock = mock(ITicketDao.class);
        when(ticketDaoMock.getAll()).thenReturn((List<Ticket>) testData.getTestTickets());
        when(ticketDaoMock.getById(anyInt()))
                .thenAnswer((Answer<Ticket>) invocation -> testData.getTestTickets()
                        .stream()
                        .filter(a -> a.getId() == (int) invocation.getArguments()[0])
                        .findFirst()
                        .get());
        when(ticketDaoMock.getName()).thenReturn("TicketDao");

        theaterDaoMock = mock(ITheaterDao.class);
        when(theaterDaoMock.getAll()).thenReturn((List<Theater>) testData.getTestTheaters());
        when(theaterDaoMock.getById(anyInt()))
                .thenAnswer((Answer<Theater>) invocation -> testData.getTestTheaters()
                        .stream()
                        .filter(a -> a.getId() == (int) invocation.getArguments()[0])
                        .findFirst()
                        .get());
        when(theaterDaoMock.getName()).thenReturn("TheaterDao");

        showTimeDaoMock = mock(IShowTimeDao.class);
        when(showTimeDaoMock.getAll()).thenReturn((List<ShowTime>) testData.getTestShowTimes());
        when(showTimeDaoMock.getById(anyInt()))
                .thenAnswer((Answer<ShowTime>) invocation -> testData.getTestShowTimes()
                        .stream()
                        .filter(a -> a.getId() == (int) invocation.getArguments()[0])
                        .findFirst()
                        .get());
        when(showTimeDaoMock.getName()).thenReturn("ShowTimeDao");

        seatDaoMock = mock(ISeatDao.class);
        when(seatDaoMock.getName()).thenReturn("SeatDao");
    }

    private static void registerMockedServices() {
        ServiceLocator.registerService(actorDaoMock);
        ServiceLocator.registerService(genreDaoMock);
        ServiceLocator.registerService(movieDaoMock);
        ServiceLocator.registerService(ticketDaoMock);
        ServiceLocator.registerService(theaterDaoMock);
        ServiceLocator.registerService(showTimeDaoMock);
        ServiceLocator.registerService(seatDaoMock);
    }

    @Test
    public void testGetUpComingShowTimes() {
        int arrange = 2;

        ArrayList<ShowTime> act = (ArrayList<ShowTime>) sut.getUpComingShowTimes();

        Assert.assertEquals(arrange, act.size());
    }

    @Test
    public void testGetUpComingMovies() {
        int arrange = 1;

        ArrayList<Movie> act = (ArrayList<Movie>) sut.getUpcomingMovies();

        Assert.assertEquals(arrange, act.size());
    }

    @Test
    public void testSeatIsAvailableAtShowTimeShouldTrue() {
        boolean arrange = true;

        boolean act = sut.getSeatIsAvailableAtShowTime(3, 1);

        Assert.assertEquals(arrange, act);
    }

    @Test
    public void testSeatIsAvailableAtShowTimeShouldFalse() {
        boolean arrange = false;

        boolean act = sut.getSeatIsAvailableAtShowTime(4, 1);

        Assert.assertEquals(arrange, act);
    }

    @Test
    public void testGetAvailableSeatsByShowTimeId() {
        int arrange = 94;

        int act = sut.getAvailableSeatsByShowTimeId(1);

        Assert.assertEquals(arrange, act);
    }

    @Test
    public void testgetShowTimeById() {
        int arrange = 1;

        int act = sut.getShowTimeById(1).getId();

        Assert.assertEquals(arrange, act);
    }
}
