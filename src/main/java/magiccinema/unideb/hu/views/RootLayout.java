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
public class RootLayout implements IView {
    protected static Logger logger = LoggerFactory.getLogger(RootLayout.class);

    private IController controller;

    public RootLayout() {
        logger.trace("RootLayout constructed");
        this.initialize();
    }

    @Override
    public String getName() {
        return "RootLayout";
    }

    @Override
    public Node getViewNode() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainView.class.getResource("rootLayout.fxml"));

        Node node = loader.load();
        this.controller = loader.getController();
        return node;
    }

    @Override
    public Views getViewType() {
        return Views.RootLayout;
    }

    @Override
    public IController getController() {
        return this.controller;
    }

    private void initialize() {

    }
}
