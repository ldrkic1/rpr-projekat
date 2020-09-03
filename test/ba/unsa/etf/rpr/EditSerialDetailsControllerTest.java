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
class EditSerialDetailsControllerTest {
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
    public void editSerial(FxRobot robot) {
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
        TableView tableView = robot.lookup("#tableViewSeries").queryAs(TableView.class);
        tableView.getSelectionModel().selectFirst();
        robot.clickOn("#detailsButton");
        robot.clickOn("#editButton");
        TextField titleField = robot.lookup("#titleField").queryAs(TextField.class);
        assertEquals("Viza za budućnost", titleField.getText());
        robot.clickOn("#titleField");
        robot.write(", BiH");
        robot.clickOn("#addGenreButton");
        robot.clickOn("#saveGenreButton");
        robot.clickOn("#addActorButton");
        robot.clickOn("#addButton");
        robot.clickOn("#yearField");
        KeyCode ctrl = KeyCode.CONTROL;
        if(System.getProperty("os.name").equals("Mac OS X")) {
            ctrl = KeyCode.COMMAND;
        }
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("2000");
        robot.clickOn("#descriptionArea");
        robot.write("...");
        robot.clickOn("#ratingValueField");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("e");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        Slider slider = robot.lookup("#ratingSlider").queryAs(Slider.class);
        slider.setValue(10);
        TextField seasonsField = robot.lookup("#seasonsField").queryAs(TextField.class);
        robot.clickOn("#seasonsField");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("81.");
        assertTrue(seasonsField.getStyleClass().contains("fieldIncorrect"));
        robot.clickOn("#seasonsField");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("9");
        assertFalse(seasonsField.getStyleClass().contains("fieldIncorrect"));
        ListView listView = robot.lookup("#genresListView").queryAs(ListView.class);
        listView.getSelectionModel().selectLast();
        robot.clickOn("#deleteGenreButton");
        ListView listView1 = robot.lookup("#actorsListView").queryAs(ListView.class);
        listView1.getSelectionModel().selectLast();
        robot.clickOn("#deleteActorButton");
        assertEquals(3, listView1.getItems().size());
        robot.clickOn("#saveChangesButton");
        Label titleLabel = robot.lookup("#titleLabel").queryAs(Label.class);
        assertTrue(titleLabel.getText().equals("Viza za budućnost, BiH"));
        robot.clickOn("#backButton");
        tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        Serial s = (Serial) tableView.getSelectionModel().getSelectedItem();
        assertEquals("Viza za budućnost, BiH", s.getTitle());
        Stage stage = (Stage) tabPane.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
    @Test
    public void editSerialAlert(FxRobot robot) {
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
        TableView tableView = robot.lookup("#tableViewSeries").queryAs(TableView.class);
        tableView.getSelectionModel().selectFirst();
        robot.clickOn("#detailsButton");
        robot.clickOn("#editButton");
        TextField titleField = robot.lookup("#titleField").queryAs(TextField.class);
        assertEquals("Viza za budućnost", titleField.getText());
        robot.clickOn("#titleField");
        KeyCode ctrl = KeyCode.CONTROL;
        if(System.getProperty("os.name").equals("Mac OS X")) {
            ctrl = KeyCode.COMMAND;
        }
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        assertTrue(titleField.getStyleClass().contains("fieldIncorrect"));
        robot.clickOn("#yearField");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("");
        TextField episodesField = robot.lookup("#episodesField").queryAs(TextField.class);
        robot.clickOn("#episodesField");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.write("100 ep");
        assertTrue(episodesField.getStyleClass().contains("fieldIncorrect"));
        robot.clickOn("#episodesField");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.write("100");
        assertFalse(episodesField.getStyleClass().contains("fieldIncorrect"));
        TextArea descriptionArea = robot.lookup("#descriptionArea").queryAs(TextArea.class);
        robot.clickOn("#descriptionArea");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        assertTrue(descriptionArea.getText().isEmpty());
        TextField directorFld = robot.lookup("#directorField").queryAs(TextField.class);
        robot.clickOn("#directorField");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        assertTrue(directorFld.getStyleClass().contains("fieldIncorrect"));
        robot.clickOn("#directorField");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.write("Sinan Ozturk");
        assertFalse(directorFld.getStyleClass().contains("fieldIncorrect"));
        TextArea imageUrlArea = robot.lookup("#imageUrlArea").queryAs(TextArea.class);
        robot.clickOn("#imageUrlArea");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        imageUrlArea.textProperty().set("htt//i.pinimg.com/originals/b9/b9/fb/b9");
        assertTrue(!AddActorController.isValid(imageUrlArea.getText()));
        robot.clickOn("#imageUrlArea");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        imageUrlArea.setText("https://i.pinimg.com/originals/b9/b9/fb/b9b9fb99fcfc3f1bb3794297c509a326.jpg");
        assertFalse(imageUrlArea.getStyleClass().contains("fieldIncorrect"));
        robot.clickOn("#saveChangesButton");
        Button okButton = robot.lookup("OK").queryAs(Button.class);
        assertNotNull(okButton);
        robot.clickOn("OK");
        robot.clickOn("#cancelEditSerialButton");
        robot.clickOn("#backButton");
        tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        tableView = robot.lookup("#tableViewSeries").queryAs(TableView.class);
        tableView.getSelectionModel().selectFirst();
        Serial serial = (Serial) tableView.getSelectionModel().getSelectedItem();
        assertEquals("Viza za budućnost", serial.getTitle());
        Stage stage = (Stage) tabPane.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
}