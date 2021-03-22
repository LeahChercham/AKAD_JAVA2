import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.beans.EventHandler;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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
    private Button displayButton;

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

                                try {
                                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modifyPopUp.fxml"));
                                    Parent root = (Parent) fxmlLoader.load();
                                    modifyPopUpController modifyPopUpController = fxmlLoader.getController();
                                    modifyPopUpController.transferMessage(user);
                                    Stage stage = new Stage();
                                    stage.setTitle("Modify");
                                    stage.setScene(new Scene(root, 500, 250));
                                    stage.show();

                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }

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
                                int user_deleted;

                                try {
                                    user_deleted = UserDAO.deleteUser(user);
                                } catch (SQLException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                    user_deleted = 0;
                                }

                                if (user_deleted > 0) {
                                    Alert alert = new Alert(AlertType.INFORMATION, "Deletion successful");
                                    alert.show();

                                    tableView.getItems().setAll(parseUserList());

                                } else {
                                    Alert alert = new Alert(AlertType.ERROR, "Could not delete user");
                                    alert.show();
                                }
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
        data.clear();

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

        String firstName = String.valueOf(firstNameTextField.getText().trim());
        String lastName = String.valueOf(lastNameTextField.getText().trim());
        String password = String.valueOf(passwordField.getText().trim());

        if (UserDAO.userExists(firstName, lastName)) {
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

                tableView.getItems().setAll(parseUserList());
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR, "User already exists!");
            alert.show();
        }
    }

    public void refreshData(){
        tableView.getItems().setAll(parseUserList());
    }
}
