package lib.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dto.ReviewDTO;
import lib.BizException;
import lib.DBController;
import util.Common;
import util.Utility;

public class ReviewPane extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private JPanel panel;
	private JPanel panelContents;
	
	
	private JPanel panelReview;
	private JButton btnReviewImage;
	private JLabel lblTicketNo;
	private JImageLabel lblStar1;
	private JImageLabel lblStar2;
	private JImageLabel lblStar3;
	private JImageLabel lblStar4;
	private JImageLabel lblStar5;
	private JLabel lblReviewDate;	// 리뷰 작성한 날짜
	private JLabel lblReviewCn;		// 리뷰 내용
	
	private final int PADDING_X = 14;
	private final int PADDING_Y = 12;
	private final int PANEL_REVIEW_WIDTH 	= 800;	// 리뷰 패널의 넓이
	private final int PANEL_REVIEW_HEIGHT 	= 122;	// 리뷰 패널의 높이
	private final int REVIEW_IMAGE_WIDTH 	= 244;
	private final int REVIEW_IMAGE_HEIGHT 	= 122;
	private final int REVIEW_RATING_STAR_SIZE = 25;

	/**
	 * Create the panel.
	 */
	public ReviewPane() {
		
		initialize();

	}
	
	public void initialize() {
		
		setLayout(null);
				
		panel = new JPanel();
		panel.setLayout(null);
		add(panel);
				
		panelContents = new JPanel();
		panelContents.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelContents.setLayout(new BoxLayout(panelContents, BoxLayout.Y_AXIS));
		panel.add(panelContents);
		
		
		String attractionNO = (String)Common.getHm().get("REVIEW_ATTRACTION_NO");
		Common.getHm().remove("REVIEW_ATTRACTION_NO");
		ArrayList<ReviewDTO> reviewList = selectReview(attractionNO);
		reviewView(reviewList, panelContents);
		
		
	}
	
	// 리뷰 전체 출력
	public void reviewView(ArrayList<ReviewDTO> reviewList, JPanel panelContents) {
		ReviewDTO reviewDTO;
		
		for(int i = 0; i < reviewList.size(); i++) {
			reviewDTO = reviewList.get(i);
			System.out.println(reviewDTO.getResveNo()+reviewDTO.getIssuNo()+"생성!");
			reviewView(reviewDTO, panelContents);
		}
	}
	
	// 하나의 리뷰 출력
	public void reviewView(ReviewDTO reviewDTO, JPanel panelContents) {
		panelReview = new JPanel();
		panelReview.setBounds(PADDING_X, PADDING_Y, PANEL_REVIEW_WIDTH, PANEL_REVIEW_HEIGHT);
		panelReview.setLayout(null);
		panelContents.add(panelReview);
		
		String photoCours = reviewDTO.getPhotoCours();
		if (photoCours == null || "".equals(photoCours) || "null".equalsIgnoreCase(photoCours)) {
			photoCours = "img/img/no_image.jpg";
		}
		System.out.println(photoCours);
		btnReviewImage = new JImageButton(reviewDTO.getPhotoCours(), REVIEW_IMAGE_WIDTH, REVIEW_IMAGE_HEIGHT);
		btnReviewImage.setBounds(0, 0, REVIEW_IMAGE_WIDTH, REVIEW_IMAGE_HEIGHT);
		panelReview.add(btnReviewImage);
		btnReviewImage.addActionListener(this);
		
		// 티켓번호 출력
		String ticketNo = reviewDTO.getResveNo()+reviewDTO.getIssuNo();
		ticketNo = ticketNo.substring(0, 4) + "****";
		lblTicketNo = new JLabel(ticketNo);
		lblTicketNo.setBounds(258, 12, 255, 36);
		panelReview.add(lblTicketNo);
		
		lblStar1 = new JImageLabel("★");
		lblStar1.setBounds(525, 16, REVIEW_RATING_STAR_SIZE, REVIEW_RATING_STAR_SIZE);
		panelReview.add(lblStar1);
		
		lblStar2 = new JImageLabel("★");
		lblStar2.setBounds(550, 16, REVIEW_RATING_STAR_SIZE, REVIEW_RATING_STAR_SIZE);
		panelReview.add(lblStar2);
		
		lblStar3 = new JImageLabel("★");
		lblStar3.setBounds(575, 16, REVIEW_RATING_STAR_SIZE, REVIEW_RATING_STAR_SIZE);
		panelReview.add(lblStar3);
		
		lblStar4 = new JImageLabel("★");
		lblStar4.setBounds(600, 16, REVIEW_RATING_STAR_SIZE, REVIEW_RATING_STAR_SIZE);
		panelReview.add(lblStar4);
		
		lblStar5 = new JImageLabel("★");
		lblStar5.setBounds(625, 16, REVIEW_RATING_STAR_SIZE, REVIEW_RATING_STAR_SIZE);
		panelReview.add(lblStar5);
		
		ArrayList<JImage> starList = new ArrayList<JImage>();
		starList.add(lblStar1);
		starList.add(lblStar2);
		starList.add(lblStar3);
		starList.add(lblStar4);
		starList.add(lblStar5);
		Utility.printRating(starList, reviewDTO.getRating()*2, REVIEW_RATING_STAR_SIZE, REVIEW_RATING_STAR_SIZE);
		
		// 리뷰 작성한 날짜 출력(DB업데이트 해야 함)
		lblReviewDate = new JLabel("날짜 출력");
		lblReviewDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblReviewDate.setBounds(710, 12, 280, 36);
		panelReview.add(lblReviewDate);
		
		// 리뷰 내용 출력
		lblReviewCn = new JLabel(reviewDTO.getCn());
		lblReviewCn.setBounds(258, 52, 732, 58);
		panelReview.add(lblReviewCn);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 이미지 클릭했을 때, 확장 (구현 예정?)
		JOptionPane.showMessageDialog(null, "리뷰 사진 클릭!");
		
	}
	
	
	public ArrayList<ReviewDTO> selectReview(String attractionNo) {
		
		ArrayList<ReviewDTO> reviewList = null;
		
		try {
			
			reviewList = new ArrayList<ReviewDTO>();
			DBController.connect();
			
			String sql
			= "SELECT 	* 					"
			+ "FROM 	REVIEW 				"
			+ "WHERE 	ATTRACTION_NO = ? 	";
//			+ "ORDER BY REVIEW_TIME DESC	";	// DDL 수정 후 업데이트 할 것.
			
			DBController.setPstmt(sql);
			DBController.getPstmt().setString(1, attractionNo);
			
			ResultSet rs = DBController.select();
			
			while(rs.next()) {
				ReviewDTO reviewDTO = new ReviewDTO();
				reviewDTO.setAttractionNo	(rs.getString("ATTRACTION_NO"));
				reviewDTO.setResveNo		(rs.getString("RESVE_NO"));
				reviewDTO.setIssuNo			(rs.getString("ISSU_NO"));
				reviewDTO.setCn				(rs.getString("CN"));
				reviewDTO.setRating			(rs.getInt("RATING"));
				reviewDTO.setPhotoCours		(rs.getString("PHOTO_COURS"));
				
				reviewList.add(reviewDTO);
			}
			DBController.close();
			
		} catch (BizException e) {
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reviewList;
		
	}
	
}
