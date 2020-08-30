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

class AddSerialControllerTest {
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
    public void addNewSerial(FxRobot robot) {
        robot.clickOn("#usernameField");
        robot.write("user");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("password");
        robot.clickOn("#loginButton");
        TabPane tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        assertNotNull(tabPane);
        robot.clickOn("#serialTab");
        robot.clickOn("#addSerialButton");
        robot.clickOn("#titleField");
        robot.write("Rebelde");
        robot.clickOn("#yearField");
        robot.write("2007");
        robot.clickOn("#seasonsField");
        robot.write("3");
        robot.clickOn("#episodesField");
        robot.write("300");
        robot.clickOn("#addGenreButton");
        robot.clickOn("#saveGenreButton");
        robot.clickOn("#addGenreButton");
        robot.clickOn("#saveGenreButton");
        ListView listView = robot.lookup("#genresListView").queryAs(ListView.class);
        listView.getSelectionModel().selectLast();
        robot.clickOn("#deleteGenreButton");
        assertTrue(listView.getItems().size() == 1);
        robot.clickOn("#addActorButton");
        robot.clickOn("#addButton");
        ListView listView1 = robot.lookup("#actorsListView").queryAs(ListView.class);
        assertTrue(listView1.getItems().size() == 1);
        robot.clickOn("#addActorButton");
        robot.clickOn("#addButton");
        listView1.getSelectionModel().selectLast();
        robot.clickOn("#deleteActorButton");
        assertTrue(listView1.getItems().size() == 1);
        robot.clickOn("#directorField");
        robot.write("Reziser");
        robot.clickOn("#descriptionArea");
        robot.write("Serija za tinejdzere...");
        robot.clickOn("#ratingValueField");
        robot.write("9");
        robot.clickOn("#priceField");
        robot.write("100");
        TextArea area = robot.lookup("#imageUrlArea").queryAs(TextArea.class);
        area.setText("https://i.pinimg.com/564x/42/07/4a/42074ae20aef2e95c7c2a233c28a519d.jpg");
        robot.clickOn("#saveChangesButton");
        tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        robot.clickOn("#serialTab");
        TableView tableView = robot.lookup("#tableViewSeries").queryAs(TableView.class);
        assertEquals(6, tableView.getItems().size());
        Stage stage = (Stage) tabPane.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
    @Test
    public void addNewSerialError(FxRobot robot) {
        robot.clickOn("#usernameField");
        robot.write("user");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("password");
        robot.clickOn("#loginButton");
        TabPane tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        assertNotNull(tabPane);
        robot.clickOn("#serialTab");
        robot.clickOn("#addSerialButton");
        robot.clickOn("#titleField");
        robot.write("Rebelde");
        robot.clickOn("#yearField");
        robot.write("2007");
        robot.clickOn("#seasonsField");
        robot.write("3");
        robot.clickOn("#episodesField");
        robot.write("300");
        robot.clickOn("#addGenreButton");
        robot.clickOn("#saveGenreButton");
        ListView listView = robot.lookup("#genresListView").queryAs(ListView.class);
        assertTrue(listView.getItems().size() == 1);
        robot.clickOn("#addActorButton");
        robot.clickOn("#addButton");
        ListView listView1 = robot.lookup("#actorsListView").queryAs(ListView.class);
        assertTrue(listView1.getItems().size() == 1);
        robot.clickOn("#directorField");
        robot.write("Test Test");
        robot.clickOn("#descriptionArea");
        robot.write("opis...");
        robot.clickOn("#ratingValueField");
        robot.write("9");
        robot.clickOn("#saveChangesButton");
        Button okButton = robot.lookup("OK").queryAs(Button.class);
        assertNotNull(okButton);
        robot.clickOn("OK");
        robot.clickOn("#cancelButton");
        tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        robot.clickOn("#serialTab");
        TableView tableView = robot.lookup("#tableViewSeries").queryAs(TableView.class);
        assertEquals(5, tableView.getItems().size());
        Stage stage = (Stage) tabPane.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }

}