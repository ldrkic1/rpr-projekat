package ba.unsa.etf.rpr;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
    private ArrayList<User> users = new ArrayList<>();
    private BackgroundImage backgroundImage = BackgroundImage.YELLOW;
    private String background = "-fx-background-image: url(\"../images/";
    public LoginController() throws SQLException {
        employees = dao.getEmployees();
        users = dao.getUsers();
    }
    @FXML
    public void initialize() {

        new Thread(() -> {
            try {
                while (true) {
                    if(backgroundImage.equals(BackgroundImage.YELLOW)) {
                        Platform.runLater(() -> {
                            grid.getStyleClass().removeAll("backgroundImage1");
                            grid.getStyleClass().add("backgroundImage2");
                            backgroundImage = BackgroundImage.PINK;
                        });
                    }
                    else if(backgroundImage.equals(BackgroundImage.PINK)) {
                        Platform.runLater(() -> {
                            grid.getStyleClass().removeAll("backgroundImage2");
                            grid.getStyleClass().add("backgroundImage3");
                            backgroundImage = BackgroundImage.BLUE;
                        });
                    }
                    else if(backgroundImage.equals(BackgroundImage.BLUE)) {
                        Platform.runLater(() -> {
                            grid.getStyleClass().removeAll("backgroundImage3");
                            grid.getStyleClass().add("backgroundImage4");
                            backgroundImage = BackgroundImage.ORANGE;
                        });
                    }
                    else {
                        Platform.runLater(() -> {
                            grid.getStyleClass().removeAll("backgroundImage4");
                            grid.getStyleClass().add("backgroundImage1");
                            backgroundImage = BackgroundImage.YELLOW;
                        });
                    }
                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
    public void loginAction(ActionEvent actionEvent) throws IOException {
        if(!usernameField.textProperty().get().equals("") && !passwordField.textProperty().get().equals("")) {
            Employee employee = dao.getEmployee(usernameField.textProperty().get());
            if( employee != null && employee.getPassword().equals(passwordField.getText())) {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/homeEmployee.fxml"));
                HomeEmployeeController ctrl = new HomeEmployeeController(employee);
                loader.setController(ctrl);
                Parent root = loader.load();
                stage.setTitle("Početna");
                stage.setScene(new Scene(root, 1200, 700));
                Stage stage1 = (Stage) usernameField.getScene().getWindow();
                stage1.close();
                stage.show();
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent windowEvent) {
                        Platform.exit();
                        System.exit(0);
                    }
                });
            }
            else {
                User user = dao.getUser(usernameField.textProperty().get());
                if(user!= null && user.getPassword().equals(passwordField.getText()) && user.getFirstName().equals("") && user.getLastName().equals("") && user.getRoomNumber() == Integer.parseInt(usernameField.getText())) {
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/register.fxml"));
                    RegisterController ctrl = new RegisterController(user, users);
                    loader.setController(ctrl);
                    Parent root = loader.load();
                    stage.setTitle("Register");
                    stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                    Stage stage1 = (Stage) usernameField.getScene().getWindow();
                    stage1.close();
                    stage.show();
                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent windowEvent) {
                            Platform.exit();
                            System.exit(0);
                        }
                    });
                }
                else if(user != null && user.getPassword().equals(passwordField.getText()) && !user.getFirstName().equals("") && !user.getLastName().equals("")) {
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
                    HomeController ctrl = new HomeController(user);
                    loader.setController(ctrl);
                    Parent root = loader.load();
                    stage.setTitle("Videoteka");
                    //stage.setMaximized(true);
                    //stage.setResizable(false);
                    stage.setScene(new Scene(root, 1200, 700));
                    Stage stage1 = (Stage) usernameField.getScene().getWindow();
                    stage1.close();
                    stage.show();
                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent windowEvent) {
                            Platform.exit();
                            System.exit(0);
                        }
                    });
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Neispravni pristupni podaci");
                    alert.setHeaderText(null);
                    alert.setContentText("Unesite ispravno korisničko ime i lozinku!");
                    alert.showAndWait();
                }
            }
        }
    }

}
