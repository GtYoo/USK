package usk;

import java.awt.BorderLayout;
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
import java.util.HashMap;

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
import util.Common;
import java.awt.Color;

public class LoginPopup extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfId;
	private JPasswordField passwordJp;
	
	private JButton btnMembership;
	private JButton okButton;
	private JButton cancelButton;
	
	private LoginDTO loginDto;
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "dev";
	String password = "123456";

	private PreparedStatement pstmt = null;
	private Connection con = null;
	private ResultSet rs = null;
	


	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			LoginPopup dialog = new LoginPopup();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public LoginPopup() {
		
		super((Frame)null , "" , true);
		   //부모자리	 //타이틀  //젤먼저뜬창을 핸들링해야 다음창으로가능 //모달  
		HashMap<String , Object>hm = Common.getHm();
		this.loginDto = (LoginDTO)hm.get("LoginDTO");
	
//		loginDto.setLoginID("");	//초기값
//		loginDto.setLevel(0);
		
		
		setBounds(100, 100, 553, 300);
		setSize(454, 242);				//중앙에 창띄우기
		setLocationRelativeTo(null);	//중앙에 창띄우기
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblId = new JLabel("아이디");
			lblId.setForeground(Color.BLACK);
			lblId.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			lblId.setBounds(94, 50, 92, 32);
			contentPanel.add(lblId);
		}
		{
			JLabel lblPassword = new JLabel("패스워드");
			lblPassword.setForeground(Color.BLACK);
			lblPassword.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			lblPassword.setBounds(94, 94, 92, 32);
			contentPanel.add(lblPassword);
		}
		
		tfId = new JTextField();
		tfId.setBounds(214, 52, 137, 26);
		contentPanel.add(tfId);
		tfId.setColumns(10);
		
		passwordJp = new JPasswordField();
		passwordJp.setBounds(214, 96, 137, 26);
		contentPanel.add(passwordJp);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnMembership = new JButton("회원가입");
				btnMembership.setBackground(Color.WHITE);
				buttonPane.add(btnMembership);
				btnMembership.addActionListener(this);
			}
			{
				okButton = new JButton("입장");
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
		
		String idPwCheck = "SELECT * FROM MBER WHERE MBER_ID = ? AND PASSWORD = ?";
//		String [] dbIdPw = new String[1]; //1인지 2인지 확인요망
		String sInPass = new String(passwordJp.getPassword());
//		String[] atCheck = new String[] {"Y","N"};
//		ArrayList<String> dbIdPw = new ArrayList<String>();
		MberDTO mberDto = new MberDTO();
		if(e.getSource() == okButton)
		{
			try {
				
				dbconnect();
								
				pstmt = con.prepareStatement(idPwCheck);
				pstmt.setString(1, tfId.getText());
				pstmt.setString(2, sInPass);
				
				rs = pstmt.executeQuery();
				
				while(rs.next())
				{
					mberDto.setMberID(rs.getString("MBER_ID"));
					mberDto.setPassword(rs.getString("PASSWORD"));
					mberDto.setMberSecsnAt(rs.getString("MBER_SECSN_AT"));

				}
				
				

			
//				if("lts".equals(tfId.getText()) && "1234".equals(password.getText()))
				if("yoo".equals(tfId.getText()) && "1234".equals(sInPass))
				{
					loginDto.setLoginID(tfId.getText());
					loginDto.setLevel(2);
					this.dispose(); //로그인창만 닫음
				}
//				else if(dbIdPw[0].equals(tfId.getText()) && dbIdPw[1].equals(sInPass))
//				for(int i = 0; i < dbIdPw.size(); i++)
//				{
//					if(dbIdPw.get(i).equals(tfId.getText()))
//					{
//						loginDto.setLoginID(tfId.getText());
//						
//					}
//				}
				else if(tfId.getText().equals(mberDto.getMberID()) && sInPass.equals(mberDto.getPassword()) 
						&& "N".equals(mberDto.getMberSecsnAt()))
				{
					loginDto.setLoginID(tfId.getText());
					loginDto.setLevel(1);
					this.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(contentPanel, "다시입력하세요.");
					return;
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
		else if(e.getSource() == btnMembership)
		{
			this.dispose();
			new MberJoinPopup();	//회원가입팝업
		}
		
	}
	
}
