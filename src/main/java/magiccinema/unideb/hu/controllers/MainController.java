package magiccinema.unideb.hu.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import magiccinema.unideb.hu.utility.DialogService;
import magiccinema.unideb.hu.utility.Navigation;
import magiccinema.unideb.hu.utility.ServiceLocator;
import magiccinema.unideb.hu.utility.exceptions.ServiceNotFoundException;
import magiccinema.unideb.hu.utility.interfaces.IController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainController implements IController {

    protected static Logger logger = LoggerFactory.getLogger(MainController.class);

    private final Navigation navigationService;
    private final DialogService dialogService;

    public MainController() throws ServiceNotFoundException {
        this.navigationService = (Navigation)ServiceLocator.getService("Navigation");
        this.dialogService = (DialogService)ServiceLocator.getService("DialogService");
    }

    @FXML
    public void handleAdminToolsClick(MouseEvent arg0) {

    }

    @FXML
    public void handleReservationClick(MouseEvent arg0) {

    }
}
