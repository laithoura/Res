package instance_classes;

public class Product {
	private int id;
	private String name;
	private Type type;
	private double unitPrice;
	private boolean status;
	
	public Product() {
		
	}
	
	public Product(int id, String name, Type type, double unitPrice, boolean status ) {
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
	
	public Type getType() {
		return this.type;
	}
	
	public void setType(Type type) {
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
