package dialog;

import java.awt.BorderLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import connection.DbConnection;
import control_classes.Formatter;
import control_classes.Help;
import control_classes.InputControl;
import control_classes.MessageShow;
import control_classes.TableSetting;
import controller.ProductDao;
import controller.SaleDao;
import data_table_model.SaleProductDataModel;
import instance_classes.Product;
import instance_classes.Sale;
import instance_classes.SaleDetail;
import interfaces.CallBackListenter;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SaleProductDialog extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textBoxName;
	private JTextField textBoxPrice;
	private JTextField textBoxQty;
	private JTextField textBoxTotal;
	private JTextField textBoxAmount;
	private JTable tableSaleProduct;
	private JButton buttonAdd, buttonRemove, buttonReset, buttonPrint;
	private JList<?> jListProduct;
	private ButtonGroup buttonGroupProductType = new ButtonGroup();
	private JRadioButton radioButtonDrink, radioButtonFood;

	private ArrayList<SaleDetail> saleProductList;
	private ArrayList<Product> productList;
	private SaleProductDataModel saleProductDataModel;
	private ProductDao productDao;
	private CallBackListenter backListener;	
	private DefaultListModel<String> listModel;
	private int selectedIndexSaleTable =-1 ;
	
	private int selectedIndexJList = -1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SaleProductDialog dialog = new SaleProductDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setCallBackListener(CallBackListenter backListener) {
		this.backListener = backListener;
	}
	
	/**
	 * Create the dialog.
	 */
	public SaleProductDialog() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {			
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		setBounds(100, 100, 658, 459);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.BLACK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		listModel = new DefaultListModel<>();	
		
		jListProduct = new JList(listModel);
		jListProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(jListProduct.getModel().getSize() == 0) return;
				
				selectedIndexJList = jListProduct.getSelectedIndex();		
				clearControls();
				setValueToControl(selectedIndexJList);
				
				/*Set Focus to TextBox Input Quantity */
				textBoxQty.requestFocus();
			}
		});
		jListProduct.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		jListProduct.setBounds(16, 45, 128, 351);
		contentPanel.add(jListProduct);
		
		radioButtonFood = new JRadioButton("Food");
		radioButtonFood.setSelected(true);
		radioButtonFood.setFont(new Font("Tahoma", Font.PLAIN, 13));
		radioButtonFood.setForeground(Color.WHITE);
		radioButtonFood.setBackground(Color.BLACK);
		radioButtonFood.setBounds(15, 15, 61, 23);
		contentPanel.add(radioButtonFood);
		
		radioButtonDrink = new JRadioButton("Drink");
		radioButtonDrink.setFont(new Font("Tahoma", Font.PLAIN, 13));
		radioButtonDrink.setForeground(Color.WHITE);
		radioButtonDrink.setBackground(Color.BLACK);
		radioButtonDrink.setBounds(78, 15, 66, 23);
		contentPanel.add(radioButtonDrink);
		
		buttonGroupProductType.add(radioButtonDrink);
		buttonGroupProductType.add(radioButtonFood);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.WHITE));
		panel.setBackground(Color.BLACK);
		panel.setBounds(151, 45, 456, 137);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		textBoxName = new JTextField();
		textBoxName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textBoxName.setBounds(87, 11, 186, 20);
		panel.add(textBoxName);
		textBoxName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(22, 14, 63, 14);
		panel.add(lblNewLabel);
		
		textBoxPrice = new JTextField();
		textBoxPrice.setEditable(false);
		textBoxPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textBoxPrice.setColumns(10);
		textBoxPrice.setBounds(87, 42, 186, 20);
		panel.add(textBoxPrice);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setBounds(22, 45, 63, 14);
		panel.add(lblPrice);
		
		textBoxQty = new JTextField();
		textBoxQty.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						double price = productList.get(selectedIndexJList).getUnitPrice();
						String qtyText = textBoxQty.getText().trim();
						int qty = Integer.parseInt((qtyText.equals(""))?"1":qtyText);
						
						int productId = productList.get(selectedIndexJList).getId();
						int inStockProductCount = productDao.countInstockDrink(productId);
						
						if((qty + countQuantity(productId) > inStockProductCount) && productList.get(selectedIndexJList).getType().toLowerCase().equals("drink")) {
							MessageShow.Error(String.format("We have %s only %d items", productList.get(selectedIndexJList).getName(), inStockProductCount), "Sale");
							return;
						}

						
						double total = price * qty;
						textBoxTotal.setText(Formatter.numberToText(total));
				
					}catch(NumberFormatException ex) {
						ex.printStackTrace();						
					}
					buttonAdd.requestFocus();
				}
			}
		});
		textBoxQty.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textBoxQty.setColumns(10);
		textBoxQty.setBounds(87, 73, 186, 20);
		panel.add(textBoxQty);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantity.setForeground(Color.WHITE);
		lblQuantity.setBounds(22, 76, 63, 14);
		panel.add(lblQuantity);
		
		textBoxTotal = new JTextField();
		textBoxTotal.setEditable(false);
		textBoxTotal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textBoxTotal.setColumns(10);
		textBoxTotal.setBounds(87, 104, 186, 20);
		panel.add(textBoxTotal);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setBounds(22, 107, 63, 14);
		panel.add(lblTotal);
		
		buttonAdd = new JButton("Add to Cart");
		buttonAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonAdd.setBounds(303, 104, 119, 23);
		panel.add(buttonAdd);
		
		JPanel panelProduct = new JPanel();
		panelProduct.setBounds(151, 188, 456, 180);
		contentPanel.add(panelProduct);
		panelProduct.setLayout(new BorderLayout(0, 0));
				
		buttonRemove = new JButton("Remove");
		buttonRemove.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonRemove.setBounds(306, 373, 95, 23);
		contentPanel.add(buttonRemove);
		
		buttonPrint = new JButton("Print");
		buttonPrint.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonPrint.setBounds(512, 373, 95, 23);
		contentPanel.add(buttonPrint);
		
		JLabel lblSaleInformation = new JLabel("Sale Information");
		lblSaleInformation.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSaleInformation.setForeground(Color.WHITE);
		lblSaleInformation.setBounds(234, 12, 155, 22);
		contentPanel.add(lblSaleInformation);
		
		textBoxAmount = new JTextField();
		textBoxAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textBoxAmount.setColumns(10);
		textBoxAmount.setBounds(151, 373, 148, 23);
		contentPanel.add(textBoxAmount);
		
		buttonReset = new JButton("Reset");
		buttonReset.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonReset.setBounds(409, 373, 95, 23);
		contentPanel.add(buttonReset);
		
		JScrollPane scrollPaneProduct = new JScrollPane();
		panelProduct.add(scrollPaneProduct, BorderLayout.CENTER);
		
		tableSaleProduct = new JTable();
		scrollPaneProduct.setViewportView(tableSaleProduct);
		
		saleProductList = new ArrayList<>();
		saleProductDataModel = new SaleProductDataModel();
		saleProductDataModel.setSaleList(saleProductList);
		tableSaleProduct.setModel(saleProductDataModel);
		
		productList = new ArrayList<>();
		productDao = new ProductDao();
		
		registerEvent();
		controlTextBox();	
		controlSaleProductTable();	
		
		setFoodListToJListProduct();
	}

	private void setFoodListToJListProduct() {
		if(radioButtonFood.isSelected()) {
			loadProductToProductList(radioButtonFood.getText());
		}	
	}

	private void controlSaleProductTable() {
		
		TableSetting.TableControl(tableSaleProduct);
		TableSetting.alignColumnToRight(tableSaleProduct, 1);
		TableSetting.alignColumnToCenter(tableSaleProduct, 2);
		TableSetting.alignColumnToRight(tableSaleProduct, 3);
	}

	private void controlTextBox() {
		InputControl.inputLetter(textBoxName);
		InputControl.inputInteger(textBoxQty);		
	}

	private void registerEvent() {
		this.buttonAdd.addActionListener(this);
		this.buttonRemove.addActionListener(this);
		this.buttonReset.addActionListener(this);
		this.buttonPrint.addActionListener(this);
		
		this.radioButtonDrink.addActionListener(this);
		this.radioButtonFood.addActionListener(this);
		
		this.tableSaleProduct.getSelectionModel().addListSelectionListener(new RowListener());		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonAdd) {
			
			if(selectedIndexJList == -1) {
				MessageShow.Error("Select product to sale", "Sale");
				return;
			}
			addProductIntoCart();
			clearControls();
			selectedIndexJList = -1;
			
		}else if(e.getSource() == buttonRemove) {
			
			if(selectedIndexSaleTable == -1) {
				MessageShow.Error("Select product to remove", "Sale");
				return;
			}else {
				if(MessageShow.deleteVerification("Do you want to remove?", "Sale") == 0) {
					saleProductList.remove(selectedIndexSaleTable);
					refreshTableModel();
				}
			}
		}else if(e.getSource() == buttonReset) {
		
			if(MessageShow.deleteVerification("Do you want to reset?", "Sale") == 0) {
				clearControls();
				saleProductList.clear();
				refreshTableModel();
				refreshSaleAmount();
			}
			
		}else if(e.getSource() == buttonPrint) {
			
			if(saleProductList.size() == 0) return;
			
			if(MessageShow.deleteVerification("Do you want to Print?", "Sale") == 0) {
				
				int lastSaleId = 0;
				Date saleDate = new Date();
				Sale sale = new Sale();
				SaleDao saleDao = new SaleDao();
				
				sale.setUserId(1);
				sale.setUserName("Thoura Test Line 341: SaleProductDialog");
				sale.setSoldDate(saleDate);
				sale.setTotal(sumAmount());
				
				if(saleDao.insertSale(sale)) {
					
					lastSaleId = Help.getLastAutoIncrement("restaurant_project", "Sold");
					
					sale.setId(lastSaleId); /*Updated Sale ID after insertion into Sold Table*/
					
					if(insertIntoSaleDetail(lastSaleId)) {
						
						/*Write Code Cut Stock Here For product which type is Drink*/						
						if(cutStockFromImportDrinkDetail()) {
							
							/*Call to display Sale on Main Form*/
							this.backListener.CallBack(sale);	
						}																
					}	
				}/*End of insertSale*/				
			}
			
		}else if(e.getSource() == radioButtonDrink) {
			
			if(radioButtonDrink.isSelected()) {
				loadDrinkToProductList();	
				clearControls();
				selectedIndexJList = -1;
			}			
		}else if(e.getSource() == radioButtonFood) {
			
			if(radioButtonFood.isSelected()) {
				loadProductToProductList(radioButtonFood.getText());
				clearControls();
				selectedIndexJList = -1;
			}
		}
	}

	private boolean cutStockFromImportDrinkDetail() {
		boolean success = false;
		
		PreparedStatement preparedStatement = null;
	
		try {
			preparedStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("UPDATE Import_Drink_Detail SET soldQty = (soldQty + ?) WHERE pro_id = ? AND (qty - soldQty) > 0 AND status = ?");
			
			for(SaleDetail saleDetail:saleProductList) {
				int minQty = 0;
				int soldQty = saleDetail.getQty();
				
				/*Begin of Dynamic Stock Cutting without selecting any import drink number*/
				
				while(soldQty > 0){					
					
					minQty = getMinInStockQuantity(saleDetail.getProductId());
					if(minQty > soldQty) {
						minQty = soldQty;
						soldQty = 0; /*To stop query for stock cutting*/
					}else {
						soldQty -= minQty;
					}
					preparedStatement.setInt(1, minQty);
					preparedStatement.setInt(2, saleDetail.getProductId());
					preparedStatement.setBoolean(3, true);
					
					preparedStatement.executeUpdate();
				}	
				/*End of Dynamic Stock Cutting without selecting any import drink number*/
				
			}/*End of For Loop*/
			success = true;
		} catch (SQLException e) {	
			success = false;
			e.printStackTrace();
		}finally {
			try {
				preparedStatement.close();				
			} catch (SQLException | NullPointerException e) {				
				e.printStackTrace();
			}
		}
		return success;
	}

	private int getMinInStockQuantity(int productId) {
		int minInstockQty = 0;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = (PreparedStatement) DbConnection.dbConnection.prepareStatement("SELECT MIN(qty) FROM Import_Drink_Detail WHERE pro_id = ? AND (qty - soldQty) > 0 AND status = ?");
			preparedStatement.setInt(1, productId);
			preparedStatement.setBoolean(2, true);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				minInstockQty = resultSet.getInt(1);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally {
			try {
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException | NullPointerException e) {				
				e.printStackTrace();
			}	
		}
		return minInstockQty;
	}

	private boolean insertIntoSaleDetail(int lastSaleId) {
		
		boolean success = false;
		SaleDao saleDao = new SaleDao();
		int saleProductCount = 0;
		
		for(SaleDetail saleDetail: saleProductList) {
			
			saleDetail.setSaleId(lastSaleId);
			if(saleDao.insertIntoSaleDetails(saleDetail)) {
				saleProductCount ++;
			}
		}
		if(saleProductCount == saleProductList.size()) {
			success = true;
		}
		
		return success;
	}

	private void loadProductToProductList(String type) {
		
		if(productList != null) productList.clear();
		if(listModel != null) listModel.clear();		
		productList = productDao.getProductListByType(type);		
		
		for(Product pro:productList) {
			listModel.addElement(pro.getName());
		}		
	}
	
	private void loadDrinkToProductList() {
		
		if(productList != null) productList.clear();
		if(listModel != null) listModel.clear();
		productList = productDao.getOnlyInstockDrinkList();		
		
		for(Product pro:productList) {
			listModel.addElement(pro.getName());
		}		
	}

	private void addProductIntoCart() {
		
		SaleDetail saleProduct = new SaleDetail();
		
		double price = productList.get(selectedIndexJList).getUnitPrice();
		String qtyText = textBoxQty.getText().trim();
		int qty = Integer.parseInt((qtyText.equals(""))?"1":qtyText);
		double total = price * qty;
		
		int productId = productList.get(selectedIndexJList).getId();
		int inStockProductCount = productDao.countInstockDrink(productId);
		
		if((qty + countQuantity(productId) > inStockProductCount) && productList.get(selectedIndexJList).getType().toLowerCase().equals("drink")) {
			MessageShow.Error(String.format("We have %s only %d items", productList.get(selectedIndexJList).getName(), inStockProductCount), "Sale");
			return;
		}
		
		saleProduct.setProductId(productList.get(selectedIndexJList).getId());
		saleProduct.setProductName(productList.get(selectedIndexJList).getName());
		saleProduct.setQty(qty);
		saleProduct.setType(productList.get(selectedIndexJList).getType());
		saleProduct.setUnitPrice(price);
		saleProduct.setAmount(total);
		
		saleProductList.add(saleProduct);
		refreshSaleAmount();
		refreshTableModel();		
	}

	private int countQuantity(int productId) {
		int count = 0;		
		for(SaleDetail saleDeatail:saleProductList) {
			if(saleDeatail.getProductId() == productId) {
				count += saleDeatail.getQty();				
			}
		}		
		return count;
	}

	private void refreshSaleAmount() {
		
		textBoxAmount.setText(Formatter.numberToText(sumAmount()));
	}

	private double sumAmount() {
		
		double amount = 0;
		for(SaleDetail sale:saleProductList) {
			amount += sale.getAmount();
		}
		return amount;		
	}

	private void refreshTableModel() {
		
		//saleProductDataModel.setSaleList(saleProductList);
		//tableSaleProduct.setModel(saleProductDataModel);
		saleProductDataModel.updateTable();
	}	

	private void clearControls() {
		
		textBoxName.setText("");
		textBoxPrice.setText("");
		textBoxQty.setText("");
		textBoxTotal.setText("");	
	}

	private void setValueToControl(int selectedIndex) {		
		if(selectedIndex < 0) return;
		textBoxName.setText(productList.get(selectedIndex).getName());
		textBoxPrice.setText(Formatter.numberToText(productList.get(selectedIndex).getUnitPrice()));
	}
	
	class RowListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting()) return;
			selectedIndexSaleTable = tableSaleProduct.getSelectedRow();
		}
		
	}
}
