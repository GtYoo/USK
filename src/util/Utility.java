package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

import dto.CmmnCodeDTO;
import lib.BizException;
import lib.DBController;
import lib.component.JImage;

// version 1.0.1. (202103/13 update)
// 	- copyFIle() 추가
public class Utility {
	
	// 이미지 저장 위치를 지정하는 코드
	public static final int IMG_CODE_LOGO 			= 0;	// 로고 이미지
	public static final int IMG_CODE_MAIN 			= 1;	// 메인 화면에서 사용하는 이미지
	public static final int IMG_CODE_FORM 			= 2;	// 특정 패널에서 사용하는 이미지
	public static final int IMG_CODE_TICKET 		= 3;	// 티켓 이미지
	public static final int IMG_CODE_ATTRACTION 	= 4;	// 어트랙션 이미지
	public static final int IMG_CODE_REVIEW 		= 5;	// 리뷰 이미지
	public static final int IMG_CODE_ADI_FCLTY 		= 6;	// 부가시설 이미지
	public static final int IMG_CODE_REPRSNT_GOODS 	= 7;	// 대표상품 이미지
	public static final int IMG_CODE_HOTEL 			= 8;	// 호텔 이미지
	
	// 예약 목록에서 결제 진행 시 사용하는 키값들
	public static final String KEY_RESVE_NO_LIST = "RESVE_NO_LIST";
	public static final String KEY_RESVE_CMMN_CODE_PC = "RESVE_CMMN_CODE_PC";
	public static final String KEY_RESVE_DE = "RESVE_DE";
	public static final String KEY_ENTNC_DE = "ENTNC_DE";
	public static final String KEY_TICKET_PHOTO_COURS = "TICKET_PHOTO_COURS";
	public static final String KEY_ADULT_CNT = "ADULT_CNT";
	public static final String KEY_CHILD_CNT = "CHILD_CNT";
	public static final String KEY_OLD_CNT = "OLD_CNT";
	public static final String KEY_TOTAL_PC = "TOTAL_PC";
	public static final String KEY_TICKET_NM = "TICKET_NM";
	public static final String KEY_TICKET_CNT = "TICKET_CNT";

	// 패널 교체
	public static void changePanel(JPanel panel, JPanel newPanel) {
		panel.setVisible(false);			// panel unvisible
		panel.removeAll();					// panel 내용 제거
		panel.add(newPanel);				// panel에 해당 UI 추가
		newPanel.setVisible(true);			// 추가된 UI visible
		panel.setVisible(true);				// panel visible
	}
	
	// 특수 문자 변환(문자열 읽을 때, 개행문자 처리)
	public static String changeSpecialChar(String str) {
		StringBuffer changedStr = new StringBuffer("");
		String[] strList;
		
		strList = str.split("\\\\r\\\\n");
		int limit = strList.length-1;
		for(int i = 0; i < limit; i++) {
			changedStr.append(strList[i]);
			changedStr.append("\n");
		}
		changedStr.append(strList[strList.length-1]);
		
		return changedStr.toString();
	}
	
	// 특수 문자 변환
	public static String changeSpecialChar(String str, String specialChar, String replacedChar) {
		StringBuffer changedStr = new StringBuffer("");
		String[] strList;
		
		strList = str.split(specialChar);
		int limit = strList.length-1;
		for(int i = 0; i < limit; i++) {
			changedStr.append(strList[i]);
			changedStr.append(replacedChar);
		}
		changedStr.append(strList[strList.length-1]);
		
		return changedStr.toString();
	}
	
	public static String changeToDateFormat(String strDate) {
		
		StringBuffer formatDate = new StringBuffer("");
		
		// 8자리:yyyymmdd, 10자리:yyyy-mm-dd
//		if (strDate.length() != 8 || strDate.length() != 10) {
//			System.out.println("에러:출력용 Date 포멧으로 변환을 할 수 없습니다.");
//			return "";
//		}
//		
		if (strDate.length() >= 10 && (strDate.charAt(4) == '-' || strDate.charAt(4) == '/')) {
			formatDate.append(strDate.substring(0, 4));
			formatDate.append(strDate.substring(5, 7));
			formatDate.append(strDate.substring(8));
		}
		else if (strDate.length() == 8){
			formatDate.append(strDate.substring(0, 4));
			formatDate.append("-");
			formatDate.append(strDate.substring(4, 6));
			formatDate.append("-");
			formatDate.append(strDate.substring(6));
		}
		
		return formatDate.toString();
	}
	
	public static String changeToCurrencyFormat(String strCurrency) {
		System.out.println("strCurr =" + strCurrency);
		StringBuffer formatCurrency = new StringBuffer("￦");
		
		int comma = strCurrency.length() % 3;
		
		for (int i = 0; i < strCurrency.length(); i++) {
			if (i == comma && comma != 0) {
				formatCurrency.append(",");
				comma += 3;
			}
			formatCurrency.append(strCurrency.charAt(i));
		}
		System.out.println(formatCurrency.toString());
		return formatCurrency.toString();
	}
	
