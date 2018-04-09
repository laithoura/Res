package Panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class UserAccountPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public UserAccountPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblUserAccount = new JLabel("User Account");
		lblUserAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserAccount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblUserAccount, BorderLayout.CENTER);

	}

}
