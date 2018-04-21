package instance_classes;

public class Table {
	private int id;
	private String name;
	private Type type;
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
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
		
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Table() {
		
	}
	
	public Table(int id, String name, instance_classes.Type type2, boolean status) {
		super();
		this.id = id;
		this.name = name;
		this.type = type2;
		this.status = status;
	}
	
}
