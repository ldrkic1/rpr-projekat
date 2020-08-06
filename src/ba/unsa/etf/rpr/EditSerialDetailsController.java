package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class EditSerialDetailsController {
    private Serial serial;
    private Scene previousScene;
    public Button saveChangesButton;
    private VideoLibraryDAO dao = null;
    public TextField titleField;
    public TextField yearField;
    public TextField directorField;
    public TextArea descriptionArea;
    public Slider ratingSlider;
    public TextField ratingValueField;
    public TextField priceField;
    public TextArea imageUrlArea;
    public TextField seasonsField;
    public TextField episodesField;
    public ListView<Actor> actorsListView;
    public ListView<Genre> genresListView;
    public Button addActorButton, deleteActorButton, addGenreButton, deleteGenreButton;
    private ObservableList<Actor> actors = null;
    private ObservableList<Genre> genres = null;
    public EditSerialDetailsController(Serial serial, Scene scene) {
        this.serial = serial;
        previousScene = scene;
        dao = VideoLibraryDAO.getInstance();
        actors = FXCollections.observableArrayList(dao.getActorsInSerial(serial.getId()));
        genres = FXCollections.observableArrayList(dao.getSerialGenres(serial.getId()));
    }
    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    @FXML
    public void initialize() {
        titleField.textProperty().set(serial.getTitle());
        yearField.textProperty().set(String.valueOf(serial.getYear()));
        genresListView.setItems(genres);
        directorField.textProperty().set(serial.getDirector());
        actorsListView.setItems(actors);
        descriptionArea.textProperty().set(serial.getDescription());
        ratingSlider.setValue(serial.getRating());
        ratingValueField.textProperty().set(String.valueOf(serial.getRating()));
        priceField.textProperty().set(String.valueOf(serial.getPrice()));
        imageUrlArea.textProperty().set(serial.getImage());
        seasonsField.textProperty().set(String.valueOf(serial.getSeasonsNumber()));
        episodesField.textProperty().set(String.valueOf(serial.getEpisodesPerSeasonNumber()*serial.getSeasonsNumber()));
        ratingSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                ratingValueField.setText(String.format("%.2f", newValue));
            }
        });
        ratingValueField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.isEmpty() && isNumeric(newValue)) ratingSlider.setValue(Double.parseDouble(newValue));
                else {
                    System.out.println("ne moze neispravna vrijednost");
                }
            }
        });
    }
    public void addActorAction(ActionEvent actionEvent) {

    }
    public void deleteActorAction(ActionEvent actionEvent) {

    }
    public void addGenreAction(ActionEvent actionEvent) {

    }
    public void deleteGenreAction(ActionEvent actionEvent) {

    }
    public void saveChangesAction(ActionEvent actionEvent) {

        Stage stage = (Stage) saveChangesButton.getScene().getWindow();
        stage.setScene(previousScene);
        stage.show();

    }
}
