package com.npu.domain;

public class CardInfo {
	int cardInfoId;
	String cardNo;
	String cardType;
	String expiry;
	String userId;
	int securityCode;

	public CardInfo(){
		
	}
	
	public CardInfo(String cardNo, String cardType, String expiry,
			String userId, int securityCode) {
		super();
		this.cardNo = cardNo;
		this.cardType = cardType;
		this.expiry = expiry;
		this.userId = userId;
		this.securityCode = securityCode;
	}
	
	public int getCardInfoId() {
		return cardInfoId;
	}
	public void setCardInfoId(int cardInfoId) {
		this.cardInfoId = cardInfoId;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	
	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}
	
}
