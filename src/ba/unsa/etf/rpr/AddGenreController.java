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
import java.util.ArrayList;

public class AddGenreController {
    public Label genreLabel;
    public ChoiceBox choiceGenre;
    public RadioButton genreRadio;
    public Label titleLabel;
    public TextField titleField;
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
    private ListView listView = null;
    private Employee employee;
    private ObservableList<Genre> genresList = null;

    public AddGenreController(boolean newGenre) {
        newGenreToDatabase = newGenre;
        if(dao == null) dao = VideoLibraryDAO.getInstance();
    }

    public AddGenreController(Content content, boolean newContent, ListView genresListView, ObservableList<Genre> genresList, Employee employee) {
        this.content = content;
        this.newContent = newContent;
        listView = genresListView;
        this.genresList = genresList;
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
            titleField.setDisable(true);
            titleLabel.setDisable(true);
            genreRadio.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                    if (genreRadio.isSelected()) {
                        genreLabel.setDisable(true);
                        choiceGenre.setDisable(true);
                        titleField.setDisable(false);
                        titleLabel.setDisable(false);
                    } else {
                        genreLabel.setDisable(false);
                        choiceGenre.setDisable(false);
                        titleField.setDisable(true);
                        titleLabel.setDisable(true);
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
        titleField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.length() < 3) {
                    titleField.getStyleClass().add("fieldIncorrect");
                    allControlsCorrect = false;
                }
                else {
                    titleField.getStyleClass().removeAll("fieldIncorrect");
                    allControlsCorrect = true;
                }
            }
        });
    }
    public void cancelAction(ActionEvent actionEvent) throws IOException {
       Stage stage = (Stage) cancelButton.getScene().getWindow();
       if(!newGenreToDatabase) {
           if (!newContent) {
               if (content instanceof Movie) {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editMovieDetails.fxml"));
                   EditMovieDetailsController ctrl = new EditMovieDetailsController((Movie) content, employee);
                   loader.setController(ctrl);
                   Parent root = loader.load();
                   stage.setScene(new Scene(root, 1200, 700));
               } else {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editSerialDetails.fxml"));
                   EditSerialDetailsController ctrl = new EditSerialDetailsController((Serial) content, employee);
                   loader.setController(ctrl);
                   Parent root = loader.load();
                   stage.setScene(new Scene(root, 1200, 700));
               }
               stage.setTitle(content.getTitle());
               stage.show();
           } else {
               stage.close();
           }

       }
    }


    public void saveGenreAction(ActionEvent actionEvent) throws IOException {
        if(allControlsCorrect) {
            if(!newGenreToDatabase) {
                if (genreRadio.isSelected()) {
                    Genre g = new Genre();
                    g.setName(titleField.getText());
                    dao.addGenre(g);
                    if(!newContent) {
                        dao.addContentGenre(g, content);
                    }
                    else {
                        int id = dao.getGenreId(g.getName());
                        if(id != 0) {
                            g.setId(id);
                            content.getGenre().add(g);
                            genresList.add(g);
                            listView.setItems(genresList);
                        }
                    }
                } else {
                    Genre g = (Genre) choiceGenre.getSelectionModel().getSelectedItem();
                    if(!newContent) dao.addContentGenre(g, content);
                    else {
                        content.getGenre().add(g);
                        genresList.add(g);
                        listView.setItems(genresList);
                    }
                }
            }
            else {
                Genre g = new Genre();
                g.setName(titleField.getText());
                dao.addGenre(g);
            }
            cancelAction(actionEvent);
        }
    }
}
