package ba.unsa.etf.rpr;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class AddHotelGuestController {
    public TextField nameField;
    public TextField surnameField;
    public ChoiceBox usernameChoice;
    public TextField passwordField;
    public ChoiceBox privilegeChoice;
    public Button generatePasswordButton;
    public Button cancelButton;
    public Button addButton;
    private VideoLibraryDAO dao = null;

    public AddHotelGuestController() {
        dao = VideoLibraryDAO.getInstance();

    }
    public AddHotelGuestController(TableView<User> usersTableView, ObservableList<User> usersList) throws IOException {
        dao = VideoLibraryDAO.getInstance();

    }

    @FXML
    public void initialize() throws IOException {

    }
    public void generatePasswordAction(ActionEvent actionEvent) {
    }

    public void cancelAction(ActionEvent actionEvent) {
    }

    public void addAction(ActionEvent actionEvent) {
    }
}
