package magiccinema.unideb.hu.controllers;

import magiccinema.unideb.hu.services.interfaces.ICinemaService;
import magiccinema.unideb.hu.utility.DialogService;
import magiccinema.unideb.hu.utility.Navigation.Navigation;
import magiccinema.unideb.hu.utility.ServiceLocator;
import magiccinema.unideb.hu.utility.exceptions.ServiceNotFoundException;
import magiccinema.unideb.hu.utility.interfaces.IController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShowTimeSelectorController implements IController {
    protected static Logger logger = LoggerFactory.getLogger(RootLayoutController.class);

    private final Navigation navigationService;
    private final DialogService dialogService;
    private final ICinemaService cinemaService;

    public ShowTimeSelectorController() throws ServiceNotFoundException {
        this.navigationService = (Navigation) ServiceLocator.getService("Navigation");
        this.dialogService = (DialogService)ServiceLocator.getService("DialogService");
        this.cinemaService = (ICinemaService)ServiceLocator.getService("CinemaService");
    }

    @Override
    public void resetData() {

    }
}
