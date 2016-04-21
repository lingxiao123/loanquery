package com.mcjs.entity;

import javax.persistence.Table;

@SuppressWarnings("serial")
@Table(name="loan_positiongather"
,catalog="loanquery"
)
public class PositionGather implements java.io.Serializable {
	private int id;
	private String accountsTime;
	private String tradeAccount;
	private String customerName;
	private String commodity;
	private String buyPositionNum;
	private String sellPositionNum;
	private String positionTotal;
	private String demurrage;
	private String marketPoundage;
	private String occupyBail;
	private String batNumber;
	private String agencyName;
	private String orgName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountsTime() {
		return accountsTime;
	}
	public void setAccountsTime(String accountsTime) {
		this.accountsTime = accountsTime;
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
	public String getCommodity() {
		return commodity;
	}
	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}
	public String getBuyPositionNum() {
		return buyPositionNum;
	}
	public void setBuyPositionNum(String buyPositionNum) {
		this.buyPositionNum = buyPositionNum;
	}
	public String getSellPositionNum() {
		return sellPositionNum;
	}
	public void setSellPositionNum(String sellPositionNum) {
		this.sellPositionNum = sellPositionNum;
	}
	public String getPositionTotal() {
		return positionTotal;
	}
	public void setPositionTotal(String positionTotal) {
		this.positionTotal = positionTotal;
	}
	public String getDemurrage() {
		return demurrage;
	}
	public void setDemurrage(String demurrage) {
		this.demurrage = demurrage;
	}
	public String getMarketPoundage() {
		return marketPoundage;
	}
	public void setMarketPoundage(String marketPoundage) {
		this.marketPoundage = marketPoundage;
	}
	public String getOccupyBail() {
		return occupyBail;
	}
	public void setOccupyBail(String occupyBail) {
		this.occupyBail = occupyBail;
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
