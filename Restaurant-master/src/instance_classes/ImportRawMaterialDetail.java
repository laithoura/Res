package instance_classes;

public class ImportRawMaterialDetail {
	private int id;
	private RawMaterial rawmaterial;
	private ImportRawMaterial importRawMaterial;
	private double qty;
	private double unitPrice;
	private double amount;
	private boolean status;
	
	public ImportRawMaterialDetail() {
		
	}

	public ImportRawMaterialDetail(int id, RawMaterial rawmaterial, ImportRawMaterial importRawMaterial, double qty,
			double unitPrice, double amount, boolean status) {
		super();
		this.id = id;
		this.rawmaterial = rawmaterial;
		this.importRawMaterial = importRawMaterial;
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

	public RawMaterial getRawmaterial() {
		return rawmaterial;
	}

	public void setRawmaterial(RawMaterial rawmaterial) {
		this.rawmaterial = rawmaterial;
	}

	public ImportRawMaterial getImportRawMaterial() {
		return importRawMaterial;
	}

	public void setImportRawMaterial(ImportRawMaterial importRawMaterial) {
		this.importRawMaterial = importRawMaterial;
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
