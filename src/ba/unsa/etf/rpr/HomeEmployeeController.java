package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class HomeEmployeeController {
    public TableView<Movie> tableViewMovies;
    public TableView<Serial> tableViewSeries;
    public TableView<User> usersTableView;
    public TableView<Genre> genresTableView;
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
    public TableColumn guestPasswordCol;
    public TableColumn<User,String> privilegeCol;
    public TableColumn usernameEmployeeCol;
    public TableColumn idEmployeeCol;
    public TableColumn genreIdCol;
    public TableColumn genreTitleCol;
    public MenuItem logoutMenuOption;
    public Button addGenreButton, editGenreAction, addNewMovieButton, addEmployeeButton, addSerialButton, editHotelGuestButton;
    private static VideoLibraryDAO dao = null;
    private ObservableList<Movie> moviesList = null;
    private ObservableList<Serial> serialList = null;
    private ObservableList<User> usersList = null;
    private ObservableList<Employee> employeesList = null;
    private ObservableList<Genre> genresList = null;
    private boolean newGenre = true;
    private Employee employee = null;
    public HomeEmployeeController() {
        dao = VideoLibraryDAO.getInstance();
        moviesList = FXCollections.observableArrayList(dao.getMovies());
        serialList = FXCollections.observableArrayList(dao.getSerials());
        usersList = FXCollections.observableArrayList(dao.getUsers());
        employeesList = FXCollections.observableArrayList(dao.getEmployees());
        genresList = FXCollections.observableArrayList(dao.getGenres());
    }
    public HomeEmployeeController(Employee employee) {
        dao = VideoLibraryDAO.getInstance();
        moviesList = FXCollections.observableArrayList(dao.getMovies());
        serialList = FXCollections.observableArrayList(dao.getSerials());
        usersList = FXCollections.observableArrayList(dao.getUsers());
        employeesList = FXCollections.observableArrayList(dao.getEmployees());
        genresList = FXCollections.observableArrayList(dao.getGenres());
        this.employee = employee;
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
        guestPasswordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        privilegeCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrivilege()));
        employeesTableView.setItems(employeesList);
        idEmployeeCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameEmployeeCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        genresTableView.setItems(genresList);
        genreIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        genreTitleCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
    public void updateLists() {
        employeesList.setAll(dao.getEmployees());
        employeesTableView.setItems(employeesList);
    }
    public void addGenreAction(ActionEvent actionEvent) throws IOException {
        AddGenreController ctrl = new AddGenreController(newGenre);
        Stage stage = (Stage) genresTableView.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addGenre.fxml"));
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Dodaj žanr");
        stage.setScene(new Scene(root, 1200,700));
        stage.show();
    }
    public void editGenreAction(ActionEvent actionEvent) throws IOException {
        EditGenreController ctrl = new EditGenreController(genresTableView.getSelectionModel().getSelectedItem());
        Stage stage = (Stage) genresTableView.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editGenre.fxml"));
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Uredi žanr");
        stage.setScene(new Scene(root, 1200,700));
        stage.show();
    }
    public void deleteGenreAction(ActionEvent actionEvent) {
        if(genresTableView.getSelectionModel().getSelectedItem() != null) {
            Genre genre = new Genre();
            genre.setId(genresTableView.getSelectionModel().getSelectedItem().getId());
            genre.setName(genresTableView.getSelectionModel().getSelectedItem().getName());
            dao.deleteGenre(genre);
            genresList.setAll(dao.getGenres());
            genresTableView.setItems(genresList);
            for (Movie m: moviesList) {
                m.setGenre(dao.getMovieGenres(m.getId()));
            }
            for (Serial s: serialList) {
                s.setGenre(dao.getSerialGenres(s.getId()));
            }
        }
    }
    public void addNewMovieAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) addNewMovieButton.getScene().getWindow();
        AddMovieController ctrl = new AddMovieController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addMovie.fxml"));
        loader.setController(ctrl);
        Parent root = loader.load();
        Scene scene = new Scene(root, 1200, 700);
        stage.setScene(scene);
        stage.setTitle("Dodaj film");
        stage.show();
    }
    public void deleteMovieAction(ActionEvent actionEvent) {
        if(tableViewMovies.getSelectionModel().getSelectedItem() != null) {
            Movie m = new Movie();
            m.setId(tableViewMovies.getSelectionModel().getSelectedItem().getId());
            dao.deleteContent(m);
            moviesList.setAll(dao.getMovies());
            tableViewMovies.setItems(moviesList);
        }
    }
    public void addEmployeeAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) addEmployeeButton.getScene().getWindow();
        AddEmployeeController ctrl = new AddEmployeeController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addEmployee.fxml"));
        loader.setController(ctrl);
        Parent root = loader.load();
        Scene scene = new Scene(root, 1200, 700);
        stage.setScene(scene);
        stage.setTitle("Dodaj uposlenika");
        stage.show();
    }
    public void deleteEmployeeAction(ActionEvent actionEvent) {
        if(employeesTableView.getSelectionModel().getSelectedItem() != null) {
            Employee e = new Employee();
            e.setId(employeesTableView.getSelectionModel().getSelectedItem().getId());
            e.setUsername(employeesTableView.getSelectionModel().getSelectedItem().getUsername());
            dao.deleteEmployee(e);
            employeesList.setAll(dao.getEmployees());
            employeesTableView.setItems(employeesList);
        }
    }
    public void logOutAction(ActionEvent actionEvent) throws SQLException, IOException {
        Stage currentStage = (Stage) tableViewMovies.getScene().getWindow();
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
    public void changePasswordAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        ChangeUsernamePasswordController ctrl = new ChangeUsernamePasswordController(employee);
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("/fxml/changeUsernamePassword.fxml"));
        loader.setController(ctrl);
        Parent root = loader.load();
        Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setTitle("Promjena lozinke");
        stage.setScene(scene);
        stage.show();
    }
    public void changeUsernameAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        ChangeUsernamePasswordController ctrl = new ChangeUsernamePasswordController(employee, true, employeesTableView, employeesList);
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("/fxml/changeUsernamePassword.fxml"));
        loader.setController(ctrl);
        Parent root = loader.load();
        Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setTitle("Promjena korisničkog imena");
        stage.setScene(scene);
        stage.show();
    }
    public void addHotelGuestAction(ActionEvent actionEvent) throws IOException {
        if(dao.checkHotel()) {
            Stage stage = new Stage();
            AddHotelGuestController ctrl = new AddHotelGuestController(usersTableView, usersList);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addHotelGuest.fxml"));
            loader.setController(ctrl);
            Parent root = loader.load();
            Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            stage.setTitle("Novi gost hotela");
            stage.setScene(scene);
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setHeaderText("Broj soba nije definisan!");
            alert.setContentText("Unesite broj soba!");
            alert.showAndWait();
        }
    }
    public void deleteHotelGuestAction(ActionEvent actionEvent) {
        if(usersTableView.getSelectionModel().getSelectedItem() != null) {
            dao.deleteHotelGuest(usersTableView.getSelectionModel().getSelectedItem().getId());
            usersList.setAll(dao.getUsers());
            usersTableView.setItems(usersList);
        }
    }
    public void editHotelGuestAction(ActionEvent actionEvent) throws IOException {
        if(usersTableView.getSelectionModel().getSelectedItem() != null) {
            User u = new User();
            u.setId(usersTableView.getSelectionModel().getSelectedItem().getId());
            u.setFirstName(usersTableView.getSelectionModel().getSelectedItem().getFirstName());
            u.setLastName(usersTableView.getSelectionModel().getSelectedItem().getLastName());
            u.setUsername(usersTableView.getSelectionModel().getSelectedItem().getUsername());
            u.setPassword(usersTableView.getSelectionModel().getSelectedItem().getPassword());
            u.setRoomNumber(usersTableView.getSelectionModel().getSelectedItem().getRoomNumber());
            EditHotelGuestContrroler ctrl = new EditHotelGuestContrroler(u, usersTableView, usersList);
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editHotelGuest.fxml"));
            loader.setController(ctrl);
            Parent root = loader.load();
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle("Izmjena podataka");
            stage.show();
        }

    }
    public void roomsNumberAction(ActionEvent actionEvent) throws IOException {
        AddRoomsNumberController ctrl = new AddRoomsNumberController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addRoomsNumber.fxml"));
        loader.setController(ctrl);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setTitle("Unos broja soba hotela");
        stage.show();
    }
    public void addSerialAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) addSerialButton.getScene().getWindow();
        AddSerialController ctrl = new AddSerialController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addSerial.fxml"));
        loader.setController(ctrl);
        Parent root = loader.load();
        Scene scene = new Scene(root, 1200, 700);
        stage.setScene(scene);
        stage.setTitle("Dodaj seriju");
        stage.show();
    }
    public void deleteSerialAction(ActionEvent actionEvent) {
        if(tableViewSeries.getSelectionModel().getSelectedItem() != null) {
            Serial s = new Serial();
            s.setId(tableViewSeries.getSelectionModel().getSelectedItem().getId());
            dao.deleteContent(s);
            serialList.setAll(dao.getSerials());
            tableViewSeries.setItems(serialList);
        }
    }
}


