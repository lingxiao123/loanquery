package com.mcjs.entity;

@SuppressWarnings("serial")
public class SearchEntrustSele implements java.io.Serializable {
	private String tradAccount;
	private String clientName;
	private String entrustNumber;
	private String stateTime;
	private String endTime;
	public String getTradAccount() {
		return tradAccount;
	}
	public void setTradAccount(String tradAccount) {
		this.tradAccount = tradAccount;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getEntrustNumber() {
		return entrustNumber;
	}
	public void setEntrustNumber(String entrustNumber) {
		this.entrustNumber = entrustNumber;
	}
	public String getStateTime() {
		return stateTime;
	}
	public void setStateTime(String stateTime) {
		this.stateTime = stateTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
