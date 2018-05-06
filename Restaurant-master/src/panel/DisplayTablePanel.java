package panel;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import java.awt.GridLayout;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import control_classes.ColorModel;
import control_classes.MessageShow;

import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Component;
import javax.swing.JMenu;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;

public class DisplayTablePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	
	private final int TABLE_WIDTH = 150;
	private final int TABLE_HEIGHT = 150;
	
	private JPopupMenu popupMenu ;
	
	public DisplayTablePanel() {
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 0, 10, 0));
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Display Table");
		lblNewLabel.setFont(new Font("Vanna-English Kbach Khmer", Font.PLAIN, 22));
		panel.add(lblNewLabel);
		
		JPanel panelContainer = new JPanel();		
		panelContainer.setBackground(Color.WHITE);
		panelContainer.setBorder(new EmptyBorder(10, 50, 0, 10));
		
		JScrollPane scrollPane = new JScrollPane(panelContainer);		
		add(scrollPane, BorderLayout.CENTER);
		//panelContainer.setLayout(new GridLayout(100, 4, 10, 10));
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	   // scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
	    
		int itemCount =25;
		int itemPerRow = 4;
		int totalRow = Math.round(itemCount/ itemPerRow);		
		
		popupMenu = new JPopupMenu();
		popupMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Hello World");			
			}
		});
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Item 1");
		popupMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Item 2");
		popupMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Item 3");
		popupMenu.add(mntmNewMenuItem);
		
		panelContainer.setLayout(new GridLayout(totalRow, itemPerRow,0,0));		
		
		for (int i = 1; i <= itemCount; i++) {
						
			panelContainer.add(createTableLabel("T00"+i,i));
		}
		
	}
	
	private JLabel createTableLabel(String tableName, int colorType) {
		
		JLabel labelTable = new JLabel(tableName);
		
		Image image = new ImageIcon("src/resources/table.png").getImage().getScaledInstance(TABLE_WIDTH, TABLE_HEIGHT, Image.SCALE_DEFAULT);
		
		labelTable.setHorizontalAlignment(JLabel.CENTER);
		
		labelTable.setHorizontalTextPosition(JLabel.CENTER);
		
		labelTable.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		if(colorType % 2 == 0){
			labelTable.setForeground(Color.WHITE);
		}else {
			labelTable.setForeground(ColorModel.getDarkRed());
		}
				
		labelTable.setIcon(new ImageIcon(image));
		
		Border border = labelTable.getBorder();
		Border margin = new EmptyBorder(10,10,10,10);
		labelTable.setBorder(new CompoundBorder(border, margin));
		
		addPopup(labelTable, popupMenu);
		
		return labelTable;	
	}
	
	// Then on your component(s)
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
					System.out.println("Trigger");
				}
				//System.out.println(e.get`);
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
