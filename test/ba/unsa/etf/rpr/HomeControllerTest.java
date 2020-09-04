package ba.unsa.etf.rpr;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
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
class HomeControllerTest {
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
    public void userRequest(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        assertNotNull(usernameFld);
        robot.clickOn("#usernameField");
        robot.write("lamka");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        assertNotNull(passwordFld);
        robot.clickOn("#passwordField");
        robot.write("lamka123");
        robot.clickOn("#loginButton");
        TableView tableView = robot.lookup("#contentTable").queryAs(TableView.class);
        tableView.getSelectionModel().selectFirst();
        robot.clickOn("#detailsButton");
        Button button = robot.lookup("#requestWatchButton").queryAs(Button.class);
        assertNotNull(button);
        robot.clickOn("#requestWatchButton");
        Button okButton = robot.lookup("OK").queryAs(Button.class);
        assertNotNull(okButton);
        robot.clickOn("OK");
        robot.clickOn("#backButton");
        robot.clickOn("#moviesTab");
        TableView tableView1 = robot.lookup("#movieTable").queryAs(TableView.class);
        tableView1.getSelectionModel().selectFirst();
        robot.clickOn("#detailsButton");
        robot.clickOn("#requestWatchButton");
        Button okBtn = robot.lookup("OK").queryAs(Button.class);
        assertNotNull(okBtn);
        robot.clickOn("OK");
        robot.clickOn("#backButton");
        robot.clickOn("#serialTab");
        TableView tableView2 = robot.lookup("#serialTable").queryAs(TableView.class);
        tableView2.getSelectionModel().selectFirst();
        robot.clickOn("#detailsButton");
        robot.clickOn("#requestWatchButton");
        Button okBtn1 = robot.lookup("OK").queryAs(Button.class);
        assertNotNull(okBtn1);
        robot.clickOn("OK");
        robot.clickOn("#backButton");
        Button btn = robot.lookup("#komedijaButton").queryAs(Button.class);
        assertNotNull(btn);
        robot.clickOn("#komedijaButton");
        tableView = robot.lookup("#contentTable").queryAs(TableView.class);
        Stage stage = (Stage) tableView.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
    @Test
    public void userPrivilegue(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        assertNotNull(usernameFld);
        robot.clickOn("#usernameField");
        robot.write("merlin");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        assertNotNull(passwordFld);
        robot.clickOn("#passwordField");
        robot.write("svejelaz");
        robot.clickOn("#loginButton");
        VBox vBox = robot.lookup("#recommendedVbox").queryAs(VBox.class);
        assertFalse(vBox.getChildren().isEmpty());
        Button genreBtn1 = robot.lookup("#komedijaButton").queryAs(Button.class);
        assertNotNull(genreBtn1);
        TableView tableView = robot.lookup("#contentTable").queryAs(TableView.class);
        robot.clickOn("#komedijaButton");
        int komedijaContentNum = tableView.getItems().size();
        robot.clickOn("#allgenresButton");
        tableView = robot.lookup("#contentTable").queryAs(TableView.class);
        int allContentNum = tableView.getItems().size();
        assertTrue( allContentNum > komedijaContentNum );
        tableView = robot.lookup("#contentTable").queryAs(TableView.class);
        Stage stage = (Stage) tableView.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
    @Test
    public void accountOptions(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        assertNotNull(usernameFld);
        robot.clickOn("#usernameField");
        robot.write("lamka");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        assertNotNull(passwordFld);
        robot.clickOn("#passwordField");
        robot.write("lamka123");
        robot.clickOn("#loginButton");
        robot.clickOn("#accountOptions");
        robot.clickOn("#userData");
        Button button = robot.lookup("#cancelButton").queryAs(Button.class);
        assertNotNull(button);
        robot.clickOn("#cancelButton");
        robot.clickOn("#accountOptions");
        robot.clickOn("#changePasswordOption");
        TextField textField = robot.lookup("#currentPasswordField").queryAs(TextField.class);
        assertNotNull(textField);
        robot.clickOn("#cancelButton");
        robot.clickOn("#accountOptions");
        robot.clickOn("#signOutMenuItem");
        usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        Stage stage = (Stage) usernameFld.getScene().getWindow();
        Platform.runLater(() -> stage.close());
    }
}