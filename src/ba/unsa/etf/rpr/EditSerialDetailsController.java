package ba.unsa.etf.rpr;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

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
    private boolean titleCorrect = true, yearCorrect = true, directorCorrect = true, descriptionCorrect = true, priceCorrect = true, ratingCorrect = true, seasonsCorrect = true, episodesCorrect = true, urlCorrect = true;
    private Employee employee;
    public EditSerialDetailsController(Serial serial, Employee employee) {
        this.serial = serial;
        dao = VideoLibraryDAO.getInstance();
        actors = FXCollections.observableArrayList(dao.getActorsInSerial(serial.getId()));
        genres = FXCollections.observableArrayList(dao.getSerialGenres(serial.getId()));
        this.employee = employee;
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
        episodesField.textProperty().set(String.valueOf(serial.getEpisodesNumber()));
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
                    ratingCorrect = true;
                    ratingValueField.getStyleClass().removeAll("fieldIncorrect");
                    ratingSlider.setValue(Double.parseDouble(newValue));
                }
                else {
                    ratingCorrect = false;
                    ratingValueField.getStyleClass().removeAll("fieldCorrect");
                    ratingValueField.getStyleClass().add("fieldIncorrect");
                }
            }
        });
        yearField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.isEmpty() && EditMovieDetailsController.isInt(newValue)) {
                    yearCorrect = true;
                    yearField.getStyleClass().removeAll("fieldIncorrect");
                }
                else {
                    yearCorrect = false;
                    yearField.getStyleClass().add("fieldIncorrect");
                }
            }
        });
        priceField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.isEmpty() && EditMovieDetailsController.isNumeric(newValue)) {
                    priceCorrect = true;
                    priceField.getStyleClass().removeAll("fieldIncorrect");
                }
                else {
                    priceField.getStyleClass().add("fieldIncorrect");
                    priceCorrect = false;
                }
            }
        });
        titleField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.isEmpty()) {
                    titleCorrect = false;
                    titleField.getStyleClass().add("fieldIncorrect");
                }
                else {
                    titleCorrect = true;
                    titleField.getStyleClass().removeAll("fieldIncorrect");
                }
            }
        });
        descriptionArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.isEmpty()) {
                    descriptionCorrect = false;
                    descriptionArea.getStyleClass().add("fieldIncorrect");
                }
                else {
                    descriptionCorrect = true;
                    descriptionArea.getStyleClass().removeAll("fieldIncorrect");
                }
            }
        });
        directorField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.isEmpty()) {
                    directorCorrect = false;
                    directorField.getStyleClass().add("fieldIncorrect");
                }
                else {
                    directorCorrect = true;
                    directorField.getStyleClass().removeAll("fieldIncorrect");
                }
            }
        });
        imageUrlArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.isEmpty() || !AddActorController.isValid(newValue)) {
                    urlCorrect = false;
                    imageUrlArea.getStyleClass().add("fieldIncorrect");
                }
                else {
                    urlCorrect = true;
                    imageUrlArea.getStyleClass().removeAll("fieldIncorrect");
                }
            }
        });
        seasonsField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.isEmpty() && EditMovieDetailsController.isInt(newValue)) {
                    seasonsCorrect = true;
                    seasonsField.getStyleClass().removeAll("fieldIncorrect");
                }
                else {
                    seasonsCorrect = false;
                    seasonsField.getStyleClass().add("fieldIncorrect");
                }
            }
        });
        episodesField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.isEmpty() && EditMovieDetailsController.isInt(newValue)) {
                    episodesCorrect = true;
                    episodesField.getStyleClass().removeAll("fieldIncorrect");
                }
                else {
                    episodesCorrect = false;
                    episodesField.getStyleClass().add("fieldIncorrect");
                }
            }
        });
    }
    public void addActorAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addActor.fxml"));
        AddActorController ctrl = new AddActorController(serial, employee);
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Dodaj glumca");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        stage.show();
        stage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                actors.setAll(dao.getActorsInSerial(serial.getId()));
                actorsListView.setItems(actors);
            }
        });
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
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addGenre.fxml"));
        AddGenreController ctrl = new AddGenreController(serial, employee);
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Dodaj žanr");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        stage.show();
        stage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                genres.setAll(dao.getMovieGenres(serial.getId()));
                genresListView.setItems(genres);
            }
        });
    }
    public void deleteGenreAction(ActionEvent actionEvent) throws SQLException {
        Genre g = genresListView.getSelectionModel().getSelectedItem();
        if(g != null) {
            dao.deleteGenreFromContent(g, serial);
            genresListView.getItems().remove(genresListView.getSelectionModel().getSelectedItem());
            genresListView.refresh();
        }
    }
    public void saveChangesAction(ActionEvent actionEvent) throws IOException, InvalidURLException {
        if(descriptionCorrect && directorCorrect && episodesCorrect && seasonsCorrect && priceCorrect && ratingCorrect && titleCorrect && urlCorrect && yearCorrect) {
            serial.setTitle(titleField.getText());
            serial.setPrice(Double.parseDouble(priceField.getText()));
            serial.setImage(imageUrlArea.getText());
            serial.setDescription(descriptionArea.getText());
            serial.setRating(Double.parseDouble(ratingValueField.getText()));
            serial.setYear(Integer.parseInt(yearField.getText()));
            serial.setDirector(directorField.getText());
            serial.setSeasonsNumber(Integer.parseInt(seasonsField.getText()));
            serial.setEpisodesNumber(Integer.parseInt(episodesField.getText()));
            dao.updateSerial(serial);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/serialDetails.fxml"));
            SerialDetailsController ctrl = new SerialDetailsController(serial, employee);
            loader.setController(ctrl);
            Parent root = loader.load();
            Scene scene = new Scene(root, 1200, 700);
            Stage stage = (Stage) saveChangesButton.getScene().getWindow();
            stage.setTitle(serial.getTitle());
            stage.setScene(scene);
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setHeaderText(null);
            alert.setContentText("Unesite ispravne podatke!");
            alert.showAndWait();
        }
    }
    public void cancelAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/serialDetails.fxml"));
        SerialDetailsController ctrl = new SerialDetailsController(serial, employee);
        loader.setController(ctrl);
        Parent root = loader.load();
        Scene scene = new Scene(root, 1200, 700);
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(serial.getTitle());
        stage.show();
    }
}
