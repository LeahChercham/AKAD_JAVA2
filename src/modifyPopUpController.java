import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class modifyPopUpController {

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private Button saveChangesButton;
    @FXML
    private Button cancelChangesButton;

    User modifiedUser;

    public void transferMessage(User user) {
        modifiedUser = user;

        firstNameTextField.setText(user.getFirstName());
        lastNameTextField.setText(user.getLastName());
    }

    @FXML
    private void handleSaveChangesButtonAction(ActionEvent event) {
        modifiedUser.setFirstName(String.valueOf(firstNameTextField.getText().trim()));
        modifiedUser.setLastName(String.valueOf(lastNameTextField.getText().trim()));

        int user_modified;
        try {
            user_modified = UserDAO.modifyUser(modifiedUser);
        } catch (SQLException e) {
            e.printStackTrace();
            user_modified = 0;
        }

        if (user_modified > 0) {
            Alert alert = new Alert(AlertType.INFORMATION, "Modification successful");
            alert.show();

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                viewController viewController = fxmlLoader.getController();
                viewController.refreshData();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Closing stage:");
            closeStage();

        } else {
            Alert alert = new Alert(AlertType.ERROR, "Could not modify user");
            alert.show();
            closeStage();

        }
    }

    @FXML
    private void handleCancelChangesButtonAction(ActionEvent event) {
        closeStage();
    }

    private void closeStage() {
        Stage stage = (Stage) saveChangesButton.getScene().getWindow();
        stage.close();
    }

}