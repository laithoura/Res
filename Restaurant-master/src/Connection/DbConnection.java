package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	public static Connection dbConnection = null;
	
	/* Dynamic Connection */
	public static Boolean createConnection (String serverIP,String databaseName, String username, String password) {
		try {
			dbConnection = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s",serverIP,databaseName),username,password);
			return true;			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/* For Static Connection */
	public static Connection getConnection() {
		try {
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/restaurant_project","root","");	
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return dbConnection;
	}
}
