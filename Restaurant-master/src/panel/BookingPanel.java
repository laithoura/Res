package panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import control_classes.Formatter;
import control_classes.Exporter;
import control_classes.MessageShow;
import control_classes.TableSetting;
import controller.BookingDao;
import data_table_model.BookingDataModel;
import dialog.InsertBookingDialog;
import dialog.UpdateBookingDialog;
import instance_classes.Booking;
import interfaces.CallBackListenter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;

public class BookingPanel extends JPanel implements ActionListener{
	private JTable tableBooking;
	private BookingDataModel bookingModel;
	private ArrayList<Booking> bookingList;
	private JButton btnExport;
	private JButton btnDelete;
	private JButton btnNewBooking;
	private JButton btnUpdate;
	private JButton btnDetail;
	
	private int selectedIndex = -1;
	private BookingDao daoBooking = new BookingDao();
	private JTextField textBoxSearch;
	private JComboBox<?> comboBoxSearchType;
	private JDateChooser dateChooser;
	private JSpinner timeSpinner; 
	private JPanel panelFooter;
	private JButton btnRefresh;
	/**
	 * Create the panel.
	 */
	public BookingPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelHeader = new JPanel();
		panelHeader.setBorder(new EmptyBorder(0, 0, 0, 0));
		add(panelHeader, BorderLayout.NORTH);
		
		btnExport = new JButton("Export");
		btnExport.setIcon(new ImageIcon(BookingPanel.class.getResource("/Resources/Excel_20.png")));
		
		btnDetail = new JButton("Detail");
		btnDetail.setIcon(new ImageIcon(BookingPanel.class.getResource("/Resources/Details_20.png")));
		btnDetail.setMinimumSize(new Dimension(65, 23));
		btnDetail.setMaximumSize(new Dimension(65, 23));
		
		btnUpdate = new JButton("Update");
		btnUpdate.setIcon(new ImageIcon(BookingPanel.class.getResource("/Resources/Edit_20.png")));
		btnUpdate.setMinimumSize(new Dimension(65, 23));
		btnUpdate.setMaximumSize(new Dimension(65, 23));
		
		btnDelete = new JButton("Delete");
		btnDelete.setIcon(new ImageIcon(BookingPanel.class.getResource("/Resources/Cancel_20.png")));
		
		btnNewBooking = new JButton("New");
		btnNewBooking.setIcon(new ImageIcon(BookingPanel.class.getResource("/Resources/Add_20.png")));
		btnNewBooking.setMinimumSize(new Dimension(65, 23));
		btnNewBooking.setMaximumSize(new Dimension(65, 23));
		
