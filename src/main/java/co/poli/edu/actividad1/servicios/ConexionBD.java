package co.poli.edu.actividad1.servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {
    private static final String URL = "jdbc:postgresql://aws-1-us-east-2.pooler.supabase.com:6543/postgres?sslmode=require";
    //private static final String USER = "postgres.jcrgllisixmujsjvxxht";
    //private static final String PASSWORD = "edwar1032797762";
    private static final Properties props = new Properties();
    static {
        props.setProperty("DB_USER", System.getenv("DB_USER"));
        props.setProperty("DB_PASSWORD", System.getenv("DB_PASSWORD"));
    }
    private static final String USER = props.getProperty("DB_USER");
    private static final String PASSWORD = props.getProperty("DB_PASSWORD");
    private static Connection connection;
    private static ConexionBD instance;

    private ConexionBD() {
        try {
            Class.forName("org.postgresql.Driver"); // carga driver una sola vez
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se encontró el driver PostgreSQL", e);
        }
    }
    public static synchronized ConexionBD getInstance() {
        if (instance == null) {
            instance = new ConexionBD();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER,PASSWORD);
        }
        return connection;
    }
}
