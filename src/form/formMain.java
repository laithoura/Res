package form;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import interfaces.CallBackListenter;
import panel.panelMainMenu;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;

import controlclasses.ColorModel;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class formMain extends JFrame implements CallBackListenter{

	private ColorModel mColor = new ColorModel();
	private JLabel lblCopyRight;
	private JPanel pHeader, panelContainer, panelFooter;
	private JPanel panel;
	private JLabel lblBackWard;
	private JLabel lblTitle;	
	private panelMainMenu mainMenu;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formMain frame = new formMain();
					frame.setVisible(true);
					frame.setSize(1100,700);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */			
	
	public formMain() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 427);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		pHeader = new JPanel();
		pHeader.setBackground(mColor.getBackColor());
		getContentPane().add(pHeader, BorderLayout.NORTH);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		lblBackWard = new JLabel("");
		lblBackWard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openMainMenuPanel();	
			}
		});
		lblBackWard.setIcon(new ImageIcon(formMain.class.getResource("/Resources/Extend_48.png")));
		lblBackWard.setForeground(Color.WHITE);
		lblBackWard.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		lblTitle = new JLabel("Chhnganh Restaurant");
		lblTitle.setForeground(mColor.getForeColor());
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		URL imgPath = formMain.class.getResource("/Resources/Product_96.png");
		Image getImg = new ImageIcon(imgPath).getImage().getScaledInstance(50, 20, Image.SCALE_DEFAULT);
		GroupLayout gl_pHeader = new GroupLayout(pHeader);
		gl_pHeader.setHorizontalGroup(
			gl_pHeader.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pHeader.createSequentialGroup()
					.addComponent(lblBackWard, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(355, Short.MAX_VALUE))
		);
		gl_pHeader.setVerticalGroup(
			gl_pHeader.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pHeader.createSequentialGroup()
					.addGroup(gl_pHeader.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pHeader.createSequentialGroup()
							.addGap(7)
							.addGroup(gl_pHeader.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTitle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)))
						.addGroup(gl_pHeader.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblBackWard, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		pHeader.setLayout(gl_pHeader);
				
		panelContainer = new JPanel();
		panelContainer.setAlignmentY(0.0f);
		panelContainer.setAlignmentX(0.0f);
		panelContainer.setBackground(mColor.getLightBrown());
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
		mainMenu = new panelMainMenu();	
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
