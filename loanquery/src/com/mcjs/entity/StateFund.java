package com.mcjs.entity;

import javax.persistence.Table;

@SuppressWarnings("serial")
@Table(name="loan_statefund"
,catalog="loanquery"
)
public class StateFund implements java.io.Serializable {
	private int id;
	private String accountsTime;
	private String tradeAccount;
	private String customerName;
	private String primeBenefit;
	private String inandoutMoney;
	private String flatProfit;
	private String positionProfit;
	private String profitTotal;
	private String exchangeFee;
	private String memberFee;
	private String susPoundage;
	private String demurrage;
	private String occupyBail;
	private String dealBail;
	private String dealPoundage;
	private String dealLoan;
	private String dealPenalty;
	private String lastBenefit;
	private String taxSettlement;
	private String riskRate;
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
	public String getPrimeBenefit() {
		return primeBenefit;
	}
	public void setPrimeBenefit(String primeBenefit) {
		this.primeBenefit = primeBenefit;
	}
	public String getInandoutMoney() {
		return inandoutMoney;
	}
	public void setInandoutMoney(String inandoutMoney) {
		this.inandoutMoney = inandoutMoney;
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
	
	public String getExchangeFee() {
		return exchangeFee;
	}
	public void setExchangeFee(String exchangeFee) {
		this.exchangeFee = exchangeFee;
	}
	public String getMemberFee() {
		return memberFee;
	}
	public void setMemberFee(String memberFee) {
		this.memberFee = memberFee;
	}
	public String getSusPoundage() {
		return susPoundage;
	}
	public void setSusPoundage(String susPoundage) {
		this.susPoundage = susPoundage;
	}
	public String getDemurrage() {
		return demurrage;
	}
	public void setDemurrage(String demurrage) {
		this.demurrage = demurrage;
	}
	public String getOccupyBail() {
		return occupyBail;
	}
	public void setOccupyBail(String occupyBail) {
		this.occupyBail = occupyBail;
	}
	public String getDealBail() {
		return dealBail;
	}
	public void setDealBail(String dealBail) {
		this.dealBail = dealBail;
	}
	public String getDealPoundage() {
		return dealPoundage;
	}
	public void setDealPoundage(String dealPoundage) {
		this.dealPoundage = dealPoundage;
	}
	public String getDealLoan() {
		return dealLoan;
	}
	public void setDealLoan(String dealLoan) {
		this.dealLoan = dealLoan;
	}
	public String getDealPenalty() {
		return dealPenalty;
	}
	public void setDealPenalty(String dealPenalty) {
		this.dealPenalty = dealPenalty;
	}
	public String getLastBenefit() {
		return lastBenefit;
	}
	public void setLastBenefit(String lastBenefit) {
		this.lastBenefit = lastBenefit;
	}
	public String getTaxSettlement() {
		return taxSettlement;
	}
	public void setTaxSettlement(String taxSettlement) {
		this.taxSettlement = taxSettlement;
	}
	public String getRiskRate() {
		return riskRate;
	}
	public void setRiskRate(String riskRate) {
		this.riskRate = riskRate;
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
