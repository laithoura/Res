package panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class panelRawMaterial extends JPanel {

	/**
	 * Create the panel.
	 */
	public panelRawMaterial() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblRawMaterials = new JLabel("Raw Materials");
		lblRawMaterials.setHorizontalAlignment(SwingConstants.CENTER);
		lblRawMaterials.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblRawMaterials, BorderLayout.CENTER);

	}

}
