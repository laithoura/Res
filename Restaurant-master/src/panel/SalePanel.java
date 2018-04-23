package panel;

import javax.swing.JPanel;
import instance_classes.ComboBoxItem;
import instance_classes.ItemRenderer;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;

public class SalePanel extends JPanel {
	private JTable tableSale;
	
	
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("unchecked")
	public SalePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelHeader = new JPanel();
		add(panelHeader, BorderLayout.NORTH);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		
		JButton buttonNew = new JButton("New");
		buttonNew.setIcon(new ImageIcon(SalePanel.class.getResource("/resources/Add_20.png")));
		buttonNew.setMinimumSize(new Dimension(65, 23));
		buttonNew.setMaximumSize(new Dimension(65, 23));
		
		JButton buttonUpdate = new JButton("Update");
		buttonUpdate.setIcon(new ImageIcon(SalePanel.class.getResource("/resources/Edit_20.png")));
		buttonUpdate.setMinimumSize(new Dimension(65, 23));
		buttonUpdate.setMaximumSize(new Dimension(65, 23));
		
		JButton buttonDetail = new JButton("Detail");
		buttonDetail.setIcon(new ImageIcon(SalePanel.class.getResource("/resources/Details_20.png")));
		buttonDetail.setMinimumSize(new Dimension(65, 23));
		buttonDetail.setMaximumSize(new Dimension(65, 23));
		
		JButton buttonDelete = new JButton("Delete");
		buttonDelete.setIcon(new ImageIcon(SalePanel.class.getResource("/resources/Cancel_20.png")));
		
		JButton buttonExport = new JButton("Export");
		buttonExport.setIcon(new ImageIcon(SalePanel.class.getResource("/resources/Excel_20.png")));
		GroupLayout gl_panelHeader = new GroupLayout(panelHeader);
		gl_panelHeader.setHorizontalGroup(
			gl_panelHeader.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 661, Short.MAX_VALUE)
				.addGroup(gl_panelHeader.createSequentialGroup()
					.addContainerGap()
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addGap(18, 23, Short.MAX_VALUE)
					.addComponent(buttonNew, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonDetail, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(buttonDelete, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(buttonExport, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panelHeader.setVerticalGroup(
			gl_panelHeader.createParallelGroup(Alignment.LEADING)
				.addGap(0, 48, Short.MAX_VALUE)
				.addGroup(gl_panelHeader.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panelHeader.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonExport, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonDelete, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonDetail, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonUpdate, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonNew, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		panelHeader.setLayout(gl_panelHeader);
		
		JPanel panelContainer = new JPanel();
		add(panelContainer, BorderLayout.CENTER);
		panelContainer.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneSale = new JScrollPane();
		panelContainer.add(scrollPaneSale, BorderLayout.CENTER);
		
		tableSale = new JTable();
		scrollPaneSale.setViewportView(tableSale);
		
		JPanel panelFooter = new JPanel();
		add(panelFooter, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("New button");
		panelFooter.add(btnNewButton);
		
		/*Vector<ComboBoxItem> comboBoxModel = new Vector<>();
		comboBoxModel.addElement(new ComboBoxItem(0, "Select here"));		
		comboBoxModel.addElement(new ComboBoxItem(1, "A"));
		comboBoxModel.addElement(new ComboBoxItem(2, "B"));
		comboBoxModel.addElement(new ComboBoxItem(3, "C"));
		comboBoxModel.addElement(new ComboBoxItem(4, "D"));
		
		JComboBox<ComboBoxItem> comboBox = new JComboBox<ComboBoxItem>(comboBoxModel);				
		comboBox.setRenderer(new ItemRenderer());
		
		comboBox.setBounds(146, 106, 216, 32);
		add(comboBox);						*/
	}
}
