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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class RegisterController {
    public Button registerButton;
    public TextField firstNameField;
    public TextField lastNameField;
    public TextField usernameField;
    public PasswordField passwordField;
    public PasswordField repeatPasswordField;
    private VideoLibraryDAO dao = null;
    private User user;
    private boolean allFieldsCorrect = false;
    private  ArrayList<User> users = null;
    public RegisterController(User user, ArrayList<User> users) {
        this.user = user;
        dao = VideoLibraryDAO.getInstance();
        this.users = users;
    }
    public  boolean isTakenUsername(String username) {
        for(User u: users) {
            if(u.getUsername().equals(username)) return true;
        }
        return false;
    }
    @FXML
    public void initialize() {
        firstNameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.length() < 2) {
                    allFieldsCorrect = false;
                    firstNameField.getStyleClass().add("fieldIncorrect");
                }
                else {
                    allFieldsCorrect = true;
                    firstNameField.getStyleClass().removeAll("fieldIncorrect");
                }
            }
        });
        lastNameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.length() < 2) {
                    allFieldsCorrect = false;
                    lastNameField.getStyleClass().add("fieldIncorrect");
                }
                else {
                    allFieldsCorrect = true;
                    lastNameField.getStyleClass().removeAll("fieldIncorrect");
                }
            }
        });
        usernameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.length() < 2 || isTakenUsername(newValue)) {
                    allFieldsCorrect = false;
                    usernameField.getStyleClass().add("fieldIncorrect");
                }
                else {
                    allFieldsCorrect = true;
                    usernameField.getStyleClass().removeAll("fieldIncorrect");
                }
            }
        });
        passwordField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.length() < 5 ) {
                    allFieldsCorrect = false;
                    passwordField.getStyleClass().add("fieldIncorrect");
                }
                else {
                    allFieldsCorrect = true;
                    passwordField.getStyleClass().removeAll("fieldIncorrect");
                }
            }
        });
        repeatPasswordField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.length() < 5 || !newValue.equals(passwordField.getText())) {
                    allFieldsCorrect = false;
                    repeatPasswordField.getStyleClass().add("fieldIncorrect");
                }
                else {
                    allFieldsCorrect = true;
                    repeatPasswordField.getStyleClass().removeAll("fieldIncorrect");
                }
            }
        });
    }
    public void  registerAction(ActionEvent actionEvent) throws IOException {
        if(allFieldsCorrect) {
            user.setFirstName(firstNameField.getText());
            user.setLastName(lastNameField.getText());
            user.setUsername(usernameField.getText());
            user.setPassword(passwordField.getText());
            dao.updateUser(user);
            Stage stage = (Stage) registerButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
            HomeController ctrl = new HomeController(user);
            loader.setController(ctrl);
            Parent root = loader.load();
            stage.setTitle("Videoteka");
            stage.setScene(new Scene(root, 1200,700));
            stage.show();
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
