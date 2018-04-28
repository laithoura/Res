package instance_classes;

public class Product {
	private int id;
	private String name;
	private String type;
	private double unitPrice;
	private boolean status;
	
	public Product() {
		
	}
	
	public Product(int id, String name, String type, double unitPrice, boolean status ) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.unitPrice = unitPrice;
		this.status = status;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public double getUnitPrice() {
		return this.unitPrice;
	}

	public void setPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public boolean getStatus() {
		return this.status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
}
