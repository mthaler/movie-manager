package com.mthaler.moviemanager.jdbc;

import com.mthaler.moviemanager.cli.Options;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class FillMoviesTable {

    private static String insertSql = "INSERT INTO MOVIES VALUES (?,?,?,?)";

    public static void main(String[] args) throws Exception {
        Options options = Options.parseOptions(args);
        Connection connection = ConnectionHelper.connect(options);
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
        connection.close();
    }
}
