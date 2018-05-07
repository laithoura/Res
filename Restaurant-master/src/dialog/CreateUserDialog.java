package dialog;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import control_classes.Help;
import control_classes.MessageShow;
import controller.UserDao;
import form.LoginForm;
import instance_classes.User;
import interfaces.CallBackListenter;
import javax.swing.JPasswordField;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class CreateUserDialog extends JDialog{
	
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> cboRole;
	private JPasswordField txtPassword;
	private JTextField txtUsername;
	private CallBackListenter backListener;
	
	
	public void setCallBackListener(CallBackListenter backListener) {
		this.backListener = backListener;
	}
	
	
	public CreateUserDialog() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginForm.class.getResource("/resources/Flora.logo.png")));
		
		setResizable(false);
		setTitle("Create User Account");		
		setBounds(100, 100, 425, 278);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		{
			JLabel lblNewLabel = new JLabel("Create User Account");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblNewLabel.setBounds(121, 11, 227, 28);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Username");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_1.setBounds(34, 58, 75, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblPassword = new JLabel("Password");
			lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPassword.setBounds(34, 97, 75, 14);
			contentPanel.add(lblPassword);
		}
		{
			JLabel lblRole = new JLabel("Role");
			lblRole.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblRole.setBounds(34, 139, 75, 14);
			contentPanel.add(lblRole);
		}
		
		
		cboRole = new JComboBox<String>();
		cboRole.addItem("Admin");
		cboRole.addItem("Import Manager");		
		cboRole.addItem("Cashier");
		cboRole.addItem("Service");
		cboRole.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboRole.setBounds(121, 136, 228, 24);
		contentPanel.add(cboRole);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtPassword.setBounds(121, 94, 228, 24);
		contentPanel.add(txtPassword);
		{
			JButton btnCancel = new JButton("Cancel");
			btnCancel.setBounds(240, 181, 108, 32);
			contentPanel.add(btnCancel);
			btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnCancel.setActionCommand("Cancel");
			{
				JButton btnCreate = new JButton("Create");
				btnCreate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String username = txtUsername.getText();
						String password=txtPassword.getText();
						String role = cboRole.getSelectedItem().toString();
									
						if(username.isEmpty() || password.isEmpty() || cboRole.getSelectedIndex() == -1) {
							MessageShow.Error("Please fill all required field.", "Validation");
							return;
						}
						
						UserDao userdao = new UserDao();
						
						if(userdao.isExistUsername(username)) {
							MessageShow.Error("The username is already exist!", "Exist");
							return;
						}
						
						User user = new User(0,username, password, role, true);
						
						if (userdao.insertUser(user)) {
							user.setId(Help.getLastAutoIncrement("restaurant_project", "user"));
							MessageShow.success("User was created successfully!", "Create User");
							backListener.CallBack(user);
							clearConrol();
						} else {
							MessageShow.success("User was created unsuccessfully!", "Create User");
						}	
						
					}
				});
				btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnCreate.setMinimumSize(new Dimension(65, 23));
				btnCreate.setMaximumSize(new Dimension(65, 23));
				btnCreate.setBounds(120, 182, 108, 32);
				contentPanel.add(btnCreate);
			}
			{
				txtUsername = new JTextField();
				txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
				txtUsername.setColumns(10);
				txtUsername.setBackground(Color.WHITE);
				txtUsername.setBounds(121, 53, 227, 24);
				contentPanel.add(txtUsername);
			}
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtUsername.setText("");
					txtPassword.setText("");
				}					
			});			
		}		
	}// Constructor
	
	private void clearConrol() {
		txtUsername.setText("");
		txtPassword.setText("");
		cboRole.setSelectedIndex(-1);
	}
	
}
