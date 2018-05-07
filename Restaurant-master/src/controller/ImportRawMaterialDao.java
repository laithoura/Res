package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import connection.DbConnection;
import instance_classes.ImportRawMaterial;
import instance_classes.ImportRawMaterialDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class ImportRawMaterialDao {
	private PreparedStatement prepareStatement;
	private ResultSet resultSet;
	private Statement st;
	
	public ImportRawMaterialDao() {
	}
	
	public boolean insertImportRawMaterial(ImportRawMaterial importRawMaterial) {
		boolean success = false;
		try {
			
			prepareStatement = DbConnection.dbConnection.prepareStatement("INSERT INTO import_rm (date, invoice_no, user_id, total, status) VALUES(?,?,?,?,true)"); 
			prepareStatement.setDate(1, new java.sql.Date(importRawMaterial.getImportRawMaterialDate().getTime()));
			prepareStatement.setString(2, importRawMaterial.getInvoiceNumber());
			prepareStatement.setInt(3, importRawMaterial.getUserId());
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
			prepareStatement.setInt(3, importRawMaterial.getUserId());						
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
	
	
	public boolean insertIntoImportRawMaterialDetail(ImportRawMaterialDetail importRawMaterialDetail) {
		boolean success = false;
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = (PreparedStatement) DbConnection.getConnection().prepareStatement("INSERT INTO import_rm_detail (rm_id, imp_rm_id, qty, unit_price, amount, status) VALUES(?,?,?,?,?,?)");
			preparedStatement.setInt(1, importRawMaterialDetail.getRawmaterialId());
			preparedStatement.setInt(2, importRawMaterialDetail.getImportRawMaterialId());
			preparedStatement.setDouble(3, importRawMaterialDetail.getQty());
			preparedStatement.setDouble(4, importRawMaterialDetail.getUnitPrice());
			preparedStatement.setDouble(5, importRawMaterialDetail.getAmount());
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
	
	public ArrayList<ImportRawMaterialDetail> getImportRawMaterialDetailList() {
		ArrayList<ImportRawMaterialDetail> importRawMaterialDetailList = new ArrayList<ImportRawMaterialDetail>();
		try {
			st = (Statement)  DbConnection.dbConnection.createStatement();
			resultSet = st.executeQuery("select imprmd.`*`, rm.name, imprm.invoice_no from import_rm_detail as imprmd inner join raw_material as rm on imprmd.rm_id = rm.id inner join import_rm as imprm on imprmd.imp_rm_id = imprm.id where imprmd.status = true");
			ImportRawMaterialDetail importRawMaterialDetail = null;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String rawMaterialName = resultSet.getString("rm.name");
				String invoiceNumber = resultSet.getString("imprm.invoice_no");
				int importRawMaterialId = resultSet.getInt("imp_rm_id");
				double qty = resultSet.getDouble("qty");
				double unitPrice = resultSet.getDouble("unit_price");
				double amount = resultSet.getDouble("amount");
				boolean status = resultSet.getBoolean("status");
				
				importRawMaterialDetail = new ImportRawMaterialDetail(id, rawMaterialName, importRawMaterialId, invoiceNumber, qty, unitPrice, amount, status);
				importRawMaterialDetailList.add(importRawMaterialDetail);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return importRawMaterialDetailList;
	}
	
	public ArrayList<ImportRawMaterial> getImportRawMaterialList() {
		ArrayList<ImportRawMaterial> importRawMaterialList = new ArrayList<ImportRawMaterial>();
		try {
			st = DbConnection.dbConnection.createStatement();
			resultSet = st.executeQuery("select * from import_rm where status = true");
			ImportRawMaterial importRawMaterial = null;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Date date = resultSet.getDate("date");
				String invoiceNo = resultSet.getString("invoice_no");
				int userId = resultSet.getInt("user_id");
				double total = resultSet.getDouble("total");
				boolean status = resultSet.getBoolean("status");	
				
				importRawMaterial = new ImportRawMaterial(id, date, invoiceNo, userId, total, status);
				importRawMaterialList.add(importRawMaterial);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return importRawMaterialList;
	}
}
