package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class HomeEmployeeController {
    public TableView<Movie> tableViewMovies;
    public TableView<Serial> tableViewSeries;
    public TableColumn colMovieId;
    public TableColumn colMovieTitle;
    public TableColumn colMovieDirector;
    public TableColumn<Movie,String> colMovieActors;

    public TableColumn colSerialId;
    public TableColumn colSerialTitle;
    public TableColumn colSerialDirector;
    public TableColumn<Serial,String> colSerialActors;
    private VideoLibraryDAO dao = null;
    private ObservableList<Movie> moviesList = null;
    private ObservableList<Serial> serialList = null;
    public HomeEmployeeController() {
        dao = VideoLibraryDAO.getInstance();
        moviesList = FXCollections.observableArrayList(dao.getMovies());
        serialList = FXCollections.observableArrayList(dao.getSerials());
    }

    @FXML
    public void initialize() {
        tableViewMovies.setItems(moviesList);
        colMovieId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMovieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMovieDirector.setCellValueFactory(new PropertyValueFactory<>("director"));
        colMovieActors.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getActorsString()));

        tableViewSeries.setItems(serialList);
        colSerialId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSerialTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colSerialDirector.setCellValueFactory(new PropertyValueFactory<>("director"));
        colSerialActors.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getActorsString()));
    }

}
