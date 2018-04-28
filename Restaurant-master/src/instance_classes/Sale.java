package instance_classes;

import java.util.Date;

public class Sale {
	
	private int id;
	private Date date;
	private int userId;
	private String userName;
	private double total;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setSoldDate(Date date) {
		this.date = date;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	public Sale() { }
	
	public Sale(int id, Date date, int userId, String userName, double total) {
		
		this.id = id;
		this.date = date;
		this.userId = userId;
		this.userName = userName;
		this.total = total;
	}
	
	@Override
	public String toString() {
		return "Sell [id=" + id + ", Date=" + date + ", userId=" + userId + ", userName=" + userName
				+ ", total=" + total + "]";
	}	
	
}
