package usk;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import dto.CmmnCodeDTO;
import dto.LoginDTO;
import dto.ResveDTO;
import dto.TicketDTO;
import lib.BizException;
import lib.DBController;
import lib.component.JImageLabel;
import util.Common;
import util.Utility;

public class ReservationConfirmationList extends JPanel implements ActionListener, ItemListener{
	
	/*
	 * 	2021-03-20
	 * 
	 *  - 결제 가격 오류 수정
	 */
	
	private static final long serialVersionUID = 3L;
	
	
	private JPanel 								panel;
	private JScrollPane 						scrollPane;
	private JPanel 								panelContents;
	private JPanel 								panelTitle;
	private JLabel 								lblTitle;
	private JPanel 								panelReservationList;
	private JLabel 								lblResveDe;
	private JLabel 								lblEntncDe;
	private JLabel 								lblTicketImage;
	private JLabel 								lblTicketSe;
	private JLabel 								lblTicketCnt;
	private JLabel 								lblTicketPc;
	private JPanel 								panelButtons;
	private JButton 							btnSetle;
	private JButton 							btnGoToMain;
	private JButton 							btnCancelReservation;
	
	private final int 							LEFT_PADDING_X 		= 10;
	private final int 							BUTTON_WIDTH 		= 120;
	private final int 							BUTTON_HEIGHT 		= 42;
	private final int 							TICKET_IMAGE_WIDTH 	= 230;
	private final int 							TICKET_IMAGE_HEIGHT	= 145;
	
	private int 								resveListGridY = 0;
	
	private final int 							TICKET_NO_LENGTH = 2;
	
	private ArrayList<ResveDTO> 				resveList;		// 회원용 예약 목록
	private ArrayList<JCheckBox> 				cbList; 		// 예약 목록 가져올 때 생성 : 전달할 예약 리스트 골라낼 때 사용
	private ArrayList<HashMap<String, Object>> 	chkResveList = new ArrayList<HashMap<String, Object>>();;	// 예약 목록 가져올 때 생성 : 결제, 예약 취소할 때 사용
	private ArrayList<CmmnCodeDTO> 				cmmnCodeList;
	private ArrayList<TicketDTO> 				ticketList;
	private JLabel lblNewLabel;
	

	/**
	 * Create the panel.
	 */
	public ReservationConfirmationList(){

		
		setLayout(null);
		setBorder(null);
		setBounds(0, 0, 1042, 611);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 1042, 611);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		add(panel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1042, 611);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		
		ImageIcon setBar = new ImageIcon("img/resve/setBar.png");
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(50, 135, 855, 7);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(setBar);
		panel.add(scrollPane);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		panelContents = new JPanel();
		panelContents.setBounds(0, 0, 1042, 611);
		panelContents.setBackground(Color.WHITE);
		GridBagLayout gbl_panelContents = new GridBagLayout();
		gbl_panelContents.columnWidths = new int[] 	{ 1018 };
		gbl_panelContents.rowHeights = new int[] {30, 64, 450, 30};
		gbl_panelContents.columnWeights = new double[]{1.0};
		gbl_panelContents.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelContents.setLayout(gbl_panelContents);
		scrollPane.setViewportView(panelContents);
		
		
		
		panelTitle = new JPanel();
		panelTitle.setLayout(null);
		panelTitle.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelTitle = new GridBagConstraints();
		gbc_panelTitle.insets = new Insets(LEFT_PADDING_X, 0, 5, 5);
		gbc_panelTitle.fill = GridBagConstraints.BOTH;
		gbc_panelTitle.gridx = 0;
		gbc_panelTitle.gridy = 1;
		panelContents.add(panelTitle, gbc_panelTitle);
		
