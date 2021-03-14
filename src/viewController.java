import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.net.URL;
import java.sql.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class viewController implements Initializable {

    @FXML
    private Button saveNewUserButton;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private TableView<User> tableView;
    @FXML
    private TableColumn<User, Integer> userId;
    @FXML
    private TableColumn<User, String> firstName;
    @FXML
    private TableColumn<User, String> lastName;
    @FXML
    private TableColumn<User, String> password;
    @FXML
    private TableColumn<User, Date> createdTimeStamp;
    @FXML
    private TableColumn<User, Date> modifiedTimeStamp;

    private ArrayList<User> users = new ArrayList<>();
    private ObservableList<User> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        userId.setCellValueFactory(new PropertyValueFactory<User, Integer>(null));
        lastName.setCellValueFactory(new PropertyValueFactory<User, String>(""));
        firstName.setCellValueFactory(new PropertyValueFactory<User, String>(""));
        password.setCellValueFactory(new PropertyValueFactory<User, String>(""));
        createdTimeStamp.setCellValueFactory(new PropertyValueFactory<User, Date>(""));
        modifiedTimeStamp.setCellValueFactory(new PropertyValueFactory<User, Date>(""));

        tableView.getItems().setAll(parseUserList());
    }

    private ObservableList<User> parseUserList() {
        // parse and construct User datamodel list by looping your ResultSet rs
        // and return the list
        users = UserDAO.getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            User user = new User(users.get(i).getUserId(), users.get(i).getLastName(), users.get(i).getFirstName(),
                    users.get(i).getPassword(), users.get(i).getCreatedTimeStamp(),
                    users.get(i).getModifiedTimeStamp());

            data.add(user);
        }
        return data;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("Saving User");

        // Get all the inputs
        String firstName = String.valueOf(firstNameTextField.getText().trim());
        String lastName = String.valueOf(lastNameTextField.getText().trim());
        String password = String.valueOf(passwordField.getText().trim());

        // Console info
        System.out.println(firstName + lastName + password + " date: ");

        // Saving User
        if (UserDAO.userExists(firstName, lastName)) {
            // Save user
            User user = new User(null, firstName, lastName, password, null, null);
            int user_saved;
            try {
                user_saved = UserDAO.saveUser(user);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                user_saved = 0;
            }
            if (user_saved > 0) {
                firstNameTextField.clear();
                passwordField.clear();
                lastNameTextField.clear();
                Alert alert = new Alert(AlertType.INFORMATION, "Save successful");
                alert.show();
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR, "User already exists!");
            alert.show();
        }
    }

}
