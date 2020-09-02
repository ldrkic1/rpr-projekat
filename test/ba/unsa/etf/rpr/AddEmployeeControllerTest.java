package ba.unsa.etf.rpr;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.File;

@ExtendWith(ApplicationExtension.class)

class AddEmployeeControllerTest {
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
    public void addEmployee(FxRobot robot) {
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
        assertTrue(tableView.getItems().size() == 3);
        robot.clickOn("#addEmployeeButton");
        Button button = robot.lookup("#addButton").queryAs(Button.class);
        assertTrue(button.isVisible());
        TextField usernameField = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write("user");
        assertTrue(usernameField.getStyleClass().contains("fieldIncorrect"));
        robot.clickOn("#generatePasswordButton");
        TextField password = robot.lookup("#passwordField").queryAs(TextField.class);
        assertFalse(password.getText().isEmpty());
        robot.clickOn("#addButton");
        Button okButton = robot.lookup("OK").queryAs(Button.class);
        assertNotNull(okButton);
        robot.clickOn("OK");
        robot.clickOn("#usernameField");
        KeyCode ctrl = KeyCode.CONTROL;
        if(System.getProperty("os.name").equals("Mac OS X")) {
            ctrl = KeyCode.COMMAND;
        }
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("ed");
        assertTrue(usernameField.getStyleClass().contains("fieldIncorrect"));
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("user14");
        assertTrue(usernameField.getStyleClass().contains("fieldCorrect"));
        robot.clickOn("#addButton");
        tableView = robot.lookup("#employeesTableView").queryAs(TableView.class);
        assertTrue(tableView.getItems().size() == 4);
        Stage stage = (Stage) tableView.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }

}