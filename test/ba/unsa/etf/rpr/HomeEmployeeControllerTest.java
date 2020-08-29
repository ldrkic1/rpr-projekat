package ba.unsa.etf.rpr;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.File;

@ExtendWith(ApplicationExtension.class)

class HomeEmployeeControllerTest {
    VideoLibraryDAO dao = null;
    @Start
    public void start (Stage stage) throws Exception {
        VideoLibraryDAO.removeInstance();
        File dbfile = new File("database.db");
        dbfile.delete();
        dao = VideoLibraryDAO.getInstance();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        LoginController ctrl = new LoginController();
        loader.setController(ctrl);
        Parent root = loader.load();
        root.setId("body");
        stage.setTitle("Prijava");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
        stage.toFront();
    }
    @Test
    public void employeeLogin(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write("user");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("password");
        robot.clickOn("#loginButton");
        TabPane tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        assertNotNull(tabPane);
        TableView tableView = robot.lookup("#tableViewMovies").queryAs(TableView.class);
        assertEquals(4, tableView.getItems().size());
        robot.clickOn("#genresTab");
        TableView tableView1 = robot.lookup("#genresTableView").queryAs(TableView.class);
        assertEquals(7, tableView1.getItems().size());
        robot.clickOn("#serialTab");
        TableView tableView2 = robot.lookup("#tableViewSeries").queryAs(TableView.class);
        assertEquals(5, tableView2.getItems().size());
        robot.clickOn("#requestsTab");
        TableView tableView3 = robot.lookup("#requestsTable").queryAs(TableView.class);
        assertEquals(2, tableView3.getItems().size());
        Stage stage = (Stage) tabPane.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }

