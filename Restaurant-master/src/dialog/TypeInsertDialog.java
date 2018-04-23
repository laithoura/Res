package dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import connection.*;
import java.awt.Font;

public class TypeInsertDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Connection con= null ;
    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement pst;
    private JTextField txtName;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TypeInsertDialog dialog = new TypeInsertDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TypeInsertDialog() {
		con = DbConnection. getConnection();
			
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Name");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel.setBounds(34, 63, 46, 14);
			contentPanel.add(lblNewLabel);
		}
		
		JComboBox cboCategory = new JComboBox();
		cboCategory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboCategory.addItem("Product");
		cboCategory.addItem("Table");
		cboCategory.addItem("Raw material");
		cboCategory.setBounds(139, 115, 132, 20);
		contentPanel.add(cboCategory);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtName.setBounds(139, 60, 132, 20);
		contentPanel.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCategory.setBounds(34, 118, 95, 14);
		contentPanel.add(lblCategory);
		
		JLabel lblInsertType = new JLabel("Insert type");
		lblInsertType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInsertType.setBounds(32, 11, 155, 14);
		contentPanel.add(lblInsertType);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnSave = new JButton("Save");
			btnSave.setFont(new Font("Tahoma", Font.PLAIN, 12));
			buttonPane.add(btnSave);
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String name = txtName.getText();
					String category = cboCategory.getSelectedItem().toString();
					
					try {
						pst = con.prepareStatement("insert into type (name, category, status) value (?,?, true);");
						pst.setString(1, name);
						pst.setString(2, category);
						if (pst.executeUpdate() > 0) {
							JOptionPane.showMessageDialog(null, "Inserted sucessfully!");
						}
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Inserted unsucessfully!");
						e.printStackTrace();
					}
					
				}
			});
			
			JButton btnCancel = new JButton("Cancel");
			btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			buttonPane.add(btnCancel);
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtName.setText("");
				}
			});
		}
		
	}
}
