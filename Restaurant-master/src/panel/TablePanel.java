package panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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

public class TablePanel extends JPanel implements ActionListener{
	
	private TableDao tableDao = new TableDao();
	private TableDataModel tableModel;
	private ArrayList<Table> listTable;
	private JTable tableTableDetail;
	private JButton buttonNew, buttonUpdate, buttonDelete, buttonExport;
	/**
	 * Create the panel.
	 */
	public TablePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelHeader = new JPanel();
		add(panelHeader, BorderLayout.NORTH);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		
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
		GroupLayout gl_panelHeader = new GroupLayout(panelHeader);
		gl_panelHeader.setHorizontalGroup(
			gl_panelHeader.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelHeader.createSequentialGroup()
					.addContainerGap()
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
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
					.addGap(5)
					.addGroup(gl_panelHeader.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonExport, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonDelete, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonUpdate, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonNew, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
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
		
		JButton button_5 = new JButton("New");
		button_5.setMinimumSize(new Dimension(65, 23));
		button_5.setMaximumSize(new Dimension(65, 23));
		panelFooter.add(button_5);
		
		LoadDataIntoTable();
		TableSetting.TableControl(tableTableDetail);
		
		RegisterEvent();
	}
	
	private void RegisterEvent() {
		tableTableDetail.getSelectionModel().addListSelectionListener(new RowListener());
		
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
	
	private void LoadDataIntoTable() {
		listTable = tableDao.getTableLists(true, true);
		
		tableModel = new TableDataModel();
		tableModel.setTableModel(listTable);			
		
		tableTableDetail.setModel(tableModel);
		
		tableModel.updateTableModel();		
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

						table.setId(Help.GetLastAutoIncrement("restaurant_project", "tables"));
						listTable.add(table);
						tableModel.setTableModel(listTable);
						tableModel.updateTableModel();
						
						MessageShow.success("Table was created successfully.", "Create Table");
					}else {
						MessageShow.Error("Table was created unsuccessfully.", "Create Table");
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
						
						MessageShow.success("Table was updated successfully.", "Create Table");
					}else {
						MessageShow.Error("Table was updated  unsuccessfully.", "Create Table");
					}
				}
			});
			insertTable.setVisible(true);
		}
	}
}
