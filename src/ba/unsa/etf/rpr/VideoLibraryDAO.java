package ba.unsa.etf.rpr;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class VideoLibraryDAO {
    private static VideoLibraryDAO instance;
    private Connection connection;
    private PreparedStatement getUsersStatement, getUserStatement, getUserByNameStatement, getUserByIdStatement, getHotelRoomsNumberStatement, getHotelsStatement, addHotelStatement, updateHotelStatement;
    private PreparedStatement getEmployeeStatament, getEmployeeByIdStatement, deleteEmployeeStatement, getEmplyeesStamement, addEmployeeStatement, nextIdEmployee, updateEmployeeStatement;
    private PreparedStatement getActorStatement, getActorByIdStatement, getActorsStatement;
    private PreparedStatement getGenreStatement, getGenreByIdStatement, getGenresStatement, deleteGenreStatement, getGenreContentsStatement, updateGenreStatement;
    private PreparedStatement getMoviesStatement, addMovieStatement, addContentStatement, deleteContentStatement, deleteMovieStatement, deleteSerialStatement;
    private PreparedStatement getSeriesStatement, addSerialStatement, addUserStatement, userNextID, deleteUserStatement, updateUserStatement;
    private PreparedStatement getContentActor, getContentGenre, addContentGenreStatement, getContetStatement;
    private PreparedStatement getActorsInMovieStatement, getActorsInSerialStatement;
    private PreparedStatement deleteActorFromContent, deleteGenreFromContent, deleteGenreContent, deleteActorContent;
    private PreparedStatement getMovieGenresStatement, getSerialGenresStatement;
    private PreparedStatement updateContetntStatement, updateMovieStatement, updateSerialStatement, nextContentIdStatement;
    private PreparedStatement addActorStatement, addGenreStatement, nextIdGenreStatement, nextIdStatement, nextIdContentAcotrStatement, nextIdContentGenreStatement, addContentActorStatement;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static VideoLibraryDAO getInstance() {
        if (instance == null) instance = new VideoLibraryDAO();
        return instance;
    }

    private void database() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("database.db.sql"));
            String sqlUpit = "";
            while (ulaz.hasNext()) {
                sqlUpit += ulaz.nextLine();
                if (sqlUpit.charAt(sqlUpit.length() - 1) == ';') {
                    try {
                        Statement stmt = connection.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private VideoLibraryDAO() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            getUserStatement = connection.prepareStatement("SELECT id, first_name, last_name, username, password, room_number, privilege FROM user WHERE username=?");
        } catch (SQLException throwables) {
            database();
            try {
                getUserStatement = connection.prepareStatement("SELECT id, first_name, last_name, username, password, room_number, privilege FROM user WHERE username=?");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            getUsersStatement = connection.prepareStatement("SELECT * FROM user");
            getUserByNameStatement = connection.prepareStatement("SELECT id, username, password, room_number, privilege FROM user WHERE first_name=? AND last_name=?");
            getUserByIdStatement = connection.prepareStatement("SELECT first_name, last_name, username, password, room_number, privilege FROM user WHERE id=?");
            getEmployeeStatament = connection.prepareStatement("SELECT id, password FROM employee WHERE username=?");
            getEmployeeByIdStatement = connection.prepareStatement("SELECT username, password FROM employee WHERE id=?");
            getEmplyeesStamement = connection.prepareStatement("SELECT * FROM employee");
            getActorByIdStatement = connection.prepareStatement("SELECT first_name, last_name, biography, born_date, image FROM actor WHERE id=?");
            getActorStatement = connection.prepareStatement("SELECT id, biography, born_date, image FROM actor WHERE first_name=? AND last_name=?");
            getActorsStatement = connection.prepareStatement("SELECT * FROM actor");
            getGenreStatement = connection.prepareStatement("SELECT id FROM genre WHERE name=?");
            getGenreByIdStatement = connection.prepareStatement("SELECT name FROM genre WHERE id=?");
            getGenresStatement = connection.prepareStatement("SELECT * FROM genre");
            getMoviesStatement = connection.prepareStatement("SELECT c.id, c.title, c.year, c.director, c.description,c.rating, c.image, c.price, m.duration_minutes FROM content c, movie m WHERE c.id=m.id");
            getSeriesStatement = connection.prepareStatement("SELECT c.id, c.title, c.year, c.director, c.description,c.rating, c.image, c.price, s.seasons_number, s.episodes_per_season FROM content c, serial s WHERE c.id=s.id");
            getActorsInMovieStatement = connection.prepareStatement("SELECT a.id, a.first_name, a.last_name, a.biography, a.born_date, a.image FROM actor a, content c, movie m, content_actor ca WHERE m.id=? AND a.id =ca.actor_id AND ca.content_id=c.id AND c.id=m.id");
            getActorsInSerialStatement = connection.prepareStatement("SELECT a.id, a.first_name, a.last_name, a.biography, a.born_date, a.image FROM content_actor ca, serial s, content c, actor a WHERE s.id =? AND s.id=c.id  AND c.id=ca.content_id AND a.id =ca.actor_id");
            getMovieGenresStatement = connection.prepareStatement("SELECT g.id, g.name FROM genre g, content_genre cg, content c, movie m WHERE m.id=? AND  m.id=c.id AND c.id = cg.content_id AND cg.genre_id=g.id");
            getSerialGenresStatement = connection.prepareStatement("SELECT g.id, g.name FROM genre g, content_genre cg, content c, serial s WHERE s.id=? AND  s.id=c.id AND c.id = cg.content_id AND cg.genre_id=g.id");
            updateContetntStatement = connection.prepareStatement("UPDATE content SET title=?,year=?,director=?,description=?,rating=?,image=?,price=? WHERE id=?");
            updateMovieStatement = connection.prepareStatement("UPDATE movie SET duration_minutes=? WHERE id=?");
            updateSerialStatement = connection.prepareStatement("UPDATE  serial SET seasons_number=?, episodes_per_season=? WHERE id=?");
            updateGenreStatement = connection.prepareStatement("UPDATE genre SET name=? WHERE id=?");
            nextIdStatement = connection.prepareStatement("SELECT MAX(id)+1 FROM actor");
            nextIdGenreStatement = connection.prepareStatement("SELECT MAX(id)+1 FROM genre");
            nextIdContentAcotrStatement = connection.prepareStatement("SELECT MAX(id)+1 FROM content_actor");
            nextIdContentGenreStatement = connection.prepareStatement("SELECT MAX(id)+1 FROM content_genre");
            addActorStatement = connection.prepareStatement("INSERT INTO actor VALUES (?,?,?,?,?,?)");
            addGenreStatement = connection.prepareStatement("INSERT INTO genre VALUES (?,?)");
            addContentActorStatement = connection.prepareStatement("INSERT INTO content_actor VALUES (?,?,?)");
            addContentGenreStatement = connection.prepareStatement("INSERT INTO content_genre VALUES (?,?,?)");
            getContentActor = connection.prepareStatement("SELECT * FROM content_actor WHERE content_id=? AND actor_id=?");
            getContentGenre = connection.prepareStatement("SELECT * FROM content_genre WHERE content_id=? AND genre_id=?");
            getGenreContentsStatement = connection.prepareStatement("SELECT cg.id FROM content c, content_genre cg WHERE cg.genre_id=? AND c.id=cg.content_id");
            deleteActorFromContent = connection.prepareStatement("DELETE FROM content_actor WHERE id=?");
            deleteGenreFromContent = connection.prepareStatement("DELETE FROM content_genre WHERE id=?");
            deleteGenreStatement = connection.prepareStatement("DELETE FROM genre WHERE id=?");
            addContentStatement = connection.prepareStatement("INSERT INTO content VALUES (?,?,?,?,?,?,?,?)");
            addMovieStatement = connection.prepareStatement("INSERT INTO movie values (?,?)");
            addSerialStatement = connection.prepareStatement("INSERT INTO serial VALUES (?,?,?)");
            nextContentIdStatement = connection.prepareStatement("SELECT MAX(id)+1 FROM content");
            nextIdEmployee = connection.prepareStatement("SELECT MAX(id)+1 FROM employee");
            addEmployeeStatement = connection.prepareStatement("INSERT INTO employee VALUES (?,?,?)");
            deleteEmployeeStatement = connection.prepareStatement("DELETE FROM employee WHERE id=?");
            updateEmployeeStatement = connection.prepareStatement("UPDATE employee SET username=?, password=? WHERE id=?");
            getHotelRoomsNumberStatement = connection.prepareStatement("SELECT rooms_number FROM hotel WHERE id=?");
            getHotelsStatement = connection.prepareStatement("SELECT * FROM hotel");
            addHotelStatement = connection.prepareStatement("INSERT INTO hotel VALUES (?,?)");
            updateHotelStatement = connection.prepareStatement("UPDATE hotel SET rooms_number=? WHERE id=?");
            addUserStatement = connection.prepareStatement("INSERT INTO user VALUES (?,?,?,?,?,?,?)");
            userNextID = connection.prepareStatement("SELECT MAX(id)+1 FROM user");
            deleteUserStatement = connection.prepareStatement("DELETE FROM user where id=?");
            deleteContentStatement = connection.prepareStatement("DELETE FROM content where id=?");
            deleteMovieStatement = connection.prepareStatement("DELETE FROM movie where id=?");
            deleteSerialStatement = connection.prepareStatement("DELETE FROM serial WHERE id=?");
            deleteActorContent = connection.prepareStatement("DELETE FROM content_actor where content_id=?");
            deleteGenreContent = connection.prepareStatement("DELETE from content_genre where content_id=?");
            getContetStatement = connection.prepareStatement("SELECT id from content WHERE title=?");
            updateUserStatement = connection.prepareStatement("UPDATE user SET first_name=?,last_name=?,username=?,password=?,room_number=? WHERE id=?");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkHotel() {
        try {
            ResultSet resultSet = getHotelsStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public LocalDate stringToDate(String date) {
        ArrayList<String> days = new ArrayList<>(Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09"));
        String[] temp = date.split("\\.");
        String d = "";
        if (Integer.parseInt(temp[0]) >= 1 && Integer.parseInt(temp[0]) <= 9 && !days.contains(temp[0])) d += "0";
        d += temp[0] + "/";
        if (Integer.parseInt(temp[1]) >= 1 && Integer.parseInt(temp[1]) <= 9 && !days.contains(temp[1])) d += "0";
        d += temp[1] + "/" + temp[2];
        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(d, formatter);
        return localDate;
    }

    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> list = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = getEmplyeesStamement.executeQuery();
            while (resultSet.next()) {
                list.add(new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public Employee getEmployee(String username) {
        try {
            getEmployeeStatament.setString(1, username);
            ResultSet resultSet = getEmployeeStatament.executeQuery();
            if (resultSet.next()) return new Employee(resultSet.getInt(1), username, resultSet.getString(2));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public ArrayList<Genre> getMovieGenres(int movieId) {
        ArrayList<Genre> genres = new ArrayList<>();
        try {
            getMovieGenresStatement.setInt(1, movieId);
            ResultSet resultSet = getMovieGenresStatement.executeQuery();
            while (resultSet.next()) {
                Genre genre = new Genre(resultSet.getInt(1), resultSet.getString(2));
                genres.add(genre);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return genres;
    }

    public ArrayList<Genre> getSerialGenres(int serialId) {
        ArrayList<Genre> genres = new ArrayList<>();
        try {
            getSerialGenresStatement.setInt(1, serialId);
            ResultSet resultSet = getSerialGenresStatement.executeQuery();
            while (resultSet.next()) {
                Genre genre = new Genre(resultSet.getInt(1), resultSet.getString(2));
                genres.add(genre);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return genres;
    }

    public ArrayList<Actor> getActorsInMovie(int movieId) {
        ArrayList<Actor> actors = new ArrayList<>();
        try {
            getActorsInMovieStatement.setInt(1, movieId);
            ResultSet resultSet = getActorsInMovieStatement.executeQuery();
            while (resultSet.next()) {
                Actor actor = new Actor(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), stringToDate(resultSet.getString(5)), resultSet.getString(6));
                actors.add(actor);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return actors;
    }

    public ArrayList<Actor> getActorsInSerial(int serialId) {
        ArrayList<Actor> actors = new ArrayList<>();
        try {
            getActorsInSerialStatement.setInt(1, serialId);
            ResultSet resultSet = getActorsInSerialStatement.executeQuery();
            while (resultSet.next()) {
                Actor actor = new Actor(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), stringToDate(resultSet.getString(5)), resultSet.getString(6));
                actors.add(actor);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return actors;
    }

    public ArrayList<Movie> getMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        try {
            ResultSet resultSet = getMoviesStatement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt(1));
                movie.setTitle(resultSet.getString(2));
                movie.setYear(resultSet.getInt(3));
                movie.setDirector(resultSet.getString(4));
                movie.setDescription(resultSet.getString(5));
                movie.setRating(resultSet.getDouble(6));
                movie.setImage(resultSet.getString(7));
                movie.setPrice(resultSet.getDouble(8));
                movie.setDurationMinutes(resultSet.getInt(9));
                movie.setMainActors(getActorsInMovie(movie.getId()));
                movie.setGenre(getMovieGenres(movie.getId()));
                movies.add(movie);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return movies;
    }

    public ArrayList<Serial> getSerials() {
        ArrayList<Serial> serials = new ArrayList<>();
        try {
            ResultSet resultSet = getSeriesStatement.executeQuery();
            while (resultSet.next()) {
                Serial serial = new Serial();
                serial.setId(resultSet.getInt(1));
                serial.setTitle(resultSet.getString(2));
                serial.setYear(resultSet.getInt(3));
                serial.setDirector(resultSet.getString(4));
                serial.setDescription(resultSet.getString(5));
                serial.setRating(resultSet.getDouble(6));
                serial.setImage(resultSet.getString(7));
                serial.setPrice(resultSet.getDouble(8));
                serial.setSeasonsNumber(resultSet.getInt(9));
                serial.setEpisodesPerSeasonNumber(resultSet.getInt(10));
                serial.setMainActors(getActorsInSerial(serial.getId()));
                serial.setGenre(getSerialGenres(serial.getId()));
                serials.add(serial);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return serials;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            ResultSet resultSet = getUsersStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setUsername(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setRoomNumber(resultSet.getInt(6));
                if (resultSet.getInt(7) == 1) user.setPrivilege(true);
                else user.setPrivilege(false);
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public void updateMovie(Movie m) {
        try {
            updateContetntStatement.setString(1, m.getTitle());
            updateContetntStatement.setInt(2, m.getYear());
            updateContetntStatement.setString(3, m.getDirector());
            updateContetntStatement.setString(4, m.getDescription());
            updateContetntStatement.setDouble(5, m.getRating());
            updateContetntStatement.setString(6, m.getImage());
            updateContetntStatement.setDouble(7, m.getPrice());
            updateContetntStatement.setInt(8, m.getId());
            updateContetntStatement.executeUpdate();
            updateMovieStatement.setInt(1, m.getDurationMinutes());
            updateMovieStatement.setInt(2, m.getId());
            updateMovieStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateSerial(Serial s) {
        try {
            updateContetntStatement.setString(1, s.getTitle());
            updateContetntStatement.setInt(2, s.getYear());
            updateContetntStatement.setString(3, s.getDirector());
            updateContetntStatement.setString(4, s.getDescription());
            updateContetntStatement.setDouble(5, s.getRating());
            updateContetntStatement.setString(6, s.getImage());
            updateContetntStatement.setDouble(7, s.getPrice());
            updateContetntStatement.setInt(8, s.getId());
            updateContetntStatement.executeUpdate();
            updateSerialStatement.setInt(1, s.getSeasonsNumber());
            updateSerialStatement.setInt(3, s.getEpisodesPerSeasonNumber());
            updateSerialStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateGenre(Genre genre) {
        try {
            updateGenreStatement.setString(1, genre.getName());
            updateGenreStatement.setInt(2, genre.getId());
            updateGenreStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Actor> getActors() {
        ArrayList<Actor> actors = new ArrayList<>();
        try {
            ResultSet resultSet = getActorsStatement.executeQuery();
            while (resultSet.next()) {
                Actor a = new Actor(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), stringToDate(resultSet.getString(5)), resultSet.getString(6));
                actors.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return actors;
    }

    public void addActor(Actor a) {
        try {
            ResultSet rs = nextIdStatement.executeQuery();
            int id = 1;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            addActorStatement.setInt(1, id);
            addActorStatement.setString(2, a.getFirstName());
            addActorStatement.setString(3, a.getLastName());
            addActorStatement.setString(4, a.getBiography());
            String date = a.getBornDate().format(formatter);
            date = date.replace("/", ".");
            addActorStatement.setString(5, date);
            addActorStatement.setString(6, a.getImage());
            addActorStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addGenre(Genre g) {
        try {
            ResultSet rs = nextIdGenreStatement.executeQuery();
            int id = 1;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            addGenreStatement.setInt(1, id);
            addGenreStatement.setString(2, g.getName());
            addGenreStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addContentActor(Actor a, Content content) {
        try {
            getActorStatement.setString(1, a.getFirstName());
            getActorStatement.setString(2, a.getLastName());
            ResultSet resultSet = getActorStatement.executeQuery();
            if (resultSet.next()) {
                ResultSet rs = nextIdContentAcotrStatement.executeQuery();
                int id = 1;
                if (rs.next()) {
                    id = rs.getInt(1);
                }
                addContentActorStatement.setInt(1, id);
                addContentActorStatement.setInt(2, resultSet.getInt(1));
                addContentActorStatement.setInt(3, content.getId());
                addContentActorStatement.executeUpdate();
                content.getMainActors().add(new Actor(resultSet.getInt(1), a.getFirstName(), a.getLastName(), a.getBiography(), a.getBornDate(), a.getImage()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addContentGenre(Genre g, Content content) {
        try {
            getGenreStatement.setString(1, g.getName());
            ResultSet resultSet = getGenreStatement.executeQuery();
            if (resultSet.next()) {
                ResultSet rs = nextIdContentGenreStatement.executeQuery();
                int id = 1;
                if (rs.next()) {
                    id = rs.getInt(1);
                }
                addContentGenreStatement.setInt(1, id);
                addContentGenreStatement.setInt(2, content.getId());
                addContentGenreStatement.setInt(3, resultSet.getInt(1));
                addContentGenreStatement.executeUpdate();
                content.getGenre().add(new Genre(resultSet.getInt(1), g.getName()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteActorFromContent(Actor a, Content content) throws SQLException {
        int idActor = a.getId();
        if (idActor == 0) {
            getActorStatement.setString(1, a.getFirstName());
            getActorStatement.setString(2, a.getLastName());
            ResultSet resultSet = getActorStatement.executeQuery();
            if (resultSet.next()) idActor = resultSet.getInt(1);
        }
        getContentActor.setInt(1, content.getId());
        getContentActor.setInt(2, idActor);
        ResultSet resultSet = getContentActor.executeQuery();
        if (resultSet.next()) {
            deleteActorFromContent.setInt(1, resultSet.getInt(1));
            deleteActorFromContent.executeUpdate();
            int actorIndex = -1;
            for (int i = 0; i < content.getMainActors().size(); i++) {
                if (content.getMainActors().get(i).equals(a)) {
                    actorIndex = i;
                }
            }
            if (actorIndex != -1) {
                content.getMainActors().remove(actorIndex);
            }
        }
    }

    public void deleteGenreFromContent(Genre g, Content content) throws SQLException {
        int idGenre = g.getId();
        if (idGenre == 0) {
            getGenreStatement.setString(1, g.getName());
            ResultSet resultSet = getGenreStatement.executeQuery();
            if (resultSet.next()) idGenre = resultSet.getInt(1);
        }
        getContentGenre.setInt(1, content.getId());
        getContentGenre.setInt(2, idGenre);
        ResultSet resultSet = getContentGenre.executeQuery();
        if (resultSet.next()) {
            deleteGenreFromContent.setInt(1, resultSet.getInt(1));
            deleteGenreFromContent.executeUpdate();
            int genreIndex = -1;
            for (int i = 0; i < content.getGenre().size(); i++) {
                if (content.getGenre().get(i).getName().equals(g.getName())) {
                    genreIndex = i;
                }
            }
            if (genreIndex != -1) {
                content.getGenre().remove(genreIndex);
            }
        }
    }

    public ArrayList<Genre> getGenres() {
        ArrayList<Genre> genres = new ArrayList<>();
        try {
            ResultSet resultSet = getGenresStatement.executeQuery();
            while (resultSet.next()) {
                Genre g = new Genre(resultSet.getInt(1), resultSet.getString(2));
                genres.add(g);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return genres;
    }

    public ArrayList<Genre> getMovieGenres(Movie m) {
        ArrayList<Genre> genres = new ArrayList<>();
        try {
            getMovieGenresStatement.setInt(1, m.getId());
            ResultSet resultSet = getMovieGenresStatement.executeQuery();
            while (resultSet.next()) {
                Genre g = new Genre(resultSet.getInt(1), resultSet.getString(2));
                genres.add(g);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return genres;
    }

    public ArrayList<Genre> getSerialGenres(Serial s) {
        ArrayList<Genre> genres = new ArrayList<>();
        try {
            getSerialGenresStatement.setInt(1, s.getId());
            ResultSet resultSet = getSerialGenresStatement.executeQuery();
            while (resultSet.next()) {
                Genre g = new Genre(resultSet.getInt(1), resultSet.getString(2));
                genres.add(g);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return genres;
    }

    public void deleteGenre(Genre g) {
        try {
            getGenreContentsStatement.setInt(1, g.getId());
            ResultSet resultSet = getGenreContentsStatement.executeQuery();
            while (resultSet.next()) {
                deleteGenreFromContent.setInt(1, resultSet.getInt(1));
                deleteGenreFromContent.executeUpdate();
            }
            deleteGenreStatement.setInt(1, g.getId());
            deleteGenreStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addContent(Content c) {
        try {
            ResultSet rs = nextContentIdStatement.executeQuery();
            int id = 1;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            addContentStatement.setInt(1, id);
            addContentStatement.setString(2, c.getTitle());
            addContentStatement.setInt(3, c.getYear());
            addContentStatement.setString(4, c.getDirector());
            addContentStatement.setString(5, c.getDescription());
            addContentStatement.setDouble(6, c.getRating());
            addContentStatement.setString(7, c.getImage());
            addContentStatement.setDouble(8, c.getPrice());
            addContentStatement.executeUpdate();

            if (c instanceof Movie) {
                addMovieStatement.setInt(1, id);
                addMovieStatement.setInt(2, ((Movie) c).getDurationMinutes());
                addMovieStatement.executeUpdate();
            } else {
                addSerialStatement.setInt(1, id);
                addSerialStatement.setInt(2, ((Serial) c).getSeasonsNumber());
                addSerialStatement.setInt(3, ((Serial) c).getEpisodesPerSeasonNumber());
                addSerialStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addEmployee(Employee e) {
        try {
            ResultSet rs = nextIdEmployee.executeQuery();
            int id = 1;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            addEmployeeStatement.setInt(1, id);
            addEmployeeStatement.setString(2, e.getUsername());
            addEmployeeStatement.setString(3, e.getPassword());
            addEmployeeStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteEmployee(Employee e) {
        try {
            deleteEmployeeStatement.setInt(1, e.getId());
            deleteEmployeeStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateEmployee(Employee e) {
        try {
            updateEmployeeStatement.setString(1, e.getUsername());
            updateEmployeeStatement.setString(2, e.getPassword());
            updateEmployeeStatement.setInt(3, e.getId());
            updateEmployeeStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addHotel(int roomsNumber) {
        try {
            addHotelStatement.setInt(1, 1);
            addHotelStatement.setInt(2, roomsNumber);
            addHotelStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int getRoomsNumber() {
        try {
            ResultSet resultSet = getHotelsStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(2);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public void updateHotel(int roomsNumber) {
        try {
            updateHotelStatement.setInt(1, roomsNumber);
            updateHotelStatement.setInt(2, 1);
            updateHotelStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addUser(User user) {
        try {
            ResultSet rs = userNextID.executeQuery();
            int id = 1;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            addUserStatement.setInt(1, id);
            addUserStatement.setString(2, user.getFirstName());
            addUserStatement.setString(3, user.getLastName());
            addUserStatement.setString(4, user.getUsername());
            addUserStatement.setString(5, user.getPassword());
            addUserStatement.setInt(6, user.getRoomNumber());
            addUserStatement.setInt(7, user.getIntegerPrivilege());
            addUserStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteHotelGuest(int id) {
        try {
            deleteUserStatement.setInt(1, id);
            deleteUserStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int getGenreId(String name) {
        try {
            getGenreStatement.setString(1, name);
            ResultSet resultSet = getGenreStatement.executeQuery();
            if (resultSet.next()) return resultSet.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public int getActorId(String name, String surname) {
        try {
            getActorStatement.setString(1, name);
            getActorStatement.setString(2, surname);
            ResultSet resultSet = getActorStatement.executeQuery();
            if (resultSet.next()) return resultSet.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public void deleteContent(Content c) {
        try {
            deleteGenreContent.setInt(1, c.getId());
            deleteGenreContent.executeUpdate();
            deleteActorContent.setInt(1, c.getId());
            deleteActorContent.executeUpdate();
            if (c instanceof Movie) {
                deleteMovieStatement.setInt(1, c.getId());
                deleteMovieStatement.executeUpdate();
            } else {
                deleteSerialStatement.setInt(1, c.getId());
                deleteSerialStatement.executeUpdate();
            }
            deleteContentStatement.setInt(1, c.getId());
            deleteContentStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addGenresToContent(Content c, ArrayList<Genre> genres) {
        try {
            for (Genre g : genres) {
                getGenreStatement.setString(1, g.getName());
                ResultSet resultSet = getGenreStatement.executeQuery();
                if (resultSet.next()) {
                    ResultSet rs = nextIdContentGenreStatement.executeQuery();
                    int id = 1;
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                    getContetStatement.setString(1,c.getTitle());
                    ResultSet resultSet1 = getContetStatement.executeQuery();
                    addContentGenreStatement.setInt(1, id);
                    addContentGenreStatement.setInt(2, resultSet1.getInt(1));
                    addContentGenreStatement.setInt(3, resultSet.getInt(1));
                    addContentGenreStatement.executeUpdate();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void addActorsToContent(Content c, ArrayList<Actor> actors) {
        try {
            for (Actor actor : actors) {
                getActorStatement.setString(1, actor.getFirstName());
                getActorStatement.setString(2, actor.getLastName());
                ResultSet resultSet = getActorStatement.executeQuery();
                if (resultSet.next()) {
                    ResultSet rs = nextIdContentAcotrStatement.executeQuery();
                    int id = 1;
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                    getContetStatement.setString(1,c.getTitle());
                    ResultSet resultSet1 = getContetStatement.executeQuery();
                    addContentActorStatement.setInt(1, id);
                    addContentActorStatement.setInt(3, resultSet1.getInt(1));
                    addContentActorStatement.setInt(2, resultSet.getInt(1));
                    addContentActorStatement.executeUpdate();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void updateUser(User user) {
        try {
            updateUserStatement.setString(1, user.getFirstName());
            updateUserStatement.setString(2, user.getLastName());
            updateUserStatement.setString(3, user.getUsername());
            updateUserStatement.setString(4, user.getPassword());
            updateUserStatement.setInt(5, user.getRoomNumber());
            updateUserStatement.setInt(6,user.getId());
            updateUserStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}