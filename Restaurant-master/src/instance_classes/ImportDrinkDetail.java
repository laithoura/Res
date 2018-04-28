package instance_classes;

public class ImportDrinkDetail {
	private int id;
	private Product product;
	private ImportDrink importDrink;
	private double qty;
	private double unitPrice;
	private double amount;
	private boolean status;
	
	public ImportDrinkDetail() {
		
	}
	

	public ImportDrinkDetail(int id, Product product, ImportDrink importDrink, double qty, double unitPrice,
			double amount, boolean status) {
		super();
		this.id = id;
		this.product = product;
		this.importDrink = importDrink;
		this.qty = qty;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.status = status;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ImportDrink getImportDrink() {
		return importDrink;
	}

	public void setImportDrink(ImportDrink importDrink) {
		this.importDrink = importDrink;
	}

	public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
