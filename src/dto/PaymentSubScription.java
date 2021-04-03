package dto;


import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;

import lib.BizException;
import lib.DBController;
import util.Common;

public class PaymentSubScription {

	private JFrame frame;
	private JPanel panel;
	private JLabel lblSubScriptionInfor;
	private JLabel lblabel;
	private JTextField tfSubScriptionNM;
	private JTextField tfSubScriptionTell;
	private JLabel label;
	private JLabel label_1;
	private JTextField tfSubScriptionEmail;
	private MberDTO mberDTO;

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "dev";
	String password = "123456";
	
	
	private PreparedStatement pstmt = null;
	private PreparedStatement pstmt2 = null;
	private PreparedStatement pstmt3 = null;
	private PreparedStatement pstmt4 = null;
	private PreparedStatement pstmt5 = null;
	private PreparedStatement pstmt6 = null;
	private Connection con = null;
	private ResultSet rs = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentSubScription window = new PaymentSubScription();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PaymentSubScription() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 587, 476);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(55, 72, 236, 319);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		MberDTO mberDto = selectMberDTO();
		
		lblSubScriptionInfor = new JLabel("예약자 정보");
		lblSubScriptionInfor.setFont(new Font("굴림", Font.PLAIN, 18));
		lblSubScriptionInfor.setBounds(25, 20, 96, 21);
		panel.add(lblSubScriptionInfor);
		
		lblabel = new JLabel("성명");
		lblabel.setFont(new Font("굴림", Font.PLAIN, 20));
		lblabel.setBounds(25, 84, 40, 24);
		panel.add(lblabel);
		
		tfSubScriptionNM = new JTextField(mberDto.getMberNm());
		tfSubScriptionNM.setBounds(104, 84, 96, 24);
		panel.add(tfSubScriptionNM);
		tfSubScriptionNM.setColumns(10);
		
		tfSubScriptionTell = new JTextField(mberDto.getMoblphon());
		tfSubScriptionTell.setColumns(10);
		tfSubScriptionTell.setBounds(104, 146, 96, 24);
		panel.add(tfSubScriptionTell);
		
		label = new JLabel("연락처");
		label.setFont(new Font("굴림", Font.PLAIN, 20));
		label.setBounds(25, 146, 60, 24);
		panel.add(label);
		
		label_1 = new JLabel("이메일");
		label_1.setFont(new Font("굴림", Font.PLAIN, 20));
		label_1.setBounds(25, 212, 60, 24);
		panel.add(label_1);
		
		tfSubScriptionEmail = new JTextField(mberDto.getEmail());
		tfSubScriptionEmail.setColumns(10);
		tfSubScriptionEmail.setBounds(104, 212, 96, 24);
		panel.add(tfSubScriptionEmail);
	}
	
	public MberDTO selectMberDTO() {
		String mberNo = (String)Common.getHm().get("MBER_NO");
		MberDTO mberDto = new MberDTO();
		
		try {
			
			DBController.connect();
			
			String sql
			= "SELECT * FROM MBER"
			+ "WHERE MBER_NO = ? ";
			
			DBController.setPstmt(sql);
			DBController.getPstmt().setString(1, mberNo);
			
			ResultSet rs = DBController.select();
			
			while(rs.next())
			{
				mberDto.setMberNo(rs.getString("MBER_NO"));
				mberDto.setMberID(rs.getString("MBER_ID"));
				mberDto.setPassword(rs.getString("PASSWORD"));
				mberDto.setMberNm(rs.getString("MBER_NM"));
				mberDto.setEmail(rs.getString("EMAIL"));
				mberDto.setMoblphon(rs.getString("MOBLPHON"));
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
		
		return mberDto;
	}
	
/*	
public ArrayList<MberDTO> selectMember(String mberNo) {
		
		ArrayList<MberDTO> aList = new ArrayList<MberDTO>();
		
		
		try {
			
			DBController.connect();
			
			String sql
			= "SELECT 	* 					"
			+ "FROM 	MBER     			"
			+ "WHERE 	MBERNO = ?  		"
			+ "ORDER BY MBERNO 		        ";
			
			DBController.setPstmt(sql);
			DBController.getPstmt().setString(1, mberNo);
			
			ResultSet rs = DBController.getRs();
			
			while(rs.next()) {
				MberDTO mberDTO = new MberDTO();
//				System.out.println(rs.getString(1));
				mberDTO.setMberNm	(	rs.getString("MBER_NM"));
				mberDTO.setMoblphon (		rs.getString("MOBLPHON"));
				mberDTO.setEmail	(	rs.getString("EMAIL"));
				
				aList.add(mberDTO);
			}
			DBController.close();
			
		} catch (BizException e) {
		} catch (SQLException e) {
			try {
				DBController.close();
			} catch (BizException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return aList;
	*/	
}
	

