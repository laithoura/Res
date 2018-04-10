package InstanceClasses;

public class BookingDetail {
	private int id;
	private int bookingID;
	private int tableID;
	private String tableName;
	private String tableType;

	public BookingDetail() {
		
	}
	public BookingDetail(int id, int bookingID, int tableID, String tableName, String tableType) {		
		this.id = id;
		this.bookingID = bookingID;
		this.tableID = tableID;
		this.tableName = tableName;
		this.tableType = tableType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

	public int getTableID() {
		return tableID;
	}

	public void setTableID(int tableID) {
		this.tableID = tableID;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	@Override
	public String toString() {
		return "BookingDetail [id=" + id + ", bookingID=" + bookingID + ", tableID=" + tableID + ", tableName="
				+ tableName + ", tableType=" + tableType + "]";
	}
	
}
