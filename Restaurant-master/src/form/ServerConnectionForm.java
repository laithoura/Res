package form;

import java.awt.BorderLayout;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connection.DbConnection;
import control_classes.ColorModel;
import controller.UserDao;
import dialog.CreateUserDialog;
import form.LoginForm;
import instance_classes.ServerConnection;
import instance_classes.User;
import interfaces.CallBackListenter;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;

public final class ServerConnectionForm extends JFrame implements ActionListener, ItemListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JComboBox<String> cboServerName;
	private JComboBox<String> cboDatabaseName;
	private JButton btnConnect,btnCancel;
	private JCheckBox checkBoxRemember;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			ServerConnectionForm dialog = new ServerConnectionForm();
			
			ServerConnection[] servers =  dialog.readServerConnection();
			if(servers != null) {
				dialog.lockToApplication(servers);
			}else {
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);	
				dialog.setLocationRelativeTo(null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	ColorModel mColor = new ColorModel();
	/**
	 * Create the dialog.
	 */
	public ServerConnectionForm() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginForm.class.getResource("/resources/Flora.logo.png")));

		setAlwaysOnTop(true);
		setResizable(false);
		setBounds(100, 100, 451, 293);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(mColor.getBackColor());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblServerName = new JLabel("Server Name / IP");
		lblServerName.setForeground(Color.WHITE);
		lblServerName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblServerName.setHorizontalAlignment(SwingConstants.LEFT);
		lblServerName.setBounds(41, 70, 126, 14);
		contentPanel.add(lblServerName);
		
		cboServerName = new JComboBox();
		cboServerName.setBackground(Color.WHITE);
		cboServerName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboServerName.setEditable(true);
		cboServerName.setBounds(165, 65, 240, 24);
		contentPanel.add(cboServerName);
		{
			JLabel lblDatabaseName = new JLabel("Database Name");
			lblDatabaseName.setForeground(Color.WHITE);
			lblDatabaseName.setHorizontalAlignment(SwingConstants.LEFT);
			lblDatabaseName.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblDatabaseName.setBounds(41, 103, 126, 14);
			contentPanel.add(lblDatabaseName);
		}
		{
			cboDatabaseName = new JComboBox();
			cboDatabaseName.setBackground(Color.WHITE);
			cboDatabaseName.setFont(new Font("Tahoma", Font.PLAIN, 12));
			cboDatabaseName.setEditable(true);
			cboDatabaseName.setBounds(165, 98, 240, 24);
			contentPanel.add(cboDatabaseName);
		}
		{
			txtUsername = new JTextField();
			txtUsername.setBackground(Color.WHITE);
			txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txtUsername.setBounds(165, 132, 240, 24);
			contentPanel.add(txtUsername);
			txtUsername.setColumns(10);
		}
		{
			JLabel lblUsername = new JLabel("Username");
			lblUsername.setForeground(Color.WHITE);
			lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
			lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblUsername.setBounds(41, 137, 126, 14);
			contentPanel.add(lblUsername);
		}
		{
			JLabel lblPassword = new JLabel("Password");
			lblPassword.setForeground(Color.WHITE);
			lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
			lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPassword.setBounds(41, 170, 126, 14);
			contentPanel.add(lblPassword);
		}
		
		txtPassword = new JPasswordField();
		txtPassword.setBackground(Color.WHITE);
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPassword.setBounds(165, 165, 240, 24);
		contentPanel.add(txtPassword);
		
		JLabel lblServerConnection = new JLabel("Connect to Server");
		lblServerConnection.setHorizontalAlignment(SwingConstants.LEFT);
		lblServerConnection.setIcon(new ImageIcon(ServerConnectionForm.class.getResource("/resources/Connection_32.png")));
		lblServerConnection.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblServerConnection.setForeground(Color.WHITE);
		lblServerConnection.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblServerConnection.setBounds(39, 11, 260, 37);
		contentPanel.add(lblServerConnection);
		{
			btnCancel = new JButton("Cancel");
			btnCancel.setBounds(311, 211, 94, 23);
			contentPanel.add(btnCancel);
			btnCancel.setActionCommand("Cancel");
		}
		{
			btnConnect = new JButton("Connect");
			btnConnect.setBounds(207, 211, 94, 23);
			contentPanel.add(btnConnect);
			btnConnect.setActionCommand("OK");
			getRootPane().setDefaultButton(btnConnect);
		}
		
		JPanel panel = new JPanel();
		panel.setBounds(41, 49, 376, 2);
		contentPanel.add(panel);
				
		loadServerNameToComboBox();
		loadDatabaseNameToComboBox();
		registerEvent();
		
		txtUsername.setText("root");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(41, 200, 377, 2);
		contentPanel.add(panel_1);
		
		checkBoxRemember = new JCheckBox("Remember Connection");
		checkBoxRemember.setFont(new Font("Tahoma", Font.PLAIN, 13));
		checkBoxRemember.setForeground(Color.WHITE);
		checkBoxRemember.setBackground(Color.BLACK);
		checkBoxRemember.setBounds(40, 211, 161, 23);
		contentPanel.add(checkBoxRemember);		
	}
	
	private void registerEvent() {
		btnConnect.addActionListener(this);
		btnCancel.addActionListener(this);
		
		cboServerName.addItemListener(this);
	}
	
	private void loadDatabaseNameToComboBox() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/?useSSL=false","root","");
			ResultSet result = con.getMetaData().getCatalogs();
			while(result.next()) {
				cboDatabaseName.addItem(result.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void loadServerNameToComboBox() {
		try {           
			cboServerName.addItem("localhost");
            cboServerName.addItem("127.0.0.1");
            
            InetAddress myHost = InetAddress.getLocalHost();
	        cboServerName.addItem(myHost.toString());             
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnConnect) {
			
			ServerConnection[] serverConnection = new ServerConnection[] {new ServerConnection(cboServerName.getSelectedItem().toString(), cboDatabaseName.getSelectedItem().toString(), txtUsername.getText(), txtPassword.getText())};
			lockToApplication(serverConnection);
			
		}else if(e.getSource() == btnCancel){
			System.exit(0);
		}
	}
	
	private void lockToApplication(ServerConnection[] serverConnections) {
		/*Test Dynamic Connection*/
		
		ServerConnection server = serverConnections[0];
		Boolean connection =  DbConnection.createConnection(server.getServerName(),server.getDatabaseName(), server.getUsername(), server.getPassword());
		if(connection) {
			if(checkBoxRemember.isSelected()) {
				saveServerConnection(serverConnections);
			}
			
			UserDao userDao = new UserDao();
			if(!userDao.isExistUserAccount()) {			
				/*If user account is not exist in db => user have to create it first then login into Application*/
				CreateUserDialog createUserDialog = new CreateUserDialog();
				createUserDialog.setCallBackListener(new CallBackListenter() {
					
					@Override
					public void CallBack(Object sender) {
						DbConnection.user = (User) sender;
						
						createUserDialog.setVisible(false);
						createUserDialog.dispose();

						/*After creating New User Account => Navigate to Login Form to Login Again*/
						if(DbConnection.user != null) {
							dispose();
							LoginForm loginForm = new LoginForm();
							loginForm.setVisible(true);						
						}else {
							return;
						}
					}
				});
				createUserDialog.setVisible(true);								
			}else{
				/*If User Account is exist in DB Table => Navigate to Login Form*/
				this.dispose();
				LoginForm loginForm = new LoginForm();
				loginForm.setVisible(true);		
			}
			
			
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == cboServerName) {
			if((cboServerName.getSelectedItem().toString() != "localhost" && cboServerName.getSelectedItem().toString() != "127.0.0.1") && cboServerName.getSelectedItem() != "" ) {
				cboDatabaseName.removeAllItems();	
				txtUsername.setText("");
			}else {
				if(cboDatabaseName.getItemCount() ==0 ) {
					loadDatabaseNameToComboBox();
					System.out.println("Load");
				}
				txtUsername.setText("root");
			}
		}		
	}
	
	private ServerConnection[] readServerConnection() {
		  
		  ObjectInputStream objectInputStream = null;
		    ServerConnection[] serverConnection = null;
			try {
				objectInputStream = new ObjectInputStream(new FileInputStream("lib/DatabaseConnection.dat"));
				serverConnection = (ServerConnection[]) (objectInputStream.readObject());
				
			} catch (FileNotFoundException ex) {
				//ex.printStackTrace();
			} catch (IOException ex) {
				//ex.printStackTrace();
			}catch(ClassNotFoundException ex) {	
				//ex.printStackTrace();
			}finally {
				try {
					objectInputStream.close();
				} catch (NullPointerException |IOException ex) {
					//ex.printStackTrace();
				}
			}
			return serverConnection;
	  }
	  
	  public void saveServerConnection(ServerConnection[] serverConnection) {
		  
		  ObjectOutputStream objectOutputStream = null;
			try {
				objectOutputStream = new ObjectOutputStream(new FileOutputStream("lib/DatabaseConnection.dat"));				
				objectOutputStream.writeObject(serverConnection);
			} catch (FileNotFoundException e1) {				
				//e1.printStackTrace();
			} catch (IOException e1) {
				//e1.printStackTrace();
			}finally {
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}
	  }
	
}
