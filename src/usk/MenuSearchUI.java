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


public class MenuSearchUI extends JPanel implements ActionListener, MouseListener, 
					ItemListener, AdjustmentListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String[] ticket = new String[] {"티켓번호", "유효기간", "티켓명", "설명", 
			"이미지", "공통코드"};
	private int[] colSize = new int[] {10, 10, 30, 60, 30, 20};
	private int[] colAlm = new int[] {JLabel.RIGHT, JLabel.RIGHT, JLabel.LEFT, 
			JLabel.LEFT, JLabel.LEFT, JLabel.RIGHT};
	private JTable table;
	private DefaultTableModel dtModel;
	
	private String[] attraction = new String[] {"어트랙션번호", "구역번호", "어트랙션명", 
			"타입", "설명", "이미지", "운영유무", "시작시간", "종료시간"};
	private int[] colSizeAtt = new int[] {5, 5, 50, 10, 80, 30, 5, 10, 10};
	private int[] colAlmAtt = new int[] {JLabel.RIGHT, JLabel.RIGHT, JLabel.LEFT, 
			JLabel.LEFT, JLabel.LEFT, JLabel.LEFT, JLabel.LEFT,  JLabel.LEFT,  JLabel.LEFT,};
	private DefaultTableModel dtModel_A;
	
	private String[] fclty = new String[] {"구역번호", "시설번호", "구분", "구분명", "시설명", 
			"시설설명", "이미지"};
	private int[] colSizeFt = new int[] {5, 5, 5, 15, 20, 70, 30};
	private int[] colAlmFt = new int[] {JLabel.RIGHT, JLabel.RIGHT, JLabel.RIGHT, 
			JLabel.LEFT, JLabel.LEFT, JLabel.LEFT, JLabel.LEFT};
	private DefaultTableModel dtModel_B;
	
	private String[] hotel = new String[] {"호텔번호", "호텔명", "설명", "대표번호", "주소", "이미지"};
	private int[] colSizeHo = new int[] {5, 10, 50, 10, 30, 30};
	private int[] colAlmHo = new int[] {JLabel.RIGHT, JLabel.LEFT, JLabel.LEFT, 
			JLabel.LEFT, JLabel.LEFT, JLabel.LEFT};
	private DefaultTableModel dtModel_C;
	
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
	private static final int TICKET_NO = 1;
	private static final int ID = 2;
	private static final int NAME = 3;
