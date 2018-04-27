package instance_classes;

import java.util.Date;

public class Sale {
	
	private int id;
	private Date soldDate;
	private int userId;
	private String userName;
	private double total;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getSoldDate() {
		return soldDate;
	}
	public void setSoldDate(Date soldDate) {
		this.soldDate = soldDate;
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
	
	public Sale(int id, Date soldDate, int userId, String userName, double total) {
		
		this.id = id;
		this.soldDate = soldDate;
		this.userId = userId;
		this.userName = userName;
		this.total = total;
	}
	
	@Override
	public String toString() {
		return "Sell [id=" + id + ", soldDate=" + soldDate + ", userId=" + userId + ", userName=" + userName
				+ ", total=" + total + "]";
	}	
	
}
