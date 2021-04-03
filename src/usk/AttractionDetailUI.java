package usk;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import dto.AttractionDTO;
import dto.ReviewDTO;
import lib.BizException;
import lib.DBController;
import lib.component.JImage;
import lib.component.JImageButton;
import lib.component.JImageLabel;
import lib.component.JMultilineLabel;
import util.Common;
import util.PanelPosition;
import util.Utility;

public class AttractionDetailUI extends JPanel implements ActionListener, KeyListener {
//public class AttractionDetailUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final int PANEL_ATTRACTION_DETAIL_WIDTH 	= 1018;
	private final int PANEL_ATTRACTION_DETAIL_HEIGHT 	= 460;
	private final int ATTRACTION_IMAGE_WIDTH 			= 545;
	private final int ATTRACTION_IMAGE_HEIGHT 			= 360;
	private final int AVG_RATING_X_POINT 				= 580;
	private final int AVG_RATING_Y_POINT 				= 395;
	private final int AVG_RATING_LABEL_WIDTH			= 50;
	private final int AVG_RATING_LABEL_HEIGHT			= 40;
	
	private final int PANEL_REGIST_REVIEW_HEIGHT 		= 122;
	private final int PANEL_REVIEW_VIEW_HEIGHT 			= 122;
	
	private final int STAR_SIZE_WIDTH 					= 25;	// 별_이미지 넓이
	private final int STAR_SIZE_HEIGHT 					= 25;	// 별_이미지 높이
	private final int STAR5 							= 10;	// 별 5개
	private final int STAR4 							= 8;	// 별 4개
	private final int STAR3 							= 6;	// 별 3개
	private final int STAR2 							= 4;	// 별 2개
	private final int STAR1 							= 2;	// 별 1개
	private final int STAR0 							= 0;	// 별 0개
	
	private final int MAX_REVIEW_TEXT 					= 99;	// 리뷰 입력시 입력가능한 최대 글자 수
	private final int TICKET_NO_LENGTH 					= 6;	// 예약번호 자리수 (6자리)
	
	private final int UPLOADED_IMAGE_WIDTH 				= 220;
	private final int UPLOADED_IMAGE_HEIGHT 			= 92;
	
	
	
	private JPanel 				panel;
	private JScrollPane 		scrollPane;
	private JPanel 				panelContents;
	

	private JPanel 				panelAttractionDetail;
	private JLabel 				lblAttractionNm;	
	private JImageLabel			lblAttractionImage;
	private JMultilineLabel		lblAttractionDc;
	private JLabel 				lblAttractionOprat;
	private JLabel 				lblAttractionOperTime;
	private JLabel 				lblAttractionAvgRating;
	private JImageLabel			lblStar1;
	private JImageLabel			lblStar2;
	private JImageLabel			lblStar3;
	private JImageLabel			lblStar4;
	private JImageLabel			lblStar5;
	
	private JPanel 				panelRegistReview;
	private JImageButton		btnImageUpload;			// 리뷰 사진 업로드
	private JLabel 				lblValidTicketNo;		// 유효한 티켓번호
	private JButton				btnRegistValidTicketNo; // 유효한 티켓번호 작성 팝업
	private JImageButton		btnStar1;
	private JImageButton		btnStar2;
	private JImageButton		btnStar3;
	private JImageButton		btnStar4;
	private JImageButton		btnStar5;
	private JLayeredPane 		layeredPane;
	private JTextArea 			taInputText;			// 리뷰 내용 작성
	private JLabel 				lblCurrentInputText;	// 현재 타이핑된 리뷰 내용 길이
	private JLabel 				lblMaxInputText;		// 최대 타이핑 가능한 리뷰 내용 길이
	private JButton 			btnRegistReview;
	

	
	private JPanel 				panelReviewView;
//	private JButton 			btnReviewImage;
//	private JLabel 				lblTicketNo;
//	private JImageLabel 		lblReviewStar1;
//	private JImageLabel 		lblReviewStar2;
//	private JImageLabel 		lblReviewStar3;
//	private JImageLabel 		lblReviewStar4;
//	private JImageLabel 		lblReviewStar5;
//	private JLabel 				lblReviewDate;	// 리뷰 작성한 날짜
//	private JLabel 				lblReviewCn;	// 리뷰 내용
	
//	private final int 			PADDING_X = 14;
//	private final int 			PADDING_Y = 12;
	private final int 			PANEL_REVIEW_WIDTH 		= 1014;	// 리뷰 패널의 넓이
	private final int 			PANEL_REVIEW_HEIGHT 	= 122;	// 리뷰 패널의 높이
	private final int 			REVIEW_IMAGE_WIDTH 		= 164;
	private final int 			REVIEW_IMAGE_HEIGHT 	= 112;
	private final int 			REVIEW_RATING_STAR_SIZE = 25;
	
	
	private AttractionDTO 		attractionDTO 	= new AttractionDTO();
	private ArrayList<JImage> 	stars 			= new ArrayList<JImage>();
	private ArrayList<JImage> 	inputStars 		= new ArrayList<JImage>();
	private ArrayList<ReviewDTO> reviewList 	= new ArrayList<ReviewDTO>();
	
	

