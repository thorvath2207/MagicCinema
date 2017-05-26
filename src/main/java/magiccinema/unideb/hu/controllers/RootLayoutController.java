package magiccinema.unideb.hu.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import magiccinema.unideb.hu.utility.DialogService;
import magiccinema.unideb.hu.utility.Navigation;
import magiccinema.unideb.hu.utility.ServiceLocator;
import magiccinema.unideb.hu.utility.constans.Views;
import magiccinema.unideb.hu.utility.exceptions.ServiceNotFoundException;
import magiccinema.unideb.hu.utility.interfaces.IController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RootLayoutController implements IController {

    protected static Logger logger = LoggerFactory.getLogger(RootLayoutController.class);

    private final Navigation navigationService;
    private final DialogService dialogService;

    public RootLayoutController() throws ServiceNotFoundException {
        this.navigationService = (Navigation)ServiceLocator.getService("Navigation");
        this.dialogService = (DialogService)ServiceLocator.getService("DialogService");
    }

    @FXML
    public void onBackToMainMenuItem(ActionEvent e){
        this.navigationService.showViewInMainWindow(Views.MainView);
    }

    @FXML
    public void onCloseMenuItem(ActionEvent e){
        this.navigationService.requestClose();
    }

    @FXML
    public void onAboutMenuItem(ActionEvent e){
        this.dialogService.showAboutPopup();
    }

    @Override
    public void resetData() {

    }
}
