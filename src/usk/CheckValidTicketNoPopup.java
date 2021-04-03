package usk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import lib.BizException;
import lib.DBController;
import util.Common;

public class CheckValidTicketNoPopup extends JDialog implements ActionListener, FocusListener {
	
	
	
	private static final long serialVersionUID = 1L;
	private JPanel panelContents;
	
	private final String EMPTY_TEXT 	= "";
	private final String NO_TEXT 		= "티켓번호를 입력하세요!";
	private final String INVALID_NO 	= "유효한 티켓번호가 아닙니다!";
	
	
	private JTextField tfInput;			// 티켓 입력부분
	private JButton btnRegist;			// 티켓 확인 버튼

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			CheckValidTicketNoPopup dialog = new CheckValidTicketNoPopup();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public CheckValidTicketNoPopup() {
		
		super((Frame)null, "", true);
		initialize();
		
	}
	
	public void initialize() {
		
		
		setBounds(100, 100, 450, 120);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		
		
		panelContents = new JPanel();
		panelContents.setBounds(0, 0, 450, 120);
		panelContents.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelContents, BorderLayout.CENTER);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{78, 245, 20, 0};
		gridBagLayout.rowHeights = new int[]{12, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelContents.setLayout(gridBagLayout);
		{
			JLabel lblNewLabel = new JLabel("티켓번호");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(2, 10, 2, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 1;
			panelContents.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			tfInput = new JTextField();
			GridBagConstraints gbc_tfInput = new GridBagConstraints();
			gbc_tfInput.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfInput.insets = new Insets(2, 0, 2, 0);
			gbc_tfInput.gridx = 1;
			gbc_tfInput.gridy = 1;
			tfInput.setColumns(10);
			panelContents.add(tfInput, gbc_tfInput);
			tfInput.addFocusListener(this);
		}
		
		btnRegist = new JButton("등록");
		GridBagConstraints gbc_btnRegist = new GridBagConstraints();
		gbc_btnRegist.insets = new Insets(2, 5, 2, 10);
		gbc_btnRegist.gridx = 2;
		gbc_btnRegist.gridy = 1;
		panelContents.add(btnRegist, gbc_btnRegist);
		btnRegist.addActionListener(this);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}
	
	private void setMessage(String message, Color color) {
		if (message != null){
			tfInput.setText(message);
			tfInput.setFont(new Font("나눔고딕", Font.BOLD, 16));
			tfInput.setForeground(color);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String ticketNo = tfInput.getText();
		// 등록버튼 하나 밖에 없으므로
		if (tfInput == null || EMPTY_TEXT.equals(ticketNo)) {
//			JOptionPane.showMessageDialog(this, "티켓번호를 입력하세요.");
			tfInput.setText(NO_TEXT);
		}
		
		// 유효한 티켓번호인지 확인
		if (checkValidTicketNo(tfInput.getText()) == false) {
			setMessage(INVALID_NO, Color.RED);
			return;
		}
		// 이전에 작성한 리뷰인지 확인
		if (checkDuplicationTicketNo(tfInput.getText()) == true) {
			setMessage(INVALID_NO, Color.RED);
			return;
			
		}
		
		// 유효한 티켓번호를 넘김
		Common.getHm().put("VALID_TICKET_NO", ticketNo);
		this.dispose();
		
	}
	
	// 유효한 티켓이면 true 반환
	public boolean checkValidTicketNo(String userInputTicketNo) {
		boolean isValid = false;
		String resveNo;
		String issuNo;
		
		if (userInputTicketNo.length() != 12) {
			setMessage(INVALID_NO, Color.RED);
			return isValid;
		}
		
		resveNo = userInputTicketNo.substring(0, 6);
		issuNo = userInputTicketNo.substring(6);
		
		try {
			
			DBController.connect();
			
			String sql
			= "SELECT 	RESVE_NO, ISSU_NO 	"
			+ "FROM 	SETLE 				"
			+ "WHERE 	RESVE_NO = ? 		"
			+ "AND 		ISSU_NO = ? 		";
			
			DBController.setPstmt(sql);
			DBController.getPstmt().setString(1, resveNo);
			DBController.getPstmt().setString(2, issuNo);
			
			ResultSet rs = DBController.getRs();
			
			if (rs.next()) {
				isValid = true;
			}
			
		} catch (BizException e) {
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DBController.close();
			} catch (BizException e) {
				e.printStackTrace();
			}
		}
		
		return isValid;
	}
	
	// 중복되면 true 반환
	public boolean checkDuplicationTicketNo(String userInputTicketNo) {
		boolean isDuplication = false;
		String resveNo;
		String issuNo;
		String attractionNo = (String)Common.getHm().get("ATTRACTION_NO");
		
		if (userInputTicketNo.length() != 12) {
			setMessage(INVALID_NO, Color.RED);
			return isDuplication;
		}
		
		resveNo = userInputTicketNo.substring(0, 6);
		issuNo = userInputTicketNo.substring(6);
		
		try {
			
			DBController.connect();
			
			String sql
			= "SELECT 	RESVE_NO, ISSU_NO 	"
			+ "FROM 	REVIEW 				"
			+ "WHERE 	RESVE_NO = ? 		"
			+ "AND 		ISSU_NO = ? 		"
			+ "AND 		ATTRACTION_NO = ? 	";
			
			DBController.setPstmt(sql);
			DBController.getPstmt().setString(1, resveNo);
			DBController.getPstmt().setString(2, issuNo);
			DBController.getPstmt().setString(3, attractionNo);
			
			ResultSet rs = DBController.getRs();
			
			if (rs.next()) {
				isDuplication = true;
			}
			
		} catch (BizException e) {
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DBController.close();
			} catch (BizException e) {
				e.printStackTrace();
			}
		}
		
		return isDuplication;
	}
	
	

	@Override
	public void focusGained(FocusEvent e) {
		
		if (NO_TEXT.equals(tfInput.getText())){
			setMessage("", Color.BLACK);
		}
		else if (INVALID_NO.equals(tfInput.getText())){
			setMessage("", Color.BLACK);
		}
		
	}

	@Override
	public void focusLost(FocusEvent e) {

		if (tfInput.getText().length() == 0) {
			setMessage(NO_TEXT, Color.RED);
		}
		
	}

}
