package dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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

import com.mysql.jdbc.PreparedStatement;
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
import control_classes.Help;
import control_classes.InputControl;
import control_classes.Item;
import controller.ImportDrinkDao;
import controller.ImportRawMaterialDao;
import controller.SaleDao;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;


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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ImportInsertDialog dialog = new ImportInsertDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ImportInsertDialog(){
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
		
		setBounds(100, 100, 535, 517);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblImport = new JLabel("Import ");
		lblImport.setBounds(10, 11, 46, 14);
		contentPanel.add(lblImport);
		
		JLabel lblInvoiceNumber = new JLabel("Invoice number");
		lblInvoiceNumber.setBounds(10, 44, 119, 14);
		contentPanel.add(lblInvoiceNumber);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 69, 46, 14);
		contentPanel.add(lblDate);
		
		JLabel lblRawMaterialdrink = new JLabel("Raw material/Drink");
		lblRawMaterialdrink.setBounds(10, 136, 130, 14);
		contentPanel.add(lblRawMaterialdrink);
		
		JLabel lblQauntity = new JLabel("Qauntity");
		lblQauntity.setBounds(10, 164, 91, 14);
		contentPanel.add(lblQauntity);
		
		JLabel label_4 = new JLabel("Import ");
		label_4.setBounds(10, 11, 46, 14);
		contentPanel.add(label_4);
		
		JLabel lblUnitPrice = new JLabel("Unit price");
		lblUnitPrice.setBounds(10, 189, 91, 14);
		contentPanel.add(lblUnitPrice);
		
		txtInvoiceNumber = new JTextField();
		txtInvoiceNumber.setBounds(161, 41, 159, 20);
		contentPanel.add(txtInvoiceNumber);
		txtInvoiceNumber.setColumns(10);
		
		txtQauntity = new JTextField();
		txtQauntity.setColumns(10);
		txtQauntity.setBounds(161, 161, 159, 20);
		contentPanel.add(txtQauntity);
		
		txtUnitPrice = new JTextField();
		txtUnitPrice.setColumns(10);
		txtUnitPrice.setBounds(161, 189, 159, 20);
		contentPanel.add(txtUnitPrice);
		
		Date nowDate = new Date();
		dcImportDate = new JDateChooser(nowDate);
		dcImportDate.setDateFormatString("dd/MM/yyyy");
		dcImportDate.setBounds(161, 69, 159, 20);
		contentPanel.add(dcImportDate);
		//dcImportDate.setDate(new Date(selectedIndex, selectedIndex, selectedIndex));
		
		JComboBox cboProduct = new JComboBox();
		cboProduct.setBounds(161, 133, 159, 20);
		contentPanel.add(cboProduct);	
		
		
		JRadioButton rabtnImportRawMaterial = new JRadioButton("Import raw material");
		rabtnImportRawMaterial.setBounds(10, 103, 149, 23);
		contentPanel.add(rabtnImportRawMaterial);
		
		JRadioButton rabtnImportDrink = new JRadioButton("Import drink");
		rabtnImportDrink.setBounds(161, 103, 109, 23);
		contentPanel.add(rabtnImportDrink);
		
		
		ButtonGroup rabtnGroup = new ButtonGroup();
		rabtnGroup.add(rabtnImportDrink);
		rabtnGroup.add(rabtnImportRawMaterial);
		rabtnImportDrink.setSelected(false);
		rabtnImportRawMaterial.setSelected(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 246, 499, 140);
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
		txtTotal.setColumns(10);
		txtTotal.setBounds(111, 411, 159, 20);
		txtTotal.setEditable(false);
		contentPanel.add(txtTotal);
		
		JLabel lblTotlaPrice = new JLabel("Totla price: ");
		lblTotlaPrice.setBounds(10, 414, 91, 14);
		contentPanel.add(lblTotlaPrice);
		
		JButton btnAdd = new JButton("Add");
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
					
					String invoiceNumber = txtInvoiceNumber.getText();
					String importDate = dcImportDate.getDate().toString();
					
					System.out.println(importDate);
					
					int imprortDetailId = cboProduct.getSelectedIndex();
					String importName = cboProduct.getSelectedItem().toString();
					double qty = Double.parseDouble(txtQauntity.getText());
					double unitPrice = Double.parseDouble(txtUnitPrice.getText());
					double  amount = qty * unitPrice;
					
					total = total + amount;
					
					String[] newRow = new String[] {importName, qty + "", unitPrice + "", amount + ""};
					model.addRow(newRow);
					txtTotal.setText(total + "");
					
					if (rabtnImportDrink.isSelected()) {
						ImportDrinkDetail importDrinkDetail = new ImportDrinkDetail(0, imprortDetailId, 0, qty, unitPrice, amount, true);
						importDrinkList.add(importDrinkDetail);
						
					} else {
						
						ImportRawMaterialDetail importRawMaterialDetail = new ImportRawMaterialDetail(0, imprortDetailId, 0, qty, unitPrice, amount, true);
						importRawMaterialList.add(importRawMaterialDetail);
					}
					
					txtQauntity.setText("");
					txtUnitPrice.setText("");
				}	
			}
		});
		
		btnAdd.setHorizontalAlignment(SwingConstants.LEFT);
		btnAdd.setIcon(new ImageIcon(ImportInsertDialog.class.getResource("/resources/Add_20.png")));
		btnAdd.setBounds(389, 44, 109, 23);
		contentPanel.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
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
		
		btnRemove.setHorizontalAlignment(SwingConstants.LEFT);
		btnRemove.setIcon(new ImageIcon(ImportInsertDialog.class.getResource("/resources/Cancel_20.png")));
		btnRemove.setBounds(389, 78, 109, 23);
		contentPanel.add(btnRemove);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSave = new JButton("Save");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String invoiceNumber = txtInvoiceNumber.getText();
						Date importDate = dcImportDate.getDate();
						int userId = 1;
						
						if (rabtnImportDrink.isSelected()) {
							importDrinkDao = new ImportDrinkDao();
							ImportDrink importDrink = new ImportDrink(0, importDate, invoiceNumber, 1, total, true);
							if (importDrinkDao.insertImportDrink(importDrink) ) {
								int lastImportDrinklId = Help.getLastAutoIncrement("restaurant_project", "import_drink_detail");
								importDrink.setId(lastImportDrinklId);
	
								if (insertIntoImportDrinkDetail(lastImportDrinklId)) {
									JOptionPane.showMessageDialog(null, "Inserted successfully!");
								}
							}
						} else {
							importRawMaterialDao = new ImportRawMaterialDao();
							ImportRawMaterial importRawMaterial = new ImportRawMaterial(0, importDate, invoiceNumber, userId, total, true);
							if (importRawMaterialDao.insertImportRawMaterialDao(importRawMaterial)) {
								
								int lastImportRawMaterialId = Help.getLastAutoIncrement("restaurant_project", "import_rm_detail");
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
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
		
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
				}
		    }
		});
		
		table.getSelectionModel().addListSelectionListener(new RowListener());
		
		/** Validation fields */
		InputControl.inputFloatingPoint(txtQauntity);
		InputControl.inputFloatingPoint(txtUnitPrice);
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
