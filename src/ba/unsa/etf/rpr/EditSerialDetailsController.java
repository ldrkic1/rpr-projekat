package ba.unsa.etf.rpr;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
public class EditSerialDetailsController {
    private Serial serial;
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
    public Button addActorButton, deleteActorButton, addGenreButton, deleteGenreButton, cancelButton;
    private ObservableList<Actor> actors = null;
    private ObservableList<Genre> genres = null;
    private boolean allControlsCorrect = true;
    public EditSerialDetailsController(Serial serial) {
        this.serial = serial;
        dao = VideoLibraryDAO.getInstance();
        actors = FXCollections.observableArrayList(dao.getActorsInSerial(serial.getId()));
        genres = FXCollections.observableArrayList(dao.getSerialGenres(serial.getId()));
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
        actorsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Actor>() {
            @Override
            public void changed(ObservableValue<? extends Actor> observableValue, Actor actor, Actor t1) {
                actorsListView.getSelectionModel().select(t1);
            }
        });
        ratingSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                ratingValueField.setText(String.format("%.2f", newValue));
            }
        });
        ratingValueField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.isEmpty() && EditMovieDetailsController.isNumeric(newValue)) {
                    allControlsCorrect = true;
                    ratingValueField.getStyleClass().removeAll("fieldIncorrect");
                    ratingSlider.setValue(Double.parseDouble(newValue));
                }
                else {
                    allControlsCorrect = false;
                    ratingValueField.getStyleClass().removeAll("fieldCorrect");
                    ratingValueField.getStyleClass().add("fieldIncorrect");
                }
            }
        });
        yearField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.isEmpty() && EditMovieDetailsController.isInt(newValue)) {
                    allControlsCorrect = true;
                    yearField.getStyleClass().removeAll("fieldIncorrect");
                }
                else {
                    allControlsCorrect = false;
                    yearField.getStyleClass().add("fieldIncorrect");
                }
            }
        });
        priceField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.isEmpty() && EditMovieDetailsController.isNumeric(newValue)) {
                    allControlsCorrect = true;
                    priceField.getStyleClass().removeAll("fieldIncorrect");
                }
                else {
                    priceField.getStyleClass().add("fieldIncorrect");
                    allControlsCorrect = false;
                }
            }
        });
        titleField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.isEmpty()) {
                    allControlsCorrect = false;
                    titleField.getStyleClass().add("fieldIncorrect");
                }
                else {
                    allControlsCorrect = true;
                    titleField.getStyleClass().removeAll("fieldIncorrect");
                }
            }
        });
        descriptionArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.isEmpty()) {
                    allControlsCorrect = false;
                    descriptionArea.getStyleClass().add("fieldIncorrect");
                }
                else {
                    allControlsCorrect = true;
                    descriptionArea.getStyleClass().removeAll("fieldIncorrect");
                }
            }
        });
        directorField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.isEmpty()) {
                    allControlsCorrect = false;
                    directorField.getStyleClass().add("fieldIncorrect");
                }
                else {
                    allControlsCorrect = true;
                    directorField.getStyleClass().removeAll("fieldIncorrect");
                }
            }
        });
        imageUrlArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.isEmpty()) {
                    allControlsCorrect = false;
                    imageUrlArea.getStyleClass().add("fieldIncorrect");
                }
                else {
                    allControlsCorrect = true;
                    imageUrlArea.getStyleClass().removeAll("fieldIncorrect");
                }
            }
        });
        seasonsField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.isEmpty() && EditMovieDetailsController.isInt(newValue)) {
                    allControlsCorrect = true;
                    seasonsField.getStyleClass().removeAll("fieldIncorrect");
                }
                else {
                    allControlsCorrect = false;
                    seasonsField.getStyleClass().add("fieldIncorrect");
                }
            }
        });
        episodesField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.isEmpty() && EditMovieDetailsController.isInt(newValue)) {
                    allControlsCorrect = true;
                    episodesField.getStyleClass().removeAll("fieldIncorrect");
                }
                else {
                    allControlsCorrect = false;
                    episodesField.getStyleClass().add("fieldIncorrect");
                }
            }
        });
    }
    public void addActorAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) imageUrlArea.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addActor.fxml"));
        AddActorController ctrl = new AddActorController(serial);
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Dodaj glumca");
        stage.setScene(new Scene(root, 1200,700));
        stage.show();
    }
    public void deleteActorAction(ActionEvent actionEvent) throws SQLException {
        Actor a = actorsListView.getSelectionModel().getSelectedItem();
        if(a != null) {
            dao.deleteActorFromContent(a, serial);
            actorsListView.getItems().remove(actorsListView.getSelectionModel().getSelectedItem());
            actorsListView.refresh();
        }
    }
    public void addGenreAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) imageUrlArea.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addGenre.fxml"));
        AddGenreController ctrl = new AddGenreController(serial);
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Dodaj žanr");
        stage.setScene(new Scene(root, 1200,700));
        stage.show();
    }
    public void deleteGenreAction(ActionEvent actionEvent) throws SQLException {
        Genre g = genresListView.getSelectionModel().getSelectedItem();
        if(g != null) {
            dao.deleteGenreFromContent(g, serial);
            genresListView.getItems().remove(genresListView.getSelectionModel().getSelectedItem());
            genresListView.refresh();
        }
    }
    public void saveChangesAction(ActionEvent actionEvent) throws IOException {
        if(allControlsCorrect) {
            serial.setTitle(titleField.getText());
            serial.setPrice(Double.parseDouble(priceField.getText()));
            serial.setImage(imageUrlArea.getText());
            serial.setDescription(descriptionArea.getText());
            serial.setRating(Double.parseDouble(ratingValueField.getText()));
            serial.setYear(Integer.parseInt(yearField.getText()));
            serial.setDirector(directorField.getText());
            serial.setSeasonsNumber(Integer.parseInt(seasonsField.getText()));
            serial.setEpisodesPerSeasonNumber(Integer.parseInt(episodesField.getText())/Integer.parseInt(seasonsField.getText()));
            dao.updateSerial(serial);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/serialDetails.fxml"));
            SerialDetailsController ctrl = new SerialDetailsController(serial);
            loader.setController(ctrl);
            Parent root = loader.load();
            Scene scene = new Scene(root, 1200, 700);
            Stage stage = (Stage) saveChangesButton.getScene().getWindow();
            stage.setTitle(serial.getTitle());
            stage.setScene(scene);
            stage.show();
        }
    }
    public void cancelAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/serialDetails.fxml"));
        SerialDetailsController ctrl = new SerialDetailsController(serial);
        loader.setController(ctrl);
        Parent root = loader.load();
        Scene scene = new Scene(root, 1200, 700);
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(serial.getTitle());
        stage.show();
    }
}
