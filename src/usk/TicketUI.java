package usk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import dto.LoginDTO;
import dto.TicketDTO;
import lib.BizException;
import lib.DBController;
import lib.component.JImageButton;
import lib.component.JMultilineLabel;
import util.Common;
import util.PanelPosition;
import util.Utility;

public class TicketUI extends JPanel implements ActionListener {
	
	/**
	 * 	2021-03-15
	 * 		- 티켓 선택 이후 로그인 체크 추가
	 */
	private static final long serialVersionUID = 3L;
	
	
	private JPanel 		panel;
	private JPanel 		panelTitle;
	private JScrollPane scrollPane;
	private JPanel 		panelContents;
	
	private final int 	PADDING_X 				= 12;
	private final int 	PADDING_Y 				= 10;
	private final int 	TITLE_PANEL_WIDTH 		= 1018;
	private final int 	TITLE_PANEL_HEIGHT 		= 80;
	private final int 	CONTENTS_PANEL_WIDTH 	= 1018;
	private final int 	CONTENTS_PANEL_HEIGHT	= 501;
	private final int 	TICKET_PANEL_WIDTH 		= 82;
	private final int 	TICKET_PANEL_HEIGHT 	= 330;
	private final int 	TICKET_IMAGE_HEIGHT 	= 220;
	private final int 	TICKET_NM_HEIGHT 		= 80;
	private final int 	TICKET_DC_HEIGHT 		= 80;
	
	private ArrayList<JImageButton> ibtnTicketList = new ArrayList<JImageButton>();
	private ArrayList<TicketDTO> ticketList;
	
	
	public TicketUI() {
		
		initialize();
		
	}
	
	public void initialize() {
		
		setLayout(null);
		this.setBackground(Color.WHITE);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1042, 611);
		add(panel);
		panel.setLayout(null);
		
		panelTitle = new JPanel();
		panelTitle.setBackground(Color.WHITE);
		panelTitle.setBounds(PADDING_X, PADDING_Y, TITLE_PANEL_WIDTH, TITLE_PANEL_HEIGHT);
		titleView(panelTitle);
		panel.add(panelTitle);
		
