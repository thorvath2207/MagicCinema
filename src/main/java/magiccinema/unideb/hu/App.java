package magiccinema.unideb.hu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import magiccinema.unideb.hu.services.*;
import magiccinema.unideb.hu.utility.DialogService;
import magiccinema.unideb.hu.utility.Navigation;
import magiccinema.unideb.hu.utility.ServiceLocator;
import magiccinema.unideb.hu.utility.constans.Views;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class App extends Application {
    protected static Logger logger = LoggerFactory.getLogger(App.class);
    private Stage primaryStage;
    private static EntityManager entityManager;

    public static void main(String[] args) {
        logger.info(String.format("The magic is starting..."));

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("cinema-db");
        entityManager = factory.createEntityManager();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setOnCloseRequest((e -> Platform.exit()));
        logger.trace("Create entity manager...");



        logger.trace("Registering services...");
        Navigation navigationService = new Navigation(primaryStage);

        ServiceLocator.registerService(navigationService);
        ServiceLocator.registerService(new DialogService());

        logger.trace("Register DAO services...");
        ServiceLocator.registerService(new GenreDao(entityManager));
        ServiceLocator.registerService(new MovieDao(entityManager));
        ServiceLocator.registerService(new SeatDao(entityManager));
        ServiceLocator.registerService(new ShowTimeDao(entityManager));
        ServiceLocator.registerService(new TheaterDao(entityManager));
        ServiceLocator.registerService(new TicketDao(entityManager));

        logger.trace("Register Magic Cinema service...");
        ServiceLocator.registerService(new CinemaService());

        navigationService.initRootLayout();

        navigationService.showViewInMainWindow(Views.MainView);
        this.primaryStage.show();
    }
}
