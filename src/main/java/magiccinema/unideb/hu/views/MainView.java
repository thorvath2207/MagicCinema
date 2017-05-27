package magiccinema.unideb.hu.views;

import com.google.auto.service.AutoService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import magiccinema.unideb.hu.utility.constans.Views;
import magiccinema.unideb.hu.utility.interfaces.IController;
import magiccinema.unideb.hu.utility.interfaces.IView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@AutoService(IView.class)
public class MainView implements IView {
    protected static Logger logger = LoggerFactory.getLogger(MainView.class);

    private IController controller;

    public MainView() {
        logger.trace("MainView constructed.");
        this.initialize();
    }

    @Override
    public String getName() {
        return "mainview";
    }

    @Override
    public Views getViewType() {
        return Views.MainView;
    }

    @Override
    public IController getController() {
        return this.controller;
    }

    @Override
    public Node getViewNode() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainView.class.getResource("mainView.fxml"));

        Node node = loader.load();
        this.controller = loader.getController();
        return node;

    }

    private void initialize() {

    }
}
