package usk;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dto.CmmnCodeDTO;
import dto.LoginDTO;
import dto.ResveDTO;
import dto.TicketDTO;
import lib.BizException;
import lib.DBController;
import lib.component.JImageLabel;
import lib.component.JMultilineLabel;

import util.Common;
import util.Utility;
import myCalendar.CalPopup;
import myCalendar.DateDTO;

public class TicketsReservation extends JPanel implements  ActionListener ,  ChangeListener {
	
	
	private static final long serialVersionUID = 1L;
	
	private final int TICKET_DETAIL_PANEL_X_POINT 	= 34;	// 티켓상세 Panel x위치
	private final int TICKET_DETAIL_PANEL_Y_POINT 	= 124;	// 티켓상세 Panel y위치
	private final int TICKET_DETAIL_PANEL_WIDTH   	= 385;	// 티켓상세 Panel 높이
	private final int TICKET_DETAIL_PANEL_HEIGHT  	= 461;	// 티켓상세 Panel 넓이
	private final int TICKET_IMAGE_WIDTH 			= 385;
	private final int TICKET_IMAGE_HEIGHT 			= 238;
	private final int TICKET_DC_WIDTH 				= 385;
	private final int TICKET_DC_HEIGHT 				= 161;
	
	private final int ENTERANCE_DATE_PANEL_X_POINT 	= 348;	// 입장일 Panel x위치
	private final int ENTERANCE_DATE_PANEL_Y_POINT 	= 60;	// 입장일 Panel y위치
	private final int ENTERANCE_DATE_PANEL_WIDTH   	= 534;	// 입장일 Panel 높이
	private final int ENTERANCE_DATE_PANEL_HEIGHT  	= 52;	// 입장일 Panel 넓이
	
	private final int TICKET_PC_PANEL_X_POINT 		= 522;	// 티켓가격 Panel x위치
	private final int TICKET_PC_PANEL_Y_POINT 		= 152;	// 티켓가격 Panel y위치
	private final int TICKET_PC_PANEL_WIDTH   		= 336;	// 티켓가격 Panel 높이
	private final int TICKET_PC_PANEL_HEIGHT  		= 126;	// 티켓가격 Panel 넓이
	
	private final int SETLE_PC_PANEL_X_POINT 		= 522;	// 결제가격 Panel x위치
	private final int SETLE_PC_PANEL_Y_POINT 		= 320;	// 결제가격 Panel y위치
	private final int SETLE_PC_PANEL_WIDTH   		= 336;	// 결제가격 Panel 높이
	private final int SETLE_PC_PANEL_HEIGHT  		= 161;	// 결제가격 Panel 넓이
	
	private final int BUTTON_PANEL_X_POINT 			= 682;	// 버튼 Panel x위치
	private final int BUTTON_PANEL_Y_POINT 			= 512;	// 버튼 Panel y위치
	private final int BUTTON_PANEL_WIDTH   			= 263;	// 버튼 Panel 높이
	private final int BUTTON_PANEL_HEIGHT  			= 42;	// 버튼 Panel 넓이
	

	// 패널 내에서 사용하는 코드
	private final int 		ADULT 	= 1;
	private final int 		CHILD 	= 2;
	private final int 		OLD 	= 3;
	

	private JPanel 			panelContents;
	private JLabel 			lblTitle;

	private JPanel 			panelTicketDetail;		// 티켓 정보 Panel
	private JLabel 			lblTicketNm;			// 티켓명
	private JImageLabel 	lblTicketImage;			// 티켓 사진
	private JMultilineLabel	lblTicketDc; 			// 티켓 설명

	private JPanel 			panelEnteranceDate;		// 날짜 정보 Panel
	private JButton 		btnEffectiveStartDate;	// 시작 날짜 버튼
	private JTextField 		tfEffectiveStartDate; 	// 시작 날짜 출력
	private JLabel 			lblNewLabel_1;			// ~
	private JButton 		btnEffectiveDeadline;	// 마지막 날짜 버튼
	private JTextField 		tfEffectiveDeadline;	// 마지막 날짜 출력
	

