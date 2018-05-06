package panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import connection.DbConnection;
import control_classes.Formatter;
import control_classes.TableSetting;
import controller.ImportDrinkDao;
import data_table_model.ImportDrinkDataModel;
import data_table_model.ImportRawMaterialDataModel;
import instance_classes.ImportDrink;
import instance_classes.ImportRawMaterial;

public class ImportDrinkPanel extends JPanel {
	
	private JTable table;
	private ImportDrinkDataModel importDrinkDataModel;
	private ArrayList<ImportDrink> importDrinkList;
	private ImportDrinkDao importDrinkDao;
	

	/**
	 * Create the panel.
	 */
	public ImportDrinkPanel() {
		
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		importDrinkDataModel = new ImportDrinkDataModel();
		importDrinkList = new ArrayList<ImportDrink>();
		
		importDrinkDao = new ImportDrinkDao();
		
		/* Show data in table */
		importDrinkList = importDrinkDao.getImportDrikList();
		importDrinkDataModel.setProuctList(importDrinkList);
		table.setModel(importDrinkDataModel);
		
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
