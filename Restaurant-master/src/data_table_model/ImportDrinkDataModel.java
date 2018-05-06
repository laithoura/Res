package data_table_model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import instance_classes.ImportDrink;
import instance_classes.ImportRawMaterial;
import instance_classes.Product;

public class ImportDrinkDataModel extends AbstractTableModel {
	private final String COLUMNS[] = new String[] {"Id", "Date", "Invoice Number","Total"};
	private ArrayList<ImportDrink> importDrinkList = new ArrayList<ImportDrink>();

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public int getRowCount() {
		return importDrinkList.size();
	}
	
	

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		ImportDrink importDrink = importDrinkList.get(rowIndex);
		switch (colIndex) {
			case 0: return importDrink.getId();
			case 1: return importDrink.getImportDrinkDate();
			case 2: return importDrink.getInvoiceNumber();
			case 3: return importDrink.getTotal();
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
	
	public void setProuctList(ArrayList<ImportDrink> importDrinkList) {
		this.importDrinkList = importDrinkList;
	}

}
