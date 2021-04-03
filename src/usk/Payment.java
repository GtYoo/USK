package usk;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import dto.CmmnCodeDTO;
import lib.BizException;
import lib.DBController;
import lib.component.JImageLabel;
import lib.component.JMultilineLabel;
import util.Common;
import util.PanelPosition;
import util.Utility;

public class Payment extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private JPanel panel;
	private JScrollPane scrollPane;
	private JPanel panelContents;
	
	private JLabel lblTitle;
	
	private JPanel panelResveList;
	
	private JPanel panelMber;

	private JPanel panelNotice;
	private JMultilineLabel textArea;
	
	private JPanel panelButton;
	private JButton btnSetle;
	private JButton btnReturn;
	
	@SuppressWarnings("unchecked")
	ArrayList<HashMap<String, Object>> resveInfoList = (ArrayList<HashMap<String, Object>>)Common.getHm().get("CHECKED_RESVE_LIST");
	ArrayList<CmmnCodeDTO> 				cmmnCodeList = Utility.getTicketPc();
	
	private final int ADULT = 1;
	private final int CHILD = 3;
	private final int OLD = 5;
	
	private final int PADDING_X = 5;
	private final int PADDING_Y = 5;
	private final int TICKET_IMAGE_WIDTH = 220;
	private final int TICKET_IMAGE_HEIGHT = 120;
	private final int PANEL_NOTICE_COLUMN = 80;
	private final int PANEL_NOTICE_ROW = 5;

	/**
	 * Create the panel.
	 */
	public Payment() {
		setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1042, 611);
		add(panel);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 1042, 611);
		panel.add(scrollPane);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		panelContents = new JPanel();
		panelContents.setBackground(Color.WHITE);
		panelContents.setBounds(0, 0, 1042, 611);
		scrollPane.setViewportView(panelContents);
		GridBagLayout gbl_panelContents = new GridBagLayout();
		gbl_panelContents.columnWidths = new int[] {1018};
		gbl_panelContents.rowHeights = new int[] {80, 240, 110, 100, 30};
		gbl_panelContents.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panelContents.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelContents.setLayout(gbl_panelContents);
		
		// 화면 타이틀 출력
		lblTitle = new JLabel("결제");
		lblTitle.setFont(new Font("나눔고딕", Font.BOLD, 24));
		lblTitle.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.fill = GridBagConstraints.BOTH;
		gbc_lblTitle.insets = new Insets(10, 10, 5, 10);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		panelContents.add(lblTitle, gbc_lblTitle);
		
		
		
		// 결제를 진행할 예약 목록 출력 패널
		panelResveList = new JPanel();
		panelResveList.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelResveList = new GridBagConstraints();
		gbc_panelResveList.insets = new Insets(0, 0, 5, 0);
		gbc_panelResveList.fill = GridBagConstraints.BOTH;
		gbc_panelResveList.gridx = 0;
		gbc_panelResveList.gridy = 1;
		panelContents.add(panelResveList, gbc_panelResveList);
		panelResveList.setLayout(new BoxLayout(panelResveList, BoxLayout.Y_AXIS));
		resveInfoView(panelResveList);
		
		// 예약자 정보 출력 패널
		panelMber = new JPanel();
		panelMber.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelMber = new GridBagConstraints();
		gbc_panelMber.insets = new Insets(0, 0, 5, 0);
		gbc_panelMber.fill = GridBagConstraints.BOTH;
		gbc_panelMber.gridx = 0;
		gbc_panelMber.gridy = 2;
		panelContents.add(panelMber, gbc_panelMber);
		
		// 주의사항 패널
		panelNotice = new JPanel();
		panelNotice.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelNotice = new GridBagConstraints();
		gbc_panelNotice.anchor = GridBagConstraints.WEST;
		gbc_panelNotice.insets = new Insets(0, 0, 5, 0);
		gbc_panelNotice.fill = GridBagConstraints.BOTH;
		gbc_panelNotice.gridx = 0;
		gbc_panelNotice.gridy = 3;
		panelContents.add(panelNotice, gbc_panelNotice);
		
		String strNotice = "⩥ 주의 사항\r\n결제완료후의 결제취소는 일절 불가능합니다.\r\n(단, 법률상의 해제 또는 무효사유등이 고객여러분들에게 인정되는 경우에는 해당되지 않습니다.)\r\n선택하신 내용에 잘못이 없는지 반드시 확인한 후에, 결제해주세요.\r\n또한, 해당 파크에서는, 티켓의 전매행위를 엄격하게 금지하고 있습니다.\r\n해당규약에 위반시, 위반자가 구입한 티켓의 QR코드를 무효시킵니다.\r\n이쪽에 동의 하시는 경우에만, 체크를 하고 하기의 버튼으로부터 다음단계로 진행해주세요.";
		textArea = new JMultilineLabel(strNotice, PANEL_NOTICE_COLUMN, PANEL_NOTICE_ROW);
		textArea.setColumns(80);
		textArea.setRows(5);
		panelNotice.add(textArea);
		
		// 버튼 패널
		panelButton = new JPanel();
		panelButton.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelButton = new GridBagConstraints();
		gbc_panelButton.fill = GridBagConstraints.BOTH;
		gbc_panelButton.gridx = 0;
		gbc_panelButton.gridy = 4;
		panelContents.add(panelButton, gbc_panelButton);
		
		btnSetle = new JButton("결제");
		panelButton.add(btnSetle);
		btnSetle.addActionListener(this);
		
		btnReturn = new JButton("돌아가기");
		panelButton.add(btnReturn);
		btnReturn.addActionListener(this);
		
		

	}
	
	// 결제를 진행할 전체 예약 정보 출력
	public void resveInfoView(JPanel panelResveList) {
		
		for (int i = 0; i < resveInfoList.size(); i++) {
			HashMap<String, Object> resveInfo = resveInfoList.get(i);
			
			{
				System.out.println("리스트주소값= "+resveInfo.get(Utility.KEY_RESVE_NO_LIST));
				System.out.println("티켓명= "+resveInfo.get(Utility.KEY_TICKET_NM));
				System.out.println("티켓사진= "+resveInfo.get(Utility.KEY_TICKET_PHOTO_COURS));
				System.out.println("예약일= "+resveInfo.get(Utility.KEY_RESVE_DE));
				System.out.println("입장일= "+resveInfo.get(Utility.KEY_ENTNC_DE));
//				System.out.println("성인 수= "+resveInfo.get(Utility.KEY_ADULT_CNT));
//				System.out.println("어린이 수= "+resveInfo.get(Utility.KEY_CHILD_CNT));
//				System.out.println("노인 수= "+resveInfo.get(Utility.KEY_OLD_CNT));
				System.out.println("티켓 매수= " + resveInfo.get(Utility.KEY_TICKET_CNT));
				System.out.println("결제금액= "+resveInfo.get(Utility.KEY_TOTAL_PC));
			}
			
			// 예약 정보 패널을 생성하여 출력
//			JPanel panelResveInfo = createPanelResveInfo(resveInfo);
			
			PanelPosition pos = new PanelPosition(0, 0);
			panelResveInfoView(resveInfo, panelResveList, pos);
			
		}
	}
	
	// 예약 정보 패널 생성
	public void panelResveInfoView(HashMap<String, Object> resveData, JPanel panelResveList, PanelPosition pos) {
		
		JPanel panelResveInfo = new JPanel();
		panelResveInfo.setSize(1018, 240);
		panelResveInfo.setBackground(Color.WHITE);
//		panelResveInfo.setLayout(new GridLayout(0, 2, PADDING_X, PADDING_Y));
		panelResveInfo.setLayout(null);
		
		pos.setX(10);
		pos.setY(10);
		
		// 티켓 정보 출력
		JPanel panelTicketInfo = new JPanel();
		panelTicketInfo.setBounds(pos.getX(), pos.getY(), 320, 240);
		panelTicketInfo.setBackground(Color.WHITE);
		panelTicketInfo.setLayout(new BoxLayout(panelTicketInfo, BoxLayout.Y_AXIS));
		panelResveInfo.add(panelTicketInfo);
		//		티켓명
		JLabel lblTicketNm = new JLabel((String)resveData.get(Utility.KEY_TICKET_NM));
		lblTicketNm.setBounds(pos.getX(), pos.getY(), 320, 40);
		panelTicketInfo.add(lblTicketNm);
		pos.moveY(40);
		//		티켓사진
		JImageLabel ilblTicketPhoto = new JImageLabel((String)resveData.get(Utility.KEY_TICKET_PHOTO_COURS), TICKET_IMAGE_WIDTH, TICKET_IMAGE_HEIGHT);
		ilblTicketPhoto.setBounds(pos.getX(), pos.getY(), 320, 200);
		panelTicketInfo.add(ilblTicketPhoto);
		
		pos.moveX(360);
		pos.setY(10);
		
		// 티켓 매수, 가격 출력
		JPanel panelTicketCnt = new JPanel();
		panelTicketCnt.setBounds(pos.getX(), pos.getY(), 696, 240);
		panelTicketCnt.setBackground(Color.WHITE);
		panelTicketCnt.setLayout(new GridLayout(4, 2, PADDING_X, PADDING_Y));
		panelResveInfo.add(panelTicketCnt);
		// 		매수 타이틀 출력
		JLabel lblTicketCnt = new JLabel("매수");
		panelTicketCnt.add(lblTicketCnt);
		// 		매수 출력 (예약 확인 목록에서 처리)
//		StringBuffer strTicketCnt = new StringBuffer("");
//		String adultCnt = String.valueOf(resveData.get(Utility.KEY_ADULT_CNT));
//		if (!adultCnt.isEmpty()) {
//			strTicketCnt.append("성인 ");
//			strTicketCnt.append(adultCnt);
//		}
//		String childCnt = String.valueOf(resveData.get(Utility.KEY_CHILD_CNT));
//		if (!childCnt.isEmpty()) {
//			strTicketCnt.append(" 어린이 ");
//			strTicketCnt.append(childCnt);
//		}
//		String oldCnt = String.valueOf(resveData.get(Utility.KEY_OLD_CNT));
//		if (!oldCnt.isEmpty()) {
//			strTicketCnt.append(" 노인 ");
//			strTicketCnt.append(oldCnt);
//		}
		JLabel lblTicketCntData = new JLabel((String)resveData.get(Utility.KEY_TICKET_CNT));
		panelTicketCnt.add(lblTicketCntData);
		// 		금액 타이틀 출력
		JLabel lblTotalPc = new JLabel("금액");
		panelTicketCnt.add(lblTotalPc);
		// 		금액 출력
		JLabel lblTotalPcData = new JLabel(String.valueOf((Integer)resveData.get(Utility.KEY_TOTAL_PC)));
		panelTicketCnt.add(lblTotalPcData);
		// 		입장일 타이틀 출력
		JLabel lblEntncDe = new JLabel("입장일");
		panelTicketCnt.add(lblEntncDe);
		// 		입장일 출력
		String strEntncDate = Utility.changeToDateFormat((String)resveData.get(Utility.KEY_ENTNC_DE));
		JLabel lblEntncDeData = new JLabel(strEntncDate);
		panelTicketCnt.add(lblEntncDeData);
		// 		예약일 타이틀 출력
		JLabel lblResveDe = new JLabel("예약일");
		panelTicketCnt.add(lblResveDe);
		// 		예약일 출력
		String strResveDate = Utility.changeToDateFormat((String)resveData.get(Utility.KEY_RESVE_DE));
		JLabel lblResveDeData = new JLabel(strResveDate);
		panelTicketCnt.add(lblResveDeData);
		
		
		
		panelResveList.add(panelResveInfo);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {

		if ( e.getSource() == btnSetle ) {
			
			JOptionPane.showMessageDialog(panel, "결제가 진행중입니다.");
			
			// 예약 건 수 만큼 반복
			for (int i = 0 ; i < resveInfoList.size(); i++) {
				HashMap<String, Object> hm = resveInfoList.get(i);
				// 한 건의 예약 리스트 가져오기
				@SuppressWarnings("unchecked")
				ArrayList<String> resveNoList = (ArrayList<String>)hm.get(Utility.KEY_RESVE_NO_LIST);
				ArrayList<Integer> ticketPcList = new ArrayList<Integer>();
				ArrayList<String> ticketCommonCodeList = new ArrayList<String>();
				
				// 티켓 매수 구하고, 티켓 마다 가격과 코드를 찾아서 리스트에 추가하기
				String tmp = (String)hm.get(Utility.KEY_TICKET_CNT);
				String[] ticketCnts = tmp.split(" ");

				String ticketNm = (String)hm.get(Utility.KEY_TICKET_NM);
				HashMap<String, Object> tmpHm;
				if (ticketCnts.length > ADULT) {
					tmpHm = getTicketPcAndCode(ticketNm+" - 성인");
					for (int ii = 0 ; ii < Integer.parseInt(ticketCnts[ADULT]); ii++) {
						ticketPcList.add((Integer)tmpHm.get("PC"));
						ticketCommonCodeList.add((String)tmpHm.get("CC"));
					}
					tmpHm.clear();
				}
				if (ticketCnts.length > CHILD) {
					tmpHm = getTicketPcAndCode(ticketNm+" - 어린이");
					for (int ii = 0 ; ii < Integer.parseInt(ticketCnts[CHILD]); ii++) {
						ticketPcList.add((Integer)tmpHm.get("PC"));
						ticketCommonCodeList.add((String)tmpHm.get("CC"));
					}
					tmpHm.clear();
				}
				if (ticketCnts.length > OLD) {
					tmpHm = getTicketPcAndCode(ticketNm+" - 노인");
					for (int ii = 0 ; ii < Integer.parseInt(ticketCnts[OLD]); ii++) {
						ticketPcList.add((Integer)tmpHm.get("PC"));
						ticketCommonCodeList.add((String)tmpHm.get("CC"));
					}
					tmpHm.clear();
				}
				
				
				for (int j = 0; j < resveNoList.size(); j++) {
					insertSetle(resveNoList.get(j), ticketPcList.get(j), ticketCommonCodeList.get(j));
				}
				
			}//end-of-for
			
			System.out.println("최종 결제 완료!");
			
//			PaymentList paymentList = new PaymentList();
//			paymentList.setBounds(0, 0, 1042, 611);
//			
//			JPanel panelCommon = (JPanel)Common.getHm().get("PANEL_COMMON");
//			Utility.changePanel(panelCommon, paymentList);
			
			ReservationConfirmationList reservationConfirmationList = new ReservationConfirmationList();
			reservationConfirmationList.setBounds(0, 0, 1042, 611);
			
			JPanel panelCommon = (JPanel)Common.getHm().get("PANEL_COMMON");
			Utility.changePanel(panelCommon, reservationConfirmationList);
			
		}
		else if ( e.getSource() == btnReturn ) {
			ReservationConfirmationList reservationConfirmationList = new ReservationConfirmationList();
			reservationConfirmationList.setBounds(0, 0, 1042, 611);
			
			JPanel panelCommon = (JPanel)Common.getHm().get("PANEL_COMMON");
			Utility.changePanel(panelCommon, reservationConfirmationList);
		}
		
	}
	
	private HashMap<String, Object> getTicketPcAndCode(String ticketNm) {
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		for (int ci = 0; ci < cmmnCodeList.size(); ci++) {
			CmmnCodeDTO cmmnCodeDTO = cmmnCodeList.get(ci);
			if (ticketNm.equals(cmmnCodeDTO.getCmmnCodeNm())) {
				hm.put("CC", cmmnCodeDTO.getCmmnCode());
				hm.put("PC", (Integer)cmmnCodeDTO.getNumber());
				break;
			}	
		}
		return hm;
	}
	
	// 결제 테이블 insert 처리(예약 테이블 결제 확인 여부 항목도 업데이트 처리)
	private void insertSetle(String resveNo, int ticketPc, String commonCode) {
		
		try {
			
			DBController.connect();
			DBController.getCon().setAutoCommit(false); // 자동 커밋 해제
			// 트랜잭션 처리 부분 - 하나의 오류라도 발생하면 전부 롤백
			
			String sql
			= "INSERT INTO 	SETLE																	  	"
			+ "(RESVE_NO, ISSU_NO, SETLE_AMOUNT, SETLE_DT, CANCL_REQST_AT, COMPT_DT, CANCDE_COMPT_AT) 	"
			+ "VALUES ( ? , ISSU_NO_SEQ.NEXTVAL , ? , SYSDATE , 'N' , NULL , 'N')			";
			DBController.setPstmt(sql);
			DBController.getPstmt().setString(1, resveNo);
			DBController.getPstmt().setInt(2, ticketPc);
			
			int rs = DBController.insert();
			if(rs > 0) {
				System.out.println("결제 테이블 등록 완료!");
			}
			else {
				throw new BizException();
			}
			
			sql
			= "UPDATE 	RESVE	  		"
			+ "SET  	SETLE_AT = 'Y' 	"
			+ "WHERE 	RESVE_NO = ? 	"
			+ "AND 		CMMN_CODE = ? 	";
			DBController.setPstmt(sql);
			DBController.getPstmt().setString(1, resveNo);
			DBController.getPstmt().setString(2, commonCode);
			
			rs = DBController.update();
			if(rs > 0) {
				System.out.println("예약 테이블 업데이트 완료!");
			}
			
			DBController.getCon().setAutoCommit(true); // 자동 커밋 재설정
			DBController.close();
			
			
		} catch (BizException e) {
			try {
				// 오류 발생시 트랜잭션 단위로 롤백처리하고 자동커밋 재설정
				DBController.getCon().rollback();
				DBController.getCon().setAutoCommit(true); // 자동 커밋 재설정
				DBController.close();
				
			} catch (BizException e1) {
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
