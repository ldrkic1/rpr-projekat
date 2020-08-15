package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class EditHotelGuestContrroler {
    public Label nameLabel;
    public Button generatePasswordButton;
    public TextField passwordField;
    public Button cancelButton;
    public ChoiceBox roomChoice;
    private User user;
    private VideoLibraryDAO dao = null;
    private ObservableList<User> list = null;
    private TableView<User> usersTableView = null;
    private ObservableList<User> usersList = null;
    public EditHotelGuestContrroler(User user, TableView<User> usersTableView, ObservableList<User> usersList) {
        this.user = user;
        dao = VideoLibraryDAO.getInstance();
        this.usersTableView = usersTableView;
        this.usersList = usersList;
    }

    @FXML
    public void initialize() {
        int roomsNumber = dao.getRoomsNumber();
        ArrayList<Integer> list = new ArrayList<>();
        for( int i = 1; i < roomsNumber; i++) {
            list.add(i);
        }
        ObservableList<Integer> choiceRoomsNumberList = FXCollections.observableArrayList(list);
        nameLabel.setText(user.getFirstName() + " " + user.getLastName());
        roomChoice.setItems(choiceRoomsNumberList);
        roomChoice.getSelectionModel().select(user.getRoomNumber() - 1);
    }

    public void generatePasswordAction(ActionEvent actionEvent) {
        passwordField.setText(AddHotelGuestController.generatePassword());
    }

    public void cancelAction(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void saveChangesAction(ActionEvent actionEvent) {
        user.setRoomNumber((Integer) roomChoice.getSelectionModel().getSelectedItem());
        if(user.getFirstName().equals("") && user.getLastName().equals("")) user.setUsername(String.valueOf(roomChoice.getSelectionModel().getSelectedItem()));
        if(!passwordField.getText().equals("")) user.setPassword(passwordField.getText());
        dao.updateUser(user);
        usersList.setAll(FXCollections.observableArrayList(dao.getUsers()));
        usersTableView.setItems(usersList);
        cancelAction(actionEvent);
    }
}
