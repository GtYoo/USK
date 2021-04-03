package usk;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dto.HotelDTO;
import lib.BizException;
import lib.DBController;
import lib.component.JImageButton;
import lib.component.JMultilineLabel;
import util.Common;
import util.PanelPosition;
import util.Utility;
import java.awt.Color;


public class HotelUI extends JPanel implements ActionListener , MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	
	private JPanel panel;
	private JPanel panelTitle;
	private JScrollPane scrollPane;
	private JPanel panelViewMain;
	
	private ArrayList<JImageButton> ibtnHotelList = new ArrayList<JImageButton>();
	private ArrayList<HotelDTO> hotelList;
	
	private final int MAX_COLUMN_CNT 			= 3;
	private 	  int rowCnt 					= 0;		// 출력할 행의 수
//	private 	  int cnt 						= 0;		// 출력할 어트랙션의 수
	
	private final int PADDING_X 				= 2;
	private final int PADDING_Y 				= 10;
//	private final int PANEL_TITLE_WIDTH 		= 488;	// 패널 사이즈 수정(좌우 이미지 간격 조정)
//	private final int PANEL_TITLE_HEIGHT 		= 220;
//	private final int LINE_PANEL_WIDTH			= 1018;
//	private final int LINE_PANEL_HEIGHT 		= 540;
	private final int HOTEL_PANEL_WIDTH 	= 300;	// 패널 넓이
	private final int HOTEL_PANEL_HEIGHT 	= 188;	// 패널 높이
	private final int HOTEL_NM_WIDTH 		= 168;	// 명 넓이
	private final int HOTEL_NM_HEIGHT		= 26; 	// 명 높이
	private final int HOTEL_IMAGE_HEIGHT	= 188; 	// 이미지 높이
	
	
	private HotelDetailUI hoteldetailUI;


	/**
	 * Create the panel.
	 */
	public HotelUI() {
		
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
		panelTitle.setBounds(12, 10, 1018, 80);
		viewTitle(panelTitle);
		panel.add(panelTitle);
		
		// 목록을 출력하기 위한 패널, 스크롤 설정
		scrollPane = new JScrollPane();
//		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(null);
		scrollPane.setBounds(12, 100, 1018, 501);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);		// 스크롤바 움직이는 속도 조절
//		scrollPane.getVerticalScrollBar().setValue();
		scrollPane.setMaximumSize(new Dimension(1042, 601));
		panel.add(scrollPane);


		
		// 스크롤 패널에 들어갈 패널, 실제 데이터 출력하는 패널
		panelViewMain = new JPanel();
		panelViewMain.setBackground(Color.WHITE);
		panelViewMain.setBorder(null);
		panelViewMain.setLayout(new GridLayout(rowCnt, MAX_COLUMN_CNT, 2, 20));
		
		// 목록 출력
		hotelList = selectHotel();
		viewHotel(hotelList, panelViewMain);
		

		scrollPane.setViewportView(panelViewMain);
		
		
	}
	
