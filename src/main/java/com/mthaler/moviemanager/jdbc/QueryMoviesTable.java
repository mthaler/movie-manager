package com.mthaler.moviemanager.jdbc;

import com.mthaler.moviemanager.cli.Options;
import java.sql.*;

public class QueryMoviesTable {

    public static void main(String[] args) throws Exception {
        Options options = Options.parseOptions(args);
        Connection connection = ConnectionHelper.connect(options);
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
