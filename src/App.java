import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    /*
     * Missing: Array All Users from DB; All DB Actions (as Imports?)
     * grid.add(what?, column number, row number)
     */

    /* Screen Description */
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader viewSceneLoader = new FXMLLoader(getClass().getResource("view.fxml"));
        Parent root = (Parent) viewSceneLoader.load();

        viewController ctrlPointer = (viewController)
        viewSceneLoader.getController();

        Scene scene = new Scene(root, 800, 500);

        stage.setTitle("User Administration");
        stage.setScene(scene);
        stage.show();
    }

    // MAIN EXECUTOR
    public static void main(String[] args) {
        launch(args);
    }
}