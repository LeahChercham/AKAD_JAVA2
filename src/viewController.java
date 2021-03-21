import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.beans.EventHandler;
import java.net.URL;
import java.sql.Date;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

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
        userId.setCellValueFactory(new PropertyValueFactory<User, Integer>("userId"));
        lastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        firstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        password.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        createdTimeStamp.setCellValueFactory(new PropertyValueFactory<User, Date>("createdTimeStamp"));
        modifiedTimeStamp.setCellValueFactory(new PropertyValueFactory<User, Date>("modifiedTimeStamp"));


    // ====================================
    // ====================================
    // ====================================

    // HERE IT STARTS
         TableColumn<User, User> modifyEntryColumn = new TableColumn<>("Modify");

        // Button modifyEntryButton = new Button("Text");
        // modifyEntryColumn.setMinWidth(40);

        // modifyEntryColumn.setCellValueFactory(param -> new
        // ReadOnlyObjectWrapper<>(param.getValue()));
        // modifyEntryColumn.setCellFactory(param -> new TableCell<User, User>() {
        // private final Button modifyEntryButton;
        // });

        TableColumn<User, User> btnCol = new TableColumn<>("here should be the button");
        btnCol.setCellFactory(new Callback<TableColumn<User, User>, TableCell<User, User>>() {
            @Override
            public TableCell<User, User> call(TableColumn<User, User> btnCol) {
                final Button button = new Button("button text");
                button.setMinWidth(130); 
                TableCell<User, User> cell = new TableCell<User, User>() {              
                    public void alert() {
                        Alert alert = new Alert(AlertType.INFORMATION, "Button Clicked!");
                        alert.show();
                    }
                  };
                return cell;

                // button.setOnAction();
        }});

        tableView.getItems().setAll(parseUserList());
        tableView.getColumns().add(btnCol);
    }

    // HERE IT ENDS
    // ====================================
    // ====================================
    // ====================================


    private ObservableList<User> parseUserList() {
        // parse and construct User datamodel list by looping
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
    private void handleSaveButtonAction(ActionEvent event) {
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

    @FXML
    private void handleModifyButtonAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION, "Button Clicked!");
        alert.show();
    }
}
