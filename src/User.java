import java.sql.Timestamp;

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
    private Timestamp createdTimeStamp; // automatic
    private Timestamp modifiedTimeStamp; // automatic

    // Constructor
    public User(Integer userId, String lastName, String firstName, String password, Timestamp createdTimeStamp, Timestamp modifiedTimeStamp){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.password = password;
        this.createdTimeStamp = createdTimeStamp;
        this.modifiedTimeStamp = modifiedTimeStamp;
    }

    // Setter & Getter
    // Setter
    public void setuserId(Integer userId) {
        this.userId = userId;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public void setcreatedTimeStamp(Timestamp createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public void setmodifiedTimeStamp(Timestamp modifiedTimeStamp) {
        this.modifiedTimeStamp = modifiedTimeStamp;
    }

    // Getter
    public Integer getuserId() {
        return userId;
    }

    public String getfirstName() {
        return firstName;
    }

    public String getlastName() {
        return lastName;
    }

    public String getpassword() {
        return password;
    }

    public Timestamp getcreatedTimeStamp() {
        return createdTimeStamp;
    }

    public Timestamp getmodifiedTimeStamp() {
        return modifiedTimeStamp;
    }
}
