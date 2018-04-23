package instance_classes;

public class RawMaterial {
	private int id;
	private String name;
	private Type type;
	private String description;
	private boolean status;
	
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
	
	public void setTpe(Type type) {
		this.type = type;
	}
	
	public String getDescriptioin() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean getStatus() {
		return this.status;			
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public RawMaterial() {
		
	}
	
	public RawMaterial(int id, String name, Type type, String description, boolean status) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.status = status;
	}
	

}
