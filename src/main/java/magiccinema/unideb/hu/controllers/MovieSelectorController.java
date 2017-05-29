package magiccinema.unideb.hu.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import magiccinema.unideb.hu.models.Actor;
import magiccinema.unideb.hu.models.Genre;
import magiccinema.unideb.hu.models.Movie;
import magiccinema.unideb.hu.services.interfaces.ICinemaService;
import magiccinema.unideb.hu.utility.DialogService;
import magiccinema.unideb.hu.utility.ServiceLocator;
import magiccinema.unideb.hu.utility.constans.AdditionalParameters;
import magiccinema.unideb.hu.utility.constans.Views;
import magiccinema.unideb.hu.utility.exceptions.ServiceNotFoundException;
import magiccinema.unideb.hu.utility.interfaces.IController;
import magiccinema.unideb.hu.utility.interfaces.IEntity;
import magiccinema.unideb.hu.utility.navigation.Navigation;
import magiccinema.unideb.hu.utility.navigation.NavigationParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class MovieSelectorController implements IController {

    protected static Logger logger = LoggerFactory.getLogger(MovieSelectorController.class);

    private final Navigation navigationService;
    private final DialogService dialogService;
    private final ICinemaService cinemaService;

    @FXML
    private TableView<Movie> moviesTable;

    @FXML
    private TableColumn<Movie, String> movieTitleColumn;

    @FXML
    private Label movieGenresLabel;

    @FXML
    private Label movieActorsLabel;

    @FXML
    private Label movieTitleLabel;

    @FXML
    private Label movieShowTimesLabel;

    @FXML
    private Button nextBtn;

    @FXML
    private ImageView coverImageView;

    private Movie selectedMovie;

    public MovieSelectorController() throws ServiceNotFoundException {
        this.navigationService = (Navigation) ServiceLocator.getService("navigation");
        this.dialogService = (DialogService) ServiceLocator.getService("DialogService");
        this.cinemaService = (ICinemaService) ServiceLocator.getService("CinemaService");
    }

    @Override
    public void resetData() {
        ObservableList<Movie> movies = FXCollections.observableArrayList();
        movies.addAll(this.cinemaService.getUpcomingMovies());
        moviesTable.setItems(movies);
    }

    @Override
    public void setData(IEntity entity, HashMap<AdditionalParameters, Integer> addinParams) {

    }

    @Override
    public void setData(List<IEntity> entities) {

    }

    @FXML
    private void initialize() {
        this.movieTitleColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getTitle()));

        this.moviesTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> this.showMovieDetails(newValue));
    }

    @FXML
    public void handleNextClick(MouseEvent args)  {
        this.navigationService.showViewInMainWindow(Views.ShowTimeSelectorView, new NavigationParameter(this.selectedMovie));
    }

    @FXML
    public void handleCancelClick(MouseEvent args) {
        this.navigationService.showViewInMainWindow(Views.MainView);
    }

    private void showMovieDetails(Movie movie) {
        this.selectedMovie = movie;
        if (movie != null) {
            this.movieTitleLabel.setText(movie.getTitle());
            this.movieGenresLabel.setText(this.getGenreLabel(movie.getGenresCollection()));
            this.movieActorsLabel.setText(this.getActorsLabel(movie.getActorsCollection()));
            this.movieShowTimesLabel.setText(String.valueOf(this.cinemaService.upComingShowTimesCounter(movie.getId())));

            if(movie.getImage() != null) {
                byte[] byteArray = movie.getImage();
                ByteArrayInputStream in = new ByteArrayInputStream(byteArray);
                try {
                    BufferedImage read = ImageIO.read(in);
                    coverImageView.setImage(SwingFXUtils.toFXImage(read, null));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                String url;
                url = this.getClass().getResource("noCover.jpg").toExternalForm();
                Image coverImage = new Image(url, 120, 160, false, false);
                coverImageView.setImage(coverImage);
            }

            this.nextBtn.setDisable(false);
        } else {
            this.clearLabels();
            this.nextBtn.setDisable(true);
        }
    }

    private String getGenreLabel(Collection<Genre> genres) {
        StringBuilder builder = new StringBuilder();
        genres.stream().forEach(g -> {
            builder.append(g.getName());
            builder.append(", ");
        });

        return builder.toString().substring(0, builder.toString().length() - 2);
    }

    private String getActorsLabel(Collection<Actor> actors) {
        StringBuilder builder = new StringBuilder();
        actors.stream().forEach(a -> {
            builder.append(a.getName());
            builder.append(", ");
        });

        return builder.toString().substring(0, builder.toString().length() - 2);
    }

    private void clearLabels() {
        this.movieTitleLabel.setText("");
        this.movieGenresLabel.setText("");
        this.movieActorsLabel.setText("");
        this.movieShowTimesLabel.setText("");
    }
}
