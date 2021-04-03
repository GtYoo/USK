package usk;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.MberDTO;
import java.awt.Color;


//import lib.BizException;
//import lib.DBController;

public class MberJoinPopup extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfId;
	private JPasswordField passwordJoin;
	private JPasswordField passwordPJoin;
	private JTextField tfName;
	private JTextField tfEmail;
	private JTextField tfTel;
	
	private JButton okButton;
	private JButton cancelButton;
	
	MberDTO mberDto = new MberDTO();
	
//	private DBController db = new DBController();
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "dev";
	String password = "123456";

	private PreparedStatement pstmt = null;
	private Connection con = null;
	private ResultSet rs = null;
	private JButton btnIdCheck;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MberJoinPopup dialog = new MberJoinPopup();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MberJoinPopup() {
		
		setBounds(100, 100, 750, 612);
		setSize(649, 545);				//중앙에 창띄우기
		setLocationRelativeTo(null);	//중앙에 창띄우기
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblId = new JLabel("* 아이디");
			lblId.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			lblId.setBounds(127, 123, 133, 39);
			contentPanel.add(lblId);
		}
		{
			JLabel lblPassword = new JLabel("* 비밀번호");
			lblPassword.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			lblPassword.setBounds(127, 171, 133, 39);
			contentPanel.add(lblPassword);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("회원가입");
			lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 30));
			lblNewLabel_1.setBounds(113, 28, 138, 67);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblName = new JLabel("* 성명");
			lblName.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			lblName.setBounds(127, 268, 133, 39);
			contentPanel.add(lblName);
		}
		{
			JLabel lblPasswordP = new JLabel("* 비밀번호확인");
			lblPasswordP.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			lblPasswordP.setBounds(127, 220, 150, 39);
			contentPanel.add(lblPasswordP);
		}
		{
			JLabel lblEmail = new JLabel("* 이메일");
			lblEmail.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			lblEmail.setBounds(127, 317, 133, 39);
			contentPanel.add(lblEmail);
		}
		{
			JLabel lblTel = new JLabel("전화번호");
			lblTel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			lblTel.setBounds(144, 365, 133, 39);
			contentPanel.add(lblTel);
		}
		{
			tfId = new JTextField();
			tfId.setBounds(311, 131, 116, 23);
			contentPanel.add(tfId);
			tfId.setColumns(10);
		}
		{
			passwordJoin = new JPasswordField();
			passwordJoin.setBounds(311, 179, 116, 23);
			contentPanel.add(passwordJoin);
		}
		{
			passwordPJoin = new JPasswordField();
			passwordPJoin.setBounds(311, 228, 116, 23);
			contentPanel.add(passwordPJoin);
		}
		{
			tfName = new JTextField();
			tfName.setColumns(10);
			tfName.setBounds(311, 276, 116, 23);
			contentPanel.add(tfName);
		}
		{
			tfEmail = new JTextField();
			tfEmail.setColumns(10);
			tfEmail.setBounds(311, 325, 116, 23);
			contentPanel.add(tfEmail);
		}
		{
			tfTel = new JTextField();
			tfTel.setColumns(10);
			tfTel.setBounds(311, 373, 116, 23);
			contentPanel.add(tfTel);
		}
		
		btnIdCheck = new JButton("중복체크");
		btnIdCheck.setBackground(Color.WHITE);
		btnIdCheck.setBounds(448, 131, 97, 23);
		contentPanel.add(btnIdCheck);
		btnIdCheck.addActionListener(this);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("확인");
				okButton.setBackground(Color.WHITE);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(this);
			}
			{
				cancelButton = new JButton("취소");
				cancelButton.setBackground(Color.WHITE);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(this);
			}
		}
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == okButton)
		{
			try {
				insertMembership();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
//			this.dispose();
		}
		else if(e.getSource() == cancelButton)
		{
			this.dispose();
		}
		else if(e.getSource() == btnIdCheck)
		{
			idCheck();
		}
	}
	
	public void dbconnect() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void insertMembership() throws Exception {
		
		//* 시퀀스로 pk자동생성  *//

//		CREATE SEQUENCE MBER_NO_SEQ
//		START WITH 1
//		INCREMENT BY 1;
		String insertMember = "INSERT INTO MBER VALUES(MBER_NO_SEQ.NEXTVAL,?,?,?,?,?,?)";
		
		int ret;
		String passwordJw = new String(passwordJoin.getPassword());		//비밀번호
		String passwordPw = new String(passwordPJoin.getPassword());	//비밀번호확인
		
		
//		String mberId = tfId.getText();			// 회원아이디
//		String password = passwordJw;			// 비밀번호
//		String passwordw = passwordPw;			// 비밀번호 확인
//		String mberNm = tfName.getText();		// 회원명
//		String email = tfEmail.getText();		// 이메일
//		String moblphon = tfTel.getText();		// 전화번호
		
		mberDto.setMberID(tfId.getText());		//아이디
		mberDto.setPassword(passwordJw);		//비밀번호
		mberDto.setMberNm(tfName.getText());	//성명
		mberDto.setEmail(tfEmail.getText());	//이메일
		mberDto.setMoblphon(tfTel.getText());	//전화번호
		
		//ID중복 체크 만들기
		//do while 검토
		if(tfId.getText().isEmpty() && passwordJw.isEmpty() && passwordPw.isEmpty() &&
				tfName.getText().isEmpty() && tfEmail.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(contentPanel, "필수항목을 입력해주세요.");
			return;
		}
		else if(!mberDto.getPassword().equals(passwordPw)) 
		{
			JOptionPane.showMessageDialog(contentPanel, "비밀번호를 확인해주세요.");
			return;
		}
		
		try {
			dbconnect();
			
			pstmt = con.prepareStatement(insertMember);
//			pstmt.setString (1, mberId);
//			pstmt.setString (2, password);
//			pstmt.setString (3, mberNm);
//			pstmt.setString (4, email);
//			pstmt.setString (5, moblphon);
			
			pstmt.setString (1, mberDto.getMberID());
			pstmt.setString (2, mberDto.getPassword());
			pstmt.setString (3, mberDto.getMberNm());
			pstmt.setString (4, mberDto.getEmail());
			pstmt.setString (5, mberDto.getMoblphon());
			pstmt.setString (6, "N");
			
			ret = pstmt.executeUpdate();
			con.setAutoCommit(true);
			if(ret > 0)
			{
				JOptionPane.showMessageDialog(contentPanel, "가입완료되었습니다!");
				this.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(contentPanel, "오류발생!");
				return;
			}

//		} catch(BizException e) {				//비지니스예외가 발생하면 아무처리안함
		} catch(Exception e) {e.printStackTrace(); //다른예외가 발생하면 이걸 처리
		} finally {
			try { if(rs != null) rs.close();
			} catch(SQLException e1) {}
			try { if(pstmt != null) pstmt.close();
			} catch(SQLException e1) {}
			try { if(con != null) con.close();
			} catch(SQLException e1) {}
		}
	}
	
	public void idCheck() {					//다 사용가능하다고 나옴. 수정필. 중복체크버튼으로 처리불가능?
		
		String idCheck = "SELECT * FROM MBER WHERE MBER_ID = ?";
		mberDto.setMberID(tfId.getText());
		int cnt = 0;
		
		try {
			dbconnect();
			
			pstmt = con.prepareStatement(idCheck);
			pstmt.setString(1, mberDto.getMberID());
			
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				cnt++;
			}
			
			if(cnt > 0)
			{
				JOptionPane.showMessageDialog(contentPanel, "아이디가 존재합니다.");
				return;
			}
			else
			{
				JOptionPane.showMessageDialog(contentPanel, "사용가능합니다.");
				
			}
			
		} catch(Exception e) {e.printStackTrace(); //다른예외가 발생하면 이걸 처리
		} finally {
			try { if(rs != null) rs.close();
			} catch(SQLException e1) {}
			try { if(pstmt != null) pstmt.close();
			} catch(SQLException e1) {}
			try { if(con != null) con.close();
			} catch(SQLException e1) {}
		}
	}
}
