package dto;

public class ResveDTO {
	private String resveNo;		// 예약번호
	private String mberNo;		// 회원번호
	private String ticketNo;	// 티켓번호
	private String cmmnCode;	// 공통코드
	private String resveDe;		// 예약일
	private String entncDe;		// 입장일
	private char setleAt;		// 결제여부
	
	
	
	public String getResveNo() {
		return resveNo;
	}
	public void setResveNo(String resveNo) {
		this.resveNo = resveNo;
	}
	public String getMberNo() {
		return mberNo;
	}
	public void setMberNo(String mberNo) {
		this.mberNo = mberNo;
	}
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	
	public String getCmmnCode() {
		return cmmnCode;
	}
	public void setCmmnCode(String cmmnCode) {
		this.cmmnCode = cmmnCode;
	}
	public String getResveDe() {
		return resveDe;
	}
	public void setResveDe(String resveDe) {
		this.resveDe = resveDe;
	}
	public String getEntncDe() {
		return entncDe;
	}
	public void setEntncDe(String entncDe) {
		this.entncDe = entncDe;
	}
	public char getSetleAt() {
		return setleAt;
	}
	public void setSetleAt(char setleAt) {
		this.setleAt = setleAt;
	}
	
	
}
