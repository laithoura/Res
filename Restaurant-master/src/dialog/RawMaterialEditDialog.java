package dialog;

import java.awt.BorderLayout;
import instance_classes.*;
import controller.RawMaterialDao;
import form.LoginForm;
import interfaces.CallBackListenter;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;


public class RawMaterialEditDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtDescription;
	private JComboBox cboType;
	private RawMaterial rawMaterial = null;
	private JTextField txtId; 
	private CallBackListenter backListener;
	
	
	
	public RawMaterialEditDialog(RawMaterial rawMaterial) {
		this();
		this.rawMaterial = rawMaterial;	
		
		txtId = new JTextField();
		txtName.setText(rawMaterial.getName());
		txtDescription.setText(rawMaterial.getDescriptioin());
		cboType.setSelectedItem(rawMaterial.getType());
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
		setResizable(false);
		
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
		setTitle("Edit Raw Material");
		setBounds(100, 100, 414, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		{
			JLabel lblName = new JLabel("Name");
			lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblName.setBounds(27, 26, 46, 14);
			contentPanel.add(lblName);
		}
		{
			JLabel lblType = new JLabel("Type");
			lblType.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblType.setBounds(27, 66, 46, 14);
			contentPanel.add(lblType);
		}
		{
			JLabel lblDescription = new JLabel("Description");
			lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblDescription.setBounds(27, 107, 88, 14);
			contentPanel.add(lblDescription);
		}
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtName.setBounds(114, 21, 251, 24);
		contentPanel.add(txtName);
		txtName.setColumns(10);
		
		txtDescription = new JTextField();
		txtDescription.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtDescription.setColumns(10);
		txtDescription.setBounds(115, 107, 250, 50);
		contentPanel.add(txtDescription);
		
		cboType = new JComboBox();
		cboType.addItem("Meat");
		cboType.addItem("Vegatable");
		cboType.addItem("Ingredient");
		
		
		cboType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboType.setBounds(114, 61, 251, 24);
		contentPanel.add(cboType);
		{
			JButton btnUpdate = new JButton("Update");
			btnUpdate.setIcon(new ImageIcon(RawMaterialEditDialog.class.getResource("/resources/Edit_20.png")));
			btnUpdate.setBounds(156, 168, 100, 30);
			contentPanel.add(btnUpdate);
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if (txtName.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please input name!");
						return;
					} else {
						int id = Integer.parseInt(txtId.getText());
						String name = txtName.getText();
						String type = cboType.getSelectedItem().toString();
						String description = txtDescription.getText();
						
						RawMaterialDao rawMaterialDao = new RawMaterialDao();
						
						rawMaterial = new RawMaterial(id, name, type, description, true);
						if (rawMaterialDao.updateRawMaterial(rawMaterial)) {
			
							JOptionPane.showMessageDialog(null, "Updated successfully");
							rawMaterial.setName(name);
							rawMaterial.setType(type);
							rawMaterial.setDescription(description);
							backListener.CallBack(rawMaterial);
						} else {
							JOptionPane.showMessageDialog(null, "Updated unsuccessfully");
						}
					}
				}
			});
			btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btnUpdate.setActionCommand("OK");
			getRootPane().setDefaultButton(btnUpdate);
		}
		{
			JButton btnCancel = new JButton("Cancel");
			btnCancel.setIcon(new ImageIcon(RawMaterialEditDialog.class.getResource("/resources/Cancel_20.png")));
			btnCancel.setBounds(266, 168, 100, 30);
			contentPanel.add(btnCancel);
			btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btnCancel.setActionCommand("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtName.setText("");
					txtDescription.setText("");						
				}					
			});
		}
	}
}
