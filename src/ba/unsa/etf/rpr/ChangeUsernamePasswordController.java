package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;

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
    private boolean isUser = false;
    private User user;
    private ArrayList<Employee> employees = null;
    public ChangeUsernamePasswordController(Employee employee) {
        dao = VideoLibraryDAO.getInstance();
        this.employee = employee;
        employees = dao.getEmployees();
    }
    public ChangeUsernamePasswordController(User user) {
        dao = VideoLibraryDAO.getInstance();
        this.user = user;
        isUser = true;
        changeUsername = false;
    }
    public ChangeUsernamePasswordController(Employee employee, boolean changeUsername, TableView<Employee> employeesTableView, ObservableList<Employee> list) {
        dao = VideoLibraryDAO.getInstance();
        this.employee = employee;
        this.changeUsername = changeUsername;
        tableView = employeesTableView;
        this.list = list;
        employees = dao.getEmployees();
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
            usernameFIeld.setText(employee.getUsername());
        }
        else {
            usernameFIeld.setVisible(false);
            usernameLabel.setVisible(false);
        }
        currentPasswordField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(isUser) {
                    if (newValue.equals(user.getPassword())) {
                        currentPasswordField.getStyleClass().removeAll("fieldIncorrect");
                        currentPasswordField.getStyleClass().add("fieldCorrect");
                        allFieldsCorrect = true;
                    } else {
                        currentPasswordField.getStyleClass().removeAll("fieldCorrect");
                        currentPasswordField.getStyleClass().add("fieldIncorrect");
                        allFieldsCorrect = false;
                    }
                }
                else {
                    if (newValue.equals(employee.getPassword())) {
                        currentPasswordField.getStyleClass().removeAll("fieldIncorrect");
                        currentPasswordField.getStyleClass().add("fieldCorrect");
                        allFieldsCorrect = true;
                    } else {
                        currentPasswordField.getStyleClass().removeAll("fieldCorrect");
                        currentPasswordField.getStyleClass().add("fieldIncorrect");
                        allFieldsCorrect = false;
                    }
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
                for (Employee e: employees) {
                    if(e.getUsername().equals(newValue) && !employee.getUsername().equals(newValue)) {
                        usernameFIeld.getStyleClass().removeAll("fieldCorrect");
                        usernameFIeld.getStyleClass().add("fieldIncorrect");
                        allFieldsCorrect = false;
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
            if(changeUsername) {
                employee.setUsername(usernameFIeld.getText());
                dao.updateEmployee(employee);
                list.setAll(dao.getEmployees());
                tableView.setItems(list);
            }
            else {
                if(isUser) {
                    user.setPassword(newPasswordField.getText());
                    dao.updateUser(user);
                }
                else {
                    employee.setPassword(newPasswordField.getText());
                    dao.updateEmployee(employee);
                }
            }
            cancelAction(actionEvent);
        }
    }
}
