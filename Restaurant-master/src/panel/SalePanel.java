package panel;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;

import control_classes.Exporter;
import control_classes.Formatter;
import control_classes.MessageShow;
import control_classes.TableSetting;
import controller.SaleDao;
import data_table_model.SaleDataModel;
import data_table_model.SaleDetailDataModel;
import dialog.SaleProductDialog;
import instance_classes.Sale;
import instance_classes.SaleDetail;
import interfaces.CallBackListenter;

import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;

public class SalePanel extends JPanel implements ActionListener{
	private JTable tableSale;
	private JTextField textBoxSearch;
	
	private JComboBox<?> comboBoxSearchType;
	private JDateChooser dateChooser;
	private JLabel labelSearch;
	
	private JButton buttonRefresh, buttonNew, buttonUpdate, buttonExport;
	private SaleDataModel saleDataModel;
	private ArrayList<Sale> saleList;
	private SaleDao saleDao = new SaleDao();
	private JCheckBox checkBoxShowSaleDetail;
	
	private SaleDetailDataModel saleDetailDataModel;
	private ArrayList<SaleDetail> saleDetailList;
	private JLabel labelTotalRows;
	private JLabel labelTotal;
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("unchecked")
	public SalePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelHeader = new JPanel();
		add(panelHeader, BorderLayout.NORTH);
		
		buttonNew = new JButton("New");
		buttonNew.setIcon(new ImageIcon(SalePanel.class.getResource("/resources/Add_20.png")));
		buttonNew.setMinimumSize(new Dimension(65, 23));
		buttonNew.setMaximumSize(new Dimension(65, 23));
		
		buttonUpdate = new JButton("Update");
		buttonUpdate.setIcon(new ImageIcon(SalePanel.class.getResource("/resources/Edit_20.png")));
		buttonUpdate.setMinimumSize(new Dimension(65, 23));
		buttonUpdate.setMaximumSize(new Dimension(65, 23));
		
		buttonExport = new JButton("Export");
		buttonExport.setIcon(new ImageIcon(SalePanel.class.getResource("/resources/Excel_20.png")));
		
		buttonRefresh = new JButton("Refresh");
		buttonRefresh.setIcon(new ImageIcon(SalePanel.class.getResource("/resources/Refresh_20.png")));
		buttonRefresh.setMinimumSize(new Dimension(65, 23));
		buttonRefresh.setMaximumSize(new Dimension(65, 23));
		
		JPanel panelSearch = new JPanel();
		panelSearch.setLayout(null);
		
