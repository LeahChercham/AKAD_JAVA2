import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader viewSceneLoader = new FXMLLoader(getClass().getResource("view.fxml"));
        Parent root = (Parent) viewSceneLoader.load();

        viewController ctrlPointer = (viewController)
        viewSceneLoader.getController();

        Scene scene = new Scene(root, 1000, 500);

        stage.setTitle("User Administration");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}