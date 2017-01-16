package com.xxfeii.baseside.modules.plat.entity;

import com.xxfeii.baseside.common.entity.BaseEntity;

/**
 * 汇率
 * 
 * @author Administrator
 *
 */
public class PlatFXRate extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer sid;
	// 币种
	private String currency;
	// 币种符合
	private String sign;
	// 对人民币汇率
	private Double rmb;

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getRmb() {
		return rmb;
	}

	public void setRmb(Double rmb) {
		this.rmb = rmb;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Override
	public String toString() {
		return "ExchangeRate [sid=" + sid + ", currency=" + currency + ", sign=" + sign + ", rmb=" + rmb + "]";
	}

}
