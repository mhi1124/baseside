package com.xxfeii.baseside.modules.plat.model;

/**
 * 提现统计显示
 * @author Administrator
 *
 */
public class FundsStatistical {

	//提现总额
	private double withdrawCount;
	// 提现币种
	private String withdrawCurrency;
	// 提现币种符合
	private String withdrawSign;
	//手续费
	private double shouxuCount;
	//实际到账总额
	private double realAmountCount;
	// 实际到达币种
	private String realCurrency;
	// 实际到达币种
	private String realSign;
	//银行到账金额
	private double bankAmountCount;
	//未结汇总金额
	private double notjhwbCount;
	//已结汇金额
	private double jhwbCount;
	// 结汇金额（RMB）
	private double jiehuiAmountCount;
	// 预计金额
	private double estimateAmountCount;
	public double getWithdrawCount() {
		return withdrawCount;
	}
	public void setWithdrawCount(double withdrawCount) {
		this.withdrawCount = withdrawCount;
	}
	public String getWithdrawCurrency() {
		return withdrawCurrency;
	}
	public void setWithdrawCurrency(String withdrawCurrency) {
		this.withdrawCurrency = withdrawCurrency;
	}
	public double getShouxuCount() {
		return shouxuCount;
	}
	public void setShouxuCount(double shouxuCount) {
		this.shouxuCount = shouxuCount;
	}
	public double getRealAmountCount() {
		return realAmountCount;
	}
	public void setRealAmountCount(double realAmountCount) {
		this.realAmountCount = realAmountCount;
	}
	public String getRealCurrency() {
		return realCurrency;
	}
	public void setRealCurrency(String realCurrency) {
		this.realCurrency = realCurrency;
	}
	public double getBankAmountCount() {
		return bankAmountCount;
	}
	public void setBankAmountCount(double bankAmountCount) {
		this.bankAmountCount = bankAmountCount;
	}
	public double getNotjhwbCount() {
		return notjhwbCount;
	}
	public void setNotjhwbCount(double notjhwbCount) {
		this.notjhwbCount = notjhwbCount;
	}
	public double getJhwbCount() {
		return jhwbCount;
	}
	public void setJhwbCount(double jhwbCount) {
		this.jhwbCount = jhwbCount;
	}
	public double getJiehuiAmountCount() {
		return jiehuiAmountCount;
	}
	public void setJiehuiAmountCount(double jiehuiAmountCount) {
		this.jiehuiAmountCount = jiehuiAmountCount;
	}
	public double getEstimateAmountCount() {
		return estimateAmountCount;
	}
	public void setEstimateAmountCount(double estimateAmountCount) {
		this.estimateAmountCount = estimateAmountCount;
	}
	public String getWithdrawSign() {
		return withdrawSign;
	}
	public void setWithdrawSign(String withdrawSign) {
		this.withdrawSign = withdrawSign;
	}
	public String getRealSign() {
		return realSign;
	}
	public void setRealSign(String realSign) {
		this.realSign = realSign;
	}
	
}
