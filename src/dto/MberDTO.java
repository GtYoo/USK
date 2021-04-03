package dto;

public class MberDTO {
	private String mberNo;		// 회원번호
	private String mberID;		// 회원아이디
	private String password;	// 비밀번호
	private String mberNm;		// 회원명
	private String email;		// 이메일
	private String moblphon;	// 휴대전화번호
	private String mberSecsnAt; // 회원탈퇴여부
	
	
	public String getMberNo() {
		return mberNo;
	}
	public void setMberNo(String mberNo) {
		this.mberNo = mberNo;
	}
	public String getMberID() {
		return mberID;
	}
	public void setMberID(String mberID) {
		this.mberID = mberID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMberNm() {
		return mberNm;
	}
	public void setMberNm(String mberNm) {
		this.mberNm = mberNm;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMoblphon() {
		return moblphon;
	}
	public void setMoblphon(String moblphon) {
		this.moblphon = moblphon;
	}
	public String getMberSecsnAt() {
		return mberSecsnAt;
	}
	public void setMberSecsnAt(String mberSecsnAt) {
		this.mberSecsnAt = mberSecsnAt;
	}
	
	
}
