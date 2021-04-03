package usk;

import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;

public class SchdulUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JTextArea txtrn;

	/**
	 * Create the panel.
	 */
	public SchdulUI() {
		setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1042, 611);
		add(panel);
		panel.setLayout(null);
		
		ImageIcon sd4 = new ImageIcon("img/schdul/2021_4.png");
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(56, 10, 590, 591);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(sd4);
		
		ImageIcon sd5 = new ImageIcon("img/schdul/2021_5.png");
		lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(658, 197, 339, 339);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(sd5);
		
		txtrn = new JTextArea();
		txtrn.setFont(new Font("나눔고딕", Font.BOLD, 17));
		txtrn.setText("퍼레이드 및 행사일정은 변동될수 있습니다.\r\n영업시간은 변동될수 있습니다");
		txtrn.setBounds(659, 123, 339, 66);
		panel.add(txtrn);

	}
}
