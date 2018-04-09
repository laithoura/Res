package panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class panelType extends JPanel {

	/**
	 * Create the panel.
	 */
	public panelType() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblTypemoreDetails = new JLabel("Type (more details....)");
		lblTypemoreDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblTypemoreDetails.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblTypemoreDetails, BorderLayout.CENTER);

	}

}
