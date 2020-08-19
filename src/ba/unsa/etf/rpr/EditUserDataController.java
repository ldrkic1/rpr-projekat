package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class EditUserDataController {
    public TextField usernameField;
    public TextField nameField;
    public TextField surnameField;
    public Button cancelButton;
    public Button saveButton;
    private User user;
    private VideoLibraryDAO dao = null;
    private boolean allFieldsCorrect = true;
    private ArrayList<User> users = null;
    public EditUserDataController(User user) {
        this.user = user;
        dao = VideoLibraryDAO.getInstance();
        users = dao.getUsers();
    }
    @FXML
    public void initialize() {
        nameField.setText(user.getFirstName());
        surnameField.setText(user.getLastName());
        usernameField.setText(user.getUsername());
        nameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.length() < 2) {
                    allFieldsCorrect = false;
                    nameField.getStyleClass().add("fieldIncorrect");
                }
                else {
                    allFieldsCorrect = true;
                    nameField.getStyleClass().removeAll("fieldIncorrect");
                }
            }
        });
        surnameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.length() < 2) {
                    allFieldsCorrect = false;
                    surnameField.getStyleClass().add("fieldIncorrect");
                }
                else {
                    allFieldsCorrect = true;
                    surnameField.getStyleClass().removeAll("fieldIncorrect");
                }
            }
        });
        usernameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {

                if(newValue.length() < 2) {
                    allFieldsCorrect = false;
                    usernameField.getStyleClass().add("fieldIncorrect");
                }
                else {
                    allFieldsCorrect = true;
                    usernameField.getStyleClass().removeAll("fieldIncorrect");
                }

                for(User u: users) {
                    if(u.getUsername().equals(newValue) && !user.getUsername().equals(newValue)) {
                        allFieldsCorrect = false;
                        usernameField.getStyleClass().add("fieldIncorrect");
                    }
                }
            }
        });
    }
    public void cancelAction(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void saveAction(ActionEvent actionEvent) {
        if(allFieldsCorrect) {
            user.setFirstName(nameField.getText());
            user.setLastName(surnameField.getText());
            user.setUsername(usernameField.getText());
            dao.updateUser(user);
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Neispravni podaci");
            alert.setHeaderText(null);
            alert.setContentText("Unesite ispravne podatke!");
            alert.showAndWait();
        }
    }
}
