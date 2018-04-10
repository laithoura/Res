package data_table_model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import instance_classes.Booking;
import instance_classes.BookingDetail;

public class BookingDataModel extends AbstractTableModel{

	private ArrayList<Booking> bookingList = null;
	private final String COLUMNS[] = {"Booking ID","Customer's Name","Customer's Phone","Booking Date","Check-in Date","Time","Total Table","Table Names"};
	
	@Override
	public int getColumnCount() {		
		return COLUMNS.length;
	}

	@Override
	public String getColumnName(int colIndex) {		
		return COLUMNS[colIndex];
	}
	
	@Override
	public int getRowCount() {
		return bookingList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		Booking booking = bookingList.get(rowIndex);	
		String tableNames = "";
		for (BookingDetail bookingDetail: booking.getTableBookingList()) {
			tableNames += "\n" + bookingDetail.getTableName();
		}
		
		switch (colIndex) {
		case 0: return booking.getId();
		case 1: return booking.getCustomerName();
		case 2: return booking.getCustomerPhone();
		case 3: return booking.getBookingDate();
		case 4: return booking.getCheckInDate();
		case 5: return booking.getTime();
		case 6: return booking.getTotalTable();
		case 7: return tableNames;
		default: return null;		
		}
	}
	
	public void updateTable() {
		fireTableDataChanged();
	}
	
	public void setBookingList(ArrayList<Booking> bookingList) {
		this.bookingList = bookingList;
	}
}
