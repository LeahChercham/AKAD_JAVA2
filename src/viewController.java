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

        TableColumn modifyEntryColumn = new TableColumn<>("Modify");
        modifyEntryColumn.setCellValueFactory(new PropertyValueFactory<>(null));

        TableColumn deleteEntryColumn = new TableColumn<>("Delete");
        deleteEntryColumn.setCellValueFactory(new PropertyValueFactory<>(null));

        Callback<TableColumn<User, String>, TableCell<User, String>> cellFactoryModify = new Callback<TableColumn<User, String>, TableCell<User, String>>() {

            @Override
            public TableCell<User, String> call(final TableColumn<User, String> param) {
                final TableCell<User, String> cell = new TableCell<User, String>() {
                    final Button modifyButton = new Button("Modify");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            modifyButton.setOnAction(event -> {

                                // HERE FUNCTION MODIFY
                                User user = getTableView().getItems().get(getIndex());
                                System.out.println(user.getFirstName());
                            });
                            setGraphic(modifyButton);
                            setText(null);
                        }
                    }
                };
                return cell;
            }

        };


        Callback<TableColumn<User, String>, TableCell<User, String>> cellFactoryDelete = new Callback<TableColumn<User, String>, TableCell<User, String>>() {

            @Override
            public TableCell<User, String> call(final TableColumn<User, String> param) {
                final TableCell<User, String> cell = new TableCell<User, String>() {
                    final Button deleteButton = new Button("Delete");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            deleteButton.setOnAction(event -> {

                                // HERE FUNCTION DELETE
                                User user = getTableView().getItems().get(getIndex());
                                System.out.println("I will delete: " + user.getFirstName());
                            });
                            setGraphic(deleteButton);
                            setText(null);
                        }
                    }
                };
                return cell;
            }

        };

        modifyEntryColumn.setCellFactory(cellFactoryModify);
        deleteEntryColumn.setCellFactory(cellFactoryDelete);
        tableView.getItems().setAll(parseUserList());
        tableView.getColumns().add(modifyEntryColumn);
        tableView.getColumns().add(deleteEntryColumn);
    }

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
