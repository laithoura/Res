package panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JComboBox.KeySelectionManager;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import control_classes.Exporter;
import control_classes.Help;
import control_classes.MessageShow;
import control_classes.TableSetting;
import controller.TableDao;
import data_table_model.TableDataModel;
import dialog.InsertTableDialog;
import instance_classes.Table;
import interfaces.CallBackListenter;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.RenderingHints.Key;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TablePanel extends JPanel implements ActionListener{
	
	private TableDao tableDao = new TableDao();
	private TableDataModel tableModel;
	private ArrayList<Table> listTable;
	private JTable tableTableDetail;
	private JButton buttonNew, buttonUpdate, buttonDelete, buttonExport, buttonRefresh;
	private JLabel labelTotalRow;
	private JTextField textBoxSeach;
	/**
	 * Create the panel.
	 */
	public TablePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelHeader = new JPanel();
		add(panelHeader, BorderLayout.NORTH);
		
		buttonNew = new JButton("New");
		buttonNew.setIcon(new ImageIcon(TablePanel.class.getResource("/resources/Add_20.png")));
		buttonNew.setMinimumSize(new Dimension(65, 23));
		buttonNew.setMaximumSize(new Dimension(65, 23));
		
		buttonUpdate = new JButton("Update");
		buttonUpdate.setIcon(new ImageIcon(TablePanel.class.getResource("/resources/Edit_20.png")));
		buttonUpdate.setMinimumSize(new Dimension(65, 23));
		buttonUpdate.setMaximumSize(new Dimension(65, 23));
		
		buttonDelete = new JButton("Delete");
		buttonDelete.setIcon(new ImageIcon(TablePanel.class.getResource("/resources/Cancel_20.png")));
		
		buttonExport = new JButton("Export");
		buttonExport.setIcon(new ImageIcon(TablePanel.class.getResource("/resources/Excel_20.png")));
		
		buttonRefresh = new JButton("Refresh");
		buttonRefresh.setIcon(new ImageIcon(TablePanel.class.getResource("/resources/Refresh_20.png")));
		buttonRefresh.setMinimumSize(new Dimension(65, 23));
		buttonRefresh.setMaximumSize(new Dimension(65, 23));
		
		textBoxSeach = new JTextField();
		textBoxSeach.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					SearchTableList();
				}
			}			
		});
		
		
		textBoxSeach.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textBoxSeach.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SearchTableList();
			}
		});
		
		lblNewLabel.setToolTipText("Search");
		lblNewLabel.setIcon(new ImageIcon(TablePanel.class.getResource("/resources/Search_20.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_panelHeader = new GroupLayout(panelHeader);
		gl_panelHeader.setHorizontalGroup(
			gl_panelHeader.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelHeader.createSequentialGroup()
					.addContainerGap()
					.addComponent(textBoxSeach, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
					.addComponent(buttonRefresh, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonNew, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonDelete, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(buttonExport, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panelHeader.setVerticalGroup(
			gl_panelHeader.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHeader.createSequentialGroup()
					.addGroup(gl_panelHeader.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelHeader.createSequentialGroup()
							.addGap(5)
							.addGroup(gl_panelHeader.createParallelGroup(Alignment.BASELINE)
								.addComponent(buttonExport, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonDelete, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonUpdate, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonNew, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonRefresh, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(textBoxSeach, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panelHeader.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel)))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		panelHeader.setLayout(gl_panelHeader);
		
		JPanel panelBody = new JPanel();
		panelBody.setBorder(new EmptyBorder(1, 10, 0, 10));
		add(panelBody, BorderLayout.CENTER);
		panelBody.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelBody.add(scrollPane, BorderLayout.CENTER);
		
		tableTableDetail = new JTable();
		scrollPane.setViewportView(tableTableDetail);
		
		JPanel panelFooter = new JPanel();
		add(panelFooter, BorderLayout.SOUTH);
		
		labelTotalRow = new JLabel("Total Row ");
		labelTotalRow.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_panelFooter = new GroupLayout(panelFooter);
		gl_panelFooter.setHorizontalGroup(
			gl_panelFooter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFooter.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelTotalRow, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(614, Short.MAX_VALUE))
		);
		gl_panelFooter.setVerticalGroup(
			gl_panelFooter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFooter.createSequentialGroup()
					.addContainerGap(5, Short.MAX_VALUE)
					.addComponent(labelTotalRow, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panelFooter.setLayout(gl_panelFooter);
		
		loadDataIntoTable();
		TableSetting.TableControl(tableTableDetail);
		
		RegisterEvent();
	}
	
	private void RegisterEvent() {
		tableTableDetail.getSelectionModel().addListSelectionListener(new RowListener());
		
		this.buttonRefresh.addActionListener(this);
		this.buttonNew.addActionListener(this);
		this.buttonUpdate.addActionListener(this);
		this.buttonDelete.addActionListener(this);
		this.buttonExport.addActionListener(this);		
	}

	class RowListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting()) return;
			int selectedIndex = tableTableDetail.getSelectedRow();
			System.out.println(selectedIndex);
		}
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {		
		if(e.getSource() == buttonNew) {
			InsertTableDialog insertTable = new InsertTableDialog();
			insertTable.setCallBackListener(new CallBackListenter() {
				@Override
				public void CallBack(Object sender) {		
					Table table = (Table)sender;								
					if(tableDao.insertTable(table)) {
						table.setId(Help.getLastAutoIncrement("restaurant_project", "tables"));
						listTable.add(0, table);
						tableModel.setTableModel(listTable);
						tableModel.updateTableModel();
						
						MessageShow.success("Table was created successfully.", "Create Table");
					}else {
						MessageShow.Error("Table was created unsuccessfully.", "Create Table");
						return;
					}
				}
			});
			insertTable.setVisible(true);
		}else if(e.getSource() == buttonUpdate) {
			int selectedIndex = tableTableDetail.getSelectedRow();
			if(selectedIndex < 0) return;
			
			InsertTableDialog insertTable = new InsertTableDialog(listTable.get(selectedIndex));
			insertTable.setCallBackListener(new CallBackListenter() {				
				@Override
				public void CallBack(Object sender) {					
					Table table = (Table)sender;					
					if(tableDao.updateTable(table)) {
						
						listTable.set(selectedIndex, table);
						tableModel.setTableModel(listTable);
						tableModel.updateTableModel();
						
						MessageShow.success("Table was updated successfully.", "Update Table");
					}else {
						MessageShow.Error("Table was updated  unsuccessfully.", "Update Table");
					}
				}
			});
			insertTable.setVisible(true);
		}else if(e.getSource() == buttonDelete) {
			int selectedIndex = tableTableDetail.getSelectedRow();
			if(selectedIndex < 0) return;
			int choose = MessageShow.deleteVerification("Do you want to delete?","Delete Table");
			if(choose == 0) {
				if(tableDao.deleteTable(listTable.get(selectedIndex).getId())) {
					listTable.remove(selectedIndex);
					tableModel.updateTableModel();
					MessageShow.success("Table was deleted successfully.", "Delete Table");
					refreshTotalRow();
				}
			}
		}else if(e.getSource() == buttonExport) {
			Exporter.jtableToExcel(tableTableDetail);
		}else if(e.getSource() == buttonRefresh) {
			loadDataIntoTable();
		}
	}
	
	private void SearchTableList() {
		if(textBoxSeach.getText().equals(""))
		{	
			loadDataIntoTable();
			return;
		}					
		listTable =  tableDao.searchTableLists(textBoxSeach.getText().trim(), true);					
		tableModel.setTableModel(listTable);
		tableTableDetail.setModel(tableModel);
		tableModel.updateTableModel();	
		refreshTotalRow();		
	}
	
	private void loadDataIntoTable() {
		listTable = tableDao.getAllTableLists(true);
		
		tableModel = new TableDataModel();
		tableModel.setTableModel(listTable);			
		
		//tableTableDetail.setModel(tableModel);
		tableModel.updateTableModel();	
		refreshTotalRow();
	}

	private void refreshTotalRow() {
		labelTotalRow.setText(String.format("Total Row : %d", listTable.size()));
	}
	
}
