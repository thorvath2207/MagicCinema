package magiccinema.unideb.hu.utility.navigation;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import magiccinema.unideb.hu.services.interfaces.INavigation;
import magiccinema.unideb.hu.utility.ServiceLocator;
import magiccinema.unideb.hu.utility.constans.Views;
import magiccinema.unideb.hu.utility.interfaces.IView;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ServiceLoader;

public class Navigation implements INavigation {
    protected static org.slf4j.Logger logger = LoggerFactory.getLogger(ServiceLocator.class);
    private ServiceLoader<IView> views;
    private Stage primaryStage;
    private BorderPane rootLayout;

    public Navigation(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.views = ServiceLoader.load(IView.class);
        logger.trace("navigation service loaded.");
    }

    public void showViewInMainWindow(Views viewName, boolean resetData, NavigationParameter parameter) throws IOException {
        logger.debug(String.format("navigation requested to %s", viewName.toString()));
        IView view = this.findView(viewName);

        if (view != null) {
            Node viewNode = view.getViewNode();

            if (viewNode != null) {
                if (view.getController() != null && resetData) {
                    view.getController().resetData();
                }

                if (view.getController() != null && parameter != null) {
                    if (parameter.getEntities() != null) {
                        view.getController().setData(parameter.getEntities());
                    }
                    view.getController().setData(parameter.getEntity(), parameter.getAdditionalParameters());
                }

                this.rootLayout.setCenter(viewNode);
            }
        }
    }

    public void showViewInMainWindow(Views viewName, NavigationParameter parameter) {
        try {
            this.showViewInMainWindow(viewName, false, parameter);
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void showViewInMainWindow(Views viewName, boolean resetData) {
        try {
            this.showViewInMainWindow(viewName, resetData, null);
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void showViewInMainWindow(Views viewName) {
        try {
            this.showViewInMainWindow(viewName, false, null);
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void initRootLayout() throws IOException {
        if (this.rootLayout != null) {
            return;
        }

        IView rootView = this.findView(Views.RootLayout);

        if (rootView != null) {
            this.rootLayout = (BorderPane) rootView.getViewNode();
            if (this.rootLayout != null) {
                Scene scene = new Scene(this.rootLayout);
                scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
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
        return "navigation";
    }
}
