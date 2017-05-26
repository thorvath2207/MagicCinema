package magiccinema.unideb.hu.utility;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import magiccinema.unideb.hu.App;
import magiccinema.unideb.hu.services.interfaces.IDialogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class DialogService implements IDialogService {

    protected static Logger logger = LoggerFactory.getLogger(App.class);

    @Override
    public void showWarningPopup(String title, String header, String message) {
        logger.trace("Show warinng popup.");
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        alert.showAndWait();
    }

    @Override
    public void showInformationPopup(String title, String header, String message) {
        logger.trace("Show info popup.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        alert.showAndWait();
    }

    @Override
    public void showAboutPopup() {
        logger.trace("Show about popup.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About magic cinema");
        alert.setHeaderText(null);
        alert.setContentText("asd asd.");

        alert.showAndWait();
    }

    @Override
    public ButtonType showConfirmationDialog(String title, String header, String message) {
        logger.trace("Show confirmation popup.");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();

        return result.get();
    }

    @Override
    public String getName() {
        return "DialogService";
    }
}
