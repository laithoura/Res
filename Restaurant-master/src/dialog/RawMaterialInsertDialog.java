package dialog;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import control_classes.Help;
import controller.RawMaterialDao;
import form.LoginForm;
import instance_classes.RawMaterial;
import interfaces.CallBackListenter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;


public class RawMaterialInsertDialog extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtDescription;
	private CallBackListenter callBack;

	
	public RawMaterialInsertDialog() {
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
		
		setTitle("Insert Raw Material");
		
		setBounds(100, 100, 414, 238);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		{
			JLabel lblNewLabel = new JLabel("Name");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNewLabel.setBounds(29, 22, 70, 20);
			contentPanel.add(lblNewLabel);
		}
		
		JComboBox cboType = new JComboBox( );
		cboType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboType.setBounds(103, 70, 253, 24);
		contentPanel.add(cboType);
		cboType.addItem("Meat");
		cboType.addItem("Vegatable");
		cboType.addItem("Ingredient");
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtName.setBounds(103, 22, 253, 24);
		contentPanel.add(txtName);
		txtName.setColumns(10);
		
		
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblType.setBounds(29, 70, 70, 20);
		contentPanel.add(lblType);
		
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescription.setBounds(29, 114, 70, 20);
		contentPanel.add(lblDescription);
		
		txtDescription = new JTextField();
		txtDescription.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtDescription.setColumns(10);
		txtDescription.setBounds(103, 114, 253, 24);
		
		
		contentPanel.add(txtDescription);
		{
			JButton btnSave = new JButton("Save");
			btnSave.setIcon(new ImageIcon(RawMaterialInsertDialog.class.getResource("/resources/Add_20.png")));
			btnSave.setBounds(149, 151, 100, 30);
			contentPanel.add(btnSave);
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if (txtName.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please input name!");
						return;
					} else {
						RawMaterialDao rawMaterialDao = new RawMaterialDao();
						String name = txtName.getText();
						String type = cboType.getSelectedItem().toString();
						String description = txtDescription.getText(); 
						
						RawMaterial rawMaterial = new RawMaterial(0, name, type, description, true);
						
						if (rawMaterialDao.insertRawMaterial(rawMaterial)) {
							
							int lastRawMaterialId = Help.getLastAutoIncrement("restaurant_project", "raw_material");
							rawMaterial.setId(lastRawMaterialId);
							JOptionPane.showMessageDialog(null, "Inserted succesfully!");							
							clearInputBox();
							callBack.CallBack(rawMaterial);
							
						} else {
							JOptionPane.showMessageDialog(null, "Inserted unsuccesfully!");
						}	
					}				
				}
			});
			btnSave.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btnSave.setActionCommand("OK");
			getRootPane().setDefaultButton(btnSave);
		}
		{
			JButton btnCancel = new JButton("Cancel");
			btnCancel.setIcon(new ImageIcon(RawMaterialInsertDialog.class.getResource("/resources/Cancel_20.png")));
			btnCancel.setBounds(256, 151, 100, 30);
			contentPanel.add(btnCancel);
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					clearInputBox();					
				}				
			});
			btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btnCancel.setActionCommand("Cancel");
		}
	}
	
	public void clearInputBox() {
		txtName.setText("");
		txtDescription.setText("");
	}
	
	public void setCallBackListener(CallBackListenter callBack) {
		this.callBack = callBack;
	}
}
