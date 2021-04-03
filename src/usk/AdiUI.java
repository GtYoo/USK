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

import dto.AdiFcltyDTO;
import lib.BizException;
import lib.DBController;
import lib.component.JImageButton;
import lib.component.JMultilineLabel;
import util.Common;
import util.PanelPosition;
import util.Utility;

public class AdiUI extends JPanel implements ActionListener, ItemListener{
	
	


	/**
	 * 		2021-03-18 
	 * 
	 * 		- 어트랙션 전체 출력 수정
	 */
	private static final long serialVersionUID = 5L;


//	private static final Collection<? extends AdiFcltyDTO> 
	
	
	private JPanel 		panel;
	private JPanel 		panelTitle;		// "어트랙션" 출력
	private JScrollPane scrollPane;		// 스크롤
	private JPanel 		panelViewMain;	// 어트랙션 목록을 출력할 패널

	private ArrayList<JImageButton> ibtnAdiList = new ArrayList<JImageButton>();
	private ArrayList<AdiFcltyDTO> adiList = new ArrayList<AdiFcltyDTO>();
	
	
	private final int MAX_COLUMN_CNT 			= 3;
	private 	  int rowCnt 					= 0;		// 출력할 행의 수
//	private 	  int cnt 						= 0;		// 출력할 어트랙션의 수
	
	private final int PADDING_X 				= 2;
	private final int PADDING_Y 				= 10;
	private final int PANEL_TITLE_WIDTH 		= 1018;	// 패널 사이즈 수정(좌우 이미지 간격 조정)
	private final int PANEL_TITLE_HEIGHT 		= 80;
//	private final int LINE_PANEL_WIDTH			= 1018;
//	private final int LINE_PANEL_HEIGHT 		= 540;
	private final int ADI_PANEL_WIDTH 	= 300;	// 어트랙션 패널 넓이
	private final int ADI_PANEL_HEIGHT 	= 128;	// 어트랙션 패널 높이
	private final int ADI_NM_WIDTH 		= 168;	// 어트랙션명 넓이
	private final int ADI_NM_HEIGHT		= 26; 	// 어트랙션명 높이
	private final int ADI_IMAGE_HEIGHT	= 128; 	// 어트랙션 이미지 높이
	
	private JComboBox<String> cmbSortation = new JComboBox<String>();
	private JLabel lblFacilities;

	/**
	 * Create the panel.
	 */
	public AdiUI() {
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
		viewTitle(panelTitle);
		panel.add(panelTitle);
		
		lblFacilities = new JLabel("Facilities");
		lblFacilities.setFont(new Font("나눔고딕", Font.BOLD, 24));
		lblFacilities.setAlignmentY(1.0f);
		lblFacilities.setBounds(200, 38, 114, 29);
		panelTitle.add(lblFacilities);
		
		// 구분중에서 선택한 구역에 해당하는 어트랙션만 나타냄
		cmbSortation = new JComboBox<String>();
		cmbSortation.setModel(new DefaultComboBoxModel<String>(new String[] {"--------------전체 카테고리--------------", "01. 식당", "02. 숍"}));
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
		adiList = selectAdi();
		viewAdi(adiList, panelViewMain);
//		scrollPane.getVerticalScrollBar().setValue(0);	// 최초 스크롤 위치 최상단으로(아직 미완)
		

		scrollPane.setViewportView(panelViewMain);

	}

	// 타이틀 출력
	private void viewTitle(JPanel panelTitle) {
		// panelTitleSize = 1018, 80
		PanelPosition pos = new PanelPosition(PADDING_X, PADDING_Y);
		panelTitle.setLayout(null);
		
		JLabel lblTitle = new JLabel("부가시설 ");
		lblTitle.setBounds(0, 5, 241, 70);
		lblTitle.setFont(new Font("나눔고딕", Font.BOLD, 45));
		panelTitle.add(lblTitle);
		
		pos.setX(pos.getX()+220+PADDING_X);
		
	
		
	}
	

