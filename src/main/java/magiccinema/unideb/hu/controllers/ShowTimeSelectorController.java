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
import magiccinema.unideb.hu.utility.ServiceLocator;
import magiccinema.unideb.hu.utility.constans.AdditionalParameters;
import magiccinema.unideb.hu.utility.constans.Views;
import magiccinema.unideb.hu.utility.controls.NumberSpinner;
import magiccinema.unideb.hu.utility.exceptions.ServiceNotFoundException;
import magiccinema.unideb.hu.utility.interfaces.IController;
import magiccinema.unideb.hu.utility.interfaces.IEntity;
import magiccinema.unideb.hu.utility.navigation.Navigation;
import magiccinema.unideb.hu.utility.navigation.NavigationParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

public class ShowTimeSelectorController implements IController {

    protected static Logger logger = LoggerFactory.getLogger(ShowTimeSelectorController.class);

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

    @FXML
    private NumberSpinner ticketsNumberSpinner;

    public ShowTimeSelectorController() throws ServiceNotFoundException {
        this.navigationService = (Navigation) ServiceLocator.getService("navigation");
        this.dialogService = (DialogService) ServiceLocator.getService("DialogService");
        this.cinemaService = (ICinemaService) ServiceLocator.getService("CinemaService");
    }

    @Override
    public void resetData() {

    }

    @Override
    public void setData(IEntity entity, HashMap<AdditionalParameters, Integer> addinParams) {
        Movie movie = (Movie) entity;

        if (movie == null) {
            this.dialogService.showWarningPopup("Warning", "Value cannot be null!", "Value cannot be null!");
            this.navigationService.showViewInMainWindow(Views.MainView);
            return;
        }

        this.selectedMovie = movie;
        this.movieTitleLabel.setText(this.selectedMovie.getTitle());

        ObservableList<ShowTime> showTimes = FXCollections.observableArrayList();
        showTimes.addAll(this.cinemaService.getUpComingShowTimesByMovieId(this.selectedMovie.getId()));
        this.showTimesTable.setItems(showTimes);
    }

    @Override
    public void setData(List<IEntity> entities) {

    }

    @FXML
    private void initialize() {
        this.showTimeColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(this.getFormatedDate(cellData.getValue().getTime())));

        this.showShowTimeDetails(null);

        this.showTimesTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> this.showShowTimeDetails(newValue));

        this.ticketsNumberSpinner.numberProperty().addListener((observable, oldValue, newValue) -> {
            if (this.availableSeats > 0 && newValue.intValue() <= this.availableSeats) {
                this.nextBtn.setDisable(false);
            } else {
                this.nextBtn.setDisable(true);
            }
        });
    }

    @FXML
    public void handleNextClick(MouseEvent args) {
        NavigationParameter parameter = new NavigationParameter(this.selectedShowTime);
        parameter.getAdditionalParameters().put(AdditionalParameters.TicketQty, this.ticketsNumberSpinner.getNumber().intValue());
        this.navigationService.showViewInMainWindow(Views.SeatSelectorView, parameter);
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

        this.ticketsNumberSpinner.setNumber(BigDecimal.valueOf(0));

        if (showTime != null) {
            this.availableSeats = this.cinemaService.getAvailableSeatsByShowTimeId(showTime.getId());

            this.timeLabel.setText(this.getFormatedDate(showTime.getTime()));
            this.availableSeatLabel.setText(this.availableSeats + "/" + showTime.getTheater().getSeatCollection().size());
            this.theatreLabel.setText(showTime.getTheater().getId() + ". theatre");
        } else {
            this.availableSeats = 0;
            this.timeLabel.setText("");
            this.availableSeatLabel.setText("0/0");
            this.theatreLabel.setText("");

            this.nextBtn.setDisable(true);
            this.clearLabels();
        }
    }

    private void clearLabels() {

    }
}
