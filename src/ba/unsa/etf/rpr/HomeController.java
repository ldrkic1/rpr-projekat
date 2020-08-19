package ba.unsa.etf.rpr;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class HomeController {
    public Button showMoviesButton;
    public Button showSerialsButton;
    public VBox contentVbox, recommendedVbox, genreVBox;
    private User user;
    private VideoLibraryDAO dao = null;
    private ArrayList<Movie> movies = null;
    private ArrayList<Serial> serials = null;
    private ArrayList<Genre> genres = null;
    public TableView<Movie> movieTable;
    public TableView<Serial> serialTable;
    public TableView<Content> contentTable;
    public TableColumn movieImageCol;
    public TableColumn movieTitleCol;
    public TableColumn serialImageCol;
    public TableColumn serialTitleCol;
    public TableColumn contentImageCol;
    public TableColumn contentTitleCol;
    private ObservableList<Movie> moviesList = null;
    private ObservableList<Serial> serialList = null;
    private ObservableList<Content> contentList = null;
    private ArrayList<Content> topContent = null;
    public HomeController(User user) {
        this.user = user;
        dao = VideoLibraryDAO.getInstance();
        movies = dao.getMovies();
        serials = dao.getSerials();
        topContent = dao.getTopContent();
        genres = dao.getGenres();
        moviesList = FXCollections.observableArrayList(movies);
        serialList = FXCollections.observableArrayList(serials);
        contentList = FXCollections.observableArrayList(topContent);
    }
    @FXML
    public void initialize() {
        movieTable.setItems(moviesList);
        movieTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        movieImageCol.setCellValueFactory(new PropertyValueFactory<>("imageView"));
        serialTable.setItems(serialList);
        serialTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        serialImageCol.setCellValueFactory(new PropertyValueFactory<>("imageView"));
        contentTable.setItems(contentList);
        contentImageCol.setCellValueFactory(new PropertyValueFactory<>("imageView"));
        contentTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
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
        ArrayList<Content> filteredContent = new ArrayList<>();
        for(Movie m: movies) {
            if(containGenre(m, title)) filteredMovies.add(m);
        }
        for(Serial s: serials) {
            if(containGenre(s, title)) filteredSerials.add(s);
        }
        for(Content c: topContent) {
            if(containGenre(c, title)) filteredContent.add(c);
        }

        moviesList.setAll(filteredMovies);
        serialList.setAll(filteredSerials);
        contentList.setAll(filteredContent);
        movieTable.setItems(moviesList);
        serialTable.setItems(serialList);
        contentTable.setItems(contentList);
    }
    public void userDataAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        EditUserDataController ctrl = new EditUserDataController(user);
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("/fxml/editUser.fxml"));
        loader.setController(ctrl);
        Parent root = loader.load();
        Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setTitle("Izmjena ličnih podataka");
        stage.setScene(scene);
        stage.show();
    }

    public void changePasswordAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        ChangeUsernamePasswordController ctrl = new ChangeUsernamePasswordController(user);
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("/fxml/changeUsernamePassword.fxml"));
        loader.setController(ctrl);
        Parent root = loader.load();
        Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setTitle("Promjena lozinke");
        stage.setScene(scene);
        stage.show();
    }

    public void logoutAction(ActionEvent actionEvent) throws SQLException, IOException {
        Stage currentStage = (Stage) movieTable.getScene().getWindow();
        Stage stage = new Stage();
        LoginController ctrl = new LoginController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        loader.setController(ctrl);
        Parent root = loader.load();
        root.setId("body");
        Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setScene(scene);
        stage.setTitle("Prijava");
        currentStage.close();
        stage.show();
    }

    public void showAllGenresAction(ActionEvent actionEvent) {
        moviesList.setAll(movies);
        serialList.setAll(serials);
        contentList.setAll(topContent);
        movieTable.setItems(moviesList);
        serialTable.setItems(serialList);
        contentTable.setItems(contentList);
    }
}
