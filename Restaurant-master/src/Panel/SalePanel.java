package panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class SalePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public SalePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("Sale / Order");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label, BorderLayout.CENTER);
		
	}

}
