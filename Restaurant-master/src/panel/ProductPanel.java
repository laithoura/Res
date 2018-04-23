package panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;

import instance_classes.*;
import interfaces.CallBackListenter;
import dialog.*;
import java.sql.*;
import instance_classes.*;
import dialog.*;
import connection.*;
import data_table_model.*;
public class ProductPanel extends JPanel implements CallBackListenter, ActionListener{
	private Connection con = null;
	private Statement st = null;
	private Statement stType = null;
	private PreparedStatement pstDelete = null;
	private ResultSet rsType;
	private ResultSet rs;
	ArrayList<Product> productList = null;
	
	private JTable table;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private int selectedIndex = 0;
	ProductDataModel productModel;

	/**
	 * Create the panel.
	 */
	public ProductPanel() {
		con = DbConnection.getConnection();
		
		
		JPanel pnlBtn = new JPanel();
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProductInsertDialog productInsert = new ProductInsertDialog();
				productInsert.setVisible(true);				
			}
		});
		
		btnAdd.setIcon(new ImageIcon(ProductPanel.class.getResource("/resources/Add_20.png")));
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		btnEdit = new JButton("Edit");
		btnEdit.setIcon(new ImageIcon(ProductPanel.class.getResource("/resources/Edit_20.png")));
		btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					pstDelete = con.prepareStatement("update product set status = false where id=" + selectedIndex);
					if (pstDelete.executeUpdate() > 0) {
						JOptionPane.showMessageDialog(null, "Deleted successfull");
						productList.remove(selectedIndex);
					}
				}catch(SQLException e) {
					JOptionPane.showMessageDialog(null, "Deleted unsuccessfull");
					e.printStackTrace();
				}
			}
		});
		btnDelete.setIcon(new ImageIcon(ProductPanel.class.getResource("/resources/Cancel_20.png")));
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		GroupLayout gl_pnlBtn = new GroupLayout(pnlBtn);
		gl_pnlBtn.setHorizontalGroup(
			gl_pnlBtn.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlBtn.createSequentialGroup()
					.addGap(5)
					.addComponent(btnAdd)
					.addGap(5)
					.addComponent(btnEdit)
					.addGap(5)
					.addComponent(btnDelete))
		);
		gl_pnlBtn.setVerticalGroup(
			gl_pnlBtn.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlBtn.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_pnlBtn.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAdd)
						.addComponent(btnEdit)
						.addComponent(btnDelete)))
		);
		pnlBtn.setLayout(gl_pnlBtn);
		
		JPanel pnlTable = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		
		table = new JTable();
		productModel = new ProductDataModel();
		productList = new ArrayList<Product>();
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from product where status = true");
			Product product = null;
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int typeId = rs.getInt("type");
				Type type = null;
				
				stType = con.createStatement();
				rsType = stType.executeQuery("select * from type where id=" + typeId);
				while(rsType.next()) {
					int idType = rsType.getInt("id");
					String nameType = rsType.getString("name");
					String category = rsType.getString("category");
					boolean statusType = rsType.getBoolean("status");
					type = new Type(idType, nameType, category, statusType);
					
				}
				
				double unitPrice = rs.getDouble("unit_price");
				boolean status = rs.getBoolean("status");	
				product = new Product(id, name, type, unitPrice, status);
				productList.add(product);
			}
			
			productModel.setProuctList(productList);
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		
		table.setModel(productModel);
		scrollPane.setViewportView(table);
		
		JLabel lblListOfProduct = new JLabel("List of product");
		lblListOfProduct.setFont(new Font("Times New Roman", Font.BOLD, 13));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(pnlBtn, GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
							.addContainerGap())
						.addComponent(lblListOfProduct, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(pnlTable, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(15))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(lblListOfProduct, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(pnlBtn, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addGap(23)
					.addComponent(pnlTable, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(11))
		);
		GroupLayout gl_pnlTable = new GroupLayout(pnlTable);
		gl_pnlTable.setHorizontalGroup(
			gl_pnlTable.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
		);
		gl_pnlTable.setVerticalGroup(
			gl_pnlTable.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
		);
		pnlTable.setLayout(gl_pnlTable);
		setLayout(groupLayout);
		table.getSelectionModel().addListSelectionListener(new RowListener());
		btnEdit.addActionListener(this);

	}
	
	class RowListener implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting()) return;
			selectedIndex = table.getSelectedRow();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Product product = null;
		if (productList.size() > 0) {
			product = productList.get(selectedIndex);
		}
			
		ProdutEditDialog productEdit = new ProdutEditDialog(product);
		
		productEdit.setCallBackListener(this);
		productEdit.setVisible(true);
		
		
	}

	@Override
	public void CallBack(Object sender) {
		productList.set(selectedIndex, (Product)sender);
		productModel.setProuctList(productList);
		productModel.updateTable();
		
	}
}