	private int iRating = 0;					// 사용자가 입력한 리뷰점수
	
	

	/**
	 * Create the panel.
	 */
	public AttractionDetailUI() {
		initialize();
	}
	
	public void initialize() {
		
		setBackground(Color.WHITE);
		setBorder(null);
		setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1042, 611);
		panel.setLayout(null);
		add(panel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1042, 611);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		panel.add(scrollPane);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		panelContents = new JPanel();
		panelContents.setBounds(0, 0, 1042, 611);
		panelContents.setBorder(null);
		panelContents.setBackground(Color.WHITE);
		GridBagLayout gbl_panelContents = new GridBagLayout();
		gbl_panelContents.columnWidths 	= new int[] {1042};
		gbl_panelContents.rowHeights 	= new int[] {PANEL_ATTRACTION_DETAIL_HEIGHT, PANEL_REGIST_REVIEW_HEIGHT, PANEL_REVIEW_VIEW_HEIGHT};
		gbl_panelContents.columnWeights = new double[]	{ Double.MIN_VALUE};
		gbl_panelContents.rowWeights 	= new double[]	{ 3.0, 7.0, Double.MIN_VALUE};
		panelContents.setLayout(gbl_panelContents);
		
		attractionDetailUI(panelContents);
		registReviewUI(panelContents);
		reviewViewUI(panelContents);
		
		
		scrollPane.setViewportView(panelContents);
		
	}
	
	// 어트랙션 정보
	private void attractionDetailUI(JPanel panelContents) {
		// 어트랙션 정보
		panelAttractionDetail = new JPanel();
		panelAttractionDetail.setBorder(null);
		panelAttractionDetail.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelAttractionDetail = new GridBagConstraints();
		gbc_panelAttractionDetail.gridy = 0;
		gbc_panelAttractionDetail.gridx = 0;
		gbc_panelAttractionDetail.fill = GridBagConstraints.BOTH;
		panelContents.add(panelAttractionDetail, gbc_panelAttractionDetail);
		
		// 선택한 어트랙션 가져오기
		attractionDTO = (AttractionDTO)Common.getHm().get("ATTRACTION_DTO");
		panelAttractionDetail.setLayout(null);
		
		lblAttractionNm = new JLabel(attractionDTO.getAttractionNm());
		lblAttractionNm.setLocation(12, 10);
		lblAttractionNm.setSize(PANEL_ATTRACTION_DETAIL_WIDTH, 80);
		lblAttractionNm.setFont(new Font("Dialog", Font.BOLD, 40));
		panelAttractionDetail.add(lblAttractionNm);
		
		lblAttractionImage = new JImageLabel(attractionDTO.getPhotoCours(), ATTRACTION_IMAGE_WIDTH, ATTRACTION_IMAGE_HEIGHT);
		lblAttractionImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblAttractionImage.setBounds(12, 90, 545, 360);
		panelAttractionDetail.add(lblAttractionImage);
		
		lblAttractionDc = new JMultilineLabel(Utility.changeSpecialChar(attractionDTO.getAttractionDc()));
		lblAttractionDc.setFont(new Font("굴림", Font.PLAIN, 20));
		lblAttractionDc.setBounds(580, 90, 430, 190);
		panelAttractionDetail.add(lblAttractionDc);
		
		String oprat = 'Y' == attractionDTO.getOpratAt() ? "※ 운행 중입니다." : "※ 운행 중이 아닙니다." ;
		lblAttractionOprat = new JLabel(oprat);
		lblAttractionOprat.setFont(new Font("굴림", Font.PLAIN, 16));
		lblAttractionOprat.setBounds(580, 240, 430, 40);
		panelAttractionDetail.add(lblAttractionOprat);
		
		String attractionOperationTime = "※ 운행시간 : " + attractionDTO.getOperBeginTime() + " ~ " + attractionDTO.getOperEndTime();
		lblAttractionOperTime = new JLabel(attractionOperationTime);
		lblAttractionOperTime.setFont(new Font("굴림", Font.PLAIN, 16));
		lblAttractionOperTime.setBounds(580, 295, 430, 40);
		panelAttractionDetail.add(lblAttractionOperTime);
		
		PanelPosition pos = new PanelPosition(AVG_RATING_X_POINT, AVG_RATING_Y_POINT);
		double rating = selectRatingOfAttraction(attractionDTO.getAttractionNo());
		lblAttractionAvgRating = new JLabel(String.valueOf(rating));
		lblAttractionAvgRating.setFont(new Font("굴림", Font.PLAIN, 20));
		lblAttractionAvgRating.setBounds(pos.getX(), pos.getY(), AVG_RATING_LABEL_WIDTH, AVG_RATING_LABEL_HEIGHT);
		panelAttractionDetail.add(lblAttractionAvgRating);
		
		pos.moveX(AVG_RATING_LABEL_WIDTH);
		lblStar1 = new JImageLabel("");
		lblStar1.setBounds(pos.getX(), pos.getY(), STAR_SIZE_WIDTH, STAR_SIZE_HEIGHT);
		panelAttractionDetail.add(lblStar1);
		
		pos.moveX(STAR_SIZE_WIDTH);
		lblStar2 = new JImageLabel("");
		lblStar2.setBounds(pos.getX(), pos.getY(), STAR_SIZE_WIDTH, STAR_SIZE_HEIGHT);
		panelAttractionDetail.add(lblStar2);
		
		pos.moveX(STAR_SIZE_WIDTH);
		lblStar3 = new JImageLabel("");
		lblStar3.setBounds(pos.getX(), pos.getY(), STAR_SIZE_WIDTH, STAR_SIZE_HEIGHT);
		panelAttractionDetail.add(lblStar3);
		
		pos.moveX(STAR_SIZE_WIDTH);
		lblStar4 = new JImageLabel("");
		lblStar4.setBounds(pos.getX(), pos.getY(), STAR_SIZE_WIDTH, STAR_SIZE_HEIGHT);
		panelAttractionDetail.add(lblStar4);
		
		pos.moveX(STAR_SIZE_WIDTH);
		lblStar5 = new JImageLabel("");
		lblStar5.setBounds(pos.getX(), pos.getY(), STAR_SIZE_WIDTH, STAR_SIZE_HEIGHT);
		panelAttractionDetail.add(lblStar5);
		
		{// 	실제 별점 출력하는 부분
			int printStar = (int)(rating*2);
			
			System.out.println("printStar = " + printStar);
			stars = new ArrayList<JImage>();
			stars.add(lblStar1);
			stars.add(lblStar2);
			stars.add(lblStar3);
			stars.add(lblStar4);
			stars.add(lblStar5);
			
			Utility.printRating(stars, printStar, STAR_SIZE_WIDTH, STAR_SIZE_HEIGHT);
		}
		
		
	}// end-of-attractionDetailUI()
	
	// 리뷰 등록
	private void registReviewUI(JPanel panelContents) {
		
		// 리뷰 등록
		panelRegistReview = new JPanel();
		panelRegistReview.setBorder(null);
		panelRegistReview.setLayout(null);
		panelRegistReview.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelAttractionDetail = new GridBagConstraints();
		gbc_panelAttractionDetail.gridx = 0;
		gbc_panelAttractionDetail.gridy = 1;
		gbc_panelAttractionDetail.fill = GridBagConstraints.BOTH;
		panelContents.add(panelRegistReview, gbc_panelAttractionDetail);
		
		btnImageUpload = new JImageButton("img/form/upload.png");
		btnImageUpload.setBounds(12, 10, UPLOADED_IMAGE_WIDTH, UPLOADED_IMAGE_HEIGHT);
		panelRegistReview.add(btnImageUpload);
		btnImageUpload.addActionListener(this);
		
		lblValidTicketNo = new JLabel("티켓 번호를 입력하세요");
		lblValidTicketNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValidTicketNo.setBounds(244, 10, 182, 24);
		panelRegistReview.add(lblValidTicketNo);
		
		btnRegistValidTicketNo = new JButton("티켓번호입력");
		btnRegistValidTicketNo.setBounds(438, 10, 116, 25);
		panelRegistReview.add(btnRegistValidTicketNo);
		btnRegistValidTicketNo.addActionListener(this);
		
		PanelPosition pos = new PanelPosition(565, 10);
		btnStar1 = new JImageButton("img/form/empty_star.png", STAR_SIZE_WIDTH, STAR_SIZE_HEIGHT);
		btnStar1.setLocation(pos.getX(), pos.getY());
		btnStar1.setBorderPainted(false);
		btnStar1.setContentAreaFilled(false);
		btnStar1.setFocusPainted(false);
		panelRegistReview.add(btnStar1);
		btnStar1.addActionListener(this);
		
		pos.moveX(STAR_SIZE_WIDTH);
		btnStar2 = new JImageButton("img/form/empty_star.png", STAR_SIZE_WIDTH, STAR_SIZE_HEIGHT);
		btnStar2.setLocation(pos.getX(), pos.getY());
		btnStar2.setBorderPainted(false);
		btnStar2.setContentAreaFilled(false);
		btnStar2.setFocusPainted(false);
		panelRegistReview.add(btnStar2);
		btnStar2.addActionListener(this);
		
		pos.moveX(STAR_SIZE_WIDTH);
		btnStar3 = new JImageButton("img/form/empty_star.png", STAR_SIZE_WIDTH, STAR_SIZE_HEIGHT);
		btnStar3.setLocation(pos.getX(), pos.getY());
		btnStar3.setBorderPainted(false);
		btnStar3.setContentAreaFilled(false);
		btnStar3.setFocusPainted(false);
		panelRegistReview.add(btnStar3);
		btnStar3.addActionListener(this);
		
		pos.moveX(STAR_SIZE_WIDTH);
		btnStar4 = new JImageButton("img/form/empty_star.png", STAR_SIZE_WIDTH, STAR_SIZE_HEIGHT);
		btnStar4.setLocation(pos.getX(), pos.getY());
		btnStar4.setBorderPainted(false);
		btnStar4.setContentAreaFilled(false);
		btnStar4.setFocusPainted(false);
		panelRegistReview.add(btnStar4);
		btnStar4.addActionListener(this);
		
		pos.moveX(STAR_SIZE_WIDTH);
		btnStar5 = new JImageButton("img/form/empty_star.png", STAR_SIZE_WIDTH, STAR_SIZE_HEIGHT);
		btnStar5.setLocation(pos.getX(), pos.getY());
		btnStar5.setBorderPainted(false);
		btnStar5.setContentAreaFilled(false);
		btnStar5.setFocusPainted(false);
		panelRegistReview.add(btnStar5);
		btnStar5.addActionListener(this);
		
		inputStars = new ArrayList<JImage>();
		inputStars.add(btnStar1);
		inputStars.add(btnStar2);
		inputStars.add(btnStar3);
		inputStars.add(btnStar4);
		inputStars.add(btnStar5);
		
		btnRegistReview = new JButton("리뷰 등록");
		btnRegistReview.setBounds(918, 11, 97, 23);
		panelRegistReview.add(btnRegistReview);
		btnRegistReview.addActionListener(this);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(244, 44, 771, 58);
		panelRegistReview.add(layeredPane);
		
		taInputText = new JTextArea();
		taInputText.setBounds(0, 0, 771, 58);
		layeredPane.add(taInputText);
		taInputText.setColumns(10);
		taInputText.setLineWrap(true);
		taInputText.addKeyListener(this);
		
		lblCurrentInputText = new JLabel("(0");
		lblCurrentInputText.setHorizontalAlignment(SwingConstants.RIGHT);
		layeredPane.setLayer(lblCurrentInputText, 2);
		lblCurrentInputText.setBounds(651, 43, 57, 15);
		layeredPane.add(lblCurrentInputText);
		
		lblMaxInputText = new JLabel("/100)");
		layeredPane.setLayer(lblMaxInputText, 1);
		lblMaxInputText.setBounds(714, 43, 57, 15);
		layeredPane.add(lblMaxInputText);
		
		
	}// end-of-registReviewUI()
	
