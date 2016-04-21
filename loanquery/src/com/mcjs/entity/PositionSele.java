package com.mcjs.entity;

import javax.persistence.Table;

@SuppressWarnings("serial")
@Table(name="loan_positionsele"
,catalog="loanquery"
)
public class PositionSele implements java.io.Serializable {
	private int id;
	private String tradeAccount;
	private String customerName;
	private String positionNumbers;
	private String buildTime;
	private String commodity;
	private String shopSign;
	private String positionAmount;
	private String runningPrice;
	private String positionPrice;
	private String flatPrice;
	private String floatProfit;
	private String occupyBail;
	private String poundage;
	private String demurrage;
	private String operator;
	private String batNumber;
	private String balanceDate;
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
	public String getPositionNumbers() {
		return positionNumbers;
	}
	public void setPositionNumbers(String positionNumbers) {
		this.positionNumbers = positionNumbers;
	}
	public String getBuildTime() {
		return buildTime;
	}
	public void setBuildTime(String buildTime) {
		this.buildTime = buildTime;
	}
	public String getCommodity() {
		return commodity;
	}
	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}
	public String getShopSign() {
		return shopSign;
	}
	public void setShopSign(String shopSign) {
		this.shopSign = shopSign;
	}
	public String getPositionAmount() {
		return positionAmount;
	}
	public void setPositionAmount(String positionAmount) {
		this.positionAmount = positionAmount;
	}
	public String getRunningPrice() {
		return runningPrice;
	}
	public void setRunningPrice(String runningPrice) {
		this.runningPrice = runningPrice;
	}
	public String getPositionPrice() {
		return positionPrice;
	}
	public void setPositionPrice(String positionPrice) {
		this.positionPrice = positionPrice;
	}
	public String getFlatPrice() {
		return flatPrice;
	}
	public void setFlatPrice(String flatPrice) {
		this.flatPrice = flatPrice;
	}
	public String getFloatProfit() {
		return floatProfit;
	}
	public void setFloatProfit(String floatProfit) {
		this.floatProfit = floatProfit;
	}
	public String getOccupyBail() {
		return occupyBail;
	}
	public void setOccupyBail(String occupyBail) {
		this.occupyBail = occupyBail;
	}
	public String getPoundage() {
		return poundage;
	}
	public void setPoundage(String poundage) {
		this.poundage = poundage;
	}
	public String getDemurrage() {
		return demurrage;
	}
	public void setDemurrage(String demurrage) {
		this.demurrage = demurrage;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getBatNumber() {
		return batNumber;
	}
	public void setBatNumber(String batNumber) {
		this.batNumber = batNumber;
	}
	public String getBalanceDate() {
		return balanceDate;
	}
	public void setBalanceDate(String balanceDate) {
		this.balanceDate = balanceDate;
	}
}
