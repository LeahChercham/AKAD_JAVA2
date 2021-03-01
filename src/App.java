import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class App extends Application {

    // TABLE VIEW AND DATA
    private TableView<User> tableView = new TableView();
    private TableColumn<User, Integer> userIdColumn = new TableColumn<>("User ID");
    private TableColumn<User, String> firstNameColumn = new TableColumn<>("First Name");
    private TableColumn<User, String> lastNameColumn = new TableColumn<>("Last Name");
    private TableColumn<User, String> passwordColumn = new TableColumn<>("Password");
    private TableColumn<User, Date> createdAtColumn = new TableColumn<>("Created at");
    private TableColumn<User, Date> modifiedAtColumn = new TableColumn<>("Modified at");
    private ArrayList<User> users;
    private ObservableList<User> data = FXCollections.observableArrayList();

    /*
     * Missing: Array All Users from DB; All DB Actions (as Imports?)
     * grid.add(what?, column number, row number)
     */
    // CONNECTION DB
    public void buildData() {
        users = UserDAO.getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            // String firstName = users.get(i).getfirstName();
            // String lastName = users.get(i).getlastName();
            // Integer userId = users.get(i).getuserId();
            // Date creationTimeStamp = users.get(i).getcreatedTimeStamp();
            // Date modifiedTimeStamp = users.get(i).getmodifiedTimeStamp();

            // tableView.add(userId, firstName, lastName, creationTimeStamp,
            // modifiedTimeStamp);
            data.add(users.get(i));
        }
        tableView.setItems(data);
    }

    /* Screen Description */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
    
        Scene scene = new Scene(root, 800, 500);
    
        stage.setTitle("User Administration");
        stage.setScene(scene);
        stage.show();
    }



    // @Override
    // public void start(Stage primaryStage) {

    //     // tableView;
    //     buildData();

    //     /*
    //      * Display Add Form: Displays the add user form and save button Description of
    //      * form and button Save method
    //      */
    //     Button displayAddFormButton = new Button();
    //     displayAddFormButton.setText("ADD");
    //     displayAddFormButton.setOnAction(new EventHandler<ActionEvent>() {
    //         @Override
    //         public void handle(ActionEvent event) {
    //             // Form for new user
    //             Label firstNameLabel = new Label("First Name:");
    //             grid.add(firstNameLabel, 0, 1);
    //             TextField firstNameTextField = new TextField();
    //             grid.add(firstNameTextField, 1, 1);

    //             Label lastNameLabel = new Label("Last Name:");
    //             grid.add(lastNameLabel, 0, 2);
    //             TextField lastNameTextField = new TextField();
    //             grid.add(lastNameTextField, 1, 2);

    //             Label passwordLabel = new Label("Password:");
    //             grid.add(passwordLabel, 0, 3);
    //             PasswordField passwordField = new PasswordField();
    //             grid.add(passwordField, 1, 3);

    //             // Add User Button
    //             Button saveNewUserButton = new Button();
    //             saveNewUserButton.setText("Save");

    //             saveNewUserButton.setOnAction(new EventHandler<ActionEvent>() {

    //                 @Override
    //                 public void handle(ActionEvent event) {
    //                     System.out.println("Saving User");

    //                     // Get all the inputs
    //                     String firstName = String.valueOf(firstNameTextField.getText().trim());
    //                     String lastName = String.valueOf(lastNameTextField.getText().trim());
    //                     String password = String.valueOf(passwordField.getText().trim());

    //                     // Console info
    //                     System.out.println(firstName + lastName + password + " date: ");

    //                     // Saving User
    //                     if (UserDAO.userExists(firstName, lastName)) {
    //                         // Save user
    //                         User user = new User(null, firstName, lastName, password, null, null);
    //                         int user_saved;
    //                         try {
    //                             user_saved = UserDAO.saveUser(user);
    //                         } catch (SQLException e) {
    //                             // TODO Auto-generated catch block
    //                             e.printStackTrace();
    //                             user_saved = 0;
    //                         }
    //                         if (user_saved > 0) {
    //                             // ("Save", "Successful", AlertType.INFORMATION);
    //                             Alert alert = new Alert(AlertType.INFORMATION, "Save successful");
    //                             alert.show();
    //                         }
    //                     } else {
    //                         // ("Error", "User already exists!", AlertType.ERROR);
    //                         Alert alert = new Alert(AlertType.ERROR, "User already exists!");
    //                         alert.show();
    //                     }

    //                 }

    //             });

    //             grid.add(saveNewUserButton, 3, 7);
    //         }
    //     });

    //     grid.add(displayAddFormButton, 0, 8);


    // }

    // MAIN EXECUTOR
    public static void main(String[] args) {
        launch(args);
    }
}