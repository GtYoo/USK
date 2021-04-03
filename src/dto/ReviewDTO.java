package dto;

public class ReviewDTO {
	private String attractionNo;	// 어트랙션번호
	private String resveNo;			// 예약번호
	private String issuNo;			// 발급번호
	private String cn;				// 내용
	private int rating;				// 별점
	private String photoCours;		// 사진경로
	private String registDe;		// 등록일
	
	
	public String getAttractionNo() {
		return attractionNo;
	}
	public void setAttractionNo(String attractionNo) {
		this.attractionNo = attractionNo;
	}
	
	public String getResveNo() {
		return resveNo;
	}
	public void setResveNo(String resveNo) {
		this.resveNo = resveNo;
	}
	public String getIssuNo() {
		return issuNo;
	}
	public void setIssuNo(String issuNo) {
		this.issuNo = issuNo;
	}
	public String getCn() {
		return cn;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getPhotoCours() {
		return photoCours;
	}
	public void setPhotoCours(String photoCours) {
		this.photoCours = photoCours;
	}
	public String getRegistDe() {
		return registDe;
	}
	public void setRegistDe(String registDe) {
		this.registDe = registDe;
	}
	
	
}
