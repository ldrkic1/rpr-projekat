package ba.unsa.etf.rpr;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class HomeController {
    public Button showMoviesButton;
    public Button showSerialsButton;
    public VBox contentVbox, recommendMainVbox, genreVBox, recommendedVbox;
    private User user;
    private VideoLibraryDAO dao = null;
    private ArrayList<Movie> movies = null;
    private ArrayList<Serial> serials = null;
    private ArrayList<Genre> genres = null;
    public TableView<Movie> movieTable;
    public TableView<Serial> serialTable;
    public TableView<Content> contentTable;
    public TableColumn movieImageCol;
    public TableColumn movieTitleCol;
    public TableColumn serialImageCol;
    public TableColumn serialTitleCol;
    public TableColumn contentImageCol;
    public TableColumn contentTitleCol;
    public TableColumn contentButtonCol;
    public TableColumn movieButtonCol;
    public TableColumn serialButtonCol;
    private ObservableList<Movie> moviesList = null;
    private ObservableList<Serial> serialList = null;
    private ObservableList<Content> contentList = null;
    private ObservableList<Content> recommendedList = null;
    private ArrayList<Content> topContent = null;
    private ArrayList<Content> recommended = null;
    public HomeController(User user) {
        this.user = user;
        dao = VideoLibraryDAO.getInstance();
        movies = dao.getMovies();
        serials = dao.getSerials();
        topContent = dao.getTopContent();
        genres = dao.getGenres();
        moviesList = FXCollections.observableArrayList(movies);
        serialList = FXCollections.observableArrayList(serials);
        contentList = FXCollections.observableArrayList(topContent);
        if(user.isPrivilege()) {
            recommended = dao.getRecommendation(user);
            recommendedList = FXCollections.observableArrayList(recommended);
        }
    }
    //top content - tableNum = 1
    //movies - tableNum = 2
    //serials - tableNum = 3
    private void addButtonInTable(int tableNum) {
        if(tableNum == 1) {
            Callback<TableColumn<Content, Void>, TableCell<Content, Void>> cellFactory = new Callback<TableColumn<Content, Void>, TableCell<Content, Void>>() {
                @Override
                public TableCell<Content, Void> call(final TableColumn<Content, Void> param) {
                    final TableCell<Content, Void> cell = new TableCell<Content, Void>() {
                        private final Button btn = new Button("Opširnije");
                        {
                            btn.setId("detailsButton");
                            btn.setOnAction((ActionEvent event) -> {
                                Content data = getTableView().getItems().get(getIndex());
                                try {
                                    Stage stage = (Stage) contentTable.getScene().getWindow();
                                    FXMLLoader loader = null;
                                    if(data instanceof  Serial) {
                                        loader = new FXMLLoader(getClass().getResource("/fxml/serialDetails.fxml"));
                                        SerialDetailsController ctrl = new SerialDetailsController((Serial) data, true, user);
                                        loader.setController(ctrl);
                                    }
                                    else {
                                        loader = new FXMLLoader(getClass().getResource("/fxml/movieDetails.fxml"));
                                        MovieDetailsController ctrl = new MovieDetailsController((Movie) data, true, user);
                                        loader.setController(ctrl);
                                    }
                                    Parent root = loader.load();
                                    stage.setTitle(data.getTitle());
                                    stage.setScene(new Scene(root, 1200, 700));
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
            contentButtonCol.setCellFactory(cellFactory);
        }
        if(tableNum == 2) {
            Callback<TableColumn<Movie, Void>, TableCell<Movie, Void>> cellFactory = new Callback<TableColumn<Movie, Void>, TableCell<Movie, Void>>() {
                @Override
                public TableCell<Movie, Void> call(final TableColumn<Movie, Void> param) {
                    final TableCell<Movie, Void> cell = new TableCell<Movie, Void>() {
                        private final Button btn = new Button("Opširnije");
                        {
                                btn.setId("detailsButton");                            btn.setOnAction((ActionEvent event) -> {
                                Movie data = getTableView().getItems().get(getIndex());
                                try {
                                    Stage stage = (Stage) movieTable.getScene().getWindow();
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/movieDetails.fxml"));
                                    MovieDetailsController ctrl = new MovieDetailsController(data, true, user);
                                    loader.setController(ctrl);
                                    Parent root = loader.load();
                                    stage.setTitle(data.getTitle());
                                    stage.setScene(new Scene(root, 1200, 700));
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
            movieButtonCol.setCellFactory(cellFactory);
        }
        if(tableNum == 3) {
            Callback<TableColumn<Serial, Void>, TableCell<Serial, Void>> cellFactory = new Callback<TableColumn<Serial, Void>, TableCell<Serial, Void>>() {
                @Override
                public TableCell<Serial, Void> call(final TableColumn<Serial, Void> param) {
                    final TableCell<Serial, Void> cell = new TableCell<Serial, Void>() {
                        private final Button btn = new Button("Opširnije");
                        {
                            btn.setId("detailsButton");
                            btn.setOnAction((ActionEvent event) -> {
                                Serial data = getTableView().getItems().get(getIndex());
                                try {
                                    Stage stage = (Stage) serialTable.getScene().getWindow();
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/serialDetails.fxml"));
                                    SerialDetailsController ctrl = new SerialDetailsController(data, true, user);
                                    loader.setController(ctrl);
                                    Parent root = loader.load();
                                    stage.setTitle(data.getTitle());
                                    stage.setScene(new Scene(root, 1200, 700));
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
            serialButtonCol.setCellFactory(cellFactory);
        }
    }

    @FXML
    public void initialize() {
        movieTable.setItems(moviesList);
        movieTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        movieImageCol.setCellValueFactory(new PropertyValueFactory<>("imageView"));
        serialTable.setItems(serialList);
        serialTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        serialImageCol.setCellValueFactory(new PropertyValueFactory<>("imageView"));
        contentTable.setItems(contentList);
        contentImageCol.setCellValueFactory(new PropertyValueFactory<>("imageView"));
        contentTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        for(Genre g: genres) {
            Button button = new Button();
            button.setText(g.getName());
            button.setPrefWidth(115);
            button.setPrefHeight(48);
            String id = g.getName().toLowerCase();
            id+="Button";
            button.setId(id);
            button.getStyleClass().add("button");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    filterContentByGenre(button.getText());
                }
            });
            genreVBox.getChildren().add(button);
        }
        if(user.isPrivilege()) {
            for(int i = 0; i < recommended.size(); i++) {
                VBox vBox = new VBox();
                vBox.setPadding(new Insets(10,10,10,10));
                vBox.setSpacing(5);
                Image image = new Image(recommended.get(i).getImage());
                ImageView imageView = new ImageView();
                imageView.setImage(image);
                imageView.setFitHeight(110);
                imageView.setFitWidth(100);
                Label title = new Label();
                title.textProperty().set(String.valueOf(recommended.get(i).getTitle()));
                vBox.getChildren().addAll(imageView,title);
                recommendedVbox.getChildren().add(vBox);
            }
        }
        else {
            recommendMainVbox.setVisible(false);
        }
        addButtonInTable(1);
        addButtonInTable(2);
        addButtonInTable(3);
    }
    private boolean containGenre(Content content, String genre) {
        for(Genre g: content.getGenre()) {
            if(g.getName().equals(genre)) return true;
        }
        return false;
    }
    public void filterContentByGenre(String title) {
        ArrayList<Movie> filteredMovies = new ArrayList<>();
        ArrayList<Serial> filteredSerials = new ArrayList<>();
        ArrayList<Content> filteredContent = new ArrayList<>();
        for(Movie m: movies) {
            if(containGenre(m, title)) filteredMovies.add(m);
        }
        for(Serial s: serials) {
            if(containGenre(s, title)) filteredSerials.add(s);
        }
        for(Content c: topContent) {
            if(containGenre(c, title)) filteredContent.add(c);
        }

        moviesList.setAll(filteredMovies);
        serialList.setAll(filteredSerials);
        contentList.setAll(filteredContent);
        movieTable.setItems(moviesList);
        serialTable.setItems(serialList);
        contentTable.setItems(contentList);
    }
    public void userDataAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        EditUserDataController ctrl = new EditUserDataController(user);
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("/fxml/editUser.fxml"));
        loader.setController(ctrl);
        Parent root = loader.load();
        Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setTitle("Izmjena ličnih podataka");
        stage.setScene(scene);
        stage.show();
    }

    public void changePasswordAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        ChangeUsernamePasswordController ctrl = new ChangeUsernamePasswordController(user);
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("/fxml/changeUsernamePassword.fxml"));
        loader.setController(ctrl);
        Parent root = loader.load();
        Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setTitle("Promjena lozinke");
        stage.setScene(scene);
        stage.show();
    }

    public void logoutAction(ActionEvent actionEvent) throws SQLException, IOException {
        Stage currentStage = (Stage) movieTable.getScene().getWindow();
        Stage stage = new Stage();
        LoginController ctrl = new LoginController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        loader.setController(ctrl);
        Parent root = loader.load();
        Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setScene(scene);
        stage.setTitle("Prijava");
        currentStage.close();
        stage.show();
    }

    public void showAllGenresAction(ActionEvent actionEvent) {
        moviesList.setAll(movies);
        serialList.setAll(serials);
        contentList.setAll(topContent);
        movieTable.setItems(moviesList);
        serialTable.setItems(serialList);
        contentTable.setItems(contentList);
    }
}
