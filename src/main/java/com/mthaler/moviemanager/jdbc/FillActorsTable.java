package com.mthaler.moviemanager.jdbc;

import com.mthaler.moviemanager.cli.Options;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class FillActorsTable {

    private static String insertSql = "INSERT INTO ACTORS VALUES (?,?,?)";

    public static void main(String[] args) throws Exception {
        Options options = Options.parseOptions(args);
        Connection connection = ConnectionHelper.connect(options);
        PreparedStatement pst = connection.prepareStatement(insertSql);
        pst.setInt(1, 1);
        pst.setString(2, "Tom Cruise");
        pst.setString(3, "male");
        pst.execute();
        pst.close();
        connection.close();
    }
}
