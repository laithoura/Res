package panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.sql.*;
import java.util.ArrayList;

import connection.*;
import controller.RawMaterialDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import dialog.*;
import instance_classes.RawMaterial;
import instance_classes.RawMaterial;
import instance_classes.Type;
import interfaces.CallBackListenter;
import panel.BookingPanel.RowListener;

import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import data_table_model.*;
public class RawMaterialPanel extends JPanel implements CallBackListenter, ActionListener{
	private Connection con = null;
	private Statement st = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private Statement stType = null;
	private ResultSet rsType = null; 
	
	private RawMaterialDataModel rawMaterialDataModel;
	private int selectedIndex = 0;
	ArrayList<RawMaterial> rawMaterialList ;
	private JTable table;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;

	/**
	 * Create the panel.
	 */
	public RawMaterialPanel() {
		con = DbConnection.getConnection();
		
		JPanel pnlBtn = new JPanel();
		pnlBtn.setBackground(Color.LIGHT_GRAY);
		
		btnAdd = new JButton("Add");
		btnAdd.setIcon(new ImageIcon(RawMaterialPanel.class.getResource("/resources/Add_20.png")));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RawMaterialInsertDialog rawMaterialInsert = new RawMaterialInsertDialog();
				rawMaterialInsert.setVisible(true);				
			}
		});
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		btnEdit = new JButton("Edit");
		btnEdit.setIcon(new ImageIcon(RawMaterialPanel.class.getResource("/resources/Edit_20.png")));
		btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RawMaterialDao rawMaterialDao = new RawMaterialDao();
				RawMaterial rawMaterial = new RawMaterial();
				rawMaterial = rawMaterialList.get(selectedIndex);
				
				if (rawMaterialDao.deleteRawMaterial(rawMaterial.getId())) {
					JOptionPane.showMessageDialog(null, "Deleted successfully");
					rawMaterialList.remove(selectedIndex);
				} else {
					JOptionPane.showMessageDialog(null, "Deleted unsuccessfully");
				}
			}
		});
		btnDelete.setIcon(new ImageIcon(RawMaterialPanel.class.getResource("/resources/Cancel_20.png")));
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		JPanel pnlTable = new JPanel();
		pnlTable.setMaximumSize(new Dimension(30000, 32767));
		pnlTable.setBackground(Color.LIGHT_GRAY);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		table = new JTable();
		rawMaterialDataModel = new RawMaterialDataModel();
		rawMaterialList = new ArrayList<RawMaterial>();
		
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from raw_material where status = true");
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString("id"));
				String name = rs.getString("name");
				String type = rs.getString("type");
				String description = rs.getString("description");
				boolean status = Boolean.parseBoolean(rs.getString("status"));
				
				RawMaterial rawMaterial = new RawMaterial(id, name, type, description, status);
				rawMaterialList.add(rawMaterial);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		rawMaterialDataModel.setRawMaterialList(rawMaterialList);
		table.setModel(rawMaterialDataModel);
		scrollPane.setViewportView(table);
		
		GroupLayout gl_pnlTable = new GroupLayout(pnlTable);
		gl_pnlTable.setHorizontalGroup(
			gl_pnlTable.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
		);
		gl_pnlTable.setVerticalGroup(
			gl_pnlTable.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTable.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(49, Short.MAX_VALUE))
		);
		pnlTable.setLayout(gl_pnlTable);
		
		JLabel lblNewLabel = new JLabel("List of raw materials");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
		GroupLayout gl_pnlBtn = new GroupLayout(pnlBtn);
		gl_pnlBtn.setHorizontalGroup(
			gl_pnlBtn.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlBtn.createSequentialGroup()
					.addGap(5)
					.addComponent(btnAdd)
					.addGap(5)
					.addComponent(btnEdit)
					.addGap(5)
					.addComponent(btnDelete))
		);
		gl_pnlBtn.setVerticalGroup(
			gl_pnlBtn.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlBtn.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_pnlBtn.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAdd)
						.addComponent(btnEdit)
						.addComponent(btnDelete)))
		);
		pnlBtn.setLayout(gl_pnlBtn);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnlTable, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
						.addComponent(pnlBtn, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(7)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(pnlBtn, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(pnlTable, GroupLayout.PREFERRED_SIZE, 165, Short.MAX_VALUE)
					.addGap(48))
		);
		setLayout(groupLayout);
		table.getSelectionModel().addListSelectionListener(new RowListener());
		btnEdit.addActionListener(this);

	}
	
	class RowListener implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting()) return;
			selectedIndex = table.getSelectedRow();
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		RawMaterial rawMaterial = rawMaterialList.get(selectedIndex);
		RawMaterialEditDialog rawMaterialEdit = new RawMaterialEditDialog(rawMaterial);
				
		rawMaterialEdit.setCallBackListener(this);
		rawMaterialEdit.setVisible(true);	
	}

	@Override
	public void CallBack(Object sender) {
		System.out.println("Called Back");
		rawMaterialList.set(selectedIndex, (RawMaterial)sender);
		rawMaterialDataModel.setRawMaterialList(rawMaterialList);
		rawMaterialDataModel.updateTable();		
	}
}
