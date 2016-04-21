package com.mcjs.entity;

@SuppressWarnings("serial")
public class LoanLeader implements java.io.Serializable {
	private int loan_id;
	private String loan_name;
	private String loan_passWord;
	private String loan_phoneNumber;
	private int loan_status;
	private String loan_addTime;
	private int loan_orgId;
	private String loan_tradAccount;
	private int loan_roleId;
	private String loan_orgname;
	
	public int getLoan_id() {
		return loan_id;
	}
	public void setLoan_id(int loan_id) {
		this.loan_id = loan_id;
	}
	public String getLoan_name() {
		return loan_name;
	}
	public void setLoan_name(String loan_name) {
		this.loan_name = loan_name;
	}
	public String getLoan_passWord() {
		return loan_passWord;
	}
	public void setLoan_passWord(String loan_passWord) {
		this.loan_passWord = loan_passWord;
	}
	public String getLoan_phoneNumber() {
		return loan_phoneNumber;
	}
	public void setLoan_phoneNumber(String loan_phoneNumber) {
		this.loan_phoneNumber = loan_phoneNumber;
	}
	public int getLoan_status() {
		return loan_status;
	}
	public void setLoan_status(int loan_status) {
		this.loan_status = loan_status;
	}
	public String getLoan_addTime() {
		return loan_addTime;
	}
	public void setLoan_addTime(String loan_addTime) {
		this.loan_addTime = loan_addTime;
	}
	public int getLoan_orgId() {
		return loan_orgId;
	}
	public void setLoan_orgId(int loan_orgId) {
		this.loan_orgId = loan_orgId;
	}
	public String getLoan_tradAccount() {
		return loan_tradAccount;
	}
	public void setLoan_tradAccount(String loan_tradAccount) {
		this.loan_tradAccount = loan_tradAccount;
	}
	public int getLoan_roleId() {
		return loan_roleId;
	}
	public void setLoan_roleId(int loan_roleId) {
		this.loan_roleId = loan_roleId;
	}
	public String getLoan_orgname() {
		return loan_orgname;
	}
	public void setLoan_orgname(String loan_orgname) {
		this.loan_orgname = loan_orgname;
	}
	
}
