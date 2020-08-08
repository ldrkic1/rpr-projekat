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

public class EditMovieDetailsController {
    private static Movie movie;
    private Scene previousScene;
    public Button saveChangesButton;
    private static VideoLibraryDAO dao = null;
    public TextField titleField;
    public TextField yearField;
    public TextField directorField;
    public TextArea descriptionArea;
    public Slider ratingSlider;
    public TextField ratingValueField;
    public TextField priceField;
    public TextField durationField;
    public TextArea imageUrlArea;
    public ListView<Actor> actorsListView;
    public ListView<Genre> genresListView;
    public Button addActorButton, deleteActorButton, addGenreButton, deleteGenreButton, cancelButton;
    private static ObservableList<Actor> actors = null;
    private ObservableList<Genre> genres = null;
    private boolean allControlsCorrect = true;
    public EditMovieDetailsController(Movie movie) {
        this.movie = movie;
        dao = VideoLibraryDAO.getInstance();
        actors = FXCollections.observableArrayList(dao.getActorsInMovie(movie.getId()));
        genres = FXCollections.observableArrayList(dao.getMovieGenres(movie.getId()));
    }

    public EditMovieDetailsController() {

    }
    public static void updateMainActors() {
        actors.setAll(dao.getActorsInMovie(movie.getId()));
    }
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    @FXML
    public void initialize() {
        titleField.textProperty().set(movie.getTitle());
        yearField.textProperty().set(String.valueOf(movie.getYear()));
        durationField.textProperty().set(String.valueOf(movie.getDurationMinutes()));
        genresListView.setItems(genres);
        directorField.textProperty().set(movie.getDirector());
        actorsListView.setItems(actors);
        descriptionArea.textProperty().set(movie.getDescription());
        ratingSlider.setValue(movie.getRating());
        ratingValueField.textProperty().set(String.valueOf(movie.getRating()));
        priceField.textProperty().set(String.valueOf(movie.getPrice()));
        imageUrlArea.textProperty().set(movie.getImage());
        ratingSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                ratingValueField.setText(String.format("%.2f", newValue));
            }
        });
        ratingValueField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.isEmpty() && isNumeric(newValue)) {
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
                if(!newValue.isEmpty() && isInt(newValue)) {
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
                if(!newValue.isEmpty() && isInt(newValue)) {
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
                if(!newValue.isEmpty() && isNumeric(newValue)) {
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

    public void addActorAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) imageUrlArea.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addActor.fxml"));
        AddActorController ctrl = new AddActorController(movie);
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Dodaj glumca");
        stage.setScene(new Scene(root, 1200,700));
        stage.show();

    }
    public void deleteActorAction(ActionEvent actionEvent) {

    }
    public void addGenreAction(ActionEvent actionEvent) {

    }
    public void deleteGenreAction(ActionEvent actionEvent) {

    }
    public void saveChangesAction(ActionEvent actionEvent) throws IOException {
        if(allControlsCorrect) {
            movie.setTitle(titleField.getText());
            movie.setDurationMinutes(Integer.parseInt(durationField.getText()));
            movie.setPrice(Double.parseDouble(priceField.getText()));
            movie.setImage(imageUrlArea.getText());
            movie.setDescription(descriptionArea.getText());
            movie.setRating(Double.parseDouble(ratingValueField.getText()));
            movie.setYear(Integer.parseInt(yearField.getText()));
            movie.setDirector(directorField.getText());
            dao.updateMovie(movie);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/movieDetails.fxml"));
            MovieDetailsController ctrl = new MovieDetailsController(movie);
            loader.setController(ctrl);
            Parent root = loader.load();
            Scene scene = new Scene(root, 1200, 700);
            Stage stage = (Stage) saveChangesButton.getScene().getWindow();
            stage.setTitle(movie.getTitle());
            stage.setScene(scene);
            stage.show();
        }
    }
    public void cancelAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/movieDetails.fxml"));
        MovieDetailsController ctrl = new MovieDetailsController(movie);
        loader.setController(ctrl);
        Parent root = loader.load();
        Scene scene = new Scene(root, 1200, 700);
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(movie.getTitle());
        stage.show();
    }
}