//	private void initializeReviewUI() {
//		
//		btnImageUpload.setImage("img/form/upload.png");
//		lblValidTicketNo.setText("티켓 번호를 입력하세요");
//		Utility.printRating(inputStars, 0, REVIEW_RATING_STAR_SIZE, REVIEW_RATING_STAR_SIZE);
//		
//		taInputText.setText("");
//		lblCurrentInputText.setText("(0");
//		lblMaxInputText.setText("/100)");
//		
//	}// end-of-registReviewUI()
	
	// 리뷰 출력
	private void reviewViewUI(JPanel panelContents) {
		
		// 리뷰 출력
		panelReviewView = new JPanel();
		panelReviewView.setBorder(null);
		panelReviewView.setLayout(new GridLayout(0, 1, 5, 5));
		panelReviewView.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelAttractionDetail = new GridBagConstraints();
		gbc_panelAttractionDetail.gridx = 0;
		gbc_panelAttractionDetail.gridy = 2;
		gbc_panelAttractionDetail.fill = GridBagConstraints.BOTH;
		panelContents.add(panelReviewView, gbc_panelAttractionDetail);
		
		reviewList = selectReview(attractionDTO.getAttractionNo());
		reviewView(reviewList, panelReviewView);
		
	}
	
	
	// 리뷰 전체 출력
	public void reviewView(ArrayList<ReviewDTO> reviewList, JPanel panelContents) {
				
		for(int i = 0; i < reviewList.size(); i++) {
			ReviewDTO reviewDTO;			
			reviewDTO = reviewList.get(i);
			reviewView(reviewDTO, panelContents);
		}
	}
		
	// 하나의 리뷰 출력
	public void reviewView(ReviewDTO reviewDTO, JPanel panelContents) {
		
		// 패널, 컴포넌트 위치 설정
		JPanel panelReviewView = new JPanel();
		panelReviewView.setBackground(Color.WHITE);
		panelReviewView.setSize(PANEL_REVIEW_WIDTH, PANEL_REVIEW_HEIGHT);
		panelReviewView.setAlignmentX(CENTER_ALIGNMENT);
		panelContents.add(panelReviewView);
		
		// 리뷰 패널의 왼쪽은 사진
		String photoCours = reviewDTO.getPhotoCours();
		if (photoCours == null || photoCours.isEmpty()) {
			photoCours = "img/img/no_image.jpg";
		}
		JImageButton btnReviewImage = new JImageButton(photoCours, REVIEW_IMAGE_WIDTH, REVIEW_IMAGE_HEIGHT);// 164, 112
		panelReviewView.add(btnReviewImage);
		btnReviewImage.addActionListener(this);
		
		
		// 리뷰 패널의 오른쪽은 리뷰 내용
		JPanel reviewInfoPanel = new JPanel();
		reviewInfoPanel.setBackground(Color.WHITE);
		reviewInfoPanel.setSize(254, 120);// 254, 120
		reviewInfoPanel.setLayout(new BoxLayout(reviewInfoPanel, BoxLayout.Y_AXIS));
		panelReviewView.add(reviewInfoPanel);
		
		JPanel reviewInfoTop = new JPanel();
		reviewInfoTop.setBackground(Color.WHITE);
		reviewInfoTop.setSize(254, 60);// 254, 122
		reviewInfoPanel.setAlignmentY(LEFT_ALIGNMENT);
		reviewInfoPanel.add(reviewInfoTop);
		
		// 티켓번호 출력
		String ticketNo = reviewDTO.getResveNo()+reviewDTO.getIssuNo();
		ticketNo = ticketNo.substring(0, 4) + "****";
		JLabel lblTicketNo = new JLabel(ticketNo);
		lblTicketNo.setSize(120, 36);
		reviewInfoTop.add(lblTicketNo);
		
		JImageLabel lblReviewStar1 = new JImageLabel("★");
		lblReviewStar1.setSize(REVIEW_RATING_STAR_SIZE, REVIEW_RATING_STAR_SIZE);
		reviewInfoTop.add(lblReviewStar1);
		
		JImageLabel lblReviewStar2 = new JImageLabel("★");
		lblReviewStar2.setSize(REVIEW_RATING_STAR_SIZE, REVIEW_RATING_STAR_SIZE);
		reviewInfoTop.add(lblReviewStar2);
		
		JImageLabel lblReviewStar3 = new JImageLabel("★");
		lblReviewStar3.setSize(REVIEW_RATING_STAR_SIZE, REVIEW_RATING_STAR_SIZE);
		reviewInfoTop.add(lblReviewStar3);
		
		JImageLabel lblReviewStar4 = new JImageLabel("★");
		lblReviewStar4.setSize(REVIEW_RATING_STAR_SIZE, REVIEW_RATING_STAR_SIZE);
		reviewInfoTop.add(lblReviewStar4);
		
		JImageLabel lblReviewStar5 = new JImageLabel("★");
		lblReviewStar5.setSize(REVIEW_RATING_STAR_SIZE, REVIEW_RATING_STAR_SIZE);
		reviewInfoTop.add(lblReviewStar5);
		
		ArrayList<JImage> starList = new ArrayList<JImage>();
		starList.add(lblReviewStar1);
		starList.add(lblReviewStar2);
		starList.add(lblReviewStar3);
		starList.add(lblReviewStar4);
		starList.add(lblReviewStar5);
		Utility.printRating(starList, reviewDTO.getRating()*2, REVIEW_RATING_STAR_SIZE, REVIEW_RATING_STAR_SIZE);
		
		// 리뷰 작성한 날짜 출력(DB업데이트 해야 함)
		String reviewDate = reviewDTO.getRegistDe();
		if (reviewDate == null || reviewDate.isEmpty()) {
			reviewDate = "00000000";// 미확인 날짜
		}
		JLabel lblReviewDate = new JLabel(Utility.changeToDateFormat(reviewDate));
		lblReviewDate.setHorizontalAlignment(SwingConstants.LEADING);
		lblReviewDate.setSize(280, 36);
		reviewInfoTop.add(lblReviewDate);
		
		// 리뷰 내용 출력
		JLabel lblReviewCn = new JLabel(reviewDTO.getCn());
		lblReviewCn.setSize(432, 46);
		lblReviewCn.setAlignmentY(JPanel.CENTER_ALIGNMENT);
		reviewInfoPanel.add(lblReviewCn);
		
		JImageLabel lblReviewHorizontalLine = new JImageLabel("img/form/horizontal_line.png", 432, 10);
		reviewInfoPanel.add(lblReviewHorizontalLine);
	}
	
		
	@Override
	public void actionPerformed(ActionEvent e) {

		// 티켓번호등록 버튼을 눌렀을 때
		if (e.getSource() == btnRegistValidTicketNo) {
			String attractionNo = attractionDTO.getAttractionNo();
			Common.getHm().put("ATTRACTION_NO", attractionNo);
			
			new CheckValidTicketNoPopup();
			
			String sTicketNo = (String)Common.getHm().get("VALID_TICKET_NO");
			if (sTicketNo != null) {
				lblValidTicketNo.setText(sTicketNo);
				Common.getHm().remove("VALID_TICKET_NO");
			}
		}
		
		// 사용자가 리뷰를 입력할 때, 별점을 설정하는 부분
		else if (e.getSource() == btnStar5) {
			System.out.println("printStar = " + STAR5);
			iRating = 5;
			Utility.printRating(inputStars, STAR0, STAR_SIZE_WIDTH, STAR_SIZE_HEIGHT);	// 빈 별로 채움
			Utility.printRating(inputStars, STAR5, STAR_SIZE_WIDTH, STAR_SIZE_HEIGHT);	// 그리고 나서 5별점 세팅
		}
		
		else if (e.getSource() == btnStar4) {
			System.out.println("printStar = " + STAR4);
			iRating = 4;
			Utility.printRating(inputStars, STAR0, STAR_SIZE_WIDTH, STAR_SIZE_HEIGHT);	// 빈 별로 채움
			Utility.printRating(inputStars, STAR4, STAR_SIZE_WIDTH, STAR_SIZE_HEIGHT);	// 그리고 나서 4별점 세팅
		}
		
		else if (e.getSource() == btnStar3) {
			System.out.println("printStar = " + STAR3);
			iRating = 3;
			Utility.printRating(inputStars, STAR0, STAR_SIZE_WIDTH, STAR_SIZE_HEIGHT);	// 빈 별로 채움
			Utility.printRating(inputStars, STAR3, STAR_SIZE_WIDTH, STAR_SIZE_HEIGHT);	// 그리고 나서 3별점 세팅
		}
		
		else if (e.getSource() == btnStar2) {
			System.out.println("printStar = " + STAR2);
			iRating = 2;
			Utility.printRating(inputStars, STAR0, STAR_SIZE_WIDTH, STAR_SIZE_HEIGHT);	// 빈 별로 채움
			Utility.printRating(inputStars, STAR2, STAR_SIZE_WIDTH, STAR_SIZE_HEIGHT);	// 그리고 나서 2별점 세팅
		}
		
		else if (e.getSource() == btnStar1) {
			System.out.println("printStar = " + STAR1);
			iRating = 1;
			Utility.printRating(inputStars, STAR0, STAR_SIZE_WIDTH, STAR_SIZE_HEIGHT);	// 빈 별로 채움
			Utility.printRating(inputStars, STAR1, STAR_SIZE_WIDTH, STAR_SIZE_HEIGHT);	// 그리고 나서 1별점 세팅
		}
		
		// 파일 업로드 팝업 창
		else if (e.getSource() == btnImageUpload) {
			
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"JPG & GIF Images"
					, "jpg", "gif");
			chooser.setFileFilter(filter);
			
			int ret = chooser.showOpenDialog(null);
			if (ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경로", JOptionPane.WARNING_MESSAGE);
				
			}
			
			if (chooser.getSelectedFile() == null) {
				btnImageUpload.setImage("img/form/upload.png", UPLOADED_IMAGE_WIDTH, UPLOADED_IMAGE_HEIGHT);
			}
			else {
				String filePath = chooser.getSelectedFile().getPath();
				System.out.println(filePath);
				btnImageUpload.setImage(filePath, UPLOADED_IMAGE_WIDTH, UPLOADED_IMAGE_HEIGHT);
				Common.getHm().put("REVIEW_IMAGE_SRC", filePath);
			}
			
		}
		
		// 리뷰 등록 버튼
		else if (e.getSource() == btnRegistReview) {
			
			if (lblValidTicketNo == null | lblValidTicketNo.getText().length() <= 0) {
				JOptionPane.showMessageDialog(null, "티켓번호를 입력하세요");
				return;
			}
			if (iRating == 0) {
				JOptionPane.showMessageDialog(null, "별점을 입력하세요");
				return;
			}
			
			// 입력할 데이터
			ReviewDTO reviewDTO = new ReviewDTO();
			reviewDTO.setAttractionNo(attractionDTO.getAttractionNo());
			reviewDTO.setResveNo(lblValidTicketNo.getText().substring(0, TICKET_NO_LENGTH)); 	// 티켓번호 = 예약번호(6자리) + 발급번호(6자리), 
			reviewDTO.setIssuNo(lblValidTicketNo.getText().substring(TICKET_NO_LENGTH));	 	// 티켓번호 등록에서 유효한 티켓번호 검증했음(자릿수 등)
			reviewDTO.setCn(taInputText.getText());;
			reviewDTO.setRating(iRating);
			String photoSrc = (String)Common.getHm().get("REVIEW_IMAGE_SRC");
			Common.getHm().remove("REVIEW_IMAGE_SRC");
			String fileNm = null;
			if (photoSrc != null || "".equals(photoSrc) == false) {
				// 파일 이름이 너무 길 경우 주의할 것!
				fileNm 	
				= "img/review/"
				+ reviewDTO.getAttractionNo()
				+ "_"+reviewDTO.getResveNo()
				+ "_"+reviewDTO.getIssuNo()
				+ ".jpg";
				Utility.copyFile(photoSrc, fileNm, Utility.IMG_CODE_REVIEW);
			}
			reviewDTO.setPhotoCours(fileNm);
			reviewDTO.setRegistDe("DB날짜 입력할 예정");
			
			
//			print reviewDTO
			System.out.println("attractionNo = \t"+reviewDTO.getAttractionNo());
			System.out.println("resveNo = \t"+reviewDTO.getResveNo());
			System.out.println("issuNo = \t"+reviewDTO.getIssuNo());
			System.out.println("rating = \t"+reviewDTO.getRating());
			System.out.println("cn = \t\t"+reviewDTO.getCn());
			System.out.println("photoCours = \t"+reviewDTO.getPhotoCours());
			System.out.println("registDe = \t"+reviewDTO.getRegistDe());
			
			// 리뷰 등록
			insertReview(reviewDTO);
			// 리뷰가 등록 된 후 리뷰등록UI 초기화
//			initializeReviewUI();
			
			JOptionPane.showMessageDialog(panel, "리뷰가 등록되었습니다.");
			// 새로 생성 하기 전 필요한 정보 담아두기
			Common.getHm().put("ATTRACTION_DTO", attractionDTO);
			
			// 새로 생성
			AttractionDetailUI attractionDetailUI = new AttractionDetailUI();
			attractionDetailUI.setBounds(0, 0, 1042, 611);
			JPanel panelCommon = (JPanel)Common.getHm().get("PANEL_COMMON");
			Utility.changePanel(panelCommon, attractionDetailUI);
		}
		
	}
	
	// 리뷰 작성시, 키보드 눌렀을 때 처리들
	@Override
	public void keyPressed(KeyEvent e) {
		
		String text = taInputText.getText();
		if (text.length() > MAX_REVIEW_TEXT) {
			taInputText.setText(text.substring(0 , MAX_REVIEW_TEXT));
		}
		
		// 글자 수 업데이트하기
		lblCurrentInputText.setText("(" + taInputText.getText().length());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	
	// private functions
	
	// DB에서 어트랙션의 평균별점 가져오기
	private double selectRatingOfAttraction(String attractionNo) {
		
		double 	rating 	= .0;	// 어트랙션의 평균 별점
		int 	sum 	= 0;	// 어트랙션의 별점 합계
		int 	count 	= 0;	// 어트랙션의 리뷰 개수
		
		try {
			
			DBController.connect();
			
			String sql
			= "SELECT 	SUM(RATING), COUNT(*) 	"
			+ "FROM 	REVIEW 					"
			+ "WHERE 	ATTRACTION_NO = ?		";
			
			DBController.setPstmt(sql);
			DBController.getPstmt().setString(1, attractionNo);
			
			ResultSet rs = DBController.select();
			
			if(rs.next()) {
				
				sum = rs.getInt(1);
				count = rs.getInt(2);
				
				long tmp = (long)((double)sum/count * 10 + 0.5);
				rating = (double)tmp/10;
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
		
		return rating;
		
	}
	
	// 리뷰 DB insert
	private void insertReview(ReviewDTO reviewDTO) {
		
		
		try {
			
			DBController.connect();
			
			String sql
			= "INSERT INTO 	REVIEW 												"
//			+ "( ''ATRRACTION_NO'' , ''RESVE_NO'' , ''ISSU_NO''					"
//			+ ", ''CN'' , ''RATING'' , ''PHOTO_COURS'' , ''REGIST_DE'' )		"
			+ "VALUES ( ? , ? , ? , ? , ? , ? , TO_CHAR(SYSDATE, 'YYYYMMDD') )	";
			
			DBController.setPstmt(sql);
			DBController.getPstmt().setString(1, reviewDTO.getAttractionNo());
			DBController.getPstmt().setString(2, reviewDTO.getResveNo());
			DBController.getPstmt().setString(3, reviewDTO.getIssuNo());
			DBController.getPstmt().setString(4, reviewDTO.getCn());
			DBController.getPstmt().setInt(5, reviewDTO.getRating());
			DBController.getPstmt().setString(6, reviewDTO.getPhotoCours());
			
			
			int rs = DBController.insert();
			
			if(rs <= 0) {
				// insert 실패
				JOptionPane.showMessageDialog(null, "오류:등록에 실패하였습니다.");
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
			
			
	}//end-of-insertReview
	
	
	// 리뷰 DB select
	private ArrayList<ReviewDTO> selectReview(String attractionNo) {
		ArrayList<ReviewDTO> rList = new ArrayList<ReviewDTO>();
		
		try {
			
			DBController.connect();
			
			String sql
			= "SELECT * 				"
			+ "FROM REVIEW 				"
			+ "WHERE ATTRACTION_NO = ?	";
			
			DBController.setPstmt(sql);
			DBController.getPstmt().setString(1, attractionNo);
			
			
			ResultSet rs = DBController.select();
						
			while(rs.next()) {
				ReviewDTO reviewDTO = new ReviewDTO();
				reviewDTO.setAttractionNo(rs.getString("ATTRACTION_NO"));
				reviewDTO.setResveNo(rs.getString("RESVE_NO"));
				reviewDTO.setIssuNo(rs.getString("ISSU_NO"));
				reviewDTO.setCn(rs.getString("CN"));
				reviewDTO.setRating(rs.getInt("RATING"));
				reviewDTO.setPhotoCours(rs.getString("PHOTO_COURS"));
				reviewDTO.setRegistDe(rs.getString("REGIST_DE"));
				
				rList.add(reviewDTO);
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
			
		return rList;
		
	}//end-of-insertReview
}
