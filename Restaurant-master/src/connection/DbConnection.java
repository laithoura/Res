package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import control_classes.MessageShow;
import instance_classes.User;

public class DbConnection {
	public static Connection dbConnection = null;
	public static User user;
	
	/* Dynamic Connection */
	public static Boolean createConnection (String serverIP,String databaseName, String username, String password) {
		try {
			dbConnection = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s?useSSL=false",serverIP,databaseName),username,password);
			return true;			
		} catch (SQLException e) {
			e.printStackTrace();
			MessageShow.Error("Please Open server connection!", "Connection Failure");
		}
		return false;
	}
	
	/* For Static Connection */
	public static Connection getConnection() {
		try {
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/restaurant_project?useSSL=false","root","");	
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return dbConnection;
	}
}
