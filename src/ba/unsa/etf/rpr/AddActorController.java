package ba.unsa.etf.rpr;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.util.ArrayList;

public class AddActorController {
    public ChoiceBox actorsChoice;
    public RadioButton addActorRadio;
    public Label choiceLabel;
    private VideoLibraryDAO dao = null;
    private ObservableList<Actor> actors = null;
    private ArrayList<Actor> actorsInMovie = null;
    private int getActorIndex(int id) {
        for( int i = 0; i < actors.size(); i++) {
            if(actors.get(i).getId() == id) return i;
        }
        return -1;
    }
    public AddActorController(Movie m) {
        dao = VideoLibraryDAO.getInstance();
        actors = FXCollections.observableArrayList(dao.getActors());
        actorsInMovie = m.getMainActors();
        for (Actor a: actorsInMovie) {
            if(getActorIndex(a.getId()) != -1) {
                actors.remove(getActorIndex(a.getId()));
            }
        }
    }

    @FXML
    public void initialize() {
        actorsChoice.setItems(actors);
        actorsChoice.getSelectionModel().selectFirst();

        addActorRadio.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldV, Boolean newV) {
                if(addActorRadio.isSelected()) {
                    actorsChoice.setDisable(true);
                    choiceLabel.setDisable(true);
                }
                else {
                    actorsChoice.setDisable(false);
                    choiceLabel.setDisable(false);
                }
            }
        });
    }
}
