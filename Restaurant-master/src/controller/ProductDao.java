package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.DbConnection;
import instance_classes.Product;

public class ProductDao {
	private PreparedStatement prepareStatement;
	private ResultSet resultSet;
	private Statement st;
	
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
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT * FROM product WHERE type = ? AND status = ? ORDER BY name;");
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
	
	/*Added by Thoura Lai : 29-04-2018*/
	public ArrayList<Product> getOnlyInstockDrinkList(){	
		ArrayList<Product> productList = new ArrayList<>();
		try {			
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT P.id, P.name, P.type, P.unit_price, P.status FROM Product AS P INNER JOIN Import_Drink_Detail AS IDD ON IDD.pro_id = P.id WHERE (IDD.qty > IDD.soldQty) AND IDD.status = ? AND P.status = ? GROUP BY P.id, P.name, P.type, P.unit_price, P.status");
			prepareStatement.setBoolean(1, true);
			prepareStatement.setBoolean(2, true);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				productList.add(new Product(resultSet.getInt(1),
											resultSet.getString(2),
											resultSet.getString(3),
											resultSet.getDouble(4),
											resultSet.getBoolean(5)			
						));
			}
		} catch (SQLException e) {		
			e.printStackTrace();	
		}finally {
			try {
				resultSet.close();
			} catch (Exception e) {			
				e.printStackTrace();
			}
		}
		return productList;
	}/*End Adding by Thoura Lai : 29-04-2018*/
	
	/*Added by Thoura Lai : 29-04-2018*/
	public int countInstockDrink(int productId){	
		int productCount = 0;
		try {			
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT SUM(qty-soldQty) FROM Import_Drink_Detail WHERE pro_id = ? AND (qty-soldQty) > 0 AND status = ?");
			prepareStatement.setInt(1, productId);
			prepareStatement.setBoolean(2, true);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				productCount = resultSet.getInt(1);
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
		return productCount;
	}/*End Adding by Thoura Lai : 29-04-2018*/	
	
	public ArrayList<Product> getProductList() {
		ArrayList<Product> productList = new ArrayList<Product>();
		try {
			st = (Statement)  DbConnection.dbConnection.createStatement();
			resultSet = st.executeQuery("select * from product where status = true");
			Product product = null;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String type = resultSet.getString("type");
				double unitPrice = resultSet.getDouble("unit_price");
				boolean status = resultSet.getBoolean("status");	
				
				product = new Product(id, name, type, unitPrice, status);
				productList.add(product);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return productList;
	}
}
