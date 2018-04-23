package data_table_model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import instance_classes.*;

public class TypeDataModel extends AbstractTableModel  {
	private final String COLUMNS[] = new String[] {"Id", "Name", "Category", "Status"};
	private ArrayList<Type> typeList = new ArrayList<Type>();

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public int getRowCount() {
		return typeList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		
		Type type = typeList.get(rowIndex);
		switch (colIndex) {
			case 0: return type.getId();
			case 1: return type.getName();
			case 2: return type.getCategory();
			case 3: return type.getStatus();
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
	
	public void setTypeList(ArrayList<Type> typeList) {
		this.typeList = typeList;
	}
	

}
