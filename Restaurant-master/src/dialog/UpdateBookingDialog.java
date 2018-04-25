package dialog;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;
import com.toedter.calendar.JDateChooser;

import connection.DbConnection;
import control_classes.ColorModel;
import control_classes.Help;
import control_classes.InputControl;
import control_classes.MessageShow;
import control_classes.TableSetting;
import controller.BookingDao;
import controller.TableDao;
import data_table_model.SelectBookingDataModel;
import instance_classes.Booking;
import instance_classes.Table;
import interfaces.CallBackListenter;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JRadioButton;

public class UpdateBookingDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField textBoxCustomerName;
	private JTextField textBoxCustomerPhone;
	private JTable tableSelectBooking;
	private ArrayList<Table> tableList;
	private SelectBookingDataModel selectBookingModel;
	
	private TableDao tableDao = new TableDao();
	private JButton btnClear;
	private JButton btnSubmit;
	private JButton btnCancel;
	
	private JRadioButton radioButtonVIP;
	private JRadioButton radioButtonNormal;
	private JRadioButton radioButtonAll;
	private ButtonGroup buttonGroupTableType;
	private JLabel labelBookingItem;
	private JDateChooser datePickerCheckInDate;
	private JSpinner spinnerTime;
	private Booking booking;
	
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
	
	public UpdateBookingDialog(Booking booking){
		this();
		this.booking = booking;
		LoadDataIntoControls();
		RefreshBookedTable();
	}
	
	private void LoadDataIntoControls() {
		
		textBoxCustomerName.setText(booking.getCustomerName());
		textBoxCustomerPhone.setText(booking.getCustomerPhone());
		datePickerCheckInDate.setDate(booking.getCheckInDate());
		spinnerTime.setValue(booking.getTime());
	
		tableList = tableDao.getBookingListOnly(booking.getId());
		tableList.addAll(tableDao.getTableLists(true, true));
		
		refreshTableModel();
	}

	public UpdateBookingDialog() {
		setTitle("Booking Table");
		setBounds(100, 100, 499, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblCustomersName = new JLabel("Customer's Name");
		lblCustomersName.setHorizontalAlignment(SwingConstants.LEFT);
		lblCustomersName.setForeground(Color.BLACK);
		lblCustomersName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCustomersName.setBounds(37, 20, 116, 24);
		contentPanel.add(lblCustomersName);
		
		JLabel lblCustomersPhone = new JLabel("Customer's Phone");
		lblCustomersPhone.setHorizontalAlignment(SwingConstants.LEFT);
		lblCustomersPhone.setForeground(Color.BLACK);
		lblCustomersPhone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCustomersPhone.setBounds(37, 52, 116, 24);
		contentPanel.add(lblCustomersPhone);
		
		JLabel lblCheckinDate = new JLabel("Check-in Date");
		lblCheckinDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblCheckinDate.setForeground(Color.BLACK);
		lblCheckinDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCheckinDate.setBounds(37, 87, 116, 24);
		contentPanel.add(lblCheckinDate);
		
		textBoxCustomerName = new JTextField();
		textBoxCustomerName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textBoxCustomerName.setColumns(10);
		textBoxCustomerName.setBackground(Color.WHITE);
		textBoxCustomerName.setBounds(146, 20, 204, 24);
		contentPanel.add(textBoxCustomerName);
		
		JLabel lblTableType = new JLabel("Type");
		lblTableType.setHorizontalAlignment(SwingConstants.LEFT);
		lblTableType.setForeground(Color.BLACK);
		lblTableType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTableType.setBounds(37, 156, 116, 24);
		contentPanel.add(lblTableType);
		
		textBoxCustomerPhone = new JTextField();
		textBoxCustomerPhone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textBoxCustomerPhone.setColumns(10);
		textBoxCustomerPhone.setBackground(Color.WHITE);
		textBoxCustomerPhone.setBounds(146, 54, 203, 24);
		contentPanel.add(textBoxCustomerPhone);
		
		JPanel panelSelectBooking = new JPanel();
		panelSelectBooking.setBounds(34, 222, 430, 185);
		contentPanel.add(panelSelectBooking);
		panelSelectBooking.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneSelectBooking = new JScrollPane();
		
		panelSelectBooking.add(scrollPaneSelectBooking, BorderLayout.CENTER);
		
		tableSelectBooking = new JTable();
		tableSelectBooking.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					
				      int index = ((JTable) e.getSource()).getSelectedRow();							    
				      
						if((boolean)selectBookingModel.getValueAt(index, 0)) {
							tableList.get(index).setAvailable(true);						
						}else {
							tableList.get(index).setAvailable(false);
						}
						tableSelectBooking.clearSelection();
						selectBookingModel.setTableModel(tableList);
						selectBookingModel.updateTableModel();
						
						RefreshBookedTable();
				}
			}				
		});
		scrollPaneSelectBooking.setViewportView(tableSelectBooking);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setIcon(new ImageIcon(UpdateBookingDialog.class.getResource("/resources/Submit_20.png")));
		btnSubmit.setMinimumSize(new Dimension(65, 23));
		btnSubmit.setMaximumSize(new Dimension(65, 23));
		btnSubmit.setBounds(276, 418, 91, 32);
		contentPanel.add(btnSubmit);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setIcon(new ImageIcon(UpdateBookingDialog.class.getResource("/resources/Cancel_20.png")));
		btnCancel.setMinimumSize(new Dimension(65, 23));
		btnCancel.setMaximumSize(new Dimension(65, 23));
		btnCancel.setBounds(373, 418, 91, 32);
		contentPanel.add(btnCancel);
		
		btnClear = new JButton("Clear");
		btnClear.setIcon(new ImageIcon(UpdateBookingDialog.class.getResource("/resources/Clear_20.png")));
		btnClear.setMinimumSize(new Dimension(65, 23));
		btnClear.setMaximumSize(new Dimension(65, 23));
		btnClear.setBounds(175, 418, 91, 32);
		contentPanel.add(btnClear);
					
		datePickerCheckInDate = new JDateChooser();
		datePickerCheckInDate.setDateFormatString("dd/MM/yyyy");
		datePickerCheckInDate.setBounds(146, 87, 203, 24);
		contentPanel.add(datePickerCheckInDate);		
	
		Date date = new Date();
		SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
		spinnerTime = new javax.swing.JSpinner(sm);
		JSpinner.DateEditor de = new JSpinner.DateEditor(spinnerTime, "HH:mm a");
		spinnerTime.setEditor(de);
		
		spinnerTime.setFont(new Font("Tahoma", Font.PLAIN, 13));
		spinnerTime.setBounds(146, 122, 204, 24);
		contentPanel.add(spinnerTime);
					
		JLabel label = new JLabel("Time");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(37, 123, 112, 24);
		contentPanel.add(label);
		
		labelBookingItem = new JLabel("Booked Table :");
		labelBookingItem.setHorizontalAlignment(SwingConstants.LEFT);
		labelBookingItem.setForeground(Color.BLACK);
		labelBookingItem.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelBookingItem.setBounds(37, 187, 427, 24);
		contentPanel.add(labelBookingItem);
		contentPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblCustomersName, lblCustomersPhone, lblCheckinDate, lblTableType, panelSelectBooking, tableSelectBooking}));
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getContentPane(), lblCustomersName, lblCustomersPhone, lblCheckinDate, textBoxCustomerName, lblTableType, textBoxCustomerPhone, panelSelectBooking, scrollPaneSelectBooking, tableSelectBooking, btnSubmit, btnCancel, btnClear, datePickerCheckInDate, datePickerCheckInDate.getCalendarButton()}));
		
		TableSetting.TableControl(tableSelectBooking);
		
		radioButtonVIP = new JRadioButton("VIP");
		radioButtonVIP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		radioButtonVIP.setBounds(143, 156, 57, 23);
		contentPanel.add(radioButtonVIP);
		
		radioButtonNormal = new JRadioButton("Normal");
		radioButtonNormal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		radioButtonNormal.setBounds(203, 156, 74, 23);
		contentPanel.add(radioButtonNormal);
		
		radioButtonAll = new JRadioButton("All");
		radioButtonAll.setFont(new Font("Tahoma", Font.PLAIN, 13));
		radioButtonAll.setBounds(288, 156, 62, 23);
		contentPanel.add(radioButtonAll);
		
		buttonGroupTableType = new ButtonGroup();
		buttonGroupTableType.add(radioButtonVIP);
		buttonGroupTableType.add(radioButtonNormal);
		buttonGroupTableType.add(radioButtonAll);
		
		tableList = new ArrayList<>();			
		selectBookingModel = new SelectBookingDataModel();
		
		controlTextBox();
		registerEvent();
	}

	private void controlTextBox() {		
		InputControl.inputLetter(textBoxCustomerName);
		InputControl.inputInteger(textBoxCustomerPhone);
	}

	private void registerEvent() {
		btnClear.addActionListener(this);
		btnSubmit.addActionListener(this);
		btnCancel.addActionListener(this);		
		
		this.radioButtonAll.addActionListener(this);
		this.radioButtonNormal.addActionListener(this);
		this.radioButtonVIP.addActionListener(this);
	}

	@SuppressWarnings("resource")
	@Override
	public void actionPerformed(ActionEvent e) {		
		if(e.getSource() == btnClear) {
			
			textBoxCustomerName.setText("");
			textBoxCustomerPhone.setText("");
			
			Date date = new Date();
			datePickerCheckInDate.setDate(date);
			spinnerTime.setValue(date);
									
		}else if(e.getSource() == btnSubmit) {					
			updateBooking();
		}else if(e.getSource() == btnCancel) {
			this.dispose();
		}else if(e.getSource() == radioButtonVIP) {			
			
			if(radioButtonVIP.isSelected()) {
				/*Get Available VIP table*/
				
				tableList = tableDao.getBookingListOnly(booking.getId());
				tableList.addAll(tableDao.getFilterTableType("VIP", true));			
				refreshTableModel();	
			}
			
		}else if(e.getSource() == radioButtonNormal) {
			
			if(radioButtonNormal.isSelected()) {
				/*Get Available Normal table*/
				tableList = tableDao.getBookingListOnly(booking.getId());
				tableList.addAll(tableDao.getFilterTableType("Normal", true));			
				refreshTableModel();		
			}					
		}else if(e.getSource() == radioButtonAll) {
			
			if(radioButtonAll.isSelected()) {
				/*Get all Available table*/
			
				tableList = tableDao.getBookingListOnly(booking.getId());
				tableList.addAll(tableDao.getTableLists(true, true));								
				refreshTableModel();
			}
		}
	}	

	private void updateBooking() {
		BookingDao bookingDao = new BookingDao();
		boolean success = false;
		int totalBooking = 0;		
		/*Begin Validation Data Input*/
		if(textBoxCustomerName.getText().trim().equals("")) {
			MessageShow.Error("Please valid Customer's Name", "Booking Table");
			return;
		}else if(textBoxCustomerPhone.getText().trim().equals("")) {
			MessageShow.Error("Please valid Customer's Phone", "Booking Table");
			return;
		}else {
			/*Count Selected Table to Booking for Customer*/
			for(Table table:tableList) {
				if(!table.isAvailable()) {
					totalBooking ++;
				}
			}
			
			if(totalBooking == 0) {
				MessageShow.Error("Please select any table to book", "Booking Table");
				return;
			}	
		}/*End of Validation Data Input*/		
		
		
		/*After Validate All Required DATA*/
		BookingDao daoBooking = new BookingDao();				
		Date checkInDate = datePickerCheckInDate.getDate();
		Date checkInTime = (Date) spinnerTime.getValue();
		
		this.booking.setCustomerName(textBoxCustomerName.getText().trim());
		this.booking.setCustomerPhone(textBoxCustomerPhone.getText().trim());
		this.booking.setCheckInDate(checkInDate);
		this.booking.setTime(checkInTime);
		this.booking.setTotalTable(totalBooking);
		
		if(daoBooking.deleteAllTablesInBookingDetails(this.booking.getId()) && daoBooking.reInsertIntoBookingDetails(this.booking.getId(),this.tableList)) {
			
			MessageShow.success("Booking was updated successfully", "Booking Table");
			backListener.CallBack((Booking)this.booking);			
		}		
	}
	
	private void RefreshBookedTable() {	
		String bookedItems = "Booked Table : ";
		for(Table table:tableList) {
			if(!table.isAvailable()) {
				bookedItems += table.getName() +" ; ";
			}
		}
		labelBookingItem.setText(bookedItems);
	}	
	
	
	private void refreshTableModel() {
		selectBookingModel = new SelectBookingDataModel();	
		selectBookingModel.setTableModel(tableList);			
		
		tableSelectBooking.setModel(selectBookingModel);
		selectBookingModel.updateTableModel();	
	}
	
}
