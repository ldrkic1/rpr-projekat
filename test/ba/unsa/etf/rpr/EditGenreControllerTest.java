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
import java.time.LocalDate;

@ExtendWith(ApplicationExtension.class)

class EditGenreControllerTest {
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
    public void editGenre(FxRobot robot) {
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
        tableView.getSelectionModel().selectFirst();
        robot.clickOn("#editGenreButton");
        TextField textField = robot.lookup("#titleGenreField").queryAs(TextField.class);
        assertTrue(textField.isVisible());
        assertTrue(textField.getText().equals("Drama"));
        robot.clickOn("#titleGenreField");
        KeyCode ctrl = KeyCode.CONTROL;
        if(System.getProperty("os.name").equals("Mac OS X")) {
            ctrl = KeyCode.COMMAND;
        }
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("Dramica");
        robot.clickOn("#saveGenreButton");
        tableView.getSelectionModel().selectFirst();
        Genre genre = (Genre)tableView.getSelectionModel().getSelectedItem();
        assertEquals("Dramica", genre.getName());
        Stage stage = (Stage) tabPane.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
    @Test
    public void editGenreAlert(FxRobot robot) {
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
        tableView.getSelectionModel().selectFirst();
        robot.clickOn("#editGenreButton");
        TextField textField = robot.lookup("#titleGenreField").queryAs(TextField.class);
        assertTrue(textField.isVisible());
        assertTrue(textField.getText().equals("Drama"));
        robot.clickOn("#titleGenreField");
        KeyCode ctrl = KeyCode.CONTROL;
        if(System.getProperty("os.name").equals("Mac OS X")) {
            ctrl = KeyCode.COMMAND;
        }
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("Dr");
        robot.clickOn("#saveGenreButton");
        Button okButton = robot.lookup("OK").queryAs(Button.class);
        assertNotNull(okButton);
        robot.clickOn("OK");
        robot.clickOn("#cancelButton");
        tableView.getSelectionModel().selectFirst();
        Genre genre = (Genre)tableView.getSelectionModel().getSelectedItem();
        assertEquals("Drama", genre.getName());
        Stage stage = (Stage) tabPane.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
}