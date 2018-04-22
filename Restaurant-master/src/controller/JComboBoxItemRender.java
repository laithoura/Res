package controller;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.plaf.basic.*;

import instance_classes.ComboBoxItem;
 
public class JComboBoxItemRender
{	
    public JComboBoxItemRender()
    {        
    	
    }
 
    public void setJcomboBoxModel(JComboBox<ComboBoxItem> comboBox, ComboBoxModel<ComboBoxItem> model)
    {        
        comboBox.setModel(model);
        comboBox.setRenderer( new ItemRenderer() );
    }
    
    class ItemRenderer extends BasicComboBoxRenderer
    {
        public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus)
	        {
	            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);	 
	            if (value != null)
	            {
	                ComboBoxItem item = (ComboBoxItem)value;
	                setText( item.getDescription().toUpperCase() );
	            }
	 
	            if (index == -1)
	            {
	                ComboBoxItem item = (ComboBoxItem)value;
	                setText( "" + item.getId() );
	            }  
	            return this;
	        }
    }    
}