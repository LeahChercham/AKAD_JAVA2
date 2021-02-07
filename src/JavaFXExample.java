import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaFXExample extends Application {
    @Override
    // screen description
    public void start(Stage primaryStage){
        Button button = new Button();
        button.setText("Say 'Hello World'");
        button.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                System.out.println("Hello world!");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(button);

        Scene scene = new Scene(root,800,500);

        primaryStage.setTitle("Hello world!");
        primaryStage.setScene((scene));
        primaryStage.show();
    }
// Main Method that starts screen
    public static void main(String[] args){
        launch(args);
    }
}