import java.sql.*;
import java.util.logging.*;

/**
 * Database
 * 
 * @author Leah Chercham
 *
 */
public class Database {
    private static final Logger logger = Logger.getLogger(Database.class.getName());
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/java2akad";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "h$q05$NMFKHUeK53";

    // Ist im Beispiel aber brauche ich das?
    // private Database() {
        // Kann ich hier die funktionen get all aus App?

    // }

    /*
     * This Method tries then connects to the Database
     */
    public static Connection getDBConnection() throws SQLException {
        Connection connection = null;

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException exception) {
            logger.log(Level.SEVERE, exception.getMessage());
        }

        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return connection;
        } catch (SQLException exception) {
            logger.log(Level.SEVERE, exception.getMessage());
        }

        return connection;

    }

}
