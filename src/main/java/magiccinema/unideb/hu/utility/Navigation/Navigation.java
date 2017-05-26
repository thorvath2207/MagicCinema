package magiccinema.unideb.hu.utility.Navigation;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import magiccinema.unideb.hu.services.interfaces.INavigation;
import magiccinema.unideb.hu.utility.ServiceLocator;
import magiccinema.unideb.hu.utility.constans.Views;
import magiccinema.unideb.hu.utility.interfaces.IView;
import org.slf4j.LoggerFactory;

import java.util.ServiceLoader;

public class Navigation implements INavigation {
    protected static org.slf4j.Logger logger = LoggerFactory.getLogger(ServiceLocator.class);
    private ServiceLoader<IView> views;
    private Stage primaryStage;
    private BorderPane rootLayout;

    public Navigation(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.views = ServiceLoader.load(IView.class);
        logger.trace("Navigation service loaded.");
    }

    public void showViewInMainWindow(Views viewName, boolean resetData, NavigationParameter parameter) {
        logger.debug(String.format("Navigation requested to %s", viewName.toString()));
        IView view = this.findView(viewName);

        if (view != null) {
            Node viewNode = view.getViewNode();

            if (viewNode != null) {
                if (view.getController() != null && resetData) {
                    view.getController().resetData();
                }

                if (view.getController() != null && parameter != null){
                    view.getController().setData(parameter.getEntity());
                }

                this.rootLayout.setCenter(viewNode);
            }
        }
    }

    public void showViewInMainWindow(Views viewName, NavigationParameter parameter) {
        this.showViewInMainWindow(viewName, false, parameter);
    }

    public void showViewInMainWindow(Views viewName) {
        this.showViewInMainWindow(viewName, false, null);
    }

    public void initRootLayout() {
        if (this.rootLayout != null) {
            return;
        }

        IView rootView = this.findView(Views.RootLayout);

        if (rootView != null) {
            this.rootLayout = (BorderPane) rootView.getViewNode();
            if (this.rootLayout != null) {
                Scene scene = new Scene(this.rootLayout);
                this.primaryStage.setScene(scene);
            }
        }
    }

    private IView findView(Views viewName) {
        for (IView view : this.views) {
            if (view.getViewType().toString().equalsIgnoreCase(viewName.toString())) {
                return view;
            }
        }

        return null;
    }

    public void requestClose() {
        this.primaryStage.close();
    }

    @Override
    public String getName() {
        return "Navigation";
    }
}
