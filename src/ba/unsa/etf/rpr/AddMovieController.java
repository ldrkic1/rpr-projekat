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

public class AddMovieController {
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
    public TextField durationField;
    public TextArea imageUrlArea;
    private VideoLibraryDAO dao = null;
    private Movie movie = null;
    private ObservableList<Actor> actorsList = FXCollections.observableArrayList();
    private ObservableList<Genre> genresList = FXCollections.observableArrayList();
    private boolean titleCorrect = false, yearCorrect = false, directorCorrect = false, descriptionCorrect = false, priceCorrect = false, ratingCorrect = false, durationCorrect = false, urlCorrect = false;
    private Employee employee = null;
    public AddMovieController(Employee employee) {
        dao = VideoLibraryDAO.getInstance();
        movie = new Movie();
        this.employee = employee;
    }
    @FXML
    public void initialize() {
        if(movie.getMainActors().size() != 0) {
            actorsList.setAll(movie.getMainActors());
            actorsListView.setItems(actorsList);
        }
        if(movie.getGenre().size() != 0) {
            genresList.setAll(movie.getGenre());
            genresListView.setItems(genresList);
        }
        actorsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Actor>() {
            @Override
            public void changed(ObservableValue<? extends Actor> observableValue, Actor actor, Actor t1) {
                actorsListView.getSelectionModel().select(t1);
            }
        });
        genresListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Genre>() {
            @Override
            public void changed(ObservableValue<? extends Genre> observableValue, Genre actor, Genre t1) {
                genresListView.getSelectionModel().select(t1);
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
        durationField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.isEmpty() && EditMovieDetailsController.isInt(newValue)) {
                    durationCorrect = true;
                    durationField.getStyleClass().removeAll("fieldIncorrect");
                }
                else {
                    durationCorrect = false;
                    durationField.getStyleClass().add("fieldIncorrect");
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
        if(descriptionCorrect && directorCorrect && durationCorrect && priceCorrect && ratingCorrect && titleCorrect && urlCorrect && yearCorrect) {
            movie.setTitle(titleField.getText());
            movie.setYear(Integer.parseInt(yearField.getText()));
            movie.setDirector(directorField.getText());
            movie.setRating(Double.parseDouble(ratingValueField.getText()));
            movie.setDescription(descriptionArea.getText());
            movie.setPrice(Double.parseDouble(priceField.getText()));
            movie.setDurationMinutes(Integer.parseInt(durationField.getText()));
            movie.setImage(imageUrlArea.getText());
            dao.addContent(movie);
            dao.addGenresToContent(movie, movie.getGenre());
            dao.addActorsToContent(movie, movie.getMainActors());
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

    public void addGenreAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addGenre.fxml"));
        AddGenreController ctrl = new AddGenreController(movie, true, employee);
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Dodaj žanr");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        stage.show();
        stage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                genresList.setAll(movie.getGenre());
                genresListView.setItems(genresList);
            }
        });

    }

    public void deleteGenreAction(ActionEvent actionEvent) throws SQLException {
        Genre g = (Genre) genresListView.getSelectionModel().getSelectedItem();
        if(g != null) {
            dao.deleteGenreFromContent(g, movie);
            genresListView.getItems().remove(genresListView.getSelectionModel().getSelectedItem());
            genresListView.refresh();
        }
    }

    public void deleteActorAction(ActionEvent actionEvent) throws SQLException {
        Actor a = (Actor) actorsListView.getSelectionModel().getSelectedItem();
        if(a != null) {
            dao.deleteActorFromContent(a, movie);
            actorsListView.getItems().remove(actorsListView.getSelectionModel().getSelectedItem());
            actorsListView.refresh();
        }
    }

    public void addActorAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addActor.fxml"));
        AddActorController ctrl = new AddActorController(movie, true, employee);
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Dodaj glumca");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        stage.show();
        stage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                actorsList.setAll(movie.getMainActors());
                actorsListView.setItems(actorsList);
            }
        });
    }
}