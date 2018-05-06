package form;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import connection.DbConnection;
import control_classes.MessageShow;
import controller.UserDao;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginForm extends JFrame {
	
	private JTextField textBoxUsername;
	private JPasswordField textBoxPassword;
	private JLabel labelLogoImage;	
	
	public LoginForm(){
						
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {			
			ex.printStackTrace();
		}
		
		setResizable(false);				
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginForm.class.getResource("/resources/Flora.logo.png")));
		setTitle("Flora Restaurant");				
		setSize(747,401);
		{
			JPanel pContain = new JPanel();
			pContain.setBackground(Color.BLACK);
			getContentPane().add(pContain, BorderLayout.CENTER);
			pContain.setLayout(null);
			
			labelLogoImage = new JLabel("");
			labelLogoImage.setIcon(new ImageIcon(LoginForm.class.getResource("/resources/Flora.logo.png")));
			labelLogoImage.setHorizontalAlignment(SwingConstants.CENTER);
			labelLogoImage.setForeground(Color.WHITE);
			labelLogoImage.setFont(new Font("Tahoma", Font.PLAIN, 14));
			labelLogoImage.setBounds(20, 69, 288, 212);
			pContain.add(labelLogoImage);
			
			JLabel label_1 = new JLabel("Welcome to Flora Restaurant");
			label_1.setHorizontalAlignment(SwingConstants.CENTER);
			label_1.setForeground(Color.WHITE);
			label_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
			label_1.setBounds(23, 23, 283, 35);
			pContain.add(label_1);
			
			textBoxUsername = new JTextField();
			textBoxUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
			textBoxUsername.setColumns(10);
			textBoxUsername.setBounds(411, 120, 249, 28);
			pContain.add(textBoxUsername);
			
			JCheckBox checkBox = new JCheckBox("show");
			checkBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(checkBox.isSelected()) {
						textBoxPassword.setEchoChar((char)0);
					}else {
						textBoxPassword.setEchoChar('•');
					}
				}
			});
			checkBox.setForeground(Color.WHITE);
			checkBox.setFont(new Font("Dialog", Font.PLAIN, 13));
			checkBox.setBackground(Color.BLACK);
			checkBox.setBounds(664, 166, 57, 23);
			pContain.add(checkBox);
			
			textBoxPassword = new JPasswordField();
			textBoxPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
			textBoxPassword.setEchoChar('•');
			textBoxPassword.setBounds(411, 163, 249, 28);
			pContain.add(textBoxPassword);
			
			JLabel label_3 = new JLabel("");
			label_3.setIcon(new ImageIcon(LoginForm.class.getResource("/resources/lock_login.png")));
			label_3.setToolTipText("Password");
			label_3.setHorizontalAlignment(SwingConstants.CENTER);
			label_3.setBounds(372, 160, 35, 35);
			pContain.add(label_3);
			
			JLabel lblThankYouFor = new JLabel("Thank you for choosing our service");
			lblThankYouFor.setHorizontalAlignment(SwingConstants.CENTER);
			lblThankYouFor.setForeground(Color.WHITE);
			lblThankYouFor.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblThankYouFor.setBounds(20, 295, 288, 35);
			pContain.add(lblThankYouFor);
			
			JLabel lblSignIn = new JLabel("SIGN IN");
			lblSignIn.setHorizontalAlignment(SwingConstants.CENTER);
			lblSignIn.setForeground(Color.WHITE);
			lblSignIn.setFont(new Font("Tahoma", Font.PLAIN, 22));
			lblSignIn.setBounds(367, 69, 94, 35);
			pContain.add(lblSignIn);
			
			JLabel label_5 = new JLabel("");
			label_5.setIcon(new ImageIcon(LoginForm.class.getResource("/resources/user_login.png")));
			label_5.setToolTipText("Password");
			label_5.setHorizontalAlignment(SwingConstants.CENTER);
			label_5.setBounds(372, 117, 35, 35);
			pContain.add(label_5);
			
			JCheckBox chckbxNewCheckBox = new JCheckBox("Remember Password");
			chckbxNewCheckBox.setForeground(Color.WHITE);
			chckbxNewCheckBox.setBackground(Color.BLACK);
			chckbxNewCheckBox.setFont(new Font("Dialog", Font.PLAIN, 13));
			chckbxNewCheckBox.setBounds(374, 212, 154, 23);
			pContain.add(chckbxNewCheckBox);
			
			JButton btnLogin = new JButton("Login");
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String username = textBoxUsername.getText();
					String password = textBoxPassword.getText();
					if(username.isEmpty()) {
						MessageShow.Error("Please enter username.","Login");
						return;
					}else if(password.isEmpty()) {
						MessageShow.Error("Please enter password", "Login");
						return;
					}else {
						UserDao userDao = new UserDao();	
						DbConnection.user = userDao.compareLogin(username, password);
						
						if(DbConnection.user != null) {
							dispose();
							MainForm mainForm = new MainForm();
							mainForm.setVisible(true);
							
						}else {
							MessageShow.Error("Username or Password is incorrect.", "Login Failed");
						}
					}
				}
			});
			btnLogin.setMinimumSize(new Dimension(65, 23));
			btnLogin.setMaximumSize(new Dimension(65, 23));
			btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnLogin.setBounds(552, 208, 108, 30);
			pContain.add(btnLogin);
			
			JPanel panel = new JPanel();
			panel.setBackground(Color.LIGHT_GRAY);
			panel.setBounds(375, 104, 337, 2);
			pContain.add(panel);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBackground(Color.DARK_GRAY);
			panel_1.setBounds(0, 340, 743, 34);
			pContain.add(panel_1);
			
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e){
					System.exit(0);
				}
			});			
			setLocationRelativeTo(null);
		}
		scaleImageInLabel(labelLogoImage);		
	}
	
	
	private void scaleImageInLabel(JLabel label) {
		
		int width = (int) label.getBounds().getWidth();
		int height = (int) label.getBounds().getHeight();
		Image image = new ImageIcon("src/resources/Flora.logo.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		label.setIcon(new ImageIcon(image));		
	}
}
