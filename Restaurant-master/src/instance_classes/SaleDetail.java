package instance_classes;

import java.util.Date;

public class SaleDetail {
	
	private int id;
	private int saleId;
	private Date saleDate;
	private String username;
	private int productId;
	private String productName;
	private String type;
	private int qty;
	private double unitPrice;
	private double amount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSaleId() {
		return saleId;
	}
	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}	
	
	public Date getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public SaleDetail() {}
	
	public SaleDetail(int id, int saleId, int productId, String productName, int qty, String type, double unitPrice, double amount) {
		this.id = id;
		this.saleId = saleId;
		this.productId = productId;
		this.productName = productName;
		this.type = type;
		this.qty = qty;
		this.unitPrice = unitPrice;
		this.amount = amount;
	}			
	
	public SaleDetail(int saleId, Date saleDate, String username, String productName, String type, int qty, double unitPrice, double amount) {
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.username = username;
		this.productName = productName;
		this.type = type;
		this.qty = qty;
		this.unitPrice = unitPrice;
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "SaleDetail [id=" + id + ", saleId=" + saleId + ", productId=" + productId + ", productName="
				+ productName + ", type=" + type + ", qty=" + qty + ", unitPrice=" + unitPrice + ", amount=" + amount
				+ "]";
	}	
}
