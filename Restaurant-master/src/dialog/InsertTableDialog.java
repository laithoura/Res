package dialog;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control_classes.ColorModel;
import control_classes.MessageShow;
import instance_classes.Table;
import interfaces.CallBackListenter;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JRadioButton;

public class InsertTableDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField textBoxTableID;
	private JTextField textBoxTableName;
	private JButton btnSubmit;
	private JButton btnCancel;
	private JRadioButton rdbtnVIP, rdbtnNormal;
	private ButtonGroup buttonGroup = new ButtonGroup();
	
	private CallBackListenter backListener;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			InsertTableDialog dialog = new InsertTableDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	ColorModel cModel = new ColorModel();
	public void setCallBackListener( CallBackListenter backListener) {
		this.backListener = backListener;
	}
	private Table table;
	
	public InsertTableDialog(Table table) {
		
		this();
		this.table = table;
		SetValueToControls();
	}
	
	private void SetValueToControls() {		
		textBoxTableID.setText(this.table.getId()+"");
		textBoxTableName.setText(this.table.getName());
		if(table.getType() == "VIP") {
			rdbtnVIP.setSelected(true);
		}else {
			rdbtnNormal.setSelected(true);
		}		
	}

	public InsertTableDialog() {
		setBackground(Color.BLACK);
		setTitle("Insert Table");
		setBounds(100, 100, 415, 283);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.BLACK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCustomersName = new JLabel("Table ID");
		lblCustomersName.setHorizontalAlignment(SwingConstants.LEFT);
		lblCustomersName.setForeground(Color.WHITE);
		lblCustomersName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCustomersName.setBounds(41, 62, 116, 24);
		contentPanel.add(lblCustomersName);
		
		JLabel lblCustomersPhone = new JLabel("Table Name");
		lblCustomersPhone.setHorizontalAlignment(SwingConstants.LEFT);
		lblCustomersPhone.setForeground(Color.WHITE);
		lblCustomersPhone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCustomersPhone.setBounds(41, 100, 116, 24);
		contentPanel.add(lblCustomersPhone);
		
		textBoxTableID = new JTextField();
		textBoxTableID.setEditable(false);
		textBoxTableID.setText("Auto Number");
		textBoxTableID.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textBoxTableID.setColumns(10);
		textBoxTableID.setBackground(Color.WHITE);
		textBoxTableID.setBounds(150, 62, 204, 24);
		contentPanel.add(textBoxTableID);
		
		JLabel lblTableType = new JLabel("Type");
		lblTableType.setHorizontalAlignment(SwingConstants.LEFT);
		lblTableType.setForeground(Color.WHITE);
		lblTableType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTableType.setBounds(41, 134, 116, 24);
		contentPanel.add(lblTableType);
		
		textBoxTableName = new JTextField();
		textBoxTableName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textBoxTableName.setColumns(10);
		textBoxTableName.setBackground(Color.WHITE);
		textBoxTableName.setBounds(150, 100, 203, 24);
		contentPanel.add(textBoxTableName);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setIcon(new ImageIcon(InsertTableDialog.class.getResource("/resources/Submit_20.png")));
		btnSubmit.setMinimumSize(new Dimension(65, 23));
		btnSubmit.setMaximumSize(new Dimension(65, 23));
		btnSubmit.setBounds(166, 184, 91, 32);
		contentPanel.add(btnSubmit);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setIcon(new ImageIcon(InsertTableDialog.class.getResource("/resources/Cancel_20.png")));
		btnCancel.setMinimumSize(new Dimension(65, 23));
		btnCancel.setMaximumSize(new Dimension(65, 23));
		btnCancel.setBounds(263, 184, 91, 32);
		contentPanel.add(btnCancel);
		
		rdbtnVIP = new JRadioButton("VIP");
		rdbtnVIP.setSelected(true);
		rdbtnVIP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnVIP.setForeground(Color.WHITE);
		rdbtnVIP.setBackground(Color.BLACK);
		rdbtnVIP.setBounds(149, 135, 49, 23);
		contentPanel.add(rdbtnVIP);
		
		rdbtnNormal = new JRadioButton("Normal");
		rdbtnNormal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnNormal.setForeground(Color.WHITE);
		rdbtnNormal.setBackground(Color.BLACK);
		rdbtnNormal.setBounds(210, 135, 91, 23);
		contentPanel.add(rdbtnNormal);
		
		buttonGroup.add(rdbtnVIP);
		buttonGroup.add(rdbtnNormal);
		
		JLabel lblInsertNewTable = new JLabel("Insert Table Information");
		lblInsertNewTable.setHorizontalAlignment(SwingConstants.LEFT);
		lblInsertNewTable.setForeground(Color.WHITE);
		lblInsertNewTable.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInsertNewTable.setBounds(41, 11, 232, 24);
		contentPanel.add(lblInsertNewTable);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(41, 172, 314, 1);
		contentPanel.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(41, 43, 314, 1);
		contentPanel.add(panel_1);
		contentPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblCustomersName, lblCustomersPhone, lblTableType}));
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getContentPane(), lblCustomersName, lblCustomersPhone, textBoxTableID, lblTableType, textBoxTableName, btnSubmit, btnCancel}));
		
		registerEvent();	
	}

	private void registerEvent() {		
		btnSubmit.addActionListener(this);
		btnCancel.addActionListener(this);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSubmit) {
			if(textBoxTableName.getText().equals("")) {
				MessageShow.Error("Please input Table Name", "Create Table"); return;
			}
			String type = (rdbtnVIP.isSelected())?"VIP":"Normal";
			
			if(this.table == null) {
				Table newTable = new Table(0,textBoxTableName.getText().trim(),type,true,true);
				backListener.CallBack(newTable);
			}else {
				this.table.setName(textBoxTableName.getText().trim());
				this.table.setType(type);
				backListener.CallBack(this.table);
			}
			
		}else if(e.getSource() == btnCancel) {
			this.dispose();
		}
	}
}