	// 별점 출력용
	// 10: 별 5개 , 9: 별 4개반 , ... , 0: 별 0개(빈별5개)
	public static void printRating(ArrayList<JImage> ilbl, int cnt, int width, int height) {
		
		// 인터페이스로 처리
		// JImage -> JImageLabel, JImageButton
		final String EMPTY_STAR_SRC = "img/form/empty_star.png";	// 빈 별 이미지 위치
		final String HALF_STAR_SRC = "img/form/half_star.png";		// 반 별 이미지 위치
		final String FULL_STAR_SRC = "img/form/full_star.png";		// 꽉찬 별 이미지 위치
		
		if (cnt < 0 || cnt > 10 ) {
			return;
		}
		
		
		switch(cnt) {
		case 10:
			ilbl.get(4).setImage(FULL_STAR_SRC, width, height);
			ilbl.get(3).setImage(FULL_STAR_SRC, width, height);
			ilbl.get(2).setImage(FULL_STAR_SRC, width, height);
			ilbl.get(1).setImage(FULL_STAR_SRC, width, height);
			ilbl.get(0).setImage(FULL_STAR_SRC, width, height);
			break;
		case 9:
			ilbl.get(4).setImage(HALF_STAR_SRC, width, height);
			ilbl.get(3).setImage(FULL_STAR_SRC, width, height);
			ilbl.get(2).setImage(FULL_STAR_SRC, width, height);
			ilbl.get(1).setImage(FULL_STAR_SRC, width, height);
			ilbl.get(0).setImage(FULL_STAR_SRC, width, height);
			break;
		case 8:
			ilbl.get(4).setImage(EMPTY_STAR_SRC, width, height);
			ilbl.get(3).setImage(FULL_STAR_SRC, width, height);
			ilbl.get(2).setImage(FULL_STAR_SRC, width, height);
			ilbl.get(1).setImage(FULL_STAR_SRC, width, height);
			ilbl.get(0).setImage(FULL_STAR_SRC, width, height);
			break;
		case 7:
			ilbl.get(4).setImage(EMPTY_STAR_SRC, width, height);
			ilbl.get(3).setImage(HALF_STAR_SRC, width, height);
			ilbl.get(2).setImage(FULL_STAR_SRC, width, height);
			ilbl.get(1).setImage(FULL_STAR_SRC, width, height);
			ilbl.get(0).setImage(FULL_STAR_SRC, width, height);
			break;
		case 6:
			ilbl.get(4).setImage(EMPTY_STAR_SRC, width, height);
			ilbl.get(3).setImage(EMPTY_STAR_SRC, width, height);
			ilbl.get(2).setImage(FULL_STAR_SRC, width, height);
			ilbl.get(1).setImage(FULL_STAR_SRC, width, height);
			ilbl.get(0).setImage(FULL_STAR_SRC, width, height);
			break;
		case 5:
			ilbl.get(4).setImage(EMPTY_STAR_SRC, width, height);
			ilbl.get(3).setImage(EMPTY_STAR_SRC, width, height);
			ilbl.get(2).setImage(HALF_STAR_SRC, width, height);
			ilbl.get(1).setImage(FULL_STAR_SRC, width, height);
			ilbl.get(0).setImage(FULL_STAR_SRC, width, height);
			break;
		case 4:
			ilbl.get(4).setImage(EMPTY_STAR_SRC, width, height);
			ilbl.get(3).setImage(EMPTY_STAR_SRC, width, height);
			ilbl.get(2).setImage(EMPTY_STAR_SRC, width, height);
			ilbl.get(1).setImage(FULL_STAR_SRC, width, height);
			ilbl.get(0).setImage(FULL_STAR_SRC, width, height);
			break;
		case 3:
			ilbl.get(4).setImage(EMPTY_STAR_SRC, width, height);
			ilbl.get(3).setImage(EMPTY_STAR_SRC, width, height);
			ilbl.get(2).setImage(EMPTY_STAR_SRC, width, height);
			ilbl.get(1).setImage(HALF_STAR_SRC, width, height);
			ilbl.get(0).setImage(FULL_STAR_SRC, width, height);
			break;
		case 2:
			ilbl.get(4).setImage(EMPTY_STAR_SRC, width, height);
			ilbl.get(3).setImage(EMPTY_STAR_SRC, width, height);
			ilbl.get(2).setImage(EMPTY_STAR_SRC, width, height);
			ilbl.get(1).setImage(EMPTY_STAR_SRC, width, height);
			ilbl.get(0).setImage(FULL_STAR_SRC, width, height);
			break;
		case 1:
			ilbl.get(4).setImage(EMPTY_STAR_SRC, width, height);
			ilbl.get(3).setImage(EMPTY_STAR_SRC, width, height);
			ilbl.get(2).setImage(EMPTY_STAR_SRC, width, height);
			ilbl.get(1).setImage(EMPTY_STAR_SRC, width, height);
			ilbl.get(0).setImage(HALF_STAR_SRC, width, height);
			break;
		case 0:
			// 디폴트 값 별 갯수 0 : 빈별 5개
			ilbl.get(4).setImage(EMPTY_STAR_SRC, width, height);
			ilbl.get(3).setImage(EMPTY_STAR_SRC, width, height);
			ilbl.get(2).setImage(EMPTY_STAR_SRC, width, height);
			ilbl.get(1).setImage(EMPTY_STAR_SRC, width, height);
			ilbl.get(0).setImage(EMPTY_STAR_SRC, width, height);
			break;
		}
	}
	
