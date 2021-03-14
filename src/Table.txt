import java.util.Date;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Table extends Application {
    @Override
    // screen description
    public void start(Stage primaryStage){


        TableView<User> table = new TableView<>();
        table.setEditable(true);;

        TableColumn<User, Integer> userIdColumn = new TableColumn<>("User ID");
        TableColumn<User, String> firstNameColumn = new TableColumn<>("First Name");
        TableColumn<User, String> lastNameColumn = new TableColumn<>("Last Name");
        TableColumn<User, String> passwordColumn = new TableColumn<>("Password");
        TableColumn<User, Date> createdAtColumn = new TableColumn<>("Created at");
        TableColumn<User, Date> modifiedAtColumn = new TableColumn<>("Modified at");
        
        table.getColumns().addAll(userIdColumn, firstNameColumn, lastNameColumn, passwordColumn, createdAtColumn, modifiedAtColumn);



        /* getAll 
        DB
        try 
        while result.next
        for(int i = 1; i < )
        row.add()
        table.add(row)
 */

        StackPane root = new StackPane();
        root.getChildren().add(table);
        Scene scene = new Scene(root,800,500);

        primaryStage.setTitle("Table");
        primaryStage.setScene((scene));
        primaryStage.show();
    }
// Main Method that starts screen
    public static void main(String[] args){
        launch(args);
    }
}