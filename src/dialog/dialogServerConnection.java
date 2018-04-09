package dialog;

import java.awt.BorderLayout;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connection.DbConnection;
import controlclasses.ColorModel;
import form.formMain;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import java.awt.Color;

public class dialogServerConnection extends JDialog implements ActionListener, ItemListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JComboBox<String> cboServerName;
	private JComboBox<String> cboDatabaseName;
	private JButton btnConnect,btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialogServerConnection dialog = new dialogServerConnection();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);	
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	ColorModel mColor = new ColorModel();
	/**
	 * Create the dialog.
	 */
	public dialogServerConnection() {
		
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
		
		setAlwaysOnTop(true);
		setResizable(false);
		setBounds(100, 100, 398, 286);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(mColor.getBackColor());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblServerName = new JLabel("Server Name / IP");
		lblServerName.setForeground(Color.WHITE);
		lblServerName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblServerName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblServerName.setBounds(30, 69, 137, 14);
		contentPanel.add(lblServerName);
		
		cboServerName = new JComboBox();
		cboServerName.setBackground(Color.WHITE);
		cboServerName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboServerName.setEditable(true);
		cboServerName.setBounds(177, 65, 167, 24);
		contentPanel.add(cboServerName);
		{
			JLabel lblDatabaseName = new JLabel("Database Name");
			lblDatabaseName.setForeground(Color.WHITE);
			lblDatabaseName.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDatabaseName.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblDatabaseName.setBounds(30, 102, 137, 14);
			contentPanel.add(lblDatabaseName);
		}
		{
			cboDatabaseName = new JComboBox();
			cboDatabaseName.setBackground(Color.WHITE);
			cboDatabaseName.setFont(new Font("Tahoma", Font.PLAIN, 12));
			cboDatabaseName.setEditable(true);
			cboDatabaseName.setBounds(177, 98, 167, 24);
			contentPanel.add(cboDatabaseName);
		}
		{
			txtUsername = new JTextField();
			txtUsername.setBackground(Color.WHITE);
			txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txtUsername.setBounds(177, 132, 167, 24);
			contentPanel.add(txtUsername);
			txtUsername.setColumns(10);
		}
		{
			JLabel lblUsername = new JLabel("Username");
			lblUsername.setForeground(Color.WHITE);
			lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
			lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblUsername.setBounds(30, 136, 137, 14);
			contentPanel.add(lblUsername);
		}
		{
			JLabel label = new JLabel("Database Name");
			label.setForeground(Color.WHITE);
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("Tahoma", Font.PLAIN, 15));
			label.setBounds(30, 168, 137, 14);
			contentPanel.add(label);
		}
		
		txtPassword = new JPasswordField();
		txtPassword.setBackground(Color.WHITE);
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPassword.setBounds(178, 165, 167, 24);
		contentPanel.add(txtPassword);
		
		JLabel lblServerConnection = new JLabel("Connect to Server");
		lblServerConnection.setHorizontalTextPosition(SwingConstants.CENTER);
		lblServerConnection.setForeground(Color.WHITE);
		lblServerConnection.setHorizontalAlignment(SwingConstants.LEFT);
		lblServerConnection.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblServerConnection.setBounds(43, 11, 197, 37);
		contentPanel.add(lblServerConnection);
		{
			btnCancel = new JButton("Cancel");
			btnCancel.setBounds(250, 208, 94, 23);
			contentPanel.add(btnCancel);
			btnCancel.setActionCommand("Cancel");
		}
		{
			btnConnect = new JButton("Connect");
			btnConnect.setBounds(146, 208, 94, 23);
			contentPanel.add(btnConnect);
			btnConnect.setActionCommand("OK");
			getRootPane().setDefaultButton(btnConnect);
		}
		
		JPanel panel = new JPanel();
		panel.setBounds(45, 49, 308, 2);
		contentPanel.add(panel);
				
		loadServerNameToComboBox();
		loadDatabaseNameToComboBox();
		registerEvent();
		
		txtUsername.setText("root");
	}
	
	private void registerEvent() {
		btnConnect.addActionListener(this);
		btnCancel.addActionListener(this);
		
		cboServerName.addItemListener(this);
	}
	
	private void loadDatabaseNameToComboBox() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/","root","");
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
			
			/*Test Static Connection*/
			//Connection dbcon = DbConnection.getConnection();
			
			/*Test Dynamic Connection*/
			Boolean connection =  DbConnection.createConnection(cboServerName.getSelectedItem().toString(), cboDatabaseName.getSelectedItem().toString(), txtUsername.getText(), txtPassword.getText());
			if(connection) {
				formMain main = new formMain();
				main.setVisible(true);
				this.dispose();
			}
		}else if(e.getSource() == btnCancel){
			System.exit(0);
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
	
	
}
