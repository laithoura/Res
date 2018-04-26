package control_classes;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class InputControl {
	
	public static void allowIntegerOnly(java.awt.event.KeyEvent evt) {                                                      
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            evt.consume();
        }
    }
	
	public static void allowFloatingPoint(java.awt.event.KeyEvent e) {
		
		String c = e.getKeyChar()+"";		
		if (!c.matches("[0-9.]+")){
        	e.consume();
        }
	}
	
	public static void allowLetterOnly(java.awt.event.KeyEvent e) {
		char enter=e.getKeyChar();
		if(Character.isDigit(enter)) {
			e.consume();
		}
	}
	
	public static void inputFloatingPoint(JTextField txt) {
		txt.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				InputControl.allowFloatingPoint(e);
			}
		});
	}
	
	public static void inputInteger(JTextField txt) {
		txt.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				InputControl.allowIntegerOnly(e);
			}
		});
	}
	
	public static void inputLetter(JTextField txt) {
		txt.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				InputControl.allowLetterOnly(e);
			}
		});
	}
	
	public static void inputAnythings(JTextField txt) {
		txt.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				
			}
		});
	}
}
