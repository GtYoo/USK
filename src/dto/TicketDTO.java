package dto;

public class TicketDTO {
	private String ticketNo;	// 티켓번호
	private int validPd;		// 유효기간
	private String ticketNm;	// 티켓명
	private String ticketDc;	// 티켓설명
	private String photoCours;	// 사진경로
	private String cmmnCode;	// 공통코드
	
	
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	public int getValidPd() {
		return validPd;
	}
	public void setValidPd(int validPd) {
		this.validPd = validPd;
	}
	
	public String getTicketNm() {
		return ticketNm;
	}
	public void setTicketNm(String ticketNm) {
		this.ticketNm = ticketNm;
	}
	
	public String getTicketDc() {
		return ticketDc;
	}
	public void setTicketDc(String ticketDc) {
		this.ticketDc = ticketDc;
	}
	public String getPhotoCours() {
		return photoCours;
	}
	public void setPhotoCours(String photoCours) {
		this.photoCours = photoCours;
	}
	public String getCmmnCode() {
		return cmmnCode;
	}
	public void setCmmnCode(String cmmnCode) {
		this.cmmnCode = cmmnCode;
	}
	
	
}
