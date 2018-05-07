package control_classes;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class Exporter {
	public static void jtableToExcel(JTable table){
		if(table.getModel().getRowCount() == 0) return;
		try{
	    	String name=null;
	        TableModel model = table.getModel();
	        JFileChooser chooser = new JFileChooser();
	        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
	        int result = chooser.showSaveDialog(null);

	        if (result == chooser.APPROVE_OPTION) { 
	           name=chooser.getSelectedFile().toString();
	        }
	        
	        FileWriter excel = new FileWriter(name+".xls");

	        for(int i = 0; i < model.getColumnCount(); i++){
	            excel.write(model.getColumnName(i) + "\t");
	        }

	        excel.write("\n");

	        for(int i=0; i< model.getRowCount(); i++) {
	            for(int j=0; j < model.getColumnCount(); j++) {
	                excel.write(model.getValueAt(i,j).toString()+"\t");
	            }
	            excel.write("\n");
	        }	        
	        excel.close();	   
	        
	    }catch(IOException e){ System.out.println(e); }
	}
}
