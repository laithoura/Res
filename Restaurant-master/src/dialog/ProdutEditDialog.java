package dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
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
import instance_classes.*;
import interfaces.CallBackListenter;
import control_classes.InputControl;
import controller.ProductDao;
import form.LoginForm;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;



public class ProdutEditDialog extends JDialog {
	/*private Connection con = null;
	private Statement stType = null;
	private ResultSet rsType = null;
	private PreparedStatement pstUpdate = null;*/

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtUnitPrice;
	private JComboBox cboType; 
	private JTextField txtId;
	private CallBackListenter backListenter;
	private Product product;
	private ArrayList<String> typeList;
	
	public ProdutEditDialog(Product product) {
		this();
		txtId = new JTextField();
		
		this.product = product;
		if(product != null) {
			
			txtName.setText(product.getName());
			txtUnitPrice.setText(product.getUnitPrice() + "");
			txtId.setText(product.getId() + "");
			cboType.setSelectedItem(product.getType());
			txtId.setVisible(false);	
		}
	}
	
	public void setCallBackListener(CallBackListenter backListener) {
		this.backListenter = backListener;
	}
 	
	public ProdutEditDialog() {
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
		setTitle("Update Product");
		setBounds(100, 100, 418, 221);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		{
			JLabel lblName = new JLabel("Name");
			lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblName.setBounds(30, 29, 66, 14);
			contentPanel.add(lblName);
		}
		{
			JLabel lblType = new JLabel("Type");
			lblType.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblType.setBounds(30, 69, 66, 14);
			contentPanel.add(lblType);
		}
		{
			JLabel lblUnitPrice = new JLabel("Unit price");
			lblUnitPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblUnitPrice.setBounds(30, 107, 66, 14);
			contentPanel.add(lblUnitPrice);
		}
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtName.setBounds(93, 24, 268, 24);
		contentPanel.add(txtName);
		txtName.setColumns(10);
		
		txtUnitPrice = new JTextField();
		txtUnitPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtUnitPrice.setColumns(10);
		txtUnitPrice.setBounds(93, 102, 268, 24);
		contentPanel.add(txtUnitPrice);
		
		cboType = new JComboBox();
		cboType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboType.addItem("Drink");
		cboType.addItem("Food");
		cboType.setBounds(93, 64, 268, 24);
		
		/** Validation fields */	
		InputControl.inputFloatingPoint(txtUnitPrice);
		contentPanel.add(cboType);
		{
			JButton btnUpdate = new JButton("Update");
			btnUpdate.setIcon(new ImageIcon(ProdutEditDialog.class.getResource("/resources/Edit_20.png")));
			btnUpdate.setBounds(151, 137, 100, 30);
			contentPanel.add(btnUpdate);
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if (txtName.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Pleas input name!");
						return;
					} else if (txtUnitPrice.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please input unit price!");
						return;
					} else {
						int id = Integer.parseInt(txtId.getText());
						String name = txtName.getText();
						double unitPrice = Double.parseDouble(txtUnitPrice.getText());
						String type = cboType.getSelectedItem().toString();
						
						ProductDao productDao = new ProductDao(); 
						
						Product product = new Product(id,name, type, unitPrice, true);
						
						if (productDao.updateProduct(product)) {
							JOptionPane.showMessageDialog(null, "Updated successfully");
							product.setName(name);
							product.setPrice(unitPrice);
							product.setType(type);
							backListenter.CallBack(product);
						
						} else {
							JOptionPane.showMessageDialog(null, "Updated unsuccessfully");
						}
					}				
				}
			});
			btnUpdate.setActionCommand("OK");
			getRootPane().setDefaultButton(btnUpdate);
		}
		{
			JButton btnCancel = new JButton("Cancel");
			btnCancel.setIcon(new ImageIcon(ProdutEditDialog.class.getResource("/resources/Cancel_20.png")));
			btnCancel.setBounds(261, 137, 100, 30);
			contentPanel.add(btnCancel);
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
}
