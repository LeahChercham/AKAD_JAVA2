import java.sql.Date;

/**
 * The class User creates Instances of the User object.
 * 
 * @version 1.0
 * @author Leah Chercham
 */

public class User {
    
    // Attribute
    private Integer UserID; // automatic
    private String LastName;
    private String FirstName;
    private String Password; // md5 sql method
    private Date CreationDate; // automatic
    private Date ModifiedDate; // automatic

    // Constructor
    public User(Integer UserID, String LastName, String FirstName, String Password, Date CreationDate, Date ModifiedDate){
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.UserID = UserID;
        this.Password = Password;
        this.CreationDate = CreationDate;
        this.ModifiedDate = ModifiedDate;
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

    public void setCreationDate(Date CreationDate) {
        this.CreationDate = CreationDate;
    }

    public void setModifiedDate(Date ModifiedDate) {
        this.ModifiedDate = ModifiedDate;
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

    public Date getCreationDate() {
        return CreationDate;
    }

    public Date getModifiedDate() {
        return ModifiedDate;
    }
}