		lblTitle = new JLabel("예약목록");
		lblTitle.setFont(new Font("굴림", Font.BOLD, 24));
		lblTitle.setBounds(LEFT_PADDING_X*3, 0, 960, 59);
		panelTitle.add(lblTitle);
		
		
		panelReservationList = new JPanel();
		panelReservationList.setBackground(Color.WHITE);
		GridBagConstraints gbc_paneReservationList = new GridBagConstraints();
		gbc_paneReservationList.insets = new Insets(0, 0, 5, 5);
		gbc_paneReservationList.fill = GridBagConstraints.BOTH;
		gbc_paneReservationList.gridx = 0;
		gbc_paneReservationList.gridy = 2;
		panelContents.add(panelReservationList, gbc_paneReservationList);
		GridBagLayout gbl_paneReservationList = new GridBagLayout();
		gbl_paneReservationList.columnWidths = new int[] {51, 80, 100, TICKET_IMAGE_WIDTH, 127, 126, 40}; // 51, 80, 100, 282, 20, 20, 40
		gbl_paneReservationList.rowHeights = new int[] {45, TICKET_IMAGE_HEIGHT, TICKET_IMAGE_HEIGHT, TICKET_IMAGE_HEIGHT, 30};
		gbl_paneReservationList.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_paneReservationList.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelReservationList.setLayout(gbl_paneReservationList);
		
		lblResveDe = new JLabel("예약일");
		lblResveDe.setFont(new Font("굴림", Font.PLAIN, 14));
		GridBagConstraints gbc_lblMberNo = new GridBagConstraints();
		gbc_lblMberNo.insets = new Insets(LEFT_PADDING_X, 0, 0, 5);
		gbc_lblMberNo.gridx = 1;
		gbc_lblMberNo.gridy = resveListGridY;// resveListGridY = 0
		panelReservationList.add(lblResveDe, gbc_lblMberNo);
		
		lblEntncDe = new JLabel("입장예정일");
		lblEntncDe.setFont(new Font("굴림", Font.PLAIN, 14));
		GridBagConstraints gbc_lblEntncDe = new GridBagConstraints();
		gbc_lblEntncDe.insets = new Insets(LEFT_PADDING_X, 0, 0, 5);
		gbc_lblEntncDe.gridx = 2;
		gbc_lblEntncDe.gridy = resveListGridY;// resveListGridY = 0
		panelReservationList.add(lblEntncDe, gbc_lblEntncDe);
		
		lblTicketImage = new JLabel("티켓종류");
		lblTicketImage.setFont(new Font("굴림", Font.PLAIN, 14));
		GridBagConstraints gbc_lblTicketImage = new GridBagConstraints();
		gbc_lblTicketImage.insets = new Insets(LEFT_PADDING_X, 0, 0, 5);
		gbc_lblTicketImage.gridx = 3;
		gbc_lblTicketImage.gridy = resveListGridY;// resveListGridY = 0
		panelReservationList.add(lblTicketImage, gbc_lblTicketImage);
		
		lblTicketSe = new JLabel("구분");
		lblTicketSe.setFont(new Font("굴림", Font.PLAIN, 14));
		GridBagConstraints gbc_lblTicketSe = new GridBagConstraints();
		gbc_lblTicketSe.insets = new Insets(LEFT_PADDING_X, 0, 0, 5);
		gbc_lblTicketSe.gridx = 4;
		gbc_lblTicketSe.gridy = resveListGridY;// resveListGridY = 0
		panelReservationList.add(lblTicketSe, gbc_lblTicketSe);
		
		lblTicketCnt = new JLabel("매수");
		lblTicketCnt.setFont(new Font("굴림", Font.PLAIN, 14));
		GridBagConstraints gbc_lblTicketCnt = new GridBagConstraints();
		gbc_lblTicketCnt.insets = new Insets(LEFT_PADDING_X, 0, 0, 5);
		gbc_lblTicketCnt.gridx = 5;
		gbc_lblTicketCnt.gridy = resveListGridY;// resveListGridY = 0
		panelReservationList.add(lblTicketCnt, gbc_lblTicketCnt);
		
		lblTicketPc = new JLabel("가격");
		lblTicketPc.setFont(new Font("굴림", Font.PLAIN, 14));
		GridBagConstraints gbc_lblTicketPc = new GridBagConstraints();
		gbc_lblTicketPc.insets = new Insets(10, 0, 0, 0);
		gbc_lblTicketPc.gridx = 6;
		gbc_lblTicketPc.gridy = resveListGridY;// resveListGridY = 0
		panelReservationList.add(lblTicketPc, gbc_lblTicketPc);
		
		resveListGridY++; //resveListGridY = 1~ : 예약 출력
		
