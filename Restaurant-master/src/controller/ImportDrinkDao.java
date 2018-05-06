package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import connection.DbConnection;
import instance_classes.ImportDrink;
import instance_classes.ImportDrinkDetail;

public class ImportDrinkDao {
	private PreparedStatement prepareStatement;
	private ResultSet resultSet;
	private Connection con;
	private Statement st;
	
	public ImportDrinkDao() {
	}
	
	public boolean insertImportDrink(ImportDrink importDrink) {
		boolean success = false;
		try {
			con = DbConnection.getConnection();
			prepareStatement = con.prepareStatement("INSERT INTO import_drink (date, invoice_no, user_id, total, status) VALUES(?,?,?,?,true)"); 
			prepareStatement.setDate(1, new java.sql.Date(importDrink.getImportDrinkDate().getDate()));
			prepareStatement.setString(2, importDrink.getInvoiceNumber());
			prepareStatement.setInt(3, importDrink.getUserId());
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
			prepareStatement.setInt(3, importDrink.getUserId());						
			prepareStatement.setDouble(4, importDrink.getTotal());			
			prepareStatement.setBoolean(5, importDrink.isStatus());
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
	
	public boolean insertIntoImportDrinkDetail(ImportDrinkDetail importDrinkDetail) {
		boolean success = false;
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = (PreparedStatement) DbConnection.getConnection().prepareStatement("INSERT INTO import_drink_detail (pro_id, imp_drink_id, qty, unit_price, amount, status) VALUES(?,?,?,?,?,?)");
			preparedStatement.setInt(1, importDrinkDetail.getImportDrinkId());
			preparedStatement.setInt(2, importDrinkDetail.getProductId());
			preparedStatement.setDouble(3, importDrinkDetail.getQty());
			preparedStatement.setDouble(4, importDrinkDetail.getUnitPrice());
			preparedStatement.setDouble(5, importDrinkDetail.getAmount());
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
	
	public ArrayList<ImportDrinkDetail> getImportDrinkDetailList() {
		ArrayList<ImportDrinkDetail> importDrinkDetailList = new ArrayList<ImportDrinkDetail>();
		try {
			st = (Statement)  DbConnection.dbConnection.createStatement();
			resultSet = st.executeQuery("select impdd.`*`, pro.name, impd.invoice_no from import_drink_detail as impdd inner join product as pro on impdd.pro_id = pro.id inner join import_drink as impd on impdd.imp_drink_id = impd.id where impdd.status = true");
			ImportDrinkDetail importDrinkDetail = null;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String drinkName = resultSet.getString("pro.name");
				String invoiceNumber = resultSet.getString("impd.invoice_no");
				int importdrinkId = resultSet.getInt("imp_drink_id");
				double qty = resultSet.getDouble("qty");
				double unitPrice = resultSet.getDouble("unit_price");
				double amount = resultSet.getDouble("amount");
				boolean status = resultSet.getBoolean("status");
				
				importDrinkDetail = new ImportDrinkDetail(id, drinkName, importdrinkId, invoiceNumber, qty, unitPrice, amount, status);;
				importDrinkDetailList.add(importDrinkDetail);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return importDrinkDetailList;
	}
	
	public ArrayList<ImportDrink> getImportDrikList() {
		ArrayList<ImportDrink> importDrinkList = new ArrayList<ImportDrink>();
		try {
			st = (Statement)  DbConnection.dbConnection.createStatement();
			resultSet = st.executeQuery("select * from import_drink where status = true");
			ImportDrink importDrink = null;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Date date = resultSet.getDate("date");
				String invoiceNo = resultSet.getString("invoice_no");
				int userId = resultSet.getInt("user_id");
				double total = resultSet.getDouble("total");
				boolean status = resultSet.getBoolean("status");	
				
				importDrink = new ImportDrink(id, date, invoiceNo, userId, total, status);
				importDrinkList.add(importDrink);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return importDrinkList;
	}

}
