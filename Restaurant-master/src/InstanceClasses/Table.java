package InstanceClasses;

public class Table {
	private int id;
	private String name;
	private Type type;
	private int tableCount;
	private String description;
	
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
	
	public int getTableCount() {
		return tableCount;
	}
	public void setTableCount(int tableCount) {
		this.tableCount = tableCount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Table() {
		
	}
	
	public Table(int id, String name, Type type, int tableCount, String description) {		
		this.id = id;
		this.name = name;
		this.type = type;
		this.tableCount = tableCount;
		this.description = description;
	}
		
}
