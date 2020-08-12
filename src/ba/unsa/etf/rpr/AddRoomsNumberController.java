package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddRoomsNumberController {
    public Button saveButton;
    public TextField numberField;
    private VideoLibraryDAO dao = null;
    private boolean checkHotel = false;
    public AddRoomsNumberController() {
        dao = VideoLibraryDAO.getInstance();
        checkHotel = dao.checkHotel();
    }
    @FXML
    public void initialize() {
        if(checkHotel) {
            numberField.setText(String.valueOf(dao.getRoomsNumber()));
            numberField.getStyleClass().removeAll("fieldIncorrect");
            numberField.getStyleClass().add("fieldCorrect");
        }
        numberField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(EditMovieDetailsController.isInt(newValue)) {
                    int number = Integer.parseInt(newValue);
                    if(number > 0) {
                        numberField.getStyleClass().removeAll("fieldIncorrect");
                        numberField.getStyleClass().add("fieldCorrect");
                    }
                    else {
                        numberField.getStyleClass().removeAll("fieldCorrect");
                        numberField.getStyleClass().add("fieldIncorrect");
                    }
                }
                else {
                    numberField.getStyleClass().removeAll("fieldCorrect");
                    numberField.getStyleClass().add("fieldIncorrect");
                }
            }
        });
    }


    public void saveAction(ActionEvent actionEvent) {
        if(numberField.getStyleClass().contains("fieldCorrect")) {
            if(!checkHotel) {
                dao.addHotel(Integer.parseInt(numberField.getText()));
            }
            else {
                dao.updateHotel(Integer.parseInt(numberField.getText()));
            }
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setHeaderText("Neispravan broj soba!");
            alert.setContentText("Unesite ispravan broj soba!");
            alert.showAndWait();

        }
    }
}
