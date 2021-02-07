import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * UserDAO Data Access Object for Users Hide from the app all the complexities
 * involved in performing CRUD operations in the database
 * 
 * @author Leah Chercham
 *
 */

public class UserDAO implements DAO<User> {
    private List<User> users = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    public UserDAO() {
        users.add(new User(null, "John", "Doe", null, 0, 0));
        users.add(new User(null, "Susan", "Smith", null, 0, 0));
    }

    @Override
    public Optional<User> get(long UserID) {
        return Optional.ofNullable(users.get((int) UserID));
    }

    @Override
    public List<User> getAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        List<User> users = new ArrayList<>();

        try {
            connection = Database.getDBConnection();
            connection.setAutoCommit(false);
            String query = "SELECT user_id, first_name, last_name, created_at, modified_at, password FROM user WHERE username = ?";
            statement = connection.prepareStatement(query);
            // int counter = 1;
            // statement.setString(counter++, first_name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getLong(5), resultSet.getLong(6));
                users.add(user);
            }
            return users;
        } catch (SQLException exception) {
            logger.log(Level.SEVERE, exception.getMessage());
        } finally {
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException exception2) {
                    logger.log(Level.SEVERE, exception2.getMessage());
                }
            }

            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException exception3) {
                    logger.log(Level.SEVERE, exception3.getMessage());
                }
            }
        }
        return users;
    }

    @Override
    public static int save(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getDBConnection();
            connection.setAutoCommit(false);
            String query = "INSERT INTO users(user_id, first_name, last_name, password, created_at, modified_at) VALUES(?, ?, ?, ?)";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int counter = 1;
            statement.setLong(counter++, user.getuserId());
            statement.setString(counter++, user.getfirstName());
            statement.setString(counter++, user.getlastName());
            statement.setString(counter++, user.getpassword());
            statement.setLong(counter++, user.getcreatedTimeStamp());
            statement.setLong(counter++, user.getmodifiedTimeStamp());
            statement.executeUpdate();
            connection.commit();
            resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException exception) {
            logger.log(Level.SEVERE, exception.getMessage());
            if (null != connection) {
                connection.rollback();
            }
        } finally {
            if (null != resultSet) {
                resultSet.close();
            }

            if (null != statement) {
                statement.close();
            }

            if (null != connection) {
                connection.close();
            }
        }

        return 0;
    }

    @Override
    public void update(User user, String[] params) {
        user.setfirstName(Objects.requireNonNull(params[0], "FirstName cannot be null"));
        user.setlastName(Objects.requireNonNull(params[1], "LastName cannot be null"));
        users.add(user);
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }

    // TO DO
    public static boolean userExists(String firstName, String lastName) {
        return true;
    }

}
