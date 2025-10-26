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


    private ConexionBD() {}


    public static Connection getConnection() throws SQLException {
        System.out.println(USER);
        System.out.println(PASSWORD );
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }
}
