package control_classes;

import java.awt.Font;

import javax.swing.JTable;

public class TableSetting {
	
	public static void TableControl(JTable table) {
		
		/*Set font to Column Header*/	
		table.getTableHeader().setFont(new Font("Times New Romain", Font.PLAIN,14)); 
		
		 /* Set font to all records */
		table.setFont(new Font("Tahoma", Font.PLAIN,13));
		table.setRowHeight(25);
		
	}
}