		JPanel panelSearch = new JPanel();
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setIcon(new ImageIcon(BookingPanel.class.getResource("/resources/Refresh_20.png")));
		btnRefresh.setMinimumSize(new Dimension(65, 23));
		btnRefresh.setMaximumSize(new Dimension(65, 23));
		GroupLayout gl_panelHeader = new GroupLayout(panelHeader);
		gl_panelHeader.setHorizontalGroup(
			gl_panelHeader.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelHeader.createSequentialGroup()
					.addGap(9)
					.addComponent(panelSearch, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewBooking, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDetail, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(btnExport, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addGap(8))
		);
		gl_panelHeader.setVerticalGroup(
			gl_panelHeader.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHeader.createSequentialGroup()
					.addGroup(gl_panelHeader.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelHeader.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panelHeader.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnExport, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
								.addComponent(btnDelete, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
								.addComponent(btnDetail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
								.addComponent(btnUpdate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
								.addComponent(btnNewBooking, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRefresh, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panelHeader.createSequentialGroup()
							.addGap(8)
							.addComponent(panelSearch, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelSearch.setLayout(null);
		
		textBoxSearch = new JTextField();
		textBoxSearch.setBounds(0, 25, 203, 24);
		panelSearch.add(textBoxSearch);
		textBoxSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					searchBooking();
				}
			}
		});
		textBoxSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textBoxSearch.setColumns(10);
		
		JLabel labelSearch = new JLabel("");
		labelSearch.setBounds(212, 27, 36, 20);
		panelSearch.add(labelSearch);
		labelSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchBooking();
			}
		});
		labelSearch.setIcon(new ImageIcon(BookingPanel.class.getResource("/resources/Search_20.png")));
		labelSearch.setToolTipText("Search");
		labelSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		comboBoxSearchType = new JComboBox();
		comboBoxSearchType.setBounds(0, 2, 203, 22);
		panelSearch.add(comboBoxSearchType);
		comboBoxSearchType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = comboBoxSearchType.getSelectedIndex();
				if(selectedIndex == -1) return;
				
				textBoxSearch.setText("");
				textBoxSearch.setFocusable(true);
				textBoxSearch.requestFocus();		
				
				if(selectedIndex == 0) { /*Booking ID*/
					textBoxSearch.setVisible(true);
					timeSpinner.setVisible(false);
					dateChooser.setVisible(false);										
					
				}else if(selectedIndex == 1) { /*Customer's Name*/
					
					textBoxSearch.setVisible(true);
					timeSpinner.setVisible(false);
					dateChooser.setVisible(false);					
					
				}else if(selectedIndex == 2) { /*Customer's Phone*/
					
					textBoxSearch.setVisible(true);
					timeSpinner.setVisible(false);
					dateChooser.setVisible(false);					
					
				}else if(selectedIndex == 3) { /*Booking Date*/
					
					dateChooser.setVisible(true);
					textBoxSearch.setVisible(false);
					timeSpinner.setVisible(false);
					
				}else if(selectedIndex == 4) { /*Check-in Date*/
					
					dateChooser.setVisible(true);
					textBoxSearch.setVisible(false);
					timeSpinner.setVisible(false);
					
				}else if(selectedIndex == 5) { /*Time*/
					
					timeSpinner.setVisible(true);
					textBoxSearch.setVisible(false);
					dateChooser.setVisible(false);
					
				}else if(selectedIndex == 6) {
					
					textBoxSearch.setVisible(true);
					timeSpinner.setVisible(false);
					dateChooser.setVisible(false);					
				}				
			}
		});
		comboBoxSearchType.setModel(new DefaultComboBoxModel(new String[] {"Booking ID", "Customer's Name", "Customer's Phone", "Booking Date", "Check-in Date", "Time", "Table Name"}));
		comboBoxSearchType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(0, 25, 203, 24);
		panelSearch.add(dateChooser);
		dateChooser.setDateFormatString("dd/MM/yyyy");
											
		Date date = new Date();
		SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
		timeSpinner = new javax.swing.JSpinner(sm);
		JSpinner.DateEditor de = new JSpinner.DateEditor(timeSpinner, "HH:mm");
		timeSpinner.setBounds(0, 25, 203, 24);
		timeSpinner.setFont(new Font("Tahoma", Font.PLAIN, 13));	
		panelSearch.add(timeSpinner);
		timeSpinner.setEditor(de);
		panelHeader.setLayout(gl_panelHeader);
		
		JPanel panelContainer = new JPanel();
		panelContainer.setBorder(new EmptyBorder(1, 10, 0, 10));
		add(panelContainer, BorderLayout.CENTER);
		panelContainer.setLayout(new BorderLayout(0, 0));			
		
		JScrollPane scrollPaneBooking = new JScrollPane();
		panelContainer.add(scrollPaneBooking, BorderLayout.CENTER);
		
		tableBooking = new JTable();
		scrollPaneBooking.setViewportView(tableBooking);				
		
		panelFooter = new JPanel();
		add(panelFooter, BorderLayout.SOUTH);
		bookingList = daoBooking.getBookingLists(true);	
		
		bookingModel = new BookingDataModel();
		
		refreshTableModel();	
		
		registerEvent();		
	}	

	private void registerEvent() {			
		
		timeSpinner.setVisible(false);
		dateChooser.setVisible(false);
		
		TableSetting.TableControl(tableBooking);	
		tableBooking.getSelectionModel().addListSelectionListener(new RowListener());		
		
		this.btnRefresh.addActionListener(this);
		this.btnNewBooking.addActionListener(this);
		this.btnUpdate.addActionListener(this);
		this.btnDetail.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.btnExport.addActionListener(this);			
	}

	class RowListener implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting()) return;
			selectedIndex = tableBooking.getSelectedRow();			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnRefresh) {
			bookingList = daoBooking.getBookingLists(true);	
			refreshTableModel();
		}
		if(e.getSource() == btnNewBooking) {
			InsertBookingDialog insertBooking = new InsertBookingDialog();
			insertBooking.setCallBackListener(new CallBackListenter() {				
				@Override
				public void CallBack(Object sender) {
					Booking booking = (Booking)sender;
					bookingList.add(booking);
					bookingModel.updateTable();															
				}
			});
			insertBooking.setVisible(true);
		}else if(e.getSource() == btnUpdate) {
			if(selectedIndex == -1) {
				MessageShow.Error("Please select record of table.", "Booking");
				return;
			}
			Booking bookingUpdate = bookingList.get(selectedIndex);
			UpdateBookingDialog updateBooking = new UpdateBookingDialog(bookingUpdate);
			
			updateBooking.setCallBackListener(new CallBackListenter() {
				@Override
				public void CallBack(Object sender) {
					Booking booking = (Booking)sender;
					if(daoBooking.updateBooking(booking)) {
						bookingList.set(selectedIndex, booking);
						bookingModel.updateTable();
						
						/*After Updated successfully we close the Dialog*/
						updateBooking.dispose();
						selectedIndex = -1;
					}
				}
			});
			updateBooking.setVisible(true);
			
		}else if(e.getSource() == btnDetail) {
			
			if(selectedIndex == -1) {
				MessageShow.Error("Please select record of table.", "Booking");
				return;
			}
			
			InsertBookingDialog insertBooking = new InsertBookingDialog(bookingList.get(selectedIndex));			
			insertBooking.setVisible(true);
			selectedIndex = -1;
			
		}else if(e.getSource() == btnDelete) {
			
			if(selectedIndex == -1) {
				MessageShow.Error("Please select record of table.", "Booking");
				return;
			}
			
			int choose = MessageShow.deleteVerification("Do you want to cancel it?","Cancel Booking");
			
			if(choose == 0) {
				int bookingId = bookingList.get(selectedIndex).getId();
				
				if(daoBooking.deleteBooking(bookingId)) {
					
					bookingList.remove(selectedIndex);
					bookingModel.updateTable();
					selectedIndex = -1;
					MessageShow.success("Booking was canceled successfully.","Cancel Booking");
					
				}else{
					MessageShow.Error("Booking was canceled unsuccessfully.","Cancel Booking");
				}				
			}
			
		}else if(e.getSource() == btnExport) {	
			Exporter.jtableToExcel(tableBooking);
		}
	}
		
	private void searchBooking() {		
		int selectedIndex = comboBoxSearchType.getSelectedIndex();
		bookingList.clear();
		if(textBoxSearch.getText().trim().equals("") && ( selectedIndex == 0 || selectedIndex == 1 || selectedIndex == 2 || selectedIndex == 6)) {
			bookingList = daoBooking.getBookingLists(true);
		}
		else {
			
			Object condition = null;
			if(selectedIndex == 0) { /*Booking ID*/
				
				try {
					condition = Integer.parseInt(textBoxSearch.getText().trim());			
				}catch(NumberFormatException ex) {			
					return;
				}
				
			}else if(selectedIndex == 1) { /*Customer's Name*/
				
				condition = textBoxSearch.getText().trim();
				
			}else if(selectedIndex == 2) { /*Customer's Phone*/
				
				condition = textBoxSearch.getText().trim();
				
			}else if(selectedIndex == 3) { /*Booking Date*/
				
				condition = dateChooser.getDate();
				
			}else if(selectedIndex == 4) { /*Check-in Date*/
				
				condition = dateChooser.getDate();
				
			}else if(selectedIndex == 5) { /*Time*/
				
				condition = Formatter.timeFormat((Date)timeSpinner.getValue());				
				
			}else if(selectedIndex == 6) {
				
				condition = textBoxSearch.getText().trim();
			}
			bookingList = daoBooking.searchBookingList(condition,selectedIndex);		
		}
		refreshTableModel();
	}
	
	private void refreshTableModel() {
		
		bookingModel.setBookingList(bookingList);		
		/*Error this line*/
		//tableBooking.setModel(bookingModel);
		bookingModel.updateTable();		
	}
}