	private JPanel 			panelTicketCount;		// 티켓 가격 정보 Panel
	private JPanel 			panelAdult;				// 	성인 Panel
	private JLabel 			lblAdult;
	private JLabel 			lblAdultPc;
	private JSpinner 		spAdult;
	private JPanel 			panelChild;				// 	어린이 Panel
	private JLabel 			lblChild;
	private JLabel 			lblChildPc;
	private JSpinner 		spChild;
	private JPanel 			panelOld;				// 	노인 Panel
	private JLabel 			lblOld;
	private JLabel 			lblOldPc;
	private JSpinner 		spOld;
	

	private JPanel 			panelPc;				// 가격 정보 Panel
	private JLabel 			lblPcTitle;	
	private JImageLabel		lblHorizontalLine;		// 수평선
	private JLabel 			lblTotalPc; 			// 합계 금액
	
	private JPanel 			panelButton;			// 버튼 Panel
	private JButton 		btnPrevious;
	private JButton 		btnNext;
	
	
	DateDTO dateDTO = new DateDTO();	//달력 jar 임폴트후 빌드패스 확인할것
	
	ArrayList<CmmnCodeDTO> 	cmmnCodeList;
	
	

	/**
	 * Create the panel.
	 */
	
	public TicketsReservation() {
		setLayout(null);
		setBounds(0, 0, 1042, 611);
		
		panelContents = new JPanel();
		panelContents.setBounds(0, 0, 1042, 611);
		add(panelContents);
		panelContents.setLayout(null);
		panelContents.setBackground(Color.WHITE);
		
		// 사용자가 선택한 티켓 가져오기
		TicketDTO ticketDTO = (TicketDTO)Common.getHm().get("TICKET_DTO");
		Common.getHm().put("TICKET_NO", ticketDTO.getTicketNo());
//		Common.getHm().remove("TICKET_DTO");
		
		
		// 화면 타이틀 출력
		lblTitle = new JLabel("티켓예약");
		lblTitle.setFont(new Font("나눔고딕", Font.BOLD, 24));
		lblTitle.setBounds(34, 23, 150, 29);
		panelContents.add(lblTitle);
		

		// 티켓 정보 Panel
		panelTicketDetail = new JPanel();
		panelTicketDetail.setBounds(TICKET_DETAIL_PANEL_X_POINT, TICKET_DETAIL_PANEL_Y_POINT, TICKET_DETAIL_PANEL_WIDTH, TICKET_DETAIL_PANEL_HEIGHT);
		panelContents.add(panelTicketDetail);
		GridBagLayout gbl_panelTicketDetail = new GridBagLayout();
		gbl_panelTicketDetail.columnWidths = new int[]{385};
		gbl_panelTicketDetail.rowHeights = new int[]{80, 216, 80, 0};
		gbl_panelTicketDetail.columnWeights = new double[]{1.0};
		gbl_panelTicketDetail.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelTicketDetail.setLayout(gbl_panelTicketDetail);
		panelTicketDetail.setBackground(Color.WHITE);
				
		// 티켓명 출력
		lblTicketNm = new JLabel(ticketDTO.getTicketNm());
		lblTicketNm.setFont(new Font("굴림", Font.BOLD, 20));
		lblTicketNm.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblTicketNm = new GridBagConstraints();
		gbc_lblTicketNm.fill = GridBagConstraints.BOTH;
		gbc_lblTicketNm.insets = new Insets(0, 0, 5, 5);
		gbc_lblTicketNm.gridx = 0;
		gbc_lblTicketNm.gridy = 0;
		panelTicketDetail.add(lblTicketNm, gbc_lblTicketNm);
		
		// 티켓 사진 출력
		lblTicketImage = new JImageLabel(ticketDTO.getPhotoCours(), TICKET_IMAGE_WIDTH, TICKET_IMAGE_HEIGHT);
		GridBagConstraints gbc_lblImage = new GridBagConstraints();
		gbc_lblImage.insets = new Insets(0, 0, 5, 5);
		gbc_lblImage.gridx = 0;
		gbc_lblImage.gridy = 1;
		panelTicketDetail.add(lblTicketImage, gbc_lblImage);
		// 티켓 설명 출력
		lblTicketDc = new JMultilineLabel(Utility.changeSpecialChar(ticketDTO.getTicketDc()));
		lblTicketDc.setFont(new Font("굴림", Font.PLAIN, 18));
		lblTicketDc.setSize(new Dimension(TICKET_DC_WIDTH, TICKET_DC_HEIGHT));
		GridBagConstraints gbc_lblTicketDc = new GridBagConstraints();
		gbc_lblTicketDc.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblTicketDc.insets = new Insets(0, 0, 0, 5);
		gbc_lblTicketDc.gridx = 0;
		gbc_lblTicketDc.gridy = 2;
		panelTicketDetail.add(lblTicketDc, gbc_lblTicketDc);
		
		
		
		// 날짜 정보 Panel(교수님 달력, 추가할 것!)
		panelEnteranceDate = new JPanel();
		panelEnteranceDate.setBounds(ENTERANCE_DATE_PANEL_X_POINT, ENTERANCE_DATE_PANEL_Y_POINT, ENTERANCE_DATE_PANEL_WIDTH, ENTERANCE_DATE_PANEL_HEIGHT);
		panelContents.add(panelEnteranceDate);
		panelEnteranceDate.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		panelEnteranceDate.setBackground(Color.WHITE);
		
		btnEffectiveStartDate = new JButton("입장일");
		panelEnteranceDate.add(btnEffectiveStartDate);
		btnEffectiveStartDate.addActionListener(this);
		
		tfEffectiveStartDate = new JTextField();
		panelEnteranceDate.add(tfEffectiveStartDate);
		tfEffectiveStartDate.setColumns(10);
		
		lblNewLabel_1 = new JLabel("~");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panelEnteranceDate.add(lblNewLabel_1);
		
		btnEffectiveDeadline = new JButton("유효마감일");
		panelEnteranceDate.add(btnEffectiveDeadline);
		btnEffectiveDeadline.addActionListener(this);
		
		tfEffectiveDeadline = new JTextField();
		panelEnteranceDate.add(tfEffectiveDeadline);
		tfEffectiveDeadline.setColumns(10);
		
		
		
		// 티켓 가격 정보 Panel
		panelTicketCount = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelTicketCount.getLayout();
		flowLayout.setHgap(20);
		panelTicketCount.setBounds(TICKET_PC_PANEL_X_POINT, TICKET_PC_PANEL_Y_POINT, TICKET_PC_PANEL_WIDTH, TICKET_PC_PANEL_HEIGHT);
		panelContents.add(panelTicketCount);
		panelTicketCount.setBackground(Color.WHITE);
		
		// 티켓 가격 DB에서 가져오기
		cmmnCodeList = this.getTicketPc(ticketDTO.getTicketNo());
		// 스피너 모델 생성
		int val = 0;
		SpinnerModel modelAdult = new SpinnerNumberModel(val, val, val+50, 1);
		SpinnerModel modelChild = new SpinnerNumberModel(val, val, val+50, 1);
		SpinnerModel modelOld = new SpinnerNumberModel(val, val, val+50, 1);
		
		// 성인, 어린이, 노인 티켓 가격
		panelAdult = new JPanel();
		panelTicketCount.add(panelAdult);
		panelAdult.setLayout(new GridLayout(0, 1, 10, 20));
		panelAdult.setBackground(Color.WHITE);
		
		panelChild = new JPanel();
		panelTicketCount.add(panelChild);
		panelChild.setLayout(new GridLayout(0, 1, 10, 20));
		panelChild.setBackground(Color.WHITE);
		
		panelOld = new JPanel();
		panelTicketCount.add(panelOld);
		panelOld.setLayout(new GridLayout(0, 1, 10, 20));
		panelOld.setBackground(Color.WHITE);
		
		int pc;
		switch (cmmnCodeList.size()-1) {
		case OLD:
			// 티켓 구분
			lblOld = new JLabel("노인");
			lblOld.setFont(new Font("굴림", Font.PLAIN, 18));
			panelOld.add(lblOld);
			lblOld.setHorizontalAlignment(SwingConstants.CENTER);
			// 구분별 가격
			pc = cmmnCodeList.get(OLD).getNumber();
			lblOldPc = new JLabel("￦"+String.valueOf(pc));
			panelOld.add(lblOldPc);
			// 스피너 모델 추가
			spOld = new JSpinner(modelOld);
			panelOld.add(spOld);
			spOld.addChangeListener(this);
		case CHILD:
			// 티켓 구분
			lblChild = new JLabel("어린이");
			lblChild.setFont(new Font("굴림", Font.PLAIN, 18));
			panelChild.add(lblChild);
			lblChild.setHorizontalAlignment(SwingConstants.CENTER);
			// 구분별 가격
			pc = cmmnCodeList.get(CHILD).getNumber();
			lblChildPc = new JLabel("￦"+String.valueOf(pc));
			panelChild.add(lblChildPc);
			// 스피너 모델 추가
			spChild = new JSpinner(modelChild);
			panelChild.add(spChild);
			spChild.addChangeListener(this);
		case ADULT:
			// 티켓 구분
			lblAdult = new JLabel("어른");
			lblAdult.setFont(new Font("굴림", Font.PLAIN, 18));
			panelAdult.add(lblAdult);
			lblAdult.setHorizontalAlignment(SwingConstants.CENTER);
			// 구분별 가격
			pc = cmmnCodeList.get(ADULT).getNumber();
			lblAdultPc = new JLabel("￦"+String.valueOf(pc));
			panelAdult.add(lblAdultPc);
			// 스피너 모델 추가
			spAdult = new JSpinner(modelAdult);
			panelAdult.add(spAdult);
			spAdult.addChangeListener(this);
		default:
			break;
		}
		
		
		// 결제 가격 정보 Panel
		panelPc = new JPanel();
		panelPc.setBounds(SETLE_PC_PANEL_X_POINT, SETLE_PC_PANEL_Y_POINT, SETLE_PC_PANEL_WIDTH, SETLE_PC_PANEL_HEIGHT);
		panelContents.add(panelPc);
		panelPc.setLayout(null);
		panelPc.setBackground(Color.WHITE);
		
		lblPcTitle = new JLabel("결제 금액");
		lblPcTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblPcTitle.setFont(new Font("굴림", Font.PLAIN, 20));
		lblPcTitle.setBounds(55, 12, 230, 55);
		panelPc.add(lblPcTitle);
		
		lblHorizontalLine = new JImageLabel("img/form/horizontal_line.png", 308, 18);
		lblHorizontalLine.setBounds(14, 64, 308, 18);
		panelPc.add(lblHorizontalLine);
		
		// 결제 금액 계산
		int totalPc = calcTotalTicketPc();
		// 총결제 금액 출력
		lblTotalPc = new JLabel("￦"+String.valueOf(totalPc));
		lblTotalPc.setFont(new Font("굴림", Font.BOLD, 20));
		lblTotalPc.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalPc.setBounds(24, 79, 298, 70);
		panelPc.add(lblTotalPc);
		
		
		
		// 화면 전환 버튼 Panel
		panelButton = new JPanel();
		panelButton.setBounds(BUTTON_PANEL_X_POINT, BUTTON_PANEL_Y_POINT, BUTTON_PANEL_WIDTH, BUTTON_PANEL_HEIGHT);
		panelButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		panelContents.add(panelButton);
		panelButton.setBackground(Color.WHITE);
		
		btnPrevious = new JButton("이전");
		panelButton.add(btnPrevious);
		btnPrevious.addActionListener(this);
		
		btnNext = new JButton("다음");
		panelButton.add(btnNext);
		btnNext.addActionListener(this);
		
		
		
	}
	
	
	// 총 결제 가격 계산하기
	private int calcTotalTicketPc() {
		
		int totalPc = 0;
		
		totalPc = (int)spAdult.getValue()*cmmnCodeList.get(ADULT).getNumber();
		if (cmmnCodeList.size()-1 >= CHILD) {
			totalPc = totalPc + (int)spChild.getValue()*cmmnCodeList.get(CHILD).getNumber();
		}
		if (cmmnCodeList.size()-1 >= OLD) {
			totalPc = totalPc + (int)spOld.getValue()*cmmnCodeList.get(OLD).getNumber();
		}
		
		return totalPc;
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnNext)
		{	
			
			ArrayList<ResveDTO> resveList = new ArrayList<ResveDTO>();
//			int index = 0;
			// 예약할 티켓들 구분별 수량만큼 resveDTO생성
			
				
			// 성인 예약 DTO 생성
			if (cmmnCodeList.size() > ADULT) {
				for (int i = 0; i < (Integer)spAdult.getValue(); i++) {
					createResveDTO(resveList, ADULT);
				}
			}
			
			// 어린이 예약 DTO 생성
			if (cmmnCodeList.size() > CHILD) {
				for (int i = 0; i < (Integer)spChild.getValue(); i++) {
					createResveDTO(resveList, CHILD);
				}
			}
			
			// 노인 예약 DTO 생성
			if (cmmnCodeList.size() > OLD) {
				for (int i = 0; i < (Integer)spOld.getValue(); i++) {
					createResveDTO(resveList, OLD);
				}
				Common.getHm().remove("TICKET_NO");		
					
			}
			
			// 예약 리스트 DB에 insert
			for (int i = 0 ; i < resveList.size(); i++) {
				insertReservation(resveList.get(i));
			}
			
			
			ReservationConfirmationList reservationConfirmationList = new ReservationConfirmationList();
			reservationConfirmationList.setBounds(0, 0, 1042, 611);
			
			JPanel panelCommon = (JPanel)Common.getHm().get("PANEL_COMMON");
			Utility.changePanel(panelCommon, reservationConfirmationList);
				
		}//end-of-if(btnNext)
		else if(e.getSource() == btnPrevious)
		{
//			System.exit(0);
			
			BeforeReservation beforeReservation = new BeforeReservation();
			beforeReservation.setBounds(0, 0, 1042, 611);
			
			JPanel panelCommon = (JPanel)Common.getHm().get("PANEL_COMMON");
			Utility.changePanel(panelCommon, beforeReservation);
		
		}
		//* 달력  *//
		else if(e.getSource() == btnEffectiveStartDate)
		{
//			DateDTO dateDTO = new DateDTO();
			new CalPopup(980, 365,  null, dateDTO);
			tfEffectiveStartDate.setText(dateDTO.getDateR());
		}
		
	}
	
	public void createResveDTO(ArrayList<ResveDTO> resveList, int CODE) {
		ResveDTO resveDTO = new ResveDTO();
//		// 예약번호 	(insert할때, SEQ번호 사용)
		// 회원번호
		LoginDTO loginDTO = (LoginDTO)Common.getHm().get("LoginDTO");
		resveDTO.setMberNo(getMberNo(loginDTO.getLoginID()));	// 아직 대기, 로그인 했을 때, 회원 번호
		// 티켓번호
		resveDTO.setTicketNo((String)Common.getHm().get("TICKET_NO"));
		// 공통코드 - 티켓번호+구분코드 10:성인, 20:어린이, 30:노인
		// CODE = 성인 1 || 어린이 2 || 노인 3
		resveDTO.setCmmnCode(cmmnCodeList.get(CODE).getCmmnCode());
		// 예약일, DB날짜 적용할 것!
		resveDTO.setResveDe(getToday());
		// 입장일
//		String str = tfEffectiveStartDate.getText();
//		Utility.changeSpecialChar(str, "-", "");				// 화면 출력 포멧에 따라 달라짐
//		String str = tfEffectiveStartDate.getText();
//		Utility.changeSpecialChar(str, "-", "");				// 화면 출력 포멧에 따라 달라짐
		resveDTO.setEntncDe(tfEffectiveStartDate.getText());
//		resveDTO.setEntncDe(tfEffectiveStartDate.setText(dateDTO.getDateV()));
		// 결제 여부 - 예약 시 무조건 'N'로 세팅
		resveDTO.setSetleAt("N".charAt(0));
		
		resveList.add(resveDTO);
	}
	
	
	public String getToday() {
		String today = "";
		
		try {
			
			DBController.connect();
			
			String sql
			= "SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') TODAY FROM DUAL";
			
			DBController.setPstmt(sql);
			
			
			ResultSet rs = DBController.select();
			if(rs.next()) {
				today = rs.getString("TODAY");
			}
			
			DBController.close();
			
		} catch (BizException e) {
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return today;
	}

	
	// 회원번호 가져오기
	public String getMberNo(String mberId) {
		String mberNo = "";
		
		try {
			
			DBController.connect();
			
			String sql
			= "SELECT MBER_NO FROM MBER WHERE MBER_ID = ?";
			
			DBController.setPstmt(sql);
			DBController.getPstmt().setString(1, mberId);
			
			
			ResultSet rs = DBController.select();
			if(rs.next()) {
				mberNo = rs.getString("MBER_NO");
			}
			
			DBController.close();
			
		} catch (BizException e) {
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return mberNo;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource() == spAdult || e.getSource() == spChild || e.getSource() == spOld)
		{
			// 결제 금액 계산
			int totalPc = calcTotalTicketPc();
			// 총결제 금액 출력
			lblTotalPc.setText("\\"+String.valueOf(totalPc));
		}
	}
	

	// 티켓 가격 가져오기
	public ArrayList<CmmnCodeDTO> getTicketPc(String ticketNo){
		ArrayList<CmmnCodeDTO> cmmnList = new ArrayList<CmmnCodeDTO>();
		
		try {
			
			DBController.connect();
			
			String sql
			= "SELECT 	* 					"
			+ "FROM 	CMMN_CODE			"
			+ "WHERE 	1=1 				"
			+ "AND 		COMMON_CODE LIKE ? 	";
			
			DBController.setPstmt(sql);
			DBController.getPstmt().setString(1, ticketNo+"%");
			
			ResultSet rs = DBController.select();
			while(rs.next()) {
				CmmnCodeDTO cmmnCodeDTO = new CmmnCodeDTO();
				System.out.println(rs.getString(1));
				cmmnCodeDTO.setCmmnCode(	rs.getString("COMMON_CODE"));
				cmmnCodeDTO.setCmmnCodeNm(	rs.getString("CODE_NM"));
				cmmnCodeDTO.setNumber(		rs.getInt("PC"));
				
				cmmnList.add(cmmnCodeDTO);
			}
			
			DBController.close();
			
		} catch (BizException e) {
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cmmnList;
	}
	
	
	
	
	// 예약 insert
	public void insertReservation(ResveDTO resveDTO){
		
		try {
			
			DBController.connect();
			
			String sql
			= "INSERT INTO 	RESVE										"
			+ "VALUES (RESVE_NO_SEQ.NEXTVAL , ? , ? , ? , ? , ? , ?)	";
			
			DBController.setPstmt(sql);
			DBController.getPstmt().setString(1, resveDTO.getMberNo());
			DBController.getPstmt().setString(2, resveDTO.getTicketNo());
			DBController.getPstmt().setString(3, resveDTO.getCmmnCode());
			DBController.getPstmt().setString(4, resveDTO.getResveDe());
			DBController.getPstmt().setString(5, resveDTO.getEntncDe());
			DBController.getPstmt().setString(6, String.valueOf(resveDTO.getSetleAt()));
			
			
			int rs = DBController.insert();
			if(rs > 0) {
				System.out.println("등록 완료!");
			}
			
			DBController.close();
			
		} catch (BizException e) {
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
