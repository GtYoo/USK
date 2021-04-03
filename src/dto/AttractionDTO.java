package dto;

public class AttractionDTO {
	private String attractionNo;	// 어트랙션 번호
	private String areaNo;			// 구역번호
	private String attractionNm;	// 어트랙션명
	private String attractionTy;	// 어트랙션유형
	private String attractionDc;	// 어트랙션설명
	private String photoCours;		// 사진경로
	private char opratAt;			// 운행여부
	private String operBeginTime;	// 운영시작시간
	private String operEndTime;		// 운영종료시간
	
	
	
	public String getAttractionNo() {
		return attractionNo;
	}
	public void setAttractionNo(String attractionNo) {
		this.attractionNo = attractionNo;
	}
	public String getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	public String getAttractionNm() {
		return attractionNm;
	}
	public void setAttractionNm(String attractionNm) {
		this.attractionNm = attractionNm;
	}
	public String getAttractionTy() {
		return attractionTy;
	}
	public void setAttractionTy(String attractionTy) {
		this.attractionTy = attractionTy;
	}
	public String getAttractionDc() {
		return attractionDc;
	}
	public void setAttractionDc(String attractionDc) {
		this.attractionDc = attractionDc;
	}
	
	public String getPhotoCours() {
		return photoCours;
	}
	public void setPhotoCours(String photoCours) {
		this.photoCours = photoCours;
	}
	public char getOpratAt() {
		return opratAt;
	}
	public void setOpratAt(char opratAt) {
		this.opratAt = opratAt;
	}
	public String getOperBeginTime() {
		return operBeginTime;
	}
	public void setOperBeginTime(String operBeginTime) {
		this.operBeginTime = operBeginTime;
	}
	public String getOperEndTime() {
		return operEndTime;
	}
	public void setOperEndTime(String operEndTime) {
		this.operEndTime = operEndTime;
	}
	
	
}
