package Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Klasa koja sluzi za konektovanje na bazu podataka
 */
public class DBConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/online_casino";
    private static final String username = "root";
    private static final String password = "";

    private static Connection conn;

    public static Connection openConnection() {
        try {
            conn = DriverManager.getConnection(URL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
