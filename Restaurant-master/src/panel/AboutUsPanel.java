package panel;

import javax.swing.JPanel;

import form.MainForm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;


public class AboutUsPanel extends JPanel{
	
	
	private JLabel labelImage;
	private Dimension rectangle;
	private int width = 600;
	private int height = 350;
	
	public AboutUsPanel() {
								
			setLayout(new BorderLayout(0, 0));
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			add(panel, BorderLayout.CENTER);
			
			labelImage = new JLabel();
						
			setImageIconToLabel(labelImage);
			
			JLabel lblNewLabel = new JLabel("Group Members");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 30));
			
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(107)
						.addComponent(labelImage, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
						.addGap(93))
					.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
						.addGap(26)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
						.addGap(22))
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
						.addGap(23)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addGap(35)
						.addComponent(labelImage, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
						.addGap(71))
			);
			panel.setLayout(gl_panel);
			
			/*Get current size of Frame*/
			this.addComponentListener(new ComponentAdapter() {
						
				public void componentResized(ComponentEvent eE) {	
					
					rectangle = labelImage.getSize();
					width = rectangle.width;
					height = rectangle.height;
					setImageIconToLabel(labelImage);

				}
			});				
						
	}	
	
	private void setImageIconToLabel(JLabel labelImage) {
				
		labelImage.setIcon(null);
		Image image = new ImageIcon("src/profile/group_java.jpg").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);				
		labelImage.setIcon(new ImageIcon(image));					
	}
}
