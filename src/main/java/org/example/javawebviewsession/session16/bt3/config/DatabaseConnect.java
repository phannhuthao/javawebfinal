package org.example.javawebviewsession.session16.bt3.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/cartrip";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối CSDL:");
            e.printStackTrace();
            return null;
        }
    }
}
