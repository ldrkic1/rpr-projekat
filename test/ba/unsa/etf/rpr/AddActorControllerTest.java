package ba.unsa.etf.rpr;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.File;
import java.time.LocalDate;

@ExtendWith(ApplicationExtension.class)

class AddActorControllerTest {
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
    public void addMovieActorOption(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write("user");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("password");
        robot.clickOn("#loginButton");
        TabPane tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        assertNotNull(tabPane);
        Button detailsBtn = robot.lookup("#detailsButton").queryAs(Button.class);
        robot.clickOn("#addNewMovieButton");
        robot.clickOn("#addActorButton");
        robot.clickOn("#addButton");
        ListView listView = robot.lookup("#actorsListView").queryAs(ListView.class);
        assertTrue(listView.getItems().size() == 1);
        Stage stage = (Stage) listView.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
    @Test
    public void addMovieNewActor1(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write("user");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("password");
        robot.clickOn("#loginButton");
        TabPane tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        assertNotNull(tabPane);
        Button detailsBtn = robot.lookup("#detailsButton").queryAs(Button.class);
        robot.clickOn("#addNewMovieButton");
        robot.clickOn("#addActorButton");
        robot.clickOn("#addActorRadio");
        robot.clickOn("#addButton");
        Button okButton = robot.lookup("OK").queryAs(Button.class);
        assertNotNull(okButton);
        robot.clickOn("OK");
        ListView listView = robot.lookup("#actorsListView").queryAs(ListView.class);
        assertFalse(listView.getItems().size() == 1);
        Stage stage = (Stage) listView.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
    @Test
    public void addMovieNewActor2(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write("user");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("password");
        robot.clickOn("#loginButton");
        TabPane tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        assertNotNull(tabPane);
        Button detailsBtn = robot.lookup("#detailsButton").queryAs(Button.class);
        robot.clickOn("#addNewMovieButton");
        robot.clickOn("#addActorButton");
        robot.clickOn("#addActorRadio");
        robot.clickOn("#nameField");
        robot.write("Test");
        robot.clickOn("#surnameField");
        robot.write("Test");
        robot.clickOn("#biographyArea");
        robot.write("Test");
        robot.clickOn("#urlArea");
        //TextArea urlArea = robot.lookup("#urlArea").queryAs(TextArea.class);
        robot.write("https://i.pinimg.com/564x/bf/ac/2e/bfac2efd7126ff0cc999194ed5830590.jpg");
        robot.clickOn("#addButton");
        ListView listView = robot.lookup("#actorsListView").queryAs(ListView.class);
        assertTrue(listView.getItems().size() == 1);
        Stage stage = (Stage) listView.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
    @Test
    public void addMovieActor3(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write("user");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("password");
        robot.clickOn("#loginButton");
        TabPane tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        assertNotNull(tabPane);
        Button detailsBtn = robot.lookup("#detailsButton").queryAs(Button.class);
        robot.clickOn("#addNewMovieButton");
        robot.clickOn("#addActorButton");
        robot.clickOn("#addActorRadio");
        robot.clickOn("#nameField");
        robot.write("Test");
        robot.clickOn("#surnameField");
        robot.write("Test");
        robot.clickOn("#biographyArea");
        robot.write("Test");
        DatePicker datePicker = robot.lookup("#birthDatePicker").queryAs(DatePicker.class);
        robot.clickOn("#birthDatePicker");
        KeyCode ctrl = KeyCode.CONTROL;
        if(System.getProperty("os.name").equals("Mac OS X")) {
            ctrl = KeyCode.COMMAND;
        }
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("06/10/1997");
        robot.clickOn("#addButton");
        Button okButton = robot.lookup("OK").queryAs(Button.class);
        assertNotNull(okButton);
        robot.clickOn("OK");
        robot.clickOn("#addActorRadio");
        robot.clickOn("#addButton");
        ListView listView = robot.lookup("#actorsListView").queryAs(ListView.class);
        assertTrue(listView.getItems().size() == 1);
        Stage stage = (Stage) listView.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
    @Test
    public void addMovieActor4(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write("user");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("password");
        robot.clickOn("#loginButton");
        TabPane tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        assertNotNull(tabPane);
        Button detailsBtn = robot.lookup("#detailsButton").queryAs(Button.class);
        robot.clickOn("#addNewMovieButton");
        robot.clickOn("#addActorButton");
        robot.clickOn("#addActorRadio");
        robot.clickOn("#nameField");
        robot.write("Test");
        robot.clickOn("#surnameField");
        robot.write("Test");
        robot.clickOn("#biographyArea");
        robot.write("Test");
        DatePicker datePicker = robot.lookup("#birthDatePicker").queryAs(DatePicker.class);
        datePicker.setValue(LocalDate.of(1997,10,6));
        robot.clickOn("#addButton");
        Button okButton = robot.lookup("OK").queryAs(Button.class);
        assertNotNull(okButton);
        robot.clickOn("OK");
        robot.clickOn("#cancelButton");
        ListView listView = robot.lookup("#actorsListView").queryAs(ListView.class);
        assertTrue(listView.getItems().isEmpty());
        Stage stage = (Stage) listView.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
}