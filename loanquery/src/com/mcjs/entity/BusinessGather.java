package com.mcjs.entity;

import javax.persistence.Table;

@SuppressWarnings("serial")
@Table(name="loan_businessgather"
,catalog="loanquery"
)
public class BusinessGather implements java.io.Serializable {
	private int id;
	private String accountsTime;
	private String tradeAccount;
	private String commodity;
	private String customerName;
	private String turnoverNum;
	private String turnoverMoney;
	private String flatProfit;
	private String positionProfit;
	private String profitTotal;
	private String exchangePoundage;
	private String synthesizePoundage;
	private String takeinPoundage;
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
	public String getCommodity() {
		return commodity;
	}
	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getTurnoverNum() {
		return turnoverNum;
	}
	public void setTurnoverNum(String turnoverNum) {
		this.turnoverNum = turnoverNum;
	}
	public String getTurnoverMoney() {
		return turnoverMoney;
	}
	public void setTurnoverMoney(String turnoverMoney) {
		this.turnoverMoney = turnoverMoney;
	}
	public String getFlatProfit() {
		return flatProfit;
	}
	public void setFlatProfit(String flatProfit) {
		this.flatProfit = flatProfit;
	}
	public String getPositionProfit() {
		return positionProfit;
	}
	public void setPositionProfit(String positionProfit) {
		this.positionProfit = positionProfit;
	}
	public String getProfitTotal() {
		return profitTotal;
	}
	public void setProfitTotal(String profitTotal) {
		this.profitTotal = profitTotal;
	}
	public String getExchangePoundage() {
		return exchangePoundage;
	}
	public void setExchangePoundage(String exchangePoundage) {
		this.exchangePoundage = exchangePoundage;
	}
	public String getSynthesizePoundage() {
		return synthesizePoundage;
	}
	public void setSynthesizePoundage(String synthesizePoundage) {
		this.synthesizePoundage = synthesizePoundage;
	}
	public String getTakeinPoundage() {
		return takeinPoundage;
	}
	public void setTakeinPoundage(String takeinPoundage) {
		this.takeinPoundage = takeinPoundage;
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
