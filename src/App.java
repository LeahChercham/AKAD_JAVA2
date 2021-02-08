import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
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
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Form for new user
        Label firstNameLabel = new Label("First Name:");
        grid.add(firstNameLabel, 0, 1);
        TextField firstNameTextField = new TextField();
        grid.add(firstNameTextField, 1, 1);

        Label lastNameLabel = new Label("Last Name:");
        grid.add(lastNameLabel, 0, 2);
        TextField lastNameTextField = new TextField();
        grid.add(lastNameTextField, 1, 2);

        Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, 0, 3);
        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 1, 3);

        Label createdTimeStampLabel = new Label("Created at:");
        grid.add(createdTimeStampLabel, 0, 5);
        TextField createdTimeStampTextField = new TextField();
        grid.add(createdTimeStampTextField, 1, 5);

        Label modifiedTimeStampLabel = new Label("Modified at:");
        grid.add(modifiedTimeStampLabel, 0, 6);
        TextField modifiedTimeStampTextField = new TextField();
        grid.add(modifiedTimeStampTextField, 1, 6);
        // ? Show Users

        // Add User Button
        Button saveNewUserButton = new Button();
        saveNewUserButton.setText("Save");
        saveNewUserButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Saving User");

                // Get all the inputs
                String firstName = String.valueOf(firstNameTextField.getText().trim());
                String lastName = String.valueOf(lastNameTextField.getText().trim());
                String password = String.valueOf(passwordField.getText().trim());

                java.util.Date utilDate = new java.util.Date();
                java.sql.Date creationTimeStamp = new java.sql.Date(utilDate.getTime());
                java.sql.Date modifiedTimeStamp = new java.sql.Date(utilDate.getTime());

                // Console info
                System.out.println(firstName + lastName + password + " date: " + creationTimeStamp + " mnodified: "
                        + modifiedTimeStamp);

                // Saving User
                if (UserDAO.userExists(firstName, lastName)) {
                    // Save user
                    User user = new User(null, firstName, lastName, password, creationTimeStamp, modifiedTimeStamp);
                    int user_saved;
                    try {
                        user_saved = UserDAO.saveUser(user);
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        user_saved = 0;
                    }
                    if (user_saved > 0) {
                        // ("Save", "Successful", AlertType.INFORMATION);
                        Alert alert = new Alert(AlertType.INFORMATION, "Save successful");
                        alert.show();
                    }
                } else {
                    // ("Error", "User already exists!", AlertType.ERROR);
                    Alert alert = new Alert(AlertType.ERROR, "User already exists!");
                    alert.show();
                }

            }

        });

        grid.add(saveNewUserButton, 3, 7);

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