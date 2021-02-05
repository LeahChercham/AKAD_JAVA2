// import javafx.application.Application;
// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.layout.StackPane;
// import javafx.stage.Stage;

// public class App extends Application {
//     @Override
//     public void start(Stage primaryStage){
//         Button button = new Button();
//         button.setText("Say 'Hello World'");
//         button.setOnAction(new EventHandler<ActionEvent>(){
//             @Override
//             public void handle(ActionEvent event){
//                 System.out.println("Hello world!");
//             }
//         });

//         StackPane root = new StackPane();
//         root.getChildren().add(button);

//         Scene scene = new Scene(root,300,250);

//         primaryStage.setTitle("Hello world!");
//         primaryStage.setScene((scene));
//         primaryStage.show();
//     }
//     public static void main(String[] args){
//         launch(args);
//     }
// }


// Min 13:30 https://www.youtube.com/watch?v=YczqOe6btEA
import java.sql.*;
public class App {
    public static void main(String[] args)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/akad_db", "root", "h$q05$NMFKHUeK53");
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery("select LastName from users");
            while(result.next()){
                System.out.println(result.getString(1));
            }
            connection.close();
        } 
        catch(Exception e){
            System.out.println(e);
        }
    }
}