import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class viewController {

    @FXML
    private Button saveNewUserButton;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private PasswordField passwordField;


    // private Alert successAlert = new Alert(AlertType.INFORMATION, "Save successful");

    @FXML
    private void handleButtonAction(ActionEvent event){
        System.out.println("Saving User");

                        //   Get all the inputs
                         String firstName = String.valueOf(firstNameTextField.getText().trim());
                         String lastName = String.valueOf(lastNameTextField.getText().trim());
                         String password = String.valueOf(passwordField.getText().trim());

                        //   Console info
                         System.out.println(firstName + lastName + password + " date: ");

                        //   Saving User
                         if (UserDAO.userExists(firstName, lastName)) {
                            //   Save user
                             User user = new User(null, firstName, lastName, password, null, null);
                             int user_saved;
                             try {
                                 user_saved = UserDAO.saveUser(user);
                             } catch (SQLException e) {
                                //   TODO Auto-generated catch block
                                 e.printStackTrace();
                                 user_saved = 0;
                             }
                             if (user_saved > 0) {
                                 Alert alert = new Alert(AlertType.INFORMATION, "Save successful");
                                 alert.show();
                             }
                         } else {
                             Alert alert = new Alert(AlertType.ERROR, "User already exists!");
                             alert.show();
                         }
    }

}
