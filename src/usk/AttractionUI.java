package usk;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dto.AttractionDTO;
import lib.BizException;
import lib.DBController;
import lib.component.JImageButton;
import lib.component.JMultilineLabel;
import util.Common;
import util.PanelPosition;
import util.Utility;

public class AttractionUI extends JPanel implements ActionListener, ItemListener{


	/**
	 * 		2021-03-18 
	 * 
	 * 		- 어트랙션 전체 출력 수정
	 */
	private static final long serialVersionUID = 5L;
	
	
	private JPanel 		panel;
	private JPanel 		panelTitle;		// "어트랙션" 출력
	private JScrollPane scrollPane;		// 스크롤
	private JPanel 		panelViewMain;	// 어트랙션 목록을 출력할 패널

	private ArrayList<JImageButton> ibtnAttractionList = new ArrayList<JImageButton>();
	private ArrayList<AttractionDTO> attractionList = new ArrayList<AttractionDTO>();
	
	
	private final int MAX_COLUMN_CNT 			= 3;
	private 	  int rowCnt 					= 0;		// 출력할 행의 수
//	private 	  int cnt 						= 0;		// 출력할 어트랙션의 수
	
	private final int PADDING_X 				= 2;
	private final int PADDING_Y 				= 10;
	private final int PANEL_TITLE_WIDTH 		= 1018;	// 패널 사이즈 수정(좌우 이미지 간격 조정)
	private final int PANEL_TITLE_HEIGHT 		= 80;
//	private final int LINE_PANEL_WIDTH			= 1018;
//	private final int LINE_PANEL_HEIGHT 		= 540;
	private final int ATTRACTION_PANEL_WIDTH 	= 300;	// 어트랙션 패널 넓이
	private final int ATTRACTION_PANEL_HEIGHT 	= 128;	// 어트랙션 패널 높이
	private final int ATTRACTION_NM_WIDTH 		= 168;	// 어트랙션명 넓이
	private final int ATTRACTION_NM_HEIGHT		= 26; 	// 어트랙션명 높이
	private final int ATTRACTION_IMAGE_HEIGHT	= 128; 	// 어트랙션 이미지 높이
	
	private JComboBox<String> cmbSortation = new JComboBox<String>();

	/**
	 * Create the panel.
	 */
	public AttractionUI() {
		setBackground(Color.WHITE);
		
		initialize();
		
	}
	
	public void initialize() {
		
		// 현재 패널 설정들
		setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1042, 611);
		add(panel);
		panel.setLayout(null);
				
		// 패널의 타이틀을 출력할 패널
		panelTitle = new JPanel();
		panelTitle.setBackground(Color.WHITE);
		panelTitle.setBorder(null);
		panelTitle.setBounds(12, 10, PANEL_TITLE_WIDTH, PANEL_TITLE_HEIGHT);
		titleView(panelTitle);
		panel.add(panelTitle);
		
		// 구분중에서 선택한 구역에 해당하는 어트랙션만 나타냄
		cmbSortation = new JComboBox<String>();
		cmbSortation.setModel(new DefaultComboBoxModel<String>(new String[] {"--------------전체 카테고리--------------", 
				"01. 위저딩 월드 오브 해리 포터™", "02. 미니언 파크", "03. 유니버설 원더랜드", "04. 할리우드", "05. 뉴욕", "06. 샌프란시스코", "07. 쥬라기 공원", "08. 애머티 빌리지"}));
		cmbSortation.setToolTipText("");
		cmbSortation.setBounds(781, 46, 225, 21);
		panelTitle.add(cmbSortation);
		cmbSortation.addItemListener(this);;
		
		// 목록을 출력하기 위한 패널, 스크롤 설정
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(null);
		scrollPane.setBounds(12, 100, 1018, 501);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);		// 스크롤바 움직이는 속도 조절
		scrollPane.setMaximumSize(new Dimension(1042, 601));
		panel.add(scrollPane);

		
		// 스크롤 패널에 들어갈 패널, 실제 데이터 출력하는 패널
		panelViewMain = new JPanel();
		panelViewMain.setBackground(Color.WHITE);
		panelViewMain.setBorder(null);
		panelViewMain.setLayout(new GridLayout(rowCnt, MAX_COLUMN_CNT, 2, 20));
		
		// 목록 출력
		attractionList = selectAttraction();
		attractionView(attractionList, panelViewMain);
