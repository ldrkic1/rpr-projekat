package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class HomeEmployeeController {
    public TableView<Movie> tableViewMovies;
    public TableView<Serial> tableViewSeries;
    public TableView<User> usersTableView;
    public TableView<Employee> employeesTableView;
    public TableColumn colMovieId;
    public TableColumn colMovieTitle;
    public TableColumn colMovieDirector;
    public TableColumn<Movie,String> colMovieActors;
    public TableColumn colMovieDetailsButton;
    public TableColumn colSerialId;
    public TableColumn colSerialTitle;
    public TableColumn colSerialDirector;
    public TableColumn<Serial,String> colSerialActors;
    public TableColumn colSerialDetailsButton;
    public TableColumn colUserId;
    public TableColumn firstNameCol;
    public TableColumn lastNameCol;
    public TableColumn usernameCol;
    public TableColumn roomNumberCol;
    public TableColumn<User,String> privilegeCol;
    public TableColumn usernameEmployeeCol;
    public TableColumn idEmployeeCol;
    private VideoLibraryDAO dao = null;
    private ObservableList<Movie> moviesList = null;
    private ObservableList<Serial> serialList = null;
    private ObservableList<User> usersList = null;
    private ObservableList<Employee> employeesList = null;
    public HomeEmployeeController() {
        dao = VideoLibraryDAO.getInstance();
        moviesList = FXCollections.observableArrayList(dao.getMovies());
        serialList = FXCollections.observableArrayList(dao.getSerials());
        usersList = FXCollections.observableArrayList(dao.getUsers());
        employeesList = FXCollections.observableArrayList(dao.getEmployees());
    }
    private void addButtonToMovieTable() {
        Callback<TableColumn<Movie, Void>, TableCell<Movie, Void>> cellFactory = new Callback<TableColumn<Movie, Void>, TableCell<Movie, Void>>() {
            @Override
            public TableCell<Movie, Void> call(final TableColumn<Movie, Void> param) {
                final TableCell<Movie, Void> cell = new TableCell<Movie, Void>() {
                    private final Button btn = new Button("Detalji");
                    {
                        //btn.setMaxWidth(colMovieDetailsButton.getMaxWidth());
                        btn.setId("detailsButton");
                        btn.setOnAction((ActionEvent event) -> {
                            Movie data = getTableView().getItems().get(getIndex());
                            try {
                                Stage stage = (Stage) tableViewMovies.getScene().getWindow();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/movieDetails.fxml"));
                                MovieDetailsController ctrl = new MovieDetailsController(data);
                                loader.setController(ctrl);
                                Parent root = loader.load();
                                stage.setTitle(data.getTitle());
                                stage.setScene(new Scene(root,1200,700));
                                stage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colMovieDetailsButton.setCellFactory(cellFactory);
    }
    private void addButtonToserialTable() {
        Callback<TableColumn<Serial, Void>, TableCell<Serial, Void>> cellFactory = new Callback<TableColumn<Serial, Void>, TableCell<Serial, Void>>() {
            @Override
            public TableCell<Serial, Void> call(final TableColumn<Serial, Void> param) {
                final TableCell<Serial, Void> cell = new TableCell<Serial, Void>() {
                    private final Button btn = new Button("Detalji");
                    {
                        //btn.setMaxWidth(colMovieDetailsButton.getMaxWidth());
                        btn.setId("detailsButton");
                        btn.setOnAction((ActionEvent event) -> {
                            Serial data = getTableView().getItems().get(getIndex());
                            try {
                                Stage stage = (Stage) tableViewSeries.getScene().getWindow();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/serialDetails.fxml"));
                                SerialDetailsController ctrl = new SerialDetailsController(data);
                                loader.setController(ctrl);
                                Parent root = loader.load();
                                stage.setTitle(data.getTitle());
                                stage.setScene(new Scene(root,1200,700));
                                stage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colSerialDetailsButton.setCellFactory(cellFactory);
    }
    @FXML
    public void initialize() {
        tableViewMovies.setItems(moviesList);
        colMovieId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMovieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMovieDirector.setCellValueFactory(new PropertyValueFactory<>("director"));
        colMovieActors.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getActorsString()));
        addButtonToMovieTable();
        tableViewSeries.setItems(serialList);
        colSerialId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSerialTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colSerialDirector.setCellValueFactory(new PropertyValueFactory<>("director"));
        colSerialActors.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getActorsString()));
        addButtonToserialTable();
        usersTableView.setItems(usersList);
        colUserId.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        roomNumberCol.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        //privilegeCol.setCellValueFactory(new PropertyValueFactory<>("privilege"));
        privilegeCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrivilege()));
        employeesTableView.setItems(employeesList);
        idEmployeeCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameEmployeeCol.setCellValueFactory(new PropertyValueFactory<>("username"));
    }
    public void updateLists() {
        moviesList = FXCollections.observableArrayList(dao.getMovies());
        serialList = FXCollections.observableArrayList(dao.getSerials());
    }
}
