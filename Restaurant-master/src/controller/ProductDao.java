package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.DbConnection;
import instance_classes.Booking;
import instance_classes.Product;

public class ProductDao {
	private PreparedStatement prepareStatement;
	private ResultSet resultSet;
	
	public boolean insertProduct(Product product) {
		boolean success = false;
		try {
			prepareStatement = DbConnection.getConnection().prepareStatement("INSERT INTO product (name, unit_price, type, status) VALUES(?,?,?,true)"); 
			prepareStatement.setString(1, product.getName());
			prepareStatement.setDouble(2, product.getUnitPrice());
			prepareStatement.setString(3, product.getType());
			
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
	
	public boolean updateProduct(Product product) {
		boolean success = false;
		try {
			prepareStatement = DbConnection.getConnection().prepareStatement("UPDATE  product set name = ?, unit_price = ?, type = ?, status = true where id = ?"); 
			prepareStatement.setString(1, product.getName());
			prepareStatement.setDouble(2, product.getUnitPrice());
			prepareStatement.setString(3, product.getType());
			prepareStatement.setInt(4, product.getId());
			
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
	
	public boolean deleteProduct(int productId) {		
		boolean success = false;
		try {			
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("UPDATE product SET status = false WHERE id = ?");			
			prepareStatement.setInt(1, productId);			
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
	
	public ArrayList<Product> getProductLists(boolean status){
		ArrayList<Product> productList = new ArrayList<>();
		try {			
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT * FROM product WHERE status = ?");						
			prepareStatement.setBoolean(1, status);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				productList.add(new Product(resultSet.getInt("id"),
											resultSet.getString("name"),
											resultSet.getString("type"),
											resultSet.getDouble("unit_price"),
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
		return productList;
	}
	
	/*Added by Thoura Lai : 28-04-2018*/
	public ArrayList<Product> getProductListByType(String productType){
		ArrayList<Product> productList = new ArrayList<>();
		try {			
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT * FROM product WHERE type = ? AND status = ? ORDER BY name");
			prepareStatement.setString(1, productType);
			prepareStatement.setBoolean(2, true);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				productList.add(new Product(resultSet.getInt("id"),
											resultSet.getString("name"),
											resultSet.getString("type"),
											resultSet.getDouble("unit_price"),
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
		return productList;
	}/*End Adding by Thoura Lai : 28-04-2018*/
	
}
