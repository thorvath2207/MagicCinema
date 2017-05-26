package magiccinema.unideb.hu.utility.interfaces;

import javafx.scene.Node;
import magiccinema.unideb.hu.utility.constans.Views;

public interface IView {
    String getName();

    Node getViewNode();

    Views getViewType();
}
