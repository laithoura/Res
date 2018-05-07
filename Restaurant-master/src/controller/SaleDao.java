package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import com.mysql.jdbc.PreparedStatement;
import connection.DbConnection;
import instance_classes.Sale;
import instance_classes.SaleDetail;

public class SaleDao {	
	private PreparedStatement prepareStatement;
	private ResultSet resultSet;
	
	public SaleDao() {}
	
	public boolean insertSale(Sale sale) {
		boolean success = false;
		try {
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("INSERT INTO Sold(date, user_id, total, status) VALUES(?,?,?,?)");
			prepareStatement.setDate(1,  new java.sql.Date(sale.getDate().getTime()));
			prepareStatement.setInt(2, sale.getUserId());
			prepareStatement.setDouble(3, sale.getTotal());			
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
	
	public boolean updateSale(Sale sale) {
		boolean success = false;
		try {
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("UPDATE Sold SET date=?, user_id=?, total=? WHERE id = ?");			
			prepareStatement.setDate(1,  new java.sql.Date(sale.getDate().getTime()));
			prepareStatement.setInt(2, sale.getUserId());
			prepareStatement.setDouble(3, sale.getTotal());
			prepareStatement.setInt(6, sale.getId());
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
	
	public boolean deleteSale(int saleId) {
		boolean success = false;
		try {
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("UPDATE Sold SET status = ? WHERE id = ?");			
			prepareStatement.setBoolean(1, false);
			prepareStatement.setInt(2, saleId);
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
	
	public ArrayList<Sale> getSaleLists(boolean status){
		ArrayList<Sale> saleList = new ArrayList<>();
		try {			
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT S.id, S.date, U.id, U.username, S.total FROM Sold AS S INNER JOIN User as U ON S.user_id = U.id WHERE S.status = ?");						
			prepareStatement.setBoolean(1, status);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				saleList.add(new Sale(	resultSet.getInt(1),
										(java.util.Date)resultSet.getDate(2),
										resultSet.getInt(3),
										resultSet.getString(4),
										resultSet.getDouble(5)																								
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
		return saleList;
	}
	
	public boolean deleteAllSaleDetail(int saleId) {
		boolean success = false;
		PreparedStatement preparedStatement = null;
		try {			
			preparedStatement = (PreparedStatement) DbConnection.getConnection().prepareStatement("DELETE FROM Sold_Detail WHERE sold_id = ?");
			preparedStatement.setInt(1, saleId);
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
	

	public boolean insertIntoSaleDetails(SaleDetail saleDetail) {
		boolean success = false;
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = (PreparedStatement) DbConnection.getConnection().prepareStatement("INSERT INTO Sold_Detail(sold_id, pro_id, unit_price, qty, amount, status) VALUES(?,?,?,?,?,?)");
			preparedStatement.setInt(1, saleDetail.getSaleId());
			preparedStatement.setInt(2, saleDetail.getProductId());
			preparedStatement.setDouble(3, saleDetail.getUnitPrice());
			preparedStatement.setInt(4, saleDetail.getQty());
			preparedStatement.setDouble(5, saleDetail.getAmount());
			preparedStatement.setBoolean(6, true);
			if(preparedStatement.executeUpdate() > 0) {
				success = true;
			}			
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
	
	public ArrayList<Sale> searchSaleList(Object condition, int searchType){
		ArrayList<Sale> saleList = new ArrayList<>();
		try {
			
			if(searchType == 0) { /*Sale ID or Invoice No*/
				
				prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT S.id, S.date, U.id, U.username, S.total FROM Sold AS S INNER JOIN User as U ON S.user_id = U.id WHERE S.id = ? AND S.status = ?");						
				prepareStatement.setInt(1, (int) condition);
				prepareStatement.setBoolean(2, true); /*Select Active Sale Records*/
				
			}else if(searchType == 1) { /*Sale Date*/
				
				prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT S.id, S.date, U.id, U.username, S.total FROM Sold AS S INNER JOIN User as U ON S.user_id = U.id WHERE S.date = ? AND S.status = ?");						
				prepareStatement.setDate(1, new java.sql.Date(((Date)condition).getTime()));
				prepareStatement.setBoolean(2, true); /*Select Active Sale Records*/
				
			}else if(searchType == 2) { /*Username*/
				
				prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT S.id, S.date, U.id, U.username, S.total FROM Sold AS S INNER JOIN User as U ON S.user_id = U.id WHERE U.username LIKE ? AND S.status = ?");						
				prepareStatement.setString(1, "%" + (String) condition + "%");
				prepareStatement.setBoolean(2, true); /*Select Active Sale Records*/			
			}			
			
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {			
				saleList.add(new Sale(	resultSet.getInt(1),
						(java.util.Date)resultSet.getDate(2),
						resultSet.getInt(3),
						resultSet.getString(4),
						resultSet.getDouble(5)																								
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
		return saleList;
	}

	public ArrayList<SaleDetail> getSaleDetailList(boolean status) {
		ArrayList<SaleDetail> saleDetailList = new ArrayList<>();
		try {			
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT S.id, S.date, U.username, P.name, P.type, SD.qty, SD.unit_price, SD.amount FROM ((Sold AS S INNER JOIN Sold_Detail AS SD ON S.id = SD.sold_id) INNER JOIN Product AS P ON SD.pro_id = P.id) INNER JOIN User as U ON S.user_id = U.id WHERE S.status = ?");						
			prepareStatement.setBoolean(1, status);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				saleDetailList.add(new SaleDetail(resultSet.getInt(1), /*Sold ID or Invoice No*/
										(java.util.Date)resultSet.getDate(2), /*Sold Date*/
										resultSet.getString(3), /*Username*/
										resultSet.getString(4), /*Product Name*/
										resultSet.getString(5), /*Type*/
										resultSet.getInt(6), /*Quantity*/
										resultSet.getDouble(7), /*Unit Price*/
										resultSet.getDouble(8) /*Amount*/
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
		return saleDetailList;
	}

	public ArrayList<SaleDetail> searchSaleDetail(Object condition, int searchType) {
		
		ArrayList<SaleDetail> saleDetailList = new ArrayList<>();
		try {								
			if(searchType == 0) { /*Search by Invoice Number*/												
				
				prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT S.id, S.date, U.username, P.name, P.type, SD.qty, SD.unit_price, SD.amount FROM ((Sold AS S INNER JOIN Sold_Detail AS SD ON S.id = SD.sold_id) INNER JOIN Product AS P ON SD.pro_id = P.id) INNER JOIN User as U ON S.user_id = U.id WHERE S.id = ? AND S.status = ?");
				prepareStatement.setInt(1, (int) condition);
				prepareStatement.setBoolean(2, true);
				
			}else if(searchType == 1) { /*Sale Date*/

				prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT S.id, S.date, U.username, P.name, P.type, SD.qty, SD.unit_price, SD.amount FROM ((Sold AS S INNER JOIN Sold_Detail AS SD ON S.id = SD.sold_id) INNER JOIN Product AS P ON SD.pro_id = P.id) INNER JOIN User as U ON S.user_id = U.id WHERE S.date = ? AND S.status = ?");
				prepareStatement.setDate(1, new java.sql.Date(((Date)condition).getTime()));
				prepareStatement.setBoolean(2, true);
				
			}else if(searchType == 2) { /*Username*/

				prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT S.id, S.date, U.username, P.name, P.type, SD.qty, SD.unit_price, SD.amount FROM ((Sold AS S INNER JOIN Sold_Detail AS SD ON S.id = SD.sold_id) INNER JOIN Product AS P ON SD.pro_id = P.id) INNER JOIN User as U ON S.user_id = U.id WHERE U.username LIKE ? AND S.status = ?");
				prepareStatement.setString(1, "%" + (String) condition + "%");
				prepareStatement.setBoolean(2, true);
			}else if(searchType == 3) { /*Product Name*/
				
				prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT S.id, S.date, U.username, P.name, P.type, SD.qty, SD.unit_price, SD.amount FROM ((Sold AS S INNER JOIN Sold_Detail AS SD ON S.id = SD.sold_id) INNER JOIN Product AS P ON SD.pro_id = P.id) INNER JOIN User as U ON S.user_id = U.id WHERE P.name LIKE ? AND S.status = ?");
				prepareStatement.setString(1, "%" + (String) condition + "%");
				prepareStatement.setBoolean(2, true);				
			}else if(searchType == 4) { /*Product Type*/
				
				prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT S.id, S.date, U.username, P.name, P.type, SD.qty, SD.unit_price, SD.amount FROM ((Sold AS S INNER JOIN Sold_Detail AS SD ON S.id = SD.sold_id) INNER JOIN Product AS P ON SD.pro_id = P.id) INNER JOIN User as U ON S.user_id = U.id WHERE P.type LIKE ? AND S.status = ?");
				prepareStatement.setString(1, "%" + (String) condition + "%");
				prepareStatement.setBoolean(2, true);	
			}
									
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				saleDetailList.add(new SaleDetail(resultSet.getInt(1), /*Sold ID or Invoice No*/
										(java.util.Date)resultSet.getDate(2), /*Sold Date*/
										resultSet.getString(3), /*Username*/
										resultSet.getString(4), /*Product Name*/
										resultSet.getString(5), /*Type*/
										resultSet.getInt(6), /*Quantity*/
										resultSet.getDouble(7), /*Unit Price*/
										resultSet.getDouble(8) /*Amount*/
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
		return saleDetailList;		
	}	
}
