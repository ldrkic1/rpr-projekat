package ba.unsa.etf.rpr;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.File;

@ExtendWith(ApplicationExtension.class)

class AddGenreControllerTest {
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
    public void addMovieGenreOption(FxRobot robot) {
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
        robot.clickOn("#addGenreButton");
        robot.clickOn("#saveGenreButton");
        ListView listView = robot.lookup("#genresListView").queryAs(ListView.class);
        assertTrue(listView.getItems().size() == 1);
        Stage stage = (Stage) listView.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
    @Test
    public void addMovieGenreOption2(FxRobot robot) {
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
        robot.clickOn("#addGenreButton");
        robot.clickOn("#genreRadio");
        robot.clickOn("#titleField");
        robot.write("Historijski");
        robot.clickOn("#saveGenreButton");
        ListView listView = robot.lookup("#genresListView").queryAs(ListView.class);
        assertTrue(listView.getItems().size() == 1);
        Stage stage = (Stage) listView.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
    @Test
    public void addMovieGenreOption3(FxRobot robot) {
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
        robot.clickOn("#addGenreButton");
        robot.clickOn("#genreRadio");
        robot.clickOn("#saveGenreButton");
        Button okButton = robot.lookup("OK").queryAs(Button.class);
        assertNotNull(okButton);
        robot.clickOn("OK");
        robot.clickOn("#genreRadio");
        robot.clickOn("#saveGenreButton");
        ListView listView = robot.lookup("#genresListView").queryAs(ListView.class);
        assertTrue(listView.getItems().size() == 1);
        robot.clickOn("#addGenreButton");
        robot.clickOn("#saveGenreButton");
        assertTrue(listView.getItems().size() == 2);
        Stage stage = (Stage) listView.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
}