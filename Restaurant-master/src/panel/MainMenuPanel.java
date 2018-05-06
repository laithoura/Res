package panel;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import control_classes.ColorModel;
import interfaces.CallBackListenter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import java.awt.Font;

public class MainMenuPanel extends JPanel implements MouseListener{
	
	private JLabel lblSale, lblImport, lblBooking, lblTable, lblProduct, lblRawMaterial, lblType,lblUserAccount;
	private JPanel panelContainer, panelSale, panelImport, panelBooking, panelTable, panelProduct, panelRawMaterial, panelType,panelUserAccount;
	private ColorModel mColor = new ColorModel();
	
	private CallBackListenter callBack;
	
	public void SetCallBackListener(CallBackListenter callBack) {
		this.callBack = callBack;
	}	
	
	public MainMenuPanel() {		
		setBackground(new Color(139, 69, 19));
		setAlignmentX(0.0f);
		setAlignmentY(0.0f);
		setLayout(new BorderLayout(0, 0));
		
		panelContainer = new JPanel();
		add(panelContainer, BorderLayout.CENTER);
		panelContainer.setLayout(new GridLayout(2, 0, 0, 0));
		
		panelSale = new JPanel();
		panelSale.setBackground(new Color(139, 69, 19));
		panelContainer.add(panelSale);
		
		lblSale = new JLabel("Sale");
		lblSale.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblSale.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSale.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/Resources/Sale_96.png")));
		lblSale.setHorizontalAlignment(SwingConstants.CENTER);
		lblSale.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_panelSale = new GroupLayout(panelSale);
		gl_panelSale.setHorizontalGroup(
			gl_panelSale.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelSale.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSale, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
					.addGap(11))
		);
		gl_panelSale.setVerticalGroup(
			gl_panelSale.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelSale.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSale, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelSale.setLayout(gl_panelSale);
		
		panelImport = new JPanel();
		panelImport.setBackground(new Color(139, 69, 19));
		panelContainer.add(panelImport);
		
		lblImport = new JLabel("Import");
		lblImport.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/Resources/Import_96.png")));
		lblImport.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblImport.setHorizontalTextPosition(SwingConstants.CENTER);
		lblImport.setHorizontalAlignment(SwingConstants.CENTER);
		lblImport.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblImport.setBackground(new Color(176, 224, 230));
		GroupLayout gl_panelImport = new GroupLayout(panelImport);
		gl_panelImport.setHorizontalGroup(
			gl_panelImport.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelImport.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImport, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelImport.setVerticalGroup(
			gl_panelImport.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelImport.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImport, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelImport.setLayout(gl_panelImport);
		
		panelBooking = new JPanel();
		panelBooking.setBackground(new Color(139, 69, 19));
		panelContainer.add(panelBooking);
		
		lblBooking = new JLabel("Booking");
		lblBooking.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/Resources/Booking_96.png")));
		lblBooking.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblBooking.setHorizontalTextPosition(SwingConstants.CENTER);
		lblBooking.setHorizontalAlignment(SwingConstants.CENTER);
		lblBooking.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBooking.setBackground(new Color(176, 224, 230));
		GroupLayout gl_panelBooking = new GroupLayout(panelBooking);
		gl_panelBooking.setHorizontalGroup(
			gl_panelBooking.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelBooking.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBooking, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelBooking.setVerticalGroup(
			gl_panelBooking.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelBooking.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBooking, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelBooking.setLayout(gl_panelBooking);
		
		panelTable = new JPanel();
		panelTable.setBackground(new Color(139, 69, 19));
		panelContainer.add(panelTable);
		
		lblTable = new JLabel("Table");
		lblTable.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/Resources/Table_96.png")));
		lblTable.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblTable.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblTable.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTable.setBackground(new Color(176, 224, 230));
		GroupLayout gl_panelTable = new GroupLayout(panelTable);
		gl_panelTable.setHorizontalGroup(
			gl_panelTable.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelTable.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTable, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelTable.setVerticalGroup(
			gl_panelTable.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTable.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTable, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelTable.setLayout(gl_panelTable);
		
		panelProduct = new JPanel();
		panelProduct.setBackground(new Color(139, 69, 19));
		panelContainer.add(panelProduct);
		
		lblProduct = new JLabel("Product");
		lblProduct.setHorizontalTextPosition(SwingConstants.CENTER);
		lblProduct.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblProduct.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/Resources/Product_96.png")));
		lblProduct.setHorizontalAlignment(SwingConstants.CENTER);
		lblProduct.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_panelProduct = new GroupLayout(panelProduct);
		gl_panelProduct.setHorizontalGroup(
			gl_panelProduct.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelProduct.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblProduct, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelProduct.setVerticalGroup(
			gl_panelProduct.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelProduct.createSequentialGroup()
					.addGap(9)
					.addComponent(lblProduct, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelProduct.setLayout(gl_panelProduct);
		
		panelRawMaterial = new JPanel();
		panelRawMaterial.setBackground(new Color(139, 69, 19));
		panelContainer.add(panelRawMaterial);
		
		lblRawMaterial = new JLabel("Raw Material");
		lblRawMaterial.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/Resources/RawMaterial_96.png")));
		lblRawMaterial.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblRawMaterial.setHorizontalTextPosition(SwingConstants.CENTER);
		lblRawMaterial.setHorizontalAlignment(SwingConstants.CENTER);
		lblRawMaterial.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRawMaterial.setBackground(new Color(176, 224, 230));
		GroupLayout gl_panelRawMaterial = new GroupLayout(panelRawMaterial);
		gl_panelRawMaterial.setHorizontalGroup(
			gl_panelRawMaterial.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRawMaterial.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRawMaterial, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelRawMaterial.setVerticalGroup(
			gl_panelRawMaterial.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRawMaterial.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRawMaterial, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelRawMaterial.setLayout(gl_panelRawMaterial);
		
		panelType = new JPanel();
		panelType.setBackground(new Color(139, 69, 19));
		panelContainer.add(panelType);
		
		lblType = new JLabel("Type");
		lblType.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblType.setHorizontalTextPosition(SwingConstants.CENTER);
		lblType.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/Resources/Category_96.png")));
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_panelType = new GroupLayout(panelType);
		gl_panelType.setHorizontalGroup(
			gl_panelType.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelType.createSequentialGroup()
					.addGap(15)
					.addComponent(lblType, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelType.setVerticalGroup(
			gl_panelType.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelType.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblType, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelType.setLayout(gl_panelType);
		
		panelUserAccount = new JPanel();
		panelUserAccount.setBackground(new Color(139, 69, 19));
		panelContainer.add(panelUserAccount);
		
		lblUserAccount = new JLabel("User Account");
		lblUserAccount.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblUserAccount.setHorizontalTextPosition(SwingConstants.CENTER);
		lblUserAccount.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/Resources/User_96.png")));
		lblUserAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserAccount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_panelUserAccount = new GroupLayout(panelUserAccount);
		gl_panelUserAccount.setHorizontalGroup(
			gl_panelUserAccount.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelUserAccount.createSequentialGroup()
					.addGap(12)
					.addComponent(lblUserAccount, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
					.addGap(13))
		);
		gl_panelUserAccount.setVerticalGroup(
			gl_panelUserAccount.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelUserAccount.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblUserAccount, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelUserAccount.setLayout(gl_panelUserAccount);
		
		registerEvent();

	}

	private void registerEvent() {
		this.lblSale.addMouseListener(this);
		this.lblImport.addMouseListener(this);
		this.lblBooking.addMouseListener(this);
		this.lblTable.addMouseListener(this);
		this.lblProduct.addMouseListener(this);
		this.lblRawMaterial.addMouseListener(this);
		this.lblType.addMouseListener(this);
		this.lblUserAccount.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {	
		if(e.getSource() == lblSale) {
			SalePanel sale = new SalePanel();
			callBack.CallBack(sale);
		}else if(e.getSource() == lblImport) {
			ImportPanel imp = new ImportPanel();
			callBack.CallBack(imp);
		}else if(e.getSource() == lblBooking) {
			BookingPanel booking = new BookingPanel();
			callBack.CallBack(booking);
		}else if(e.getSource() == lblTable) {
			TablePanel table = new TablePanel();
			callBack.CallBack(table);
		}else if(e.getSource() == lblProduct) {
			ProductPanel product = new ProductPanel();
			callBack.CallBack(product);
		}else if(e.getSource() == lblRawMaterial) {
			RawMaterialPanel raw = new RawMaterialPanel();
			callBack.CallBack(raw);
		}else if(e.getSource() == lblType) {
			//TypePanel type = new TypePanel();
			  DisplayTablePanel displayTablePanel = new DisplayTablePanel();
			callBack.CallBack(displayTablePanel);
			//callBack.CallBack(type);
		}else if(e.getSource() == lblUserAccount) {
			UserAccountPanel user = new UserAccountPanel();
			callBack.CallBack(user);
		}			
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == lblSale || e.getSource() == lblImport || e.getSource() == lblBooking || e.getSource() == lblTable || e.getSource() == lblProduct || e.getSource() == lblRawMaterial || e.getSource() == lblType || e.getSource() == lblUserAccount)
		{
			((JComponent) e.getSource()).setForeground(mColor.getForeColor());
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == lblSale || e.getSource() == lblImport || e.getSource() == lblBooking || e.getSource() == lblTable || e.getSource() == lblProduct || e.getSource() == lblRawMaterial || e.getSource() == lblType || e.getSource() == lblUserAccount)
		{			
			((JComponent) e.getSource()).setForeground(mColor.getBlackColor());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		//JOptionPane.showMessageDialog(null, "Released");
		
	}
}
