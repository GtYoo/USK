package usk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
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

import dto.LoginDTO;
import dto.MberDTO;
import lib.BizException;
import util.Common;



public class MberModify extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JLabel lblId;
	private JLabel lblPw;
	private JLabel lblPwP;
	private JLabel lblNm;
	private JLabel lblEmail;
	private JLabel lblTel;
	private JTextField tfId;
	private JTextField tfNm;
	private JTextField tfEmail;
	private JTextField tfTel;
	
	private JButton okButton;
	private JButton cancelButton;
	
	private LoginDTO loginDto;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "dev";
	String password = "123456";

	private PreparedStatement pstmt = null;
	private PreparedStatement pstmt2 = null;
	private Connection con = null;
	private ResultSet rs = null;
	private JButton btnWd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MberModify dialog = new MberModify();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MberModify() {
		
		super((Frame)null, "", true);
		
		loginDto = (LoginDTO)Common.getHm().get("LoginDTO");
		String mberInfo = "SELECT * FROM MBER WHERE MBER_ID = ?";
		String loginId = loginDto.getLoginID();
		MberDTO mberDto = new MberDTO();
		
		try {
			
			dbconnect();
			
			pstmt = con.prepareStatement(mberInfo);
			pstmt.setString(1, loginId);
			
			rs = pstmt.executeQuery();
			
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
		
		setBounds(100, 100, 503, 538);
		setSize(503, 538);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblNewLabel = new JLabel("회원정보수정");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel.setBounds(53, 25, 131, 46);
		contentPanel.add(lblNewLabel);
		
		lblId = new JLabel("아이디");
		lblId.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblId.setBounds(121, 126, 66, 21);
		contentPanel.add(lblId);
		
		lblPw = new JLabel("비밀번호");
		lblPw.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblPw.setBounds(121, 169, 80, 21);
		contentPanel.add(lblPw);
		
		lblPwP = new JLabel("비밀번호확인");
		lblPwP.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblPwP.setBounds(121, 212, 108, 21);
		contentPanel.add(lblPwP);
		
		lblNm = new JLabel("성명");
		lblNm.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblNm.setBounds(121, 255, 42, 21);
		contentPanel.add(lblNm);
		
		lblEmail = new JLabel("이메일");
		lblEmail.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblEmail.setBounds(121, 298, 57, 21);
		contentPanel.add(lblEmail);
		
		lblTel = new JLabel("전화번호");
		lblTel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblTel.setBounds(121, 341, 86, 21);
		contentPanel.add(lblTel);
		
		tfId = new JTextField("");
		tfId.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		tfId.setBounds(256, 126, 116, 21);
		contentPanel.add(tfId);
		tfId.setColumns(10);
		tfId.setText(mberDto.getMberID());
		tfId.setEnabled(false);
		
		tfNm = new JTextField();
		tfNm.setColumns(10);
		tfNm.setBounds(256, 256, 116, 21);
		contentPanel.add(tfNm);
		tfNm.setText(mberDto.getMberNm());
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(256, 299, 116, 21);
		contentPanel.add(tfEmail);
		tfEmail.setText(mberDto.getEmail());
		
		tfTel = new JTextField();
		tfTel.setColumns(10);
		tfTel.setBounds(256, 342, 116, 21);
		contentPanel.add(tfTel);
		tfTel.setText(mberDto.getMoblphon());
		
		passwordField = new JPasswordField();
		passwordField.setBounds(256, 170, 116, 21);
		contentPanel.add(passwordField);
		passwordField.setText(mberDto.getPassword());
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(256, 213, 116, 21);
		contentPanel.add(passwordField_1);
		passwordField_1.setText(mberDto.getPassword());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setBackground(Color.WHITE);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(this);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setBackground(Color.WHITE);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(this);
				
				btnWd = new JButton("회원탈퇴");
				btnWd.setBackground(Color.WHITE);
				btnWd.setActionCommand("OK");
				buttonPane.add(btnWd);
				btnWd.addActionListener(this);
			}
		}
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void dbconnect() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String mberSearch = "SELECT * FROM MBER WHERE MBER_ID = ?";
		String mberUpdate = "UPDATE MBER SET PASSWORD = ?, MBER_NM = ?, EMAIL = ?, "
				+ "MOBLPHON = ? WHERE MBER_ID = ?";
		String mberDelete = "UPDATE MBER SET MBER_ID = ?, PASSWORD = ?, MBER_NM = ?, EMAIL = ?, "
				+ "MOBLPHON = ?, MBER_SECSN_AT = ? WHERE MBER_ID = ?";
		String getPw = new String(passwordField.getPassword());
		String getPwp = new String(passwordField_1.getPassword());
		
		int ret;
		
		String id = tfId.getText();
		String pw = getPw;
		String nm = tfNm.getText();
		String email = tfEmail.getText();
		String tel = tfTel.getText();
		
		if(e.getSource() == okButton)
		{
			if(!getPw.equals(getPwp))
			{
				JOptionPane.showMessageDialog(contentPanel, "비밀번호를 확인해주세요.");
				return;
			}
			
			try {
				dbconnect();
				
				pstmt = con.prepareStatement(mberSearch);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if(!rs.next())
				{
					JOptionPane.showMessageDialog(contentPanel, "회원아이디가 없습니다.");
					return;
				}
				
				pstmt2 = con.prepareStatement(mberUpdate);
				pstmt2.setString(1, pw);
				pstmt2.setString(2, nm);
				pstmt2.setString(3, email);
				pstmt2.setString(4, tel);
				pstmt2.setString(5, id);
				
				
				ret = pstmt2.executeUpdate();
				
				if(ret > 0)
				{
					JOptionPane.showMessageDialog(contentPanel, "수정완료되었습니다.");
					this.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(contentPanel, "오류발생");
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
		}
		else if(e.getSource() == cancelButton)
		{
			this.dispose();
		}
		else if(e.getSource() == btnWd)
		{
			int re = JOptionPane.showConfirmDialog(contentPanel, "탈퇴하시겠습니까?", "회원탈퇴", 
								JOptionPane.YES_NO_OPTION);
			if(re == JOptionPane.CLOSED_OPTION)
			{
				
			}
			else if(re == JOptionPane.YES_OPTION)
			{
				try {
					dbconnect();
					
					pstmt = con.prepareStatement(mberSearch);
					pstmt.setString(1, id);
					rs = pstmt.executeQuery();

					if(!rs.next())
					{
						JOptionPane.showMessageDialog(contentPanel, "회원아이디가 없습니다.");
						throw new BizException();
					}
					
					pstmt2 = con.prepareStatement(mberDelete);
					pstmt2.setString(1, id);
					pstmt2.setString(2, pw);
					pstmt2.setString(3, nm);
					pstmt2.setString(4, email);
					pstmt2.setString(5, tel);
					pstmt2.setString(6, "Y");
					pstmt2.setString(7, id);
					
					ret = pstmt2.executeUpdate();
					
					
					
					if(ret > 0)
					{
						JOptionPane.showMessageDialog(contentPanel, "탈퇴되었습니다.");
						this.dispose();
						
					}
					else
					{
						JOptionPane.showMessageDialog(contentPanel, "오류발생");
					}
				
				} catch(BizException e1) {
				} catch(Exception e1) {
					e1.printStackTrace();
				} finally {
					try { if(rs != null) rs.close();
					} catch(SQLException e1) {}
					try { if(pstmt != null) pstmt.close();
					} catch(SQLException e1) {}
					try { if(con != null) con.close();
					} catch(SQLException e1) {}
				}
//				JPanel upCommon = (JPanel)Common.getHm().get("PANELUP_COMMON");
//				JPanel main2Common = (JPanel)Common.getHm().get("PANELMAIN2");
//				JFrame mainFrame = (JFrame) Common.getHm().get("MAIN_FRAME");
//				mainFrame.setVisible(true);
//				mainFrame.getContentPane().add(upCommon);
//				mainFrame.getContentPane().add(main2Common);
//				main2Common.setVisible(false);
//				upCommon.setVisible(true);
				
			}
			else
			{
				this.dispose();
			}

		}
	}
}
