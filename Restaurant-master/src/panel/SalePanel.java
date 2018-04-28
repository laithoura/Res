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
import java.util.ArrayList;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;

import control_classes.TableSetting;
import controller.SaleDao;
import data_table_model.SaleDataModel;
import instance_classes.Sale;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;

public class SalePanel extends JPanel {
	private JTable tableSale;
	private JTextField textBoxSearch;
	
	private JComboBox<?> comboBoxSearchType;
	private JDateChooser dateChooser;
	private JLabel labelSearch;
	
	private JButton buttonRefresh, buttonNew, buttonUpdate, buttonDetail, buttonExport;
	private SaleDataModel saleDataModel;
	private ArrayList<Sale> saleList;
	private SaleDao saleDao = new SaleDao();
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
		
		buttonDetail = new JButton("Detail");
		buttonDetail.setIcon(new ImageIcon(SalePanel.class.getResource("/resources/Details_20.png")));
		buttonDetail.setMinimumSize(new Dimension(65, 23));
		buttonDetail.setMaximumSize(new Dimension(65, 23));
		
		buttonExport = new JButton("Export");
		buttonExport.setIcon(new ImageIcon(SalePanel.class.getResource("/resources/Excel_20.png")));
		
		buttonRefresh = new JButton("Refresh");
		buttonRefresh.setIcon(new ImageIcon(SalePanel.class.getResource("/resources/Refresh_20.png")));
		buttonRefresh.setMinimumSize(new Dimension(65, 23));
		buttonRefresh.setMaximumSize(new Dimension(65, 23));
		
		JPanel panelSearch = new JPanel();
		panelSearch.setLayout(null);
		
		textBoxSearch = new JTextField();
		textBoxSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textBoxSearch.setColumns(10);
		textBoxSearch.setBounds(0, 25, 203, 24);
		panelSearch.add(textBoxSearch);
		
		labelSearch = new JLabel("");
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
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(0, 25, 203, 24);
		panelSearch.add(dateChooser);
		dateChooser.setDateFormatString("dd/MM/yyyy");
		panelSearch.add(dateChooser);	
		
		GroupLayout gl_panelHeader = new GroupLayout(panelHeader);
		gl_panelHeader.setHorizontalGroup(
			gl_panelHeader.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelHeader.createSequentialGroup()
					.addGap(11)
					.addComponent(panelSearch, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
					.addComponent(buttonRefresh, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(buttonNew, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonDetail, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
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
							.addGroup(gl_panelHeader.createParallelGroup(Alignment.LEADING)
								.addComponent(buttonRefresh, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panelHeader.createParallelGroup(Alignment.BASELINE)
									.addComponent(buttonExport, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
									.addComponent(buttonDetail, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
									.addComponent(buttonUpdate, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
									.addComponent(buttonNew, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
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
		
		JButton btnNewButton = new JButton("New button");
		panelFooter.add(btnNewButton);
		
		saleDataModel = new SaleDataModel();
		saleList = saleDao.getSaleLists(true);
		
		refreshTableModel();
		
		/*Vector<ComboBoxItem> comboBoxModel = new Vector<>();
		comboBoxModel.addElement(new ComboBoxItem(0, "Select here"));		
		comboBoxModel.addElement(new ComboBoxItem(1, "A"));
		comboBoxModel.addElement(new ComboBoxItem(2, "B"));
		comboBoxModel.addElement(new ComboBoxItem(3, "C"));
		comboBoxModel.addElement(new ComboBoxItem(4, "D"));
		
		JComboBox<ComboBoxItem> comboBox = new JComboBox<ComboBoxItem>(comboBoxModel);				
		comboBox.setRenderer(new ItemRenderer());
		
		comboBox.setBounds(146, 106, 216, 32);
		add(comboBox);						*/
		registerEvent();
		hideSearchControls();
		controlTable();
	}
	private void controlTable() {
		
		TableSetting.TableControl(tableSale);
		
		TableSetting.alignColumnToCenter(tableSale, 0); /*invoice no*/
		TableSetting.alignColumnToCenter(tableSale, 1); /*Date*/
		TableSetting.alignColumnToLeft(tableSale, 2); /*Username*/
		TableSetting.alignColumnToRight(tableSale, 3); /*Total*/
	}
	private void registerEvent() {				
		
	}
	private void refreshTableModel() {
		
		saleDataModel.setSaleList(saleList);
		tableSale.setModel(saleDataModel);
		saleDataModel.updateTable();
		
	}
	private void hideSearchControls() {	
				
		dateChooser.setVisible(false);
	}
}
