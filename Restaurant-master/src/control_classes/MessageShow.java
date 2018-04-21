package control_classes;

import javax.swing.JOptionPane;

public class MessageShow {
	
	public static void success(String description,String title) {
		JOptionPane.showMessageDialog(null, description, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void Warning(String description,String title) {
		JOptionPane.showMessageDialog(null, description, title, JOptionPane.WARNING_MESSAGE);
	}
	
	public static void Error(String description,String title) {
		JOptionPane.showMessageDialog(null, description, title, JOptionPane.ERROR_MESSAGE);
	}
	
	public static int deleteVerification(String description,String title) {
		return JOptionPane.showConfirmDialog(null, description, title, JOptionPane.YES_NO_OPTION);		
	}
	
	public static int saveVerification(String description,String title) {
		return JOptionPane.showConfirmDialog(null, description, title, JOptionPane.YES_NO_CANCEL_OPTION);		
	}
}
