package com.mthaler.moviemanager.jdbc;

import com.mthaler.moviemanager.cli.Options;
import com.mthaler.moviemanager.model.Movie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieManager {

    private final Options options;
    private Connection connection = null;
    private String url = "jdbc:mysql://localhost:3307/JH";
    private String driverClass = "com.mysql.jdbc.Driver";
    private String tableSql = "create table MOVIES (ID integer not null, TITLE varchar(255), DIRECTOR varchar(255), SYNOPSIS varchar(255), primary key (ID))";
    private String insertSql = "INSERT INTO MOVIES VALUES (?,?,?,?)";

    public MovieManager(String[] args) {
        options = Options.parseOptions(args);
    }

    private void init() {
        // Create a connection
        createConnection();
    }

    private void createConnection() {
        try {
            Class.forName(driverClass).newInstance();
            connection = DriverManager.getConnection(url, options.getUsername(), options.getPassword());
        } catch (Exception ex) {
            System.err.println("Exception while creating a connection:"
                    + ex.getMessage());
        }
        System.out.println("Connection created successfully");
    }

    private Connection getConnection() {

        if (connection == null) {
            createConnection();
        }

        return connection;
    }

    private void persistMovie() {
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertSql);

            pst.setInt(1, 1);
            pst.setString(2, "Top Gun");
            pst.setString(3, "Tony Scott");
            pst.setString(4, "Maverick is a hot pilot. When he encounters "
                    + "a pair of MiGs over the Persian Gulf,"
                    + " his wingman is clearly outflown and freaks. "
                    + "On almost no fuel, Maverick is able to talk "
                    + "him back down to the Carrier..");

            pst.execute();
            System.out.println("Movie persisted successfully!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void queryMovie() {
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertSql);

            pst.setInt(1, 1);
            pst.setString(2, "Top Gun");
            pst.setString(3, "Tony Scott");
            pst.setString(4, "Maverick is a hot pilot. When he encounters "
                    + "a pair of MiGs over the Persian Gulf,"
                    + " his wingman is clearly outflown and freaks. "
                    + "On almost no fuel, Maverick is able to talk "
                    + "him back down to the Carrier..");

            pst.execute();
            System.out.println("Movie persisted successfully!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void queryMovies() {
        List<Movie> movies = new ArrayList<Movie>();
        Movie m = null;
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM MOVIES");
            while (rs.next()) {
                m = new Movie();
                m.setId(rs.getInt("ID"));
                m.setTitle(rs.getString("TITLE"));
                movies.add(m);
                System.out
                        .println("Movie Found: " + rs.getInt("ID") + ", Title:" + rs.getString("TITLE"));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public static void main(String[] args) {
        MovieManager movieManager = new MovieManager(args);
        movieManager.init();
        movieManager.persistMovie();
        movieManager.queryMovies();

    }
}
