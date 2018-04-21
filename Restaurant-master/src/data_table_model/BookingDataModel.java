package data_table_model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import control_classes.DateFormat;
import instance_classes.Booking;
import instance_classes.BookingDetail;

public class BookingDataModel extends AbstractTableModel{

	private ArrayList<Booking> bookingList;
	private final String COLUMNS[] = {"Booking ID","Customer's Name","Customer's Phone","Booking Date","Check-in Date","Time","Total Table"};
		
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
		return bookingList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		Booking booking = bookingList.get(rowIndex);			
		switch (colIndex) {
		case 0: return booking.getId();
		case 1: return booking.getCustomerName();
		case 2: return booking.getCustomerPhone();
		case 3: return DateFormat.dateFormat(booking.getBookingDate());
		case 4: return DateFormat.dateFormat(booking.getCheckInDate());
		case 5: return DateFormat.timeFormat(booking.getTime());
		case 6: return booking.getTotalTable();		
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
