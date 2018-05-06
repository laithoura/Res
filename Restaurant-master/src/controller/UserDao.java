package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import connection.DbConnection;
import instance_classes.Table;
import instance_classes.User;

public class UserDao {

	private PreparedStatement prepareStatement;
	private ResultSet resultSet;
	
	public boolean insertUser(User user) {
		boolean success = false;
		try {
			prepareStatement = DbConnection.dbConnection.prepareStatement("INSERT INTO user (username, password, role, status) VALUES(?,?,?,true)"); 
			prepareStatement.setString(1, user.getUsername());
			prepareStatement.setString(2, user.getPassword());
			prepareStatement.setString(3, user.getRole());
			
			if (prepareStatement.executeUpdate() > 0) {
				success = true;
			}
			
		}catch (SQLException ex) {
			ex.printStackTrace();
			success = false;
		}finally {
			try {
				prepareStatement.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
		
		return success;	
	}

	public boolean updateUser(User user) {
		boolean success = false;
		try {
			prepareStatement = DbConnection.getConnection().prepareStatement("UPDATE  user set username = ?, password = ?, role = ? where id = ?"); 
			prepareStatement.setString(1, user.getUsername());
			prepareStatement.setString(2, user.getPassword());
			prepareStatement.setString(3, user.getRole());
			prepareStatement.setInt(4, user.getId());
			
			if (prepareStatement.executeUpdate() > 0) {
				success = true;
			}
			
		}catch (SQLException ex) {
			ex.printStackTrace();
			success = false;
		}finally {
			try {
				prepareStatement.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
		
		return success;	
	}
	
	
	public boolean deleteUser(int userId) {		
		boolean success = false;
		try {			
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("UPDATE user SET status = false WHERE id = ?");			
			prepareStatement.setInt(1, userId);			
			if(prepareStatement.executeUpdate() > 0) {
				success = true;
			}
		} catch (SQLException e) {		
			e.printStackTrace();
			success = false;
		}finally {
			try {
				prepareStatement.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
		return success;
	}
	
	
	public ArrayList<User> getUserLists(boolean status){
		ArrayList<User> userList = new ArrayList<>();
		try {			
			prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT * FROM user WHERE status = ?");						
			prepareStatement.setBoolean(1, status);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				userList.add(new User(resultSet.getInt("id"),
											resultSet.getString("username"),
											resultSet.getString("password"),
											resultSet.getString("role"),
											resultSet.getBoolean("status")			
						));
			}
		} catch (SQLException e) {		
			e.printStackTrace();	
		}finally {
			try {
				prepareStatement.close();
				resultSet.close();
			} catch (Exception e) {			
				e.printStackTrace();
			}
		}
		return userList;
		}
	
		
		public boolean isExistUsername(String username) {				
			boolean exist = false;
			try {
				prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT username FROM user WHERE username = ?");
				prepareStatement.setString(1, username);
				resultSet = prepareStatement.executeQuery();
				while(resultSet.next()) {
					if(username.equals(resultSet.getString(1))) {
						exist = true;
						break;
					}
				}
			} catch (SQLException e) {			
				e.printStackTrace();
			}finally {
				try {
					prepareStatement.close();
					resultSet.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return exist;
		}
		
		public User compareLogin(String username, String password) {
			
			User user = null;
			
				try {
					prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
					prepareStatement.setString(1, username);
					prepareStatement.setString(2, password);
					
					resultSet = prepareStatement.executeQuery();
					while(resultSet.next()) {
						
						user = new User(resultSet.getInt("id"),
								resultSet.getString("username"),
								resultSet.getString("password"),
								resultSet.getString("role"),
								resultSet.getBoolean("status"));
								break;
					}
				} catch (SQLException e) {			
					e.printStackTrace();
					user = null;
				}finally {
					try {
						prepareStatement.close();
						resultSet.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}			
			return user;			
		}
		
		public boolean isExistUserAccount() {				
			boolean exist = false;
			try {
				prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT COUNT(*) FROM user WHERE status = ?");
				prepareStatement.setBoolean(1, true);
				resultSet = prepareStatement.executeQuery();
				resultSet.next();
				int rowCount = resultSet.getInt(1);
				if(rowCount > 0) {
					exist = true;
				}
			} catch (SQLException e) {			
				e.printStackTrace();
			}finally {
				try {
					prepareStatement.close();
					resultSet.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return exist;
		}
		
		
		public ArrayList<User> searchUser(String condition,boolean status){
			
			ArrayList<User> userList = new ArrayList<>();
			try {			
				prepareStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT * FROM user WHERE id = ? OR username LIKE ? OR role LIKE ? AND status = ?");
				
				int userId = 0;
				try {
					userId = Integer.parseInt(condition);
				} catch (NumberFormatException e) {
					userId = 0;
				}
				
				prepareStatement.setInt(1, userId);
				prepareStatement.setString(2, "%" + condition + "%");			
				prepareStatement.setString(3, "%" + condition + "%");			
				prepareStatement.setBoolean(4, status);	
				resultSet = prepareStatement.executeQuery();
				
				while(resultSet.next()) {
					userList.add(new User(resultSet.getInt("id"),
							resultSet.getString("username"),
							resultSet.getString("password"),
							resultSet.getString("role"),
							resultSet.getBoolean("status")	
							));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					prepareStatement.close();
					resultSet.close();
				} catch (Exception e) {				
					e.printStackTrace();
				}
			}
			return userList;
		}
	
}
	
		
		
