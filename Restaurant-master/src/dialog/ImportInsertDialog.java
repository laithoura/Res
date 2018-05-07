package dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.ArrayList;
import java.util.Date;
import instance_classes.*;
import interfaces.CallBackListenter;
import connection.*;
import control_classes.Formatter;
import control_classes.Help;
import control_classes.InputControl;
import control_classes.Item;
import control_classes.MessageShow;
import controller.ImportDrinkDao;
import controller.ImportRawMaterialDao;
import form.LoginForm;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;


public class ImportInsertDialog extends JDialog{
	private Connection con = null;
	private Statement stImportType = null;
	private ResultSet rsImportType = null;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtInvoiceNumber;
	private JTextField txtQauntity;
	private JTextField txtUnitPrice;
	private JTable table;
	private JTextField txtTotal;
	private DefaultTableModel model;
	private JDateChooser dcImportDate;
	private double total = 0;
	private int selectedIndex = 0;
	private ArrayList<ImportRawMaterialDetail> importRawMaterialList = new ArrayList<ImportRawMaterialDetail>();; 
	private ArrayList<ImportDrinkDetail> importDrinkList = new ArrayList<ImportDrinkDetail>();;
	private ImportDrinkDao importDrinkDao;
	private ImportRawMaterialDao importRawMaterialDao;
	private CallBackListenter callBack;

	
	
