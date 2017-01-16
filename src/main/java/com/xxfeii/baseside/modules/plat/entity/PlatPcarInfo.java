package com.xxfeii.baseside.modules.plat.entity;

import java.util.Date;

import com.xxfeii.baseside.common.entity.BaseEntity;

/**
 * P卡记录
 * @author Administrator
 *
 */
public class PlatPcarInfo extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer sid;
	//提现记录id
	private Integer pfid;
	//P卡到的币种
	private String pCurrency;
	//发生时间
	private Date creatTime;
	//更新时间
	private Date updateTime;
	//发生金额
	private Double amount;
	
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getPfid() {
		return pfid;
	}
	public void setPfid(Integer pfid) {
		this.pfid = pfid;
	}
	public String getpCurrency() {
		return pCurrency;
	}
	public void setpCurrency(String pCurrency) {
		this.pCurrency = pCurrency;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
