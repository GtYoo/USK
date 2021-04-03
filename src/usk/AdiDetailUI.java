package usk;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import dto.AdiFcltyDTO;
import dto.ReprsntGoodsDTO;
import lib.component.JImageLabel;
import lib.component.JMultilineLabel;
import util.Common;

public class AdiDetailUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel lblShopImage;
	private JLabel lblShopNm;
	private JTextArea textShopDc;
	private AdiFcltyDTO adifcltyDto;


	private JLabel lblRpmImage1;
//	private JLabel lblRpmImage2;
//	private JLabel lblRpmImage3;
	private JLabel lblRpm;
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "dev";
	String password = "123456";

	private PreparedStatement pstmt = null;
//	private PreparedStatement pstmt2 = null;
	private Connection con = null;
	private ResultSet rs = null;

	/**
	 * Create the panel.
	 */
	public AdiDetailUI() {
		setLayout(null);
		
		adifcltyDto = (AdiFcltyDTO)Common.getHm().get("ADIFCLTY_DTO");
//		adifcltyDto = (AdiFcltyDTO)Common.getHm().get("ADIFCLTY_DTO");
		ArrayList<ReprsntGoodsDTO> reprsntGoodsList = new ArrayList<ReprsntGoodsDTO>();
		ReprsntGoodsDTO reprsntGoodsDto = new ReprsntGoodsDTO();
		String goodsSearch = "SELECT * FROM REPRSNT_GOODS WHERE FCLTY_NO = ?";
		String fcltyNo = adifcltyDto.getFcltyNo();
		Common.getHm().remove("ADIFCLTY_DTO");
		
		
		try {
			
			dbconnect();
			
			pstmt = con.prepareStatement(goodsSearch);
			pstmt.setString(1, fcltyNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				reprsntGoodsDto = new ReprsntGoodsDTO();
				reprsntGoodsDto.setAreaNo(rs.getString("AREA_NO"));
				reprsntGoodsDto.setFcltyNo(rs.getString("FCLTY_NO"));
				reprsntGoodsDto.setGoodsNo(rs.getString("GOODS_NO"));
				reprsntGoodsDto.setPhotoCours(rs.getString("PHOTO_COURS"));
				
				reprsntGoodsList.add(reprsntGoodsDto);
			}
			
		} catch(Exception r) {
			r.printStackTrace();
		} finally {
			try { if(rs != null) rs.close();
			} catch(SQLException e1) {}
			try { if(pstmt != null) pstmt.close();
			} catch(SQLException e1) {}
			try { if(con != null) con.close();
			} catch(SQLException e1) {}
		}
		
		
		
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1042, 611);
		add(panel);
		panel.setLayout(null);
		
//		adifcltyDto = (AdiFcltyDTO)Common.getHm().get("ADIFCLTY_DTO");
//		Common.getHm().remove("ADIFCLTY_DTO");
		

//		ReprsntGoodsDTO ReprsntGoodsDTO = (ReprsntGoodsDTO)Common.getHm().get("REPRSNT_GOODSDTO");
//		Common.getHm().remove("REPRSNT_GOODSDTO");
		
//		System.out.println(adifcltyDto.getPhotoCours());
		lblShopImage = new JImageLabel(adifcltyDto.getPhotoCours(), 658, 387);
		lblShopImage.setBounds(54, 90, 516, 285);
		panel.add(lblShopImage);
		
		lblShopNm = new JLabel(adifcltyDto.getFcltyNm());
		lblShopNm.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblShopNm.setBounds(54, 24, 658, 53);
		panel.add(lblShopNm);
	
		
		textShopDc = new JMultilineLabel(adifcltyDto.getFcltyDc());
		textShopDc.setLineWrap(true);
		textShopDc.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		textShopDc.setBounds(737, 24, 249, 389);
		panel.add(textShopDc);
		
		int[] x_point = {35, 290, 566};
		for (int i = 0; i < reprsntGoodsList.size(); i++) {
		
			ImageIcon img = new ImageIcon(reprsntGoodsList.get(i).getPhotoCours());
//			lblRpmImage1 = new JImageLabel(reprsntGoodsDto.getPhotoCours(), 213, 150);
			lblRpmImage1 = new JLabel();
			lblRpmImage1.setBounds(x_point[i], 423, 213, 150);
			panel.add(lblRpmImage1);
			lblRpmImage1.setIcon(img);
		
		}
		
		
		lblRpm = new JLabel("대표메뉴");
		lblRpm.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblRpm.setBounds(54, 385, 57, 15);
		panel.add(lblRpm);
	}
	
	public void dbconnect() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
