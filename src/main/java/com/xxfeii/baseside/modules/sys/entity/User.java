package com.xxfeii.baseside.modules.sys.entity;

import java.util.Date;

import com.xxfeii.baseside.common.entity.BaseEntity;

/**
 * 
 * @ClassName User
 * @Description 用户实体类
 * @author davi
 * @date 2016年9月5日
 * 
 */
public class User extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户真实姓名
	 */
	private String userName;
	/**
	 * 这里账户名称统一使用邮箱
	 */
	private String accountName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 状态：0：正常；1：锁定；2：删除
	 */
	private Integer userStatus;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 创建者accountName
	 */
	private String creatorName;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 所属角色
	 */
	private Role role;
	/**
	 * 个人资料信息
	 */
	private UserInfo userInfo;

	/**
	 * 前端列表页使用
	 */
	private String roleName;

	public User() {

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
		// 设置角色名称,dtgrid使用
		this.roleName = role.getRoleName();
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getRoleName() {
		return roleName;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", accountName=" + accountName
				+ ", password=" + password + ", userStatus=" + userStatus
				+ ", description=" + description + ", creatorName="
				+ creatorName + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", role=" + role + ", userInfo=" + userInfo
				+ ", roleName=" + roleName + "]";
	}

}
