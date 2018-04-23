package dialog;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connection.DbConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;

public class RawMaterialInsertDialog extends JDialog {
	
	private Connection con = null;
	private PreparedStatement pst = null;
	private Statement stType = null;
	private ResultSet rs = null;

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtDescription;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RawMaterialInsertDialog dialog = new RawMaterialInsertDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RawMaterialInsertDialog() {
		
		con = DbConnection.getConnection();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblInsertRawMaterail = new JLabel("Insert raw materail");
			lblInsertRawMaterail.setFont(new Font("Times New Roman", Font.BOLD, 13));
			lblInsertRawMaterail.setBounds(10, 11, 187, 14);
			contentPanel.add(lblInsertRawMaterail);
		}
		{
			JLabel lblNewLabel = new JLabel("Name");
			lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			lblNewLabel.setBounds(10, 52, 123, 20);
			contentPanel.add(lblNewLabel);
		}
		
		JComboBox cboType = new JComboBox( );
		cboType.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cboType.setBounds(143, 100, 181, 20);
		contentPanel.add(cboType);
		try {
			stType = con.createStatement();
			rs = stType.executeQuery("select * from type where category=\"Raw material\" && status = true");
			while(rs.next()) {
				cboType.addItem(Integer.parseInt(rs.getString("id")));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		txtName = new JTextField();
		txtName.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtName.setBounds(143, 52, 184, 20);
		contentPanel.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblType.setBounds(10, 100, 123, 20);
		contentPanel.add(lblType);
		
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblDescription.setBounds(10, 144, 123, 20);
		contentPanel.add(lblDescription);
		
		txtDescription = new JTextField();
		txtDescription.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtDescription.setColumns(10);
		txtDescription.setBounds(143, 144, 184, 20);
		contentPanel.add(txtDescription);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSave = new JButton("Save");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						try {
							String name = txtName.getText();
							int type = Integer.parseInt(cboType.getSelectedItem().toString());
							String description = txtDescription.getText(); 
							String sql = "insert into raw_material (name, type, description, status) value (?,?,?, true);";
							pst = con.prepareStatement(sql);
							pst.setString(1, name);
							pst.setInt(2, type);
							pst.setString(3, description);
							if (pst.executeUpdate() > 0) {
								JOptionPane.showMessageDialog(null, "Inserted succesfully!");
							}
						}catch (SQLException e) {
							JOptionPane.showMessageDialog(null, "Inserted unsuccesfully!");
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
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						txtName.setText("");
						txtDescription.setText("");
						
					}
					
				});
				btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
	}
}
