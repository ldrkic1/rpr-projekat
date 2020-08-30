package ba.unsa.etf.rpr;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditGenreController {
    private VideoLibraryDAO dao = null;
    public TextField titleGenreField;
    private Genre genre;
    private boolean fieldCorrect = true;
    public Button cancelButton;

    public EditGenreController(Genre g) {
        dao = VideoLibraryDAO.getInstance();
        genre = g;
    }
    @FXML
    public void initialize() {
        titleGenreField.setText(genre.getName());
        titleGenreField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.length() < 3) {
                    titleGenreField.getStyleClass().add("fieldIncorrect");
                    fieldCorrect = false;
                }
                else {
                    titleGenreField.getStyleClass().removeAll("fieldIncorrect");
                    fieldCorrect = true;
                }
            }
        });
    }

    public void cancelAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void saveGenreAction(ActionEvent actionEvent) throws IOException {
        if(fieldCorrect) {
            genre.setName(titleGenreField.getText());
            dao.updateGenre(genre);
            cancelAction(actionEvent);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setHeaderText(null);
            alert.setContentText("Unesite ispravan naziv žanra!");
            alert.showAndWait();
        }
    }
}
