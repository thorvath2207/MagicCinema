package magiccinema.unideb.hu.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import magiccinema.unideb.hu.models.Seat;
import magiccinema.unideb.hu.models.ShowTime;
import magiccinema.unideb.hu.services.interfaces.ICinemaService;
import magiccinema.unideb.hu.utility.DialogService;
import magiccinema.unideb.hu.models.SeatUi;
import magiccinema.unideb.hu.utility.ServiceLocator;
import magiccinema.unideb.hu.utility.constans.AdditionalParameters;
import magiccinema.unideb.hu.utility.constans.Views;
import magiccinema.unideb.hu.utility.exceptions.ServiceNotFoundException;
import magiccinema.unideb.hu.utility.interfaces.IController;
import magiccinema.unideb.hu.utility.interfaces.IEntity;
import magiccinema.unideb.hu.utility.navigation.Navigation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SeatSelectorController implements IController {
    protected static Logger logger = LoggerFactory.getLogger(SeatSelectorController.class);

    private final Navigation navigationService;
    private final DialogService dialogService;
    private final ICinemaService cinemaService;

    private ShowTime selectedShowTime;

    @FXML
    private GridPane seatButtonPane;

    private List<SeatUi> selectedSeats;

    private List<Seat> seats;

    private int ticketQty;

    public SeatSelectorController() throws ServiceNotFoundException {
        this.navigationService = (Navigation) ServiceLocator.getService("Navigation");
        this.dialogService = (DialogService) ServiceLocator.getService("DialogService");
        this.cinemaService = (ICinemaService) ServiceLocator.getService("CinemaService");
    }

    @FXML
    public void handleNextClick(MouseEvent args) {

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
        this.selectedSeats = new ArrayList<>();
        this.seatButtonPane.getChildren().removeAll();
    }

    @Override
    public void setData(IEntity entity, HashMap<AdditionalParameters, Integer> addinParams) {
        ShowTime showTime = (ShowTime) entity;
        this.resetData();
        if (showTime != null) {
            this.selectedShowTime = showTime;

            this.seats = this.cinemaService.getSeatsToShowTime(this.selectedShowTime.getId());
            String url = getClass().getResource("reservedSeat.png").toExternalForm();
            Image reservedSeat = new Image(url, 20, 20, false, false);
            url = getClass().getResource("freeSeat.png").toExternalForm();
            Image freeSeat = new Image(url, 20, 20, false, false);

            this.seats
                    .stream()
                    .forEach(seat -> {
                        boolean isAvailableAtShowTime = this.cinemaService.getSeatIsAvailableAtShowTime(seat.getId(), this.selectedShowTime.getId());
                        if (!isAvailableAtShowTime) {
                            SeatUi toggleButton = new SeatUi(String.valueOf(seat.getSeatNumber()), new ImageView(reservedSeat), seat);
                            toggleButton.setDisable(true);
                            this.seatButtonPane.add(toggleButton, seat.getSeatNumber() - 1, seat.getRowNumber() - 1);
                        } else {
                            SeatUi toggleButton = new SeatUi(String.valueOf(seat.getSeatNumber()), new ImageView(freeSeat), seat);
                            if (seat.getSeatNumber() == 1) {
                                Label lbl = new Label();
                                lbl.setText(String.valueOf(seat.getRowNumber()) + ". row");
                                this.seatButtonPane.add(lbl, 0, seat.getRowNumber() - 1);
                            }
                            toggleButton.setOnAction(e -> {
                                this.updateToggleButtons(e);
                            });
                            toggleButton.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
                            this.seatButtonPane.add(toggleButton, seat.getSeatNumber(), seat.getRowNumber() - 1);
                        }
                    });
        } else {
            this.dialogService.showWarningPopup("Warning", "Value cannot be null!", "Value cannot be null!");
            this.navigationService.showViewInMainWindow(Views.MainView);
        }
    }

    private void updateToggleButtons(ActionEvent e) {
        SeatUi senderSeatUi = (SeatUi) e.getSource();

        if (senderSeatUi.isSelected()) {
            senderSeatUi.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
            this.selectedSeats.add(senderSeatUi);
        } else {
            senderSeatUi.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
            this.selectedSeats.remove(senderSeatUi);
        }

        if (this.selectedSeats.size() == this.ticketQty) {

        }
    }
}
