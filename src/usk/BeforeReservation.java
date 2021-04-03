package usk;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import util.Common;
import util.Utility;
import java.awt.Color;

public class BeforeReservation extends JPanel implements ActionListener {

	
	private static final long serialVersionUID = 1L;
	
	
	private JPanel panel;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private JButton btnUnConsent;
	private JButton btnConsent;
	private JTextArea textArea_2;
	private JLabel lblInformation;
	
	
	/**
	 * Create the panel.
	 */
	public BeforeReservation() {
		
		initialize();

	}
	
	public void initialize() {
		
		setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1042, 611);
		panel.setLayout(null);
		add(panel);
		
		
		lblInformation = new JLabel("Information");
		lblInformation.setFont(new Font("나눔고딕", Font.BOLD, 24));
		lblInformation.setBounds(100, 10, 134, 29);
		panel.add(lblInformation);
		
		textArea = new JTextArea();
		textArea.setText("⩥ 고객여러분께 부탁의 말씀\r\n\r\n코로나19로 인해 고객님들과의 여행의 건강과 안전의 확보를 최우선으로, \r\n정부가 공표하고 있는 \r\n「유원지 •테마파크에 제시된 신종코로나바이러스감염확대예방가이드라인」을\r\n기준으로, 국내외의 정세와 보건행정기관과 의료전문가의 지도를 받고, \r\n철저한 위생강화대책을 세운 후, 운영을 재개하였습니다. \r\n사전에 「본 입장에 관하여, 고객여러분에게의 부탁」을 확인후, 입장을 해주세요.");
		textArea.setBounds(100, 78, 868, 175);
		textArea.setEditable(false);
		panel.add(textArea);
		
		textArea_1 = new JTextArea();
		textArea_1.setText("⩥ 어트랙션•쇼 체험에 대하여\r\n\r\n일부 어트랙션에서는, 어트랙션입구에서 손소독하는 것에 협력해주십시오.\r\n대기열, 어트랙션체험중에 관계없이, 각 어트랙션시설에서는 반드시 마스크의 착용을 부탁드립니다.\r\n각 어트랙션의 대기열에서는, 다른 고객과의 간격을 두기위한 표시를 설치하였습니다.\r\n어트랙션체험시, 반드시 다른 고객과의 거리를 충분히 확보해주십시오. 또한, 체험중에 다른 고객과의 대면을 피하고, 동일방향에서만 체험을 해주시기 바랍니다.\r\n엔터테이너의 이동시에는, 엔터테이너와의 직접적인 접촉을 피하고, 거리를 확보해주시기 바랍니다.\r\n");
		textArea_1.setBounds(100, 263, 868, 164);
		textArea.setEditable(false);
		panel.add(textArea_1);
		
		textArea_2 = new JTextArea();
		textArea_2.setBounds(100, 437, 868, 73);
		textArea_2.setText("⩥입장에 대하여\r\n\r\n※거주하시는 지역의 이동에 관한 최근의 방침을 확인 후, 자신의 판단 하에 행동하여 주십시오.");
		textArea_2.setEditable(false);
		panel.add(textArea_2);
		
		btnConsent = new JButton("동의");
		btnConsent.setBounds(355, 543, 127, 23);
		btnConsent.addActionListener(this);
		panel.add(btnConsent);
		
		btnUnConsent = new JButton("동의안함(창닫기)");
		btnUnConsent.setBounds(579, 543, 127, 23);
		btnUnConsent.addActionListener(this);
		panel.add(btnUnConsent);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnConsent)
		{

			TicketsReservation ticketsReservation = new TicketsReservation();
			ticketsReservation.setBounds(0, 0, 1042, 611);
			
			JPanel panelCommon = (JPanel)Common.getHm().get("PANEL_COMMON");
			Utility.changePanel(panelCommon, ticketsReservation);
				
		}
		else if(e.getSource() == btnUnConsent)
		{
			
			MainUI panelMainUI = new MainUI();
			panelMainUI.setBounds(0, 0, 1042, 611);
			
			JPanel panelCommon = (JPanel)Common.getHm().get("PANEL_COMMON");
			Utility.changePanel(panelCommon, panelMainUI);
		
		}
		
	}
}
