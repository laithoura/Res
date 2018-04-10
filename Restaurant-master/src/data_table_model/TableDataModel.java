package data_table_model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import instance_classes.Table;

public class TableDataModel extends AbstractTableModel{

	private String COLUMNS[]= {"Table ID","Table Name","Type","Table Number","Table Total","Description"};	
	private ArrayList<Table> tableList = new ArrayList<Table>();
	
	@Override
	public int getColumnCount() {	
		return COLUMNS.length;
	}

	@Override
	public int getRowCount() {
		return tableList.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Table table = tableList.get(row);
		switch (col) {
		case 0: return table.getId();
		case 1: return table.getName();
		case 2: return table.getType().getName();
		case 3: return table.getTableCount();
		case 4: return table.getDescription();
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