		// 예약 리스트 출력
		//	회원번호 가져오기
		LoginDTO loginDTO = (LoginDTO)Common.getHm().get("LoginDTO");
		String mberNo = Utility.getMberNo(loginDTO.getLoginID());
		resveList = selectResveList(mberNo);
				
		
		if (resveList.size() == 0) {
			// 	예약 0건 일 경우
			JOptionPane.showMessageDialog(panel, "조회된 예약 정보가 없습니다.");
		}
		else {
			// 	회원번호에 해당하는 예약 리스트 출력
			resveListView(resveList, panelReservationList);
		}
		
		
		resveListGridY++; // resveListGridY = 마지막 예약한 행 다음에 추가/예약 건수가 없을 땐 4로 설정(마지막 행의 높이 30)
		if (resveListGridY < 4) {
			resveListGridY = 4;
		}
		
		// 버튼 패널 추가
		panelButtons = new JPanel();
		panelButtons.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panelButtons.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		GridBagConstraints gbc_panelButtons = new GridBagConstraints();
		gbc_panelButtons.insets = new Insets(LEFT_PADDING_X, 0, 0, 5);
		gbc_panelButtons.fill = GridBagConstraints.BOTH;
		gbc_panelButtons.gridx = 0;
		gbc_panelButtons.gridy = resveListGridY;// 4행부터 시작해서 티켓이 끝난 행 다음 행
		panelContents.add(panelButtons, gbc_panelButtons);
		
		btnSetle = new JButton("결제");
		btnSetle.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		panelButtons.add(btnSetle);
		btnSetle.addActionListener(this);
		
		btnGoToMain = new JButton("확인");
		btnGoToMain.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		panelButtons.add(btnGoToMain);
		btnGoToMain.addActionListener(this);
		
