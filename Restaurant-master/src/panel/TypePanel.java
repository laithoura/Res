package panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Color;
import java.sql.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import dialog.*;
import connection.*;
import data_table_model.TypeDataModel;
import instance_classes.*;
import interfaces.CallBackListenter;
import control_classes.*;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Dimension;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class TypePanel extends JPanel implements CallBackListenter,ActionListener{
	
	private JTable table;
	private Connection con = null;
	private PreparedStatement pst = null;
	private Statement st = null;
	private ResultSet rs = null;
	private int selectedIndex = 0;
	ArrayList<Type> typeList;
	private JButton btnEdit1 ;
	private TypeDataModel typeModel;
	private JScrollPane scrollPane;
	
	/**
	 * Create the panel.
	 */
	public TypePanel() {
		con = DbConnection.getConnection();
		
		JPanel panelTable = new JPanel();
		panelTable.setLayout(new BorderLayout(0, 0));
		typeModel = new TypeDataModel();
		typeList = new ArrayList<Type>();	
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from type where status = true");
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString("id"));
                String name = rs.getString("name");
                String category = rs.getString("category");
                boolean status = Boolean.parseBoolean(rs.getString("status"));
				Type type = new Type(id, name, category, status);
				typeList.add(type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		scrollPane = new JScrollPane();
		typeModel.setTypeList(typeList);
		
		
		
		JPanel pnlBtn = new JPanel();
		pnlBtn.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadDataFromDatabase();
			
			}
		});
		btnRefresh.setIcon(new ImageIcon(TypePanel.class.getResource("/resources/Details_20.png")));
		pnlBtn.add(btnRefresh);
		
		btnEdit1 = new JButton("Edit");
		btnEdit1.setIcon(new ImageIcon(TypePanel.class.getResource("/resources/Edit_20.png")));
		pnlBtn.add(btnEdit1);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setIcon(new ImageIcon(TypePanel.class.getResource("/resources/Add_20.png")));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TypeInsertDialog typeInertDialog = new TypeInsertDialog();
				typeInertDialog.setVisible(true);
			}
		});
		pnlBtn.add(btnAdd);
		
		JLabel lblTitle = new JLabel("List of Type");
		lblTitle.setBackground(Color.WHITE);
		lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(typeModel);
		TableSetting.TableControl(table);
		table.getSelectionModel().addListSelectionListener(new RowListener());
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelTable, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(647))
						.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(pnlBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE))
							.addGap(22)))
					.addGap(0))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(pnlBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
		);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (typeList.size() > 0) {
					
					try {
						pst = con.prepareStatement("update type set status = false where id = ?");
						int id = typeList.get(selectedIndex).getId();
						pst.setInt(1, id);
						if (pst.executeUpdate() > 0) {
							JOptionPane.showMessageDialog(null, "Deleted suceessfully");
							typeList.remove(selectedIndex);
						}
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Deleted unsuceessfully");
						e.printStackTrace();
					}			
				}
								
			}
		});
		btnDelete.setIcon(new ImageIcon(TypePanel.class.getResource("/resources/Cancel_20.png")));
		btnDelete.setActionCommand("Delete");
		pnlBtn.add(btnDelete);
		setLayout(groupLayout);
		
		btnEdit1.addActionListener(this);
	}
	
	class RowListener implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting()) return;
			selectedIndex = table.getSelectedRow();
		}
	}

	@Override
	public void CallBack(Object sender) {
		typeList.set(selectedIndex, (Type)sender);
		typeModel.setTypeList(typeList);
		typeModel.updateTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Type type = typeList.get(selectedIndex);
		TypeEditDialog typeEdit = new TypeEditDialog(type);
		
		typeEdit.setCallBackListener(this);
		typeEdit.setVisible(true);
	}
	
	private void loadDataFromDatabase() {
		typeModel = new TypeDataModel();
		typeList = new ArrayList<Type>();	
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from type where status = true");
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString("id"));
                String name = rs.getString("name");
                String category = rs.getString("category");
                boolean status = Boolean.parseBoolean(rs.getString("status"));
				Type type = new Type(id, name, category, status);
				typeList.add(type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		scrollPane = new JScrollPane();
		typeModel.setTypeList(typeList);
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(typeModel);
		TableSetting.TableControl(table);
	}
	
}