	// 부가시설 출력
	public void viewAdi(ArrayList<AdiFcltyDTO> aList, JPanel panelViewMain) {

		
		AdiFcltyDTO aDTO;
		
		JPanel panelAdi;
		JMultilineLabel lblAdiNm;	// 부가시설명 명
		JImageButton btnAdiImage;	// 부가시설 이미지
		
		PanelPosition pos = new PanelPosition(PADDING_X, 2);
		
		for (int i = 0; i < aList.size(); i++) {
			
			aDTO = aList.get(i);
			
			// delay 중여야 함!!!!
			System.out.println(aDTO.getFcltyNo() + "어트랙션 생성");
			
			panelAdi = new JPanel();
			panelAdi.setSize(ADI_PANEL_WIDTH, ADI_PANEL_HEIGHT);
			panelAdi.setLayout(new BoxLayout(panelAdi, BoxLayout.Y_AXIS));
			panelAdi.setBackground(Color.WHITE);
			
						
			// 부가시설 이미지 추가
			btnAdiImage = new JImageButton(aDTO.getPhotoCours());
			btnAdiImage.setBounds(pos.getX(), pos.getY(), ADI_PANEL_WIDTH, ADI_IMAGE_HEIGHT);	// 높이 수정, 상단의 값 설정
			ibtnAdiList.add(btnAdiImage);
			panelAdi.add(btnAdiImage);
			btnAdiImage.addActionListener(this);
			
			
			// 부가시설 명 추가
			lblAdiNm = new JMultilineLabel(aDTO.getFcltyNm());
			lblAdiNm.setBounds(pos.getX(), pos.getY(), ADI_NM_WIDTH, ADI_NM_HEIGHT);	// 넓이, 높이 수정, 상단의 값 설정
			if (aDTO.getFcltyNm().length() > 20) {
				lblAdiNm.setFont(new Font("나눔고딕", Font.BOLD, 16));
			}
			else {
				lblAdiNm.setFont(new Font("나눔고딕", Font.BOLD, 20));
			}
			lblAdiNm.setAlignmentX(LEFT_ALIGNMENT);
			panelAdi.add(lblAdiNm);
			
			
			panelViewMain.add(panelAdi);
			
		}
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		

		for (int i = 0; i < ibtnAdiList.size(); i++) {
			if (e.getSource() == ibtnAdiList.get(i)) {
				
				// action 처리
				
				// 선택된 부가시설DTO를 전송
				HashMap<String, Object> hm = Common.getHm();
				AdiFcltyDTO aDTO = adiList.get(i);
				hm.put("ADIFCLTY_DTO", aDTO);
				
				break;
				
				
				//	JOptionPane.showMessageDialog(null, aDTO.getAreaNo()+"버튼클릭!");
				
				
			}
		}
		
		AdiDetailUI AdidetailUI = new AdiDetailUI();
		AdidetailUI.setBounds(0, 0, 1042, 611);
		
		JPanel panelCommon = (JPanel)Common.getHm().get("PANEL_COMMON");
		Utility.changePanel(panelCommon,AdidetailUI);
		
	}
	

//	private void adiView(ArrayList<AdiFcltyDTO> adiList2, JPanel panelViewMain2) {
//		// TODO Auto-generated method stub
//		
//	}

	public ArrayList<AdiFcltyDTO> selectAdi() {
		
		ArrayList<AdiFcltyDTO> adiList = null;
		
		
		try {
			
			adiList = new ArrayList<AdiFcltyDTO>();
			DBController.connect();
			
			String sql
			= "SELECT 	* 					"
			+ "FROM 	ADI_FCLTY 			"
			+ "ORDER BY FCLTY_SE 		";
			
			DBController.setPstmt(sql);
			
			ResultSet rs = DBController.getRs();
			
			while(rs.next()) {
				AdiFcltyDTO adifcltyDTO = new AdiFcltyDTO();
				System.out.println(rs.getString(1));
				adifcltyDTO.setAreaNo(	rs.getString("AREA_NO"));
				adifcltyDTO.setFcltyNo(		rs.getString("FCLTY_NO"));
				adifcltyDTO.setFcltySe(	rs.getString("FCLTY_SE"));
				adifcltyDTO.setFcltySeNm(	rs.getString("FCLTY_SE_NM"));
				adifcltyDTO.setFcltyNm(	rs.getString("FCLTY_NM"));
				adifcltyDTO.setFcltyDc(	rs.getString("FCLTY_DC"));
				adifcltyDTO.setPhotoCours(	rs.getString("PHOTO_COURS"));
				
				
				adiList.add(adifcltyDTO);
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
		
		return adiList;
		
	}
	
	
	// need to implements
	
	//식당

	public ArrayList<AdiFcltyDTO> selectAdi(String fcltySe) {
		
		ArrayList<AdiFcltyDTO> adiList = new ArrayList<AdiFcltyDTO>();
		
		
		try {
			

			DBController.connect();
			
			String sql
			= "SELECT 	* 					"
			+ "FROM 	ADI_FCLTY 			"
			+ "WHERE FCLTY_SE = ?			"
			+ "ORDER BY FCLTY_NO 		";
			
			DBController.setPstmt(sql);
			DBController.getPstmt().setString(1, fcltySe);
			ResultSet rs = DBController.getRs();
			
			while(rs.next()) {
				AdiFcltyDTO adifcltyDTO = new AdiFcltyDTO();
//				System.out.println(rs.getString(1));
				adifcltyDTO.setAreaNo(	rs.getString("AREA_NO"));
				adifcltyDTO.setFcltyNo(		rs.getString("FCLTY_NO"));
				adifcltyDTO.setFcltySe(	rs.getString("FCLTY_SE"));
				adifcltyDTO.setFcltySeNm(	rs.getString("FCLTY_SE_NM"));
				adifcltyDTO.setFcltyNm(	rs.getString("FCLTY_NM"));
				adifcltyDTO.setFcltyDc(	rs.getString("FCLTY_DC"));
				adifcltyDTO.setPhotoCours(	rs.getString("PHOTO_COURS"));
				
				
				adiList.add(adifcltyDTO);
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
		
		return adiList;
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == cmbSortation)
		{
		
			
			if(e.getStateChange() == ItemEvent.SELECTED)
			{
				panelViewMain.removeAll();
				
				String fcltySe = "" + e.getItem();
				ArrayList<AdiFcltyDTO> adi = new ArrayList<AdiFcltyDTO>();
				
				if("--------------전체 카테고리--------------".equals(fcltySe)) {
					adi = selectAdi();
				}
				else {
					fcltySe = fcltySe.substring(0, 2);
					adi = selectAdi(fcltySe);
				}
				// 기존의 어트랙션 목록을 제거 후, 다시 추가
				adiList.clear();
				ibtnAdiList.clear();
				adiList.addAll(adi);
				// 새로운 어트랙션 목록으로 다시 패널 화면 채우기
				viewAdi(adiList, panelViewMain);
				panelViewMain.revalidate();
				scrollPane.setViewportView(panelViewMain);
				
			}
		}
	}
}
		

