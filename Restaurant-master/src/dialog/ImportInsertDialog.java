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

import com.toedter.calendar.JDateChooser;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.sql.*;
import java.util.ArrayList;

import instance_classes.*;
import connection.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class ImportInsertDialog extends JDialog{
	private Connection con = null;
	private Statement stImportType = null;
	private ResultSet rsImportType = null;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtInvoiceNumber;
	private JTextField txtQauntity;
	private JTextField txtUnitPrice;
	private JTextField txtDate;
	private JTable table;
	private JTextField txtTotal;
	private DefaultTableModel model;
	private JDateChooser dcImportDate;
	private double total = 0;
	private int selectedIndex = 0;
	private ArrayList<ImportRawMaterialDetail> importList; 

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
		con = DbConnection.getConnection();
		
		setBounds(100, 100, 535, 517);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
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
		
		dcImportDate = new JDateChooser();
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
				
		/*table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "Raw material/Drink", "Qauntity", "Unit price", "Amount"
			}
		));*/
		
		table.setModel(model);
		table.getColumnModel().getColumn(1).setPreferredWidth(112);
		scrollPane.setViewportView(table);
		
		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(111, 411, 159, 20);
		contentPanel.add(txtTotal);
		
		JLabel lblTotlaPrice = new JLabel("Totla price: ");
		lblTotlaPrice.setBounds(10, 414, 91, 14);
		contentPanel.add(lblTotlaPrice);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String invoiceNumber = txtInvoiceNumber.getText();
				String importDate = dcImportDate.getDate().toString();
				
				System.out.println(importDate);
				
				/*String importName = cboProduct.getSelectedItem().toString();
				double qty = Double.parseDouble(txtQauntity.getText());
				double unitPrice = Double.parseDouble(txtUnitPrice.getText());
				double  amount = qty * unitPrice;
				
				total = total + amount;
				
				String[] newRow = new String[] {importName, qty + "", unitPrice + "", amount + ""};
				model.addRow(newRow);
				txtTotal.setText(total + "");	*/
				
				
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
					try {
						stImportType = con.createStatement();
						rsImportType = stImportType.executeQuery("select id from product where status = true");
						while (rsImportType.next()) {
							cboProduct.addItem(rsImportType.getInt("id"));
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
		    }
		});
		
		
		rabtnImportRawMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					stImportType = con.createStatement();
					rsImportType = stImportType.executeQuery("select * from raw_material where status = true");
					while(rsImportType.next()) {
						cboProduct.addItem(rsImportType.getInt("id"));
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		    }
		});
		
		table.getSelectionModel().addListSelectionListener(new RowListener());
	}
	
	
	
	class RowListener implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting()) return;
			selectedIndex = table.getSelectedRow();
		}
	}

}
