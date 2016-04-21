package com.mcjs.entity;

@SuppressWarnings("serial")
public class SearchCustomFund implements java.io.Serializable {
	private String tradAccount;
	private String clientName;
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
}
