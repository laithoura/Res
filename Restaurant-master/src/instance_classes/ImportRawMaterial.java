package instance_classes;

import java.util.Date;

public class ImportRawMaterial {
	private int id;
	private Date importRawMaterialDate;
	private String invoiceNumber;
	private User user;
	private double total;
	private boolean status;
	
	public ImportRawMaterial() {
		
	}
	
	public ImportRawMaterial(int id, Date importRawMaterialDate, String invoiceNumber, User user, double total,
			boolean status) {
		super();
		this.id = id;
		this.importRawMaterialDate = importRawMaterialDate;
		this.invoiceNumber = invoiceNumber;
		this.user = user;
		this.total = total;
		this.status = status;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getImportRawMaterialDate() {
		return importRawMaterialDate;
	}

	public void setImportRawMaterialDate(Date importRawMaterialDate) {
		this.importRawMaterialDate = importRawMaterialDate;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
