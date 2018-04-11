package instance_classes;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Time;

public class Booking {
	
	private int id;
	private String customerName;
	private String customerPhone;
	private Date time;
	private Date bookingDate;
	private Date checkInDate;
	private int totalTable;
	private ArrayList<BookingDetail> tableBookingList = new ArrayList<>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public Date getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}
	public int getTotalTable() {
		return totalTable;
	}
	public void setTotalTable(int totalTable) {
		this.totalTable = totalTable;
	}
	public ArrayList<BookingDetail> getTableBookingList() {
		return tableBookingList;
	}
	public void setTableBookingList(ArrayList<BookingDetail> tableBookingList) {
		this.tableBookingList = tableBookingList;
	}
	
	public Booking() {
		
	}
	
	public Booking(int id, String customerName, String customerPhone, Date bookingDate, Date checkInDate, Date time,int totalTable, ArrayList<BookingDetail> tableBookingList) {
		this.id = id;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.time = time;
		this.bookingDate = bookingDate;
		this.checkInDate = checkInDate;
		this.totalTable = totalTable;
		this.tableBookingList = tableBookingList;
	}
	@Override
	public String toString() {
		return "Booking [id=" + id + ", customerName=" + customerName + ", customerPhone=" + customerPhone + ", time="
				+ time + ", bookingDate=" + bookingDate + ", checkInDate=" + checkInDate + ", totalTable=" + totalTable
				+ ", tableBookingList=" + tableBookingList + "]";
	}
		
}
