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

public class EditMovieDetailsController {
    private static Movie movie;
    public Button saveChangesButton;
    private static VideoLibraryDAO dao = null;
    public Label titleLabel;
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
    private static ObservableList<Genre> genres = null;
    private boolean titleCorrect = true, yearCorrect = true, directorCorrect = true, descriptionCorrect = true, priceCorrect = true, ratingCorrect = true, durationCorrect = true, urlCorrect = true;
    private boolean addNewMovie = false;
    private Employee employee;
    public EditMovieDetailsController(Movie movie, Employee employee) {
        this.movie = movie;
        dao = VideoLibraryDAO.getInstance();
        actors = FXCollections.observableArrayList(dao.getActorsInMovie(movie.getId()));
        genres = FXCollections.observableArrayList(dao.getMovieGenres(movie.getId()));
        this.employee = employee;
    }
    public EditMovieDetailsController(boolean newMovie) {
        if(dao == null) dao = VideoLibraryDAO.getInstance();
        addNewMovie = newMovie;
        movie = new Movie();
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
        if(!addNewMovie) {
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
        }
        else {
            titleLabel.setText("");
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
                if(!newValue.isEmpty() && isNumeric(newValue)) {
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
                if(!newValue.isEmpty() && isInt(newValue)) {
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
                if(!newValue.isEmpty() && isInt(newValue)) {
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
                if(!newValue.isEmpty() && isNumeric(newValue)) {
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
    }
    public void addActorAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addActor.fxml"));
        AddActorController ctrl = new AddActorController(movie, employee);
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Dodaj glumca");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        stage.show();
        stage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                actors.setAll(dao.getActorsInMovie(movie.getId()));
                actorsListView.setItems(actors);
            }
        });
    }
    public void deleteActorAction(ActionEvent actionEvent) throws SQLException {
        Actor a = actorsListView.getSelectionModel().getSelectedItem();
        if(a != null) {
            dao.deleteActorFromContent(a, movie);
            actorsListView.getItems().remove(actorsListView.getSelectionModel().getSelectedItem());
            actorsListView.refresh();
        }
    }
    public void addGenreAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addGenre.fxml"));
        AddGenreController ctrl = new AddGenreController(movie,employee);
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Dodaj žanr");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        stage.show();
        stage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                genres.setAll(dao.getMovieGenres(movie.getId()));
                genresListView.setItems(genres);
            }
        });
    }
    public void deleteGenreAction(ActionEvent actionEvent) throws SQLException {
        Genre g = genresListView.getSelectionModel().getSelectedItem();
        if(g != null) {
            dao.deleteGenreFromContent(g, movie);
            genresListView.getItems().remove(genresListView.getSelectionModel().getSelectedItem());
            genresListView.refresh();
        }
    }
    public void saveChangesAction(ActionEvent actionEvent) throws IOException, InvalidUrlException {
        if(descriptionCorrect && directorCorrect && durationCorrect && priceCorrect && ratingCorrect && titleCorrect && urlCorrect && yearCorrect) {
            movie.setTitle(titleField.getText());
            movie.setDurationMinutes(Integer.parseInt(durationField.getText()));
            movie.setPrice(Double.parseDouble(priceField.getText()));
            movie.setImage(imageUrlArea.getText());
            movie.setDescription(descriptionArea.getText());
            movie.setRating(Double.parseDouble(ratingValueField.getText()));
            movie.setYear(Integer.parseInt(yearField.getText()));
            movie.setDirector(directorField.getText());
            if(!addNewMovie) {
                dao.updateMovie(movie);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/movieDetails.fxml"));
                MovieDetailsController ctrl = new MovieDetailsController(movie, employee);
                loader.setController(ctrl);
                Parent root = loader.load();
                Scene scene = new Scene(root, 1200, 700);
                Stage stage = (Stage) saveChangesButton.getScene().getWindow();
                stage.setTitle(movie.getTitle());
                stage.setScene(scene);
                stage.show();
            }
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/movieDetails.fxml"));
        MovieDetailsController ctrl = new MovieDetailsController(movie, employee);
        loader.setController(ctrl);
        Parent root = loader.load();
        Scene scene = new Scene(root, 1200, 700);
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(movie.getTitle());
        stage.show();
    }
}
