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
class EditMovieDetailsControllerTest {
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
    public void editMovie(FxRobot robot) {
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
        tableView.getSelectionModel().selectFirst();
        robot.clickOn("#detailsButton");
        robot.clickOn("#editButton");
        TextField titleField = robot.lookup("#titleField").queryAs(TextField.class);
        assertEquals("Smrt u Sarajevu", titleField.getText());
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
        TextField durationFld = robot.lookup("#durationField").queryAs(TextField.class);
        robot.clickOn("#durationField");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("81.");
        assertTrue(durationFld.getStyleClass().contains("fieldIncorrect"));
        robot.clickOn("#durationField");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("81");
        assertFalse(durationFld.getStyleClass().contains("fieldIncorrect"));
        ListView listView = robot.lookup("#genresListView").queryAs(ListView.class);
        listView.getSelectionModel().selectLast();
        robot.clickOn("#deleteGenreButton");
        ListView listView1 = robot.lookup("#actorsListView").queryAs(ListView.class);
        listView1.getSelectionModel().selectLast();
        robot.clickOn("#deleteActorButton");
        assertEquals(2, listView1.getItems().size());
        robot.clickOn("#saveChangesButton");
        Label titleLabel = robot.lookup("#titleLabel").queryAs(Label.class);
        assertTrue(titleLabel.getText().equals("Smrt u Sarajevu, BiH"));
        robot.clickOn("#backButton");
        tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        Movie m = (Movie) tableView.getSelectionModel().getSelectedItem();
        assertEquals("Smrt u Sarajevu, BiH", m.getTitle());
        Stage stage = (Stage) tabPane.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
    @Test
    public void editMovieAlert(FxRobot robot) {
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
        tableView.getSelectionModel().selectFirst();
        robot.clickOn("#detailsButton");
        robot.clickOn("#editButton");
        TextField titleField = robot.lookup("#titleField").queryAs(TextField.class);
        assertEquals("Smrt u Sarajevu", titleField.getText());
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
        TextField priceFld = robot.lookup("#priceField").queryAs(TextField.class);
        robot.clickOn("#priceField");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.write("12KM");
        assertTrue(priceFld.getStyleClass().contains("fieldIncorrect"));
        robot.clickOn("#priceField");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.write("12");
        assertFalse(priceFld.getStyleClass().contains("fieldIncorrect"));
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
        robot.write("Danis Tanović");
        assertFalse(directorFld.getStyleClass().contains("fieldIncorrect"));
        TextArea imageUrlArea = robot.lookup("#imageUrlArea").queryAs(TextArea.class);
        robot.clickOn("#imageUrlArea");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        imageUrlArea.textProperty().set("https://upload.wikimedia.org/wikipedia/bs/thumb/3/38/Poster_filma_Smrt_u_Sarajevu.jpg/220px-Poster_filma_Smrt_u_Sarajevu");
        robot.clickOn("#imageUrlArea");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        imageUrlArea.setText("https://upload.wikimedia.org/wikipedia/bs/thumb/3/38/Poster_filma_Smrt_u_Sarajevu.jpg/220px-Poster_filma_Smrt_u_Sarajevu.jpg");
        assertFalse(imageUrlArea.getStyleClass().contains("fieldIncorrect"));
        robot.clickOn("#saveChangesButton");
        Button okButton = robot.lookup("OK").queryAs(Button.class);
        assertNotNull(okButton);
        robot.clickOn("OK");
        robot.clickOn("#cancelButton");
        robot.clickOn("#backButton");
        tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        Movie m = (Movie) tableView.getSelectionModel().getSelectedItem();
        assertEquals("Smrt u Sarajevu", m.getTitle());
        Stage stage = (Stage) tabPane.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
}