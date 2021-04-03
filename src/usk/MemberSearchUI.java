package usk;

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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import lib.BizException;

public class MemberSearchUI extends JPanel implements ActionListener , MouseListener , 
					ItemListener , AdjustmentListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelMember;
	private JButton btnSearch;
	private JButton btnClear;
	private JLabel lblSe;
	private JComboBox<String> cmbSelect;
	private JTextField tfScondition;
	private JScrollPane scrollPane;
	private JLabel lblCode;
	private JTextField tfCode;
	private JLabel lblId;
	private JTextField tfId;
	private JLabel lblName;
	private JTextField tfName;
	private JLabel lblEmail;
	private JTextField tfEmail;
	private JLabel lblTel;
	private JTextField tfTel;
	private JLabel lblMSG;
	private JLabel lblMSGIn;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;
	
	private String[] member = new String[] {"코드", "아이디", "성명", "이메일", "전화번호", "탈퇴여부"};
	private int[] colSize = new int[] {10, 30, 20, 60, 60, 10};
	private int[] colAlm = new int[] {JLabel.RIGHT, JLabel.LEFT, JLabel.LEFT, 
			JLabel.LEFT, JLabel.LEFT, JLabel.CENTER};
	private JTable table;
	private DefaultTableModel dtModel;
	
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
	private Connection con = null;
	private ResultSet rs = null;
	
	private static final int NONE = 0;
	private static final int CODE = 1;
	private static final int ID = 2;
	private static final int NAME = 3;
	private 			 int iSearch = CODE;
	
	private int page = 0;
	private static final int MAXCNT = 10;
	private static final int SOFF = 0;
	private static final int SON = 1;
	private				 int iScroll = SOFF;
	
	String prvScondition = null;
	private JButton btnNext;
	private JLabel lblMberAt;
	private JTextField tfMberAt;

	/**
	 * Create the panel.
	 */
	public MemberSearchUI() {
		setLayout(null);
		
		panelMember = new JPanel();
		panelMember.setBackground(Color.WHITE);
		panelMember.setBounds(0, 0, 1042, 611);
		add(panelMember);
		panelMember.setLayout(null);
		
		btnSearch = new JButton("조회");
		btnSearch.setBounds(691, 39, 122, 42);
		panelMember.add(btnSearch);
		btnSearch.addActionListener(this);
		
		btnClear = new JButton("초기화");
		btnClear.setBounds(850, 39, 122, 42);
		panelMember.add(btnClear);
		btnClear.addActionListener(this);
		
		lblSe = new JLabel("* 조회구분");
		lblSe.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblSe.setBounds(74, 39, 109, 42);
		panelMember.add(lblSe);
		
		cmbSelect = new JComboBox<String>();
		cmbSelect.setModel(new DefaultComboBoxModel<String>(new String[] {"코드", "아이디", "성명"}));
		cmbSelect.setBounds(181, 51, 74, 21);
		panelMember.add(cmbSelect);
		cmbSelect.addItemListener(this);
		
		tfScondition = new JTextField();
		tfScondition.setBounds(267, 51, 109, 21);
		panelMember.add(tfScondition);
		tfScondition.setColumns(10);
		
		lblCode = new JLabel("* 코드");
		lblCode.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblCode.setBounds(89, 494, 55, 32);
		panelMember.add(lblCode);
		
		tfCode = new JTextField();
		tfCode.setBounds(145, 501, 55, 21);
		panelMember.add(tfCode);
		tfCode.setColumns(10);
		
		lblId = new JLabel("* 아이디");
		lblId.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblId.setBounds(225, 494, 62, 32);
		panelMember.add(lblId);
		
		tfId = new JTextField();
		tfId.setColumns(10);
		tfId.setBounds(297, 501, 79, 21);
		panelMember.add(tfId);
		
		lblName = new JLabel("성명");
		lblName.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblName.setBounds(399, 494, 41, 32);
		panelMember.add(lblName);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(445, 501, 62, 21);
		panelMember.add(tfName);
		
		lblEmail = new JLabel("이메일");
		lblEmail.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblEmail.setBounds(529, 494, 55, 32);
		panelMember.add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(590, 501, 135, 21);
		panelMember.add(tfEmail);
		
		lblTel = new JLabel("전화번호");
		lblTel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblTel.setBounds(750, 494, 62, 32);
		panelMember.add(lblTel);
		
		tfTel = new JTextField();
		tfTel.setColumns(10);
		tfTel.setBounds(825, 501, 135, 21);
		panelMember.add(tfTel);
		
		lblMberAt = new JLabel("탈퇴여부");
		lblMberAt.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblMberAt.setBounds(391, 532, 62, 32);
		panelMember.add(lblMberAt);
		
		tfMberAt = new JTextField();
		tfMberAt.setColumns(10);
		tfMberAt.setBounds(466, 539, 41, 21);
		panelMember.add(tfMberAt);
		
		lblMSG = new JLabel("MSG");
		lblMSG.setBounds(87, 548, 41, 32);
		panelMember.add(lblMSG);
		
		lblMSGIn = new JLabel("MSG");
		lblMSGIn.setBounds(128, 548, 237, 32);
		panelMember.add(lblMSGIn);
		
		btnInsert = new JButton("등록");
		btnInsert.setBounds(690, 554, 122, 26);
		panelMember.add(btnInsert);
		btnInsert.addActionListener(this);
		
		btnUpdate = new JButton("수정");
		btnUpdate.setBounds(849, 554, 122, 26);
		panelMember.add(btnUpdate);
		btnUpdate.addActionListener(this);
		
		btnDelete = new JButton("삭제");
		btnDelete.setBounds(528, 554, 122, 26);
		panelMember.add(btnDelete);
		btnDelete.addActionListener(this);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(74, 104, 898, 368);
		panelMember.add(scrollPane);
		

		
		dtModel = new DefaultTableModel(member, 0);
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
		
		btnNext = new JButton("NEXT");
		btnNext.setBounds(860, 470, 109, 21);
		panelMember.add(btnNext);
		btnNext.addActionListener(this);

	}
	
	//* DB연결  *//
	public void dbconnect() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//* 선택활성화  *//
	public void enableClear() {
		tfCode.setEnabled(true);
		tfId.setEnabled(true);
		btnInsert.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
	}
	
	//* 테이블 정리  *//
	public void clear(int iMode) {
		
		if((iMode & CONDITION) > 0)
		{
			tfScondition.setText("");
			lblMSGIn.setText("");
		}
		if((iMode & TABLE) > 0)
		{
			dtModel.setRowCount(0);
		}
		if((iMode & FIELD) > 0)
		{
			tfCode.setText("");
			tfId.setText("");
			tfName.setText("");
			tfEmail.setText("");
			tfTel.setText("");
			tfMberAt.setText("");
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		enableClear();
		if(e.getSource() == btnClear)	//초기화버튼
		{
			clear(ALL);
		}
		else if(e.getSource() == btnSearch)		//조회버튼
		{
			if(tfScondition.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(panelMember, "조회조건을 입력하세요.");
				return;
			}
			
			page = 1;
			clear(TABLE | FIELD);
		
			if(iSearch == CODE)			//회원번호로 조회
			{
				codes();
			}
			else if(iSearch == ID)		//회원아이디로 조회
			{
				id();
			}
			else if(iSearch == NAME)	//회원이름으로 조회
			{
				name();
			}
			else
			{
				JOptionPane.showMessageDialog(panelMember, "조회조건을 입력하세요.");
				return;
			}
		}
		else if(e.getSource() == btnDelete)		//삭제버튼
		{
			delete();
		}
		else if(e.getSource() == btnUpdate)		//수정버튼
		{
			update();
		}
		else if(e.getSource() == btnNext)		//다음버튼
		{
			page++;
			clear(FIELD);
			
			if(iSearch == CODE)
			{
				codes();
			}
			else if(iSearch == ID)
			{
				id();
			}
			else if(iSearch == NAME)
			{
				name();
			}
		}
	}
	
	//* 회원번호로 검색  *//
	public void codes() {
		
		String codeSearch = "SELECT * FROM (SELECT O.* ,  ROWNUM RNUM FROM "
				+ "(SELECT LPAD(MBER_NO,6,'0') as MBER_NO, MBER_ID, MBER_NM, EMAIL, MOBLPHON, MBER_SECSN_AT "
				+ "FROM MBER WHERE 1 = 1 AND MBER_NO >= ?"
				+ "ORDER BY MBER_NO )O)"
				+"WHERE RNUM >=? AND ROWNUM <= ?";
//		SELECT LPAD(MBER_NO,6,'0') as MBER_NO FROM MBER; 고려중
//		시퀀스 번호를 문자로 받아서 숫자로 DTO에 넣고 다시 숫자로 겟 
		String[] mberData = new String[6];
		
		try {
			dbconnect();
			pstmt = con.prepareStatement(codeSearch);
			pstmt.setString(1, tfScondition.getText());
			pstmt.setInt(2, (page - 1) * MAXCNT + 1);
			pstmt.setInt(3, MAXCNT + 1);
			
			rs = pstmt.executeQuery();
			
			int i = 0;
			
//			while(rs.next())
//			{
//				mberData[0] = rs.getString(1);
//				mberData[1] = rs.getString(2);
//				mberData[2] = rs.getString(3);
//				mberData[3] = rs.getString(4);
//				mberData[4] = rs.getString(5);
//				dtModel.addRow(mberData);
//				i++;
//			}
			
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
				
				for(int col = 1; col <= 6; col++)
				{
					if(rs.getObject(col) == null)
					{
						mberData[col - 1] = "";
					}
					else
					{
						mberData[col - 1] = rs.getObject(col).toString();
					}
				}
				
				dtModel.addRow(mberData);
				iScroll = SON;
			}
			
			if(i == 0)
			{
				lblMSGIn.setForeground(Color.RED);
				lblMSGIn.setText("해당 자료가 없습니다.");
			}
			else
			{
				lblMSGIn.setForeground(Color.BLACK);
				lblMSGIn.setText("정상 조회되었습니다.");
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
	
	//* 아이디로 검색  *//
	public void id() {
		
		String idSearch = "SELECT * FROM (SELECT O.* ,  ROWNUM RNUM FROM "
				+ "(SELECT MBER_NO, MBER_ID, MBER_NM, EMAIL, MOBLPHON, MBER_SECSN_AT "
				+ "FROM MBER WHERE 1 = 1 AND MBER_ID LIKE ?)O)"
				+"WHERE RNUM >=? AND ROWNUM <= ?";
//		SELECT LPAD(MBER_NO,6,'0') as MBER_NO FROM MBER; 고려중
		String[] mberData = new String[6];
		
		try {
			dbconnect();
			pstmt = con.prepareStatement(idSearch);
			pstmt.setString(1, tfScondition.getText() + "%");
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
				
				for(int col = 1; col <= 6; col++)
				{
					if(rs.getObject(col) == null)
					{
						mberData[col - 1] = "";
					}
					else
					{
						mberData[col - 1] = rs.getObject(col).toString();
					}
				}
				
				dtModel.addRow(mberData);
				iScroll = SON;
			}
			
			if(i == 0)
			{
				lblMSGIn.setForeground(Color.RED);
				lblMSGIn.setText("해당 자료가 없습니다.");
			}
			else
			{
				lblMSGIn.setForeground(Color.BLACK);
				lblMSGIn.setText("정상 조회되었습니다.");
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
	
	//* 이름으로 검색  *//
	public void name() {
		
		String nmSearch = "SELECT * FROM (SELECT O.* ,  ROWNUM RNUM FROM "
				+ "(SELECT MBER_NO, MBER_ID, MBER_NM, EMAIL, MOBLPHON, MBER_SECSN_AT "
				+ "FROM MBER WHERE 1 = 1 AND MBER_NM LIKE ?)O)"
				+"WHERE RNUM >=? AND ROWNUM <= ?";
//		SELECT LPAD(MBER_NO,6,'0') as MBER_NO FROM MBER; 고려중
		String[] mberData = new String[6];
		
		try {
			dbconnect();
			pstmt = con.prepareStatement(nmSearch);
			pstmt.setString(1, tfScondition.getText() + "%");
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
				
				for(int col = 1; col <= 6; col++)
				{
					if(rs.getObject(col) == null)
					{
						mberData[col - 1] = "";
					}
					else
					{
						mberData[col - 1] = rs.getObject(col).toString();
					}
				}
				
				dtModel.addRow(mberData);
				iScroll = SON;
			}
			
			if(i == 0)
			{
				lblMSGIn.setForeground(Color.RED);
				lblMSGIn.setText("해당 자료가 없습니다.");
			}
			else
			{
				lblMSGIn.setForeground(Color.BLACK);
				lblMSGIn.setText("정상 조회되었습니다.");
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
	
//	public void insert() {
//		
//	}
	
	//* 삭제 *//
	public void delete() {
		
		String mberSelect = "SELECT * FROM MBER WHERE MBER_NO = ?";
		String mberDelete = "DELETE FROM MBER WHERE MBER_NO = ?";
		String mberCode = tfCode.getText();
		
		int ret;
		
		if(mberCode.isEmpty())
		{
			JOptionPane.showMessageDialog(panelMember, "코드를 입력하세요.");
			lblMSGIn.setText("");
			return;
		}
		
		try {
			dbconnect();
	
			pstmt = con.prepareStatement(mberSelect);
			pstmt.setInt(1, Integer.valueOf(mberCode));
			rs = pstmt.executeQuery();
			
			if(!rs.next())
			{
				JOptionPane.showMessageDialog(panelMember, "해당코드가 없습니다.");
				lblMSGIn.setForeground(Color.RED);
				lblMSGIn.setText("해당코드가 없습니다.");
				throw new BizException();
			}
			
			pstmt2 = con.prepareStatement(mberDelete);
			pstmt2.setInt(1, Integer.valueOf(mberCode));
			
			ret = pstmt2.executeUpdate();
			if(ret > 0)
			{
				lblMSGIn.setForeground(Color.BLACK);
				lblMSGIn.setText("정상삭제 되었습니다.");
			}
			else
			{
				lblMSGIn.setForeground(Color.RED);
				lblMSGIn.setText("비정상처리 되었습니다.");
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
			try { if(con != null) con.close();
			} catch(SQLException e1) {}
		}
	}
	
	//* 수정 *//
	public void update() {
		
		String mberSelect = "SELECT MBER_NO_SEQ.NEXTVAL, MBER_ID, MBER_NM, EMAIL, MOBLPHON FROM MBER WHERE 1 = 1 AND MBER_NO = ?";
		String mberUpdate = "UPDATE MBER SET MBER_ID = ?, MBER_NM = ?, EMAIL = ?, "
				+ "MOBLPHON = ?, MBER_SECSN_AT = ? WHERE MBER_NO = ?";
		String mberCode = tfCode.getText();
		String mberId = tfId.getText();
		String mberNm = tfName.getText();
		String mberEmail = tfEmail.getText();
		String mberTel = tfTel.getText();
		String mberAt = tfMberAt.getText();
		
		int ret;
		
		if(mberCode.isEmpty())
		{
			JOptionPane.showMessageDialog(panelMember, "회원번호를 입력하세요.");
			lblMSGIn.setText("");
			return;
		}
		
		try {
			dbconnect();
			
			pstmt = con.prepareStatement(mberSelect);
			pstmt.setInt(1, Integer.valueOf(mberCode));	//Int로 안바꿔줘서 인식이 안됐음 <수정완료>
			rs = pstmt.executeQuery();
			if(!rs.next())
			{
				JOptionPane.showMessageDialog(panelMember, "회원번호가 없습니다.");
				lblMSGIn.setForeground(Color.RED);
				lblMSGIn.setText("회원번호가 없습니다.");
				throw new BizException();
			}
			
			pstmt2 = con.prepareStatement(mberUpdate);
			pstmt2.setString(1, String.valueOf(mberId));
			pstmt2.setString(2, String.valueOf(mberNm));
			pstmt2.setString(3, String.valueOf(mberEmail));
			pstmt2.setString(4, String.valueOf(mberTel));
			pstmt2.setString(5, String.valueOf(mberAt));
			pstmt2.setInt(6, Integer.valueOf(mberCode)); //Int로 안바꿔줘서 인식이 안됐음 <수정완료>
			
			ret = pstmt2.executeUpdate();
			
			if(ret > 0)
			{
				lblMSGIn.setForeground(Color.BLACK);
				lblMSGIn.setText("정상처리 되었습니다.");
			}
			else
			{
				lblMSGIn.setForeground(Color.RED);
				lblMSGIn.setText("비정상처리 되었습니다.");
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
			
			lblMSGIn.setText("");
			
			if(row > -1)
			{
				tfCode.setText((String)table.getValueAt(row, 0));
				tfCode.setEnabled(false);
				tfId.setText((String)table.getValueAt(row, 1));
				tfId.setEnabled(false);
				tfName.setText((String)table.getValueAt(row, 2));
				tfEmail.setText((String)table.getValueAt(row, 3));
				tfTel.setText((String)table.getValueAt(row, 4));
				tfMberAt.setText((String)table.getValueAt(row, 5));
				
				btnInsert.setEnabled(false);
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		if(e.getSource() == cmbSelect)
		{
			if(e.getStateChange() == ItemEvent.SELECTED)
			{
				btnNext.setEnabled(false);
				
				if("코드".equals(e.getItem().toString()))
				{
					iSearch = CODE;
				}
				else if("아이디".equals(e.getItem().toString()))
				{
					iSearch = ID;
				}
				else if("성명".equals(e.getItem().toString()))
				{
					iSearch = NAME;
				}
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
