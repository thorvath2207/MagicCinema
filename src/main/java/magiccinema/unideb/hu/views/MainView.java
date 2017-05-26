package magiccinema.unideb.hu.views;

import com.google.auto.service.AutoService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import magiccinema.unideb.hu.utility.constans.Views;
import magiccinema.unideb.hu.utility.interfaces.IView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@AutoService(IView.class)
public class MainView implements IView {
    protected static Logger logger = LoggerFactory.getLogger(MainView.class);

    public MainView() {
        logger.trace("MainView ctor.");
        this.initialize();
    }

    @Override
    public String getName() {
        return "mainview";
    }

    @Override
    public Views getViewType() { return Views.MainView; }

    @Override
    public Node getViewNode() {
        FXMLLoader loader = new FXMLLoader(MainView.class.getResource("mainView.fxml"));
        try {
            return loader.load();
        } catch (IOException e) {
            logger.error("%s cant find resource", getName());
            return null;
        }
    }

    private void initialize() {

    }
}
