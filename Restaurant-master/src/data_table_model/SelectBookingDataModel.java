package data_table_model;

import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import instance_classes.Table;

public class SelectBookingDataModel extends AbstractTableModel {	
	
	private final String COLUMNS[]= {"Table Name","Type Name","Booking"};	
	private ArrayList<Table> tableList;
		
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
		return tableList.size();
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		System.out.println("Did AddTableModelListener");
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {		
		switch (columnIndex) {
		case 0: return Boolean.class;	
		case 1: return String.class;
		case 2: return String.class;											
		default: return null;
		}
	}	
	
	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		Table table = tableList.get(rowIndex);
		
		switch (colIndex) {
		case 0: return !table.isAvailable();
		case 1: return table.getName();		
		case 2: return table.getType();
		default: return null;
		}
	}
	
	public void updateTableModel() {
		fireTableDataChanged();
	}
	
	public void setTableModel(ArrayList<Table> tableList) {
		if(tableList == null) {
			tableList = new ArrayList<>();
		}			
		this.tableList = tableList;
	}	
}
