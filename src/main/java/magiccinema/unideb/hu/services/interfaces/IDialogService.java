package magiccinema.unideb.hu.services.interfaces;

import javafx.scene.control.ButtonType;

public interface IDialogService extends IService {
    void showWarningPopup(String title, String header, String message);

    void showInformationPopup(String title, String header, String message);

    void showAboutPopup();

    ButtonType showConfirmationDialog(String title, String header, String message);
}
