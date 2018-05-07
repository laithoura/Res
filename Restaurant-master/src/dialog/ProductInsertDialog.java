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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;
import connection.*;
import control_classes.Help;
import control_classes.InputControl;
import controller.ProductDao;
import form.LoginForm;
import instance_classes.Product;
import interfaces.CallBackListenter;
public class ProductInsertDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtUnitPrice;
	private JComboBox cboType;
	private CallBackListenter callBack;

	
	public ProductInsertDialog() {
		
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
		cboType.addItem("Drink");
		cboType.addItem("Food");
		cboType.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cboType.setBounds(108, 86, 228, 20);
		
		
		/** Validation fields */	
		InputControl.inputFloatingPoint(txtUnitPrice);
		
		contentPanel.add(cboType);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSave = new JButton("Save");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						/** Check fields */
						
						if (txtName.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Pleas input name!");
						} else if (txtUnitPrice.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Please input unit price!");
						} else {
							String name = txtName.getText().toString();
							String type = cboType.getSelectedItem().toString();
							double unitPrice = Double.parseDouble(txtUnitPrice.getText());
							
							ProductDao productDao = new ProductDao();
							
							Product product = new Product(0,name, type, unitPrice, true);
							if (productDao.insertProduct(product)) {
								int lastProductId = Help.getLastAutoIncrement("restaurant_project", "product");
								product.setId(lastProductId);
								JOptionPane.showMessageDialog(null, "Inserted successfully!");
								clearInputBox();
								callBack.CallBack(product);
								
							} else {
								JOptionPane.showMessageDialog(null, "Inserted unsuccessfully!");
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
				btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						clearInputBox();					
					}					
				});
			}
		}
	}
	
	private void clearInputBox() {
		txtName.setText("");
		txtUnitPrice.setText("");		
	}
	
	public void setCallBackListener(CallBackListenter callBack) {
		this.callBack = callBack;
	}
}
