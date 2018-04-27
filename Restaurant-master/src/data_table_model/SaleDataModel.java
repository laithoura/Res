package data_table_model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import instance_classes.Sale;

public class SaleDataModel extends AbstractTableModel{

	private ArrayList<Sale> sellingList;
	
	private final String COLUMNS[] = {"Invoice No","Date","","Username","Total"};
		
	@Override
	public String getColumnName(int colIndex) {		
		return COLUMNS[colIndex];
	}
	
	@Override
	public int getColumnCount() {		
		return COLUMNS.length;
	}
	
	@Override
	public int getRowCount() {
		return sellingList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		Sale sell = sellingList.get(rowIndex);			
		switch (colIndex) {
		case 0: return sell.getId();
		case 1: return sell.getSoldDate();
		case 2: return sell.getUserName();
		case 3: return sell.getTotal();		
		default: return null;		
		}
	}
	
	public void updateTable() {
		fireTableDataChanged();
	}
	
	public void setBookingList(ArrayList<Sale> sellingList) {
		this.sellingList = sellingList;
	}
}
