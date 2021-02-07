
// Min 13:30 https://www.youtube.com/watch?v=YczqOe6btEA
import java.sql.*;

public class JDBCExample {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/akad_db", "root",
                    "h$q05$NMFKHUeK53");
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select LastName from users");
            while (result.next()) {
                System.out.println(result.getString(1));
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
/* 
    private static void addToDB(String[] args) {
    
    } */
}