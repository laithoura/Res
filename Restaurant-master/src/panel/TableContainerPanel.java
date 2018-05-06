package panel;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableContainerPanel extends JPanel implements ActionListener {

	private JRadioButton radioButtonAdd, radioButtonView;
	private ButtonGroup buttonGroupTable;
	private JPanel panelContainer;
	/**
	 * Create the panel.
	 */
	public TableContainerPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelHeader = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelHeader.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panelHeader, BorderLayout.NORTH);
		
		radioButtonAdd = new JRadioButton("Table");
		radioButtonAdd.setSelected(true);
		radioButtonAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelHeader.add(radioButtonAdd);
		
		radioButtonView = new JRadioButton("View Table");
		radioButtonView.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelHeader.add(radioButtonView);
		
		panelContainer = new JPanel();
		add(panelContainer, BorderLayout.CENTER);
		panelContainer.setLayout(new BorderLayout(0, 0));

		buttonGroupTable = new ButtonGroup();
		buttonGroupTable.add(radioButtonAdd);
		buttonGroupTable.add(radioButtonView);
		
		radioButtonAdd.addActionListener(this);
		radioButtonView.addActionListener(this);
		
		addTablePanelToContainer();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == radioButtonAdd) {
			if(radioButtonAdd.isSelected()) {
				addTablePanelToContainer();
			}
		}else if(e.getSource() == radioButtonView) {
			if(radioButtonView.isSelected()) {
				addDisplayTablePaneTolContainer();
			}
		}
	}
	
	
	private void addTablePanelToContainer() {
		TablePanel tablePanel = new TablePanel();
		panelContainer.removeAll();					
		panelContainer.revalidate();
		panelContainer.add(tablePanel,BorderLayout.CENTER);	
	}
	
	
	private void addDisplayTablePaneTolContainer() {
		DisplayTablePanel displayTablePanel = new DisplayTablePanel();
		panelContainer.removeAll();					
		panelContainer.revalidate();
		panelContainer.add(displayTablePanel,BorderLayout.CENTER);	
	}

}
