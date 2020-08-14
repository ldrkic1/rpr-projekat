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

public class SerialDetailsController {
    public ImageView serialImage;
    public Label titleLabel, descriptionSerialLabel, yearLabel, ratingLabel, priceLabel, directorLabel, genreLabel, seasonsLabel, episodesLabel;
    private Serial serial;
    public VBox actorsVBox;
    public Button backButton;
    public SerialDetailsController(Serial s) {
        serial = s;
    }
    @FXML
    public void initialize() {
        titleLabel.textProperty().set(serial.getTitle());
        yearLabel.textProperty().set(yearLabel.getText()+ " " + serial.getYear());
        String imageSource = serial.getImage();
        Image image = new Image(imageSource);
        serialImage.setImage(image);
        descriptionSerialLabel.textProperty().set(serial.getDescription());
        for(int i = 0; i < serial.getGenre().size();i++) {
            genreLabel.textProperty().set(genreLabel.getText() + serial.getGenre().get(i).getName() + " ");
        }
        if(serial.getPrice() == 0) priceLabel.textProperty().set(priceLabel.getText() + "Besplatno");
        else priceLabel.textProperty().set(priceLabel.getText() + serial.getPrice() + " KM");
        directorLabel.textProperty().set(directorLabel.getText() + serial.getDirector());
        ratingLabel.textProperty().set(ratingLabel.getText() + serial.getRating());
        for(int i = 0; i < serial.getMainActors().size(); i++) {
            HBox actorHbox = new HBox();
            actorHbox.setPadding(new Insets(10,10,10,10));
            actorHbox.setSpacing(5);
            Image actorImage = new Image(serial.getMainActors().get(i).getImage());
            ImageView imageViewAcotr = new ImageView();
            imageViewAcotr.setImage(actorImage);
            imageViewAcotr.setFitHeight(110);
            imageViewAcotr.setFitWidth(100);
            Label actorName = new Label();
            actorName.textProperty().set(String.valueOf(serial.getMainActors().get(i)));
            actorHbox.getChildren().addAll(imageViewAcotr,actorName);
            actorsVBox.getChildren().add(actorHbox);
        }
        seasonsLabel.textProperty().set(seasonsLabel.getText() + serial.getSeasonsNumber());
        episodesLabel.textProperty().set(episodesLabel.getText() + serial.getEpisodesPerSeasonNumber() * serial.getSeasonsNumber());
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editSerialDetails.fxml"));
        EditSerialDetailsController ctrl = new EditSerialDetailsController(serial);
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
