package usk;

import javax.swing.JPanel;

import dto.HotelDTO;
import lib.component.JImageLabel;
import util.Common;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Font;
//import javax.swing.JTextPane;
import java.awt.Color;

public class HotelDetailUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel lblHotelImage;
	private JLabel lblHotelNm;
//	private JLabel lblHotelDc;
	private JLabel lblHotelTel;
	private JLabel lblHotelAdd;
	
	private HotelDTO hotelDto;
	private JTextArea textAreaHotelDc;
	private JLabel lblHotelMap;

	/**
	 * Create the panel.
	 */
	public HotelDetailUI() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1042, 611);
		add(panel);
		panel.setLayout(null);
		
		hotelDto = (HotelDTO)Common.getHm().get("HOTEL_DTO");
		Common.getHm().remove("HOTEL_DTO");
		
		lblHotelImage = new JImageLabel(hotelDto.getPhotoCours(), 658, 387);
		lblHotelImage.setBackground(Color.WHITE);
		lblHotelImage.setBounds(53, 24, 658, 387);
		panel.add(lblHotelImage);
		
		lblHotelNm = new JLabel(hotelDto.getHotelNm());
		lblHotelNm.setBackground(Color.WHITE);
		lblHotelNm.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblHotelNm.setBounds(53, 435, 658, 53);
		panel.add(lblHotelNm);
		
		textAreaHotelDc = new JTextArea(hotelDto.getHotelDc());
		textAreaHotelDc.setBackground(Color.WHITE);
		textAreaHotelDc.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		textAreaHotelDc.setBounds(737, 24, 249, 325);
		panel.add(textAreaHotelDc);
		textAreaHotelDc.setLineWrap(true);
		
//		lblHotelDc = new JLabel(hotelDto.getHotelDc());
//		lblHotelDc.setBounds(737, 24, 249, 464);
//		panel.add(lblHotelDc);
		
		lblHotelTel = new JLabel(hotelDto.getHotelTelno());
		lblHotelTel.setBackground(Color.WHITE);
		lblHotelTel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblHotelTel.setBounds(53, 498, 211, 53);
		panel.add(lblHotelTel);
		
		lblHotelAdd = new JLabel(hotelDto.getHotelLqtx());
		lblHotelAdd.setBackground(Color.WHITE);
		lblHotelAdd.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblHotelAdd.setBounds(276, 498, 435, 53);
		panel.add(lblHotelAdd);
		
		lblHotelMap = new JLabel("호텔위치지도");
		lblHotelMap.setBackground(Color.WHITE);
		lblHotelMap.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lblHotelMap.setBounds(737, 359, 249, 192);
		panel.add(lblHotelMap);

	}
}
