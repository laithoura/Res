package panel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import instance_classes.*;
import interfaces.CallBackListenter;
import dialog.*;
import control_classes.MessageShow;
import control_classes.TableSetting;
import controller.ProductDao;
import data_table_model.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class ProductPanel extends JPanel implements CallBackListenter, ActionListener{
	ArrayList<Product> productList = null;
	
	private JTable table;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private int selectedIndex = -1;
	ProductDataModel productModel;
	private JTextField txtSearch;
	private ProdutEditDialog productEdit;
	private ProductDao productDao;

	/**
	 * Create the panel.
	 */
	public ProductPanel() {
		
		table = new JTable();
		productDao = new ProductDao();
		productModel = new ProductDataModel();
		productList = new ArrayList<Product>();
		JPanel pnlBtn = new JPanel();
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProductInsertDialog productInsert = new ProductInsertDialog();
				productInsert.setCallBackListener(new CallBackListenter() {
					
					@Override
					public void CallBack(Object sender) {
						productList.add((Product) sender);
						productModel.updateTable();
						
					}
				});
				productInsert.setVisible(true);	
			}
		});
		
		btnAdd.setIcon(new ImageIcon(ProductPanel.class.getResource("/resources/Add_20.png")));
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		btnEdit = new JButton("Edit");
		btnEdit.setIcon(new ImageIcon(ProductPanel.class.getResource("/resources/Edit_20.png")));
		btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		/** Button delete action */
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selectedIndex < 0) {
					return;
				}
				int choose = MessageShow.deleteVerification("Do you want to delete?", "Delete");
				if(choose == 0) {
					ProductDao productDao = new ProductDao();
					Product pro = new Product();
					pro = productList.get(selectedIndex);
					if (productDao.deleteProduct(pro.getId())) {
						JOptionPane.showMessageDialog(null, "Deleted successfull!");
						productList.remove(selectedIndex);
						productModel.updateTable();
						selectedIndex = -1;
						
					} else {
						JOptionPane.showMessageDialog(null, "Deleted unsuccessfull");
					
					}
				}			
			}
		});
		btnDelete.setIcon(new ImageIcon(ProductPanel.class.getResource("/resources/Cancel_20.png")));
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		/** Search function */
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					AbstractTableModel tableProduct =(AbstractTableModel)table.getModel();
					String search = txtSearch.getText();
					TableRowSorter<AbstractTableModel> tr = new TableRowSorter<AbstractTableModel>(tableProduct);
					table.setRowSorter(tr);
					tr.setRowFilter(RowFilter.regexFilter(search));
				}
			}
		});
		txtSearch.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Search :");
		GroupLayout gl_pnlBtn = new GroupLayout(pnlBtn);
		gl_pnlBtn.setHorizontalGroup(
			gl_pnlBtn.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlBtn.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addGap(81)
					.addComponent(btnAdd)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEdit)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDelete)
					.addGap(8))
		);
		gl_pnlBtn.setVerticalGroup(
			gl_pnlBtn.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlBtn.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_pnlBtn.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDelete)
						.addComponent(btnEdit)
						.addComponent(btnAdd)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
		);
		pnlBtn.setLayout(gl_pnlBtn);
		
		JPanel pnlTable = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		
		/*Show data on table */
		
		productList = productDao.getProductList();
		productModel.setProuctList(productList);
		table.setModel(productModel);
		scrollPane.setViewportView(table);
		
		JLabel lblListOfProduct = new JLabel("List of product");
		lblListOfProduct.setFont(new Font("Times New Roman", Font.BOLD, 18));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(pnlTable, GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
					.addGap(15))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(161)
					.addComponent(lblListOfProduct, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
					.addGap(208))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(pnlBtn, GroupLayout.PREFERRED_SIZE, 471, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblListOfProduct, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(pnlBtn, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addGap(23)
					.addComponent(pnlTable, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
					.addGap(11))
		);
		GroupLayout gl_pnlTable = new GroupLayout(pnlTable);
		gl_pnlTable.setHorizontalGroup(
			gl_pnlTable.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
		);
		gl_pnlTable.setVerticalGroup(
			gl_pnlTable.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
		);
		pnlTable.setLayout(gl_pnlTable);
		setLayout(groupLayout);
		table.getSelectionModel().addListSelectionListener(new RowListener());
		btnEdit.addActionListener(this);
		
		TableSetting.TableControl(table);
		TableSetting.alignColumnToRight(table, 3);

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
		if (productList.size() > 0 && selectedIndex >= 0) { 
			product = productList.get(selectedIndex);
		}
			
		productEdit = new ProdutEditDialog(product);
		
		productEdit.setCallBackListener(this);
		productEdit.setVisible(true);		
	}

	@Override
	public void CallBack(Object sender) {
		productList.set(selectedIndex, (Product)sender);
		productModel.setProuctList(productList);
		productModel.updateTable();
		
		productEdit.setVisible(false);
		productEdit.dispose();		
	}
}
