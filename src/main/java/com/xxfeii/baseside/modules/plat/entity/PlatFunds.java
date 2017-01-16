package com.xxfeii.baseside.modules.plat.entity;

import java.util.Date;

import com.xxfeii.baseside.common.entity.BaseEntity;

/**
 * 账号资金
 * 
 * @author Administrator
 *
 */
public class PlatFunds extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//
	private Integer sid;
	private String platAccount;
	// 创建时间
	private Date creatTime;
	// 提现时间
	private Date withdrawTime;
	// 提现金额
	private Double withdrawAmount;
	// 提现币种
	private String withdrawCurrency;
	//到账时间
	private Date daozhangTime;
	//到账币种
	private String daozhangCurrency;
	//到账金额
	private Double daozhangAmount;
	//手续费
	private Double shouxufei;
	// 预计金额
	private Double estimateAmount;
	//提现币种符号
	private String withdrawSign;
	//提现币种符号
	private String daozhangSign;
	private String platNo;
	
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getPlatAccount() {
		return platAccount;
	}
	public void setPlatAccount(String platAccount) {
		this.platAccount = platAccount;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public Date getWithdrawTime() {
		return withdrawTime;
	}
	public void setWithdrawTime(Date withdrawTime) {
		this.withdrawTime = withdrawTime;
	}
	public Double getWithdrawAmount() {
		return withdrawAmount;
	}
	public void setWithdrawAmount(Double withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}
	public String getWithdrawCurrency() {
		return withdrawCurrency;
	}
	public void setWithdrawCurrency(String withdrawCurrency) {
		this.withdrawCurrency = withdrawCurrency;
	}
	public Double getShouxufei() {
		return shouxufei;
	}
	public void setShouxufei(Double shouxufei) {
		this.shouxufei = shouxufei;
	}
	
	public Double getEstimateAmount() {
		return estimateAmount;
	}
	public void setEstimateAmount(Double estimateAmount) {
		this.estimateAmount = estimateAmount;
	}
	public Date getDaozhangTime() {
		return daozhangTime;
	}
	public void setDaozhangTime(Date daozhangTime) {
		this.daozhangTime = daozhangTime;
	}
	public String getDaozhangCurrency() {
		return daozhangCurrency;
	}
	public void setDaozhangCurrency(String daozhangCurrency) {
		this.daozhangCurrency = daozhangCurrency;
	}
	public Double getDaozhangAmount() {
		return daozhangAmount;
	}
	public void setDaozhangAmount(Double daozhangAmount) {
		this.daozhangAmount = daozhangAmount;
	}
	public String getWithdrawSign() {
		return withdrawSign;
	}
	public void setWithdrawSign(String withdrawSign) {
		this.withdrawSign = withdrawSign;
	}
	public String getDaozhangSign() {
		return daozhangSign;
	}
	public void setDaozhangSign(String daozhangSign) {
		this.daozhangSign = daozhangSign;
	}
	public String getPlatNo() {
		return platNo;
	}
	public void setPlatNo(String platNo) {
		this.platNo = platNo;
	}
	


}
