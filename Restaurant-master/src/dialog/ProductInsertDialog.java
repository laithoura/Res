package dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;
import connection.*;
public class ProductInsertDialog extends JDialog {
	
	private Connection con = null;
	private PreparedStatement pst = null;
	private Statement stType = null;
	private ResultSet rsType = null;

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtUnitPrice;
	private JComboBox cboType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ProductInsertDialog dialog = new ProductInsertDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ProductInsertDialog() {
		con = DbConnection.getConnection();
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Insert product");
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
			lblNewLabel.setBounds(10, 11, 121, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Name");
			lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(10, 50, 326, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblType = new JLabel("Type");
			lblType.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			lblType.setBounds(10, 89, 326, 14);
			contentPanel.add(lblType);
		}
		{
			JLabel lblUnitPrice = new JLabel("Unit price");
			lblUnitPrice.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			lblUnitPrice.setBounds(10, 131, 326, 14);
			contentPanel.add(lblUnitPrice);
		}
		
		txtName = new JTextField();
		txtName.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtName.setBounds(108, 47, 228, 20);
		contentPanel.add(txtName);
		txtName.setColumns(10);
		{
			txtUnitPrice = new JTextField();
			txtUnitPrice.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			txtUnitPrice.setColumns(10);
			txtUnitPrice.setBounds(108, 128, 228, 20);
			contentPanel.add(txtUnitPrice);
		}
		
		cboType = new JComboBox();
		cboType.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cboType.setBounds(108, 86, 228, 20);
		try {
			stType = con.createStatement();
			rsType = stType.executeQuery("select id, name from type where category = \"Product\"");
			while(rsType.next()) {
				cboType.addItem(rsType.getInt("id"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		
		contentPanel.add(cboType);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSave = new JButton("Save");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						try {
							String name = txtName.getText().toString();
							System.out.println(name);
							int type = Integer.parseInt(cboType.getSelectedItem().toString());
							double unitPrice = Double.parseDouble(txtUnitPrice.getText());
							
							pst = con.prepareStatement("insert into product(name, type, unit_price, status) values(?,?,?,true);");
							pst.setString(1, name);
							pst.setInt(2, type);
							pst.setDouble(3, unitPrice);
							if(pst.executeUpdate() > 0) {
								JOptionPane.showMessageDialog(null, "Inserted successfully!");
							}
						}catch(SQLException e) {
							JOptionPane.showMessageDialog(null, "Inserted unsuccessfully!");
							e.printStackTrace();
						}			
					}
					
				});
				btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				btnSave.setActionCommand("OK");
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						txtName.setText("");
						txtUnitPrice.setText("");						
					}					
				});
			}
		}
	}
}
