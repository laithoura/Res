package instance_classes;

public class SaleDetail {
	
	private int id;
	private int saleId;
	private int productId;
	private String productName;
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
	
	public SaleDetail(int id, int saleId, int productId, String productName, int qty,double unitPrice, double amount) {
		this.id = id;
		this.saleId = saleId;
		this.productId = productId;
		this.productName = productName;
		this.qty = qty;
		this.unitPrice = unitPrice;
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "SaleDetail [id=" + id + ", saleId=" + saleId + ", productId=" + productId + ", productName="
				+ productName + ", qty=" + qty + ", unitPrice=" + unitPrice + ", amount=" + amount + "]";
	}
	
}
