package magiccinema.unideb.hu;

import javafx.application.Application;
import javafx.stage.Stage;
import magiccinema.unideb.hu.services.GenreDao;
import magiccinema.unideb.hu.utility.Navigation;
import magiccinema.unideb.hu.utility.ServiceLocator;
import magiccinema.unideb.hu.utility.constans.Views;
import magiccinema.unideb.hu.utility.exceptions.ServiceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class App extends Application {
    private Stage primaryStage;
    private Navigation navigationService;

    protected static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws ServiceNotFoundException {
        logger.info(String.format("The magic is starting..."));
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.navigationService = new Navigation(primaryStage);
        logger.trace("Registering services...");
        ServiceLocator.registerService(this.navigationService);
        ServiceLocator.registerService(new GenreDao());


        this.navigationService.initRootLayout();
        this.navigationService.showViewInMainWindow(Views.MainView);
        this.primaryStage.show();
    }
}