//	private static final int ATTRACTION_NO = 4;
//	private static final int AREA_NO = 5;
	private 			 int iSearch = TICKET_NO;
	private static final int ATTRACTION_NO = 4;
	private static final int AREA_NO = 5;
	private 			 int iSearch_A = ATTRACTION_NO;
	private static final int FCLTY_NO = 6;
	private static final int AREA_NO_B = 7;
	private 			 int iSearch_B = FCLTY_NO;
	private static final int HOTEL_NO = 8;
	private 			 int iSearch_C = HOTEL_NO;
	
	private int page = 0;
	private static final int MAXCNT = 10;
	private static final int SOFF = 0;
	private static final int SON = 1;
	private				 int iScroll = SOFF;
	
	String prvScondition = null;

	private JPanel panelAllMenu;
	private JButton btnTicketSearch;
	private JButton btnAttractionSearch;
	private JButton btnAdiSearch;
	private JPanel panelTicket;
	private JLabel lblSe;
	private JTextField tfScondition;
	private JComboBox<String> cmbSelect;
	private JButton btnSearch;
	private JButton btnClear;
	private JScrollPane scrollPane;
	private JLabel lblTicketNo;
	private JTextField tfTicketNo;
	private JLabel lblPd;
	private JTextField tfPd;
	private JLabel lblTicketNm;
	private JTextField tfTicketNm;
	private JLabel lblTicketDc;
	private JTextField tfTicketDc;
	private JLabel lblTicketIm;
	private JTextField tfTicketIm;
	private JLabel lblTicketCom;
	private JTextField tfTicketCom;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnInsert;
	private JButton btnNext;
	private JLabel lblMSG;
	private JLabel lblMSGOutput;
	private JPanel panelAttraction;
	private JLabel lblSe_A;
	private JTextField tfScondition_A;
	private JComboBox<String> cmbSelect_A;
	private JButton btnSearch_A;
	private JButton btnClear_A;
	private JLabel lblAttractionNo;
	private JTextField tfAttractionNo;
	private JLabel lblAreaNo;
	private JTextField tfAreaNo;
	private JLabel lblAttractionNm;
	private JTextField tfAttractionNm;
	private JLabel lblAttractionDc;
	private JTextField tfAttractionDc;
	private JLabel lblAttractionTy;
	private JTextField tfAttractionTy;
	private JLabel lblAttractionIm;
	private JTextField tfAttractionIm;
	private JButton btnUpdate_A;
	private JButton btnDelete_A;
	private JButton btnInsert_A;
	private JScrollPane scrollPane_A;
	private JButton btnNext_A;
	private JLabel lblMSG_A;
	private JLabel lblMSGOutput_A;
	private JLabel lblOpAt;
	private JTextField tfOpAt;
	private JLabel lblBeginTime;
	private JTextField tfBeginTime;
	private JLabel lblEndTime;
	private JTextField tfEndTime;
	private JPanel panelMenuSelect;
	private JTable table_A;
	private JButton btnHotelSearch;
	private JPanel panelFclty;
	private JLabel lblSe_B;
	private JTextField tfScondition_B;
	private JComboBox<String> cmbSelect_B;
	private JButton btnSearch_B;
	private JButton btnClear_B;
	private JLabel lblAreaNo_B;
	private JTextField tfAreaNoB;
	private JLabel lblFcltyNo;
	private JTextField tfFcltyNo;
	private JButton btnUpdate_B;
	private JButton btnDelete_B;
	private JButton btnInsert_B;
	private JLabel lblMSG_B;
	private JLabel lblMSGOutput_B;
	private JScrollPane scrollPane_B;
	private JButton btnNext_B;
	private JLabel lblFcltySe;
	private JTextField tfFcltySe;
	private JLabel lblFcltySeNm;
	private JTextField tfFcltySeNm;
	private JLabel lblFcltyNm;
	private JTextField tfFcltyNm;
	private JLabel lblFcltyDc;
	private JTextField tfFcltyDc;
	private JLabel lblFcltyIm;
	private JTextField tfFcltyIm;
	private JTable table_B;
	private JPanel panelHotel;
	private JLabel lblSe_C;
	private JTextField tfScondition_C;
	private JComboBox<String> cmbSelect_C;
	private JButton btnSearch_C;
	private JButton btnClear_C;
	private JLabel lblHotelNo;
	private JTextField tfHotelNo;
	private JLabel lblHotelNm;
	private JTextField tfHotelNm;
	private JButton btnUpdate_C;
	private JButton btnDelete_C;
	private JButton btnInsert_C;
	private JLabel lblMSG_C;
	private JLabel lblMSGOutput_C;
	private JLabel lblHotelTel;
	private JTextField tfHotelTel;
	private JLabel lblHotelDc;
	private JTextField tfHotelDc;
	private JLabel lblHotelIm;
	private JTextField tfHotelIm;
	private JScrollPane scrollPane_C;
	private JButton btnNext_C;
	private JTable table_C;
	private JLabel lblHotelLqtx;
	private JTextField tfHotelLqtx;

	/**
	 * Create the panel.
	 */
	public MenuSearchUI() {
		setLayout(null);
		
		//* 메뉴선택  *//
		panelMenuSelect = new JPanel();
		panelMenuSelect.setBackground(Color.WHITE);
		panelMenuSelect.setBounds(12, 0, 540, 43);
		add(panelMenuSelect);
		panelMenuSelect.setLayout(null);
		
		btnTicketSearch = new JButton("티켓");
		btnTicketSearch.setBounds(35, 10, 100, 23);
		panelMenuSelect.add(btnTicketSearch);
		btnTicketSearch.addActionListener(this);
		
		btnAttractionSearch = new JButton("어트랙션");
		btnAttractionSearch.setBounds(155, 10, 100, 23);
		panelMenuSelect.add(btnAttractionSearch);
		btnAttractionSearch.addActionListener(this);
		
		btnAdiSearch = new JButton("부가시설");
		btnAdiSearch.setBounds(275, 10, 100, 23);
		panelMenuSelect.add(btnAdiSearch);
		btnAdiSearch.addActionListener(this);
		
		btnHotelSearch = new JButton("호텔");
		btnHotelSearch.setBounds(395, 10, 100, 23);
		panelMenuSelect.add(btnHotelSearch);
		btnHotelSearch.addActionListener(this);

		//* 메뉴조회공통패널  *//
		panelAllMenu = new JPanel();
		panelAllMenu.setBackground(Color.WHITE);
		panelAllMenu.setBounds(0, 0, 1042, 611);
		add(panelAllMenu);
		panelAllMenu.setLayout(null);
		
		//* 티켓메뉴패널  *//
		panelTicket = new JPanel();
		panelTicket.setBackground(Color.WHITE);
		panelTicket.setBounds(12, 43, 1018, 558);
		panelAllMenu.add(panelTicket);
		panelTicket.setLayout(null);
		
		lblSe = new JLabel("조회구분");
		lblSe.setBounds(31, 23, 60, 23);
		panelTicket.add(lblSe);
		
		tfScondition = new JTextField();
		tfScondition.setBounds(210, 25, 116, 21);
		panelTicket.add(tfScondition);
		tfScondition.setColumns(10);
		
		cmbSelect = new JComboBox<String>();
		cmbSelect.setModel(new DefaultComboBoxModel<String>(new String[] {"티켓번호"}));
		cmbSelect.setBounds(92, 25, 106, 21);
		panelTicket.add(cmbSelect);
		cmbSelect.addItemListener(this);
		
		btnSearch = new JButton("조회");
		btnSearch.setBounds(775, 25, 97, 23);
		panelTicket.add(btnSearch);
		btnSearch.addActionListener(this);
		
		btnClear = new JButton("초기화");
		btnClear.setBounds(893, 25, 97, 23);
		panelTicket.add(btnClear);
		btnClear.addActionListener(this);
		
		lblTicketNo = new JLabel("티켓번호");
		lblTicketNo.setBounds(31, 456, 60, 23);
		panelTicket.add(lblTicketNo);
		
		tfTicketNo = new JTextField();
		tfTicketNo.setBounds(92, 457, 60, 21);
		panelTicket.add(tfTicketNo);
		tfTicketNo.setColumns(10);
		
		lblPd = new JLabel("유효기간");
		lblPd.setBounds(168, 456, 60, 23);
		panelTicket.add(lblPd);
		
		tfPd = new JTextField();
		tfPd.setColumns(10);
		tfPd.setBounds(229, 457, 60, 21);
		panelTicket.add(tfPd);
		
		lblTicketNm = new JLabel("티켓명");
		lblTicketNm.setBounds(301, 456, 60, 23);
		panelTicket.add(lblTicketNm);
		
		tfTicketNm = new JTextField();
		tfTicketNm.setColumns(10);
		tfTicketNm.setBounds(349, 457, 116, 21);
		panelTicket.add(tfTicketNm);
		
		lblTicketDc = new JLabel("티켓설명");
		lblTicketDc.setBounds(168, 492, 60, 23);
		panelTicket.add(lblTicketDc);
		
		tfTicketDc = new JTextField();
		tfTicketDc.setColumns(10);
		tfTicketDc.setBounds(229, 493, 401, 55);
		panelTicket.add(tfTicketDc);
		
		lblTicketIm = new JLabel("이미지");
		lblTicketIm.setBounds(477, 456, 60, 23);
		panelTicket.add(lblTicketIm);
		
		tfTicketIm = new JTextField();
		tfTicketIm.setColumns(10);
		tfTicketIm.setBounds(525, 457, 105, 21);
		panelTicket.add(tfTicketIm);
		
		lblTicketCom = new JLabel("공통코드");
		lblTicketCom.setBounds(642, 456, 60, 23);
		panelTicket.add(lblTicketCom);
		
		tfTicketCom = new JTextField();
		tfTicketCom.setColumns(10);
		tfTicketCom.setBounds(701, 457, 105, 21);
		panelTicket.add(tfTicketCom);
		
		btnUpdate = new JButton("수정");
		btnUpdate.setBounds(893, 492, 97, 23);
		panelTicket.add(btnUpdate);
		btnUpdate.addActionListener(this);
		
		btnDelete = new JButton("삭제");
		btnDelete.setBounds(893, 525, 97, 23);
		panelTicket.add(btnDelete);
		btnDelete.addActionListener(this);
		
		btnInsert = new JButton("등록");
		btnInsert.setBounds(783, 525, 97, 23);
		panelTicket.add(btnInsert);
		btnInsert.addActionListener(this);
		
		lblMSG = new JLabel("MSG");
		lblMSG.setBounds(31, 530, 39, 23);
		panelTicket.add(lblMSG);
				
		lblMSGOutput = new JLabel("MSG");
		lblMSGOutput.setBounds(82, 530, 150, 23);
		panelTicket.add(lblMSGOutput);
				
		panelTicket.setVisible(true);
				
		scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 76, 959, 343);
		panelTicket.add(scrollPane);
		
		//*티켓테이블 모델설정*//
		dtModel = new DefaultTableModel(ticket, 0);
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
		btnNext.setBounds(893, 429, 97, 23);
		panelTicket.add(btnNext);
		btnNext.addActionListener(this);
		
		
		//* 어트랙션메뉴패널  *//
		panelAttraction = new JPanel();
		panelAttraction.setBackground(Color.WHITE);
		panelAttraction.setLayout(null);
		panelAttraction.setBounds(12, 43, 1018, 558);
		panelAllMenu.add(panelAttraction);
		
		lblSe_A = new JLabel("조회구분");
		lblSe_A.setBounds(31, 23, 60, 23);
		panelAttraction.add(lblSe_A);
		
		tfScondition_A = new JTextField();
		tfScondition_A.setColumns(10);
		tfScondition_A.setBounds(210, 25, 116, 21);
		panelAttraction.add(tfScondition_A);
		
		cmbSelect_A = new JComboBox<String>();
		cmbSelect_A.setModel(new DefaultComboBoxModel<String>(new String[] {"어트랙션번호", "구역번호"}));
		cmbSelect_A.setBounds(92, 25, 106, 21);
		panelAttraction.add(cmbSelect_A);
		cmbSelect_A.addItemListener(this);
		
		btnSearch_A = new JButton("조회");
		btnSearch_A.setBounds(775, 25, 97, 23);
		panelAttraction.add(btnSearch_A);
		btnSearch_A.addActionListener(this);
		
		btnClear_A = new JButton("초기화");
		btnClear_A.setBounds(893, 25, 97, 23);
		panelAttraction.add(btnClear_A);
		btnClear_A.addActionListener(this);
		
		lblAttractionNo = new JLabel("어트랙션번호");
		lblAttractionNo.setBounds(31, 456, 79, 23);
		panelAttraction.add(lblAttractionNo);
		
		tfAttractionNo = new JTextField();
		tfAttractionNo.setColumns(10);
		tfAttractionNo.setBounds(113, 457, 39, 21);
		panelAttraction.add(tfAttractionNo);
		
		lblAreaNo = new JLabel("구역번호");
		lblAreaNo.setBounds(168, 456, 60, 23);
		panelAttraction.add(lblAreaNo);
		
		tfAreaNo = new JTextField();
		tfAreaNo.setColumns(10);
		tfAreaNo.setBounds(229, 457, 60, 21);
		panelAttraction.add(tfAreaNo);
		
		lblAttractionNm = new JLabel("어트랙션명");
		lblAttractionNm.setBounds(301, 456, 60, 23);
		panelAttraction.add(lblAttractionNm);
		
		tfAttractionNm = new JTextField();
		tfAttractionNm.setColumns(10);
		tfAttractionNm.setBounds(373, 457, 92, 21);
		panelAttraction.add(tfAttractionNm);
		
		lblAttractionDc = new JLabel("어트랙션설명");
		lblAttractionDc.setBounds(149, 492, 79, 23);
		panelAttraction.add(lblAttractionDc);
		
		tfAttractionDc = new JTextField();
		tfAttractionDc.setColumns(10);
		tfAttractionDc.setBounds(229, 493, 401, 55);
		panelAttraction.add(tfAttractionDc);
		
		lblAttractionTy = new JLabel("타입");
		lblAttractionTy.setBounds(477, 456, 32, 23);
		panelAttraction.add(lblAttractionTy);
		
		tfAttractionTy = new JTextField();
		tfAttractionTy.setColumns(10);
		tfAttractionTy.setBounds(514, 457, 60, 21);
		panelAttraction.add(tfAttractionTy);
		
		lblAttractionIm = new JLabel("이미지");
		lblAttractionIm.setBounds(586, 456, 60, 23);
		panelAttraction.add(lblAttractionIm);
		
		tfAttractionIm = new JTextField();
		tfAttractionIm.setColumns(10);
		tfAttractionIm.setBounds(634, 457, 97, 21);
		panelAttraction.add(tfAttractionIm);
		
		btnUpdate_A = new JButton("수정");
		btnUpdate_A.setBounds(893, 492, 97, 23);
		panelAttraction.add(btnUpdate_A);
		btnUpdate_A.addActionListener(this);
		
		btnDelete_A = new JButton("삭제");
		btnDelete_A.setBounds(893, 525, 97, 23);
		panelAttraction.add(btnDelete_A);
		btnDelete_A.addActionListener(this);
		
		btnInsert_A = new JButton("등록");
		btnInsert_A.setBounds(783, 525, 97, 23);
		panelAttraction.add(btnInsert_A);
		btnInsert_A.addActionListener(this);
		
		lblMSG_A = new JLabel("MSG");
		lblMSG_A.setBounds(31, 530, 39, 23);
		panelAttraction.add(lblMSG_A);
		
		lblMSGOutput_A = new JLabel("MSG");
		lblMSGOutput_A.setBounds(82, 530, 150, 23);
		panelAttraction.add(lblMSGOutput_A);
		
		lblOpAt = new JLabel("영업유무");
		lblOpAt.setBounds(634, 492, 58, 23);
		panelAttraction.add(lblOpAt);
		
		tfOpAt = new JTextField();
		tfOpAt.setColumns(10);
		tfOpAt.setBounds(692, 493, 39, 21);
		panelAttraction.add(tfOpAt);
		
		lblBeginTime = new JLabel("시작시간");
		lblBeginTime.setBounds(743, 456, 60, 23);
		panelAttraction.add(lblBeginTime);
		
		tfBeginTime = new JTextField();
		tfBeginTime.setColumns(10);
		tfBeginTime.setBounds(800, 457, 84, 21);
		panelAttraction.add(tfBeginTime);
		
		lblEndTime = new JLabel("종료시간");
		lblEndTime.setBounds(743, 492, 60, 23);
		panelAttraction.add(lblEndTime);
		
		tfEndTime = new JTextField();
		tfEndTime.setColumns(10);
		tfEndTime.setBounds(800, 493, 84, 21);
		panelAttraction.add(tfEndTime);
		
		scrollPane_A = new JScrollPane();
		scrollPane_A.setBounds(31, 76, 959, 343);
		panelAttraction.add(scrollPane_A);
		
		//*어트랙션테이블 모델설정*//
		dtModel_A = new DefaultTableModel(attraction, 0);
		table_A = new JTable();
		table_A.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		table_A.setAutoCreateColumnsFromModel(false);
		table_A.setModel(dtModel_A);
		for(int j = 0; j < colAlmAtt.length; j++)
		{
			DefaultTableCellRenderer renderer_A = new DefaultTableCellRenderer();
			renderer_A.setHorizontalAlignment(colAlmAtt[j]);
			TableColumn column = new TableColumn(j, colSizeAtt[j], renderer_A, null);
			table_A.addColumn(column);
		}
		
		table_A.setFocusable(false);
		scrollPane_A.setViewportView(table_A);
		table_A.addMouseListener(this);
		table_A.setRowHeight(25);
		panelAttraction.setVisible(false);
		
		btnNext_A = new JButton("NEXT");
		btnNext_A.setBounds(893, 429, 97, 23);
		panelAttraction.add(btnNext_A);
		btnNext_A.addActionListener(this);
		
		//* 부가시설메뉴패널  *//
		panelFclty = new JPanel();
		panelFclty.setBackground(Color.WHITE);
		panelFclty.setBounds(12, 43, 1018, 558);
		panelAllMenu.add(panelFclty);
		panelFclty.setLayout(null);
		
		lblSe_B = new JLabel("조회구분");
		lblSe_B.setBounds(31, 23, 60, 23);
		panelFclty.add(lblSe_B);
		
		tfScondition_B = new JTextField();
		tfScondition_B.setBounds(210, 25, 116, 21);
		tfScondition_B.setColumns(10);
		panelFclty.add(tfScondition_B);
		
		cmbSelect_B = new JComboBox<String>();
		cmbSelect_B.setModel(new DefaultComboBoxModel<String>(new String[] {"부가시설번호", "구역번호"}));
		cmbSelect_B.setBounds(92, 25, 106, 21);
		panelFclty.add(cmbSelect_B);
		cmbSelect_B.addItemListener(this);
		
		btnSearch_B = new JButton("조회");
		btnSearch_B.setBounds(775, 25, 97, 23);
		panelFclty.add(btnSearch_B);
		btnSearch_B.addActionListener(this);
		
		btnClear_B = new JButton("초기화");
		btnClear_B.setBounds(893, 25, 97, 23);
		panelFclty.add(btnClear_B);
		btnClear_B.addActionListener(this);
		
		lblAreaNo_B = new JLabel("구역번호");
		lblAreaNo_B.setBounds(31, 449, 53, 15);
		panelFclty.add(lblAreaNo_B);
		
		tfAreaNoB = new JTextField();
		tfAreaNoB.setBounds(91, 446, 39, 21);
		tfAreaNoB.setColumns(10);
		panelFclty.add(tfAreaNoB);
		
		lblFcltyNo = new JLabel("부가시설번호");
		lblFcltyNo.setBounds(157, 449, 78, 15);
		panelFclty.add(lblFcltyNo);
		
		tfFcltyNo = new JTextField();
		tfFcltyNo.setBounds(241, 446, 45, 21);
		tfFcltyNo.setColumns(10);
		panelFclty.add(tfFcltyNo);
		
		btnUpdate_B = new JButton("수정");
		btnUpdate_B.setBounds(893, 492, 97, 23);
		panelFclty.add(btnUpdate_B);
		btnUpdate_B.addActionListener(this);
		
		btnDelete_B = new JButton("삭제");
		btnDelete_B.setBounds(893, 525, 97, 23);
		panelFclty.add(btnDelete_B);
		btnDelete_B.addActionListener(this);
		
		btnInsert_B = new JButton("등록");
		btnInsert_B.setBounds(783, 525, 97, 23);
		panelFclty.add(btnInsert_B);
		btnInsert_B.addActionListener(this);
		
		lblMSG_B = new JLabel("MSG");
		lblMSG_B.setBounds(31, 530, 39, 23);
		panelFclty.add(lblMSG_B);
		
		lblMSGOutput_B = new JLabel("MSG");
		lblMSGOutput_B.setBounds(82, 530, 150, 23);
		panelFclty.add(lblMSGOutput_B);
		
		lblFcltySe = new JLabel("구분");
		lblFcltySe.setBounds(311, 449, 33, 15);
		panelFclty.add(lblFcltySe);
		
		tfFcltySe = new JTextField();
		tfFcltySe.setColumns(10);
		tfFcltySe.setBounds(349, 446, 45, 21);
		panelFclty.add(tfFcltySe);
		
		lblFcltySeNm = new JLabel("구분명");
		lblFcltySeNm.setBounds(414, 449, 39, 15);
		panelFclty.add(lblFcltySeNm);
		
		tfFcltySeNm = new JTextField();
		tfFcltySeNm.setColumns(10);
		tfFcltySeNm.setBounds(464, 446, 53, 21);
		panelFclty.add(tfFcltySeNm);
		
		lblFcltyNm = new JLabel("시설명");
		lblFcltyNm.setBounds(540, 449, 39, 15);
		panelFclty.add(lblFcltyNm);
		
		tfFcltyNm = new JTextField();
		tfFcltyNm.setColumns(10);
		tfFcltyNm.setBounds(590, 446, 126, 21);
		panelFclty.add(tfFcltyNm);
		
		lblFcltyDc = new JLabel("설명");
		lblFcltyDc.setBounds(311, 484, 33, 15);
		panelFclty.add(lblFcltyDc);
		
		tfFcltyDc = new JTextField();
		tfFcltyDc.setColumns(10);
		tfFcltyDc.setBounds(349, 481, 367, 67);
		panelFclty.add(tfFcltyDc);
		
		lblFcltyIm = new JLabel("이미지");
		lblFcltyIm.setBounds(735, 449, 39, 15);
		panelFclty.add(lblFcltyIm);
		
		tfFcltyIm = new JTextField();
		tfFcltyIm.setColumns(10);
		tfFcltyIm.setBounds(785, 446, 95, 21);
		panelFclty.add(tfFcltyIm);
		
		scrollPane_B = new JScrollPane();
		scrollPane_B.setBounds(31, 76, 959, 343);
		panelFclty.add(scrollPane_B);
		
		//*부가시설테이블 모델설정*//
		dtModel_B = new DefaultTableModel(fclty, 0);
		table_B = new JTable();
		table_B.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		table_B.setAutoCreateColumnsFromModel(false);
		table_B.setModel(dtModel_B);
		for(int k = 0; k < colAlmFt.length; k++)
		{
			DefaultTableCellRenderer renderer_B = new DefaultTableCellRenderer();
			renderer_B.setHorizontalAlignment(colAlmFt[k]);
			TableColumn column = new TableColumn(k, colSizeFt[k], renderer_B, null);
			table_B.addColumn(column);
		}
		
		table_B.setFocusable(false);
		scrollPane_B.setViewportView(table_B);
		table_B.addMouseListener(this);
		table_B.setRowHeight(25);
		
		btnNext_B = new JButton("NEXT");
		btnNext_B.setBounds(893, 429, 97, 23);
		panelFclty.add(btnNext_B);
		btnNext_B.addActionListener(this);
		
		
		//* 호텔메뉴패널  *//
		panelHotel = new JPanel();
		panelHotel.setBackground(Color.WHITE);
		panelHotel.setBounds(12, 43, 1018, 558);
		panelAllMenu.add(panelHotel);
		panelHotel.setLayout(null);
		
		lblSe_C = new JLabel("조회구분");
		lblSe_C.setBounds(31, 23, 60, 23);
		panelHotel.add(lblSe_C);
		
		tfScondition_C = new JTextField();
		tfScondition_C.setColumns(10);
		tfScondition_C.setBounds(210, 25, 116, 21);
		panelHotel.add(tfScondition_C);
		
		cmbSelect_C = new JComboBox<String>();
		cmbSelect_C.setModel(new DefaultComboBoxModel<String>(new String[] {"호텔번호"}));
		cmbSelect_C.setBounds(92, 25, 106, 21);
		panelHotel.add(cmbSelect_C);
		cmbSelect_C.addItemListener(this);
		
		btnSearch_C = new JButton("조회");
		btnSearch_C.setBounds(775, 25, 97, 23);
		panelHotel.add(btnSearch_C);
		btnSearch_C.addActionListener(this);
		
		btnClear_C = new JButton("초기화");
		btnClear_C.setBounds(893, 25, 97, 23);
		panelHotel.add(btnClear_C);
		btnClear_C.addActionListener(this);
		
		lblHotelNo = new JLabel("호텔번호");
		lblHotelNo.setBounds(31, 449, 53, 15);
		panelHotel.add(lblHotelNo);
		
		tfHotelNo = new JTextField();
		tfHotelNo.setColumns(10);
		tfHotelNo.setBounds(91, 446, 39, 21);
		panelHotel.add(tfHotelNo);
		
		lblHotelNm = new JLabel("호텔명");
		lblHotelNm.setBounds(157, 449, 45, 15);
		panelHotel.add(lblHotelNm);
		
		tfHotelNm = new JTextField();
		tfHotelNm.setColumns(10);
		tfHotelNm.setBounds(206, 446, 126, 21);
		panelHotel.add(tfHotelNm);
		
		btnUpdate_C = new JButton("수정");
		btnUpdate_C.setBounds(893, 492, 97, 23);
		panelHotel.add(btnUpdate_C);
		btnUpdate_C.addActionListener(this);
		
		btnDelete_C = new JButton("삭제");
		btnDelete_C.setBounds(893, 525, 97, 23);
		panelHotel.add(btnDelete_C);
		btnDelete_C.addActionListener(this);
		
		btnInsert_C = new JButton("등록");
		btnInsert_C.setBounds(783, 525, 97, 23);
		panelHotel.add(btnInsert_C);
		btnInsert_C.addActionListener(this);
		
		lblMSG_C = new JLabel("MSG");
		lblMSG_C.setBounds(31, 530, 39, 23);
		panelHotel.add(lblMSG_C);
		
		lblMSGOutput_C = new JLabel("MSG");
		lblMSGOutput_C.setBounds(82, 530, 150, 23);
		panelHotel.add(lblMSGOutput_C);
		
		lblHotelTel = new JLabel("전화번호");
		lblHotelTel.setBounds(359, 449, 60, 15);
		panelHotel.add(lblHotelTel);
		
		tfHotelTel = new JTextField();
		tfHotelTel.setColumns(10);
		tfHotelTel.setBounds(422, 446, 106, 21);
		panelHotel.add(tfHotelTel);
		
		lblHotelDc = new JLabel("설명");
		lblHotelDc.setBounds(347, 484, 33, 15);
		panelHotel.add(lblHotelDc);
		
		tfHotelDc = new JTextField();
		tfHotelDc.setColumns(10);
		tfHotelDc.setBounds(385, 481, 367, 67);
		panelHotel.add(tfHotelDc);
		
		lblHotelIm = new JLabel("이미지");
		lblHotelIm.setBounds(557, 449, 39, 15);
		panelHotel.add(lblHotelIm);
		
		tfHotelIm = new JTextField();
		tfHotelIm.setColumns(10);
		tfHotelIm.setBounds(607, 446, 145, 21);
		panelHotel.add(tfHotelIm);
		
		lblHotelLqtx = new JLabel("주소");
		lblHotelLqtx.setBounds(89, 484, 33, 15);
		panelHotel.add(lblHotelLqtx);
		
		tfHotelLqtx = new JTextField();
		tfHotelLqtx.setColumns(10);
		tfHotelLqtx.setBounds(131, 481, 201, 21);
		panelHotel.add(tfHotelLqtx);
		
		scrollPane_C = new JScrollPane();
		scrollPane_C.setBounds(31, 76, 959, 343);
		panelHotel.add(scrollPane_C);
		
		//*호텔테이블 모델설정*//
		dtModel_C = new DefaultTableModel(hotel, 0);
		table_C = new JTable();
		table_C.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		table_C.setAutoCreateColumnsFromModel(false);
		table_C.setModel(dtModel_C);
		for(int h = 0; h < colAlmHo.length; h++)
		{
			DefaultTableCellRenderer renderer_C = new DefaultTableCellRenderer();
			renderer_C.setHorizontalAlignment(colAlmHo[h]);
			TableColumn column = new TableColumn(h, colSizeHo[h], renderer_C, null);
			table_C.addColumn(column);
		}
		
		table_C.setFocusable(false);
		scrollPane_C.setViewportView(table_C);
		table_C.addMouseListener(this);
		table_C.setRowHeight(25);
		
		btnNext_C = new JButton("NEXT");
		btnNext_C.setBounds(893, 429, 97, 23);
		panelHotel.add(btnNext_C);
		btnNext_C.addActionListener(this);
		
		
		panelTicket.setVisible(true);
		panelAttraction.setVisible(false);
		panelFclty.setVisible(false);
		panelHotel.setVisible(false);

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
		btnInsert.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		
		tfAttractionNo.setEnabled(true);
		btnInsert_A.setEnabled(true);
		btnUpdate_A.setEnabled(false);
		btnDelete_A.setEnabled(false);
		
		tfFcltyNo.setEnabled(true);
		btnInsert_B.setEnabled(true);
		btnUpdate_B.setEnabled(false);
		btnDelete_B.setEnabled(false);
		
		tfHotelNo.setEnabled(true);
		btnInsert_C.setEnabled(true);
		btnUpdate_C.setEnabled(false);
		btnDelete_C.setEnabled(false);
	}
	
	public void clear(int iMode) {
		
		if((iMode & CONDITION) > 0)
		{
			tfScondition.setText("");
			lblMSGOutput.setText("");
			
			tfScondition_A.setText("");
			lblMSGOutput_A.setText("");
			
			tfScondition_B.setText("");
			lblMSGOutput_B.setText("");
			
			tfScondition_C.setText("");
			lblMSGOutput_C.setText("");
		}
		if((iMode & TABLE) > 0)
		{
			dtModel.setRowCount(0);
			dtModel_A.setRowCount(0);
			dtModel_B.setRowCount(0);
			dtModel_C.setRowCount(0);
		}
		if((iMode & FIELD) > 0)
		{
			tfTicketNo.setText("");
			tfPd.setText("");
			tfTicketNm.setText("");
			tfTicketIm.setText("");
			tfTicketDc.setText("");
			tfTicketCom.setText("");
			
			tfAttractionNo.setText("");
			tfAreaNo.setText("");
			tfAttractionNm.setText("");
			tfAttractionTy.setText("");
			tfAttractionDc.setText("");
			tfAttractionIm.setText("");
			tfOpAt.setText("");
			tfBeginTime.setText("");
			tfEndTime.setText("");
			
			tfAreaNoB.setText("");
			tfFcltyNo.setText("");
			tfFcltySe.setText("");
			tfFcltySeNm.setText("");
			tfFcltyNm.setText("");
			tfFcltyDc.setText("");
			tfFcltyIm.setText("");
			
			tfHotelNo.setText("");
			tfHotelNm.setText("");
			tfHotelDc.setText("");
			tfHotelTel.setText("");
			tfHotelLqtx.setText("");
			tfHotelIm.setText("");
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnTicketSearch)
		{
//			Utility.changePanel(panelAllMenu, panelTicket);
			panelAttraction.setVisible(false);
			panelFclty.setVisible(false);
			panelHotel.setVisible(false);
			panelTicket.setVisible(true);
			enableClear();
		}
		if(e.getSource() == btnClear)
		{
			clear(ALL);
		}
		else if(e.getSource() == btnSearch)
		{
			if(tfScondition.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(panelTicket, "조회조건을 입력하세요.");
				return;
			}
		
			page = 1;
			clear(TABLE | FIELD);
	
			if(iSearch == TICKET_NO)
			{
				ticketAllSearch();
			}
			else
			{
				JOptionPane.showMessageDialog(panelTicket, "조회조건을 입력하세요.");
				return;
			}
		}
		else if(e.getSource() == btnInsert)
		{
			ticketInsert();
		}
		else if(e.getSource() == btnDelete)
		{
			ticketDelete();
		}
		else if(e.getSource() == btnUpdate)
		{
			ticketUpdate();
		}
		else if(e.getSource() == btnNext)
		{
			page++;
			clear(FIELD);
		
			if(iSearch == TICKET_NO)
			{
				ticketAllSearch();
			}
		}
		else if(e.getSource() == btnAttractionSearch)
		{
//			Utility.changePanel(panelAllMenu, panelAttraction);
			panelTicket.setVisible(false);
			panelFclty.setVisible(false);
			panelHotel.setVisible(false);
			panelAttraction.setVisible(true);
			enableClear();
		}
		else if(e.getSource() == btnClear_A)
		{
			clear(ALL);
		}
		else if(e.getSource() == btnSearch_A)
		{
			if(tfScondition_A.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(panelAttraction, "조회조건을 입력하세요.");
				return;
			}
		
			page = 1;
			clear(TABLE | FIELD);
	
			if(iSearch_A == ATTRACTION_NO)
			{
				attractionAllSearch();
			}
			else if(iSearch_A == AREA_NO)
			{
				attractionArea();
			}
			else
			{
				JOptionPane.showMessageDialog(panelAttraction, "조회조건을 입력하세요.");
				return;
			}
		}
		else if(e.getSource() == btnInsert_A)
		{
			attractionInsert();
		}
		else if(e.getSource() == btnDelete_A)
		{
			attractionDelete();
		}
		else if(e.getSource() == btnUpdate_A)
		{
			attractionUpdate();
		}
		else if(e.getSource() == btnNext_A)
		{
			page++;
			clear(FIELD);
		
			if(iSearch_A == ATTRACTION_NO)
			{
				attractionAllSearch();
			}
			else if(iSearch_A == AREA_NO)
			{
				attractionArea();
			}
		}
		else if(e.getSource() == btnAdiSearch)
		{
			panelTicket.setVisible(false);
			panelAttraction.setVisible(false);
			panelHotel.setVisible(false);
			panelFclty.setVisible(true);
			enableClear();
		}
		else if(e.getSource() == btnClear_B)
		{
			clear(ALL);
		}
		else if(e.getSource() == btnSearch_B)
		{
			if(tfScondition_B.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(panelFclty, "조회조건을 입력하세요.");
				return;
			}
		
			page = 1;
			clear(TABLE | FIELD);
	
			if(iSearch_B == FCLTY_NO)
			{
				fcltyAllSearch();
			}
			else if(iSearch_B == AREA_NO_B)
			{
				fcltyArea();
			}
			else
			{
				JOptionPane.showMessageDialog(panelFclty, "조회조건을 입력하세요.");
				return;
			}
		}
		else if(e.getSource() == btnInsert_B)
		{
			fcltyInsert();
		}
		else if(e.getSource() == btnDelete_B)
		{
			fcltyDelete();
		}
		else if(e.getSource() == btnUpdate_B)
		{
			fcltyUpdate();
		}
		else if(e.getSource() == btnNext_B)
		{
			page++;
			clear(FIELD);
		
			if(iSearch_B == FCLTY_NO)
			{
				fcltyAllSearch();
			}
			else if(iSearch_B == AREA_NO_B)
			{
				fcltyArea();
			}
		}
		else if(e.getSource() == btnHotelSearch)
		{
			panelTicket.setVisible(false);
			panelAttraction.setVisible(false);
			panelFclty.setVisible(false);
			panelHotel.setVisible(true);
			enableClear();
		}
		else if(e.getSource() == btnClear_C)
		{
			clear(ALL);
		}
		else if(e.getSource() == btnSearch_C)
		{
			if(tfScondition_C.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(panelHotel, "조회조건을 입력하세요.");
				return;
			}
		
			page = 1;
			clear(TABLE | FIELD);
	
			if(iSearch_C == HOTEL_NO)
			{
				hotelAllSearch();
			}
			else
			{
				JOptionPane.showMessageDialog(panelHotel, "조회조건을 입력하세요.");
				return;
			}
		}
		else if(e.getSource() == btnInsert_C)
		{
			hotelInsert();
		}
		else if(e.getSource() == btnDelete_C)
		{
			hotelDelete();
		}
		else if(e.getSource() == btnUpdate_C)
		{
			hotelUpdate();
		}
		else if(e.getSource() == btnNext_C)
		{
			page++;
			clear(FIELD);
		
			if(iSearch_C == HOTEL_NO)
			{
				hotelAllSearch();
			}
		}
	}
	
	//*티켓조회*//
	public void ticketAllSearch() {
		
		String noSearch = "SELECT * FROM (SELECT O.* ,  ROWNUM RNUM FROM "
				+ "(SELECT * FROM TICKET WHERE 1 = 1 AND TICKET_NO >= ? "
				+ "ORDER BY TICKET_NO )O)"
				+"WHERE RNUM >=? AND ROWNUM <= ?";
		String[] ticketData = new String[6];
		
		try {
			dbconnect();
			pstmt = con.prepareStatement(noSearch);
			pstmt.setString(1, "T" + tfScondition.getText());
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
						ticketData[col - 1] = "";
					}
					else
					{
						ticketData[col - 1] = rs.getObject(col).toString();
					}
				}
				
				dtModel.addRow(ticketData);
				iScroll = SON;
			}
			
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
	
	//*티켓등록*//
	public void ticketInsert() {
		
		String no = "SELECT * FROM TICKET WHERE TICKET_NO = ?";
		String ticketInsert = "INSERT INTO TICKET VALUES(?,?,?,?,?,?)";
		
		int ret;
		
		String ticketNo = tfTicketNo.getText();
		String ticketPd = tfPd.getText();
		String ticketNm = tfTicketNm.getText();
		String ticketDc = tfTicketDc.getText();
		String ticketIm = tfTicketIm.getText();
		String ticketCm = tfTicketCom.getText();
		
		if(ticketNo.isEmpty())
		{
			JOptionPane.showMessageDialog(panelTicket, "티켓번호를 입력하세요.");
			lblMSGOutput.setText("");
			return;
		}
		if(ticketPd.isEmpty())
		{
			JOptionPane.showMessageDialog(panelTicket, "유효기간을 입력하세요.");
			lblMSGOutput.setText("");
			return;
		}
		if(ticketNm.isEmpty())
		{
			JOptionPane.showMessageDialog(panelTicket, "티켓명을 입력하세요.");
			lblMSGOutput.setText("");
			return;
		}
		if(ticketCm.isEmpty())
		{
			JOptionPane.showMessageDialog(panelTicket, "공통코드를 입력하세요.");
			lblMSGOutput.setText("");
			return;
		}
		
		try {
			dbconnect();
			
			pstmt = con.prepareStatement(no);
			pstmt.setInt(1, Integer.parseInt(ticketNo));
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				JOptionPane.showMessageDialog(panelTicket, "해당번호가 존재합니다.");
				lblMSGOutput.setForeground(Color.RED);
				lblMSGOutput.setText("해당번호가 존재합니다.");
				throw new BizException();
			}
			
			pstmt2 = con.prepareStatement(ticketInsert);
			pstmt2.setString(1, ticketNo);
			pstmt2.setInt(2, Integer.parseInt(ticketPd));
			pstmt2.setString(3, ticketNm);
			pstmt2.setString(4, ticketDc);
			pstmt2.setString(5, ticketIm);
			pstmt2.setString(6, ticketCm);
			
			ret = pstmt2.executeUpdate();
			
			if(ret > 0)
			{
				JOptionPane.showMessageDialog(panelTicket, "정상등록 되었습니다.");
				lblMSGOutput.setForeground(Color.BLACK);
				lblMSGOutput.setText("정상등록");
			}
			else
			{
				JOptionPane.showMessageDialog(panelTicket, "오류발생!");
				lblMSGOutput.setForeground(Color.RED);
				lblMSGOutput.setText("오류발생");
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
	
	//*티켓삭제*// 삭제를 시키면 불러오지를 못함.
	public void ticketDelete() {
		
		String no = "SELECT * FROM TICKET WHERE TICKET_NO = ?";
		String ticketDelete = "DELETE FROM TICKET WHERE TICKET_NO = ?";
		String ticketNo = tfTicketNo.getText();
		
		int ret;
		
		if(ticketNo.isEmpty())
		{
			JOptionPane.showMessageDialog(panelTicket, "티켓번호를 입력하세요.");
			lblMSGOutput.setText("");
			return;
		}
		
		try {
			dbconnect();
			
			pstmt = con.prepareStatement(no);
			pstmt.setInt(1, Integer.parseInt(ticketNo));
			
			rs = pstmt.executeQuery();
			
			if(!rs.next())
			{
				JOptionPane.showMessageDialog(panelTicket, "해당번호가 없습니다.");
				lblMSGOutput.setForeground(Color.RED);
				lblMSGOutput.setText("해당번호가 없습니다.");
				throw new BizException();
			}
			
			pstmt2 = con.prepareStatement(ticketDelete);
			pstmt2.setString(1, ticketNo);
			
			ret = pstmt2.executeUpdate();
			
			if(ret > 0)
			{
				JOptionPane.showMessageDialog(panelTicket, "삭제 되었습니다.");
				lblMSGOutput.setForeground(Color.BLACK);
				lblMSGOutput.setText("정상삭제");
			}
			else
			{
				JOptionPane.showMessageDialog(panelTicket, "오류발생!");
				lblMSGOutput.setForeground(Color.RED);
				lblMSGOutput.setText("오류발생");
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
	
	//*티켓수정*//
	public void ticketUpdate() {
		
		String no = "SELECT * FROM TICKET WHERE TICKET_NO = ?";
		String ticketUpdate = "UPDATE TICKET SET VALID_PD = ?, TICKET_NM = ?, "
				+ "TICKET_DC = ?, PHOTO_COURS = ?, CMMN_CODE = ? "
				+ "WHERE TICKET_NO = ? ";
		
		int ret;
		
		String ticketNo = tfTicketNo.getText();
		String ticketPd = tfPd.getText();
		String ticketNm = tfTicketNm.getText();
		String ticketDc = tfTicketDc.getText();
		String ticketIm = tfTicketIm.getText();
		String ticketCm = tfTicketCom.getText();
		
		if(ticketNo.isEmpty())
		{
			JOptionPane.showMessageDialog(panelTicket, "티켓번호를 입력하세요.");
			lblMSGOutput.setText("");
			return;
		}
		
		try {
			dbconnect();
			
			pstmt = con.prepareStatement(no);
			pstmt.setString(1, ticketNo);	
			rs = pstmt.executeQuery();
			if(!rs.next())
			{
				JOptionPane.showMessageDialog(panelTicket, "티켓번호가 없습니다.");
				lblMSGOutput.setForeground(Color.RED);
				lblMSGOutput.setText("티켓번호가 없습니다.");
				throw new BizException();
			}
			
			pstmt2 = con.prepareStatement(ticketUpdate);
			pstmt2.setInt(1, Integer.valueOf(ticketPd));
			pstmt2.setString(2, String.valueOf(ticketNm));
			pstmt2.setString(3, String.valueOf(ticketDc));
			pstmt2.setString(4, String.valueOf(ticketIm));
			pstmt2.setString(5, String.valueOf(ticketCm));
			pstmt2.setString(6, ticketNo);
			
			ret = pstmt2.executeUpdate();
			
			if(ret > 0)
			{
				JOptionPane.showMessageDialog(panelTicket, "정상수정 되었습니다.");
				lblMSGOutput.setForeground(Color.BLACK);
				lblMSGOutput.setText("정상수정 되었습니다.");
			}
			else
			{
				JOptionPane.showMessageDialog(panelTicket, "오류발생!");
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
			try { if(con != null) con.close();
			} catch(SQLException e1) {}
		}
	}
	
	//*어트랙션조회*//
	public void attractionAllSearch() {
		
		String noSearch = "SELECT * FROM (SELECT O.* ,  ROWNUM RNUM FROM "
				+ "(SELECT * FROM ATTRACTION WHERE 1 = 1 AND ATTRACTION_NO >= ?"
				+ "ORDER BY ATTRACTION_NO )O)"
				+"WHERE RNUM >=? AND ROWNUM <= ?";
		String[] attractionData = new String[9];
		
		try {
			dbconnect();
			pstmt = con.prepareStatement(noSearch);
			pstmt.setInt(1, Integer.valueOf(tfScondition_A.getText()));
			pstmt.setInt(2, (page - 1) * MAXCNT + 1);
			pstmt.setInt(3, MAXCNT + 1);
			
			rs = pstmt.executeQuery();
			
			int i = 0;
			
			while(true)
			{
				if(!rs.next())
				{
					btnNext_A.setEnabled(false);
					break;
				}
				
				i++;
				
				if(i > MAXCNT)
				{
					btnNext_A.setEnabled(true);
					break;
				}
				
				for(int col = 1; col <= 9; col++)
				{
					if(rs.getObject(col) == null)
					{
						attractionData[col - 1] = "";
					}
					else
					{
						attractionData[col - 1] = rs.getObject(col).toString();
					}
				}
				
				dtModel_A.addRow(attractionData);
				iScroll = SON;
			}
			
			if(i == 0)
			{
				lblMSGOutput_A.setForeground(Color.RED);
				lblMSGOutput_A.setText("오류");
			}
			else
			{
				lblMSGOutput_A.setForeground(Color.BLACK);
				lblMSGOutput_A.setText("정상 조회");
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
	
	//*어트랙션조회(구역)*//
	public void attractionArea() {
		
		String areaSearch = "SELECT * FROM (SELECT O.* ,  ROWNUM RNUM FROM "
				+ "(SELECT * FROM ATTRACTION WHERE 1 = 1 AND AREA_NO = ?"
				+ "ORDER BY ATTRACTION_NO )O)"
				+"WHERE RNUM >=? AND ROWNUM <= ?";
		String[] attractionData = new String[9];
		
		try {
			dbconnect();
			pstmt = con.prepareStatement(areaSearch);
			pstmt.setInt(1, Integer.valueOf(tfScondition_A.getText()));
			pstmt.setInt(2, (page - 1) * MAXCNT + 1);
			pstmt.setInt(3, MAXCNT + 1);
			
			rs = pstmt.executeQuery();
			
			int i = 0;
			
			while(true)
			{
				if(!rs.next())
				{
					btnNext_A.setEnabled(false);
					break;
				}
				
				i++;
				
				if(i > MAXCNT)
				{
					btnNext_A.setEnabled(true);
					break;
				}
				
				for(int col = 1; col <= 9; col++)
				{
					if(rs.getObject(col) == null)
					{
						attractionData[col - 1] = "";
					}
					else
					{
						attractionData[col - 1] = rs.getObject(col).toString();
					}
				}
				
				dtModel_A.addRow(attractionData);
				iScroll = SON;
			}
			
			if(i == 0)
			{
				lblMSGOutput_A.setForeground(Color.RED);
				lblMSGOutput_A.setText("오류");
			}
			else
			{
				lblMSGOutput_A.setForeground(Color.BLACK);
				lblMSGOutput_A.setText("정상 조회");
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
	
	//*어트랙션등록*//
	public void attractionInsert() {
		
		String no = "SELECT * FROM ATTRACTION WHERE ATTRACTION_NO = ?";
		String attractionInsert = "INSERT INTO ATTRACTION VALUES(?,?,?,?,?,?,?,?,?)";
		
		int ret;
		
		String attractionNo = tfAttractionNo.getText();
		String areaNo = tfAreaNo.getText();
		String attractionNm = tfAttractionNm.getText();
		String attractionTy = tfAttractionTy.getText();
		String attractionDc = tfAttractionDc.getText();
		String attractionIm = tfAttractionIm.getText();
		String attractionOpAt = tfOpAt.getText();
		String beginTime = tfBeginTime.getText();
		String endTime = tfEndTime.getText();
		
		if(attractionNo.isEmpty())
		{
			JOptionPane.showMessageDialog(panelAttraction, "어트랙션번호를 입력하세요.");
			lblMSGOutput_A.setText("");
			return;
		}
		if(areaNo.isEmpty())
		{
			JOptionPane.showMessageDialog(panelAttraction, "구역번호를 입력하세요.");
			lblMSGOutput_A.setText("");
			return;
		}
		if(attractionNm.isEmpty())
		{
			JOptionPane.showMessageDialog(panelAttraction, "어트랙션명을 입력하세요.");
			lblMSGOutput_A.setText("");
			return;
		}
		if(attractionDc.isEmpty())
		{
			JOptionPane.showMessageDialog(panelAttraction, "설명을 입력하세요.");
			lblMSGOutput_A.setText("");
			return;
		}
		if(attractionOpAt.isEmpty())
		{
			JOptionPane.showMessageDialog(panelAttraction, "운영여부를 입력하세요.");
			lblMSGOutput_A.setText("");
			return;
		}
		if(beginTime.isEmpty())
		{
			JOptionPane.showMessageDialog(panelAttraction, "운영시작시간을 입력하세요.");
			lblMSGOutput_A.setText("");
			return;
		}
		if(endTime.isEmpty())
		{
			JOptionPane.showMessageDialog(panelAttraction, "운영종료시간을 입력하세요.");
			lblMSGOutput_A.setText("");
			return;
		}
		
		try {
			dbconnect();
			
			pstmt = con.prepareStatement(no);
			pstmt.setInt(1, Integer.parseInt(attractionNo));
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				JOptionPane.showMessageDialog(panelAttraction, "해당번호가 존재합니다.");
				lblMSGOutput_A.setForeground(Color.RED);
				lblMSGOutput_A.setText("해당번호가 존재합니다.");
				throw new BizException();
			}
			
			pstmt2 = con.prepareStatement(attractionInsert);
			pstmt2.setInt(1, Integer.valueOf(attractionNo));
			pstmt2.setString(2, areaNo);
			pstmt2.setString(3, attractionNm);
			pstmt2.setString(4, attractionTy);
			pstmt2.setString(5, attractionDc);
			pstmt2.setString(6, attractionIm);
			pstmt2.setString(7, attractionOpAt);
			pstmt2.setString(8, beginTime);
			pstmt2.setString(9, endTime);
			
			ret = pstmt2.executeUpdate();
			
			if(ret > 0)
			{
				JOptionPane.showMessageDialog(panelAttraction, "정상등록 되었습니다.");
				lblMSGOutput_A.setForeground(Color.BLACK);
				lblMSGOutput_A.setText("정상등록");
			}
			else
			{
				JOptionPane.showMessageDialog(panelAttraction, "오류발생!");
				lblMSGOutput_A.setForeground(Color.RED);
				lblMSGOutput_A.setText("오류발생");
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
	
	//*어트랙션삭제*//
	public void attractionDelete() {
		
		String no = "SELECT * FROM ATTRACTION WHERE ATTRACTION_NO = ?";
		String attractionDelete = "DELETE FROM ATTRACTION WHERE ATTRACTION_NO = ?";
		String attractionNo = tfAttractionNo.getText();
		
		int ret;
		
		if(attractionNo.isEmpty())
		{
			JOptionPane.showMessageDialog(panelAttraction, "어트랙션번호를 입력하세요.");
			lblMSGOutput_A.setText("");
			return;
		}
		
		try {
			dbconnect();
			
			pstmt = con.prepareStatement(no);
			pstmt.setInt(1, Integer.parseInt(attractionNo));
			
			rs = pstmt.executeQuery();
			
			if(!rs.next())
			{
				JOptionPane.showMessageDialog(panelAttraction, "해당번호가 없습니다.");
				lblMSGOutput_A.setForeground(Color.RED);
				lblMSGOutput_A.setText("해당번호가 없습니다.");
				throw new BizException();
			}
			
			pstmt2 = con.prepareStatement(attractionDelete);
			pstmt2.setInt(1, Integer.valueOf(attractionNo));
			
			ret = pstmt2.executeUpdate();
			
			if(ret > 0)
			{
				JOptionPane.showMessageDialog(panelAttraction, "삭제 되었습니다.");
				lblMSGOutput_A.setForeground(Color.BLACK);
				lblMSGOutput_A.setText("정상삭제");
			}
			else
			{
				JOptionPane.showMessageDialog(panelAttraction, "오류발생!");
				lblMSGOutput_A.setForeground(Color.RED);
				lblMSGOutput_A.setText("오류발생");
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
	
	//*어트랙션수정*//
	public void attractionUpdate() {
		
		String no = "SELECT * FROM ATTRACTION WHERE ATTRACTION_NO = ?";
		String attractionUpdate = "UPDATE ATTRACTION SET AREA_NO = ?, ATTRACTION_NM = ?, "
				+ "ATTRACTION_TY = ?, ATTRACTION_DC = ?, PHOTO_COURS = ?, OPRAT_AT = ?, "
				+ "OPER_BEGIN_TIME = ?, OPER_END_TIME = ? "
				+ "WHERE ATTRACTION_NO = ? ";
		
		int ret;
		
		String attractionNo = tfAttractionNo.getText();
		String areaNo = tfAreaNo.getText();
		String attractionNm = tfAttractionNm.getText();
		String attractionTy = tfAttractionTy.getText();
		String attractionDc = tfAttractionDc.getText();
		String attractionIm = tfAttractionIm.getText();
		String attractionOpAt = tfOpAt.getText();
		String beginTime = tfBeginTime.getText();
		String endTime = tfEndTime.getText();
		
		if(attractionNo.isEmpty())
		{
			JOptionPane.showMessageDialog(panelAttraction, "어트랙션번호를 입력하세요.");
			lblMSGOutput_A.setText("");
			return;
		}
		
		try {
			dbconnect();
			
			pstmt = con.prepareStatement(no);
			pstmt.setInt(1, Integer.valueOf(attractionNo));	
			rs = pstmt.executeQuery();
			if(!rs.next())
			{
				JOptionPane.showMessageDialog(panelAttraction, "어트랙션번호가 없습니다.");
				lblMSGOutput_A.setForeground(Color.RED);
				lblMSGOutput_A.setText("어트랙션번호가 없습니다.");
				throw new BizException();
			}
			
			pstmt2 = con.prepareStatement(attractionUpdate);
			pstmt2.setString(1, String.valueOf(areaNo));
			pstmt2.setString(2, String.valueOf(attractionNm));
			pstmt2.setString(3, String.valueOf(attractionTy));
			pstmt2.setString(4, String.valueOf(attractionDc));
			pstmt2.setString(5, String.valueOf(attractionIm));
			pstmt2.setString(6, String.valueOf(attractionOpAt));
			pstmt2.setString(7, String.valueOf(beginTime));
			pstmt2.setString(8, String.valueOf(endTime));
			pstmt2.setInt(9, Integer.valueOf(attractionNo));
			
			ret = pstmt2.executeUpdate();
			
			if(ret > 0)
			{
				JOptionPane.showMessageDialog(panelAttraction, "정상수정 되었습니다.");
				lblMSGOutput_A.setForeground(Color.BLACK);
				lblMSGOutput_A.setText("정상수정 되었습니다.");
			}
			else
			{
				JOptionPane.showMessageDialog(panelAttraction, "오류발생!");
				lblMSGOutput_A.setForeground(Color.RED);
				lblMSGOutput_A.setText("오류발생!");
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
	
	//*부가시설조회*//
	public void fcltyAllSearch() {
		
		String noSearch = "SELECT * FROM (SELECT O.* ,  ROWNUM RNUM FROM "
				+ "(SELECT * FROM ADI_FCLTY WHERE 1 = 1 AND FCLTY_NO >= ?"
				+ "ORDER BY FCLTY_NO )O)"
				+"WHERE RNUM >=? AND ROWNUM <= ?";
		String[] adiData = new String[7];
		
		try {
			dbconnect();
			pstmt = con.prepareStatement(noSearch);
			pstmt.setString(1, tfScondition_B.getText());
			pstmt.setInt(2, (page - 1) * MAXCNT + 1);
			pstmt.setInt(3, MAXCNT + 1);
			
			rs = pstmt.executeQuery();
			
			int i = 0;
			
			while(true)
			{
				if(!rs.next())
				{
					btnNext_B.setEnabled(false);
					break;
				}
				
				i++;
				
				if(i > MAXCNT)
				{
					btnNext_B.setEnabled(true);
					break;
				}
				
				for(int col = 1; col <= 7; col++)
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
				
				dtModel_B.addRow(adiData);
				iScroll = SON;
			}
			
			if(i == 0)
			{
				lblMSGOutput_B.setForeground(Color.RED);
				lblMSGOutput_B.setText("오류");
			}
			else
			{
				lblMSGOutput_B.setForeground(Color.BLACK);
				lblMSGOutput_B.setText("정상 조회");
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
	
	//*부가시설조회(구역)*//
	public void fcltyArea() {
		
		String noSearch = "SELECT * FROM (SELECT O.* ,  ROWNUM RNUM FROM "
				+ "(SELECT * FROM ADI_FCLTY WHERE 1 = 1 AND AREA_NO = ?"
				+ "ORDER BY FCLTY_NO )O)"
				+"WHERE RNUM >=? AND ROWNUM <= ?";
		String[] adiData = new String[7];
		
		try {
			dbconnect();
			pstmt = con.prepareStatement(noSearch);
			pstmt.setInt(1, Integer.valueOf(tfScondition_B.getText()));
			pstmt.setInt(2, (page - 1) * MAXCNT + 1);
			pstmt.setInt(3, MAXCNT + 1);
			
			rs = pstmt.executeQuery();
			
			int i = 0;
			
			while(true)
			{
				if(!rs.next())
				{
					btnNext_B.setEnabled(false);
					break;
				}
				
				i++;
				
				if(i > MAXCNT)
				{
					btnNext_B.setEnabled(true);
					break;
				}
				
				for(int col = 1; col <= 7; col++)
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
				
				dtModel_B.addRow(adiData);
				iScroll = SON;
			}
			
			if(i == 0)
			{
				lblMSGOutput_B.setForeground(Color.RED);
				lblMSGOutput_B.setText("오류");
			}
			else
			{
				lblMSGOutput_B.setForeground(Color.BLACK);
				lblMSGOutput_B.setText("정상 조회");
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
	
	//*부가시설등록*//
	public void fcltyInsert() {
		
		String no = "SELECT * FROM ADI_FCLTY WHERE FCLTY_NO = ?";
		String fcltyInsert = "INSERT INTO ADI_FCLTY VALUES(?,?,?,?,?,?,?)";
		
		int ret;
		
		String areaNo = tfAreaNoB.getText();
		String fcltyNo = tfFcltyNo.getText();
		String fcltySe = tfFcltySe.getText();
		String fcltySeNm = tfFcltySeNm.getText();
		String fcltyNm = tfFcltyNm.getText();
		String fcltyDc = tfFcltyDc.getText();
		String fcltyIm = tfFcltyIm.getText();
		
		if(areaNo.isEmpty())
		{
			JOptionPane.showMessageDialog(panelFclty, "구역번호를 입력하세요.");
			lblMSGOutput_B.setText("");
			return;
		}
		if(fcltyNo.isEmpty())
		{
			JOptionPane.showMessageDialog(panelFclty, "시설번호를 입력하세요.");
			lblMSGOutput_B.setText("");
			return;
		}
		if(fcltySe.isEmpty())
		{
			JOptionPane.showMessageDialog(panelFclty, "시설구분을 입력하세요.");
			lblMSGOutput_B.setText("");
			return;
		}
		if(fcltySeNm.isEmpty())
		{
			JOptionPane.showMessageDialog(panelFclty, "시설구분명을 입력하세요.");
			lblMSGOutput_B.setText("");
			return;
		}
		if(fcltyNm.isEmpty())
		{
			JOptionPane.showMessageDialog(panelFclty, "시설명을 입력하세요.");
			lblMSGOutput_B.setText("");
			return;
		}
		
		try {
			dbconnect();
			
			pstmt = con.prepareStatement(no);
			pstmt.setString(1, fcltyNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				JOptionPane.showMessageDialog(panelAttraction, "해당번호가 존재합니다.");
				lblMSGOutput_A.setForeground(Color.RED);
				lblMSGOutput_A.setText("해당번호가 존재합니다.");
				throw new BizException();
			}
			
			pstmt2 = con.prepareStatement(fcltyInsert);
			pstmt2.setString(1, areaNo);
			pstmt2.setString(2, fcltyNo);
			pstmt2.setString(3, fcltySe);
			pstmt2.setString(4, fcltySeNm);
			pstmt2.setString(5, fcltyNm);
			pstmt2.setString(6, fcltyDc);
			pstmt2.setString(7, fcltyIm);
			
			ret = pstmt2.executeUpdate();
			
			if(ret > 0)
			{
				JOptionPane.showMessageDialog(panelAttraction, "정상등록 되었습니다.");
				lblMSGOutput_A.setForeground(Color.BLACK);
				lblMSGOutput_A.setText("정상등록");
			}
			else
			{
				JOptionPane.showMessageDialog(panelAttraction, "오류발생!");
				lblMSGOutput_A.setForeground(Color.RED);
				lblMSGOutput_A.setText("오류발생");
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
	
	//*부가시설삭제*//
	public void fcltyDelete() {
		
		String no = "SELECT * FROM ADI_FCLTY WHERE FCLTY_NO = ?";
		String fcltyDelete = "DELETE FROM ADI_FCLTY WHERE FCLTY_NO = ?";
		String fcltyNo = tfFcltyNo.getText();
		
		int ret;
		
		if(fcltyNo.isEmpty())
		{
			JOptionPane.showMessageDialog(panelFclty, "시설번호를 입력하세요.");
			lblMSGOutput_B.setText("");
			return;
		}
		
		try {
			dbconnect();
			
			pstmt = con.prepareStatement(no);
			pstmt.setString(1, fcltyNo);
			
			rs = pstmt.executeQuery();
			
			if(!rs.next())
			{
				JOptionPane.showMessageDialog(panelFclty, "해당번호가 없습니다.");
				lblMSGOutput_B.setForeground(Color.RED);
				lblMSGOutput_B.setText("해당번호가 없습니다.");
				throw new BizException();
			}
			
			pstmt2 = con.prepareStatement(fcltyDelete);
			pstmt2.setString(1, fcltyNo);
			
			ret = pstmt2.executeUpdate();
			
			if(ret > 0)
			{
				JOptionPane.showMessageDialog(panelFclty, "삭제 되었습니다.");
				lblMSGOutput_B.setForeground(Color.BLACK);
				lblMSGOutput_B.setText("정상삭제");
			}
			else
			{
				JOptionPane.showMessageDialog(panelFclty, "오류발생!");
				lblMSGOutput_B.setForeground(Color.RED);
				lblMSGOutput_B.setText("오류발생");
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
	
	//*부가시설수정*//
	public void fcltyUpdate() {
		
		String no = "SELECT * FROM ADI_FCLTY WHERE FCLTY_NO = ?";
		String fcltyUpdate = "UPDATE ADI_FCLTY SET AREA_NO = ?, FCLTY_SE = ?, "
				+ "FCLTY_SE_NM = ?, FCLTY_NM = ?, FCLTY_DC = ?, PHOTO_COURS = ? "
				+ "WHERE FCLTY_NO = ?";
		
		int ret;
		
		String areaNo = tfAreaNoB.getText();
		String fcltyNo = tfFcltyNo.getText();
		String fcltySe = tfFcltySe.getText();
		String fcltySeNm = tfFcltySeNm.getText();
		String fcltyNm = tfFcltyNm.getText();
		String fcltyDc = tfFcltyDc.getText();
		String fcltyIm = tfFcltyIm.getText();
		
		if(fcltyNo.isEmpty())
		{
			JOptionPane.showMessageDialog(panelFclty, "시설번호를 입력하세요.");
			lblMSGOutput_B.setText("");
			return;
		}
		
		try {
			dbconnect();
			
			pstmt = con.prepareStatement(no);
			pstmt.setString(1, fcltyNo);	
			rs = pstmt.executeQuery();
			if(!rs.next())
			{
				JOptionPane.showMessageDialog(panelFclty, "시설번호가 없습니다.");
				lblMSGOutput_B.setForeground(Color.RED);
				lblMSGOutput_B.setText("시설번호가 없습니다.");
				throw new BizException();
			}
			
			pstmt2 = con.prepareStatement(fcltyUpdate);
			pstmt2.setString(1, String.valueOf(areaNo));
			pstmt2.setString(2, String.valueOf(fcltySe));
			pstmt2.setString(3, String.valueOf(fcltySeNm));
			pstmt2.setString(4, String.valueOf(fcltyNm));
			pstmt2.setString(5, String.valueOf(fcltyDc));
			pstmt2.setString(6, String.valueOf(fcltyIm));
			pstmt2.setString(7, String.valueOf(fcltyNo));
			
			ret = pstmt2.executeUpdate();
			
			if(ret > 0)
			{
				JOptionPane.showMessageDialog(panelFclty, "정상수정 되었습니다.");
				lblMSGOutput_B.setForeground(Color.BLACK);
				lblMSGOutput_B.setText("정상수정 되었습니다.");
			}
			else
			{
				JOptionPane.showMessageDialog(panelFclty, "오류발생!");
				lblMSGOutput_B.setForeground(Color.RED);
				lblMSGOutput_B.setText("오류발생!");
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
	
	//*호텔조회*//
	public void hotelAllSearch() {
		
		String noSearch = "SELECT * FROM (SELECT O.* ,  ROWNUM RNUM FROM "
				+ "(SELECT * FROM HOTEL WHERE 1 = 1 AND HOTEL_NO >= ?"
				+ "ORDER BY HOTEL_NO )O)"
				+"WHERE RNUM >=? AND ROWNUM <= ?";
		String[] hotelData = new String[6];
		
		try {
			dbconnect();
			pstmt = con.prepareStatement(noSearch);
			pstmt.setString(1, tfScondition_C.getText());
			pstmt.setInt(2, (page - 1) * MAXCNT + 1);
			pstmt.setInt(3, MAXCNT + 1);
			
			rs = pstmt.executeQuery();
			
			int i = 0;
			
			while(true)
			{
				if(!rs.next())
				{
					btnNext_C.setEnabled(false);
					break;
				}
				
				i++;
				
				if(i > MAXCNT)
				{
					btnNext_C.setEnabled(true);
					break;
				}
				
				for(int col = 1; col <= 6; col++)
				{
					if(rs.getObject(col) == null)
					{
						hotelData[col - 1] = "";
					}
					else
					{
						hotelData[col - 1] = rs.getObject(col).toString();
					}
				}
				
				dtModel_C.addRow(hotelData);
				iScroll = SON;
			}
			
			if(i == 0)
			{
				lblMSGOutput_C.setForeground(Color.RED);
				lblMSGOutput_C.setText("오류");
			}
			else
			{
				lblMSGOutput_C.setForeground(Color.BLACK);
				lblMSGOutput_C.setText("정상 조회");
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
	
	//*호텔등록*//
	public void hotelInsert() {
		
		String no = "SELECT * FROM HOTEL WHERE HOTEL_NO = ?";
		String hotelInsert = "INSERT INTO HOTEL VALUES(?,?,?,?,?,?)";
		
		int ret;
		
		String hotelNo = tfHotelNo.getText();
		String hotelNm = tfHotelNm.getText();
		String hotelDc = tfHotelDc.getText();
		String hotelTel = tfHotelTel.getText();
		String hotelLqtx = tfHotelLqtx.getText();
		String hotelIm = tfHotelIm.getText();
		
		if(hotelNo.isEmpty())
		{
			JOptionPane.showMessageDialog(panelHotel, "호텔번호를 입력하세요.");
			lblMSGOutput_C.setText("");
			return;
		}
		
		try {
			dbconnect();
			
			pstmt = con.prepareStatement(no);
			pstmt.setString(1, hotelNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				JOptionPane.showMessageDialog(panelHotel, "해당번호가 존재합니다.");
				lblMSGOutput_C.setForeground(Color.RED);
				lblMSGOutput_C.setText("해당번호가 존재합니다.");
				throw new BizException();
			}
			
			pstmt2 = con.prepareStatement(hotelInsert);
			pstmt2.setString(1, hotelNo);
			pstmt2.setString(2, hotelNm);
			pstmt2.setString(3, hotelDc);
			pstmt2.setString(4, hotelTel);
			pstmt2.setString(5, hotelLqtx);
			pstmt2.setString(6, hotelIm);
			
			ret = pstmt2.executeUpdate();
			
			if(ret > 0)
			{
				JOptionPane.showMessageDialog(panelHotel, "정상등록 되었습니다.");
				lblMSGOutput_C.setForeground(Color.BLACK);
				lblMSGOutput_C.setText("정상등록");
			}
			else
			{
				JOptionPane.showMessageDialog(panelHotel, "오류발생!");
				lblMSGOutput_C.setForeground(Color.RED);
				lblMSGOutput_C.setText("오류발생");
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
	
	//*호텔삭제*//
	public void hotelDelete() {
		
		String no = "SELECT * FROM HOTEL WHERE HOTEL_NO = ?";
		String hotelDelete = "DELETE FROM HOTEL WHERE HOTEL_NO = ?";
		String hotelNo = tfHotelNo.getText();
		
		int ret;
		
		if(hotelNo.isEmpty())
		{
			JOptionPane.showMessageDialog(panelHotel, "호텔번호를 입력하세요.");
			lblMSGOutput_C.setText("");
			return;
		}
		
		try {
			dbconnect();
			
			pstmt = con.prepareStatement(no);
			pstmt.setString(1, hotelNo);
			
			rs = pstmt.executeQuery();
			
			if(!rs.next())
			{
				JOptionPane.showMessageDialog(panelHotel, "해당번호가 없습니다.");
				lblMSGOutput_C.setForeground(Color.RED);
				lblMSGOutput_C.setText("해당번호가 없습니다.");
				throw new BizException();
			}
			
			pstmt2 = con.prepareStatement(hotelDelete);
			pstmt2.setString(1, hotelNo);
			
			ret = pstmt2.executeUpdate();
			
			if(ret > 0)
			{
				JOptionPane.showMessageDialog(panelHotel, "삭제 되었습니다.");
				lblMSGOutput_C.setForeground(Color.BLACK);
				lblMSGOutput_C.setText("정상삭제");
			}
			else
			{
				JOptionPane.showMessageDialog(panelHotel, "오류발생!");
				lblMSGOutput_C.setForeground(Color.RED);
				lblMSGOutput_C.setText("오류발생");
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
	
	//*호텔수정*//
	public void hotelUpdate() {
		
		String no = "SELECT * FROM HOTEL WHERE HOTEL_NO = ?";
		String hotelUpdate = "UPDATE HOTEL SET HOTEL_NM = ?, HOTEL_DC = ?, "
				+ "HOTEL_TELNO = ?, HOTEL_LQTX = ?, PHOTO_COURS = ? "
				+ "WHERE HOTEL_NO = ?";
		
		int ret;
		
		String hotelNo = tfHotelNo.getText();
		String hotelNm = tfHotelNm.getText();
		String hotelDc = tfHotelDc.getText();
		String hotelTel = tfHotelTel.getText();
		String hotelLqtx = tfHotelLqtx.getText();
		String hotelIm = tfHotelIm.getText();
		
		if(hotelNo.isEmpty())
		{
			JOptionPane.showMessageDialog(panelHotel, "호텔번호를 입력하세요.");
			lblMSGOutput_C.setText("");
			return;
		}
		
		try {
			dbconnect();
			
			pstmt = con.prepareStatement(no);
			pstmt.setString(1, hotelNo);	
			rs = pstmt.executeQuery();
			if(!rs.next())
			{
				JOptionPane.showMessageDialog(panelHotel, "호텔번호가 없습니다.");
				lblMSGOutput_C.setForeground(Color.RED);
				lblMSGOutput_C.setText("호텔번호가 없습니다.");
				throw new BizException();
			}
			
			pstmt2 = con.prepareStatement(hotelUpdate);
			pstmt2.setString(1, String.valueOf(hotelNm));
			pstmt2.setString(2, String.valueOf(hotelDc));
			pstmt2.setString(3, String.valueOf(hotelTel));
			pstmt2.setString(4, String.valueOf(hotelLqtx));
			pstmt2.setString(5, String.valueOf(hotelIm));
			pstmt2.setString(6, String.valueOf(hotelNo));
			
			ret = pstmt2.executeUpdate();
			
			if(ret > 0)
			{
				JOptionPane.showMessageDialog(panelHotel, "정상수정 되었습니다.");
				lblMSGOutput_C.setForeground(Color.BLACK);
				lblMSGOutput_C.setText("정상수정 되었습니다.");
			}
			else
			{
				JOptionPane.showMessageDialog(panelHotel, "오류발생!");
				lblMSGOutput_C.setForeground(Color.RED);
				lblMSGOutput_C.setText("오류발생!");
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
			
			lblMSGOutput.setText("");
			
			if(row > -1)
			{
				tfTicketNo.setText((String)table.getValueAt(row, 0));
				tfTicketNo.setEnabled(false);
				tfPd.setText((String)table.getValueAt(row, 1));
				tfTicketNm.setText((String)table.getValueAt(row, 2));
				tfTicketDc.setText((String)table.getValueAt(row, 3));
				tfTicketIm.setText((String)table.getValueAt(row, 4));
				tfTicketCom.setText((String)table.getValueAt(row, 5));
				
				btnInsert.setEnabled(false);
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
			}
		}
		else if(e.getSource() == table_A)
		{
			JTable jtable = (JTable)e.getSource();
			row = jtable.getSelectedRow();
			
			lblMSGOutput.setText("");
			
			if(row > -1)
			{
				tfAttractionNo.setText((String)table_A.getValueAt(row, 0));
				tfAttractionNo.setEnabled(false);
				tfAreaNo.setText((String)table_A.getValueAt(row, 1));
				tfAreaNo.setEnabled(false);
				tfAttractionNm.setText((String)table_A.getValueAt(row, 2));
				tfAttractionTy.setText((String)table_A.getValueAt(row, 3));
				tfAttractionDc.setText((String)table_A.getValueAt(row, 4));
				tfAttractionIm.setText((String)table_A.getValueAt(row, 5));
				tfOpAt.setText((String)table_A.getValueAt(row, 6));
				tfBeginTime.setText((String)table_A.getValueAt(row, 7));
				tfEndTime.setText((String)table_A.getValueAt(row, 8));
				
				btnInsert_A.setEnabled(false);
				btnUpdate_A.setEnabled(true);
				btnDelete_A.setEnabled(true);
			}
		}
		else if(e.getSource() == table_B)
		{
			JTable jtable = (JTable)e.getSource();
			row = jtable.getSelectedRow();
			
			lblMSGOutput_B.setText("");
			
			if(row > -1)
			{
				tfAreaNoB.setText((String)table_B.getValueAt(row, 0));
				tfAreaNoB.setEnabled(false);
				tfFcltyNo.setText((String)table_B.getValueAt(row, 1));
				tfFcltyNo.setEnabled(false);
				tfFcltySe.setText((String)table_B.getValueAt(row, 2));
				tfFcltySeNm.setText((String)table_B.getValueAt(row, 3));
				tfFcltyNm.setText((String)table_B.getValueAt(row, 4));
				tfFcltyDc.setText((String)table_B.getValueAt(row, 5));
				tfFcltyIm.setText((String)table_B.getValueAt(row, 6));
				
				btnInsert_B.setEnabled(false);
				btnUpdate_B.setEnabled(true);
				btnDelete_B.setEnabled(true);

			}
		}
		else if(e.getSource() == table_C)
		{
			JTable jtable = (JTable)e.getSource();
			row = jtable.getSelectedRow();
			
			lblMSGOutput_C.setText("");
			
			if(row > -1)
			{
				tfHotelNo.setText((String)table_C.getValueAt(row, 0));
				tfHotelNo.setEnabled(false);
				tfHotelNm.setText((String)table_C.getValueAt(row, 1));
				tfHotelDc.setText((String)table_C.getValueAt(row, 2));
				tfHotelTel.setText((String)table_C.getValueAt(row, 3));
				tfHotelLqtx.setText((String)table_C.getValueAt(row, 4));
				tfHotelIm.setText((String)table_C.getValueAt(row, 5));
				
				btnInsert_C.setEnabled(false);
				btnUpdate_C.setEnabled(true);
				btnDelete_C.setEnabled(true);

			}
		}
		
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
		
		if(e.getSource() == cmbSelect)
		{
			if(e.getStateChange() == ItemEvent.SELECTED)
			{
				btnNext.setEnabled(false);
				
				if("티켓번호".equals(e.getItem().toString()))
				{
					iSearch = TICKET_NO;
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
		else if(e.getSource() == cmbSelect_A)
		{
			if(e.getStateChange() == ItemEvent.SELECTED)
			{
				btnNext_A.setEnabled(false);
				
				if("어트랙션번호".equals(e.getItem().toString()))
				{
					iSearch_A = ATTRACTION_NO;
				}
				else if("구역번호".equals(e.getItem().toString()))
				{
					iSearch_A = AREA_NO;
				}
				else
				{
					iSearch_A = NONE;
				}
			}
		}
		else if(e.getSource() == cmbSelect_B)
		{
			if(e.getStateChange() == ItemEvent.SELECTED)
			{
				btnNext_B.setEnabled(false);
				
				if("부가시설번호".equals(e.getItem().toString()))
				{
					iSearch_B = FCLTY_NO;
				}
				else if("구역번호".equals(e.getItem().toString()))
				{
					iSearch_B = AREA_NO_B;
				}
				else
				{
					iSearch_B = NONE;
				}
			}
		}
		else if(e.getSource() == cmbSelect_C)
		{
			if(e.getStateChange() == ItemEvent.SELECTED)
			{
				btnNext_C.setEnabled(false);
				
				if("호텔번호".equals(e.getItem().toString()))
				{
					iSearch_C = HOTEL_NO;
				}
				else
				{
					iSearch_C = NONE;
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
