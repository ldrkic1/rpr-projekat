package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AddActorController {
    public ChoiceBox actorsChoice;
    public RadioButton addActorRadio;
    public Label choiceLabel, nameLabel, surnameLabel, biographyLabel, birthDateLabel, urlLabel, imgLabel;
    public DatePicker birthDatePicker;
    public TextField nameField, surnameField;
    public TextArea biographyArea, urlArea;
    public ImageView actorImg;
    public Pane pane;
    public Button addActorButton, cancelAddActorButton;
    private VideoLibraryDAO dao = null;
    private ObservableList<Actor> actors = null;
    private ArrayList<Actor> actorsInMovie = null;
    private Content content;
    private boolean firstNameCorrect = true, dateCorrect = true, lastNameCorrect = true, biographyCorrect = true, urlCorrect = true;
    private boolean newContent = false;
    private Employee employee;
    public AddActorController(Content content, boolean newContent, Employee employee) {
        this.content = content;
        this.newContent = newContent;
        dao = VideoLibraryDAO.getInstance();
        actors = FXCollections.observableArrayList(dao.getActors());
        this.employee = employee;
        if(content.getMainActors().size() != 0) {
            for (Actor a: content.getMainActors()) {
                if(getActorIndex(a.getId()) != -1) {
                    actors.remove(getActorIndex(a.getId()));
                }
            }
        }
    }

    private int getActorIndex(int id) {
        for( int i = 0; i < actors.size(); i++) {
            if(actors.get(i).getId() == id) return i;
        }
        return -1;
    }
    public AddActorController(Content c, Employee employee) {
        content = c;
        dao = VideoLibraryDAO.getInstance();
        actors = FXCollections.observableArrayList(dao.getActors());
        this.employee = employee;
        actorsInMovie = c.getMainActors();
        for (Actor a: actorsInMovie) {
            if(getActorIndex(a.getId()) != -1) {
                actors.remove(getActorIndex(a.getId()));
            }
        }
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static boolean isValid(String url)
    {
        /* Try creating a valid URL */
        try {
            new URL(url).toURI();
            return true;
        }

        // If there was an Exception
        // while creating URL object
        catch (Exception e) {
            return false;
        }
    }
    @FXML
    public void initialize() {
        actorsChoice.setItems(actors);
        actorsChoice.getSelectionModel().selectFirst();
        birthDatePicker.setValue(LocalDate.now());
        nameLabel.setDisable(true);
        surnameLabel.setDisable(true);
        biographyLabel.setDisable(true);
        birthDateLabel.setDisable(true);
        birthDatePicker.setDisable(true);
        urlLabel.setDisable(true);
        nameField.setDisable(true);
        surnameField.setDisable(true);
        biographyArea.setDisable(true);
        urlArea.setDisable(true);
        imgLabel.setDisable(true);
        pane.setDisable(true);

        addActorRadio.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldV, Boolean newV) {
                if(addActorRadio.isSelected()) {
                    actorsChoice.setDisable(true);
                    choiceLabel.setDisable(true);
                    nameLabel.setDisable(false);
                    surnameLabel.setDisable(false);
                    biographyLabel.setDisable(false);
                    birthDateLabel.setDisable(false);
                    birthDatePicker.setDisable(false);
                    nameField.setDisable(false);
                    surnameField.setDisable(false);
                    biographyArea.setDisable(false);
                    urlLabel.setDisable(false);
                    urlArea.setDisable(false);
                    imgLabel.setDisable(false);
                    pane.setDisable(false);
                    nameField.getStyleClass().add("fieldIncorrect");
                    surnameField.getStyleClass().add("fieldIncorrect");
                    biographyArea.getStyleClass().add("fieldIncorrect");
                    urlArea.getStyleClass().add("fieldIncorrect");
                    urlCorrect=false;
                    biographyCorrect = false;
                    firstNameCorrect = false;
                    lastNameCorrect = false;
                    //dateCorrect = false;
                }
                else {
                    actorsChoice.setDisable(false);
                    choiceLabel.setDisable(false);
                    nameLabel.setDisable(true);
                    surnameLabel.setDisable(true);
                    biographyLabel.setDisable(true);
                    birthDateLabel.setDisable(true);
                    birthDatePicker.setDisable(true);
                    nameField.setDisable(true);
                    surnameField.setDisable(true);
                    biographyArea.setDisable(true);
                    urlLabel.setDisable(true);
                    urlArea.setDisable(true);
                    imgLabel.setDisable(true);
                    pane.setDisable(true);
                    urlCorrect=true;
                    biographyCorrect = true;
                    firstNameCorrect = true;
                    lastNameCorrect = true;
                    dateCorrect = true;
                }
            }
        });
        birthDatePicker.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
            @Override
            public String toString(LocalDate localDate)
            {
                if(localDate==null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }
            @Override
            public LocalDate fromString(String dateString)
            {
                if(dateString==null || dateString.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(dateString,dateTimeFormatter);
            }
        });
        birthDatePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate oldLocalDate, LocalDate newLocalDate) {
                LocalDate date = birthDatePicker.getValue();
                if (date.toString().equals("")) {
                    birthDatePicker.getStyleClass().add("fieldIncorrect");
                    dateCorrect = false;
                }
                else {
                    birthDatePicker.getStyleClass().removeAll("fieldIncorrect");
                    dateCorrect = true;
                }
            }
        });
        urlArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(isValid(newValue)) {
                    ImageView img = new ImageView();
                    Image image = new Image(newValue);
                    urlArea.getStyleClass().removeAll("fieldIncorrect");
                    urlArea.getStyleClass().add("fieldCorrect");
                    img.setImage(image);
                    img.setFitWidth(170);
                    img.setFitHeight(200);
                    pane.getChildren().add(img);
                    pane.setPadding(new Insets(20,20,20,20));
                    urlCorrect = true;

                }
                else {
                    urlArea.getStyleClass().removeAll("fieldCorrect");
                    urlArea.getStyleClass().add("fieldIncorrect");
                    urlCorrect = false;
                }
            }
        });
        nameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.length() < 2) {
                    nameField.getStyleClass().add("fieldIncorrect");
                    firstNameCorrect = false;
                }
                else {
                    nameField.getStyleClass().removeAll("fieldIncorrect");
                    firstNameCorrect = true;
                }
            }
        });
        surnameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.length() < 2) {
                    surnameField.getStyleClass().add("fieldIncorrect");
                    lastNameCorrect = false;
                }
                else {
                    surnameField.getStyleClass().removeAll("fieldIncorrect");
                    lastNameCorrect = true;
                }
            }
        });
        biographyArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.isEmpty()) {
                    biographyArea.getStyleClass().add("fieldIncorrect");
                    biographyCorrect = false;
                }
                else {
                    biographyArea.getStyleClass().removeAll("fieldIncorrect");
                    biographyCorrect = true;
                }
            }
        });
    }

    public void addActorAction(ActionEvent actionEvent) throws IOException, InvalidUrlException {
        if(biographyCorrect && lastNameCorrect  && firstNameCorrect && dateCorrect && urlCorrect) {
            if(addActorRadio.isSelected()) {
                String date = birthDatePicker.getValue().format(formatter).toString();
                date = date.replace("/", ".");
                Actor a = new Actor();
                a.setFirstName(nameField.getText());
                a.setLastName(surnameField.getText());
                a.setBiography(biographyArea.getText());
                a.setBornDate(LocalDate.parse(birthDatePicker.getValue().format(formatter), formatter));
                a.setImage(urlArea.getText());
                dao.addActor(a);
                if(!newContent) {
                    dao.addContentActor(a, content);
                }
                else {
                    int id = dao.getActorId(a.getFirstName(), a.getLastName());
                    if(id != 0) {
                        a.setId(id);
                        content.getMainActors().add(a);

                    }
                }
            }
            else {
                Actor a = (Actor) actorsChoice.getSelectionModel().getSelectedItem();
                if(!newContent) dao.addContentActor(a,content);
                else {
                    content.getMainActors().add(a);
                }
            }
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

    public void cancelAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) actorsChoice.getScene().getWindow();
        stage.close();
    }
}