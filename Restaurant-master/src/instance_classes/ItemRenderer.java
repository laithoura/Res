package instance_classes;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class ItemRenderer extends BasicComboBoxRenderer
{
    public Component getListCellRendererComponent(
        JList list, Object value, int index,
        boolean isSelected, boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);	 
            if (value != null)
            {
                ComboBoxItem item = (ComboBoxItem)value;
                setText( item.getDescription() );
            }
 
            if (index == -1)
            {
                ComboBoxItem item = (ComboBoxItem)value;
                setText(item.toString() );
            }
            return this;
        }
}    