package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DbConnection;
import instance_classes.ImportDrink;

public class ImportDrinkDao {
	private PreparedStatement prepareStatement;
	private ResultSet resultSet;
	private Connection con;
	
	public ImportDrinkDao() {
	}
	
	public boolean insertImportDrink(ImportDrink importDrink) {
		boolean success = false;
		try {
			con = DbConnection.getConnection();
			prepareStatement = con.prepareStatement("INSERT INTO import_drink (date, invoice_no, user_id, total, status) VALUES(?,?,?,?,true)"); 
			prepareStatement.setDate(1, new java.sql.Date(importDrink.getImportDrinkDate().getDate()));
			prepareStatement.setString(2, importDrink.getInvoiceNumber());
			prepareStatement.setInt(3, importDrink.getUser().getId());
			prepareStatement.setDouble(4, importDrink.getTotal());
			
			if (prepareStatement.executeUpdate() > 0) {
				success = true;
			}
			
		}catch (SQLException ex) {
			ex.printStackTrace();
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
	
	public boolean updateImportRawMaterail(ImportDrink importDrink) {
		boolean success = false;
		try {			
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("UPDATE import_drink SET date=?, invoice_no=?, user_id=?, total=?, status=? WHERE id = ?");			
			prepareStatement.setDate(1, new java.sql.Date(importDrink.getImportDrinkDate().getDate()));
			prepareStatement.setString(2, importDrink.getInvoiceNumber());
			prepareStatement.setInt(3, importDrink.getUser().getId());						
			prepareStatement.setDouble(4, importDrink.getTotal());			
			prepareStatement.setBoolean(5, importDrink.getStatus());
			prepareStatement.setInt(6, importDrink.getId());
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
	
	public boolean deleteImportDrink(int importDrinkId) {		
		boolean success = false;
		try {			
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("UPDATE import_drink SET status = false WHERE id = ?");			
			prepareStatement.setInt(1, importDrinkId);			
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

}
