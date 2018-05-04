package form;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;

import control_classes.ColorModel;
import interfaces.CallBackListenter;
import panel.MainMenuPanel;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainForm extends JFrame implements CallBackListenter{

	private ColorModel mColor = new ColorModel();
	private JLabel lblCopyRight;
	private JPanel pHeader, panelContainer, panelFooter;
	private JPanel panel;
	private JLabel lblBackWard;
	private JLabel lblTitle;
	private MainMenuPanel mainMenu;
	private JLabel label_5;
	private JLabel label;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */			

	public MainForm() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		pHeader = new JPanel();
		pHeader.setBackground(mColor.getBackColor());
		getContentPane().add(pHeader, BorderLayout.NORTH);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		
		lblBackWard = new JLabel("");
		lblBackWard.setToolTipText("Go Back");
		lblBackWard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openMainMenuPanel();	
			}
		});
		
		lblBackWard.setIcon(new ImageIcon(MainForm.class.getResource("/Resources/Back_32.png")));
		lblBackWard.setForeground(Color.WHITE);
		lblBackWard.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		lblTitle = new JLabel("FLORA RESTAURANT");
		lblTitle.setForeground(mColor.getForeColor());
		lblTitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		URL imgPath = MainForm.class.getResource("/Resources/Product_96.png");
		Image getImg = new ImageIcon(imgPath).getImage().getScaledInstance(50, 20, Image.SCALE_DEFAULT);
		
		JPanel panelRightInfo = new JPanel();
		panelRightInfo.setBackground(mColor.getBackColor());
		FlowLayout fl_panelRightInfo = (FlowLayout) panelRightInfo.getLayout();
		fl_panelRightInfo.setAlignment(FlowLayout.RIGHT);
		GroupLayout gl_pHeader = new GroupLayout(pHeader);
		gl_pHeader.setHorizontalGroup(
			gl_pHeader.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pHeader.createSequentialGroup()
					.addGap(1)
					.addComponent(lblBackWard, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addGroup(gl_pHeader.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pHeader.createSequentialGroup()
							.addGap(13)
							.addComponent(panelRightInfo, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
							.addGap(8))
						.addGroup(gl_pHeader.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(355, Short.MAX_VALUE))))
		);
		gl_pHeader.setVerticalGroup(
			gl_pHeader.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pHeader.createSequentialGroup()
					.addGroup(gl_pHeader.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pHeader.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_pHeader.createSequentialGroup()
								.addGap(5)
								.addComponent(panelRightInfo, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
							.addComponent(panel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pHeader.createSequentialGroup()
							.addGap(9)
							.addComponent(lblBackWard))
						.addGroup(gl_pHeader.createSequentialGroup()
							.addGap(6)
							.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(MainForm.class.getResource("/resources/Settings_32.png")));
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelRightInfo.add(label);
		
		JLabel lblName = new JLabel("");
		lblName.setIcon(new ImageIcon(MainForm.class.getResource("/Resources/User_32.png")));
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelRightInfo.add(lblName);
		
		JLabel lblUsername = new JLabel("Username : Role Name");
		lblUsername.setForeground(mColor.getForeColor());
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelRightInfo.add(lblUsername);
		
		label_5 = new JLabel(" ");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelRightInfo.add(label_5);
		
		JLabel lblMinimizeIcon = new JLabel("");
		lblMinimizeIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		lblMinimizeIcon.setToolTipText("Minimize");
		lblMinimizeIcon.setIcon(new ImageIcon(MainForm.class.getResource("/Resources/Minus_32.png")));
		lblMinimizeIcon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelRightInfo.add(lblMinimizeIcon);
		
		JLabel lblCloseIcon = new JLabel("");
		lblCloseIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblCloseIcon.setToolTipText("Close\r\n");
		lblCloseIcon.setIcon(new ImageIcon(MainForm.class.getResource("/Resources/Close_32.png")));
		lblCloseIcon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelRightInfo.add(lblCloseIcon);
		pHeader.setLayout(gl_pHeader);
				
		panelContainer = new JPanel();
		panelContainer.setAlignmentY(0.0f);
		panelContainer.setAlignmentX(0.0f);
		panelContainer.setBackground(mColor.getBackColor());
		getContentPane().add(panelContainer, BorderLayout.CENTER);
		panelContainer.setLayout(new BorderLayout(0, 0));
		
		panelFooter = new JPanel();
		panelFooter.setAlignmentY(0.0f);
		panelFooter.setAlignmentX(0.0f);
		panelFooter.setBackground(mColor.getBackColor());
		FlowLayout fl_panelFooter = (FlowLayout) panelFooter.getLayout();
		fl_panelFooter.setVgap(10);
		fl_panelFooter.setHgap(10);
		getContentPane().add(panelFooter, BorderLayout.SOUTH);
		
		lblCopyRight = new JLabel("SLS@19th@CopyRight2018");
		lblCopyRight.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCopyRight.setForeground(mColor.getForeColor());
		panelFooter.add(lblCopyRight);
				
		openMainMenuPanel();			
	}

	private void openMainMenuPanel() {
		mainMenu = new MainMenuPanel();	
		mainMenu.SetCallBackListener(this);
		panelContainer.removeAll();					
		panelContainer.revalidate();
		panelContainer.add(mainMenu,BorderLayout.CENTER);		
	}

	@Override
	public void CallBack(Object sender) {		
		panelContainer.removeAll();					
		panelContainer.revalidate();
		panelContainer.add((JComponent)sender,BorderLayout.CENTER);	
	}
}
