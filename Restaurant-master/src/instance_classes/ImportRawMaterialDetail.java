package instance_classes;

public class ImportRawMaterialDetail {
	private int id;
	private int rawmaterialId;
	private String rawMaterialName;
	private String invoiceNumber;
	private int importRawMaterialId;
	private double qty;
	private double unitPrice;
	private double amount;
	private boolean status;
	
	
	public ImportRawMaterialDetail(int id, String rawMaterialName, int importRawMaterialId, String invoiceNumber,
			double qty, double unitPrice, double amount, boolean status) {
		super();
		this.id = id;
		this.rawMaterialName = rawMaterialName;
		this.invoiceNumber = invoiceNumber;
		this.importRawMaterialId = importRawMaterialId;
		this.qty = qty;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.status = status;
	}

	
	public ImportRawMaterialDetail() {
		
	}
	
	

	public ImportRawMaterialDetail(int id, int rawmaterialId, int importRawMaterialId, double qty, double unitPrice,
			double amount, boolean status) {
		this.id = id;
		this.rawmaterialId = rawmaterialId;
		this.importRawMaterialId = importRawMaterialId;
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

	public int getRawmaterialId() {
		return rawmaterialId;
	}

	public void setRawmaterialId(int rawmaterialId) {
		this.rawmaterialId = rawmaterialId;
	}

	public int getImportRawMaterialId() {
		return importRawMaterialId;
	}

	public void setImportRawMaterialId(int importRawMaterialId) {
		this.importRawMaterialId = importRawMaterialId;
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

	public String getRawMaterialName() {
		return rawMaterialName;
	}

	public void setRawMaterialName(String rawMaterialName) {
		this.rawMaterialName = rawMaterialName;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	
}