		textBoxSearch = new JTextField();
		textBoxSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					/*If user Pressed Enter key and TextBox Search is Empty*/
					if(textBoxSearch.getText().equals("") && comboBoxSearchType.getSelectedIndex() != 1) { /*Selected Index = 1 Search by Sale Date*/
						
						refreshSaleTable();
						
					}else { /*If user Pressed Enter key and TextBox Search != Empty*/
						
						/*Search Sale aS user expected*/
						searchSaleInformation();
					}
				}
			}
		});
		textBoxSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textBoxSearch.setColumns(10);
		textBoxSearch.setBounds(0, 25, 203, 24);
		panelSearch.add(textBoxSearch);
		
		labelSearch = new JLabel("");
		labelSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchSaleInformation();
			}			
		});
		labelSearch.setIcon(new ImageIcon(SalePanel.class.getResource("/resources/Search_20.png")));
		labelSearch.setToolTipText("Search");
		labelSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelSearch.setBounds(212, 27, 36, 20);
		panelSearch.add(labelSearch);
		
		comboBoxSearchType = new JComboBox();		
		comboBoxSearchType.setModel(new DefaultComboBoxModel(new String[] {"Invoice No", "Sold Date", "Username"}));
		comboBoxSearchType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBoxSearchType.setBounds(0, 2, 203, 22);
		panelSearch.add(comboBoxSearchType);
		
		Date date = new Date();
		dateChooser = new JDateChooser(date);
		dateChooser.setBounds(0, 25, 203, 24);
		panelSearch.add(dateChooser);
		dateChooser.setDateFormatString("dd/MM/yyyy");
		panelSearch.add(dateChooser);	
		
		GroupLayout gl_panelHeader = new GroupLayout(panelHeader);
		gl_panelHeader.setHorizontalGroup(
			gl_panelHeader.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelHeader.createSequentialGroup()
					.addGap(11)
					.addComponent(panelSearch, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
					.addGap(120)
					.addComponent(buttonRefresh, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(buttonNew, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonExport, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panelHeader.setVerticalGroup(
			gl_panelHeader.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHeader.createSequentialGroup()
					.addGroup(gl_panelHeader.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelHeader.createSequentialGroup()
							.addGap(4)
							.addComponent(panelSearch, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelHeader.createSequentialGroup()
							.addGap(16)
							.addGroup(gl_panelHeader.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panelHeader.createParallelGroup(Alignment.LEADING)
									.addComponent(buttonRefresh, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_panelHeader.createParallelGroup(Alignment.BASELINE)
										.addComponent(buttonUpdate, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
										.addComponent(buttonNew, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
								.addComponent(buttonExport, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		checkBoxShowSaleDetail = new JCheckBox("Show Sale Details");
		checkBoxShowSaleDetail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		checkBoxShowSaleDetail.setBounds(209, 2, 124, 23);
		panelSearch.add(checkBoxShowSaleDetail);
		panelHeader.setLayout(gl_panelHeader);
		
		JPanel panelContainer = new JPanel();
		panelContainer.setBorder(new EmptyBorder(1, 10, 1, 10));
		add(panelContainer, BorderLayout.CENTER);
		panelContainer.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneSale = new JScrollPane();
		panelContainer.add(scrollPaneSale, BorderLayout.CENTER);
		
		tableSale = new JTable();
		scrollPaneSale.setViewportView(tableSale);
		
		JPanel panelFooter = new JPanel();
		add(panelFooter, BorderLayout.SOUTH);
		
		labelTotalRows = new JLabel("Total Row : 0");
		labelTotalRows.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		labelTotal = new JLabel("Total : 0.00 Riel");
		labelTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_panelFooter = new GroupLayout(panelFooter);
		gl_panelFooter.setHorizontalGroup(
			gl_panelFooter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFooter.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelTotalRows)
					.addPreferredGap(ComponentPlacement.RELATED, 507, Short.MAX_VALUE)
					.addComponent(labelTotal, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panelFooter.setVerticalGroup(
			gl_panelFooter.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelFooter.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panelFooter.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelTotalRows, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelTotal, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panelFooter.setLayout(gl_panelFooter);
		
		saleDataModel = new SaleDataModel();
		saleList = saleDao.getSaleLists(true);
		
		loadSaleTableModel();
		
		registerEvent();
		hideSearchControls();
		alignSaleTableField();
		TableSetting.TableControl(tableSale);
		
	}
	private void alignSaleTableField() {		
		TableSetting.alignColumnToCenter(tableSale, 0); /*invoice no*/
		TableSetting.alignColumnToCenter(tableSale, 1); /*Date*/
		TableSetting.alignColumnToLeft(tableSale, 2); /*Username*/
		TableSetting.alignColumnToRight(tableSale, 3); /*Total*/
	}
	private void registerEvent() {				
		
		this.buttonNew.addActionListener(this);
		this.buttonRefresh.addActionListener(this);
		this.buttonUpdate.addActionListener(this);
		this.buttonExport.addActionListener(this);
		this.comboBoxSearchType.addActionListener(this);
		
		this.checkBoxShowSaleDetail.addActionListener(this);
	}
	
	private void hideSearchControls() {	
				
		dateChooser.setVisible(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == buttonNew) {
			
			SaleProductDialog saleDailog = new SaleProductDialog();
			saleDailog.setCallBackListener(new CallBackListenter() {
				
				@Override
				public void CallBack(Object sender) {
					saleList.add(0,(Sale)sender);
					saleDataModel.updateTable();
				}
			});
			saleDailog.setVisible(true);
			
		}/*End New Button*/
		else if(e.getSource() == buttonRefresh) {
			
			refreshSaleTable();
			
		}/*End Refresh Button*/
		else if(e.getSource() == buttonExport) {
			
			if(tableSale.getModel().getRowCount() == 0) return;
			Exporter.jtableToExcel(tableSale);
		}/*End Export button*/
		
		else if(e.getSource() == comboBoxSearchType) {
			
			int selectedIndex = comboBoxSearchType.getSelectedIndex();
			if(selectedIndex == -1) return;
			textBoxSearch.setText("");
			textBoxSearch.setFocusable(true);
			textBoxSearch.requestFocus();	
			
			if(selectedIndex == 0) { /*Search by Invoice Number*/
				
				textBoxSearch.setVisible(true);					
				dateChooser.setVisible(false);										
				
			}else if(selectedIndex == 1) { /*Sale Date*/
				
				textBoxSearch.setVisible(false);					
				dateChooser.setVisible(true);
				
			}else if(selectedIndex == 2) { /*Username*/
				textBoxSearch.setVisible(true);					
				dateChooser.setVisible(false);															
			}else if(selectedIndex == 3) { /*Product Name*/
				
				textBoxSearch.setVisible(true);					
				dateChooser.setVisible(false);	
				
			}else if(selectedIndex == 4) { /*Product Type*/
				
				textBoxSearch.setVisible(true);					
				dateChooser.setVisible(false);				
			}
			
		}else if(e.getSource() == checkBoxShowSaleDetail) {	
			
			/*User switches to View Sale Detail or Sale Report*/
			
			if(checkBoxShowSaleDetail.isSelected()) { /*Display Sale Details Report*/
												
				//if(saleList != null) saleList.clear(); /*Clear All Sale List Report*/
				
				saleDetailDataModel = new SaleDetailDataModel();
				saleDetailList = new ArrayList<>();
				saleDetailList = saleDao.getSaleDetailList(true);
				
				loadSaleDetailTableModel();
				
				comboBoxSearchType.setModel(new DefaultComboBoxModel(new String[] {"Invoice No", "Sold Date", "Username", "Product Name","Type"}));
				
			}else { /*Display Sale Report*/				
								
				if(saleDetailList != null) saleDetailList.clear(); /*Clear All Sale Details List Report to Release Memory Location*/
				
				loadSaleTableModel();				
				comboBoxSearchType.setModel(new DefaultComboBoxModel(new String[] {"Invoice No", "Sold Date", "Username"}));
			}
		}
	}
	
	private void refreshSaleTable() {
		
		if(checkBoxShowSaleDetail.isSelected()) {
			
			if(saleDetailList != null) saleDetailList.clear();
			saleDetailList = saleDao.getSaleDetailList(true);
			loadSaleDetailTableModel();
		}else {
			if(saleList != null) saleList.clear();
			saleList = saleDao.getSaleLists(true);
			loadSaleTableModel();
		}
	}
	
	private void loadSaleDetailTableModel() {
		
		saleDetailDataModel.setSaleDetailList(saleDetailList);
		tableSale.setModel(saleDetailDataModel);
		saleDetailDataModel.updateTable();					
		alignSaleDetailTableField();
		countTableRow();
		sumTotal();
	}
	private void alignSaleDetailTableField() {
		TableSetting.alignColumnToCenter(tableSale, 0); /*Invoice No*/
		TableSetting.alignColumnToCenter(tableSale, 1); /*Date*/
		TableSetting.alignColumnToCenter(tableSale, 4); /*Product Type*/		
		TableSetting.alignColumnToCenter(tableSale, 5); /*Quantity*/
		TableSetting.alignColumnToRight(tableSale, 6); /*Unit Price*/
		TableSetting.alignColumnToRight(tableSale, 7); /*Amount*/
	}
	private void searchSaleInformation() {		
		int selectedIndex = comboBoxSearchType.getSelectedIndex();
		Object condition = null;
		if(selectedIndex == -1) return;
		 
		if(selectedIndex == 0) { /*Search by Invoice Number*/
			
			try {
				condition = Integer.parseInt(textBoxSearch.getText().trim());				
			}catch(NumberFormatException ex) {
				MessageShow.Error("Please input invoice number!", "Search Sale");
				return;
			}
			
		}else if(selectedIndex == 1) { /*Sale Date*/
			
			condition = dateChooser.getDate();		
			if(condition == null) {
				MessageShow.Error("Please choose date!", "Search Sale");
				return;
			}
		}else if(selectedIndex == 2) { /*Username*/
			
			condition = textBoxSearch.getText().trim();		
			if(condition.equals("")) {
				MessageShow.Error("Please input username!", "Search Sale");
				return;
			}			
		}else if(selectedIndex == 3) { /*Product Name*/
			
			condition = textBoxSearch.getText().trim();		
			if(condition.equals("")) {
				MessageShow.Error("Please input product name!", "Search Sale");
				return;
			}
			
		}else if(selectedIndex == 4) { /*Product Type*/
			
			condition = textBoxSearch.getText().trim();	
			if(condition.equals("")) {
				MessageShow.Error("Please input product type!", "Search Sale");
				return;
			}
		}
		
		if(checkBoxShowSaleDetail.isSelected()) {
			
			/*Search SaleDetail Report*/
			if(saleDetailList != null) saleDetailList.clear();
			saleDetailList = saleDao.searchSaleDetail(condition,selectedIndex);
			loadSaleDetailTableModel();
		}else {
			
			/*Search Sale Report*/
			if(saleList != null) saleList.clear();
			saleList = saleDao.searchSaleList(condition, selectedIndex);
			loadSaleTableModel();
		}
	}
	
	private void loadSaleTableModel() {
		
		saleDataModel.setSaleList(saleList);
		tableSale.setModel(saleDataModel);
		saleDataModel.updateTable();
		alignSaleTableField();
		countTableRow();
		sumTotal();
	}
	
	private void countTableRow() {		
		labelTotalRows.setText(String.format("Total Rows : %d rows", tableSale.getModel().getRowCount() ));
	}
	
	private void sumTotal() {
		
		double total = 0;
		if(checkBoxShowSaleDetail.isSelected()) {
			
			total = 0;
			for(SaleDetail saleDetail: saleDetailList) {
				total += saleDetail.getAmount();
			}
		}else {
			
			total = 0;
			for(Sale sale:saleList) {
				total += sale.getTotal();
			}
		}
		labelTotal.setText(Formatter.numberToText(total) + " Riel");
	}
}
