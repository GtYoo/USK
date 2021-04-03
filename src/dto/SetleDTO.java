package dto;

import java.util.Date;

public class SetleDTO {
	private String resveNo;		// 예약번호
	private String issuNo;		// 발급번호
	private int setleAmount;	// 결제금액
	private Date setleDt;		// 결제일시
	private char canclReqstAt;	// 취소신청여부
	private Date comptDt;		// 취소일시
	private char cancdeComptAt;	// 결제취소완료여부
	
	
	
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
	public int getSetleAmount() {
		return setleAmount;
	}
	public void setSetleAmount(int setleAmount) {
		this.setleAmount = setleAmount;
	}
	public Date getSetleDt() {
		return setleDt;
	}
	public void setSetleDt(Date setleDt) {
		this.setleDt = setleDt;
	}
	public char getCanclReqstAt() {
		return canclReqstAt;
	}
	public void setCanclReqstAt(char canclReqstAt) {
		this.canclReqstAt = canclReqstAt;
	}
	public Date getComptDt() {
		return comptDt;
	}
	public void setComptDt(Date comptDt) {
		this.comptDt = comptDt;
	}
	public char getCancdeComptAt() {
		return cancdeComptAt;
	}
	public void setCancdeComptAt(char cancdeComptAt) {
		this.cancdeComptAt = cancdeComptAt;
	}
	
	
	
}
