package com.mthaler.moviemanager.util;

import com.mthaler.moviemanager.cli.Options;
import java.sql.*;

public class QueryMoviesTable {

    public static void main(String[] args) throws Exception {
        Options options = Options.parseOptions(args);
        Class.forName("org.postgresql.Driver").newInstance();
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/moviemanager", options.getUsername(), options.getPassword());
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM MOVIES");
        while (rs.next()) {
            int id = rs.getInt("ID");
            String title = rs.getString("TITLE");
            String director = rs.getString("DIRECTOR");
            String synopsis = rs.getString("SYNOPSIS");
            System.out.println("id: " + id + " title: " + title + " director: " + director + " synopsis: " + synopsis);
        }
        rs.close();
        st.close();
        connection.close();
    }
}
