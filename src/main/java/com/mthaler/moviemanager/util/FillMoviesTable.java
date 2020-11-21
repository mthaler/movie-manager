package com.mthaler.moviemanager.util;

import com.mthaler.moviemanager.cli.Options;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class FillMoviesTable {

    private static String insertSql = "INSERT INTO MOVIES VALUES (?,?,?,?)";

    public static void main(String[] args) throws Exception {
        Options options = Options.parseOptions(args);
        Class.forName("org.postgresql.Driver").newInstance();
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/moviemanager", options.getUsername(), options.getPassword());
         PreparedStatement pst = connection.prepareStatement(insertSql);
        pst.setInt(1, 1);
        pst.setString(2, "Top Gun");
        pst.setString(3, "Tony Scott");
        pst.setString(4, "Maverick is a hot pilot. When he encounters "
                + "a pair of MiGs over the Persian Gulf,"
                + " his wingman is clearly outflown and freaks. "
                + "On almost no fuel, Maverick is able to talk "
                + "him back down to the Carrier..");
        pst.execute();
        pst.close();
    }
}