    @Test
    public void employeeLogoutOption(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write("user");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("password");
        robot.clickOn("#loginButton");
        TabPane tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        assertNotNull(tabPane);
        robot.clickOn("#settingsMenu");
        robot.clickOn("#logoutMenuOption");
        TextField usernameField = robot.lookup("#usernameField").queryAs(TextField.class);
        assertNotNull(usernameField);
        Stage stage = (Stage) usernameField.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
    @Test
    public void employeeAddMovieOption(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write("user");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("password");
        robot.clickOn("#loginButton");
        TabPane tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        assertNotNull(tabPane);
        robot.clickOn("#addNewMovieButton");
        TextField titleField = robot.lookup("#titleField").queryAs(TextField.class);
        assertNotNull(titleField);
        robot.clickOn("#cancelButton");
        tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        Stage stage = (Stage) tabPane.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
    @Test
    public void employeeDeleteMovieOption(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write("user");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("password");
        robot.clickOn("#loginButton");
        TabPane tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        assertNotNull(tabPane);
        TableView tableView = robot.lookup("#tableViewMovies").queryAs(TableView.class);
        assertEquals(4, tableView.getItems().size());
        tableView.getSelectionModel().selectLast();
        robot.clickOn("#deleteMovieButton");
        assertEquals(3, tableView.getItems().size());
        Stage stage = (Stage) tabPane.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
    @Test
    public void employeeAddSerialOption(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write("user");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("password");
        robot.clickOn("#loginButton");
        TabPane tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        assertNotNull(tabPane);
        robot.clickOn("#serialTab");
        TableView tableView2 = robot.lookup("#tableViewSeries").queryAs(TableView.class);
        assertEquals(5, tableView2.getItems().size());
        robot.clickOn("#addSerialButton");
        TextField yearField = robot.lookup("#yearField").queryAs(TextField.class);
        assertNotNull(yearField);
        robot.clickOn("#cancelButton");
        tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        assertNotNull(tabPane);
        Stage stage = (Stage) tabPane.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
    @Test
    public void employeeDeleteSerialOption(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write("user");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("password");
        robot.clickOn("#loginButton");
        TabPane tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        assertNotNull(tabPane);
        robot.clickOn("#serialTab");
        TableView tableView2 = robot.lookup("#tableViewSeries").queryAs(TableView.class);
        assertEquals(5, tableView2.getItems().size());
        tableView2.getSelectionModel().selectLast();
        robot.clickOn("#deleteSerialButton");
        assertEquals(4, tableView2.getItems().size());
        Stage stage = (Stage) tabPane.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }

    @Test
    public void employeeDeleteRequestOption(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write("user");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("password");
        robot.clickOn("#loginButton");
        TabPane tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        assertNotNull(tabPane);
        robot.clickOn("#requestsTab");
        TableView tableView3 = robot.lookup("#requestsTable").queryAs(TableView.class);
        assertEquals(2, tableView3.getItems().size());
        tableView3.getSelectionModel().selectLast();
        robot.clickOn("#deleteRequestButton");
        assertEquals(1, tableView3.getItems().size());
        Stage stage = (Stage) tabPane.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
    @Test
    public void employeeDeleteGenreOption(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write("user");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("password");
        robot.clickOn("#loginButton");
        TabPane tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        assertNotNull(tabPane);
        robot.clickOn("#genresTab");
        TableView tableView1 = robot.lookup("#genresTableView").queryAs(TableView.class);
        assertEquals(7, tableView1.getItems().size());
        tableView1.getSelectionModel().selectLast();
        robot.clickOn("#deleteGenreButton");
        assertEquals(6, tableView1.getItems().size());
        Stage stage = (Stage) tabPane.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
    @Test
    public void employeeDeleteGuestOption(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write("user");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("password");
        robot.clickOn("#loginButton");
        TabPane tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        assertNotNull(tabPane);
        robot.clickOn("#guestsTab");
        TableView tableView1 = robot.lookup("#usersTableView").queryAs(TableView.class);
        assertEquals(3, tableView1.getItems().size());
        tableView1.getSelectionModel().selectFirst();
        robot.clickOn("#deleteGuestButton");
        assertEquals(2, dao.getUsers().size());
        Stage stage = (Stage) tabPane.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
    @Test
    public void employeeDeleteEmployeeOption(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write("admin");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("password");
        robot.clickOn("#loginButton");
        TabPane tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        assertNotNull(tabPane);
        robot.clickOn("#employeesTab");
        TableView tableView1 = robot.lookup("#employeesTableView").queryAs(TableView.class);
        assertEquals(2, tableView1.getItems().size());
        tableView1.getSelectionModel().selectFirst();
        robot.clickOn("#deleteEmployeeButton");
        assertEquals(1, tableView1.getItems().size());
        Stage stage = (Stage) tabPane.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
    @Test
    public void employeeChangePasswordOption(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write("user");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("password");
        robot.clickOn("#loginButton");
        TabPane tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        assertNotNull(tabPane);
        robot.clickOn("#settingsMenu");
        robot.clickOn("#changePasswordOption");
        TextField passField = robot.lookup("#newPasswordField").queryAs(TextField.class);
        assertNotNull(passField);
        TextField usernameField = robot.lookup("#usernameFIeld").queryAs(TextField.class);
        assertFalse(usernameField.isVisible());
        robot.clickOn("#cancelButton");
        Stage stage = (Stage) tabPane.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
    @Test
    public void employeeChangeUsernameOption(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write("user");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("password");
        robot.clickOn("#loginButton");
        TabPane tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        assertNotNull(tabPane);
        robot.clickOn("#settingsMenu");
        robot.clickOn("#changeUsernameOption");
        TextField passField = robot.lookup("#newPasswordField").queryAs(TextField.class);
        assertFalse(passField.isVisible());
        TextField usernameField = robot.lookup("#usernameFIeld").queryAs(TextField.class);
        assertTrue(usernameField.isVisible());
        robot.clickOn("#cancelButton");
        Stage stage = (Stage) tabPane.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
    @Test
    public void employeeAddEmployeeOption(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write("admin");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("password");
        robot.clickOn("#loginButton");
        TabPane tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        assertNotNull(tabPane);
        robot.clickOn("#employeesTab");
        TableView tableView = robot.lookup("#employeesTableView").queryAs(TableView.class);
        assertTrue(tableView.getItems().size() == 2);
        robot.clickOn("#addEmployeeButton");
        Button button = robot.lookup("#addButton").queryAs(Button.class);
        assertTrue(button.isVisible());
        robot.clickOn("#usernameField");
        robot.write("admin");
        robot.clickOn("#passwordField");
        robot.write("password");
        robot.clickOn("#addButton");
        assertTrue(tableView.getItems().size() == 3);
        Stage stage = (Stage) tabPane.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
    @Test
    public void employeeAddGenreOption(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write("user");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("password");
        robot.clickOn("#loginButton");
        TabPane tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        assertNotNull(tabPane);
        robot.clickOn("#genresTab");
        TableView tableView = robot.lookup("#genresTableView").queryAs(TableView.class);
        assertTrue(tableView.getItems().size() == 7);
        robot.clickOn("#addGenreButton");
        Button button = robot.lookup("#saveGenreButton").queryAs(Button.class);
        assertTrue(button.isVisible());
        robot.clickOn("#titleField");
        robot.write("Horor");
        robot.clickOn("#saveGenreButton");
        assertTrue(tableView.getItems().size() == 8);
        Stage stage = (Stage) tabPane.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
}