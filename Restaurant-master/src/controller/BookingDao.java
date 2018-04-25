package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import connection.DbConnection;
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
			prepareStatement.setDate(3, new java.sql.Date(booking.getTime().getTimezoneOffset()));
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
			prepareStatement.setDate(3, new java.sql.Date(booking.getTime().getDate()) );						
			prepareStatement.setDate(4, new java.sql.Date(booking.getCheckInDate().getDate()));			
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
											(java.util.Date)resultSet.getDate("time"),
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
	
}
