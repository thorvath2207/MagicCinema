package magiccinema.unideb.hu.models;

import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;

public class SeatUi extends ToggleButton {
    private Seat seat;

    public SeatUi(Seat seat){
        this.seat = seat;
    }

    public SeatUi(String s, ImageView imageView, Seat seat) {
        super(s, imageView);
        this.seat = seat;
    }

    public Seat getSeat() {
        return this.seat;
    }
}
