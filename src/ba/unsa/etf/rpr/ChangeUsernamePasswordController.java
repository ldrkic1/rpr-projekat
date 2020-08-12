package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ChangeUsernamePasswordController {

    public PasswordField currentPasswordField;
    public PasswordField newPasswordField;
    public PasswordField confirmNewPasswordField;
    public Button cancelButton;
    public Button saveButton;
    public TextField usernameFIeld;
    public Label passwordLabel, usernameLabel, newPasswordLabel, confirmPasswordLabel;
    private Employee employee = null;
    private VideoLibraryDAO dao = null;
    private boolean allFieldsCorrect = false;
    private boolean changeUsername = false;
    private TableView<Employee> tableView = null;
    private ObservableList<Employee> list = null;
    public ChangeUsernamePasswordController(Employee employee) {
        dao = VideoLibraryDAO.getInstance();
        this.employee = employee;
    }
    public ChangeUsernamePasswordController(Employee employee, boolean changeUsername, TableView<Employee> employeesTableView, ObservableList<Employee> list) {
        dao = VideoLibraryDAO.getInstance();
        this.employee = employee;
        this.changeUsername = changeUsername;
        tableView = employeesTableView;
        this.list = list;
    }
    @FXML
    public void initialize() {
        if(changeUsername) {
            passwordLabel.setVisible(false);
            currentPasswordField.setVisible(false);
            newPasswordLabel.setVisible(false);
            newPasswordField.setVisible(false);
            confirmNewPasswordField.setVisible(false);
            confirmPasswordLabel.setVisible(false);
        }
        else {
            usernameFIeld.setVisible(false);
            usernameLabel.setVisible(false);
        }
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
        usernameFIeld.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.length() < 2) {
                    usernameFIeld.getStyleClass().removeAll("fieldCorrect");
                    usernameFIeld.getStyleClass().add("fieldIncorrect");
                    allFieldsCorrect = false;
                }
                else {
                    usernameFIeld.getStyleClass().removeAll("fieldIncorrect");
                    usernameFIeld.getStyleClass().add("fieldCorrect");
                    allFieldsCorrect = true;
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
            if(changeUsername) {
                employee.setUsername(usernameFIeld.getText());
                dao.updateEmployee(employee);
                list.setAll(dao.getEmployees());
                tableView.setItems(list);
            }
            else {
                employee.setPassword(newPasswordField.getText());
                dao.updateEmployee(employee);
            }
            cancelAction(actionEvent);
        }
    }
}
