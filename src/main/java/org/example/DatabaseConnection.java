package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String HOST =
            System.getenv("DB_HOST") != null ? System.getenv("DB_HOST") : "localhost";
    private static final String PORT =
            System.getenv("DB_PORT") != null ? System.getenv("DB_PORT") : "3306";
    private static final String USER =
            System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "fuelapp"; // ← changed
    private static final String PASSWORD =
            System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "fuel123"; // ← no password

    private static final String URL =
            "jdbc:mariadb://" + HOST + ":" + PORT + "/fuel_calculator_localization";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}