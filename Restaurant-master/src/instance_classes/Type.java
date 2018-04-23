package instance_classes;

public class Type {
	private int id;
	private String name;
	private String category;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public boolean getStatus() {
		return this.status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public Type() {
		
	}
	
	public Type(int id, String name, String description, boolean status) {
		this.id = id;
		this.name = name;
		this.category = description;
		this.status = status;
	}
	
	
}
