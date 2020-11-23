package com.mthaler.moviemanager.jdbc;

import com.mthaler.moviemanager.cli.Options;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryActorsTable {

    public static void main(String[] args) throws Exception {
        Options options = Options.parseOptions(args);
        Connection connection = ConnectionHelper.connect(options);
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM ACTORS");
        while (rs.next()) {
            int id = rs.getInt("ID");
            String name = rs.getString("NAME");
            String gender = rs.getString("GENDER");
            System.out.println("id: " + id + " name: " + name + " gender: " + gender);
        }
        rs.close();
        st.close();
        connection.close();
    }
}
