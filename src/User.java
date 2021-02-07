/**
 * The class User creates Instances of the User object.
 * 
 * @version 1.0
 * @author Leah Chercham
 */

public class User {
    
    // Attribute
    private Integer UserID;
    private String LastName;
    private String FirstName;
    private String Password;

    // Constructor
    public User(Integer UserID, String LastName, String FirstName, String Password){
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.UserID = UserID;
        this.Password = Password;
    }

    // Setter & Getter
    // Setter
    public void setUserID(Integer UserID) {
        this.UserID = UserID;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    // Getter
    public Integer getUserID() {
        return UserID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getPassword() {
        return Password;
    }
}
