package dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control_classes.TableSetting;
import data_table_model.SelectBookingDataModel;
import instance_classes.Booking;
import instance_classes.BookingDetail;
import instance_classes.Table;
import interfaces.CallBackListenter;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.ImageIcon;

public class UpdateBookingDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField txtCustomerName;
	private JTextField txtCustomerPhone;
	private JTextField textField_3;
	private JTable tableSelectBooking;
	private ArrayList<Table> tableList;
	private SelectBookingDataModel selectBookingModel;
	private JButton btnUpdate;
	private JButton btnCancel;
	
	private Booking bookingUpdate;
	
	private CallBackListenter backListener;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UpdateBookingDialog dialog = new UpdateBookingDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	public void setCallBackListener( CallBackListenter backListener) {
		this.backListener = backListener;
	}	
	
	public UpdateBookingDialog(Booking bookingUpdate) {
		this();
		if(bookingUpdate == null) bookingUpdate = new Booking();
		this.bookingUpdate = bookingUpdate;
	}
	
	public UpdateBookingDialog() {
		setTitle("Update");
		setBounds(100, 100, 450, 481);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("VIP");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnNewRadioButton.setBounds(233, 159, 50, 23);
		contentPanel.add(rdbtnNewRadioButton);
		
		JLabel lblCustomersName = new JLabel("Customer's Name");
		lblCustomersName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCustomersName.setForeground(Color.BLACK);
		lblCustomersName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCustomersName.setBounds(12, 20, 137, 24);
		contentPanel.add(lblCustomersName);
		
		JLabel lblCustomersPhone = new JLabel("Customer's Phone");
		lblCustomersPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCustomersPhone.setForeground(Color.BLACK);
		lblCustomersPhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCustomersPhone.setBounds(12, 52, 137, 24);
		contentPanel.add(lblCustomersPhone);
		
		JLabel lblCheckinDate = new JLabel("Check-in Date");
		lblCheckinDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCheckinDate.setForeground(Color.BLACK);
		lblCheckinDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCheckinDate.setBounds(12, 87, 137, 24);
		contentPanel.add(lblCheckinDate);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBackground(Color.WHITE);
		textField.setBounds(158, 87, 167, 24);
		contentPanel.add(textField);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTime.setForeground(Color.BLACK);
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTime.setBounds(12, 120, 137, 24);
		contentPanel.add(lblTime);
		
		txtCustomerName = new JTextField();
		txtCustomerName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCustomerName.setColumns(10);
		txtCustomerName.setBackground(Color.WHITE);
		txtCustomerName.setBounds(158, 20, 167, 24);
		contentPanel.add(txtCustomerName);
		
		JRadioButton rdbtnNornal = new JRadioButton("Nornal");
		rdbtnNornal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnNornal.setBounds(154, 159, 72, 23);
		contentPanel.add(rdbtnNornal);
		
		JLabel lblTableType = new JLabel("Table Type");
		lblTableType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTableType.setForeground(Color.BLACK);
		lblTableType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTableType.setBounds(12, 157, 137, 24);
		contentPanel.add(lblTableType);
		
		txtCustomerPhone = new JTextField();
		txtCustomerPhone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCustomerPhone.setColumns(10);
		txtCustomerPhone.setBackground(Color.WHITE);
		txtCustomerPhone.setBounds(159, 54, 167, 24);
		contentPanel.add(txtCustomerPhone);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBackground(Color.WHITE);
		textField_3.setBounds(159, 121, 167, 24);
		contentPanel.add(textField_3);
		
		JPanel panelSelectBooking = new JPanel();
		panelSelectBooking.setBounds(12, 199, 412, 198);
		contentPanel.add(panelSelectBooking);
		panelSelectBooking.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneSelectBooking = new JScrollPane();
		panelSelectBooking.add(scrollPaneSelectBooking, BorderLayout.CENTER);
		
		tableSelectBooking = new JTable();
		scrollPaneSelectBooking.setViewportView(tableSelectBooking);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setIcon(new ImageIcon(UpdateBookingDialog.class.getResource("/resources/Edit_20.png")));
		btnUpdate.setMinimumSize(new Dimension(65, 23));
		btnUpdate.setMaximumSize(new Dimension(65, 23));
		btnUpdate.setBounds(236, 402, 91, 32);
		contentPanel.add(btnUpdate);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setIcon(new ImageIcon(UpdateBookingDialog.class.getResource("/resources/Cancel_20.png")));
		btnCancel.setMinimumSize(new Dimension(65, 23));
		btnCancel.setMaximumSize(new Dimension(65, 23));
		btnCancel.setBounds(333, 402, 91, 32);
		contentPanel.add(btnCancel);
		
		LoadModelToTable();
	}

	private void LoadModelToTable() {
		
		TableSetting.TableControl(tableSelectBooking);
		
		tableList = new ArrayList<>();
		instance_classes.Type type = new instance_classes.Type(1,"Table","VIP");
		tableList.add(new Table(1,"T001",type,true));
		tableList.add(new Table(1,"T001",type,true));
		tableList.add(new Table(1,"T001",type,true));
		tableList.add(new Table(1,"T001",type,true));
				
		selectBookingModel = new SelectBookingDataModel();
		selectBookingModel.setTableModel(tableList);
		
		tableSelectBooking.setModel(selectBookingModel);
		selectBookingModel.updateTableModel();
		
		registerEvent();
	}

	private void registerEvent() {
		btnUpdate.addActionListener(this);
		btnCancel.addActionListener(this);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnUpdate) {
			bookingUpdate.setCustomerName("Girls");
			backListener.CallBack(bookingUpdate);
		}else if(e.getSource() == btnCancel) {
			
		}
	}
}
