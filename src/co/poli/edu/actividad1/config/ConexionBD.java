package co.poli.edu.actividad1.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:postgresql://aws-1-us-east-2.pooler.supabase.com:6543/postgres?sslmode=require";
    private static final String USER = "postgres.jcrgllisixmujsjvxxht";
    private static final String PASSWORD = "edwar1032797762";


    private static Connection connection;


    private ConexionBD() {}


    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}
