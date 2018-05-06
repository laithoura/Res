package panel;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import control_classes.ColorModel;
import controller.TableDao;
import instance_classes.Table;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Component;
import java.awt.SystemColor;
import javax.swing.JRadioButton;

public class DisplayTablePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private final int TABLE_WIDTH = 150;
	private final int TABLE_HEIGHT = 150;
	private ArrayList<Table> tableList;
	private JRadioButton radioButtonNormal, radioButtonAll, radioButtonVIP;
	private ButtonGroup buttonGroup;
	private TableDao tableDao;
	private JPanel panelContainer;
	private JMenuItem menuItemToAvailable, menuItemToUnavailable;
	private JPopupMenu popupMenu ;
	
	private String lableHoverText;
	
	public DisplayTablePanel() {
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBackground(SystemColor.control);
		add(panel, BorderLayout.NORTH);
		
		radioButtonAll = new JRadioButton("All");
		radioButtonAll.setSelected(true);
		radioButtonAll.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(radioButtonAll);
		
		radioButtonVIP = new JRadioButton("VIP");
		radioButtonVIP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(radioButtonVIP);
		
		radioButtonNormal = new JRadioButton("Normal");
		radioButtonNormal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(radioButtonNormal);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButtonAll);
		buttonGroup.add(radioButtonVIP);
		buttonGroup.add(radioButtonNormal);
		
		
		panelContainer = new JPanel();		
		panelContainer.setBackground(SystemColor.control);
		panelContainer.setBorder(new EmptyBorder(10, 50, 0, 10));
		
		JScrollPane scrollPane = new JScrollPane(panelContainer);		
		add(scrollPane, BorderLayout.CENTER);
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);		
		
		popupMenu = new JPopupMenu();
		popupMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Hello World");			
			}
		});
		
		
		menuItemToAvailable = new JMenuItem("To Available");
		popupMenu.add(menuItemToAvailable);
		
		menuItemToUnavailable = new JMenuItem("To Unavailable");
		popupMenu.add(menuItemToUnavailable);
		
		
		tableList = new ArrayList<>();
		
		tableDao =  new TableDao();
		
		displayAllTables();	
		
		registerEvent();
	}
	
		
	private void registerEvent() {
		
		radioButtonAll.addActionListener(this);
		radioButtonNormal.addActionListener(this);
		radioButtonVIP.addActionListener(this);
		
		menuItemToAvailable.addActionListener(this);
		menuItemToUnavailable.addActionListener(this);
	}

	

	private void displayTableType(String type) {
		
		panelContainer.removeAll();
		panelContainer.revalidate();
		panelContainer.repaint();
		
		tableList = tableDao.getTableByType(type, true);
		
		double itemCount = tableList.size();
		double itemPerRow = 5;
		double totalRow = Math.ceil(itemCount/itemPerRow);	
		
		panelContainer.setLayout(new GridLayout((int)totalRow, (int)itemPerRow,(int)itemPerRow,(int)itemPerRow));	
		
		//panelContainer.setLayout(new GridLayout(totalRow, itemPerRow,0,0));	
		
		for(Table table : tableList) {
			panelContainer.add(createTableLabel(table));			
		}
	}
	
	private void displayAllTables() {
		
		panelContainer.removeAll();
		panelContainer.revalidate();
		panelContainer.repaint();
		
		tableList = tableDao.getAllTableLists(true);
		
		double itemCount = tableList.size();
		double itemPerRow = 5;
		double totalRow = Math.ceil(itemCount/itemPerRow);	
		
		panelContainer.setLayout(new GridLayout((int)totalRow, (int)itemPerRow,(int)itemPerRow,(int)itemPerRow));	
		
		for(Table table : tableList) {
			panelContainer.add(createTableLabel(table));			
		}
	}



	private JLabel createTableLabel(Table table) {
		
		JLabel labelTable = new JLabel(table.getName());
		
		Image image = new ImageIcon("src/resources/table.png").getImage().getScaledInstance(TABLE_WIDTH, TABLE_HEIGHT, Image.SCALE_DEFAULT);
		
		labelTable.setHorizontalAlignment(JLabel.CENTER);
		
		labelTable.setHorizontalTextPosition(JLabel.CENTER);
		
		labelTable.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		if(table.isAvailable()) {
			labelTable.setForeground(Color.WHITE);
		}else {
			labelTable.setForeground(ColorModel.getDarkRed());

		}		
				
		labelTable.setIcon(new ImageIcon(image));
		
		Border border = labelTable.getBorder();
		Border margin = new EmptyBorder(10,10,10,10);
		labelTable.setBorder(new CompoundBorder(border, margin));
		
		addPopup(labelTable, popupMenu);
		
		labelTable.addMouseListener(new MouseAdapter() {
			
		public void mouseEntered(MouseEvent e) {
			
				lableHoverText = labelTable.getText().trim();
				System.out.println(lableHoverText);
				
			}
		});
		
		return labelTable;	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == radioButtonAll) {
			if(radioButtonAll.isSelected()) {
				displayAllTables();
			}
				
		}else if(e.getSource() == radioButtonNormal) {
			if(radioButtonNormal.isSelected()) {
				displayTableType("Normal");
			}
		}else if(e.getSource() == radioButtonVIP) {
			if(radioButtonVIP.isSelected()) {
				displayTableType("VIP");
			}				
		}else if(e.getSource() == menuItemToAvailable) {

			TableDao tableDao = new TableDao();
			for(Table table : tableList) {
				if(table.getName().equals(lableHoverText)) {
					if(tableDao.setAvailability(table.getId(), true)) {
						break;
					}					
				}
			}
			
			if(radioButtonAll.isSelected()) {
				displayAllTables();
			}else if(radioButtonNormal.isSelected()) {
				displayTableType("Normal");
			}else if(radioButtonVIP.isSelected()) {
				displayTableType("VIP");
			}
			
		}else if(e.getSource() == menuItemToUnavailable) {
			
			TableDao tableDao = new TableDao();
			for(Table table : tableList) {
				if(table.getName().equals(lableHoverText)) {
					if(tableDao.setAvailability(table.getId(), false)) {
						break;
					}				
				}
			}
			
			if(radioButtonAll.isSelected()) {
				displayAllTables();
			}else if(radioButtonNormal.isSelected()) {
				displayTableType("Normal");
			}else if(radioButtonVIP.isSelected()) {
				displayTableType("VIP");
			}		
		}/*Set to unavailable*/
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
