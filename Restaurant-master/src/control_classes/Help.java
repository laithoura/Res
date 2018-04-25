package control_classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.PreparedStatement;

import connection.DbConnection;

public class Help {
	public static int getLastAutoIncrement(String dbName,String tableName) {
		ResultSet resultSet = null;
		int autoID = 0;
		PreparedStatement preparedStatement = null;
		
		try {			
			preparedStatement = (PreparedStatement) DbConnection.getConnection().prepareStatement("SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?;");			
			preparedStatement.setString(1,dbName );		
			preparedStatement.setString(2, tableName);					
			
			resultSet = preparedStatement.executeQuery();	
			
			resultSet.next();
			autoID = resultSet.getInt(1) - 1;			
		} catch (SQLException e) {	
			e.printStackTrace();
		}finally {		
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return autoID;
	}
}
