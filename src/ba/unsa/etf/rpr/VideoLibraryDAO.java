package ba.unsa.etf.rpr;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class VideoLibraryDAO {
    private static VideoLibraryDAO instance;
    private Connection connection;

    private PreparedStatement getUsersStatement, getUserStatement, getUserByNameStatement, getUserByIdStatement;
    private PreparedStatement getEmployeeStatament, getEmployeeByIdStatement, getEmplyeesStamement;
    private PreparedStatement getActorStatement, getActorByIdStatement, getActorsStatement;
    private PreparedStatement getGenreStatement, getGenreByIdStatement, getGenresStatement;
    private PreparedStatement getMoviesStatement;
    private PreparedStatement getSeriesStatement;
    private PreparedStatement getActorsInMovieStatement, getActorsInSerialStatement;
    public static VideoLibraryDAO getInstance() {
        if(instance == null) instance = new VideoLibraryDAO();
        return instance;
    }
    private void database() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("database.db.sql"));
            String sqlUpit = "";
            while (ulaz.hasNext()) {
                sqlUpit += ulaz.nextLine();
                if ( sqlUpit.charAt( sqlUpit.length()-1 ) == ';') {
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
            getActorsInSerialStatement = connection.prepareStatement("SELECT a.id, a.first_name, a.last_name, a.biography, a.born_date, a.image FROM content_actor ca, serial s, content c, actor a WHERE s.id =? AND s.id=c.id AND ca.id=a.id AND c.id=s.id");
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
    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> list = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = getEmplyeesStamement.executeQuery();
            while (resultSet.next()) {
                list.add(new Employee(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }
    public Employee getEmployee(String username) {
        try {
            getEmployeeStatament.setString(1,username);
            ResultSet resultSet = getEmployeeStatament.executeQuery();
            if(resultSet.next()) return new Employee(resultSet.getInt(1),username,resultSet.getString(2));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
