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
    private boolean allControlsCorrect = false;
    public AddMovieController() {
        dao = VideoLibraryDAO.getInstance();
        movie = new Movie();
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
        durationField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.isEmpty() && EditMovieDetailsController.isInt(newValue)) {
                    allControlsCorrect = true;
                    durationField.getStyleClass().removeAll("fieldIncorrect");
                }
                else {
                    allControlsCorrect = false;
                    durationField.getStyleClass().add("fieldIncorrect");
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
    }
    public void cancelAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) actorsListView.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/homeEmployee.fxml"));
        HomeEmployeeController ctrl = new HomeEmployeeController();
        loader.setController(ctrl);
        Parent root = loader.load();
        Scene scene = new Scene(root, 1200, 700);
        stage.setTitle("Početna");
        stage.setScene(scene);
        stage.show();
    }

    public void saveChangesAction(ActionEvent actionEvent) throws IOException {
        if(allControlsCorrect) {
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
    }

    public void addGenreAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addGenre.fxml"));
        AddGenreController ctrl = new AddGenreController(movie, true, genresListView, genresList);
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Dodaj žanr");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        stage.show();
    }

    public void deleteGenreAction(ActionEvent actionEvent) {
    }

    public void deleteActorAction(ActionEvent actionEvent) {
    }

    public void addActorAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addActor.fxml"));
        AddActorController ctrl = new AddActorController(movie, true, actorsListView, actorsList);
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Dodaj glumca");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        stage.show();
    }
}
