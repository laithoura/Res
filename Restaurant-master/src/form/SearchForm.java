package form;
import java.awt.EventQueue;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;

import interfaces.SearchCallBackListener;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.ImageIcon;

public class SearchForm extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JDateChooser startDateChooser, endDateChooser;
	
	private SearchCallBackListener searchCallBackListener;
	private JButton btnSearch;
	private JButton btnCancel;

	
	public void setSearchBackListener(SearchCallBackListener searchCallBackListener) {
		this.searchCallBackListener = searchCallBackListener;
	}
	
	
	public SearchForm() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginForm.class.getResource("/resources/Flora.logo.png")));
		
		setTitle("Search Between Dates");
		setResizable(false);
		setBounds(100, 100, 373, 173);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		
		Date date = new Date();
		startDateChooser = new JDateChooser(date);
		startDateChooser.setBounds(114, 19, 204, 25);
		contentPane.add(startDateChooser);
		startDateChooser.setDateFormatString("dd/MM/yyyy");
		contentPane.add(startDateChooser);	
		
		JLabel lblNewLabel = new JLabel("Start Date");
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		lblNewLabel.setBounds(32, 24, 80, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		lblEndDate.setBounds(32, 60, 80, 14);
		contentPane.add(lblEndDate);
		
		endDateChooser = new JDateChooser(date);
		endDateChooser.setDateFormatString("dd/MM/yyyy");
		endDateChooser.setBounds(114, 55, 204, 25);
		contentPane.add(endDateChooser);
		
		btnSearch = new JButton("Search");
		btnSearch.setIcon(new ImageIcon(SearchForm.class.getResource("/resources/Search_20.png")));
		btnSearch.setMinimumSize(new Dimension(65, 23));
		btnSearch.setMaximumSize(new Dimension(65, 23));
		btnSearch.setBounds(114, 91, 97, 30);
		contentPane.add(btnSearch);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setIcon(new ImageIcon(SearchForm.class.getResource("/resources/Cancel_20.png")));
		btnCancel.setMinimumSize(new Dimension(65, 23));
		btnCancel.setMaximumSize(new Dimension(65, 23));
		btnCancel.setBounds(221, 91, 97, 30);
		contentPane.add(btnCancel);
		
		btnSearch.addActionListener(this);
		btnCancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSearch) {
			Date startDate = startDateChooser.getDate();
			Date endDate = endDateChooser.getDate(); 
			searchCallBackListener.searchBackListener(startDate, endDate);
		}else if(e.getSource() == btnCancel) {
			this.dispose();
		}
	}
}
