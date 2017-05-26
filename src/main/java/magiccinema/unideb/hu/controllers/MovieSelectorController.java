package magiccinema.unideb.hu.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import magiccinema.unideb.hu.models.Movie;
import magiccinema.unideb.hu.services.interfaces.ICinemaService;
import magiccinema.unideb.hu.utility.DialogService;
import magiccinema.unideb.hu.utility.Navigation.Navigation;
import magiccinema.unideb.hu.utility.ServiceLocator;
import magiccinema.unideb.hu.utility.exceptions.ServiceNotFoundException;
import magiccinema.unideb.hu.utility.interfaces.IController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MovieSelectorController implements IController {
    protected static Logger logger = LoggerFactory.getLogger(RootLayoutController.class);

    private final Navigation navigationService;
    private final DialogService dialogService;
    private final ICinemaService cinemaService;

    @FXML
    private TableView<Movie> moviesTable;

    @FXML
    private TableColumn<Movie, String> movieTitleColumn;

    @FXML
    private Label genreLabel;

    @FXML
    private Label actorsLabel;

    @FXML
    private Label movieTitleLabel;

    public MovieSelectorController () throws ServiceNotFoundException {
        this.navigationService = (Navigation) ServiceLocator.getService("Navigation");
        this.dialogService = (DialogService)ServiceLocator.getService("DialogService");
        this.cinemaService = (ICinemaService)ServiceLocator.getService("CinemaService");
    }

    @Override
    public void resetData() {
        ObservableList<Movie> movies = FXCollections.observableArrayList();
        movies.addAll(this.cinemaService.getUpcomingMovies());
        moviesTable.setItems(movies);
    }

    @FXML
    private void initialize() {
        movieTitleColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getTitle()));
    }
}
