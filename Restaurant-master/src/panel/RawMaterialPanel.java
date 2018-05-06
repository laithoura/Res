package panel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JButton;
import java.util.ArrayList;

import control_classes.MessageShow;
import control_classes.TableSetting;
import controller.RawMaterialDao;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import dialog.*;
import instance_classes.RawMaterial;
import interfaces.CallBackListenter;

import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import data_table_model.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.RowFilter;
public class RawMaterialPanel extends JPanel implements CallBackListenter, ActionListener{
	
	private RawMaterialDataModel rawMaterialDataModel;
	private int selectedIndex = -1;
	ArrayList<RawMaterial> rawMaterialList ;
	private JTable table;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JTextField txtSearch;
	private RawMaterialEditDialog rawMaterialEdit;
	private RawMaterialDao rawMaterialDao;
	
	/**
	 * Create the panel.
	 */
	public RawMaterialPanel() {
		
		table = new JTable();
		rawMaterialDataModel = new RawMaterialDataModel();
		rawMaterialList = new ArrayList<RawMaterial>();
		rawMaterialDao = new RawMaterialDao();
		JPanel pnlBtn = new JPanel();
		pnlBtn.setBackground(Color.LIGHT_GRAY);
		
		btnAdd = new JButton("Add");
		btnAdd.setIcon(new ImageIcon(RawMaterialPanel.class.getResource("/resources/Add_20.png")));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RawMaterialInsertDialog rawMaterialInsert = new RawMaterialInsertDialog();
				rawMaterialInsert.setCallBackListener(new CallBackListenter() {
					
					@Override
					public void CallBack(Object sender) {
						rawMaterialList.add((RawMaterial) sender);
						rawMaterialDataModel.updateTable();				
					}
				});
				rawMaterialInsert.setVisible(true);				
			}
		});
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		btnEdit = new JButton("Edit");
		btnEdit.setIcon(new ImageIcon(RawMaterialPanel.class.getResource("/resources/Edit_20.png")));
		btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectedIndex < 0) {
					return;
				}
				int choose = MessageShow.deleteVerification("Do you want to delete?", "Delete");
				if(choose == 0) {
					RawMaterialDao rawMaterialDao = new RawMaterialDao();
					RawMaterial rawMaterial = new RawMaterial();
					rawMaterial = rawMaterialList.get(selectedIndex);
					
					if (rawMaterialDao.deleteRawMaterial(rawMaterial.getId())) {
						JOptionPane.showMessageDialog(null, "Deleted successfully");
						rawMaterialList.remove(selectedIndex);
						rawMaterialDataModel.updateTable();
						selectedIndex = -1;
					} else {
						JOptionPane.showMessageDialog(null, "Deleted unsuccessfully");
					}								
			
				}
			}
		});
		btnDelete.setIcon(new ImageIcon(RawMaterialPanel.class.getResource("/resources/Cancel_20.png")));
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		JPanel pnlTable = new JPanel();
		pnlTable.setMaximumSize(new Dimension(30000, 32767));
		pnlTable.setBackground(Color.LIGHT_GRAY);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		/* Show data on table */
		
		rawMaterialList = rawMaterialDao.getRawMaterialList();
		rawMaterialDataModel.setRawMaterialList(rawMaterialList);
		table.setModel(rawMaterialDataModel);
		scrollPane.setViewportView(table);
		
		GroupLayout gl_pnlTable = new GroupLayout(pnlTable);
		gl_pnlTable.setHorizontalGroup(
			gl_pnlTable.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
		);
		gl_pnlTable.setVerticalGroup(
			gl_pnlTable.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTable.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
					.addGap(1))
		);
		pnlTable.setLayout(gl_pnlTable);
		
		JLabel lblNewLabel = new JLabel("List of raw materials");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		txtSearch = new JTextField();
		/** Search function */
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				AbstractTableModel tableRawmaterial =(AbstractTableModel)table.getModel();
				String search = txtSearch.getText();
				TableRowSorter<AbstractTableModel> tr = new TableRowSorter<AbstractTableModel>(tableRawmaterial);
				table.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search));
			
			}
		});
		txtSearch.setColumns(10);
		
		JLabel lblSearch = new JLabel("Search: ");
		GroupLayout gl_pnlBtn = new GroupLayout(pnlBtn);
		gl_pnlBtn.setHorizontalGroup(
			gl_pnlBtn.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlBtn.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSearch)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
					.addComponent(btnAdd)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEdit)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDelete))
		);
		gl_pnlBtn.setVerticalGroup(
			gl_pnlBtn.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlBtn.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_pnlBtn.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDelete)
						.addComponent(btnEdit)
						.addComponent(btnAdd)
						.addComponent(lblSearch)
						.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
		);
		pnlBtn.setLayout(gl_pnlBtn);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlTable, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(pnlBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(7)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(pnlBtn, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(pnlTable, GroupLayout.PREFERRED_SIZE, 165, Short.MAX_VALUE)
					.addGap(23))
		);
		setLayout(groupLayout);
		table.getSelectionModel().addListSelectionListener(new RowListener());
		
		btnEdit.addActionListener(this);
		TableSetting.TableControl(table);

	}
	
	class RowListener implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting()) return;
			selectedIndex = table.getSelectedRow();
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		RawMaterial rawMaterial = null;
		if (rawMaterialList.size() > 0 && selectedIndex >= 0) { 
			rawMaterial = rawMaterialList.get(selectedIndex);
		}
		
		rawMaterialEdit = new RawMaterialEditDialog(rawMaterial);
				
		rawMaterialEdit.setCallBackListener(this);
		rawMaterialEdit.setVisible(true);	
	}

	@Override
	public void CallBack(Object sender) {

		rawMaterialList.set(selectedIndex, (RawMaterial)sender);
		//rawMaterialDataModel.setRawMaterialList(rawMaterialList);
		rawMaterialDataModel.updateTable();	
		
		rawMaterialEdit.setVisible(false);
		rawMaterialEdit.dispose();
	}
}
