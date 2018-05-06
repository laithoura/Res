package panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import connection.DbConnection;
import control_classes.TableSetting;
import controller.ImportRawMaterialDao;
import data_table_model.ImportRawMaterialDataModel;
import data_table_model.ImportRawMaterialDetailDataModel;
import instance_classes.ImportRawMaterial;
import instance_classes.ImportRawMaterialDetail;

public class ImportRawMaterialDetailPanel extends JPanel {

	private JTable table = new JTable();;
	private ImportRawMaterialDetailDataModel importRawMaterialDetailDataModel;
	private ArrayList<ImportRawMaterialDetail> importRawMaterialDetialList;
	private ImportRawMaterialDao importRawMaterialDao;
	
	/**
	 * Create the panel.
	 */
	public ImportRawMaterialDetailPanel() {
		
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		importRawMaterialDetailDataModel = new ImportRawMaterialDetailDataModel();
		importRawMaterialDetialList = new ArrayList<ImportRawMaterialDetail>();
		importRawMaterialDao = new ImportRawMaterialDao();
		
		importRawMaterialDetialList = importRawMaterialDao.getImportRawMaterialDetailList();
		importRawMaterialDetailDataModel.setProuctList(importRawMaterialDetialList);
		
		table.setModel(importRawMaterialDetailDataModel);
		
		scrollPane.setViewportView(table);
		TableSetting.TableControl(table);
		
		/** Align right column of table */
		
		TableSetting.alignColumnToRight(table, 4);
		TableSetting.alignColumnToRight(table, 5);
		TableSetting.alignColumnToRight(table,6);

	}
	
	public void search(String search) {
		AbstractTableModel tableProduct =(AbstractTableModel)table.getModel();
		TableRowSorter<AbstractTableModel> tr = new TableRowSorter<AbstractTableModel>(tableProduct);
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(search));
	}

}
