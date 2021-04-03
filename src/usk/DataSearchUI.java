package usk;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import lib.BizException;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DataSearchUI extends JPanel implements ActionListener, MouseListener, 
					ItemListener, AdjustmentListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String[] mber = new String[] {"ID", "성명", "예약번호", "예약일", "발급번호", 
			"결제금액", "결제일", "취소여부", "취소일", "취소완료", "회원번호", "공통코드", "티켓번호", 
			"입장일", "결제여부"};
	private int[] colSize = new int[] {15, 10, 10, 10, 10, 20, 10, 3, 10, 3, 1, 1, 1, 1, 1};
	private int[] colAlm = new int[] {JLabel.LEFT, JLabel.LEFT, JLabel.LEFT, 
			JLabel.LEFT, JLabel.LEFT, JLabel.RIGHT, JLabel.LEFT, JLabel.CENTER, JLabel.LEFT,
			JLabel.CENTER, JLabel.CENTER, JLabel.CENTER, JLabel.CENTER, JLabel.CENTER, JLabel.CENTER};
	private JTable table;
	private DefaultTableModel dtModel;
	
//	private String[] resve = new String[] {"ID", "성명", "예약번호", "예약일", "회원번호", "공통코드", "티켓번호", 
//			"입장일", "결제여부"};
//	private int[] colSizeResve = new int[] {10, 10, 20, 30, 5, 5, 5, 30, 5};
//	private int[] colAlmResve = new int[] {JLabel.LEFT, JLabel.LEFT, JLabel.LEFT, 
//			JLabel.LEFT, JLabel.RIGHT, JLabel.LEFT, JLabel.LEFT, JLabel.LEFT, JLabel.LEFT};
//	private JTable tableResve;
//	private DefaultTableModel dtModelResve;
	
	private JPanel panelData;
	private JButton btnSearch;
	private JButton btnClear;
	private JLabel lblSearchSe;
	private JComboBox<String> cmbSearch;
	private JTextField tfScondition;
	private JScrollPane scrollPane;
	private JButton btnDelete;
	private JButton btnNext;
	private JButton btnUpdate;
	private JLabel lblMberNo;
	private JTextField tfMberNo;
	private JLabel lblResveNo;
	private JTextField tfResveNo;
	private JLabel lblTicketNo;
	private JTextField tfTicketNo;
	private JLabel lblCmmnNo;
	private JTextField tfCmmnNo;
	private JLabel lblResveDt;
	private JTextField tfResveDt;
	private JLabel lblEntDt;
	private JTextField tfEntDt;
	private JLabel lblIssuNo;
	private JTextField tfIssuNo;
	private JLabel lblSetleAt;
	private JTextField tfSetleAt;
	private JLabel lblSetleAmount;
	private JTextField tfSetleAmount;
	private JLabel lblSetleDt;
	private JTextField tfSetleDt;
	private JLabel lblCancelAt;
	private JTextField tfCancelAt;
	private JLabel lblCancelDt;
	private JTextField tfCancelDt;
	private JLabel lblCancelCpAt;
	private JTextField tfCancelCpAt;
	
	private static final int CONDITION = 1;
	private static final int TABLE = 2;
	private static final int FIELD = 4;
	private static final int ALL = 7;
	
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
	
	private static final int NONE = 0;
	private static final int ALLSEARCH = 1;
	private static final int MBER_NAME_SEARCH = 2;
	private static final int RESVE_NO = 3;
