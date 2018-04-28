package control_classes;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableSetting {
	
	public static void TableControl(JTable table) {
		
		/*Set font to Column Header*/	
		table.getTableHeader().setFont(new Font("Times New Romain", Font.PLAIN,14)); 
		
		 /* Set font to all records */
		table.setFont(new Font("Tahoma", Font.PLAIN,13));
		table.setRowHeight(25);
		
		/*Align Header Text to Center*/				
		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
	}
	
	public static void alignColumnToRight(JTable table,int colIndex) {
		
		DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
		centerRender.setHorizontalAlignment(JLabel.RIGHT);
		table.getColumnModel().getColumn(colIndex).setCellRenderer(centerRender);
	}
	
	public static void alignColumnToLeft(JTable table,int colIndex) {
			
			DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
			centerRender.setHorizontalAlignment(JLabel.LEFT);
			table.getColumnModel().getColumn(colIndex).setCellRenderer(centerRender);
	}
	
	public static void alignColumnToCenter(JTable table,int colIndex) {
		
		DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
		centerRender.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(colIndex).setCellRenderer(centerRender);
	}
	
}
