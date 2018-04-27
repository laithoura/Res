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
import javax.swing.JComboBox;
import instance_classes.*;
import interfaces.CallBackListenter;
import connection.*;
import controller.ProductDao;

import java.sql.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ProdutEditDialog dialog = new ProdutEditDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ProdutEditDialog(Product product) {
		this();
		txtId = new JTextField();
		
		this.product = product;
		txtName.setText(product.getName());
		txtUnitPrice.setText(product.getUnitPrice() + "");
		txtId.setText(product.getId() + "");
		cboType.setSelectedItem(product.getType());
		txtId.setVisible(false);	
	}
	
	public void setCallBackListener(CallBackListenter backListener) {
		this.backListenter = backListener;
	}
 	
	public ProdutEditDialog() {
		//con = DbConnection.getConnection();
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblEditProduct = new JLabel("Edit product");
		lblEditProduct.setBounds(10, 11, 103, 14);
		contentPanel.add(lblEditProduct);
		{
			JLabel lblName = new JLabel("Name");
			lblName.setBounds(10, 46, 103, 14);
			contentPanel.add(lblName);
		}
		{
			JLabel lblType = new JLabel("Type");
			lblType.setBounds(10, 88, 103, 14);
			contentPanel.add(lblType);
		}
		{
			JLabel lblUnitPrice = new JLabel("Unit price");
			lblUnitPrice.setBounds(10, 124, 103, 14);
			contentPanel.add(lblUnitPrice);
		}
		
		txtName = new JTextField();
		txtName.setBounds(123, 43, 159, 20);
		contentPanel.add(txtName);
		txtName.setColumns(10);
		
		txtUnitPrice = new JTextField();
		txtUnitPrice.setColumns(10);
		txtUnitPrice.setBounds(123, 121, 159, 20);
		contentPanel.add(txtUnitPrice);
		
		cboType = new JComboBox();
		cboType.addItem("Drink");
		cboType.addItem("Food");
		cboType.setBounds(123, 85, 159, 20);
		contentPanel.add(cboType);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnUpdate = new JButton("Update");
				btnUpdate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
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
				});
				btnUpdate.setActionCommand("OK");
				buttonPane.add(btnUpdate);
				getRootPane().setDefaultButton(btnUpdate);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						txtName.setText("");
						txtUnitPrice.setText("");						
					}					
				});
			}
		}
	}
}
