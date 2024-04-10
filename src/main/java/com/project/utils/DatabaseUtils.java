package com.project.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/airplane_ticket", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
