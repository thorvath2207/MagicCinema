package magiccinema.unideb.hu.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import magiccinema.unideb.hu.models.Reservation;
import magiccinema.unideb.hu.models.Seat;
import magiccinema.unideb.hu.models.ShowTime;
import magiccinema.unideb.hu.models.Ticket;
import magiccinema.unideb.hu.services.interfaces.ICinemaService;
import magiccinema.unideb.hu.utility.DialogService;
import magiccinema.unideb.hu.utility.ServiceLocator;
import magiccinema.unideb.hu.utility.constans.AdditionalParameters;
import magiccinema.unideb.hu.utility.constans.Views;
import magiccinema.unideb.hu.utility.exceptions.ServiceNotFoundException;
import magiccinema.unideb.hu.utility.interfaces.IController;
import magiccinema.unideb.hu.utility.interfaces.IEntity;
import magiccinema.unideb.hu.utility.navigation.Navigation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReservationCreateController implements IController {

    protected static Logger logger = LoggerFactory.getLogger(ReservationCreateController.class);

    private final Navigation navigationService;
    private final DialogService dialogService;
    private final ICinemaService cinemaService;

    @FXML
    private TableView<Seat> seatsListTable;

    @FXML
    private TableColumn<Seat, String> seatStringTableColumn;

    @FXML
    private Label movieLabel;

    @FXML
    private TextField nameTextField;

    private List<Ticket> tickets;

    private List<Seat> seats;

    private ShowTime showTime;

    public ReservationCreateController() throws ServiceNotFoundException {
        this.navigationService = (Navigation) ServiceLocator.getService("Navigation");
        this.dialogService = (DialogService) ServiceLocator.getService("DialogService");
        this.cinemaService = (ICinemaService) ServiceLocator.getService("CinemaService");
    }

    @FXML
    private void initialize() {
        this.seatStringTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(getSeatFormattedString(cellData.getValue())));
    }

    private String getSeatFormattedString(Seat seat) {
        StringBuilder builder = new StringBuilder();
        builder.append(seat.getRowNumber());
        builder.append(". row --- ");
        builder.append(seat.getSeatNumber());
        builder.append(". seat");

        return builder.toString();
    }

    private boolean validForm() {
        if (this.nameTextField.getText().isEmpty()) {
            return false;
        }

        return true;
    }

    private void createTickets(Reservation reservation) {
        this.tickets = new ArrayList<>();
        this.seats.forEach(seat -> {
            Ticket ticket = new Ticket();
            ticket.setSeat(seat);
            ticket.setShowTime(this.showTime);
            ticket.setReservation(reservation);
            this.tickets.add(ticket);
        });
    }

    @FXML
    public void handleNextClick(MouseEvent event) {
        if (this.validForm()) {
            Reservation reservationToCreate = new Reservation();
            this.createTickets(reservationToCreate);
            reservationToCreate.setTicketCollection(this.tickets);
            reservationToCreate.setName(this.nameTextField.getText());
            reservationToCreate.setReservationDate(LocalDateTime.now());
            this.cinemaService.reservationCreate(reservationToCreate);

            this.dialogService.showInformationPopup("Reservation success", "you are created reservation", "");
            this.navigationService.showViewInMainWindow(Views.MainView);
        }
    }

    @FXML
    public void handleCancelClick(MouseEvent args) {
        ButtonType dialogResult = this.dialogService.showConfirmationDialog("Cancel", "Are you sure?", "Your changes will be lost.");
        if (dialogResult == ButtonType.OK) {
            this.navigationService.showViewInMainWindow(Views.MainView);
        }
    }

    @Override
    public void resetData() {

    }

    @Override
    public void setData(IEntity entity, HashMap<AdditionalParameters, Integer> addinParams) {
        this.showTime = this.cinemaService.getShowTimeById(addinParams.get(AdditionalParameters.ShowTimeId));
    }

    @Override
    public void setData(List<IEntity> entities) {
        ObservableList<Seat> seats = FXCollections.observableArrayList();
        this.seats = new ArrayList<>();
        entities.forEach(iEntity -> this.seats.add((Seat) iEntity));
        seats.addAll(this.seats);
        this.seatsListTable.setItems(seats);
    }
}
