package com.xxfeii.baseside.modules.plat.entity;

import java.util.Date;

import com.xxfeii.baseside.common.entity.BaseEntity;

/**
 * 结汇记录
 * @author Administrator
 *
 */
public class PlatRemittance extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer sid;
	//提现记录id/或P卡转出id
	private Integer pid;
	//发生时间
	private Date creatTime;
	//更新时间
	private Date updateTime;
	//结汇金额(外币)
	private Double jiehuiAmount;
	//结汇金额(RMB)
	private Double rmbAmount;
	
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
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
	public Double getJiehuiAmount() {
		return jiehuiAmount;
	}
	public void setJiehuiAmount(Double jiehuiAmount) {
		this.jiehuiAmount = jiehuiAmount;
	}
	public Double getRmbAmount() {
		return rmbAmount;
	}
	public void setRmbAmount(Double rmbAmount) {
		this.rmbAmount = rmbAmount;
	}
	
	
}
