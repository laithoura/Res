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


public class RawMaterialEditDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtDescription;
	private JComboBox cboType;
	private RawMaterial rawMaterial = null;
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
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
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
		cboType.addItem("Meat");
		cboType.addItem("Vegatable");
		cboType.addItem("Ingredient");
		
		
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
