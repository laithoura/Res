package dialog;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import control_classes.ColorModel;
import control_classes.MessageShow;
import control_classes.TableSetting;
import controller.BookingDao;
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
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InsertBookingDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField textBoxCustomerName;
	private JTextField textBoxCustomerPhone;
	private JTable tableSelectBooking;
	private ArrayList<Table> tableList;
	private SelectBookingDataModel selectBookingModel;
	
	private JButton btnClear;
	private JButton btnSubmit;
	private JButton btnCancel;

	
	private CallBackListenter backListener;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			InsertBookingDialog dialog = new InsertBookingDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
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
	
	public InsertBookingDialog() {
		setTitle("Booking Table");
		setBounds(100, 100, 506, 473);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
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
		lblTableType.setBounds(34, 156, 116, 24);
		contentPanel.add(lblTableType);
		
		textBoxCustomerPhone = new JTextField();
		textBoxCustomerPhone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textBoxCustomerPhone.setColumns(10);
		textBoxCustomerPhone.setBackground(Color.WHITE);
		textBoxCustomerPhone.setBounds(146, 54, 203, 24);
		contentPanel.add(textBoxCustomerPhone);
		
		JPanel panelSelectBooking = new JPanel();
		panelSelectBooking.setBounds(34, 195, 430, 185);
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
				      System.out.println(index);
				      
						if((boolean)selectBookingModel.getValueAt(index, 0)) {
							tableList.get(index).setStatus(false);
							System.out.println("True to False");
						}else {
							tableList.get(index).setStatus(true);
							System.out.println("False to True");
						}
						tableSelectBooking.clearSelection();
						selectBookingModel.setTableModel(tableList);
						selectBookingModel.updateTableModel();
				}
			}
		});
		scrollPaneSelectBooking.setViewportView(tableSelectBooking);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setIcon(new ImageIcon(InsertBookingDialog.class.getResource("/resources/Submit_20.png")));
		btnSubmit.setMinimumSize(new Dimension(65, 23));
		btnSubmit.setMaximumSize(new Dimension(65, 23));
		btnSubmit.setBounds(274, 391, 91, 32);
		contentPanel.add(btnSubmit);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setIcon(new ImageIcon(InsertBookingDialog.class.getResource("/resources/Cancel_20.png")));
		btnCancel.setMinimumSize(new Dimension(65, 23));
		btnCancel.setMaximumSize(new Dimension(65, 23));
		btnCancel.setBounds(371, 391, 91, 32);
		contentPanel.add(btnCancel);
		
		btnClear = new JButton("Clear");
		btnClear.setIcon(new ImageIcon(InsertBookingDialog.class.getResource("/resources/Clear_20.png")));
		btnClear.setMinimumSize(new Dimension(65, 23));
		btnClear.setMaximumSize(new Dimension(65, 23));
		btnClear.setBounds(173, 391, 91, 32);
		contentPanel.add(btnClear);
					
		JDateChooser datePickerCheckInDate = new JDateChooser();
		datePickerCheckInDate.setDateFormatString("dd/MM/yyyy");
		datePickerCheckInDate.setBounds(146, 87, 203, 24);
		contentPanel.add(datePickerCheckInDate);		
	
		Date date = new Date();
		SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
		JSpinner spinner = new javax.swing.JSpinner(sm);
		JSpinner.DateEditor de = new JSpinner.DateEditor(spinner, "HH:mm a");
		spinner.setEditor(de);
		
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 13));
		spinner.setBounds(146, 122, 204, 24);
		contentPanel.add(spinner);
					
		JLabel label = new JLabel("Time");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(37, 123, 112, 24);
		contentPanel.add(label);
		
		JCheckBox checkBoxNormal = new JCheckBox("Normal");
		checkBoxNormal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		checkBoxNormal.setBounds(141, 157, 74, 23);
		contentPanel.add(checkBoxNormal);
		
		JCheckBox checkBoxVIP = new JCheckBox("VIP");
		checkBoxVIP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		checkBoxVIP.setBounds(214, 157, 55, 23);
		contentPanel.add(checkBoxVIP);
		contentPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblCustomersName, lblCustomersPhone, lblCheckinDate, lblTableType, panelSelectBooking, tableSelectBooking}));
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getContentPane(), lblCustomersName, lblCustomersPhone, lblCheckinDate, textBoxCustomerName, lblTableType, textBoxCustomerPhone, panelSelectBooking, scrollPaneSelectBooking, tableSelectBooking, btnSubmit, btnCancel, btnClear, datePickerCheckInDate, datePickerCheckInDate.getCalendarButton()}));
		
		LoadModelToTable();		
	}

	private void LoadModelToTable() {
		
		TableSetting.TableControl(tableSelectBooking);
		
		tableList = new ArrayList<>();
		instance_classes.Type type = new instance_classes.Type(1,"Table","VIP", false);
		tableList.add(new Table(1,"T001",type,true));
		tableList.add(new Table(1,"T001",type,true));
		tableList.add(new Table(1,"T001",type,true));
		tableList.add(new Table(1,"T001",type,true));		
		
		selectBookingModel = new SelectBookingDataModel();
		selectBookingModel.setTableModel(tableList);
		
		//tableSelectBooking.setModel(selectBookingModel);
		selectBookingModel.updateTableModel();
		
		registerEvent();
	}

	private void registerEvent() {
		btnClear.addActionListener(this);
		btnSubmit.addActionListener(this);
		btnCancel.addActionListener(this);				
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		if(e.getSource() == btnClear) {
			
		}else if(e.getSource() == btnSubmit) {		
			BookingDao daoBooking = new BookingDao();
			
			Date date = new Date();
			Booking booking = new Booking(1,"Thoura","012403032",date,date,date,10);	
			
			if(daoBooking.insertBooking(booking)) {
				backListener.CallBack((Booking)booking);
				//System.out.println("Inserted");
				MessageShow.success("Booking was saved successfully", "Booking Table");
			}				
		}else if(e.getSource() == btnCancel) {
			
		}
	}
}
