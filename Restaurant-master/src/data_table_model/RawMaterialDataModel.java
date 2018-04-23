package data_table_model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import instance_classes.*;

public class RawMaterialDataModel extends AbstractTableModel{

	private final String COLUMNS[] = new String[] {"Id", "Name", "Type","Description", "Status"};
	private ArrayList<RawMaterial> rawMaterialList = new ArrayList<RawMaterial>();

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public int getRowCount() {
		return rawMaterialList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		
		RawMaterial rawMaterial = rawMaterialList.get(rowIndex);
		switch (colIndex) {
			case 0: return rawMaterial.getId();
			case 1: return rawMaterial.getName();
			case 2: return rawMaterial.getType().getName();
			case 3: return rawMaterial.getDescriptioin();
			case 4: return rawMaterial.getStatus();
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
	
	public void setRawMaterialList(ArrayList<RawMaterial> rawMaterialList) {
		this.rawMaterialList = rawMaterialList;
	}

}
