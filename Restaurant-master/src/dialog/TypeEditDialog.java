package dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import interfaces.CallBackListenter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import connection.*;

public class TypeEditDialog extends JDialog {
	private Connection con = null;
	private PreparedStatement pst = null;
	private Statement stType = null;
	private ResultSet rs = null;

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JComboBox cboCategory;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TypeEditDialog dialog = new TypeEditDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	private CallBackListenter backListener ;
	private instance_classes.Type type;
	
	public TypeEditDialog(instance_classes.Type type2) {
		this();
		this.type = type2;
		
		txtName.setText(type2.getName().toString());
		cboCategory.setSelectedItem(type2.getCategory().toString());
		txtId.setText(type2.getId() + "");
		txtId.setVisible(false);

	}
	
	public void setCallBackListener(CallBackListenter backListener) {
		this.backListener = backListener;
	}
	
	public TypeEditDialog() {
		con = DbConnection.getConnection();
		txtId = new JTextField();
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblEditType = new JLabel("Edit type");
		lblEditType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEditType.setBounds(21, 26, 155, 14);
		contentPanel.add(lblEditType);
		
		JLabel label_1 = new JLabel("Name");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(23, 78, 46, 14);
		contentPanel.add(label_1);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtName.setColumns(10);
		txtName.setBounds(128, 75, 132, 20);
		contentPanel.add(txtName);
		
		cboCategory = new JComboBox();
		cboCategory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboCategory.setBounds(128, 130, 132, 20);
		contentPanel.add(cboCategory);
		cboCategory.addItem("Product");
		cboCategory.addItem("Raw material");
		cboCategory.addItem("Table");
		
		JLabel label_2 = new JLabel("Category");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(23, 133, 95, 14);
		contentPanel.add(label_2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnUpdate = new JButton("Update");
				btnUpdate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String name = txtName.getText();
						String category = cboCategory.getSelectedItem().toString();
						int id = Integer.parseInt(txtId.getText());
						try {
							pst = con.prepareStatement("update type set name = ?, category = ? where id = ?");
							pst.setString(1, name);
							pst.setString(2, category);
							pst.setInt(3, id);
							if (pst.executeUpdate() > 0) {
								type.setCategory(category);
								type.setName(name);
								backListener.CallBack(type);
								JOptionPane.showMessageDialog(null, "Updated successfully!");
							}
						} catch (SQLException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "Updated unsuccessfully!");
						}					
					}
					
				});
				btnUpdate.setActionCommand("OK");
				buttonPane.add(btnUpdate);
				getRootPane().setDefaultButton(btnUpdate);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						txtName.setText("");
					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
	}
}
