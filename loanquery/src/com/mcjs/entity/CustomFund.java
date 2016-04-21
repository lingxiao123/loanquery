package com.mcjs.entity;

import javax.persistence.Table;

@SuppressWarnings("serial")
@Table(name="loan_customfund"
,catalog="loanquery"
)
public class CustomFund implements java.io.Serializable {
	private int id;
	private String tradeAccount;
	private String customerName;
	private String primeBenefit;
	private String inandoutMoney;
	private String flatProfit;
	private String positionProfit;
	private String poundage;
	private String occupyBail;
	private String usableBail;
	private String freezeBail;
	private String freezePoundage;
	private String presentIncome;
	private String dealBail;
	private String dealPoundage;
	private String dealLoan;
	private String dealPenalty;
	private String penaltyMoney;
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
	public String getPoundage() {
		return poundage;
	}
	public void setPoundage(String poundage) {
		this.poundage = poundage;
	}
	public String getOccupyBail() {
		return occupyBail;
	}
	public void setOccupyBail(String occupyBail) {
		this.occupyBail = occupyBail;
	}
	public String getUsableBail() {
		return usableBail;
	}
	public void setUsableBail(String usableBail) {
		this.usableBail = usableBail;
	}
	public String getFreezeBail() {
		return freezeBail;
	}
	public void setFreezeBail(String freezeBail) {
		this.freezeBail = freezeBail;
	}
	public String getFreezePoundage() {
		return freezePoundage;
	}
	public void setFreezePoundage(String freezePoundage) {
		this.freezePoundage = freezePoundage;
	}
	public String getPresentIncome() {
		return presentIncome;
	}
	public void setPresentIncome(String presentIncome) {
		this.presentIncome = presentIncome;
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
	public String getPenaltyMoney() {
		return penaltyMoney;
	}
	public void setPenaltyMoney(String penaltyMoney) {
		this.penaltyMoney = penaltyMoney;
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
