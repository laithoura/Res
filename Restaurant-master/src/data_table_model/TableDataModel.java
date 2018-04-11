package data_table_model;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;

import instance_classes.Table;

public class TableDataModel extends AbstractTableModel{

	private final String COLUMNS[]= {"Table ID","Table Name","Type Name","Status"};	
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
	public Object getValueAt(int rowIndex, int colIndex) {
		Table table = tableList.get(rowIndex);
		switch (colIndex) {
		case 0: return table.getId();
		case 1: return table.getName();
		case 2: return table.getType().getCategory();
		case 3: return (table.isStatus())?"Available":"Unavailable";		
		default: return null;
		}
	}
	
	public void updateTableModel() {
		fireTableDataChanged();
	}
	
	public void setTableModel(ArrayList<Table> tableList) {
		if(tableList != null) {
			tableList = new ArrayList<>();
		}			
		this.tableList = tableList;
	}

}