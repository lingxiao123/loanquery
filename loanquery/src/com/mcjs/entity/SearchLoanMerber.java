package com.mcjs.entity;

@SuppressWarnings("serial")
public class SearchLoanMerber implements java.io.Serializable {
	private String lm_name;
	private String lm_tradAccount;
	private String lm_loanname;
	public String getLm_name() {
		return lm_name;
	}
	public void setLm_name(String lm_name) {
		this.lm_name = lm_name;
	}
	public String getLm_tradAccount() {
		return lm_tradAccount;
	}
	public void setLm_tradAccount(String lm_tradAccount) {
		this.lm_tradAccount = lm_tradAccount;
	}
	public String getLm_loanname() {
		return lm_loanname;
	}
	public void setLm_loanname(String lm_loanname) {
		this.lm_loanname = lm_loanname;
	}
}