		// 목록을 출력하기 위한 패널, 스크롤 설정
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(null);
		scrollPane.setOpaque(true);
		scrollPane.setBounds(12, 100, CONTENTS_PANEL_WIDTH, CONTENTS_PANEL_HEIGHT);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);	// 스크롤바 속도 조절
		scrollPane.setMaximumSize(new Dimension(1042, 601));
		
		panel.add(scrollPane);
		
		panelContents = new JPanel();
		panelContents.setBackground(Color.WHITE);
		panelContents.setBounds(0, 0, CONTENTS_PANEL_WIDTH, CONTENTS_PANEL_HEIGHT);
		panelContents.setBorder(null);
		panelContents.setOpaque(true);
		panelContents.setLayout(new GridLayout(0, 3, 14, 10));

		ticketList = selectTicket();					// 티켓목록을 DB에서 가져오기
		ticketView(ticketList, panelContents);			// 티켓을 패널에 추가

		
		// 스크롤 적용할 패널 지정하기
		scrollPane.setViewportView(panelContents);
		scrollPane.getViewport().setViewPosition(new Point(0, 0));	// 최초 스크롤 위치 최상단으로(미완)


	}
	
	// 타이틀 출력
	private void titleView(JPanel panelTitle) {
		// panelTitleSize = 1018, 80
		PanelPosition pos = new PanelPosition(PADDING_X, PADDING_Y);
		panelTitle.setLayout(null);
		
		JLabel lblTitle = new JLabel("티켓 ");
		lblTitle.setBounds(0, 5, 129, 70);
		lblTitle.setFont(new Font("나눔고딕", Font.BOLD, 45));
		panelTitle.add(lblTitle);
		
		pos.setX(pos.getX()+220+PADDING_X);
		
		JLabel lblSubTitle = new JLabel("Ticket");
		lblSubTitle.setBounds(120, 38, 88, 29);
		lblSubTitle.setFont(new Font("나눔고딕", Font.BOLD, 24));
		lblSubTitle.setAlignmentY(BOTTOM_ALIGNMENT);
		panelTitle.add(lblSubTitle);
		
	}
	
	// 티켓 출력
	public void ticketView(ArrayList<TicketDTO> ticketList, JPanel panelContents) {
		
		for (int i = 0; i < ticketList.size(); i++) {
			
			JPanel panelTicket = new JPanel();
			panelTicket.setBackground(Color.WHITE);
			panelTicket.setLayout(new BorderLayout());
			panelTicket.setBounds(12, 10, TICKET_PANEL_WIDTH, TICKET_PANEL_HEIGHT);
			
			JLabel 			lblTicketNm;
			JImageButton 	ibtnTicket;
			JMultilineLabel mlblTicketDc;
			
			TicketDTO ticketDTO = ticketList.get(i);
			
			
			
			// 티켓 사진 추가
			ibtnTicket = new JImageButton(ticketDTO.getPhotoCours(), TICKET_PANEL_WIDTH, TICKET_IMAGE_HEIGHT);
			ibtnTicket.setLocation(PADDING_X, 0);
			panelTicket.add(ibtnTicket, BorderLayout.NORTH);
			ibtnTicketList.add(ibtnTicket);
			ibtnTicket.addActionListener(this);
			
			// 티켓명 추가
			lblTicketNm = new JLabel(ticketDTO.getTicketNm());
			lblTicketNm.setFont(new Font("나눔고딕", Font.BOLD, 20));
			lblTicketNm.setBounds(PADDING_X, PADDING_Y, TICKET_PANEL_WIDTH, TICKET_NM_HEIGHT);
			panelTicket.add(lblTicketNm, BorderLayout.CENTER);
			
			// 티켓 설명 추가
			mlblTicketDc = new JMultilineLabel(Utility.changeSpecialChar(ticketDTO.getTicketDc()));
			mlblTicketDc.setBounds(PADDING_X, PADDING_Y, TICKET_PANEL_WIDTH, TICKET_DC_HEIGHT);
			mlblTicketDc.setColumns(5);
			panelTicket.add(mlblTicketDc, BorderLayout.SOUTH);
			
			panelContents.add(panelTicket);
			panelContents.revalidate();
			scrollPane.getViewport().setViewPosition(new Point(0, 0));	// 최초 스크롤 위치 최상단으로(미완)
			
		}
		
		
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {

		JImageButton btn;
		
		for (int i = 0; i < ibtnTicketList.size(); i++) {
			btn = ibtnTicketList.get(i);
			if (e.getSource() == btn) {
//				JOptionPane.showMessageDialog(null, ticketList.get(i).getTicketNo());
				
				// 로그인 상태 확인
				LoginDTO loginDTO = (LoginDTO)Common.getHm().get("LoginDTO");
				
				// 로그인 상태에서만 다음 화면으로 넘어간다.
				if (loginDTO.getLevel() > 0) {
					// 선택된 티켓DTO를 전송
					HashMap<String, Object> hm = Common.getHm();
					hm.put("TICKET_DTO", ticketList.get(i));
					
					BeforeReservation beforeReservationUI = new BeforeReservation();
					beforeReservationUI.setBounds(0, 0, 1042, 611);
					
					JPanel panelCommon = (JPanel)Common.getHm().get("PANEL_COMMON");
					Utility.changePanel(panelCommon, beforeReservationUI);
				}
				// 로그인 하지 않은 상태면 메인으로 이동
				else {
					JOptionPane.showMessageDialog(panel, "로그인을 먼저 해 주십시오.");
				}
				break;
			}
		}

	}
	
	

	@SuppressWarnings("unchecked")
	public ArrayList<TicketDTO> selectTicket() {
		
		ArrayList<TicketDTO> ticketList = null;
		
		if (Common.getHm().get("TICKET_ALL") != null) {
			return (ArrayList<TicketDTO>)Common.getHm().get("TICKET_ALL");
		}
		
		try {
			
			ticketList = new ArrayList<TicketDTO>();
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
				
				ticketList.add(ticketDTO);
			}
			
			DBController.close();
			
		} catch (BizException e) {
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		if (Common.getHm().get("TICKET_ALL") == null) {
			Common.getHm().put("TICKET_ALL", ticketList);
		}
		return ticketList;
		
	}

	
}
