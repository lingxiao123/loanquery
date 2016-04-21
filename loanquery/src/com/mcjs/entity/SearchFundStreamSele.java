package com.mcjs.entity;

@SuppressWarnings("serial")
public class SearchFundStreamSele implements java.io.Serializable {
	private String tradAccount;
	private String clientName;
	private String funderStreamNumber;
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
	public String getFunderStreamNumber() {
		return funderStreamNumber;
	}
	public void setFunderStreamNumber(String funderStreamNumber) {
		this.funderStreamNumber = funderStreamNumber;
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
