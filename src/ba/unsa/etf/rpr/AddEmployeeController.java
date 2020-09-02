package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class AddEmployeeController {
    public Button addButton;
    public Button cancelButton;
    public TextField usernameField;
    public TextField passwordField;
    private VideoLibraryDAO dao = null;
    private ObservableList<Employee> employees = null;
    private boolean allFieldsCorrect = false;
    private Employee employee;
    public AddEmployeeController(Employee employee) {
        dao = VideoLibraryDAO.getInstance();
        employees = FXCollections.observableArrayList(dao.getEmployees());
        this.employee = employee;
    }
    private boolean checkUsername(String username) {
        for (Employee e: employees) {
            if(e.getUsername().equals(username)) return true;
        }
        return false;
    }
    @FXML
    public void initialize() {
        usernameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.length() < 3 || checkUsername(newValue)) {
                    usernameField.getStyleClass().removeAll("fieldCorrect");
                    usernameField.getStyleClass().add("fieldIncorrect");
                    allFieldsCorrect = false;
                }
                else {
                    usernameField.getStyleClass().removeAll("fieldIncorrect");
                    usernameField.getStyleClass().add("fieldCorrect");
                    allFieldsCorrect = true;
                }
            }
        });
    }
    public void addAction(ActionEvent actionEvent) throws IOException {
        if(!passwordField.getText().isEmpty() && !usernameField.getText().isEmpty() && allFieldsCorrect) {
            Employee e = new Employee();
            e.setUsername(usernameField.getText());
            e.setPassword(passwordField.getText());
            dao.addEmployee(e);
            cancelAction(actionEvent);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setHeaderText(null);
            alert.setContentText("Unesite ispravne podatke!");
            alert.showAndWait();
        }
    }

    public void cancelAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }
    public void generatePasswordAction(ActionEvent actionEvent) {
        passwordField.setText(AddHotelGuestController.generatePassword());
    }
}
