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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.ArrayList;

@ExtendWith(ApplicationExtension.class)

class RegisterControllerTest {
    VideoLibraryDAO dao = VideoLibraryDAO.getInstance();
    User user = null;
    ArrayList<User> users = dao.getUsers();
    @Start
    public void start (Stage stage) throws Exception {
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
    public void closeRegisterWindow(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write("14");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("3Cs6HrRLJl");
        robot.clickOn("#loginButton");
        TextField firstNameFld = robot.lookup("#firstNameField").queryAs(TextField.class);
        Stage stage = (Stage) firstNameFld.getScene().getWindow();
        robot.clickOn("#cancelButton");
        assertFalse(stage.isShowing());
    }
    @Test
    public void incorrectFields1(FxRobot robot) {
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write("14");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("3Cs6HrRLJl");
        robot.clickOn("#loginButton");
        robot.clickOn("#firstNameField");
        robot.write("A");
        robot.clickOn("#lastNameField");
        robot.write("A");
        TextField firstNameFld = robot.lookup("#firstNameField").queryAs(TextField.class);
        assertTrue(firstNameFld.getStyleClass().contains("fieldIncorrect"));
        TextField lastNameFld = robot.lookup("#lastNameField").queryAs(TextField.class);
        assertTrue(lastNameFld.getStyleClass().contains("fieldIncorrect"));
        Stage stage = (Stage) firstNameFld.getScene().getWindow();
        robot.clickOn("#cancelButton");
        assertFalse(stage.isShowing());
    }
    @Test
    public void incorrectFields2(FxRobot robot) {

        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write("14");
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write("3Cs6HrRLJl");
        robot.clickOn("#loginButton");
        robot.clickOn("#firstNameField");
        robot.write("Neko");
        robot.clickOn("#lastNameField");
        robot.write("Nekic");
        robot.clickOn("#usernameField");
        if(users.size()!= 0) robot.write(users.get(0).getUsername());
        else robot.write("l");
        robot.clickOn("#passwordField");
        robot.write("password");
        robot.clickOn("#repeatPasswordField");
        robot.write("pasword");
        PasswordField passRepeatField = robot.lookup("#repeatPasswordField").queryAs(PasswordField.class);
        assertTrue(passRepeatField.getStyleClass().contains("fieldIncorrect"));
        passRepeatField.clear();
        robot.write("password");
        assertFalse(passRepeatField.getStyleClass().contains("fieldIncorrect"));
        TextField firstNameFld = robot.lookup("#firstNameField").queryAs(TextField.class);
        assertFalse(firstNameFld.getStyleClass().contains("fieldIncorrect"));
        TextField lastNameFld = robot.lookup("#lastNameField").queryAs(TextField.class);
        assertFalse(lastNameFld.getStyleClass().contains("fieldIncorrect"));
        TextField usernameField = robot.lookup("#usernameField").queryAs(TextField.class);
        assertTrue(usernameField.getStyleClass().contains("fieldIncorrect"));
        robot.clickOn("#registerButton");
        Button okButton = robot.lookup("OK").queryAs(Button.class);
        assertNotNull(okButton);
        robot.clickOn("OK");
        Stage stage = (Stage) firstNameFld.getScene().getWindow();
        robot.clickOn("#cancelButton");
        assertFalse(stage.isShowing());
    }
    @Test
    public void register(FxRobot robot) {
        user = new User();
        user.setFirstName("");
        user.setLastName("");
        user.setPrivilege(false);
        user.setPassword(AddHotelGuestController.generatePassword());
        if(users.size() == 0) {
            user.setId(1);
            user.setRoomNumber(1);
            user.setUsername("1");
        }
        else {
            user.setId(users.size() + 1);
            user.setUsername(String.valueOf(users.get(0).getRoomNumber()));
            user.setRoomNumber(users.get(0).getRoomNumber());
        }
        dao.addUser(user);
        TextField usernameFld = robot.lookup("#usernameField").queryAs(TextField.class);
        robot.clickOn("#usernameField");
        robot.write(user.getUsername());
        TextField passwordFld = robot.lookup("#passwordField").queryAs(TextField.class);
        robot.clickOn("#passwordField");
        robot.write(user.getPassword());
        robot.clickOn("#loginButton");
        robot.clickOn("#firstNameField");
        robot.write("Neko");
        robot.clickOn("#lastNameField");
        robot.write("Nekic");
        robot.clickOn("#usernameField");
        if(users.size() == 0) robot.write("neko1");
        else robot.write(users.get(0).getUsername() + "2");
        robot.clickOn("#passwordField");
        robot.write("password");
        robot.clickOn("#repeatPasswordField");
        robot.write("pasword");
        PasswordField passField = robot.lookup("#repeatPasswordField").queryAs(PasswordField.class);
        assertTrue(passField.getStyleClass().contains("fieldIncorrect"));
        passField.clear();
        robot.write("password");
        assertFalse(passField.getStyleClass().contains("fieldIncorrect"));
        TextField firstNameFld = robot.lookup("#firstNameField").queryAs(TextField.class);
        assertFalse(firstNameFld.getStyleClass().contains("fieldIncorrect"));
        TextField lastNameFld = robot.lookup("#lastNameField").queryAs(TextField.class);
        assertFalse(lastNameFld.getStyleClass().contains("fieldIncorrect"));
        TextField usernameField = robot.lookup("#usernameField").queryAs(TextField.class);
        assertFalse(usernameField.getStyleClass().contains("fieldIncorrect"));
        robot.clickOn("#registerButton");
        TabPane tabPane = robot.lookup("#tabPane").queryAs(TabPane.class);
        assertNotNull(tabPane);
        user.setPassword(passwordFld.getText());
        user.setUsername(usernameField.getText());
        user.setFirstName(firstNameFld.getText());
        user.setLastName(lastNameFld.getText());
        dao.updateUser(user);
        Stage stage = (Stage) tabPane.getScene().getWindow();
        Platform.runLater(() -> stage.close());
        dao.deleteHotelGuest(user.getId());
    }

}