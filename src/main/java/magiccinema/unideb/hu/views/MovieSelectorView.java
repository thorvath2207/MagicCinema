package magiccinema.unideb.hu.views;

import com.google.auto.service.AutoService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import magiccinema.unideb.hu.controllers.MovieSelectorController;
import magiccinema.unideb.hu.utility.constans.Views;
import magiccinema.unideb.hu.utility.interfaces.IController;
import magiccinema.unideb.hu.utility.interfaces.IView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@AutoService(IView.class)
public class MovieSelectorView implements IView {
    protected static Logger logger = LoggerFactory.getLogger(MovieSelectorController.class);

    private IController controller;

    public MovieSelectorView() {
        logger.trace("MovieSelectorView constructed.");
        this.initialize();
    }

    @Override
    public String getName() {
        return "MovieSelectorView";
    }

    @Override
    public Node getViewNode() {
        FXMLLoader loader = new FXMLLoader(MainView.class.getResource("movieSelectorView.fxml"));
        try {
            Node node = loader.load();
            this.controller = loader.getController();
            return node;
        } catch (IOException e) {
            logger.error("%s cant find resource", getName());
            return null;
        }
    }

    @Override
    public Views getViewType() {
        return Views.MovieSelectorView;
    }

    @Override
    public IController getController() {
        return this.controller;
    }

    private void initialize() {

    }
}
