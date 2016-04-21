package com.mcjs.entity;

@SuppressWarnings("serial")
public class SearchLoan implements java.io.Serializable {
	private String loan_name;
	private String loan_orgname;
	public String getLoan_name() {
		return loan_name;
	}
	public void setLoan_name(String loan_name) {
		this.loan_name = loan_name;
	}
	public String getLoan_orgname() {
		return loan_orgname;
	}
	public void setLoan_orgname(String loan_orgname) {
		this.loan_orgname = loan_orgname;
	}
}
