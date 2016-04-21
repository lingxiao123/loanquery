package com.mcjs.entity;

import javax.persistence.Table;

@SuppressWarnings("serial")
@Table(name="loan_entrustsele"
,catalog="loanquery"
)
public class EntrustSele implements java.io.Serializable {
	private int id;
	private String balanceDate;
	private String entrustTime;
	private String tradeAccount;
	private String customerName;
	private String entrustNumber;
	private String positionNumber;
	private String entrustType;
	private String commodityName;
	private String shopDirection;
	private String buildFlatPrice;
	private String stopLoss;
	private String stopProfit;
	private String number;
	private String entrustPrice;
	private String transactionPrice;
	private String entrustStatus;
	private String cancelTime;
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
	public String getEntrustTime() {
		return entrustTime;
	}
	public void setEntrustTime(String entrustTime) {
		this.entrustTime = entrustTime;
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
	public String getEntrustNumber() {
		return entrustNumber;
	}
	public void setEntrustNumber(String entrustNumber) {
		this.entrustNumber = entrustNumber;
	}
	public String getPositionNumber() {
		return positionNumber;
	}
	public void setPositionNumber(String positionNumber) {
		this.positionNumber = positionNumber;
	}
	public String getEntrustType() {
		return entrustType;
	}
	public void setEntrustType(String entrustType) {
		this.entrustType = entrustType;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getShopDirection() {
		return shopDirection;
	}
	public void setShopDirection(String shopDirection) {
		this.shopDirection = shopDirection;
	}
	public String getBuildFlatPrice() {
		return buildFlatPrice;
	}
	public void setBuildFlatPrice(String buildFlatPrice) {
		this.buildFlatPrice = buildFlatPrice;
	}
	public String getStopLoss() {
		return stopLoss;
	}
	public void setStopLoss(String stopLoss) {
		this.stopLoss = stopLoss;
	}
	public String getStopProfit() {
		return stopProfit;
	}
	public void setStopProfit(String stopProfit) {
		this.stopProfit = stopProfit;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getEntrustPrice() {
		return entrustPrice;
	}
	public void setEntrustPrice(String entrustPrice) {
		this.entrustPrice = entrustPrice;
	}
	public String getTransactionPrice() {
		return transactionPrice;
	}
	public void setTransactionPrice(String transactionPrice) {
		this.transactionPrice = transactionPrice;
	}
	public String getEntrustStatus() {
		return entrustStatus;
	}
	public void setEntrustStatus(String entrustStatus) {
		this.entrustStatus = entrustStatus;
	}
	public String getCancelTime() {
		return cancelTime;
	}
	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
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
