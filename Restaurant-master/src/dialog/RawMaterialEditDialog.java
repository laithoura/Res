package dialog;

import java.awt.BorderLayout;
import instance_classes.*;
import connection.*;
import interfaces.CallBackListenter;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
public class RawMaterialEditDialog extends JDialog {
	private Connection con = null;
	private Statement stType = null;
	private ResultSet rsType = null;
	private PreparedStatement pstUpdate = null;
	

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtDescription;
	private JComboBox cboType;
	private RawMaterial rawMateiral = null;
	private JTextField txtId; 
	private CallBackListenter backListener;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RawMaterialEditDialog dialog = new RawMaterialEditDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public RawMaterialEditDialog(RawMaterial rawMaterial) {
		this();
		this.rawMateiral = rawMaterial;	
		
		txtId = new JTextField();
		txtName.setText(rawMaterial.getName());
		txtDescription.setText(rawMaterial.getDescriptioin());
		cboType.setSelectedItem(rawMaterial.getType().getId());
		txtId.setText(rawMaterial.getId() + "");
		txtId.setVisible(false);
		
	}
	
	public void setCallBackListener(CallBackListenter backListener) {
		this.backListener = backListener;
	}

	/**
	 * Create the dialog.
	 */
	public RawMaterialEditDialog() {
		
		con = DbConnection.getConnection();

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Edit raw material");
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
			lblNewLabel.setBounds(10, 11, 155, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblName = new JLabel("Name");
			lblName.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			lblName.setBounds(10, 47, 46, 14);
			contentPanel.add(lblName);
		}
		{
			JLabel lblType = new JLabel("Type");
			lblType.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			lblType.setBounds(10, 87, 46, 14);
			contentPanel.add(lblType);
		}
		{
			JLabel lblDescription = new JLabel("Description");
			lblDescription.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			lblDescription.setBounds(10, 133, 88, 14);
			contentPanel.add(lblDescription);
		}
		
		txtName = new JTextField();
		txtName.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtName.setBounds(107, 44, 167, 20);
		contentPanel.add(txtName);
		txtName.setColumns(10);
		
		txtDescription = new JTextField();
		txtDescription.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtDescription.setColumns(10);
		txtDescription.setBounds(108, 130, 167, 50);
		contentPanel.add(txtDescription);
		
		cboType = new JComboBox();
		try {
			stType = con.createStatement();
			rsType = stType.executeQuery("select * from type where category = \"Raw material\" && status = true");
			while(rsType.next()) {
				cboType.addItem(rsType.getInt("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cboType.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cboType.setBounds(107, 84, 167, 20);
		contentPanel.add(cboType);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnUpdate = new JButton("Update");
				btnUpdate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String id = txtId.getText();
						String name = txtName.getText();
						int typeId = Integer.parseInt(cboType.getSelectedItem().toString());
						String description = txtDescription.getText();
						
						try {
							pstUpdate = con.prepareStatement("update raw_material set name = ?, type = ?, description = ? where id = ?");
							pstUpdate.setString(1, name);
							pstUpdate.setInt(2, typeId);
							pstUpdate.setString(3, description);
							pstUpdate.setString(4, id);
							if (pstUpdate.executeUpdate() > 0) {
								JOptionPane.showMessageDialog(null, "Updated successfully");			
								rawMateiral.setName(name);
								rawMateiral.setDescription(description);
								instance_classes.Type type = null;
								stType = con.createStatement();
								rsType = stType.executeQuery("select * from type where id =" + typeId);
								while (rsType.next()) {
									int idType = rsType.getInt("id");
									String nameType = rsType.getString("name");
									String categoryType = rsType.getString("category");
									boolean statusType = rsType.getBoolean("status");
									type = new instance_classes.Type(idType, nameType, categoryType, statusType);							
								}
								rawMateiral.setTpe(type);
								backListener.CallBack(rawMateiral);
								
								
							}
						} catch (SQLException e) {
							JOptionPane.showMessageDialog(null, "Updated unsuccessfully");
							e.printStackTrace();
						}
						
					}
				});
				btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				btnUpdate.setActionCommand("OK");
				buttonPane.add(btnUpdate);
				getRootPane().setDefaultButton(btnUpdate);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				btnCancel.setActionCommand("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						txtName.setText("");
						txtDescription.setText("");						
					}					
				});
				buttonPane.add(btnCancel);
			}
		}
	}
}
