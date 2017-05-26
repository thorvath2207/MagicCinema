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
public class RootLayout implements IView {
    protected static Logger logger = LoggerFactory.getLogger(MainView.class);

    @Override
    public String getName() {
        return "RootLayout";
    }

    @Override
    public Node getViewNode() {
        FXMLLoader loader = new FXMLLoader(MainView.class.getResource("rootLayout.fxml"));
        try {
            return loader.load();
        } catch (IOException e) {
            logger.error(String.format("%s cant find resource", this.getName()));
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Views getViewType() {
        return Views.RootLayout;
    }
}
