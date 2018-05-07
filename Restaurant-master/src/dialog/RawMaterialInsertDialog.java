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


public class RawMaterialInsertDialog extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtDescription;
	private CallBackListenter callBack;

	
	public RawMaterialInsertDialog() {
		
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
		cboType.addItem("Meat");
		cboType.addItem("Vegatable");
		cboType.addItem("Ingredient");
		
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
				btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				btnSave.setActionCommand("OK");
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						clearInputBox();					
					}				
				});
				btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
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
