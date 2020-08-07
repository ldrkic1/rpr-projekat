package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class LoginController {

    public GridPane grid;
    public TextField usernameField;
    public TextField passwordField;
    public VideoLibraryDAO dao = VideoLibraryDAO.getInstance();
    private ArrayList<Employee> employees = new ArrayList<>();
    public LoginController() throws SQLException {
        employees = dao.getEmployees();
    }
    public void loginAction(ActionEvent actionEvent) throws IOException {
        if(!usernameField.textProperty().get().equals("") && !passwordField.textProperty().get().equals("")) {
            if(dao.getEmployee(usernameField.textProperty().get())!=null) {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/homeEmployee.fxml"));
                HomeEmployeeController ctrl = new HomeEmployeeController();
                loader.setController(ctrl);
                Parent root = loader.load();
                stage.setTitle("Home");
                stage.setScene(new Scene(root, 1200, 700));
                Stage stage1 = (Stage) usernameField.getScene().getWindow();
                stage1.close();
                stage.show();
            }
            else {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/register.fxml"));
                RegisterController ctrl = new RegisterController();
                loader.setController(ctrl);
                Parent root = loader.load();
                stage.setTitle("Register");
                stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                Stage stage1 = (Stage) usernameField.getScene().getWindow();
                stage1.close();
                stage.show();
            }
        }
    }

   /* @FXML
    public void initialize() {
        grid.getStyleClass().add("body");
    }*/
}
