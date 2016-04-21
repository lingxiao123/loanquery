package com.mcjs.entity;

import javax.persistence.Table;

@SuppressWarnings("serial")
@Table(name="loan_fundstreamsele"
,catalog="loanquery"
)
public class FundStreamSele implements java.io.Serializable {
	private int id;
	private String balanceDate;
	private String tradeAccount;
	private String customerName;
	private String serialNumber;
	private String serviceName;
	private String happenTime;
	private String changeMoney;
	private String modificaMoney;
	private String relevanceNumber;
	private String batNumber;
	private String agencyName;
	private String orgName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBalanceDate() {
		return balanceDate;
	}
	public void setBalanceDate(String balanceDate) {
		this.balanceDate = balanceDate;
	}
	public String getTradeAccount() {
		return tradeAccount;
	}
	public void setTradeAccount(String tradeAccount) {
		this.tradeAccount = tradeAccount;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getHappenTime() {
		return happenTime;
	}
	public void setHappenTime(String happenTime) {
		this.happenTime = happenTime;
	}
	public String getChangeMoney() {
		return changeMoney;
	}
	public void setChangeMoney(String changeMoney) {
		this.changeMoney = changeMoney;
	}
	public String getModificaMoney() {
		return modificaMoney;
	}
	public void setModificaMoney(String modificaMoney) {
		this.modificaMoney = modificaMoney;
	}
	public String getRelevanceNumber() {
		return relevanceNumber;
	}
	public void setRelevanceNumber(String relevanceNumber) {
		this.relevanceNumber = relevanceNumber;
	}
	public String getBatNumber() {
		return batNumber;
	}
	public void setBatNumber(String batNumber) {
		this.batNumber = batNumber;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}	
	
}
