package panel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import control_classes.Exporter;
import control_classes.MessageShow;
import control_classes.TableSetting;
import controller.UserDao;
import data_table_model.UserDataModel;
import dialog.CreateUserDialog;
import dialog.UpdateUserDialog;
import instance_classes.User;
import interfaces.CallBackListenter;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserAccountPanel extends JPanel implements ActionListener{
	
	ArrayList<User> userList = null;	
	private JTable tableUser;
	private JButton btnSignUp;
	private JButton btnUpdate;
	private JButton btnDelete;
	private int selectedIndex = -1;
	private UserDataModel userDataModel;
	private JTextField txtSearch;
	private UserDao userDao = new UserDao();
	private JButton btnExport;
	
	/**
	 * Create the panel.
	*/ 
	public UserAccountPanel() {
		
		JPanel panelTable = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		
		tableUser = new JTable();
		userDataModel = new UserDataModel();
		
		userList = new ArrayList<User>();		
		
		userList= userDao.getUserLists(true);
		
		userDataModel.setUserList(userList);
		
		tableUser.setModel(userDataModel);
		
		scrollPane.setViewportView(tableUser);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(selectedIndex == -1) {
					MessageShow.Error("Please select any user to delete", "Delele User");	
					return;
				}
				int userId = userList.get(selectedIndex).getId(); 
				
				int choose = MessageShow.deleteVerification("Do you want to delete user ID = " + userId + " ?", "Delete User");
				
				if(choose == 0) {
					
					if (userDao.deleteUser(userId)) {
						
						MessageShow.success("User was deleted successfully", "Delete User");
						userList.remove(selectedIndex);												
						userDataModel.updateTable();
						
					} else {
						MessageShow.Error("User was deleted unsuccessfully", "Delete User");										
					}
					selectedIndex = -1;
				}				
			}
		});
		btnDelete.setIcon(new ImageIcon(ProductPanel.class.getResource("/resources/Cancel_20.png")));
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		btnUpdate = new JButton("Update");
		btnUpdate.setIcon(new ImageIcon(ProductPanel.class.getResource("/resources/Edit_20.png")));
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnUpdate.addActionListener(this);
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				CreateUserDialog createUserDialog = new CreateUserDialog();
				createUserDialog.setCallBackListener(new CallBackListenter() {
					
					@Override
					public void CallBack(Object sender) {
						if(sender == null) return;
						userList.add((User)sender);
						userDataModel.updateTable();												
					}
				});
				createUserDialog.setVisible(true);			
			}
		});
		
		btnSignUp.setIcon(new ImageIcon(ProductPanel.class.getResource("/resources/Add_20.png")));
		btnSignUp.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					searchUser();				
				}
			}			
		});
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSearch.setColumns(10);
		txtSearch.setBackground(Color.WHITE);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchUser();
			}
		});
		lblNewLabel.setToolTipText("Search");
		lblNewLabel.setIcon(new ImageIcon(UserAccountPanel.class.getResource("/resources/Search_20.png")));
		
		JButton buttonRefresh = new JButton("Refresh");
		buttonRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {							
				txtSearch.setText("");
				refreshTableUser();
			}
		});
		buttonRefresh.setIcon(new ImageIcon(UserAccountPanel.class.getResource("/resources/Refresh_20.png")));
		buttonRefresh.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		btnExport = new JButton("Export");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Exporter.jtableToExcel(tableUser);
			}
			
		});
		btnExport.setIcon(new ImageIcon(UserAccountPanel.class.getResource("/resources/Excel_20.png")));
		btnExport.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelTable, GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
							.addComponent(buttonRefresh, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSignUp)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDelete)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExport, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addGap(5))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnDelete)
							.addComponent(btnUpdate)
							.addComponent(btnSignUp)
							.addComponent(btnExport, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addComponent(buttonRefresh, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addComponent(panelTable, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
					.addContainerGap())
		);
		GroupLayout gl_pnlTable = new GroupLayout(panelTable);
		gl_pnlTable.setHorizontalGroup(
			gl_pnlTable.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
		);
		gl_pnlTable.setVerticalGroup(
			gl_pnlTable.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
		);
		panelTable.setLayout(gl_pnlTable);
		setLayout(groupLayout);
		
		tableUser.getSelectionModel().addListSelectionListener(new RowListener());
		
		controlTable();
	}
	class RowListener implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting()) return;
			selectedIndex = tableUser.getSelectedRow();
		}
	}

	private void controlTable() {
		
		TableSetting.TableControl(tableUser);
		TableSetting.alignColumnToCenter(tableUser, 0);
		TableSetting.alignColumnToCenter(tableUser, 2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnUpdate) {
			
			if(selectedIndex == -1) {
				MessageShow.Error("Please select any user to edit", "Update User");	
				return;
			}
			
			User user = null;
			if (userList.size() > 0) {
				user = userList.get(selectedIndex);
			}
				
			UpdateUserDialog updateUserDialog = new UpdateUserDialog(user);	
			
			updateUserDialog.setCallBackListener(new CallBackListenter() {
				
				@Override
				public void CallBack(Object sender) {
					
					userList.set(selectedIndex, (User)sender);

					/*Update the Edited Record in User Table Model*/
					userDataModel.fireTableRowsUpdated(selectedIndex, selectedIndex);

					/*Close Update Dialog*/
					updateUserDialog.setVisible(false);
					updateUserDialog.dispose();					
					selectedIndex = -1;
				}
			});
			updateUserDialog.setVisible(true);				
		}		
	}
	
	
	private void refreshTableUser() {				
		userList = userDao.getUserLists(true);
		//userDataModel.setUserList(userList);
		userDataModel.updateTable();
	}

	
	private void searchUser() {
		String search = txtSearch.getText().trim();
		
		if(search.isEmpty() || search.equals("")) {
			
			refreshTableUser();
		}else {
			userList = userDao.searchUser(search, true);
			userDataModel.setUserList(userList);
			userDataModel.updateTable();
		}					
	}			
	
}