package ba.unsa.etf.rpr;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.File;
import java.time.LocalDate;

@ExtendWith(ApplicationExtension.class)

class EditUserDataControllerTest {
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
    public void changeUserData(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write("lamka");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("lamka123");
        robot.clickOn("#loginButton");
        BorderPane borderPane = robot.lookup("#homeBorderPane").queryAs(BorderPane.class);
        robot.clickOn("#accountOptions");
        robot.clickOn("#userData");
        TextField userField = robot.lookup("#usernameField").queryAs(TextField.class);
        assertNotNull(userField);
        robot.clickOn("#usernameField");
        KeyCode ctrl = KeyCode.CONTROL;
        if(System.getProperty("os.name").equals("Mac OS X")) {
            ctrl = KeyCode.COMMAND;
        }
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("merlin");
        robot.clickOn("#saveButton");
        Button okButton = robot.lookup("OK").queryAs(Button.class);
        assertNotNull(okButton);
        robot.clickOn("OK");
        robot.clickOn("#usernameField");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("lamkica");
        TextField nameField = robot.lookup("#nameField").queryAs(TextField.class);
        robot.clickOn("#nameField");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("L");
        assertTrue(nameField.getStyleClass().contains("fieldIncorrect"));
        TextField surnameFld = robot.lookup("#surnameField").queryAs(TextField.class);
        robot.clickOn("#surnameField");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("D");
        assertTrue(surnameFld.getStyleClass().contains("fieldIncorrect"));
        robot.clickOn("#nameField");
        robot.write("amija");
        assertFalse(nameField.getStyleClass().contains("fieldIncorrect"));
        robot.clickOn("#surnameField");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("Drkić");
        assertFalse(surnameFld.getStyleClass().contains("fieldIncorrect"));
        robot.clickOn("#saveButton");
        Stage stage = (Stage) borderPane.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
    @Test
    public void cancelChanges(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write("lamka");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("lamka123");
        robot.clickOn("#loginButton");
        BorderPane borderPane = robot.lookup("#homeBorderPane").queryAs(BorderPane.class);
        robot.clickOn("#accountOptions");
        robot.clickOn("#userData");
        TextField userField = robot.lookup("#usernameField").queryAs(TextField.class);
        assertNotNull(userField);
        robot.clickOn("#usernameField");
        KeyCode ctrl = KeyCode.CONTROL;
        if(System.getProperty("os.name").equals("Mac OS X")) {
            ctrl = KeyCode.COMMAND;
        }
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("merlin");
        robot.clickOn("#saveButton");
        Button okButton = robot.lookup("OK").queryAs(Button.class);
        assertNotNull(okButton);
        robot.clickOn("OK");
        robot.clickOn("#usernameField");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("lamkica");
        robot.clickOn("#cancelButton");
        Stage stage = (Stage) borderPane.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
}