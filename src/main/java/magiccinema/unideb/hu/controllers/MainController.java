package magiccinema.unideb.hu.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import magiccinema.unideb.hu.models.Genre;
import magiccinema.unideb.hu.services.GenreDao;
import magiccinema.unideb.hu.utility.ServiceLocator;
import magiccinema.unideb.hu.utility.exceptions.ServiceNotFoundException;
import magiccinema.unideb.hu.utility.interfaces.IController;

import java.util.List;

public class MainController implements IController {

    @FXML
    public void handleAdminToolsClick(MouseEvent arg0) throws ServiceNotFoundException {
        GenreDao dao = (GenreDao)ServiceLocator.getService("GenreDao");
        Genre toAdd = new Genre("Action");
        dao.add(toAdd);
        List<Genre> genres = dao.getAll();
        System.out.println(genres.size());
    }
}
