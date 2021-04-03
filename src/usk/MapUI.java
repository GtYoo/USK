package usk;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

public class MapUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel lblMap;
	private JLabel lblAdd;
	private JLabel lblTel;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel lblNewLabel;
	private JLabel label_3;
	private JLabel lblNamyangrobeongil;
	private JLabel lblNamyangeupHwaseongsiGyeonggido;
	private JLabel lblGyeonggidoRepublicOf;

	/**
	 * Create the panel.
	 */
	public MapUI() {
		setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1042, 611);
		add(panel);
		panel.setLayout(null);
		
		ImageIcon mapMain = new ImageIcon("img/map/uskmap.png");
		lblMap = new JLabel("New label");
		lblMap.setBounds(51, 33, 588, 357);
		panel.add(lblMap);
		lblMap.setIcon(mapMain);
		
		lblAdd = new JLabel("경기도 화성시 남양로 1549번길 10");
		lblAdd.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblAdd.setBounds(651, 102, 364, 87);
		panel.add(lblAdd);
		
		lblTel = new JLabel("대표번호 1534 - 6243");
		lblTel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblTel.setBounds(651, 337, 364, 77);
		panel.add(lblTel);
		
		label = new JLabel("교통편 안내");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		label.setBounds(51, 400, 364, 50);
		panel.add(label);
		
		label_1 = new JLabel("인천국제공항 > 1009번 유니버셜 리무진버스 > 유니버셜 스튜디오 코리아");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_1.setBounds(51, 474, 526, 50);
		panel.add(label_1);
		
		label_2 = new JLabel("김포국제공항 > 3000번 유니버셜 리무진버스 > 유니버셜 스튜디오 코리아");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_2.setBounds(51, 534, 526, 50);
		panel.add(label_2);
		
		lblNewLabel = new JLabel("유니버셜 스튜디오 코리아");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblNewLabel.setBounds(651, 143, 364, 77);
		panel.add(lblNewLabel);
		
		label_3 = new JLabel("18255");
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		label_3.setBounds(651, 68, 364, 87);
		panel.add(label_3);
		
		lblNamyangrobeongil = new JLabel("10, Namyang-ro 930beon-gil, ");
		lblNamyangrobeongil.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblNamyangrobeongil.setBounds(651, 230, 364, 87);
		panel.add(lblNamyangrobeongil);
		
		lblNamyangeupHwaseongsiGyeonggido = new JLabel("Namyang-eup, Hwaseong-si,");
		lblNamyangeupHwaseongsiGyeonggido.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblNamyangeupHwaseongsiGyeonggido.setBounds(651, 260, 364, 77);
		panel.add(lblNamyangeupHwaseongsiGyeonggido);
		
		lblGyeonggidoRepublicOf = new JLabel("Gyeonggi-do, Republic of Korea");
		lblGyeonggidoRepublicOf.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblGyeonggidoRepublicOf.setBounds(651, 285, 364, 77);
		panel.add(lblGyeonggidoRepublicOf);

	}
}
