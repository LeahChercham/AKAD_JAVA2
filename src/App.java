import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class App extends Application {

    /*
     * Missing: Array All Users from DB; All DB Actions (as Imports?)
     * grid.add(what?, column number, row number)
     */

    /* Screen Description */
    @Override
    public void start(Stage primaryStage) {

        // Grid Definition
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        
        // Show Users
        
        // Add User Button
        Button button = new Button();
        button.setText("Say 'Hello World'");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello world!");
            }
        });
        
        grid.add(button, 0, 0);
        
        
        
        Scene scene = new Scene(grid, 800, 500);
        primaryStage.setTitle("User Administration");
        primaryStage.setScene((scene));
        primaryStage.show();
    }

    // Main Method that starts screen
    public static void main(String[] args) {
        launch(args);
    }
}