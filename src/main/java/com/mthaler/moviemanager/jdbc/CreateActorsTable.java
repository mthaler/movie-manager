package com.mthaler.moviemanager.jdbc;

import com.mthaler.moviemanager.cli.Options;
import java.sql.Connection;
import java.sql.Statement;

public class CreateActorsTable {

    public static void main(String[] args) throws Exception {
        Options options = Options.parseOptions(args);
        Connection connection = ConnectionHelper.connect(options);
        Statement st = connection.createStatement();
        st.execute("CREATE TABLE  ACTORS(ID integer PRIMARY KEY, NAME TEXT NOT NULL, GENDER TEXT NOT NULL);");
        st.close();
        connection.close();
    }
}
