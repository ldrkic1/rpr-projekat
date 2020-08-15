package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
        for( int i = 1; i <= roomsNumber; i++) {
            list.add(i);
        }
        ObservableList<Integer> choiceRoomsNumberList = FXCollections.observableArrayList(freeRomms());
        usernameChoice.setItems(choiceRoomsNumberList);
        usernameChoice.getSelectionModel().selectFirst();
        ArrayList<String> listPrivilege = new ArrayList<>();
        listPrivilege.add("DA");
        listPrivilege.add("NE");
        ObservableList<String> choicePrivilege = FXCollections.observableArrayList(listPrivilege);
        privilegeChoice.setItems(choicePrivilege);
        privilegeChoice.getSelectionModel().selectFirst();
    }
    public ArrayList<Integer> freeRomms() {
        int roomsNumber = dao.getRoomsNumber();
        ArrayList<User> users = dao.getUsers();
        ArrayList<Integer> listRooms = new ArrayList<>();
        for( int i = 1; i <= roomsNumber; i++) {
            listRooms.add(i);
        }
        for(User u: users) {
            listRooms.remove((Integer) u.getRoomNumber());
        }
        return listRooms;
    }
    public static String generatePassword() {
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
        return sb.toString();
    }
    public void generatePasswordAction(ActionEvent actionEvent) {
        passwordField.setText(generatePassword());
    }

    public void cancelAction(ActionEvent actionEvent) {
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }

    public void addAction(ActionEvent actionEvent) {
        if(!passwordField.getText().equals("")) {
            User user = new User();
            user.setFirstName("");
            user.setLastName("");
            user.setUsername(String.valueOf(usernameChoice.getSelectionModel().getSelectedItem()));
            user.setPassword(passwordField.getText());
            user.setRoomNumber((Integer) usernameChoice.getSelectionModel().getSelectedItem());
            if(privilegeChoice.getSelectionModel().getSelectedItem().equals("DA")) user.setPrivilege(true);
            else user.setPrivilege(false);
            dao.addUser(user);
            list.setAll(dao.getUsers());
            tableView.setItems(list);
            cancelAction(actionEvent);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setHeaderText("Lozinka nije generisana");
            alert.setContentText("Potrebno je generisati lozinku!");
            alert.showAndWait();
        }
    }
}