	// 파일 복사
	public static void copyFile(String originFilePath, String fileNm, int IMG_CODE) {
		
		// 여기에 저장할 위치 지정하기(상대주소)
//		final String LOGO_IMG_PATH 				= "img/mainImage/";
//		final String MAIN_IMG_PATH 				= "img/mainImage/";
//		final String FORM_IMG_PATH 				= "img/form/";
//		final String TICKET_IMG_PATH 			= "img/ticket/";
//		final String ATTRACTION_IMG_PATH 		= "img/attraction/";
//		final String REVIEW_IMG_PATH 			= "img/review/";
//		final String ADI_FCLTY_IMG_PATH 		= "img/adi_fclty/";
//		final String REPRSNT_GOODS_IMG_PATH 	= "img/reprsnt_goods/";
//		final String HOTEL_IMG_PATH 			= "img/hotel/";
		
		String toFilePath;
		
				
		if (fileNm == null || "".equals(fileNm)) {
			System.out.println("파일 이름이 없습니다!");
			return;
		}
		if (originFilePath == null || "".equals(originFilePath)) {
			System.out.println("저장할 파일이 없습니다!");
			return;
		}
		
//		// 원본 파일의 확장명 뽑아내기
//		String extension = originFilePath.substring(originFilePath.lastIndexOf("."));
//		
//		switch(IMG_CODE) {
//		default:
//		case IMG_CODE_LOGO:
//			toFilePath = LOGO_IMG_PATH + fileNm + extension;
//			break;
//		case IMG_CODE_MAIN:
//			toFilePath = MAIN_IMG_PATH + fileNm + extension;
//			break;
//		case IMG_CODE_FORM:
//			toFilePath = FORM_IMG_PATH + fileNm + extension;
//			break;
//		case IMG_CODE_TICKET:
//			toFilePath = TICKET_IMG_PATH + fileNm + extension;
//			break;
//		case IMG_CODE_ATTRACTION:
//			toFilePath = ATTRACTION_IMG_PATH + fileNm + extension;
//			break;
//		case IMG_CODE_REVIEW:
//			toFilePath = REVIEW_IMG_PATH + fileNm + extension;
//			break;
//		case IMG_CODE_ADI_FCLTY:
//			toFilePath = ADI_FCLTY_IMG_PATH + fileNm + extension;
//			break;
//		case IMG_CODE_REPRSNT_GOODS:
//			toFilePath = REPRSNT_GOODS_IMG_PATH + fileNm + extension;
//			break;
//		case IMG_CODE_HOTEL:
//			toFilePath = HOTEL_IMG_PATH + fileNm + extension;
//			break;
//		}
		toFilePath = fileNm;
		
		// 파일 처리를 위한 객체
		File oriFile = new File(originFilePath);
		File copyFile = new File(toFilePath);
		
		// 파일 복사
		try {
			
			FileInputStream fis = new FileInputStream(oriFile);
			FileOutputStream fos = new FileOutputStream(copyFile);
			
			int fileByte = 0;
			
			while( (fileByte = fis.read()) != -1 ) {
				fos.write(fileByte);
			}
			
			fis.close();
			fos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 로그인 정보로 회원번호 가져오기
	public static String getMberNo(String mberId) {
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
	
	// 티켓 가격 가져오기
	public static ArrayList<CmmnCodeDTO> getTicketPc(){
		ArrayList<CmmnCodeDTO> cmmnList = new ArrayList<CmmnCodeDTO>();
		
		try {
			
			DBController.connect();
			
			String sql
			= "SELECT 	* 					"
			+ "FROM 	CMMN_CODE			"
			+ "WHERE 	PC IS NOT NULL 		"
			+ "ORDER BY COMMON_CODE 		";
			
			DBController.setPstmt(sql);
			
			ResultSet rs = DBController.select();
			while(rs.next()) {
				CmmnCodeDTO cmmnCodeDTO = new CmmnCodeDTO();
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
}