//	private static final int ALLRESVE = 4;
	private 			 int iSearch = ALLSEARCH;
	
	private int page = 0;
	private static final int MAXCNT = 10;
	private static final int SOFF = 0;
	private static final int SON = 1;
	private				 int iScroll = SOFF;
	
	String prvScondition = null;
	private JLabel lblMSG;
	private JLabel lblMSGOutput;
	private JLabel lblMberNm;
	private JTextField tfMberNm;
	private JLabel lblId;
	private JTextField tfMberId;
	private JLabel lblTotal;


	/**
	 * Create the panel.
	 */
	public DataSearchUI() {
		setLayout(null);
		
		panelData = new JPanel();
		panelData.setBackground(Color.WHITE);
		panelData.setBounds(0, 0, 1042, 611);
		add(panelData);
		panelData.setLayout(null);
		
		btnSearch = new JButton("조회");
		btnSearch.setBounds(691, 39, 122, 42);
		panelData.add(btnSearch);
		btnSearch.addActionListener(this);
		
		btnClear = new JButton("초기화");
		btnClear.setBounds(850, 39, 122, 42);
		panelData.add(btnClear);
		btnClear.addActionListener(this);
		
		lblSearchSe = new JLabel("* 조회구분");
		lblSearchSe.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblSearchSe.setBounds(74, 39, 109, 42);
		panelData.add(lblSearchSe);
		
		cmbSearch = new JComboBox<String>();
		cmbSearch.setModel(new DefaultComboBoxModel<String>(new String[] {"전체", "회원", "예약번호", "전체예약"}));
		cmbSearch.setBounds(181, 51, 74, 21);
		panelData.add(cmbSearch);
		cmbSearch.addItemListener(this);
		
		tfScondition = new JTextField();
		tfScondition.setColumns(10);
		tfScondition.setBounds(267, 51, 109, 21);
		panelData.add(tfScondition);
		
		btnDelete = new JButton("삭제");
		btnDelete.setBounds(850, 553, 122, 31);
		panelData.add(btnDelete);
		btnDelete.addActionListener(this);
		
		btnUpdate = new JButton("수정");
		btnUpdate.setBounds(850, 513, 122, 31);
		panelData.add(btnUpdate);
		btnUpdate.addActionListener(this);
		
		lblMberNo = new JLabel("회원번호");
		lblMberNo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblMberNo.setBounds(74, 482, 65, 21);
		panelData.add(lblMberNo);
		
		tfMberNo = new JTextField();
		tfMberNo.setBounds(145, 483, 116, 21);
		panelData.add(tfMberNo);
		tfMberNo.setColumns(10);
		
		lblResveNo = new JLabel("예약번호");
		lblResveNo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblResveNo.setBounds(74, 509, 65, 21);
		panelData.add(lblResveNo);
		
		tfResveNo = new JTextField();
		tfResveNo.setColumns(10);
		tfResveNo.setBounds(145, 510, 116, 21);
		panelData.add(tfResveNo);
		
		lblTicketNo = new JLabel("티켓번호");
		lblTicketNo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblTicketNo.setBounds(273, 482, 65, 21);
		panelData.add(lblTicketNo);
		
		tfTicketNo = new JTextField();
		tfTicketNo.setColumns(10);
		tfTicketNo.setBounds(344, 483, 55, 21);
		panelData.add(tfTicketNo);
		
		lblCmmnNo = new JLabel("공통코드");
		lblCmmnNo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblCmmnNo.setBounds(273, 509, 65, 21);
		panelData.add(lblCmmnNo);
		
		tfCmmnNo = new JTextField();
		tfCmmnNo.setColumns(10);
		tfCmmnNo.setBounds(344, 510, 55, 21);
		panelData.add(tfCmmnNo);
		
		lblResveDt = new JLabel("예약일");
		lblResveDt.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblResveDt.setBounds(411, 482, 49, 21);
		panelData.add(lblResveDt);
		
		tfResveDt = new JTextField();
		tfResveDt.setColumns(10);
		tfResveDt.setBounds(463, 483, 97, 21);
		panelData.add(tfResveDt);
		
		lblEntDt = new JLabel("입장일");
		lblEntDt.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblEntDt.setBounds(411, 509, 49, 21);
		panelData.add(lblEntDt);
		
		tfEntDt = new JTextField();
		tfEntDt.setColumns(10);
		tfEntDt.setBounds(463, 510, 97, 21);
		panelData.add(tfEntDt);
		
		lblIssuNo = new JLabel("발급번호");
		lblIssuNo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblIssuNo.setBounds(74, 536, 65, 21);
		panelData.add(lblIssuNo);
		
		tfIssuNo = new JTextField();
		tfIssuNo.setColumns(10);
		tfIssuNo.setBounds(145, 537, 116, 21);
		panelData.add(tfIssuNo);
		
		lblSetleAt = new JLabel("결제여부");
		lblSetleAt.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblSetleAt.setBounds(411, 536, 65, 21);
		panelData.add(lblSetleAt);
		
		tfSetleAt = new JTextField();
		tfSetleAt.setColumns(10);
		tfSetleAt.setBounds(482, 537, 78, 21);
		panelData.add(tfSetleAt);
		
		lblSetleAmount = new JLabel("결제금액");
		lblSetleAmount.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblSetleAmount.setBounds(273, 536, 65, 21);
		panelData.add(lblSetleAmount);
		
		tfSetleAmount = new JTextField();
		tfSetleAmount.setColumns(10);
		tfSetleAmount.setBounds(344, 537, 55, 21);
		panelData.add(tfSetleAmount);
		
		lblSetleDt = new JLabel("결제일시");
		lblSetleDt.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblSetleDt.setBounds(74, 563, 65, 21);
		panelData.add(lblSetleDt);
		
		tfSetleDt = new JTextField();
		tfSetleDt.setColumns(10);
		tfSetleDt.setBounds(145, 564, 116, 21);
		panelData.add(tfSetleDt);
		
		lblCancelAt = new JLabel("취소신청여부");
		lblCancelAt.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblCancelAt.setBounds(273, 563, 90, 21);
		panelData.add(lblCancelAt);
		
		tfCancelAt = new JTextField();
		tfCancelAt.setColumns(10);
		tfCancelAt.setBounds(375, 564, 37, 21);
		panelData.add(tfCancelAt);
		
		lblCancelDt = new JLabel("취소일시");
		lblCancelDt.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblCancelDt.setBounds(584, 563, 65, 21);
		panelData.add(lblCancelDt);
		
		tfCancelDt = new JTextField();
		tfCancelDt.setColumns(10);
		tfCancelDt.setBounds(655, 564, 170, 21);
		panelData.add(tfCancelDt);
		
		lblCancelCpAt = new JLabel("취소완료여부");
		lblCancelCpAt.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblCancelCpAt.setBounds(421, 563, 90, 21);
		panelData.add(lblCancelCpAt);
		
		tfCancelCpAt = new JTextField();
		tfCancelCpAt.setColumns(10);
		tfCancelCpAt.setBounds(523, 564, 37, 21);
		panelData.add(tfCancelCpAt);
		
		lblMSG = new JLabel("MSG");
		lblMSG.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblMSG.setBounds(631, 487, 57, 15);
		panelData.add(lblMSG);
		
		lblMSGOutput = new JLabel("");
		lblMSGOutput.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblMSGOutput.setBounds(705, 487, 97, 15);
		panelData.add(lblMSGOutput);
		
		lblMberNm = new JLabel("성명");
		lblMberNm.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblMberNm.setBounds(584, 536, 37, 21);
		panelData.add(lblMberNm);
		
		tfMberNm = new JTextField();
		tfMberNm.setColumns(10);
		tfMberNm.setBounds(623, 537, 65, 21);
		panelData.add(tfMberNm);
		
		lblId = new JLabel("아이디");
		lblId.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblId.setBounds(699, 536, 49, 21);
		panelData.add(lblId);
		
		tfMberId = new JTextField();
		tfMberId.setColumns(10);
		tfMberId.setBounds(751, 537, 74, 21);
		panelData.add(tfMberId);
		
		lblTotal = new JLabel("");
		lblTotal.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblTotal.setBounds(451, 39, 172, 42);
		panelData.add(lblTotal);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(74, 104, 898, 368);
		panelData.add(scrollPane);
		
		dtModel = new DefaultTableModel(mber, 0);
		table = new JTable();
		table.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		table.setAutoCreateColumnsFromModel(false);
		table.setModel(dtModel);
		for(int i = 0; i < colAlm.length; i++)
		{
			DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(colAlm[i]);
			TableColumn column = new TableColumn(i, colSize[i], renderer, null);
			table.addColumn(column);
		}
		
		table.setFocusable(false);
		scrollPane.setViewportView(table);
		table.addMouseListener(this);
		table.setRowHeight(25);
//		table.setVisible(true);
		
		btnNext = new JButton("NEXT");
		btnNext.setBounds(850, 482, 122, 21);
		panelData.add(btnNext);
		btnNext.addActionListener(this);
		
//		dtModelResve = new DefaultTableModel(resve, 0);
//		tableResve = new JTable();
//		tableResve.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
//		tableResve.setAutoCreateColumnsFromModel(false);
//		tableResve.setModel(dtModelResve);
//		for(int i = 0; i < colAlmResve.length; i++)
//		{
//			DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
//			renderer.setHorizontalAlignment(colAlmResve[i]);
//			TableColumn column = new TableColumn(i, colSizeResve[i], renderer, null);
//			tableResve.addColumn(column);
//		}
//		
//		tableResve.setFocusable(false);
//		scrollPane.setViewportView(tableResve);
//		tableResve.addMouseListener(this);
//		tableResve.setRowHeight(25);
//		tableResve.setVisible(false);

	}
	
	public void dbconnect() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void enableClear() {
		
		tfTicketNo.setEnabled(true);
//		btnInsert.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
	}
	
	public void clear(int iMode) {
		
		if((iMode & CONDITION) > 0)
		{
			tfScondition.setText("");
			lblMSGOutput.setText("");
		}
		if((iMode & TABLE) > 0)
		{
			dtModel.setRowCount(0);
		}
		if((iMode & FIELD) > 0)
		{
			tfMberNo.setText("");
			tfMberNm.setText("");
			tfResveNo.setText("");
			tfResveDt.setText("");
			tfIssuNo.setText("");
			tfSetleAmount.setText("");
			tfSetleDt.setText("");
			tfTicketNo.setText("");
			tfCmmnNo.setText("");
			tfEntDt.setText("");
			tfSetleAt.setText("");
			tfCancelAt.setText("");
			tfCancelDt.setText("");
			tfCancelCpAt.setText("");
			tfMberId.setText("");
			
		}
		
//		scrollPane.removeAll();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		enableClear();
		if(e.getSource() == btnSearch)
		{
			if(tfScondition.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(panelData, "조회조건을 입력하세요.");
				return;
			}
			
			page = 1;
			clear(TABLE | FIELD);
			
			if(iSearch == ALLSEARCH)
			{
//				scrollPane.add(table);
//				table.setVisible(true);
//				tableResve.setVisible(false);
				allSearch();
			}
			else if(iSearch == MBER_NAME_SEARCH)
			{
//				table.setVisible(true);
//				tableResve.setVisible(false);
				mberNameSearch();
			}
			else if(iSearch == RESVE_NO)
			{
//				table.setVisible(true);
//				tableResve.setVisible(false);
				resveNoSearch();
			}
//			else if(iSearch == ALLRESVE)
//			{
//				scrollPane.add(tableResve);
////				table.setVisible(false);
////				tableResve.setVisible(true);
//				resveAllSearch();
//			}
			else
			{
				JOptionPane.showMessageDialog(panelData, "조회조건을 입력하세요.");
				return;
			}
		}
		else if(e.getSource() == btnClear)
		{
			clear(ALL);
			lblTotal.setText("");
		}
		else if(e.getSource() == btnUpdate)
		{
			resveUpdate();
		}
		else if(e.getSource() == btnDelete)
		{
			
		}
		else if(e.getSource() == btnNext)
		{
			page++;
			clear(FIELD);
			
			if(iSearch == ALLSEARCH)
			{
				allSearch();
			}
			else if(iSearch == MBER_NAME_SEARCH)
			{
				mberNameSearch();
			}
			else if(iSearch == RESVE_NO)
			{
				resveNoSearch();
			}
//			else if(iSearch == ALLRESVE)
//			{
//				resveAllSearch();
//			}
		}
		
	}
	
	public void allSearch() {
		
//		table.setVisible(true);
//		tableResve.setVisible(false);
		String noSearch = "SELECT * FROM (SELECT O.* ,  ROWNUM RNUM FROM "
				+ "(SELECT M.MBER_ID, M.MBER_NM, R.RESVE_NO, R.RESVE_DE, S.ISSU_NO, S.SETLE_AMOUNT, "
				+ "S.SETLE_DT, S.CANCL_REQST_AT, S.COMPT_DT, S.CANCDE_COMPT_AT, M.MBER_NO, "
				+ "T.CMMN_CODE, T.TICKET_NO, R.ENTNC_DE, R.SETLE_AT "
				+ "FROM MBER M, RESVE R, SETLE S, TICKET T WHERE 1 = 1 AND M.MBER_NO = R.MBER_NO "
				+ "AND R.RESVE_NO = S.RESVE_NO AND T.TICKET_NO = R.TICKET_NO AND "
				+ "R.RESVE_NO >= ? ORDER BY RESVE_NO )O)"
				+ "WHERE RNUM >= ? AND ROWNUM <= ?";
		String[] adiData = new String[15];
		
		try {
			dbconnect();
			pstmt = con.prepareStatement(noSearch);
			pstmt.setString(1, tfScondition.getText());
			pstmt.setInt(2, (page - 1) * MAXCNT + 1);
			pstmt.setInt(3, MAXCNT + 1);
			
			rs = pstmt.executeQuery();
			
			int i = 0;
			
			while(true)
			{
				if(!rs.next())
				{
					btnNext.setEnabled(false);
					break;
				}
				
				i++;
				
				if(i > MAXCNT)
				{
					btnNext.setEnabled(true);
					break;
				}
				
				for(int col = 1; col <= 15; col++)
				{
					if(rs.getObject(col) == null)
					{
						adiData[col - 1] = "";
					}
					else
					{
						adiData[col - 1] = rs.getObject(col).toString();
					}
				}
				
				dtModel.addRow(adiData);
				iScroll = SON;
			}
			lblTotal.setText("총 " + String.valueOf(i) + " 건 입니다.");
			
			
			if(i == 0)
			{
				lblMSGOutput.setForeground(Color.RED);
				lblMSGOutput.setText("오류");
			}
			else
			{
				lblMSGOutput.setForeground(Color.BLACK);
				lblMSGOutput.setText("정상 조회");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close();
			} catch(SQLException e1) {}
			try { if(pstmt != null) pstmt.close();
			} catch(SQLException e1) {}
			try { if(con != null) con.close();
			} catch(SQLException e1) {}
		}
	}
	
	public void mberNameSearch() {
		
//		table.setVisible(true);
//		tableResve.setVisible(false);
		String noSearch = "SELECT * FROM (SELECT O.* ,  ROWNUM RNUM FROM "
				+ "(SELECT M.MBER_ID, M.MBER_NM, R.RESVE_NO, R.RESVE_DE, S.ISSU_NO, S.SETLE_AMOUNT, "
				+ "S.SETLE_DT, S.CANCL_REQST_AT, S.COMPT_DT, S.CANCDE_COMPT_AT, M.MBER_NO, "
				+ "T.CMMN_CODE, T.TICKET_NO, R.ENTNC_DE, R.SETLE_AT "
				+ "FROM MBER M, RESVE R, SETLE S, TICKET T WHERE 1 = 1 AND M.MBER_NO = R.MBER_NO "
				+ "AND R.RESVE_NO = S.RESVE_NO AND T.TICKET_NO = R.TICKET_NO AND "
				+ "M.MBER_NM = ? ORDER BY RESVE_NO )O)"
				+ "WHERE RNUM >= ? AND ROWNUM <= ?";
		String[] adiData = new String[15];
		
		try {
			dbconnect();
			pstmt = con.prepareStatement(noSearch);
			pstmt.setString(1, tfScondition.getText());
			pstmt.setInt(2, (page - 1) * MAXCNT + 1);
			pstmt.setInt(3, MAXCNT + 1);
			
			rs = pstmt.executeQuery();
			
			int i = 0;
			
			while(true)
			{
				if(!rs.next())
				{
					btnNext.setEnabled(false);
					break;
				}
				
				i++;
				
				if(i > MAXCNT)
				{
					btnNext.setEnabled(true);
					break;
				}
				
				for(int col = 1; col <= 15; col++)
				{
					if(rs.getObject(col) == null)
					{
						adiData[col - 1] = "";
					}
					else
					{
						adiData[col - 1] = rs.getObject(col).toString();
					}
				}
				
				dtModel.addRow(adiData);
				iScroll = SON;
			}
			lblTotal.setText("총 " + String.valueOf(i) + " 건 입니다.");
			
			
			if(i == 0)
			{
				lblMSGOutput.setForeground(Color.RED);
				lblMSGOutput.setText("오류");
			}
			else
			{
				lblMSGOutput.setForeground(Color.BLACK);
				lblMSGOutput.setText("정상 조회");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close();
			} catch(SQLException e1) {}
			try { if(pstmt != null) pstmt.close();
			} catch(SQLException e1) {}
			try { if(con != null) con.close();
			} catch(SQLException e1) {}
		}
	}
	
	public void resveNoSearch() {
		
//		table.setVisible(true);
//		tableResve.setVisible(false);
		String noSearch = "SELECT * FROM (SELECT O.* ,  ROWNUM RNUM FROM "
				+ "(SELECT M.MBER_ID, M.MBER_NM, R.RESVE_NO, R.RESVE_DE, S.ISSU_NO, S.SETLE_AMOUNT, "
				+ "S.SETLE_DT, S.CANCL_REQST_AT, S.COMPT_DT, S.CANCDE_COMPT_AT, M.MBER_NO, "
				+ "T.CMMN_CODE, T.TICKET_NO, R.ENTNC_DE, R.SETLE_AT "
				+ "FROM MBER M, RESVE R, SETLE S, TICKET T WHERE 1 = 1 AND M.MBER_NO = R.MBER_NO "
				+ "AND R.RESVE_NO = S.RESVE_NO AND T.TICKET_NO = R.TICKET_NO AND "
				+ "R.RESVE_NO = ? ORDER BY RESVE_NO )O)"
				+ "WHERE RNUM >= ? AND ROWNUM <= ?";
		String[] adiData = new String[15];
		
		try {
			dbconnect();
			pstmt = con.prepareStatement(noSearch);
			pstmt.setString(1, tfScondition.getText());
			pstmt.setInt(2, (page - 1) * MAXCNT + 1);
			pstmt.setInt(3, MAXCNT + 1);
			
			rs = pstmt.executeQuery();
			
			int i = 0;
			
			while(true)
			{
				if(!rs.next())
				{
					btnNext.setEnabled(false);
					break;
				}
				
				i++;
				
				if(i > MAXCNT)
				{
					btnNext.setEnabled(true);
					break;
				}
				
				for(int col = 1; col <= 15; col++)
				{
					if(rs.getObject(col) == null)
					{
						adiData[col - 1] = "";
					}
					else
					{
						adiData[col - 1] = rs.getObject(col).toString();
					}
				}
				
				dtModel.addRow(adiData);
				iScroll = SON;
			}
			lblTotal.setText("총 " + String.valueOf(i) + " 건 입니다.");
			
			
			if(i == 0)
			{
				lblMSGOutput.setForeground(Color.RED);
				lblMSGOutput.setText("오류");
			}
			else
			{
				lblMSGOutput.setForeground(Color.BLACK);
				lblMSGOutput.setText("정상 조회");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close();
			} catch(SQLException e1) {}
			try { if(pstmt != null) pstmt.close();
			} catch(SQLException e1) {}
			try { if(con != null) con.close();
			} catch(SQLException e1) {}
		}
	}
	
