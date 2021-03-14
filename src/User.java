import java.sql.Date;

/**
 * The class User creates Instances of the User object.
 * 
 * @version 1.0
 * @author Leah Chercham
 */

public class User {

    // Attribute
    private Integer userId; // automatic
    private String lastName;
    private String firstName;
    private String password; // md5 sql method
    private Date createdTimeStamp; // automatic
    private Date modifiedTimeStamp; // automatic

    // Constructor
    public User(Integer userId, String lastName, String firstName, String password, Date creationTimeStamp,
            Date modifiedTimeStamp2) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.password = password;
        this.createdTimeStamp = creationTimeStamp;
        this.modifiedTimeStamp = modifiedTimeStamp2;
    }

    // Setter & Getter
    // Setter
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreatedTimeStamp(Date createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public void setModifiedTimeStamp(Date modifiedTimeStamp) {
        this.modifiedTimeStamp = modifiedTimeStamp;
    }

    // Getter
    public Integer getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public Date getModifiedTimeStamp() {
        return modifiedTimeStamp;
    }
}
