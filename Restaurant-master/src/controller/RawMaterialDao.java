package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.DbConnection;
import instance_classes.RawMaterial;

public class RawMaterialDao {
	private PreparedStatement prepareStatement;
	private ResultSet resultSet;
	
	public boolean insertRawMaterial(RawMaterial rawMaterial) {
		boolean success = false;
		try {
			prepareStatement = DbConnection.getConnection().prepareStatement("INSERT INTO raw_material (name, type, description, status) VALUES(?,?,?,true)"); 
			prepareStatement.setString(1, rawMaterial.getName());
			prepareStatement.setString(2, rawMaterial.getType());
			prepareStatement.setString(3, rawMaterial.getDescriptioin());
			
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
	
	public boolean updateRawMaterial(RawMaterial rawMaterial) {
		boolean success = false;
		try {
			prepareStatement = DbConnection.getConnection().prepareStatement("UPDATE  raw_material set name = ?, type = ?, description = ?, status = true"); 
			prepareStatement.setString(1, rawMaterial.getName());
			prepareStatement.setString(2, rawMaterial.getType());
			prepareStatement.setString(3, rawMaterial.getDescriptioin());
			
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
	
	public boolean deleteRawMaterial(int rawMaterialId) {		
		boolean success = false;
		try {			
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("UPDATE raw_material SET status = false WHERE id = ?");			
			prepareStatement.setInt(1, rawMaterialId);			
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
	
	public ArrayList<RawMaterial> getBookingLists(boolean status){
		ArrayList<RawMaterial> rawMaterialList = new ArrayList<>();
		try {			
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT * FROM raw_materail WHERE status = ?");						
			prepareStatement.setBoolean(1, status);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				rawMaterialList.add(new RawMaterial(resultSet.getInt("id"),
											resultSet.getString("name"),
											resultSet.getString("type"),
											resultSet.getString("description"),
											resultSet.getBoolean("status")			
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
		return rawMaterialList;
	}
}
