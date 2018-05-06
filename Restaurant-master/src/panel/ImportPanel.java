package panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import dialog.ImportInsertDialog;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ImportPanel extends JPanel {
	
	private JPanel panelTable;
	private JPanel panelButton;
	private JTextField txtSearch;
	private JLabel lblShow;
	
	private ImportRawMaterialPanel importRawMaterialPanel;
	private ImportRawMaterialDetailPanel importRawMaterialDetailPanel;
	private ImportDrinkDetailPanel importDrinkDetailPanel;
	private ImportDrinkPanel importDrinkPanel;
	private ImportInsertDialog importInsertDialog;

	/**
	 * Create the panel.
	 */
	public ImportPanel() {
		
		panelButton = new JPanel();
		panelTable = new JPanel();
		lblShow = new JLabel("");
		JButton btnAddImport = new JButton("Add import");
		btnAddImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				importInsertDialog = new ImportInsertDialog();
				importInsertDialog.setVisible(true);
				
			}
		});
		
		JButton btnShowImportRaw = new JButton("Show import raw material detail");
		btnShowImportRaw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disposeObjectPanel();
				lblShow.setText("List of import raw material detial");
				importRawMaterialDetailPanel = new ImportRawMaterialDetailPanel();
				panelTable.removeAll();
				panelTable.revalidate();
				panelTable.add(importRawMaterialDetailPanel, BorderLayout.CENTER);
			}

			
		});
		
		JButton btnShowImportDrink = new JButton("Show import drink detail");
		btnShowImportDrink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disposeObjectPanel();
				lblShow.setText("List of import drink detail");
				importDrinkDetailPanel = new ImportDrinkDetailPanel();
				panelTable.removeAll();
				panelTable.revalidate();
				panelTable.add(importDrinkDetailPanel, BorderLayout.CENTER);
			}
		});
		
		JButton btnImportDrink = new JButton("Show Import Drink");
		btnImportDrink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				disposeObjectPanel();
				lblShow.setText("List of import drinks");
				importDrinkPanel = new ImportDrinkPanel();
				panelTable.removeAll();
				panelTable.revalidate();
				panelTable.add(importDrinkPanel, BorderLayout.CENTER);
			}
		});
		
		JButton btnShowImporRawMaterial = new JButton("Show Import Raw Material");
		btnShowImporRawMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disposeObjectPanel();
				lblShow.setText("List of import raw materials");
				importRawMaterialPanel = new ImportRawMaterialPanel();
				panelTable.removeAll();
				panelTable.revalidate();
				panelTable.add(importRawMaterialPanel, BorderLayout.CENTER);
			}
		});
		
		JLabel lblSearch = new JLabel("Search: ");
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String search = txtSearch.getText();
					if(importDrinkPanel != null) {
						importDrinkPanel.search(search);
					}else if(importDrinkDetailPanel != null) {
						importDrinkDetailPanel.search(search);
					}else if(importRawMaterialPanel != null) {
						importRawMaterialPanel.search(search);
					}else if(importRawMaterialDetailPanel != null) {
						importRawMaterialDetailPanel.search(search);
					}
				}
			}
		});
		
		txtSearch.setColumns(10);
		GroupLayout gl_panelButton = new GroupLayout(panelButton);
		gl_panelButton.setHorizontalGroup(
			gl_panelButton.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelButton.createSequentialGroup()
					.addComponent(lblSearch)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnAddImport)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnImportDrink)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnShowImporRawMaterial)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnShowImportDrink, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnShowImportRaw, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panelButton.setVerticalGroup(
			gl_panelButton.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelButton.createSequentialGroup()
					.addGroup(gl_panelButton.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnShowImportRaw, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnShowImportDrink, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnShowImporRawMaterial)
						.addComponent(btnImportDrink)
						.addComponent(btnAddImport)
						.addComponent(lblSearch)
						.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panelButton.setLayout(gl_panelButton);
		
		lblShow.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(panelButton, GroupLayout.PREFERRED_SIZE, 762, Short.MAX_VALUE)
					.addGap(6))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelTable, GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblShow)
					.addContainerGap(800, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(panelButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(lblShow)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(431))
		);
		panelTable.setLayout(new BorderLayout(0, 0));
		setLayout(groupLayout);

	}
	
	private void disposeObjectPanel() {
		
		try {
			
			this.importDrinkDetailPanel = null;
			this.importDrinkPanel = null;
			this.importRawMaterialPanel = null;
			this.importRawMaterialDetailPanel = null;
			
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
}
