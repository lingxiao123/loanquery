package com.mcjs.entity;

@SuppressWarnings("serial")
public class LoanMember implements java.io.Serializable {
	private int lm_id;
	private String lm_name;
	private String lm_tradAccount;
	private String lm_phoneNumber;
	private int lm_status;
	private String lm_addTime;
	private int lm_loanid;
	private String lm_loanname;
	
	public int getLm_id() {
		return lm_id;
	}
	public void setLm_id(int lm_id) {
		this.lm_id = lm_id;
	}
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
	public String getLm_phoneNumber() {
		return lm_phoneNumber;
	}
	public void setLm_phoneNumber(String lm_phoneNumber) {
		this.lm_phoneNumber = lm_phoneNumber;
	}
	public int getLm_status() {
		return lm_status;
	}
	public void setLm_status(int lm_status) {
		this.lm_status = lm_status;
	}
	public String getLm_addTime() {
		return lm_addTime;
	}
	public void setLm_addTime(String lm_addTime) {
		this.lm_addTime = lm_addTime;
	}
	public int getLm_loanid() {
		return lm_loanid;
	}
	public void setLm_loanid(int lm_loanid) {
		this.lm_loanid = lm_loanid;
	}
	public String getLm_loanname() {
		return lm_loanname;
	}
	public void setLm_loanname(String lm_loanname) {
		this.lm_loanname = lm_loanname;
	}
	
	
}
