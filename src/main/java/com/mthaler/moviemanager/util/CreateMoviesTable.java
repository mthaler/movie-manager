package com.mthaler.moviemanager.util;

import com.mthaler.moviemanager.cli.Options;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateMoviesTable {

    public static void main(String[] args) throws Exception {
        Options options = Options.parseOptions(args);
        Class.forName("org.postgresql.Driver").newInstance();
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/moviemanager", options.getUsername(), options.getPassword());
        Statement st = connection.createStatement();
        st.execute("CREATE TABLE  MOVIES(ID integer PRIMARY KEY, TITLE TEXT NOT NULL, DIRECTOR TEXT NOT NULL, SYNOPSIS TEXT NOT NULL);");
        st.close();
    }
}