	public ImportInsertDialog(){
		setResizable(false);
		con = DbConnection.dbConnection;
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginForm.class.getResource("/resources/Flora.logo.png")));
		setTitle("Import Drink/ Raw Material");
		setBounds(100, 100, 582, 479);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblInvoiceNumber = new JLabel("Invoice number");
		lblInvoiceNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInvoiceNumber.setBounds(30, 46, 119, 14);
		contentPanel.add(lblInvoiceNumber);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDate.setBounds(30, 76, 46, 14);
		contentPanel.add(lblDate);
		
		JLabel lblRawMaterialdrink = new JLabel("Raw material/Drink");
		lblRawMaterialdrink.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRawMaterialdrink.setBounds(30, 138, 130, 14);
		contentPanel.add(lblRawMaterialdrink);
		
		JLabel lblQauntity = new JLabel("Qauntity");
		lblQauntity.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQauntity.setBounds(30, 169, 91, 14);
		contentPanel.add(lblQauntity);
		
		JLabel label_4 = new JLabel("Import ");
		label_4.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		label_4.setBounds(30, 11, 195, 20);
		contentPanel.add(label_4);
		
		JLabel lblUnitPrice = new JLabel("Unit price");
		lblUnitPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUnitPrice.setBounds(30, 198, 91, 14);
		contentPanel.add(lblUnitPrice);
		
		txtInvoiceNumber = new JTextField();
		txtInvoiceNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtInvoiceNumber.setBounds(146, 41, 194, 24);
		contentPanel.add(txtInvoiceNumber);
		txtInvoiceNumber.setColumns(10);
		
		txtQauntity = new JTextField();
		txtQauntity.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtQauntity.setColumns(10);
		txtQauntity.setBounds(146, 164, 194, 24);
		contentPanel.add(txtQauntity);
		
		txtUnitPrice = new JTextField();
		txtUnitPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtUnitPrice.setColumns(10);
		txtUnitPrice.setBounds(146, 193, 194, 24);
		contentPanel.add(txtUnitPrice);
		
		Date nowDate = new Date();
		dcImportDate = new JDateChooser(nowDate);
		dcImportDate.setDateFormatString("dd/MM/yyyy");
		dcImportDate.setBounds(146, 71, 194, 24);
		contentPanel.add(dcImportDate);
		//dcImportDate.setDate(new Date(selectedIndex, selectedIndex, selectedIndex));
		
		JComboBox cboProduct = new JComboBox();
		cboProduct.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboProduct.setBounds(146, 133, 194, 24);
		contentPanel.add(cboProduct);	
		
		
		JRadioButton rabtnImportRawMaterial = new JRadioButton("Raw material");
		rabtnImportRawMaterial.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rabtnImportRawMaterial.setBounds(146, 102, 119, 23);
		contentPanel.add(rabtnImportRawMaterial);
		
		JRadioButton rabtnImportDrink = new JRadioButton("Drink");
		rabtnImportDrink.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rabtnImportDrink.setBounds(268, 103, 109, 23);
		contentPanel.add(rabtnImportDrink);
		
		
		ButtonGroup rabtnGroup = new ButtonGroup();
		rabtnGroup.add(rabtnImportDrink);
		rabtnGroup.add(rabtnImportRawMaterial);
		rabtnImportDrink.setSelected(false);
		rabtnImportRawMaterial.setSelected(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(28, 229, 517, 157);
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		model = new DefaultTableModel();
		String[] columns = new String[] {"Raw material/Drink", "Qauntity", "Unit price", "Amount"};
		model.setColumnIdentifiers(columns);
		
		table.setModel(model);
		table.getColumnModel().getColumn(1).setPreferredWidth(112);
		scrollPane.setViewportView(table);
		
		txtTotal = new JTextField();
		txtTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotal.setColumns(10);
		txtTotal.setBounds(106, 400, 159, 24);
		txtTotal.setEditable(false);
		contentPanel.add(txtTotal);
		
		JLabel lblTotlaPrice = new JLabel("Totla price: ");
		lblTotlaPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotlaPrice.setBounds(28, 405, 91, 14);
		contentPanel.add(lblTotlaPrice);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (txtInvoiceNumber.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please input invoice number!");
					return;
				} else if (txtQauntity.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please input qauntity!");
					return;
				} else if (txtUnitPrice.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please input unit price!");
					return;
				} else {
					
					rabtnImportDrink.setEnabled(false);
					rabtnImportRawMaterial.setEnabled(false);
														
					int itemId = ((Item) cboProduct.getSelectedItem()).getValue();										
					String itemName = cboProduct.getSelectedItem().toString();
					
					
					System.out.println(itemId + itemName);
					
					double qty = Double.parseDouble(txtQauntity.getText());
					double unitPrice = Double.parseDouble(txtUnitPrice.getText());
					double  amount = qty * unitPrice;
					
					total = total + amount;
					
					String[] newRow = new String[] {itemName, qty + "", Formatter.numberToText(unitPrice) , Formatter.numberToText(amount) };
					model.addRow(newRow);
					
					txtTotal.setText(total + "");
					
					if (rabtnImportDrink.isSelected()) {
						ImportDrinkDetail importDrinkDetail = new ImportDrinkDetail(0, itemId, 0, qty, unitPrice, amount, true);
						importDrinkList.add(importDrinkDetail);
						
					} else {
						
						ImportRawMaterialDetail importRawMaterialDetail = new ImportRawMaterialDetail(0, itemId, 0, qty, unitPrice, amount, true);
						importRawMaterialList.add(importRawMaterialDetail);
					}
					
					txtQauntity.setText("");
					txtUnitPrice.setText("");
				}	
			}
		});
		btnAdd.setIcon(new ImageIcon(ImportInsertDialog.class.getResource("/resources/Add_20.png")));
		btnAdd.setBounds(350, 191, 91, 28);
		contentPanel.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getRowCount() > 0) {
					if (selectedIndex >= 0) {
						model.removeRow(selectedIndex);		
					} else {
						JOptionPane.showMessageDialog(null, "Please select a row on table");
					}
				}
			}
		});
		btnRemove.setIcon(new ImageIcon(ImportInsertDialog.class.getResource("/resources/Cancel_20.png")));
		btnRemove.setBounds(445, 191, 101, 28);
		contentPanel.add(btnRemove);
		
		rabtnImportDrink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rabtnImportDrink.isSelected() == true) {
					cboProduct.removeAllItems();
					try {
						stImportType = con.createStatement();
						rsImportType = stImportType.executeQuery("select id, name from product where type = \"Drink\" AND status = true");
						while (rsImportType.next()) {
							int id = rsImportType.getInt("id");
							String name = rsImportType.getString("name");
							
							cboProduct.addItem(new Item(id, name));
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
					
					if (cboProduct.getItemCount() == 0) {
						JOptionPane.showMessageDialog(null, "Please input drink!");
					}
				}
		    }
		});
		
		
		rabtnImportRawMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rabtnImportRawMaterial.isSelected() == true) {
					cboProduct.removeAllItems();
					try {
						stImportType = con.createStatement();
						rsImportType = stImportType.executeQuery("select * from raw_material where status = true");
						while(rsImportType.next()) {
							int id = rsImportType.getInt("id");
							String name = rsImportType.getString("name");
							
							cboProduct.addItem(new Item(id, name));
						}
						
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
					
					if (cboProduct.getItemCount() == 0) {
						JOptionPane.showMessageDialog(null, "Please input raw material!");
					}
				}
		    }
		});
		
		table.getSelectionModel().addListSelectionListener(new RowListener());
		
		/** Validation fields */
		InputControl.inputFloatingPoint(txtQauntity);
		InputControl.inputFloatingPoint(txtUnitPrice);
		
		JLabel lblImportType = new JLabel("Import Type");
		lblImportType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblImportType.setBounds(30, 106, 119, 14);
		contentPanel.add(lblImportType);
		{
			JButton btnSave = new JButton("Save");
			btnSave.setIcon(new ImageIcon(ImportInsertDialog.class.getResource("/resources/Add_20.png")));
			btnSave.setBounds(353, 397, 91, 30);
			contentPanel.add(btnSave);
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(table.getModel().getRowCount() == 0) {
						MessageShow.Error("Please import before save to database.", "Import");
						return;
					}
					
					String invoiceNumber = txtInvoiceNumber.getText();
					Date importDate = dcImportDate.getDate();
					
					int userId = DbConnection.user.getId();
					
					if (rabtnImportDrink.isSelected()) {
						
						importDrinkDao = new ImportDrinkDao();
						
						ImportDrink importDrink = new ImportDrink(0, importDate, invoiceNumber, userId, total, true);
						
						if (importDrinkDao.insertImportDrink(importDrink) ) {
							
							int lastImportDrinkId = Help.getLastAutoIncrement("restaurant_project", "import_drink");
							importDrink.setId(lastImportDrinkId);
	
							if (insertIntoImportDrinkDetail(lastImportDrinkId)) {
								JOptionPane.showMessageDialog(null, "Inserted successfully!");
							}
						}
						
					} else {
						
						importRawMaterialDao = new ImportRawMaterialDao();
						
						ImportRawMaterial importRawMaterial = new ImportRawMaterial(0, importDate, invoiceNumber, userId, total, true);
						
						if (importRawMaterialDao.insertImportRawMaterial(importRawMaterial)) {
							
							int lastImportRawMaterialId = Help.getLastAutoIncrement("restaurant_project", "import_rm");
							
							importRawMaterial.setId(lastImportRawMaterialId);
	
							if (insertIntoImportRawMaterialDetail(lastImportRawMaterialId)) {
								
								JOptionPane.showMessageDialog(null, "Inserted successfully!");
							}
						}
					}
					
					rabtnImportDrink.setEnabled(true);
					rabtnImportRawMaterial.setEnabled(true);
					
					clearTextBox();
					model.setRowCount(0);
				}
			});
			btnSave.setActionCommand("OK");
			getRootPane().setDefaultButton(btnSave);
		}
		{
			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancel.setIcon(new ImageIcon(ImportInsertDialog.class.getResource("/resources/Cancel_20.png")));
			btnCancel.setBounds(454, 397, 91, 30);
			contentPanel.add(btnCancel);
			btnCancel.setActionCommand("Cancel");
		}
	}
	
	
	class RowListener implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting()) return;
			selectedIndex = table.getSelectedRow();
		}
	}
	
	private boolean insertIntoImportRawMaterialDetail(int lastImportRawMaterialId) {
		
		boolean success = false;
		ImportRawMaterialDao importRawMaterialDao = new ImportRawMaterialDao();
		int importRawMaterialDetailCount = 0;
		
		for(ImportRawMaterialDetail importRawMaterialDetail: importRawMaterialList) {
			
			importRawMaterialDetail.setImportRawMaterialId(lastImportRawMaterialId);
			if(importRawMaterialDao.insertIntoImportRawMaterialDetail(importRawMaterialDetail)) {
				importRawMaterialDetailCount ++;
			}
		}
		if(importRawMaterialDetailCount == importRawMaterialList.size()) {
			success = true;
		}
		
		return success;
	}
	
	private boolean insertIntoImportDrinkDetail(int lastImportDrinkId) {
			
		boolean success = false;
		ImportDrinkDao importDrinklDao = new ImportDrinkDao();
		int importDrinkDetailCount = 0;
		
		for(ImportDrinkDetail importDrinkDetail: importDrinkList) {
			
			importDrinkDetail.setImportDrinkId(lastImportDrinkId);
			
			if(importDrinklDao.insertIntoImportDrinkDetail(importDrinkDetail)) {
				importDrinkDetailCount ++;
			}
		}
		if(importDrinkDetailCount == importDrinkList.size()) {
			success = true;
		}
		
		return success;
	}
	
	public void setCallBackListener(CallBackListenter callBack) {
		this.callBack = callBack;
	}
	
	private void clearTextBox() {
		txtTotal.setText("");
		txtInvoiceNumber.setText("");
		txtQauntity.setText("");
		txtUnitPrice.setText("");
	}
}
