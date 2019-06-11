package com.proartz.jdbctest;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcAutoGenKey {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
        String user = "testuser";
        String password = "T3st623@";

        String author = "Oscar Wilde";
        String sql = "INSERT INTO Authors(Name) VALUES(?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, author);
            pst.executeUpdate();

            try (ResultSet rs = pst.getGeneratedKeys()) {

                if (rs.first()) {

                    System.out.printf("The ID of new author: %d%n", rs.getLong(1));
                }
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(JdbcAutoGenKey.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
