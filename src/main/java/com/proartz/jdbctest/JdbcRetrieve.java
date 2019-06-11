package com.proartz.jdbctest;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcRetrieve {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
        String user = "testuser";
        String password = "T3st623@";

        String query = "SELECT * FROM Authors";

        try (Connection con = DriverManager.getConnection(url, user, password);
                PreparedStatement pst = con.prepareStatement(query);
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                System.out.print(rs.getInt(1));
                System.out.print(": ");
                System.out.println(rs.getString(2));
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(JdbcRetrieve.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

    }

}
