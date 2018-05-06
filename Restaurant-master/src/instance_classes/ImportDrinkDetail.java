package instance_classes;

public class ImportDrinkDetail {
	private int id;
	private int productId;
	private int importDrinkId;
	private double qty;
	private double unitPrice;
	private double amount;
	private boolean status;
	private String drinkName;
	private String invoiceNumber;
	
	public ImportDrinkDetail() {
		
	}
	
	public ImportDrinkDetail(int id, String drinkName, int importDrinkId, String invoiceNumber,  double qty, double unitPrice, double amount,
			boolean status) {
		this.id = id;
		this.drinkName = drinkName;
		this.invoiceNumber = invoiceNumber;
		this.importDrinkId = importDrinkId;
		this.qty = qty;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.status = status;
	}

	public ImportDrinkDetail(int id, int productId, int importDrinkId,  double qty, double unitPrice, double amount,
			boolean status) {
		this.id = id;
		this.productId = productId;
		this.importDrinkId = importDrinkId;
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

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getImportDrinkId() {
		return importDrinkId;
	}

	public void setImportDrinkId(int importDrinkId) {
		this.importDrinkId = importDrinkId;
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

	public String getDrinkName() {
		return drinkName;
	}

	public void setDrinkName(String drinkName) {
		this.drinkName = drinkName;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}