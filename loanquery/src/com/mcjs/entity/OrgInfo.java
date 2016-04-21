package com.mcjs.entity;

@SuppressWarnings("serial")
public class OrgInfo implements java.io.Serializable {
	private int org_id;
	private String org_FullName;
	private String org_Abbreviation;
	private String org_PassWord;
	private String org_PhoneNumber;
	private int org_Status;
	private String org_AddTime;
	private int org_RoleId;
	
	public int getOrg_id() {
		return org_id;
	}
	public void setOrg_id(int org_id) {
		this.org_id = org_id;
	}
	public String getOrg_FullName() {
		return org_FullName;
	}
	public void setOrg_FullName(String org_FullName) {
		this.org_FullName = org_FullName;
	}
	public String getOrg_Abbreviation() {
		return org_Abbreviation;
	}
	public void setOrg_Abbreviation(String org_Abbreviation) {
		this.org_Abbreviation = org_Abbreviation;
	}
	public String getOrg_PassWord() {
		return org_PassWord;
	}
	public void setOrg_PassWord(String org_PassWord) {
		this.org_PassWord = org_PassWord;
	}
	public String getOrg_PhoneNumber() {
		return org_PhoneNumber;
	}
	public void setOrg_PhoneNumber(String org_PhoneNumber) {
		this.org_PhoneNumber = org_PhoneNumber;
	}
	public int getOrg_Status() {
		return org_Status;
	}
	public void setOrg_Status(int org_Status) {
		this.org_Status = org_Status;
	}
	public String getOrg_AddTime() {
		return org_AddTime;
	}
	public void setOrg_AddTime(String org_AddTime) {
		this.org_AddTime = org_AddTime;
	}
	public int getOrg_RoleId() {
		return org_RoleId;
	}
	public void setOrg_RoleId(int org_RoleId) {
		this.org_RoleId = org_RoleId;
	}
	
}
