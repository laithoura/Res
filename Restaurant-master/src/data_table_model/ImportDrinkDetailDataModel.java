package data_table_model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import connection.DbConnection;
import instance_classes.ImportDrinkDetail;
import instance_classes.ImportRawMaterialDetail;
import instance_classes.Product;

public class ImportDrinkDetailDataModel extends AbstractTableModel {
	private final String COLUMNS[] = new String[] {"Id", "Drink", "Import ID", "Invoice number","Qauntity", "Unit price", "Amount"};
	private ArrayList<ImportDrinkDetail> importDrinkDetailList = new ArrayList<ImportDrinkDetail>();

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public int getRowCount() {
		return importDrinkDetailList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		ImportDrinkDetail importDrinkDetail = importDrinkDetailList.get(rowIndex);
		switch (colIndex) {
			case 0: return importDrinkDetail.getId();
			case 1: return importDrinkDetail.getDrinkName();
			case 2: return importDrinkDetail.getImportDrinkId();
			case 3: return importDrinkDetail.getInvoiceNumber();		
			case 4: return importDrinkDetail.getQty();
			case 5: return importDrinkDetail.getUnitPrice();
			case 6: return importDrinkDetail.getAmount();
			default: return null;		
		}
	}
	
	@Override
	public String getColumnName(int colIndex) {	
		return COLUMNS[colIndex];
	}
	
	public void updateTable() {
		fireTableDataChanged();
	}
	
	public void setProuctList(ArrayList<ImportDrinkDetail> importDrinkDetailList) {
		this.importDrinkDetailList = importDrinkDetailList;
	}

}
