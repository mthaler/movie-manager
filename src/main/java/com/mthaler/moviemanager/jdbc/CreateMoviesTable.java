package com.mthaler.moviemanager.jdbc;

import com.mthaler.moviemanager.cli.Options;
import java.sql.Connection;
import java.sql.Statement;

public class CreateMoviesTable {

    public static void main(String[] args) throws Exception {
        Options options = Options.parseOptions(args);
        Connection connection = ConnectionHelper.connect(options);
        Statement st = connection.createStatement();
        st.execute("CREATE TABLE  MOVIES(ID integer PRIMARY KEY, TITLE TEXT NOT NULL, DIRECTOR TEXT NOT NULL, SYNOPSIS TEXT NOT NULL);");
        st.close();
        connection.close();
    }
}
