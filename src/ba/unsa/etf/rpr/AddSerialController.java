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

public class AddSerialController {
    public TextField titleField;
    public TextField yearField;
    public TextField directorField;
    public TextArea descriptionArea;
    public Slider ratingSlider;
    public TextField ratingValueField;
    public TextField priceField;
    public ListView actorsListView;
    public Button addActorButton;
    public Button deleteActorButton;
    public ListView genresListView;
    public Button addGenreButton;
    public Button deleteGenreButton;
    public TextArea imageUrlArea;
    public TextField seasonsField;
    public TextField episodesField;
    public Button saveChangesButton;
    public Button cancelButton;
    private VideoLibraryDAO dao = null;
    private Serial serial = null;
    private ObservableList<Actor> actorsList = FXCollections.observableArrayList();
    private ObservableList<Genre> genresList = FXCollections.observableArrayList();
    private boolean seasonsCorrect = false, episodesCorrect = false, titleCorrect = false, yearCorrect = false, directorCorrect = false, descriptionCorrect = false, priceCorrect = false, ratingCorrect = false, durationCorrect = false, urlCorrect = false;
    private Employee employee;
    public AddSerialController(Employee employee) {
        dao = VideoLibraryDAO.getInstance();
        serial = new Serial();
        this.employee = employee;
    }
    @FXML
    public void initialize() {
        if(serial.getMainActors().size() != 0) {
            actorsList.setAll(serial.getMainActors());
            actorsListView.setItems(actorsList);
        }
        if(serial.getGenre().size() != 0) {
            genresList.setAll(serial.getGenre());
            genresListView.setItems(genresList);
        }
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
                if(newValue.isEmpty()) {
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
        AddActorController ctrl = new AddActorController(serial, true, employee);
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Dodaj glumca");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        stage.show();
        stage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                actorsList.setAll(serial.getMainActors());
                actorsListView.setItems(actorsList);
            }
        });
    }

    public void deleteActorAction(ActionEvent actionEvent) throws SQLException {
        Actor a = (Actor) actorsListView.getSelectionModel().getSelectedItem();
        if(a != null) {
            dao.deleteActorFromContent(a, serial);
            actorsListView.getItems().remove(actorsListView.getSelectionModel().getSelectedItem());
            actorsListView.refresh();
        }
    }

    public void addGenreAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addGenre.fxml"));
        AddGenreController ctrl = new AddGenreController(serial, true, employee);
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Dodaj žanr");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        stage.show();
        stage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                genresList.setAll(serial.getGenre());
                genresListView.setItems(genresList);
            }
        });
    }

    public void deleteGenreAction(ActionEvent actionEvent) throws SQLException {
        Genre g = (Genre) genresListView.getSelectionModel().getSelectedItem();
        if(g != null) {
            dao.deleteGenreFromContent(g, serial);
            genresListView.getItems().remove(genresListView.getSelectionModel().getSelectedItem());
            genresListView.refresh();
        }
    }

    public void cancelAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) actorsListView.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/homeEmployee.fxml"));
        HomeEmployeeController ctrl = new HomeEmployeeController(employee);
        loader.setController(ctrl);
        Parent root = loader.load();
        Scene scene = new Scene(root, 1200, 700);
        stage.setTitle("Početna");
        stage.setScene(scene);
        stage.show();
    }

    public void saveChangesAction(ActionEvent actionEvent) throws IOException, InvalidUrlException {
        if(yearCorrect && descriptionCorrect && directorCorrect && episodesCorrect && priceCorrect && ratingCorrect && seasonsCorrect && titleCorrect && urlCorrect) {
            serial.setTitle(titleField.getText());
            serial.setYear(Integer.parseInt(yearField.getText()));
            serial.setDirector(directorField.getText());
            serial.setRating(Double.parseDouble(ratingValueField.getText()));
            serial.setDescription(descriptionArea.getText());
            serial.setPrice(Double.parseDouble(priceField.getText()));
            serial.setSeasonsNumber(Integer.parseInt(seasonsField.getText()));
            serial.setEpisodesNumber(Integer.parseInt(episodesField.getText()));
            serial.setImage(imageUrlArea.getText());
            dao.addContent(serial);
            dao.addGenresToContent(serial, serial.getGenre());
            dao.addActorsToContent(serial, serial.getMainActors());
            cancelAction(actionEvent);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setHeaderText(null);
            alert.setContentText("Unesite ispravne podatke!");
            alert.showAndWait();
        }
    }
}
