package com.mthaler.moviemanager.jdbc;

import com.mthaler.moviemanager.cli.Options;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

    public static Connection connect(Options options) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("org.postgresql.Driver").newInstance();
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/moviemanager", options.getUsername(), options.getPassword());
        return connection;
    }
}
