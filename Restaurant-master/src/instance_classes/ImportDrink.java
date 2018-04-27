package instance_classes;

import java.util.Date;

public class ImportDrink {
	private int id;
	private Date importDrinkDate;
	private String invoiceNumber;
	private User user;
	private double total;
	private boolean status;
	
	public ImportDrink() {
		
	}
	
	public ImportDrink(int id, Date importDrinkDate, String invoiceNumber, User user, double total, boolean status) {
		super();
		this.id = id;
		this.importDrinkDate = importDrinkDate;
		this.invoiceNumber = invoiceNumber;
		this.user = user;
		this.total = total;
		this.status = status;
	}

	public int getId() {
		return this.id;
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

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getInvoniceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public double getTotal() {
		return this.total;
	}
	
	public void setTotal(double total) {
		this. total = total;
	}
	
	public boolean getStatus() {
		return this.status;
	}
	
	public void setStaus(boolean status) {
		this.status = status;
	}
}
