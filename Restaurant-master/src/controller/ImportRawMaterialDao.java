package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.DbConnection;
import instance_classes.ImportRawMaterial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import instance_classes.User;


public class ImportRawMaterialDao {
	private PreparedStatement prepareStatement;
	private ResultSet resultSet;
	private Connection con;
	
	public ImportRawMaterialDao() {
	}
	
	public boolean insertImportRawMaterialDao(ImportRawMaterial importRawMaterial) {
		boolean success = false;
		try {
			con = DbConnection.getConnection();
			prepareStatement = con.prepareStatement("INSERT INTO import_rm (date, invoice_no, user_id, total, status) VALUES(?,?,?,?,true)"); 
			prepareStatement.setDate(1, new java.sql.Date(importRawMaterial.getImportRawMaterialDate().getDate()));
			prepareStatement.setString(2, importRawMaterial.getInvoiceNumber());
			prepareStatement.setInt(3, importRawMaterial.getUser().getId());
			prepareStatement.setDouble(4, importRawMaterial.getTotal());
			//prepareStatement.setBoolean(5, importRawMaterial.isStatus());
			
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
	
	public boolean updateImportRawMaterail(ImportRawMaterial importRawMaterial) {
		boolean success = false;
		try {			
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("UPDATE import_rm SET date=?, invoice_no=?, user_id=?, total=?, status=? WHERE id = ?");			
			prepareStatement.setDate(1, new java.sql.Date(importRawMaterial.getImportRawMaterialDate().getDate()));
			prepareStatement.setString(2, importRawMaterial.getInvoiceNumber());
			prepareStatement.setInt(3, importRawMaterial.getUser().getId());						
			prepareStatement.setDouble(4, importRawMaterial.getTotal());			
			prepareStatement.setBoolean(5, importRawMaterial.isStatus());
			prepareStatement.setInt(6, importRawMaterial.getId());
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
	
	public boolean deleteBooking(int importRawMaterialId) {		
		boolean success = false;
		try {			
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("UPDATE import_rm SET status = false WHERE id = ?");			
			prepareStatement.setInt(1, importRawMaterialId);			
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
	
	/*public ArrayList<ImportRawMaterial> getImportRawMaterialList(boolean status){
		ArrayList<ImportRawMaterial> importRawMaterialList = new ArrayList<ImportRawMaterial>();
		try {			
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT * FROM import_rm WHERE status = true");						
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				importRawMaterialList.add(new ImportRawMaterial(resultSet.getInt("id"),
											(java.util.Date)resultSet.getDate("date"),
											resultSet.getString("invoice_no"),
											//resultSet.getInt("user_id"),
											resultSet.getDouble("total"),
											resultSet.getBoolean("status");
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
	}*/
	
	/*public User getUserByUserId(int userId) {
		try {
			
		}catch (SQLException ex) {
			
		}
		
		return User;
	}*/

}
