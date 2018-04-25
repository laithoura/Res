package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.PreparedStatement;
import connection.DbConnection;
import instance_classes.Booking;
import instance_classes.Table;

public class TableDao {	
	private PreparedStatement prepareStatement;
	private ResultSet resultSet;
	
	public TableDao() {}
	
	public boolean insertTable(Table table) {
		boolean success = false;
		try {
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("INSERT INTO tables(name, type, available, status) VALUES(?,?,?,?)");
			prepareStatement.setString(1, table.getName());
			prepareStatement.setString(2, table.getType());
			prepareStatement.setBoolean(3, true);			
			prepareStatement.setBoolean(4, true);
			if(prepareStatement.executeUpdate() > 0) {
				success = true;
			}
		} catch (SQLException e) {		
			e.printStackTrace();
			success = false;
		}finally {
			try {
				prepareStatement.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
		return success;
	}
	
	public boolean updateBookingTable(int tableID, boolean available) {
		boolean success = false;
		try {
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("UPDATE tables SET available = ? WHERE id = ?");			
							
			prepareStatement.setBoolean(1, available);
			prepareStatement.setInt(2, tableID);
			if(prepareStatement.executeUpdate() > 0) {
				success = true;
			}
		} catch (SQLException e) {		
			e.printStackTrace();
			success = false;
		}finally {
			try {
				prepareStatement.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}	
		return success;
	}
	
	public boolean updateTable(Table table) {
		boolean success = false;
		try {
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("UPDATE tables SET name=?, type=? WHERE id = ?");			
			prepareStatement.setString(1, table.getName());
			prepareStatement.setString(2, table.getType());					
			prepareStatement.setInt(3, table.getId());
			if(prepareStatement.executeUpdate() > 0) {
				success = true;
			}
		} catch (SQLException e) {		
			e.printStackTrace();
			success = false;
		}finally {
			try {
				prepareStatement.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}	
		return success;
	}
	
	public boolean deleteTable(int tableID) {
		boolean success = false;
		try {			
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("UPDATE tables SET status = ? WHERE id = ?");			
			prepareStatement.setBoolean(1, false);
			prepareStatement.setInt(2, tableID);		
			if(prepareStatement.executeUpdate() > 0) {
				success = true;
			}
		} catch (SQLException e) {		
			e.printStackTrace();
			success = false;
		}finally {
			try {
				prepareStatement.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
		return success;
	}
	
	public ArrayList<Table> getBookingListOnly(int bookingID){
		ArrayList<Table> tableList = new ArrayList<>();
		try {			
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT TS.id, TS.name, TS.type, TS.available, TS.status FROM booking_detail AS BD INNER JOIN tables AS TS ON BD.table_id = TS.id WHERE booking_id = ?");
			prepareStatement.setInt(1, bookingID);	
			resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next()) {
				
				tableList.add(new Table(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getBoolean(4),resultSet.getBoolean(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				prepareStatement.close();
				resultSet.close();
			} catch (Exception e) {		
				e.printStackTrace();
			}
		}
		return tableList;
	}
	
	public ArrayList<Table> getTableLists(boolean available,boolean status){
		ArrayList<Table> tableList = new ArrayList<>();
		try {			
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT * FROM tables WHERE status = ? and available = ?");						
			prepareStatement.setBoolean(1, status);
			prepareStatement.setBoolean(2, available);
			resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next()) {			
				tableList.add(new Table(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getBoolean(4),resultSet.getBoolean(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				prepareStatement.close();
				resultSet.close();
			} catch (Exception e) {		
				e.printStackTrace();
			}
		}
		return tableList;
	}
	
	public ArrayList<Table> getAllTableLists(boolean status){
		ArrayList<Table> tableList = new ArrayList<>();
		try {			
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT * FROM tables WHERE status = ?");						
			prepareStatement.setBoolean(1, status);			
			resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next()) {
				tableList.add(new Table(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getBoolean(4),resultSet.getBoolean(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				prepareStatement.close();
				resultSet.close();
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
		return tableList;
	}
	
	public boolean checkExistTableName(String name) {				
		boolean exist = false;
		try {
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT name FROM tables WHERE name = ?");
			prepareStatement.setString(1, name);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				if(name.equals(resultSet.getString(1))) {
					exist = true;
				}
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally {
			try {
				prepareStatement.close();
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return exist;
	}
	
	public ArrayList<Table> searchTableLists(String condition,boolean status){
		ArrayList<Table> tableList = new ArrayList<>();
		try {			
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT * FROM tables WHERE name = ? OR type = ? AND status = ?");						
			prepareStatement.setString(1, condition);			
			prepareStatement.setString(2, condition);			
			prepareStatement.setBoolean(3, status);		
			resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next()) {
				tableList.add(new Table(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getBoolean(4),resultSet.getBoolean(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				prepareStatement.close();
				resultSet.close();
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
		return tableList;
	}
	
}
