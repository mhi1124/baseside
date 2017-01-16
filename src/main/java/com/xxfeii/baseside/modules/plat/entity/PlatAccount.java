package com.xxfeii.baseside.modules.plat.entity;

import com.xxfeii.baseside.common.entity.BaseEntity;

/**
 * 账号对卡号
 * @author Administrator
 *
 */
public class PlatAccount extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer sid;
	//平台
	private String platNo;
	//平台账号
	private String platAccount;
	//PAYPAL币别
	private String paypalCurrency;
	//银行卡
	private String bankCard;
	//p卡或者wordfirst 提现类型
	private String type;
	
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getPlatNo() {
		return platNo;
	}
	public void setPlatNo(String platNo) {
		this.platNo = platNo;
	}
	public String getPlatAccount() {
		return platAccount;
	}
	public void setPlatAccount(String platAccount) {
		this.platAccount = platAccount;
	}
	public String getPaypalCurrency() {
		return paypalCurrency;
	}
	public void setPaypalCurrency(String paypalCurrency) {
		this.paypalCurrency = paypalCurrency;
	}
	public String getBankCard() {
		return bankCard;
	}
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