//		scrollPane.getVerticalScrollBar().setValue(0);	// 최초 스크롤 위치 최상단으로(아직 미완)
		

		scrollPane.setViewportView(panelViewMain);

	}

	// 타이틀 출력
	private void titleView(JPanel panelTitle) {
		// panelTitleSize = 1018, 80
		PanelPosition pos = new PanelPosition(PADDING_X, PADDING_Y);
		panelTitle.setLayout(null);
		
		JLabel lblTitle = new JLabel("어트랙션 ");
		lblTitle.setBounds(0, 5, 241, 70);
		lblTitle.setFont(new Font("나눔고딕", Font.BOLD, 45));
		panelTitle.add(lblTitle);
		
		pos.setX(pos.getX()+220+PADDING_X);
		
		JLabel lblSubTitle = new JLabel("Attraction");
		lblSubTitle.setBounds(200, 38, 114, 29);
		lblSubTitle.setFont(new Font("나눔고딕", Font.BOLD, 24));
		lblSubTitle.setAlignmentY(BOTTOM_ALIGNMENT);
		panelTitle.add(lblSubTitle);
		
	}
	

	// 어트랙션 출력
	public void attractionView(ArrayList<AttractionDTO> aList, JPanel panelViewMain) {

		
		AttractionDTO aDTO;
		
		JPanel panelAttraction;
		JMultilineLabel lblAttractionNm;	// 어트랙션 명
		JImageButton btnAttractionImage;	// 어트랙션 이미지
		
		PanelPosition pos = new PanelPosition(PADDING_X, 2);
		
		for (int i = 0; i < aList.size(); i++) {
			
			aDTO = aList.get(i);
			
			// delay 중여야 함!!!!
			System.out.println(aDTO.getAttractionNo() + "어트랙션 생성");
			
			panelAttraction = new JPanel();
			panelAttraction.setSize(ATTRACTION_PANEL_WIDTH, ATTRACTION_PANEL_HEIGHT);
			panelAttraction.setLayout(new BoxLayout(panelAttraction, BoxLayout.Y_AXIS));
			panelAttraction.setBackground(Color.WHITE);
			
						
			// 어트랙션 이미지 추가
			btnAttractionImage = new JImageButton(aDTO.getPhotoCours());
			btnAttractionImage.setBounds(pos.getX(), pos.getY(), ATTRACTION_PANEL_WIDTH, ATTRACTION_IMAGE_HEIGHT);	// 높이 수정, 상단의 값 설정
			ibtnAttractionList.add(btnAttractionImage);
			panelAttraction.add(btnAttractionImage);
			btnAttractionImage.addActionListener(this);
			
			
			// 어트랙션 명 추가
			lblAttractionNm = new JMultilineLabel(aDTO.getAttractionNm());
			lblAttractionNm.setBounds(pos.getX(), pos.getY(), ATTRACTION_NM_WIDTH, ATTRACTION_NM_HEIGHT);	// 넓이, 높이 수정, 상단의 값 설정
			if (aDTO.getAttractionNm().length() > 20) {
				lblAttractionNm.setFont(new Font("나눔고딕", Font.BOLD, 16));
			}
			else {
				lblAttractionNm.setFont(new Font("나눔고딕", Font.BOLD, 20));
			}
			lblAttractionNm.setAlignmentX(LEFT_ALIGNMENT);
			panelAttraction.add(lblAttractionNm);
			
			
			panelViewMain.add(panelAttraction);
			
		}
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		

		for (int i = 0; i < ibtnAttractionList.size(); i++) {
			if (e.getSource() == ibtnAttractionList.get(i)) {
				
				// action 처리
				
				// 선택된 어트랙션DTO를 전송
				HashMap<String, Object> hm = Common.getHm();
				AttractionDTO aDTO = attractionList.get(i);
				hm.put("ATTRACTION_DTO", aDTO);
				
//				JOptionPane.showMessageDialog(null, aDTO.getAttractionNo()+"버튼클릭!");
				
				break;
			}
		}
		
		AttractionDetailUI attractiondetailUI = new AttractionDetailUI();
		attractiondetailUI.setBounds(0, 0, 1042, 611);
		
		JPanel panelCommon = (JPanel)Common.getHm().get("PANEL_COMMON");
		Utility.changePanel(panelCommon, attractiondetailUI);
		
	}
	
	
	
	// need to implements
	
	public ArrayList<AttractionDTO> selectAttraction() {
		
		ArrayList<AttractionDTO> attractionList = null;
		
		
		try {
			
			attractionList = new ArrayList<AttractionDTO>();
			DBController.connect();
			
			String sql
			= "SELECT 	* 					"
			+ "FROM 	ATTRACTION 			"
			+ "ORDER BY ATTRACTION_NO 		";
			
			DBController.setPstmt(sql);
			
			ResultSet rs = DBController.getRs();
			
			while(rs.next()) {
				AttractionDTO attractionDTO = new AttractionDTO();
//				System.out.println(rs.getString(1));
				attractionDTO.setAttractionNo(	rs.getString("ATTRACTION_NO"));
				attractionDTO.setAreaNo(		rs.getString("AREA_NO"));
				attractionDTO.setAttractionNm(	rs.getString("ATTRACTION_NM"));
				attractionDTO.setAttractionTy(	rs.getString("ATTRACTION_TY"));
				attractionDTO.setAttractionDc(	rs.getString("ATTRACTION_DC"));
				attractionDTO.setPhotoCours(	rs.getString("PHOTO_COURS"));
				attractionDTO.setOpratAt(		rs.getString("OPRAT_AT").charAt(0));
				attractionDTO.setOperBeginTime(	rs.getString("OPER_BEGIN_TIME"));
				attractionDTO.setOperEndTime(	rs.getString("OPER_END_TIME"));
				
				attractionList.add(attractionDTO);
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
		
		return attractionList;
		
	}
	
	public ArrayList<AttractionDTO> selectAttraction(String areaNo) {
		
		ArrayList<AttractionDTO> aList = new ArrayList<AttractionDTO>();
		
		
		try {
			
			DBController.connect();
			
			String sql
			= "SELECT 	* 					"
			+ "FROM 	ATTRACTION 			"
			+ "WHERE 	AREA_NO = ? 		"
			+ "ORDER BY ATTRACTION_NO 		";
			
			DBController.setPstmt(sql);
			DBController.getPstmt().setString(1, areaNo);
			
			ResultSet rs = DBController.getRs();
			
			while(rs.next()) {
				AttractionDTO attractionDTO = new AttractionDTO();
//				System.out.println(rs.getString(1));
				attractionDTO.setAttractionNo(	rs.getString("ATTRACTION_NO"));
				attractionDTO.setAreaNo(		rs.getString("AREA_NO"));
				attractionDTO.setAttractionNm(	rs.getString("ATTRACTION_NM"));
				attractionDTO.setAttractionTy(	rs.getString("ATTRACTION_TY"));
				attractionDTO.setAttractionDc(	rs.getString("ATTRACTION_DC"));
				attractionDTO.setPhotoCours(	rs.getString("PHOTO_COURS"));
				attractionDTO.setOpratAt(		rs.getString("OPRAT_AT").charAt(0));
				attractionDTO.setOperBeginTime(	rs.getString("OPER_BEGIN_TIME"));
				attractionDTO.setOperEndTime(	rs.getString("OPER_END_TIME"));
				
				aList.add(attractionDTO);
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
		
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == cmbSortation)
		{
						
			if(e.getStateChange() == ItemEvent.SELECTED)
			{
				panelViewMain.removeAll();
				
				String areaNo = "" + e.getItem();
				ArrayList<AttractionDTO> aList = new ArrayList<AttractionDTO>();
				
				if("--------------전체 카테고리--------------".equals(areaNo)) {
					aList = selectAttraction();
				}
				else {
					areaNo = areaNo.substring(0, 2);
					aList = selectAttraction(areaNo);
				}
				// 기존의 어트랙션 목록을 제거 후, 다시 추가
				attractionList.clear();
				ibtnAttractionList.clear();
				attractionList.addAll(aList);
				// 새로운 어트랙션 목록으로 다시 패널 화면 채우기
				attractionView(attractionList, panelViewMain);
				panelViewMain.revalidate();
				scrollPane.setViewportView(panelViewMain);
				
			}
		}
		
	}
}
