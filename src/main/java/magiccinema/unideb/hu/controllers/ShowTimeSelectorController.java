package magiccinema.unideb.hu.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import magiccinema.unideb.hu.models.Movie;
import magiccinema.unideb.hu.models.ShowTime;
import magiccinema.unideb.hu.services.interfaces.ICinemaService;
import magiccinema.unideb.hu.utility.DialogService;
import magiccinema.unideb.hu.utility.Navigation.Navigation;
import magiccinema.unideb.hu.utility.Navigation.NavigationParameter;
import magiccinema.unideb.hu.utility.ServiceLocator;
import magiccinema.unideb.hu.utility.constans.Views;
import magiccinema.unideb.hu.utility.exceptions.ServiceNotFoundException;
import magiccinema.unideb.hu.utility.interfaces.IController;
import magiccinema.unideb.hu.utility.interfaces.IEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ShowTimeSelectorController implements IController {
    protected static Logger logger = LoggerFactory.getLogger(RootLayoutController.class);

    private final Navigation navigationService;
    private final DialogService dialogService;
    private final ICinemaService cinemaService;

    private Movie selectedMovie;

    private int availableSeats;

    private ShowTime selectedShowTime;

    @FXML
    private Button nextBtn;

    @FXML
    private TableView<ShowTime> showTimesTable;

    @FXML
    private TableColumn<ShowTime, String> showTimeColumn;

    @FXML
    private Label availableSeatLabel;

    @FXML
    private Label theatreLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private Label movieTitleLabel;

    public ShowTimeSelectorController() throws ServiceNotFoundException {
        this.navigationService = (Navigation) ServiceLocator.getService("Navigation");
        this.dialogService = (DialogService) ServiceLocator.getService("DialogService");
        this.cinemaService = (ICinemaService) ServiceLocator.getService("CinemaService");
    }

    @Override
    public void resetData() {

    }

    @Override
    public void setData(IEntity entity) {
        Movie movie = (Movie) entity;

        if (movie == null) {
            this.dialogService.showWarningPopup("Warning", "Value cannot be null!", "Value cannot be null!");
            this.navigationService.showViewInMainWindow(Views.MainView);
        }

        this.selectedMovie = movie;
        this.movieTitleLabel.setText(this.selectedMovie.getTitle());

        ObservableList<ShowTime> showTimes = FXCollections.observableArrayList();
        showTimes.addAll(this.cinemaService.getUpComingShowTimesByMovieId(this.selectedMovie.getId()));
        showTimesTable.setItems(showTimes);
    }

    @FXML
    private void initialize() {

        this.showTimeColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(this.getFormatedDate(cellData.getValue().getTime())));

        this.showShowTimeDetails(null);

        this.showTimesTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> this.showShowTimeDetails(newValue));
    }

    @FXML
    public void handleNextClick(MouseEvent args) {
        this.navigationService.showViewInMainWindow(Views.SeatSelectorView, new NavigationParameter(this.selectedShowTime));
    }

    @FXML
    public void handleCancelClick(MouseEvent args) {
        ButtonType dialogResult = this.dialogService.showConfirmationDialog("Cancel", "Are you sure?", "Your changes will be lost.");
        if (dialogResult == ButtonType.OK) {
            this.navigationService.showViewInMainWindow(Views.MainView);
        }
    }

    private String getFormatedDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);
    }

    private void showShowTimeDetails(ShowTime showTime) {
        this.selectedShowTime = showTime;
        if (showTime != null) {
            this.availableSeats = this.cinemaService.getAvailableSeatsByShowTimeId(showTime.getId());

            this.timeLabel.setText(this.getFormatedDate(showTime.getTime()));
            this.availableSeatLabel.setText(availableSeats + "/" + showTime.getTheater().getSeatCollection().size());
            this.theatreLabel.setText(showTime.getTheater().getId() + ". theatre");

            if (this.availableSeats > 0) {
                this.nextBtn.setDisable(true);
            } else {
                this.nextBtn.setDisable(false);
            }
        } else {
            this.availableSeats = 0;
            this.timeLabel.setText(this.getFormatedDate(showTime.getTime()));
            this.availableSeatLabel.setText(availableSeats + "/" + showTime.getTheater().getSeatCollection().size());
            this.theatreLabel.setText(showTime.getTheater().getId() + ". theatre");

            this.nextBtn.setDisable(false);
            this.clearLabels();
        }
    }

    private void clearLabels() {

    }
}
