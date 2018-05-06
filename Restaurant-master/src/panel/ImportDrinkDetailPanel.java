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
import controller.ImportDrinkDao;
import data_table_model.ImportDrinkDetailDataModel;
import instance_classes.ImportDrinkDetail;


public class ImportDrinkDetailPanel extends JPanel {
	
	private JTable table = new JTable();;
	private ImportDrinkDetailDataModel importDrinkDetailDataModel;
	private ArrayList<ImportDrinkDetail> importDrinkDetialList;
	private ImportDrinkDao importDrinkDao;
	/**
	 * Create the panel.
	 */
	public ImportDrinkDetailPanel() {
		
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		importDrinkDetailDataModel = new ImportDrinkDetailDataModel();
		importDrinkDetialList = new ArrayList<ImportDrinkDetail>();
		
		importDrinkDao = new ImportDrinkDao();
		importDrinkDetialList = importDrinkDao.getImportDrinkDetailList();
		importDrinkDetailDataModel.setProuctList(importDrinkDetialList);
		table.setModel(importDrinkDetailDataModel);
		
		scrollPane.setViewportView(table);
		TableSetting.TableControl(table);
		
		/** Align column of table */
		TableSetting.alignColumnToRight(table, 4);
		TableSetting.alignColumnToRight(table, 5);
		TableSetting.alignColumnToRight(table, 6);

	}
	
	public void search(String search) {
		AbstractTableModel tableProduct =(AbstractTableModel)table.getModel();
		TableRowSorter<AbstractTableModel> tr = new TableRowSorter<AbstractTableModel>(tableProduct);
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(search));
	}

}
