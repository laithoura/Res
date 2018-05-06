package data_table_model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import control_classes.Formatter;
import instance_classes.SaleDetail;

public class SaleDetailDataModel extends AbstractTableModel{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<SaleDetail> saleDetailList;
	
	private final String COLUMNS[] = {"Invoice No","Date","Username","Product Name","Type","Quantity","Unit Price","Amount"};
		
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
		return saleDetailList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		SaleDetail saleDetail = saleDetailList.get(rowIndex);			
		switch (colIndex) {
			case 0: return Formatter.numberToInvoiceNo(saleDetail.getSaleId());
			case 1: return Formatter.dateFormat( saleDetail.getSaleDate());
			case 2: return saleDetail.getUsername();
			case 3: return saleDetail.getProductName();
			case 4: return saleDetail.getType();
			case 5: return saleDetail.getQty();
			case 6: return Formatter.numberToText(saleDetail.getUnitPrice());
			case 7: return Formatter.numberToText(saleDetail.getAmount());
			default: return null;		
		}
	}
	
	public void updateTable() {
		fireTableDataChanged();
	}
	
	public void setSaleDetailList(ArrayList<SaleDetail> saleDetailList) {
		this.saleDetailList = saleDetailList;
	}
}
