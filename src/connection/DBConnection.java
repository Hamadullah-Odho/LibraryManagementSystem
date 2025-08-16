package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

final public class DBConnection {

    private DBConnection() {}

    public static Connection getConnection() {
        Connection conn = null;
        try{
            String url = "jdbc:mysql://localhost:3306/library_db";
            String user = "root";
            String password = "0324";

            conn = DriverManager.getConnection(url,user,password);

        }
        catch(SQLException e){
            System.out.println("Connection Failed!" + e.getMessage());
        }
        return conn;
    }
}


