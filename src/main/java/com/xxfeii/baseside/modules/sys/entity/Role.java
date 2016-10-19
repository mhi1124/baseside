package com.xxfeii.baseside.modules.sys.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xxfeii.baseside.common.entity.BaseEntity;


/**
 * 
 * @ClassName Role
 * @Description 角色实体类
 * @author davi
 * @date 2016年9月5日
 *
 */
public class Role extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private String sid;
	/**
	 * 角色名
	 */
	private String roleName;
	/**
	 * 角色key
	 */
	private String roleKey;
	/**
	 * 角色状态 1：正常；2：锁定；3：删除
	 */
	private Integer status;
	/**
	 * 角色描述信息
	 */
	private String description;
	/**
	 * 角色创建时间
	 */
	private Date createTime;
	/**
	 * 角色更新时间
	 */
	private Date updateTime;
	
	/**
	 * 角色下所有用户列表结合
	 */
	private List<User> userList = new ArrayList<User>();

	/**
	 * 该角色下的菜单
	 */
	private List<Menu> menuList = new ArrayList<Menu>();
	
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	@Override
	public String toString() {
		return "Role [sid=" + sid + ", roleName=" + roleName + ", roleKey="
				+ roleKey + ", status=" + status + ", description="
				+ description + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", userList=" + userList + "]";
	}

	
	
}