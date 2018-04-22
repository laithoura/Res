package panel;

import javax.swing.JPanel;
import instance_classes.ComboBoxItem;
import instance_classes.ItemRenderer;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

public class SalePanel extends JPanel {
	
	
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("unchecked")
	public SalePanel() {
		setLayout(null);
		
		Vector<ComboBoxItem> comboBoxModel = new Vector<>();
		comboBoxModel.addElement(new ComboBoxItem(0, "Select here"));		
		comboBoxModel.addElement(new ComboBoxItem(1, "A"));
		comboBoxModel.addElement(new ComboBoxItem(2, "B"));
		comboBoxModel.addElement(new ComboBoxItem(3, "C"));
		comboBoxModel.addElement(new ComboBoxItem(4, "D"));
		
		JComboBox<ComboBoxItem> comboBox = new JComboBox<ComboBoxItem>(comboBoxModel);				
		comboBox.setRenderer(new ItemRenderer());
		
		comboBox.setBounds(146, 106, 216, 32);
		add(comboBox);						
	}
}
