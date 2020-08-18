package ba.unsa.etf.rpr;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class HomeController {
    public Button showMoviesButton;
    public Button showSerialsButton;
    public VBox contentVbox, topContentVBox, genreVBox;
    private User user;
    private VideoLibraryDAO dao = null;
    private ArrayList<Movie> movies = null;
    private ArrayList<Serial> serials = null;
    private ArrayList<Genre> genres = null;
    public TableView<Movie> movieTable;
    public TableView<Serial> serialTable;
    public TableColumn movieImageCol;
    public TableColumn  movieTitleCol;
    public TableColumn serialImageCol;
    public TableColumn  serialTitleCol;
    private ObservableList<Movie> moviesList = null;
    private ObservableList<Serial> serialList = null;

    public HomeController(User user) {
        this.user = user;
        dao = VideoLibraryDAO.getInstance();
        movies = dao.getMovies();
        serials = dao.getSerials();
        genres = dao.getGenres();
        moviesList = FXCollections.observableArrayList(movies);
        serialList = FXCollections.observableArrayList(serials);
    }
    @FXML
    public void initialize() {
        movieTable.setItems(moviesList);
        movieTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        movieImageCol.setCellValueFactory(new PropertyValueFactory<>("imageView"));
        serialTable.setItems(serialList);
        serialTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        serialImageCol.setCellValueFactory(new PropertyValueFactory<>("imageView"));

        for(Genre g: genres) {
            Button button = new Button();
            button.setText(g.getName());
            button.setPrefWidth(115);
            button.setPrefHeight(48);
            button.getStyleClass().add("button");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    filterContentByGenre(button.getText());
                }
            });
            genreVBox.getChildren().add(button);
        }
    }
    private boolean containGenre(Content content, String genre) {
        for(Genre g: content.getGenre()) {
            if(g.getName().equals(genre)) return true;
        }
        return false;
    }
    public void filterContentByGenre(String title) {
        ArrayList<Movie> filteredMovies = new ArrayList<>();
        ArrayList<Serial> filteredSerials = new ArrayList<>();
        for(Movie m: movies) {
            if(containGenre(m, title)) filteredMovies.add(m);
        }
        for(Serial s: serials) {
            if(containGenre(s, title)) filteredSerials.add(s);
        }
        moviesList.setAll(filteredMovies);
        serialList.setAll(filteredSerials);
        movieTable.setItems(moviesList);
        serialTable.setItems(serialList);
    }
    public void userDataAction(ActionEvent actionEvent) {
    }

    public void changePasswordAction(ActionEvent actionEvent) {
    }

    public void logoutAction(ActionEvent actionEvent) {
    }

    public void showAllGenresAction(ActionEvent actionEvent) {
        moviesList.setAll(movies);
        serialList.setAll(serials);
        movieTable.setItems(moviesList);
        movieTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        movieImageCol.setCellValueFactory(new PropertyValueFactory<>("imageView"));
        serialTable.setItems(serialList);
        serialTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        serialImageCol.setCellValueFactory(new PropertyValueFactory<>("imageView"));
    }
}
