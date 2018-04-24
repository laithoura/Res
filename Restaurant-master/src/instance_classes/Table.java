package instance_classes;

public class Table {
	private int id;
	private String name;
	private String type;
	private boolean available;
	private boolean status;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
		
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Table() {
		
	}
	
	public Table(int id, String name, String type,boolean available, boolean status) {		
		this.id = id;
		this.name = name;
		this.type = type;
		this.available = available;
		this.status = status;
	}
}
