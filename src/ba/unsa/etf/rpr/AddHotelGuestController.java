package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;

public class AddHotelGuestController {
    public ChoiceBox usernameChoice;
    public TextField passwordField;
    public ChoiceBox<String> privilegeChoice;
    public Button generatePasswordButton;
    public Button cancelButton;
    public Button addButton;
    private VideoLibraryDAO dao = null;
    private TableView<User> tableView = null;
    private ObservableList<User> list = null;
    public AddHotelGuestController() {
        dao = VideoLibraryDAO.getInstance();

    }
    public AddHotelGuestController(TableView<User> usersTableView, ObservableList<User> usersList) throws IOException {
        dao = VideoLibraryDAO.getInstance();
        tableView = usersTableView;
        list = usersList;
    }

    @FXML
    public void initialize() throws IOException {
        int roomsNumber = dao.getRoomsNumber();
        ArrayList<Integer> list = new ArrayList<>();
        for( int i = 1; i < roomsNumber; i++) {
            list.add(i);
        }
        ObservableList<Integer> choiceRoomsNumberList = FXCollections.observableArrayList(list);
        usernameChoice.setItems(choiceRoomsNumberList);
        usernameChoice.getSelectionModel().selectFirst();
        ArrayList<String> listPrivilege = new ArrayList<>();
        listPrivilege.add("DA");
        listPrivilege.add("NE");
        ObservableList<String> choicePrivilege = FXCollections.observableArrayList(listPrivilege);
        privilegeChoice.setItems(choicePrivilege);
        privilegeChoice.getSelectionModel().selectFirst();
    }
    public void generatePasswordAction(ActionEvent actionEvent) {
        // ASCII range - alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        // each iteration of loop choose a character randomly from the given ASCII range
        // and append it to StringBuilder instance
        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        passwordField.setText(sb.toString());
    }

    public void cancelAction(ActionEvent actionEvent) {
    }

    public void addAction(ActionEvent actionEvent) {
    }
}