//	public void adminHand() {
//		
//		//* 관리자전용 메뉴들 수정삭제 동작패널 *//
//		panelAdmin = new JPanel();
//		panelAdmin.setBounds(305, 632, 1032, 87);
//		frame.getContentPane().add(panelAdmin);
//		panelAdmin.setLayout(null);
//		
//		lblSearch = new JLabel("조회");
//		lblSearch.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		lblSearch.setBounds(694, 1, 38, 32);
//		panelAdmin.add(lblSearch);
//		
//		tfSearch = new JTextField();
//		tfSearch.setBounds(736, 9, 72, 21);
//		panelAdmin.add(tfSearch);
//		tfSearch.setColumns(10);
//		
//		btnInsertAdmin = new JButton("등록");
//		btnInsertAdmin.setBounds(705, 43, 97, 23);
//		panelAdmin.add(btnInsertAdmin);
//		
//		btnDeleteAdmin = new JButton("삭제");
//		btnDeleteAdmin.setBounds(814, 43, 97, 23);
//		panelAdmin.add(btnDeleteAdmin);
//		
//		btnUpdateAdmin = new JButton("수정");
//		btnUpdateAdmin.setBounds(923, 43, 97, 23);
//		panelAdmin.add(btnUpdateAdmin);
//		
//		lblCode = new JLabel("코드");
//		lblCode.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		lblCode.setBounds(12, 10, 49, 32);
//		panelAdmin.add(lblCode);
//		
//		lblFcltyNm = new JLabel("시설명");
//		lblFcltyNm.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		lblFcltyNm.setBounds(108, 10, 49, 32);
//		panelAdmin.add(lblFcltyNm);
//		
//		lblDc = new JLabel("설명");
//		lblDc.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		lblDc.setBounds(262, 10, 49, 32);
//		panelAdmin.add(lblDc);
//		
//		tfCode = new JTextField();
//		tfCode.setColumns(10);
//		tfCode.setBounds(51, 16, 42, 21);
//		panelAdmin.add(tfCode);
//		
//		tfFcltyNm = new JTextField();
//		tfFcltyNm.setColumns(10);
//		tfFcltyNm.setBounds(162, 16, 92, 21);
//		panelAdmin.add(tfFcltyNm);
//		
//		tfDc = new JTextField();
//		tfDc.setColumns(10);
//		tfDc.setBounds(301, 16, 376, 59);
//		panelAdmin.add(tfDc);
//		
//		btnSearchAdmin = new JButton("조회");
//		btnSearchAdmin.setBounds(814, 10, 97, 23);
//		panelAdmin.add(btnSearchAdmin);
//		
//		btnClearAdmin = new JButton("초기화");
//		btnClearAdmin.setBounds(923, 10, 97, 23);
//		panelAdmin.add(btnClearAdmin);
//		
//		lblDbImage = new JLabel("이미지");
//		lblDbImage.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		lblDbImage.setBounds(12, 43, 49, 32);
//		panelAdmin.add(lblDbImage);
//		
//		tfDbImage = new JTextField();
//		tfDbImage.setColumns(10);
//		tfDbImage.setBounds(66, 51, 188, 21);
//		panelAdmin.add(tfDbImage);
//
//	}
	
	private void viewTitle(JPanel panelTitle) {
		
		// panelTitleSize = 1018, 80
		PanelPosition pos = new PanelPosition(PADDING_X, PADDING_Y);
		panelTitle.setLayout(null);
		
		JLabel lblTitle = new JLabel("호텔 ");
		lblTitle.setBounds(0, 5, 115, 75);
		lblTitle.setFont(new Font("나눔고딕", Font.BOLD, 45));
		panelTitle.add(lblTitle);
		
		pos.setX(pos.getX()+220+PADDING_X);
		
		JLabel lblSubTitle = new JLabel("Hotel");
		lblSubTitle.setBounds(120, 38, 65, 29);
		lblSubTitle.setFont(new Font("나눔고딕", Font.BOLD, 24));
		lblSubTitle.setAlignmentY(BOTTOM_ALIGNMENT);
		panelTitle.add(lblSubTitle);
	}

	// 호텔 출력
	public void viewHotel(ArrayList<HotelDTO> hotelList, JPanel panelViewMain) {

		
		HotelDTO aDTO;
		
		JPanel panelHotel;
		JMultilineLabel lblHotelNm;	// 호텔 명
		JImageButton btnHotelImage;	// 호텔 이미지

		
		PanelPosition pos = new PanelPosition(PADDING_X, 2);
		
		for (int i = 0; i < hotelList.size(); i++) {
			
			aDTO = hotelList.get(i);
			
			
			panelHotel = new JPanel();
			panelHotel.setSize(HOTEL_PANEL_WIDTH, HOTEL_PANEL_HEIGHT);
			panelHotel.setLayout(new BoxLayout(panelHotel, BoxLayout.Y_AXIS));
			panelHotel.setBackground(Color.WHITE);
			
			
			
						
			// 호텔 이미지 추가
			btnHotelImage = new JImageButton(aDTO.getPhotoCours());
			btnHotelImage.setBounds(pos.getX(), pos.getY(), HOTEL_PANEL_WIDTH, HOTEL_IMAGE_HEIGHT);	// 높이 수정, 상단의 값 설정
			ibtnHotelList.add(btnHotelImage);
			panelHotel.add(btnHotelImage);
			btnHotelImage.addActionListener(this);
			
			// 호텔 명 추가
			lblHotelNm = new JMultilineLabel(aDTO.getHotelNm());
			lblHotelNm.setBounds(pos.getX(), pos.getY(), HOTEL_NM_WIDTH, HOTEL_NM_HEIGHT);	// 넓이, 높이 수정, 상단의 값 설정
			if (aDTO.getHotelNm().length() > 20) {
				lblHotelNm.setFont(new Font("나눔고딕", Font.BOLD, 16));
			}
			else {
				lblHotelNm.setFont(new Font("나눔고딕", Font.BOLD, 20));
			}
			lblHotelNm.setAlignmentX(LEFT_ALIGNMENT);
			panelHotel.add(lblHotelNm);
			
			
			panelViewMain.add(panelHotel);
			
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		for (int i = 0; i < ibtnHotelList.size(); i++) {
			if (e.getSource() == ibtnHotelList.get(i)) {
				
				// action 처리
				
				// 선택된 DTO를 전송
				HashMap<String, Object> hm = Common.getHm();
				HotelDTO aDTO = hotelList.get(i);
				hm.put("HOTEL_DTO", aDTO);
				
//				JOptionPane.showMessageDialog(null, aDTO.getHotelNo()+"버튼클릭!");
				
				break;
			}
		}
		
		hoteldetailUI = new HotelDetailUI();
		hoteldetailUI.setBounds(0, 0, 1042, 611);
		JPanel panelCommon = (JPanel)Common.getHm().get("PANEL_COMMON");
		Utility.changePanel(panelCommon, hoteldetailUI);
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<HotelDTO> selectHotel() {
		
		ArrayList<HotelDTO> hotelList = null;
		
		if (Common.getHm().get("HOTEL_ALL") != null) {
			return (ArrayList<HotelDTO>)Common.getHm().get("HOTEL_ALL");
		}
		
		try {
			
			hotelList = new ArrayList<HotelDTO>();
			DBController.connect();
			
			String sql
			= "SELECT 	* 					"
			+ "FROM 	HOTEL 			"
			+ "ORDER BY HOTEL_NO 		";
			
			DBController.setPstmt(sql);
			
			ResultSet rs = DBController.getRs();
			
			while(rs.next()) {
				HotelDTO hotelDTO = new HotelDTO();
//				System.out.println(rs.getString(1));
				hotelDTO.setHotelNo(	rs.getString("HOTEL_NO"));
				hotelDTO.setHotelNm(	rs.getString("HOTEL_NM"));
				hotelDTO.setHotelDc(	rs.getString("HOTEL_DC"));
				hotelDTO.setHotelTelno(	rs.getString("HOTEL_TELNO"));
				hotelDTO.setHotelLqtx(	rs.getString("HOTEL_LQTX"));
				hotelDTO.setPhotoCours(	rs.getString("PHOTO_COURS"));
				
				hotelList.add(hotelDTO);
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
		
		Common.getHm().put("HOTEL_ALL", hotelList);
		return hotelList;
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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

	
	
//	public boolean insertHotel(HotelDTO hotelDTO) {
//		boolean isCommit = false;
//		
//		String sql
//		= "INSERT INTO HOTEL 														"
//		+ "( HOTEL_NO , HOTEL_NM , HOTEL_DC 	"
//		+ ", PHOTO_COURS , OPRAT_AT , OPER_BEGIN_TIME , OPER_END_TIME ) 				"
//		+ "VALUES 																		"
//		+ "( ? , ? , ? , ? , ? , ? , ? , ? , ? ) 										";
//		
//		System.out.println(sql);
//		
//		return isCommit;
//	}
	
//	public boolean modifyAttraction(AttractionDTO attractionDTO) {
//		boolean isCommit = false;
//		
//		return isCommit;
//	}
//	
//	public boolean deleteAttraction(String attractionNo) {
//		boolean isCommit = false;
//		
//		return isCommit;
//	}
}


