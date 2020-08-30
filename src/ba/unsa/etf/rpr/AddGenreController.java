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
import java.io.PushbackInputStream;
import java.security.PublicKey;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddGenreController {
    public Label genreLabel;
    public ChoiceBox choiceGenre;
    public RadioButton genreRadio;
    public Label titleLabel;
    public TextField titleGenreField;
    public Button cancelButton;
    public Button saveGenreButton;
    public Separator separator;
    private Content content;
    private boolean allControlsCorrect = true;
    private static VideoLibraryDAO dao = null;
    private static ObservableList<Genre> genres = null;
    private ArrayList<Genre> movieGenres = null, serialGenres = null;
    private boolean newGenreToDatabase;
    private boolean newContent = false;
    private Employee employee;

    public AddGenreController(boolean newGenre) {
        newGenreToDatabase = newGenre;
        if(dao == null) dao = VideoLibraryDAO.getInstance();
    }

    public AddGenreController(Content content, boolean newContent, Employee employee) {
        this.content = content;
        this.newContent = newContent;
        this.employee = employee;
        newGenreToDatabase = false;
        dao = VideoLibraryDAO.getInstance();
        genres = FXCollections.observableArrayList(dao.getGenres());
        if(content.getGenre().size() != 0) {
                for (Genre g: content.getGenre()) {
                    if(getGenreIndex(g.getId()) != -1) {
                        genres.remove(getGenreIndex(g.getId()));
                    }
                }
        }
    }

    private int getGenreIndex(int id) {
        for( int i = 0; i < genres.size(); i++) {
            if(genres.get(i).getId() == id) return i;
        }
        return -1;
    }
    public AddGenreController(Content content, Employee employee) {
        newGenreToDatabase = false;
        this.content = content;
        dao = VideoLibraryDAO.getInstance();
        genres = FXCollections.observableArrayList(dao.getGenres());
        movieGenres = dao.getMovieGenres(content.getId());
        serialGenres = dao.getSerialGenres(content.getId());
        this.employee = employee;
        if(content instanceof Movie) {
            for (Genre g: movieGenres) {
                if(getGenreIndex(g.getId()) != -1) {
                    genres.remove(getGenreIndex(g.getId()));
                }
            }
        }
        else {
            for (Genre g: serialGenres) {
                if(getGenreIndex(g.getId()) != -1) {
                    genres.remove(getGenreIndex(g.getId()));
                }
            }
        }
    }
    @FXML
    public void initialize() {
        if(!newGenreToDatabase) {
            choiceGenre.setItems(genres);
            choiceGenre.getSelectionModel().selectFirst();
            titleGenreField.setDisable(true);
            titleLabel.setDisable(true);
            genreRadio.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                    if (genreRadio.isSelected()) {
                        genreLabel.setDisable(true);
                        choiceGenre.setDisable(true);
                        titleGenreField.setDisable(false);
                        titleLabel.setDisable(false);
                        allControlsCorrect = false;
                    } else {
                        genreLabel.setDisable(false);
                        choiceGenre.setDisable(false);
                        titleGenreField.setDisable(true);
                        titleLabel.setDisable(true);
                        allControlsCorrect = true;
                    }
                }
            });
        }
        else {
            choiceGenre.setVisible(false);
            genreLabel.setVisible(false);
            genreRadio.setVisible(false);
            separator.setVisible(false);
        }
        titleGenreField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.length() < 3) {
                    titleGenreField.getStyleClass().add("fieldIncorrect");
                    allControlsCorrect = false;
                }
                else {
                    titleGenreField.getStyleClass().removeAll("fieldIncorrect");
                    allControlsCorrect = true;
                }
            }
        });
    }
    public void cancelAction(ActionEvent actionEvent) throws IOException {
       Stage stage = (Stage) cancelButton.getScene().getWindow();
       stage.close();
    }


    public void saveGenreAction(ActionEvent actionEvent) throws IOException, SQLException {
        if(allControlsCorrect) {
            if(!newGenreToDatabase) {
                if (genreRadio.isSelected()) {
                    Genre g = new Genre();
                    g.setName(titleGenreField.getText());
                    dao.addGenre(g);
                    if(!newContent) {
                        dao.addContentGenre(g, content);
                    }
                    else {
                        int id = dao.getGenreId(g.getName());
                        if(id != 0) {
                            g.setId(id);
                            content.getGenre().add(g);
                        }
                    }
                } else {
                    Genre g = (Genre) choiceGenre.getSelectionModel().getSelectedItem();
                    if(!newContent) dao.addContentGenre(g, content);
                    else {
                        content.getGenre().add(g);
                    }
                }
            }
            else {
                Genre g = new Genre();
                g.setName(titleGenreField.getText());
                dao.addGenre(g);
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
}
