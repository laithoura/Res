package data_table_model;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import control_classes.Formatter;
import instance_classes.Sale;

public class SaleDataModel extends AbstractTableModel{

	private ArrayList<Sale> saleList;
	
	private final String COLUMNS[] = {"Invoice No","Date","Username","Total"};
		
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
		return saleList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		Sale sale = saleList.get(rowIndex);			
		switch (colIndex) {
			case 0: return Formatter.numberToInvoiceNo(sale.getId());
			case 1: return Formatter.dateFormat( sale.getDate());
			case 2: return sale.getUserName();
			case 3: return Formatter.numberToText(sale.getTotal());
			default: return null;		
		}
	}
	
	public void updateTable() {
		fireTableDataChanged();
	}
	
	public void setSaleList(ArrayList<Sale> saleList) {
		this.saleList = saleList;
	}
}