		btnCancelReservation = new JButton("예약취소");
		btnCancelReservation.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		panelButtons.add(btnCancelReservation);
		btnCancelReservation.addActionListener(this);
		

	}
	
	
	
	
	// 예약 목록 출력
	public void resveListView(ArrayList<ResveDTO> resveList, JPanel paneReservationList) {
		
		// 1. 티켓가격 가져오기
		cmmnCodeList = Utility.getTicketPc();
		// 2. 티켓 정보 가져오기
		ticketList = getTicketList();
		// 3. 출력을 위한 변수 초기화
		cbList = new ArrayList<JCheckBox>();
		// 4. 예약 목록 분류하기(분류 순서 : 예약일, 티켓, 구분)// 입장일 추가해야함!!
		ArrayList<ResveDTO> rList = new ArrayList<ResveDTO>();	// 행 출력을 위한 리스트(예약일, 티켓번호가 같은 것 묶어서 출력)
		ResveDTO prevResveDTO = resveList.get(0);
		rList.add(prevResveDTO);
		
		for (int i = 1; i < resveList.size(); i++) {
			ResveDTO currResveDTO = resveList.get(i);
			// DB에서 가져올 때, 예약번호, 예약일, 티켓번호, 공통코드 순서
			if ( prevResveDTO.getTicketNo().equals(currResveDTO.getTicketNo()) ) {
				rList.add(currResveDTO);
			}
			else {
				resveView(rList, paneReservationList);	// 한 건의 예약을  출력(예약일, 티켓번호가 같은 티켓들)
				resveListGridY++;	// 다음 행으로 
				rList.clear();
				prevResveDTO = resveList.get(i);
				rList.add(prevResveDTO);
			}
		}
		resveView(rList, paneReservationList);	// 마지막 건의 예약을  출력(예약일, 티켓번호가 같은 티켓들)
		rList.clear();	// 다음 예약 리스트를 위한 초기화
	}
	
	// 한 건의 예약 출력
	public void resveView(ArrayList<ResveDTO> resveList, JPanel paneReservationList) {
		
		HashMap<String, Object> resveInfoHashMap = new HashMap<String, Object>();
		ArrayList<String> resveNoList = new ArrayList<String>();
		String tNo;
		
		ResveDTO resveDTO = resveList.get(0);
		resveNoList.add(resveDTO.getResveNo());
		// 체크박스
		JCheckBox cbSelect = new JCheckBox();
		cbSelect.setBackground(Color.WHITE);
		cbList.add(cbSelect);
		GridBagConstraints gbc_cbSelect = new GridBagConstraints();
		gbc_cbSelect.insets = new Insets(LEFT_PADDING_X, 0, 0, 5);
		gbc_cbSelect.gridx = 0;
		gbc_cbSelect.gridy = resveListGridY;
		paneReservationList.add(cbSelect, gbc_cbSelect);
		// 		결제가 된 예약은 취소, 결제 불가능
		if ('Y' == resveDTO.getSetleAt()) {
			cbSelect.setEnabled(false);
			cbSelect.setBackground(Color.GRAY);
		}
		// 예약일
		JLabel lblDataResveDe = new JLabel(resveDTO.getResveDe());
		lblDataResveDe.setFont(new Font("굴림", Font.PLAIN, 14));
		GridBagConstraints gbc_lblDataResveDe = new GridBagConstraints();
		gbc_lblDataResveDe.insets = new Insets(LEFT_PADDING_X, 0, 0, 5);
		gbc_lblDataResveDe.gridx = 1;
		gbc_lblDataResveDe.gridy = resveListGridY;
		paneReservationList.add(lblDataResveDe, gbc_lblDataResveDe);
		resveInfoHashMap.put(Utility.KEY_RESVE_DE, resveDTO.getResveDe());
		
		// 입장예정일
		JLabel lblDataEntncDe = new JLabel(resveDTO.getEntncDe());
		lblDataEntncDe.setFont(new Font("굴림", Font.PLAIN, 14));
		GridBagConstraints gbc_lblDataEntncDe = new GridBagConstraints();
		gbc_lblDataEntncDe.insets = new Insets(LEFT_PADDING_X, 0, 0, 5);
		gbc_lblDataEntncDe.gridx = 2;
		gbc_lblDataEntncDe.gridy = resveListGridY;
		paneReservationList.add(lblDataEntncDe, gbc_lblDataEntncDe);
		resveInfoHashMap.put(Utility.KEY_ENTNC_DE, resveDTO.getEntncDe());
		
		// 티켓 사진
		tNo = resveDTO.getTicketNo();
		String ticketPhotoCours = getTicketPhotoCours(tNo);
		JImageLabel lblDataTicketImage = new JImageLabel(ticketPhotoCours, TICKET_IMAGE_WIDTH, TICKET_IMAGE_HEIGHT);
		lblDataTicketImage.setFont(new Font("굴림", Font.PLAIN, 14));
		GridBagConstraints gbc_lblDataTicketImage = new GridBagConstraints();
		gbc_lblDataTicketImage.insets = new Insets(LEFT_PADDING_X, 0, 0, 5);
		gbc_lblDataTicketImage.gridx = 3;
		gbc_lblDataTicketImage.gridy = resveListGridY;
		paneReservationList.add(lblDataTicketImage, gbc_lblDataTicketImage);
		resveInfoHashMap.put(Utility.KEY_TICKET_PHOTO_COURS, ticketPhotoCours);
		
		// 구분 출력을 하기 위한 패널
		JPanel panelTicketSe = new JPanel();
		panelTicketSe.setBackground(Color.WHITE);
		panelTicketSe.setLayout(new BoxLayout(panelTicketSe, BoxLayout.Y_AXIS));		
		GridBagConstraints gbc_lblTicketSe = new GridBagConstraints();
		gbc_lblTicketSe.insets = new Insets(LEFT_PADDING_X, 0, 0, 5);
		gbc_lblTicketSe.gridx = 4;
		gbc_lblTicketSe.gridy = resveListGridY;
		paneReservationList.add(panelTicketSe, gbc_lblTicketSe);
		
		// 매수를 출력하기 위한 패널
		JPanel panelTicketCnt = new JPanel();
		panelTicketCnt.setBackground(Color.WHITE);
		panelTicketCnt.setLayout(new BoxLayout(panelTicketCnt, BoxLayout.Y_AXIS));
		GridBagConstraints gbc_lblTicketCnt = new GridBagConstraints();
		gbc_lblTicketCnt.insets = new Insets(LEFT_PADDING_X, 0, 0, 5);
		gbc_lblTicketCnt.gridx = 5;
		gbc_lblTicketCnt.gridy = resveListGridY;
		paneReservationList.add(panelTicketCnt, gbc_lblTicketCnt);
		
		// 가격을 출력하기 위한 패널
		JPanel panelTicketPc = new JPanel();
		panelTicketPc.setBackground(Color.WHITE);
		panelTicketPc.setLayout(new BoxLayout(panelTicketPc, BoxLayout.Y_AXIS));
		GridBagConstraints gbc_lblTicketPc = new GridBagConstraints();
		gbc_lblTicketPc.gridx = 6;
		gbc_lblTicketPc.gridy = resveListGridY;
		paneReservationList.add(panelTicketPc, gbc_lblTicketPc);
		
		// 첫번째 resveDTO는 위에서 세팅되어 있음!
		// (만약 정상적으로 출력이 되지 않는다면 티켓 정렬 상태 확인해볼 것!)
		StringBuffer ticketCounting = new StringBuffer("");
		int cnt =1;
		int index = 0;		// 공통코드 인덱스
		int totalPc = 0;	// 예약 한 건의 총 금액
		for (int i = 1; i < resveList.size(); i++) {
			ResveDTO nextResveDTO = resveList.get(i);
			resveNoList.add(nextResveDTO.getResveNo());
			
			if (resveDTO.getCmmnCode().equals(nextResveDTO.getCmmnCode())) {
				cnt++;
				continue;
			}
			else {
				String se = "구분";
				switch(resveDTO.getCmmnCode().substring(TICKET_NO_LENGTH)) {
				case "10":
					se = "성인";
					break;
				case "20":
					se = "어린이";
					break;
				case "30":
					se = "노인";
					break;
				}
				ticketCounting.append(se + " ");

				JLabel lblDataTicketSe = new JLabel(se);
				lblDataTicketSe.setBackground(Color.WHITE);
				lblDataTicketSe.setFont(new Font("굴림", Font.PLAIN, 14));
				panelTicketSe.add(lblDataTicketSe);
				
				ticketCounting.append(cnt + " ");
				JLabel lblDataTicketCnt = new JLabel(String.valueOf(cnt));
				lblDataTicketCnt.setBackground(Color.WHITE);
				lblDataTicketCnt.setFont(new Font("굴림", Font.PLAIN, 14));
				panelTicketCnt.add(lblDataTicketCnt);
				
				index = 0;
				for (; index < cmmnCodeList.size(); index++) {
					if (resveDTO.getCmmnCode().equals(cmmnCodeList.get(index).getCmmnCode())) {
						System.out.println("resveNo = "+resveDTO.getResveNo()+", cmmnCode = "+cmmnCodeList.get(index).getCmmnCode()+", pc = "+cmmnCodeList.get(index).getNumber());
						break; // 공통코드가 같은 인스턴스를 발견하면 멈춤
					}
				}
				String pc = Utility.changeToCurrencyFormat(String.valueOf(cmmnCodeList.get(index).getNumber()*cnt));
				JLabel lblDataTicketPc = new JLabel(pc);
				lblDataTicketPc.setBackground(Color.WHITE);
				lblDataTicketPc.setFont(new Font("굴림", Font.PLAIN, 14));
				panelTicketPc.add(lblDataTicketPc);
				totalPc += cmmnCodeList.get(index).getNumber()*cnt;
				
				resveDTO = resveList.get(i);
				cnt = 1;
			}//end-of-if(공통코드 비교)
		}
		// 마지막 티켓도 출력! // resveInfoHashMap에도 추가
		String se = "구분";
		switch(resveDTO.getCmmnCode().substring(TICKET_NO_LENGTH)) {
		case "10":
			se = "성인";
//			resveInfoHashMap.put(Utility.KEY_ADULT_CNT, adultCnt);
			break;
		case "20":
			se = "어린이";
//			resveInfoHashMap.put(Utility.KEY_CHILD_CNT, childCnt);
			break;
		case "30":
			se = "노인";
//			resveInfoHashMap.put(Utility.KEY_OLD_CNT, oldCnt);
			break;
		}
		index = 0;
		for (; index < cmmnCodeList.size(); index++) {
			if (resveDTO.getCmmnCode().equals(cmmnCodeList.get(index).getCmmnCode())) {
				System.out.println("resveNo = "+resveDTO.getResveNo()+", cmmnCode = "+cmmnCodeList.get(index).getCmmnCode()+", pc = "+cmmnCodeList.get(index).getNumber());
				break; // 공통코드가 같은 인스턴스를 발견하면 멈춤
			}
		}
		totalPc += cmmnCodeList.get(index).getNumber()*cnt;
		ticketCounting.append(se + " ");
		
		JLabel lblDataTicketSe = new JLabel(se);
		lblDataTicketSe.setFont(new Font("굴림", Font.PLAIN, 14));
		panelTicketSe.add(lblDataTicketSe);
		
		ticketCounting.append(cnt + "");
		JLabel lblDataTicketCnt = new JLabel(String.valueOf(cnt));
		lblDataTicketCnt.setFont(new Font("굴림", Font.PLAIN, 14));
		panelTicketCnt.add(lblDataTicketCnt);
		
		String pc = Utility.changeToCurrencyFormat(String.valueOf(cmmnCodeList.get(index).getNumber()*cnt));
		JLabel lblDataTicketPc = new JLabel(pc);
		lblDataTicketPc.setFont(new Font("굴림", Font.PLAIN, 14));
		panelTicketPc.add(lblDataTicketPc);
		resveInfoHashMap.put(Utility.KEY_TOTAL_PC, (Integer)totalPc);
		resveInfoHashMap.put(Utility.KEY_TICKET_CNT, ticketCounting.toString());
		resveInfoHashMap.put(Utility.KEY_TICKET_NM, getTicketNm(tNo));
		resveInfoHashMap.put(Utility.KEY_RESVE_NO_LIST, resveNoList);
		
		chkResveList.add(resveInfoHashMap);	// 한 건의 예약 처리 완료 시 추가(체크박스 체크시 전송할 데이터)
	}
	
	public String getTicketPhotoCours(String ticketNo) {
		String photoCours = "";
		
		for (int i = 0; i < ticketList.size(); i++) {
			TicketDTO tDTO = ticketList.get(i);
			if (ticketNo.equals(tDTO.getTicketNo())) {
				photoCours = tDTO.getPhotoCours();
			}
		}
		
		return photoCours;
	}
	
	public String getTicketNm(String ticketNo) {
		String ticketNm = "";
		
		for (int i = 0; i < ticketList.size(); i++) {
			TicketDTO tDTO = ticketList.get(i);
			if (ticketNo.equals(tDTO.getTicketNo())) {
				ticketNm = tDTO.getTicketNm();
			}
		}
		
		return ticketNm;
	}
	
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		// "결제" 버튼
		if (e.getSource() == btnSetle) {
			JOptionPane.showMessageDialog(panel, "결제 화면으로 넘어갑니다.");
			
			ArrayList<HashMap<String, Object>> transferData = getCheckedItems();
			Common.getHm().put("CHECKED_RESVE_LIST", transferData);
			
			Payment payment = new Payment();
			payment.setBounds(0, 0, 1042, 611);
			JPanel panelCommon = (JPanel)Common.getHm().get("PANEL_COMMON");
			Utility.changePanel(panelCommon, payment);
		}
		
		// "확인" 버튼
		if (e.getSource() == btnGoToMain) {
			MainUI mainUI = new MainUI();
			mainUI.setBounds(0, 0, 1042, 611);
			JPanel panelCommon = (JPanel)Common.getHm().get("PANEL_COMMON");
			Utility.changePanel(panelCommon, mainUI);
		}
				
		// "예약 취소" 버튼
		if (e.getSource() == btnCancelReservation) {
			
			ArrayList<HashMap<String, Object>> cancelData = getCheckedItems();
			
			for (int i = 0; i < cancelData.size(); i++) {
				HashMap<String, Object> hm = cancelData.get(i);
				String ticketNm = (String)hm.get(Utility.KEY_TICKET_NM);
				String ticketNo = getTicketNo(ticketNm);
				String entncDe = (String)hm.get(Utility.KEY_ENTNC_DE);
				
				// DB에서 삭제하기 (결제가 진행된 예약 건에 대해선 주의 할 것!)
				deleteResveList(ticketNo, entncDe);
			}
			
			JOptionPane.showMessageDialog(panel, "예약이 취소되었습니다.");
			
			ReservationConfirmationList reservationConfirmationList = new ReservationConfirmationList();
			reservationConfirmationList.setBounds(0, 0, 1042, 611);
			JPanel panelCommon = (JPanel)Common.getHm().get("PANEL_COMMON");
			Utility.changePanel(panelCommon, reservationConfirmationList);
		}
		
	}
	
	// 체크박스에 선택된 항목 가져오기
	private ArrayList<HashMap<String, Object>> getCheckedItems() {
		
		ArrayList<HashMap<String, Object>> checkedData = new ArrayList<HashMap<String, Object>>();
		
		for (int i = 0; i < cbList.size(); i++) {
			JCheckBox cb = cbList.get(i);
			
			if ( cb.isSelected() ) {
				System.out.println(i+"번째 체크박스");
				checkedData.add((HashMap<String, Object>)chkResveList.get(i));
			}
		}
		
		return checkedData;
	}
	
	// 티켓명으로 티켓번호 구하기
	private String getTicketNo(String ticketNm) {
		TicketDTO tDTO;
		String ticketNo = "";
		
		for (int i = 0; i < ticketList.size(); i++) {
			tDTO = ticketList.get(i);
			if ( ticketNm.equals(tDTO.getTicketNm()) ) {
				ticketNo = tDTO.getTicketNo();
				break;
			}
		}
		
		return ticketNo;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		// 일단 대기!
	}
	
	
	
	
	// 티켓 정보 가져오기
	public ArrayList<TicketDTO> getTicketList(){
		ArrayList<TicketDTO> tList = new ArrayList<TicketDTO>();
		
		try {			
			DBController.connect();
			
			String sql
			= "SELECT 	* 				"
			+ "FROM 	TICKET 			"
			+ "ORDER BY TICKET_NO 		";
			
			
			DBController.setPstmt(sql);
			ResultSet rs = DBController.select();
			
			while(rs.next()) {
				TicketDTO ticketDTO = new TicketDTO();
				System.out.println(rs.getString(1));
				ticketDTO.setTicketNo	(rs.getString	("TICKET_NO"));
				ticketDTO.setValidPd	(rs.getInt		("VALID_PD"));
				ticketDTO.setTicketNm	(rs.getString	("TICKET_NM"));
				ticketDTO.setTicketDc	(rs.getString	("TICKET_DC"));
				ticketDTO.setPhotoCours	(rs.getString	("PHOTO_COURS"));
				ticketDTO.setCmmnCode	(rs.getString	("CMMN_CODE"));
				
				tList.add(ticketDTO);
			}
			
			DBController.close();
			
		} catch (BizException e) {
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
		return tList;
	}
	
	// 예약 리스트 DB에서 가져오기
	public ArrayList<ResveDTO> selectResveList(String mberNo){
		ArrayList<ResveDTO> rList = new ArrayList<ResveDTO>();
		
		try {
			
			DBController.connect();
			
			String sql
			= "SELECT 	* 								"
			+ "FROM 	RESVE 							"
			+ "WHERE 	MBER_NO = ?						"
			+ "ORDER BY RESVE_NO, RESVE_DE, CMMN_CODE	";
			
			
			DBController.setPstmt(sql);
			DBController.getPstmt().setString(1, mberNo);
			
			
			ResultSet rs = DBController.select();
			
			while(rs.next()) {
				ResveDTO resveDTO = new ResveDTO();
				System.out.println(rs.getString(1));
				resveDTO.setResveNo		(rs.getString	("RESVE_NO"));
				resveDTO.setMberNo		(rs.getString	("MBER_NO"));
				resveDTO.setTicketNo	(rs.getString	("TICKET_NO"));
				resveDTO.setCmmnCode	(rs.getString	("CMMN_CODE"));
				resveDTO.setResveDe		(rs.getString	("RESVE_DE"));
				resveDTO.setEntncDe		(rs.getString	("ENTNC_DE"));
				resveDTO.setSetleAt		(rs.getString	("SETLE_AT").charAt(0));
				
				rList.add(resveDTO);
			}
			
			DBController.close();
			
		} catch (BizException e) {
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return rList;
	}
	
	// 예약 리스트 DB에서 삭제하기
	public void deleteResveList(String ticketNo, String entncDe){
		
		try {
			
			DBController.connect();
			
			String sql
			= "DELETE 	FROM RESVE 		"
			+ "WHERE 	TICKET_NO = ? 	"
			+ "AND 		ENTNC_DE = ? 	";
			
			
			DBController.setPstmt(sql);
			DBController.getPstmt().setString(1, ticketNo);
			DBController.getPstmt().setString(2, entncDe);
			
			int rs = DBController.delete();
			
			if (rs > 0) {
				System.out.println("삭제 완료");
			}
			else {
				JOptionPane.showMessageDialog(panel, "오류:예약을 취소할 수 없습니다. \n잠시 후에 다시 시도해주세요.");
			}
			
			DBController.close();
			
		} catch (BizException e) {
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
}
