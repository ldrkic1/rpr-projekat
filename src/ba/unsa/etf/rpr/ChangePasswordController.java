package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class ChangePasswordController {

    public PasswordField currentPasswordField;
    public PasswordField newPasswordField;
    public PasswordField confirmNewPasswordField;
    public Button cancelButton;
    public Button saveButton;
    private Employee employee = null;
    private VideoLibraryDAO dao = null;
    private boolean allFieldsCorrect = false;
    public ChangePasswordController(Employee employee) {
        dao = VideoLibraryDAO.getInstance();
        this.employee = employee;
    }
    @FXML
    public void initialize() {
        currentPasswordField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.equals(employee.getPassword())) {
                    currentPasswordField.getStyleClass().removeAll("fieldIncorrect");
                    currentPasswordField.getStyleClass().add("fieldCorrect");
                    allFieldsCorrect = true;
                }
                else {
                    currentPasswordField.getStyleClass().removeAll("fieldCorrect");
                    currentPasswordField.getStyleClass().add("fieldIncorrect");
                    allFieldsCorrect = false;
                }
            }
        });
        newPasswordField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.length() >= 5) {
                    newPasswordField.getStyleClass().removeAll("fieldIncorrect");
                    newPasswordField.getStyleClass().add("fieldCorrect");
                    allFieldsCorrect = true;
                }
                else {
                    newPasswordField.getStyleClass().removeAll("fieldCorrect");
                    newPasswordField.getStyleClass().add("fieldIncorrect");
                    allFieldsCorrect = false;
                }
            }
        });
        confirmNewPasswordField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.length() >= 5 && newValue.equals(newPasswordField.textProperty().get())) {
                    confirmNewPasswordField.getStyleClass().removeAll("fieldIncorrect");
                    confirmNewPasswordField.getStyleClass().add("fieldCorrect");
                    allFieldsCorrect = true;
                }
                else {
                    confirmNewPasswordField.getStyleClass().removeAll("fieldCorrect");
                    confirmNewPasswordField.getStyleClass().add("fieldIncorrect");
                    allFieldsCorrect = false;
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
            employee.setPassword(newPasswordField.getText());
            dao.updateEmployee(employee);
            cancelAction(actionEvent);
        }
    }
}
