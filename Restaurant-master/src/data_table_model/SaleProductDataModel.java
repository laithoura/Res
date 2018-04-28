package data_table_model;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import control_classes.Formatter;
import instance_classes.SaleDetail;

public class SaleProductDataModel extends AbstractTableModel{

	private ArrayList<SaleDetail> saleProductList;	
	private final String COLUMNS[] = {"Name","Price","Quantity","Total"};
		
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
		return saleProductList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		SaleDetail saleProduct = saleProductList.get(rowIndex);			
		switch (colIndex) {
			case 0: return saleProduct.getProductName();
			case 1: return Formatter.numberToText(saleProduct.getUnitPrice());
			case 2: return saleProduct.getQty();
			case 3: return Formatter.numberToText(saleProduct.getAmount());
			default: return null;		
		}
	}
	
	public void updateTable() {
		fireTableDataChanged();
	}
	
	public void setSaleList(ArrayList<SaleDetail> saleProductList) {
		this.saleProductList = saleProductList;
	}
}
