package magiccinema.unideb.hu.utility.interfaces;

import javafx.scene.Node;
import magiccinema.unideb.hu.utility.constans.Views;

import java.io.IOException;

public interface IView {
    String getName();

    Node getViewNode() throws IOException;

    Views getViewType();

    IController getController();
}