//	public void resveAllSearch() {
//		
//		table.setVisible(false);
//		tableResve.setVisible(true);
//		String noSearch = "SELECT * FROM (SELECT O.* ,  ROWNUM RNUM FROM "
//				+ "(SELECT M.MBER_ID, M.MBER_NM, R.RESVE_NO, R.RESVE_DE, M.MBER_NO, "
//				+ "T.CMMN_CODE, T.TICKET_NO, R.ENTNC_DE, R.SETLE_AT "
//				+ "FROM MBER M, RESVE R, TICKET T WHERE 1 = 1 AND M.MBER_NO = R.MBER_NO "
//				+ "AND T.TICKET_NO = R.TICKET_NO AND "
//				+ "R.RESVE_NO >= ? ORDER BY RESVE_NO )O)"
//				+ "WHERE RNUM >= ? AND ROWNUM <= ?";
//		String[] adiData = new String[9];
//		
//		try {
//			dbconnect();
//			pstmt = con.prepareStatement(noSearch);
//			pstmt.setString(1, tfScondition.getText());
//			pstmt.setInt(2, (page - 1) * MAXCNT + 1);
//			pstmt.setInt(3, MAXCNT + 1);
//			
//			rs = pstmt.executeQuery();
//			
//			int i = 0;
//			
//			while(true)
//			{
//				if(!rs.next())
//				{
//					btnNext.setEnabled(false);
//					break;
//				}
//				
//				i++;
//				
//				if(i > MAXCNT)
//				{
//					btnNext.setEnabled(true);
//					break;
//				}
//				
//				for(int col = 1; col <= 9; col++)
//				{
//					if(rs.getObject(col) == null)
//					{
//						adiData[col - 1] = "";
//					}
//					else
//					{
//						adiData[col - 1] = rs.getObject(col).toString();
//					}
//				}
//				
//				dtModelResve.addRow(adiData);
//				iScroll = SON;
//			}
//			lblTotal.setText("총 " + String.valueOf(i) + " 건 입니다.");
//			
//			
//			if(i == 0)
//			{
//				lblMSGOutput.setForeground(Color.RED);
//				lblMSGOutput.setText("오류");
//			}
//			else
//			{
//				lblMSGOutput.setForeground(Color.BLACK);
//				lblMSGOutput.setText("정상 조회");
//			}
//			
//		} catch(Exception e) {
//			e.printStackTrace();
//		} finally {
//			try { if(rs != null) rs.close();
//			} catch(SQLException e1) {}
//			try { if(pstmt != null) pstmt.close();
//			} catch(SQLException e1) {}
//			try { if(con != null) con.close();
//			} catch(SQLException e1) {}
//		}
//	}
	
	public void resveUpdate() {
		
//		table.setVisible(true);
//		tableResve.setVisible(false);
		String noSearch = "SELECT * FROM (SELECT O.* ,  ROWNUM RNUM FROM "
				+ "(SELECT M.MBER_ID, M.MBER_NM, R.RESVE_NO, R.RESVE_DE, S.ISSU_NO, S.SETLE_AMOUNT, "
				+ "S.SETLE_DT, S.CANCL_REQST_AT, S.COMPT_DT, S.CANCDE_COMPT_AT, M.MBER_NO, "
				+ "T.CMMN_CODE, T.TICKET_NO, R.ENTNC_DE, R.SETLE_AT "
				+ "FROM MBER M, RESVE R, SETLE S, TICKET T WHERE 1 = 1 AND M.MBER_NO = R.MBER_NO "
				+ "AND R.RESVE_NO = S.RESVE_NO AND T.TICKET_NO = R.TICKET_NO AND "
				+ "R.RESVE_NO = ? ORDER BY RESVE_NO )O)"
				+ "WHERE RNUM >= ? AND ROWNUM <= ?";
		String reUpdate_a = "UPDATE MBER SET MBER_ID = ?, MBER_NM = ? "
				+ "WHERE MBER_NO = ?";
		String reUpdate_b = "UPDATE TICKET SET CMMN_CODE = ?, TICKET_NO = ? "
				+ "WHERE TICKET_NO = ?";
		String reUpdate_c = "UPDATE RESVE SET RESVE_DE = ?, ENTNC_DE = ?, SETLE_AT = ? "
				+ "WHERE RESVE_NO = ?";
		String reUpdate_d = "UPDATE SETLE SET SETLE_AMOUNT = ?, CANCL_REQST_AT = ?, "
				+ "CANCDE_COMPT_AT = ? "
				+ "WHERE ISSU_NO = ?";
		String comptDt = "UPDATE SETLE SET COMPT_DT = SYSDATE WHERE ISSU_NO = ?";
		
		int ret_a = 0;
		int ret_b = 0;
		int ret_c = 0;
		int ret_d = 0;
		int ret_e = 0;
//		int ret = ret_a + ret_b + ret_c + ret_d + ret_e;
//		int ret;
		
		String mberId = tfMberId.getText();
		String mberNm = tfMberNm.getText();
		String resveNo = tfResveNo.getText();
		String resveDt = tfResveDt.getText();
		String issuNo = tfIssuNo.getText();
		String setleAmount = tfSetleAmount.getText();
//		String setleDt = tfSetleDt.getText();
		String cancelAt = tfCancelAt.getText();
//		String cancelDt = tfCancelDt.getText();
		String cancelCpAt = tfCancelCpAt.getText();
		String mberNo = tfMberNo.getText();
		String cmmnNo = tfCmmnNo.getText();
		String ticketNo = tfTicketNo.getText();
		String entDt = tfEntDt.getText();
		String setleAt = tfSetleAt.getText();
		
//		String timeA = setleDt;
//		String timeB = cancelDt;
		
//		java.sql.Date ta = java.sql.Date.valueOf(timeA);
//		java.sql.Date tb = java.sql.Date.valueOf(timeB);

//		SimpleDateFormat date_a = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar dateFormat = Calendar.getInstance();
//		String dateFm = date_a.format(dateFormat).toString();
		

//		SimpleDateFormat date_b = new SimpleDateFormat("yyyyMMdd hhmmss");
	
//		DateTimeFormatter.ofPattern("yyyyMMddhhmmss").format(LocalDateTime.now());
		
		if(resveNo.isEmpty())
		{
			JOptionPane.showMessageDialog(panelData, "예약번호를 입력하세요.");
			lblMSGOutput.setText("");
			return;
		}
		
		try {
			dbconnect();
			
			pstmt = con.prepareStatement(noSearch);
			pstmt.setString(1, resveNo);
			pstmt.setInt(2, (page - 1) * MAXCNT + 1);
			pstmt.setInt(3, MAXCNT + 1);
			rs = pstmt.executeQuery();
			if(!rs.next())
			{
				JOptionPane.showMessageDialog(panelData, "등록번호가 없습니다.");
				lblMSGOutput.setForeground(Color.RED);
				lblMSGOutput.setText("번호가 없습니다.");
				throw new BizException();
			}
			
			pstmt2 = con.prepareStatement(reUpdate_a);
			pstmt2.setString(1, String.valueOf(mberId));
			pstmt2.setString(2, String.valueOf(mberNm));
			pstmt2.setString(3, String.valueOf(mberNo));
			
			ret_a = pstmt2.executeUpdate();
			
			pstmt3 = con.prepareStatement(reUpdate_b);
			pstmt3.setString(1, String.valueOf(cmmnNo));
			pstmt3.setString(2, String.valueOf(ticketNo));
			pstmt3.setString(3, String.valueOf(ticketNo));
			
			ret_b = pstmt3.executeUpdate();
			
			pstmt4 = con.prepareStatement(reUpdate_c);
			pstmt4.setString(1, String.valueOf(resveDt));
			pstmt4.setString(2, String.valueOf(entDt));
			pstmt4.setString(3, String.valueOf(setleAt));
			pstmt4.setString(4, String.valueOf(resveNo));
			
			ret_c = pstmt4.executeUpdate();
			
			pstmt5 = con.prepareStatement(reUpdate_d);
			pstmt5.setInt(1, Integer.parseInt(setleAmount));
			pstmt5.setString(2, String.valueOf(cancelAt));
			pstmt5.setString(3, String.valueOf(cancelCpAt));
			pstmt5.setString(4, String.valueOf(issuNo));
			
			ret_d = pstmt5.executeUpdate();
			
			if("Y".equals(cancelCpAt))
			{
				pstmt6 = con.prepareStatement(comptDt);
				pstmt6.setString(1, issuNo);
				
				ret_e = pstmt6.executeUpdate();
			}

			if(ret_a > 0 || ret_b > 0 || ret_c > 0 || ret_d > 0 || ret_e > 0)
			{
				JOptionPane.showMessageDialog(panelData, "정상수정 되었습니다.");
				lblMSGOutput.setForeground(Color.BLACK);
				lblMSGOutput.setText("정상수정 되었습니다.");
			}
			else
			{
				JOptionPane.showMessageDialog(panelData, "오류발생!");
				lblMSGOutput.setForeground(Color.RED);
				lblMSGOutput.setText("오류발생!");
			}
			
		} catch(BizException e) {			
		} catch(Exception e) {e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close();
			} catch(SQLException e1) {}
			try { if(pstmt != null) pstmt.close();
			} catch(SQLException e1) {}
			try { if(pstmt2 != null) pstmt2.close();
			} catch(SQLException e1) {}
			try { if(pstmt3 != null) pstmt3.close();
			} catch(SQLException e1) {}
			try { if(pstmt4 != null) pstmt4.close();
			} catch(SQLException e1) {}
			try { if(pstmt5 != null) pstmt5.close();
			} catch(SQLException e1) {}
			try { if(con != null) con.close();
			} catch(SQLException e1) {}
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		
		int row;
		
		if(e.getSource() == table)
		{
			JTable jtable = (JTable)e.getSource();
			row = jtable.getSelectedRow();
			
			lblMSGOutput.setText("");
			
			if(row > -1)
			{
				tfMberId.setText((String)table.getValueAt(row, 0));
				tfMberId.setEnabled(false);
				tfMberNm.setText((String)table.getValueAt(row, 1));
				tfResveNo.setText((String)table.getValueAt(row, 2));
				tfResveNo.setEnabled(false);
				tfResveDt.setText((String)table.getValueAt(row, 3));
				tfResveDt.setEnabled(false);
				tfIssuNo.setText((String)table.getValueAt(row, 4));
				tfIssuNo.setEnabled(false);
				tfSetleAmount.setText((String)table.getValueAt(row, 5));
				tfSetleDt.setText((String)table.getValueAt(row, 6));
				tfCancelAt.setText((String)table.getValueAt(row, 7));
				tfCancelDt.setText((String)table.getValueAt(row, 8));
				tfCancelCpAt.setText((String)table.getValueAt(row, 9));
				tfMberNo.setText((String)table.getValueAt(row, 10));
				tfMberNo.setEnabled(false);
				tfCmmnNo.setText((String)table.getValueAt(row, 11));
				tfTicketNo.setText((String)table.getValueAt(row, 12));
				tfEntDt.setText((String)table.getValueAt(row, 13));
				tfSetleAt.setText((String)table.getValueAt(row, 14));
				
//				btnInsert.setEnabled(false);
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
			}
		}
//		else if(e.getSource() == tableResve)
//		{
//			JTable jtable = (JTable)e.getSource();
//			row = jtable.getSelectedRow();
//			
//			lblMSGOutput.setText("");
//			
//			if(row > -1)
//			{
//				tfMberId.setText((String)tableResve.getValueAt(row, 0));
//				tfMberId.setEnabled(false);
//				tfMberNm.setText((String)tableResve.getValueAt(row, 1));
//				tfResveNo.setText((String)tableResve.getValueAt(row, 2));
//				tfResveNo.setEnabled(false);
//				tfResveDt.setText((String)tableResve.getValueAt(row, 3));
//				tfResveDt.setEnabled(false);
//				tfMberNo.setText((String)tableResve.getValueAt(row, 4));
//				tfMberNo.setEnabled(false);
//				tfCmmnNo.setText((String)tableResve.getValueAt(row, 5));
//				tfTicketNo.setText((String)tableResve.getValueAt(row, 6));
//				tfEntDt.setText((String)tableResve.getValueAt(row, 7));
//				tfSetleAt.setText((String)tableResve.getValueAt(row, 8));
//				
////				btnInsert.setEnabled(false);
//				btnUpdate.setEnabled(true);
//				btnDelete.setEnabled(true);
//			}
//		}
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		
		if(e.getSource() == cmbSearch)
		{
			if(e.getStateChange() == ItemEvent.SELECTED)
			{
				btnNext.setEnabled(false);
				
				if("전체".equals(e.getItem().toString()))
				{
//					table.setVisible(true);
//					tableResve.setVisible(false);
					iSearch = ALLSEARCH;
				}
				else if("회원".equals(e.getItem().toString()))
				{
//					table.setVisible(true);
//					tableResve.setVisible(false);
					iSearch = MBER_NAME_SEARCH;
				}
				else if("예약번호".equals(e.getItem().toString()))
				{
//					table.setVisible(true);
//					tableResve.setVisible(false);
					iSearch = RESVE_NO;
				}
//				else if("전체예약".equals(e.getItem().toString()))
//				{
//					table.setVisible(false);
//					tableResve.setVisible(true);
//					iSearch = ALLRESVE;
//				}
				else
				{
					iSearch = NONE;
				}
			}
		}
		
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		
		if(iScroll == SON)
		{
			iScroll = SOFF;
			JScrollBar scrb = (JScrollBar)e.getSource();
			scrb.setValue(scrb.getMaximum());
		}
		
	}
}
