package panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import control_classes.Exporter;
import control_classes.TableSetting;
import data_table_model.BookingDataModel;
import dialog.InsertBookingDialog;
import dialog.UpdateBookingDialog;
import instance_classes.Booking;
import instance_classes.BookingDetail;
import interfaces.CallBackListenter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ImageIcon;

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
	/**
	 * Create the panel.
	 */
	public BookingPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelHeader = new JPanel();
		add(panelHeader, BorderLayout.NORTH);
		
		btnExport = new JButton("Export");
		btnExport.setIcon(new ImageIcon(BookingPanel.class.getResource("/Resources/Excel_20.png")));
		
		btnDetail = new JButton("Detail");
		btnDetail.setIcon(new ImageIcon(BookingPanel.class.getResource("/Resources/Details_20.png")));
		btnDetail.setMinimumSize(new Dimension(65, 23));
		btnDetail.setMaximumSize(new Dimension(65, 23));
		
		JComboBox cboFilter = new JComboBox();
		cboFilter.setEditable(true);
		
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
		GroupLayout gl_panelHeader = new GroupLayout(panelHeader);
		gl_panelHeader.setHorizontalGroup(
			gl_panelHeader.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelHeader.createSequentialGroup()
					.addContainerGap()
					.addComponent(cboFilter, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, Short.MAX_VALUE)
					.addComponent(btnNewBooking, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDetail, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(btnExport, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panelHeader.setVerticalGroup(
			gl_panelHeader.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHeader.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panelHeader.createParallelGroup(Alignment.BASELINE)
						.addComponent(cboFilter, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnExport, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDetail, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewBooking, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		panelHeader.setLayout(gl_panelHeader);
		
		JPanel panelContainer = new JPanel();
		panelContainer.setBorder(new EmptyBorder(1, 10, 0, 10));
		add(panelContainer, BorderLayout.CENTER);
		panelContainer.setLayout(new BorderLayout(0, 0));
				
		JPanel panelFooter = new JPanel();
		add(panelFooter, BorderLayout.SOUTH);
		GroupLayout gl_panelFooter = new GroupLayout(panelFooter);
		gl_panelFooter.setHorizontalGroup(
			gl_panelFooter.createParallelGroup(Alignment.LEADING)
				.addGap(0, 661, Short.MAX_VALUE)
		);
		gl_panelFooter.setVerticalGroup(
			gl_panelFooter.createParallelGroup(Alignment.LEADING)
				.addGap(0, 10, Short.MAX_VALUE)
		);
		panelFooter.setLayout(gl_panelFooter);
		
		JScrollPane scrollPaneBooking = new JScrollPane();
		panelContainer.add(scrollPaneBooking, BorderLayout.CENTER);
		
		tableBooking = new JTable();
		scrollPaneBooking.setViewportView(tableBooking);				
		
		TableSetting.TableControl(tableBooking);
	
		bookingModel = new BookingDataModel();
		
		bookingList = new ArrayList<>();
		
		ArrayList<BookingDetail> bookingDTList = new ArrayList<>();
		bookingDTList.add(new BookingDetail(1,2,4,"T002","VIP"));
				
		Date date = new Date();		
		Booking booking = new Booking(1,"Thoura","012403032",date,date,date,10,bookingDTList);		
		bookingList.add(booking);
		
		bookingModel.setBookingList(bookingList);
		
		/*Error this line*/		
		tableBooking.setModel(bookingModel);
		bookingModel.updateTable();
		
		tableBooking.getSelectionModel().addListSelectionListener(new RowListener());
		
		RegisterEvent();
	}
	
	private void RegisterEvent() {
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
			
			//JOptionPane.showMessageDialog(null, "Selected Index : "+selectedIndex+" : "+bookingList.get(selectedIndex).toString());			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnNewBooking) {
			InsertBookingDialog insertBooking = new InsertBookingDialog();
			insertBooking.setCallBackListener(new CallBackListenter() {				
				@Override
				public void CallBack(Object sender) {
					bookingList.add((Booking)sender);
					bookingModel.updateTable();
					
					System.out.println("Inserted");
				}
			});
			insertBooking.setVisible(true);
		}else if(e.getSource() == btnUpdate) {	
			
			Booking bookingUpdate = bookingList.get(selectedIndex);
			UpdateBookingDialog updateBooking = new UpdateBookingDialog(bookingUpdate);
			updateBooking.setCallBackListener(new CallBackListenter() {				
				@Override
				public void CallBack(Object sender) {					
					bookingList.set(selectedIndex, (Booking)sender);					
					bookingModel.updateTable();
					System.out.println("Updated");
					selectedIndex = -1;
				}
			});
			updateBooking.setVisible(true);
			
		}else if(e.getSource() == btnDetail) {
			
		}else if(e.getSource() == btnDelete) {
			bookingList.remove(selectedIndex);
			selectedIndex = -1;
			bookingModel.updateTable();
			
			System.out.println("Delected");
		}else if(e.getSource() == btnExport) {	
			Exporter.jtableToExcel(tableBooking);
		}
	}
}
