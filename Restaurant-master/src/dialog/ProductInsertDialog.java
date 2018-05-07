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
import control_classes.Help;
import control_classes.InputControl;
import controller.ProductDao;
import form.LoginForm;
import instance_classes.Product;
import interfaces.CallBackListenter;
import javax.swing.ImageIcon;


public class ProductInsertDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtUnitPrice;
	private JComboBox cboType;
	private CallBackListenter callBack;

	
	public ProductInsertDialog() {
		setFont(new Font("Dialog", Font.PLAIN, 12));
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
		
		setTitle("Insert Product");
		setBounds(100, 100, 418, 229);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		{
			JLabel lblNewLabel_1 = new JLabel("Name");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNewLabel_1.setBounds(33, 31, 59, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblType = new JLabel("Type");
			lblType.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblType.setBounds(33, 70, 59, 14);
			contentPanel.add(lblType);
		}
		{
			JLabel lblUnitPrice = new JLabel("Unit price");
			lblUnitPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblUnitPrice.setBounds(33, 112, 59, 14);
			contentPanel.add(lblUnitPrice);
		}
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtName.setBounds(102, 26, 253, 24);
		contentPanel.add(txtName);
		txtName.setColumns(10);
		{
			txtUnitPrice = new JTextField();
			txtUnitPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txtUnitPrice.setColumns(10);
			txtUnitPrice.setBounds(102, 107, 253, 24);
			contentPanel.add(txtUnitPrice);
		}
		
		cboType = new JComboBox();
		cboType.addItem("Drink");
		cboType.addItem("Food");
		cboType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboType.setBounds(102, 65, 253, 24);
		
		
		/** Validation fields */	
		InputControl.inputFloatingPoint(txtUnitPrice);
		
		contentPanel.add(cboType);
		{
			JButton btnSave = new JButton("Save");
			btnSave.setIcon(new ImageIcon(ProductInsertDialog.class.getResource("/resources/Add_20.png")));
			btnSave.setBounds(149, 146, 100, 30);
			contentPanel.add(btnSave);
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
			btnSave.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnSave.setActionCommand("OK");
			getRootPane().setDefaultButton(btnSave);
		}
		{
			JButton btnCancel = new JButton("Cancel");
			btnCancel.setIcon(new ImageIcon(ProductInsertDialog.class.getResource("/resources/Cancel_20.png")));
			btnCancel.setBounds(255, 146, 100, 30);
			contentPanel.add(btnCancel);
			btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnCancel.setActionCommand("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					clearInputBox();					
				}					
			});
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
