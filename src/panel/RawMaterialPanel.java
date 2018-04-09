package Panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class RawMaterialPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public RawMaterialPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblRawMaterials = new JLabel("Raw Materials");
		lblRawMaterials.setHorizontalAlignment(SwingConstants.CENTER);
		lblRawMaterials.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblRawMaterials, BorderLayout.CENTER);

	}

}
