package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.DbConnection;
import instance_classes.RawMaterial;

public class RawMaterialDao {
	private PreparedStatement prepareStatement;
	private ResultSet resultSet;
	private Statement st;
	
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
			prepareStatement = DbConnection.getConnection().prepareStatement("UPDATE  raw_material set name = ?, type = ?, description = ?, status = true where id = ?"); 
			prepareStatement.setString(1, rawMaterial.getName());
			prepareStatement.setString(2, rawMaterial.getType());
			prepareStatement.setString(3, rawMaterial.getDescriptioin());
			prepareStatement.setInt(4, rawMaterial.getId());
			
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
	
	public ArrayList<RawMaterial> getRawMaterialList(){
		ArrayList<RawMaterial> rawMaterialList = new ArrayList<RawMaterial>();
		try {
			st = (Statement)  DbConnection.dbConnection.createStatement();
			resultSet = st.executeQuery("select * from raw_material where status = true");
			while (resultSet.next()) {
				int id = Integer.parseInt(resultSet.getString("id"));
				String name = resultSet.getString("name");
				String type = resultSet.getString("type");
				String description = resultSet.getString("description");
				boolean status = resultSet.getBoolean("status");
				
				RawMaterial rawMaterial = new RawMaterial(id, name, type, description, status);
				rawMaterialList.add(rawMaterial);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rawMaterialList;
	}
	
}
