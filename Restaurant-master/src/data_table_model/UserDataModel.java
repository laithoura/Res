package data_table_model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import instance_classes.User;

public class UserDataModel extends AbstractTableModel{

	private final String COLUMNS[] = new String[] {"User ID", "Username","Role"};
	private ArrayList<User> userList = new ArrayList<User>();

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public int getRowCount() {
		return userList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		
		User user = userList.get(rowIndex);
		switch (colIndex) {
			case 0: return user.getId();
			case 1: return user.getUsername();
			case 2: return user.getRole();
			default: return null;		
		}
	}
	
	
	@Override
	public String getColumnName(int colIndex) {	
		return COLUMNS[colIndex];
	}
	
	public void updateTable() {
		fireTableDataChanged();
	}
	
	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}

}
