package ba.unsa.etf.rpr;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditGenreController {
    private VideoLibraryDAO dao = null;
    public TextField titleField;
    private Genre genre;
    private boolean fieldCorrect = true;
    public Button cancelButton;

    public EditGenreController(Genre g) {
        dao = VideoLibraryDAO.getInstance();
        genre = g;
    }
    @FXML
    public void initialize() {
        titleField.setText(genre.getName());
        titleField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.length() < 3) {
                    titleField.getStyleClass().add("fieldIncorrect");
                    fieldCorrect = false;
                }
                else {
                    titleField.getStyleClass().removeAll("fieldIncorrect");
                    fieldCorrect = true;
                }
            }
        });
    }

    public void cancelAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/homeEmployee.fxml"));
        HomeEmployeeController ctrl = new HomeEmployeeController();
        loader.setController(ctrl);
        Parent root = loader.load();
        Scene scene = new Scene(root, 1200, 700);
        stage.setScene(scene);
        stage.setTitle("Home");
    }

    public void saveGenreAction(ActionEvent actionEvent) throws IOException {
        if(fieldCorrect) {
            genre.setName(titleField.getText());
            dao.updateGenre(genre);
            cancelAction(actionEvent);
        }
    }
}
