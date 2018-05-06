package data_table_model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import instance_classes.ImportRawMaterial;
import instance_classes.ImportRawMaterialDetail;

public class ImportRawMaterialDetailDataModel extends AbstractTableModel{
	private final String COLUMNS[] = new String[] {"Id", "Raw Material", "Impport Id", "Inovoice", "Qauntity", "Unit price", "Amount"};
	private ArrayList<ImportRawMaterialDetail> importRawMaterialDetialList = new ArrayList<ImportRawMaterialDetail>();

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public int getRowCount() {
		return importRawMaterialDetialList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		ImportRawMaterialDetail importRawMaterialDetail = importRawMaterialDetialList.get(rowIndex);
		switch (colIndex) {
			case 0: return importRawMaterialDetail.getId();
			case 1: return importRawMaterialDetail.getRawMaterialName();
			case 2: return importRawMaterialDetail.getImportRawMaterialId();
			case 3: return importRawMaterialDetail.getInvoiceNumber();
			case 4: return importRawMaterialDetail.getQty();
			case 5: return importRawMaterialDetail.getUnitPrice();
			case 6: return importRawMaterialDetail.getAmount();
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
	
	public void setProuctList(ArrayList<ImportRawMaterialDetail> importRawMaterialDetialList) {
		this.importRawMaterialDetialList = importRawMaterialDetialList;
	}

}
