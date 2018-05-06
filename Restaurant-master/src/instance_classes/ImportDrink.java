package instance_classes;

import java.util.Date;

public class ImportDrink {
	private int id;
	private Date importDrinkDate;
	private String invoiceNumber;
	private int userId;
	private double total;
	private boolean status;
	
	public ImportDrink() {
		
	}

	public ImportDrink(int id, Date importDrinkDate, String invoiceNumber, int userId, double total, boolean status) {
		super();
		this.id = id;
		this.importDrinkDate = importDrinkDate;
		this.invoiceNumber = invoiceNumber;
		this.userId = userId;
		this.total = total;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getImportDrinkDate() {
		return importDrinkDate;
	}

	public void setImportDrinkDate(Date importDrinkDate) {
		this.importDrinkDate = importDrinkDate;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
