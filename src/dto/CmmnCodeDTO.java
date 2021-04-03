package dto;

public class CmmnCodeDTO {
	private String 	cmmnCode;		// 공통코드
	private String 	cmmnCodeNm; 	// 코드명
	private int 	number; 		// 가격

	public String getCmmnCode() {
		return cmmnCode;
	}

	public void setCmmnCode(String cmmnCode) {
		this.cmmnCode = cmmnCode;
	}

	public String getCmmnCodeNm() {
		return cmmnCodeNm;
	}

	public void setCmmnCodeNm(String cmmnCodeNm) {
		this.cmmnCodeNm = cmmnCodeNm;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	
}
