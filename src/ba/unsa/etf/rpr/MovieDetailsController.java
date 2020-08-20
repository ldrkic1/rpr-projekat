package ba.unsa.etf.rpr;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    public Button backButton, editButton, requestWatchButton;
    private boolean userPreview = false;
    private User user = null;
    private VideoLibraryDAO dao = null;
    private Employee employee = null;
    public MovieDetailsController(Movie movie, Employee employee) {
        dao = VideoLibraryDAO.getInstance();
        this.movie = movie;
        this.employee = employee;
    }

    public MovieDetailsController(Movie data, boolean userPreview, User user) {
        if(dao == null) dao = VideoLibraryDAO.getInstance();
        this.movie = data;
        this.userPreview = userPreview;
        this.user = user;
    }

    @FXML
    public void initialize() {
        if(userPreview)  editButton.setVisible(false);
        else requestWatchButton.setVisible(false);
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
        EditMovieDetailsController ctrl = new EditMovieDetailsController(movie, employee);
        loader.setController(ctrl);
        Parent root = loader.load();
        Scene editScene = new Scene(root, 1200, 700);
        currentStage.setScene(editScene);
        currentStage.show();
    }
    public void backAction(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) titleLabel.getScene().getWindow();
        FXMLLoader loader = null;
        if(!userPreview) {
            loader = new FXMLLoader(getClass().getResource("/fxml/homeEmployee.fxml"));
            HomeEmployeeController ctrl = new HomeEmployeeController(employee);
            loader.setController(ctrl);
            currentStage.setTitle("Početna");
        }
        else {
            loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
            HomeController ctrl = new HomeController(user);
            loader.setController(ctrl);
            currentStage.setTitle("Videoteka");
        }
        Parent root = loader.load();
        currentStage.setScene(new Scene(root, 1200, 700));
        currentStage.show();
    }
    public void requestWatchAction(ActionEvent actionEvent) {
        boolean added = dao.addUserRequest(user, movie);
        if(added) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Potvrda");
            alert.setHeaderText("Uspješno ste poslali zahtjev za gledanje filma!");
            alert.setContentText("Osoblje našeg hotela će Vam uskoro omogućiti gledanje filma " + movie.getTitle() + ".");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Obavijest");
            alert.setHeaderText(null);
            alert.setContentText("Već ste poslali zahtjev za gledanje filma " + movie.getTitle() + ". Osoblje našeg hotela će Vam uskoro omogućiti gledanje filma.");
            alert.showAndWait();
        }
    }
}
