package data_table_model;

import java.util.ArrayList;
import instance_classes.*;
import javax.swing.table.AbstractTableModel;


public class ProductDataModel extends AbstractTableModel {

	private final String COLUMNS[] = new String[] {"Id", "Name", "Type","Unit Price", "Status"};
	private ArrayList<Product> productList = new ArrayList<Product>();

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public int getRowCount() {
		return productList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		
		Product product = productList.get(rowIndex);
		switch (colIndex) {
			case 0: return product.getId();
			case 1: return product.getName();
			case 2: return product.getType();
			case 3: return product.getUnitPrice();
			case 4: return product.getStatus();
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
	
	public void setProuctList(ArrayList<Product> productList) {
		this.productList = productList;
	}

}
