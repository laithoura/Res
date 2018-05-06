package panel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import control_classes.TableSetting;
import controller.ImportRawMaterialDao;
import data_table_model.ImportRawMaterialDataModel;
import instance_classes.ImportRawMaterial;

public class ImportRawMaterialPanel extends JPanel {
	
	private JTable table;
	private ImportRawMaterialDataModel importRawMaterialDataModel;
	private ArrayList<ImportRawMaterial> importRawMaterialList;
	private ImportRawMaterialDao importRawMaterialDao;

	/**
	 * Create the panel.
	 */
	public ImportRawMaterialPanel() {
		
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		importRawMaterialDataModel = new ImportRawMaterialDataModel();
		importRawMaterialList = new ArrayList<ImportRawMaterial>();
		importRawMaterialDao = new ImportRawMaterialDao();
		
		/*Show data on table */
		importRawMaterialList = importRawMaterialDao.getImportRawMaterialList();
		importRawMaterialDataModel.setProuctList(importRawMaterialList);
		table.setModel(importRawMaterialDataModel);
		
		scrollPane.setViewportView(table);
		TableSetting.TableControl(table);
		
		/** Align to right */
		TableSetting.alignColumnToRight(table, 3);
	}
	
	public void search(String search) {
		AbstractTableModel tableProduct =(AbstractTableModel)table.getModel();
		TableRowSorter<AbstractTableModel> tr = new TableRowSorter<AbstractTableModel>(tableProduct);
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(search));
	}
}
