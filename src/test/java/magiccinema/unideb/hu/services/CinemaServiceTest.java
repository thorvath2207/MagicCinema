package magiccinema.unideb.hu.services;

import magiccinema.unideb.hu.models.*;
import magiccinema.unideb.hu.services.interfaces.dao.*;
import magiccinema.unideb.hu.testShared.testData;
import magiccinema.unideb.hu.utility.ServiceLocator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testGetUpComingShowTimes(){
        int arrange = 2;

        ArrayList<ShowTime> act = (ArrayList<ShowTime>) sut.getUpComingShowTimes();

        Assert.assertEquals(2, act);
    }

    private static void setUpMockedServices() {
        actorDaoMock = mock(IActorDao.class);
        when(actorDaoMock.getAll()).thenReturn((List<Actor>) testData.getTestActors());
        when(actorDaoMock.getName()).thenReturn("ActorDao");

        genreDaoMock = mock(IGenreDao.class);
        when(genreDaoMock.getAll()).thenReturn((List<Genre>) testData.getTestGenres());
        when(genreDaoMock.getName()).thenReturn("GenreDao");

        movieDaoMock = mock(IMovieDao.class);
        when(movieDaoMock.getAll()).thenReturn((List<Movie>) testData.getTestMovies());
        when(movieDaoMock.getName()).thenReturn("MovieDao");

        ticketDaoMock = mock(ITicketDao.class);
        when(ticketDaoMock.getAll()).thenReturn((List<Ticket>) testData.getTestTickets());
        when(ticketDaoMock.getName()).thenReturn("TicketDao");

        theaterDaoMock = mock(ITheaterDao.class);
        when(theaterDaoMock.getAll()).thenReturn((List<Theater>) testData.getTestTheaters());
        when(theaterDaoMock.getName()).thenReturn("TheaterDao");

        showTimeDaoMock = mock(IShowTimeDao.class);
        when(showTimeDaoMock.getAll()).thenReturn((List<ShowTime>) testData.getTestShowTimes());
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
}
