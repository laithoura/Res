package controller;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.PreparedStatement;
import connection.DbConnection;
import control_classes.DateFormat;
import instance_classes.Booking;
import instance_classes.Table;

public class BookingDao {	
	private PreparedStatement prepareStatement;
	private ResultSet resultSet;
	
	public BookingDao() {}
	
	public boolean insertBooking(Booking booking) {
		boolean success = false;
		try {
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("INSERT INTO Booking(cus_name, cus_phone, time, booking_date, checkin_date, total_table, status) VALUES(?,?,?,?,?,?,?)");
			prepareStatement.setString(1, booking.getCustomerName());
			prepareStatement.setString(2, booking.getCustomerPhone());
			prepareStatement.setString(3, booking.getTime());
			prepareStatement.setDate(4, new java.sql.Date(booking.getBookingDate().getTime()));
			prepareStatement.setDate(5, new java.sql.Date(booking.getCheckInDate().getTime()));
			prepareStatement.setInt(6, booking.getTotalTable());
			prepareStatement.setBoolean(7, true);			
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
	
	public boolean updateBooking(Booking booking) {
		boolean success = false;
		try {			
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("UPDATE Booking SET cus_name=?, cus_phone=?, time=?, checkin_date=?, total_table=? WHERE id = ?");			
			prepareStatement.setString(1, booking.getCustomerName());
			prepareStatement.setString(2, booking.getCustomerPhone());
			prepareStatement.setString(3, booking.getTime());						
			prepareStatement.setDate(4, new java.sql.Date(booking.getCheckInDate().getTime()));			
			prepareStatement.setInt(5, booking.getTotalTable());
			prepareStatement.setInt(6, booking.getId());
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
	
	public boolean deleteBooking(int bookingId) {
		boolean success = false;
		try {
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("UPDATE Booking SET status = ? WHERE id = ?");			
			prepareStatement.setBoolean(1, false);
			prepareStatement.setInt(2, bookingId);			
			if(prepareStatement.executeUpdate() > 0) {
				if(updateBookingTablesToAvailable(bookingId)) {
					success = true;
				}
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
	
	private boolean updateBookingTablesToAvailable(int bookingId) {
		boolean success = false;
		try {
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("UPDATE tables AS T JOIN Booking_Detail AS BD ON T.id = BD.table_id SET T.available = ? WHERE booking_id = ? ");			
			prepareStatement.setBoolean(1, true);
			prepareStatement.setInt(2, bookingId);
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
	
	public ArrayList<Booking> getBookingLists(boolean status){
		ArrayList<Booking> bookingList = new ArrayList<>();
		try {			
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT * FROM Booking WHERE status = ?");						
			prepareStatement.setBoolean(1, status);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				bookingList.add(new Booking(resultSet.getInt("id"),
											resultSet.getString("cus_name"),
											resultSet.getString("cus_phone"),
											(java.util.Date)resultSet.getDate("booking_date"),
											(java.util.Date)resultSet.getDate("checkin_date"),
											resultSet.getString("time"),
											resultSet.getInt("total_table")							
						));
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
		return bookingList;
	}
	
	public boolean deleteAllTablesInBookingDetails(int bookingId) {
		boolean success = false;
		PreparedStatement preparedStatement = null;
		try {			
			preparedStatement = (PreparedStatement) DbConnection.getConnection().prepareStatement("DELETE FROM Booking_Detail WHERE booking_id = ?");
			preparedStatement.setInt(1, bookingId);
			if(preparedStatement.executeUpdate() > 0) {
				success = true;
			}					
		} catch (SQLException ex) {
			success = false;
			ex.printStackTrace();
		}finally {
			try {
				preparedStatement.close();
			} catch (SQLException ex) {					
				ex.printStackTrace();
			}
		}/*End Try-Catch*/
		return success;
	}

	public boolean reInsertIntoBookingDetails(int bookingId,ArrayList<Table> tableList) {
		
		boolean success = false;
		TableDao tableDao = new TableDao();
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = (PreparedStatement) DbConnection.getConnection().prepareStatement("INSERT INTO Booking_Detail(table_id,booking_id,status) VALUES(?,?,?)");
			for(Table table : tableList) {
				if(!table.isAvailable()) {
					
					preparedStatement.setInt(1, table.getId());
					preparedStatement.setInt(2, bookingId);
					preparedStatement.setBoolean(3, true);
					
					if(preparedStatement.executeUpdate() > 0) {
						/*Update table to Unavailable*/
						tableDao.updateBookingTable(table.getId(),false);									
					}
				}else {
					/*Re-Update table to Available*/
					tableDao.updateBookingTable(table.getId(),true);
				}
			}
			success = true;
		} catch (SQLException e) {
			success = false;
			e.printStackTrace();			
		}finally {
			try {
				preparedStatement.close();
			} catch (SQLException ex) {						
				ex.printStackTrace();
			}
		}/*End Try-Catch*/		
		return success;
	}
	
	public ArrayList<Booking> searchBookingList(Object condition, int searchType){
		ArrayList<Booking> bookingList = new ArrayList<>();
		try {
			
			if(searchType == 0) { /*Booking ID*/
				
				prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT id, cus_name, cus_phone, booking_date, checkin_date, time, total_table FROM Booking WHERE id = ? AND status = ?");				
				prepareStatement.setInt(1, (int) condition);
				prepareStatement.setBoolean(2, true); /*Available Booking Status*/
				
			}else if(searchType == 1) { /*Cus Name*/
				
				prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT id, cus_name, cus_phone, booking_date, checkin_date, time, total_table FROM Booking WHERE cus_name LIKE ? AND status = ?");				
				prepareStatement.setString(1, "%" +((String) condition) + "%" );
				prepareStatement.setBoolean(2, true); /*Available Booking Status*/
				
			}else if(searchType == 2) { /*Cus Phone*/
				
				prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT id, cus_name, cus_phone, booking_date, checkin_date, time, total_table FROM Booking WHERE cus_phone LIKE ? AND status = ?");				
				prepareStatement.setString(1, "%" + (String) condition + "%");
				prepareStatement.setBoolean(2, true); /*Available Booking Status*/
				
			}else if(searchType == 3) { /*Booking Date*/
				prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT id, cus_name, cus_phone, booking_date, checkin_date, time, total_table FROM Booking WHERE booking_date = ? AND status = ?");
				
				prepareStatement.setDate(1, new java.sql.Date(((Date)condition).getTime()));
				prepareStatement.setBoolean(2, true); /*Available Booking Status*/
			}else if(searchType == 4) { /*Check-in Date*/
				
				prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT id, cus_name, cus_phone, booking_date, checkin_date, time, total_table FROM Booking WHERE checkin_date = ? AND status = ?");
				
				prepareStatement.setDate(1, new java.sql.Date(((Date)condition).getTime()));
				prepareStatement.setBoolean(2, true); /*Available Booking Status*/

			}else if(searchType == 5) { /*Time*/
				
				prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT id, cus_name, cus_phone, booking_date, checkin_date, time, total_table FROM Booking WHERE time = ? AND status = ?");
								
				prepareStatement.setString(1, (String) condition);
				prepareStatement.setBoolean(2, true); /*Available Booking Status*/
				
			}else if(searchType == 6) { /*Table Name*/
				prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT B.id, B.cus_name, B.cus_phone, B.booking_date, B.checkin_date, B.time, B.total_table"
						+ "	FROM (Booking AS B INNER JOIN Booking_Detail AS BD ON B.id = BD.booking_id) INNER JOIN Tables AS T ON BD.table_id = T.id"
						+ " WHERE T.name = ? AND B.status = ?");
				
				prepareStatement.setString(1, (String) condition);
				prepareStatement.setBoolean(2, true); /*Available Booking Status*/
			}
			
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {			
				bookingList.add(new Booking(resultSet.getInt(1),
											resultSet.getString(2),
											resultSet.getString(3),
											(java.util.Date)resultSet.getDate(4),
											(java.util.Date)resultSet.getDate(5),
											resultSet.getString(6),
											resultSet.getInt(7)						
						));
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
		return bookingList;
	}
	
}
