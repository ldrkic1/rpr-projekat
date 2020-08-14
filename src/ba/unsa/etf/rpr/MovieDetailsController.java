package ba.unsa.etf.rpr;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
public class MovieDetailsController {
    public ImageView movieImage;
    public Label titleLabel, descriptionMovieLabel, yearLabel, ratingLabel, priceLabel, directorLabel, genreLabel, durationLabel;
    private Movie movie;
    public VBox actorsVBox;
    public Button backButton;
    private Scene previousScene;
    public MovieDetailsController(Movie m, Scene scene) {
        movie = m;
        previousScene = scene;
    }

    public MovieDetailsController(Movie movie) {
        this.movie = movie;
    }

    @FXML
    public void initialize() {
        titleLabel.textProperty().set(movie.getTitle());
        yearLabel.textProperty().set(yearLabel.getText() + movie.getYear());
        String imageSource = movie.getImage();
        Image image = new Image(imageSource);
        movieImage.setImage(image);
        descriptionMovieLabel.textProperty().set(movie.getDescription());
        for(int i = 0; i < movie.getGenre().size();i++) {
            genreLabel.textProperty().set(genreLabel.getText() + movie.getGenre().get(i).getName() + " ");
        }
        if(movie.getPrice() == 0) priceLabel.textProperty().set(priceLabel.getText() + "Besplatno");
        else priceLabel.textProperty().set(priceLabel.getText() + movie.getPrice() + " KM");
        directorLabel.textProperty().set(directorLabel.getText() + movie.getDirector());
        ratingLabel.textProperty().set(ratingLabel.getText() + movie.getRating());
        for(int i = 0; i < movie.getMainActors().size(); i++) {
            HBox actorHbox = new HBox();
            actorHbox.setPadding(new Insets(10,10,10,10));
            actorHbox.setSpacing(5);
            Image actorImage = new Image(movie.getMainActors().get(i).getImage());
            ImageView imageViewAcotr = new ImageView();
            imageViewAcotr.setImage(actorImage);
            imageViewAcotr.setFitHeight(110);
            imageViewAcotr.setFitWidth(100);
            Label actorName = new Label();
            actorName.textProperty().set(String.valueOf(movie.getMainActors().get(i)));
            actorHbox.getChildren().addAll(imageViewAcotr,actorName);
            actorsVBox.getChildren().add(actorHbox);
        }
        durationLabel.textProperty().set(durationLabel.getText() + movie.getDurationMinutes() + "min");
        Image icon = new Image("https://cdn1.iconfinder.com/data/icons/ios-11-ui-elements/29/26_back_left_arrow_navigation_sign-512.png");
        ImageView backIcon = new ImageView();
        backIcon.setImage(icon);
        backIcon.setFitHeight(30);
        backIcon.setFitWidth(30);
        backButton.setGraphic(backIcon);
    }
    public void editAction(ActionEvent actionEvent) throws IOException {
        Scene currentScene = titleLabel.getScene();
        Stage currentStage = (Stage) titleLabel.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editMovieDetails.fxml"));
        EditMovieDetailsController ctrl = new EditMovieDetailsController(movie);
        loader.setController(ctrl);
        Parent root = loader.load();
        Scene editScene = new Scene(root, 1200, 700);
        currentStage.setScene(editScene);
        currentStage.show();
    }
    public void backAction(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) titleLabel.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/homeEmployee.fxml"));
        HomeEmployeeController ctrl = new HomeEmployeeController();
        loader.setController(ctrl);
        Parent root = loader.load();
        currentStage.setScene(new Scene(root, 1200, 700));
        currentStage.setTitle("Početna");
        currentStage.show();
    }
}
