package panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class panelProduct extends JPanel {

	/**
	 * Create the panel.
	 */
	public panelProduct() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblProduct = new JLabel("Product");
		lblProduct.setHorizontalAlignment(SwingConstants.CENTER);
		lblProduct.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblProduct, BorderLayout.CENTER);

	}

}
