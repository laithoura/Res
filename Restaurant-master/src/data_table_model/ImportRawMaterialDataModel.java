package data_table_model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import instance_classes.ImportRawMaterial;
import instance_classes.Product;

public class ImportRawMaterialDataModel extends AbstractTableModel {
	private final String COLUMNS[] = new String[] {"Id", "Date", "Invoice Number","Total"};
	private ArrayList<ImportRawMaterial> importRawMaterialList = new ArrayList<ImportRawMaterial>();

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public int getRowCount() {
		return importRawMaterialList.size();
	}
	
	

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		ImportRawMaterial importRawMaterial = importRawMaterialList.get(rowIndex);
		switch (colIndex) {
			case 0: return importRawMaterial.getId();
			case 1: return importRawMaterial.getImportRawMaterialDate();
			case 2: return importRawMaterial.getInvoiceNumber();
			case 3: return importRawMaterial.getTotal();
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
	
	public void setProuctList(ArrayList<ImportRawMaterial> importRawMaterialList) {
		this.importRawMaterialList = importRawMaterialList;
	}

}
