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
    private ArrayList<User> users = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    @Override
    public Optional<User> get(long UserID) {
        return Optional.ofNullable(users.get((int) UserID));
    }

    // @Override
    public static ArrayList<User> getAllUsers() {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<User> users = new ArrayList<>();

        try {
            connection = Database.getDBConnection();
            connection.setAutoCommit(false);
            String query = "SELECT user_id, first_name, last_name, password, created_at, modified_at, password FROM users";
            statement = connection.prepareStatement(query);
            // int counter = 1;
            // statement.setString(counter++, first_name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6));
                users.add(user);
            }
            return users;
        } catch (SQLException exception) {

            // Severe: No Value specified for paramweter 1
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

    // @Override
    public static int saveUser(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getDBConnection();
            connection.setAutoCommit(false);
            String query = "INSERT INTO users(first_name, last_name, password, created_at, modified_at) VALUES(?, ?, MD5(?), NOW(), NOW())";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            int counter = 1;
            statement.setString(counter++, user.getFirstName());
            statement.setString(counter++, user.getLastName());
            statement.setString(counter++, user.getPassword());
            // statement.setString(counter++, user.getPassword());
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

    public static int modifyUser(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getDBConnection();
            connection.setAutoCommit(false);
            String query = "UPDATE users SET first_name = ?, last_name = ?, modified_at = NOW() WHERE user_id = ?";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            int counter = 1;
            statement.setString(counter++, user.getFirstName());
            statement.setString(counter++, user.getLastName());
            // statement.setString(counter++, user.getPassword());
            statement.setLong(counter++, user.getUserId());
            statement.executeUpdate();
            connection.commit();
            resultSet = statement.getGeneratedKeys();
            return 1;
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
        user.setFirstName(Objects.requireNonNull(params[0], "FirstName cannot be null"));
        user.setLastName(Objects.requireNonNull(params[1], "LastName cannot be null"));
        users.add(user);
    }

    public static int deleteUser(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = Database.getDBConnection();
            connection.setAutoCommit(false);
            String query = "DELETE FROM users WHERE user_id = ?";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            int counter = 1;
            statement.setInt(counter++, user.getUserId());
            statement.execute();
            connection.commit();

        } catch (SQLException exception) {
            logger.log(Level.SEVERE, exception.getMessage());
            if (null != connection) {
                connection.rollback();
            }
        } finally {

            if (null != statement) {
                statement.close();
            }

            if (null != connection) {
                connection.close();
            }
        }

        return 1;
    }

    @Override
    public int save(User t) {
        // TODO Auto-generated method stub
        // INFO i can not put the method in here because of static errors.
        return 0;
    }

    @Override
    public List<User> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(User t) {
        // TODO Auto-generated method stub

    }
}