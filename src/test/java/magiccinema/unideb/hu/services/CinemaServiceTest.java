package magiccinema.unideb.hu.services;

import magiccinema.unideb.hu.services.interfaces.dao.*;
import org.junit.BeforeClass;
import org.mockito.Mock;

public class CinemaServiceTest {

    private CinemaService sut;

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

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        //ServiceLocator.registerService(new TestGenreDao());
        //ServiceLocator.registerService(new TestMovieDao());
        //ServiceLocator.registerService(new TestSeatDao());
        //ServiceLocator.registerService(new TestShowTimeDao());
        //ServiceLocator.registerService(new TestTheaterDao());
        //ServiceLocator.registerService(new TestTicketDao());
    }

    private static void setUpMockedServices() {

    }



}
