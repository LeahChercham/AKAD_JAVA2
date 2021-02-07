import java.util.*;

/**
 * UserDAO
 * Data Access Object for Users
 * Hide from the app all the complexities involved in performing
 * CRUD operations in the database
 * 
 * @author Leah Chercham
 *
 */

public class UserDAO implements DAO<User> {
    private List<User> users = new ArrayList<>();

    public UserDAO() {
        users.add(new User(null, "John", "Doe", null, null, null));
        users.add(new User(null, "Susan", "Smith", null, null, null));
    }

    @Override
    public Optional<User> get(long UserID) {
        return Optional.ofNullable(users.get((int) UserID));
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void save(User user) {
        users.add(user);
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

}
