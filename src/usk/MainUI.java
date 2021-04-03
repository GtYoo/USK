package usk;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;

public class MainUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblMainImage1;
	private JLabel lblMainImage2;
	private JLabel lblMainImage3;
	private JPanel panel;
	private JLabel lblMainImage4;

	/**
	 * Create the panel.
	 */
	public MainUI() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		
	
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1042, 611);
		add(panel);
		panel.setLayout(null);
		
		ImageIcon mainImage1 = new ImageIcon("img/mainImage/nomalImage1.png");
		lblMainImage1 = new JLabel("");
		lblMainImage1.setBounds(56, 52, 456, 248);
		panel.add(lblMainImage1);
		lblMainImage1.setIcon(mainImage1);
		
		ImageIcon mainImage2 = new ImageIcon("img/mainImage/nomalImage2.png");
		lblMainImage2 = new JLabel("");
		lblMainImage2.setBounds(530, 52, 456, 248);
		panel.add(lblMainImage2);
		lblMainImage2.setIcon(mainImage2);
		
		ImageIcon mainImage3 = new ImageIcon("img/mainImage/nomalImage3.png");
		lblMainImage3 = new JLabel("");
		lblMainImage3.setBounds(56, 318, 456, 248);
		panel.add(lblMainImage3);
		lblMainImage3.setIcon(mainImage3);
		
		ImageIcon mainImage4 = new ImageIcon("img/mainImage/nomalImage4.png");
		lblMainImage4 = new JLabel("");
		lblMainImage4.setBounds(530, 318, 456, 248);
		panel.add(lblMainImage4);
		lblMainImage4.setIcon(mainImage4);
		

	}
}
