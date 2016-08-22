package com.xxfeii.baseside.modules.sys.entity;

import java.util.List;

import com.xxfeii.baseside.common.entity.BaseEntity;

/**
 * 
 * @ClassName MenuEntity
 * @Description 菜单实体类
 * @author davi
 * @date 2016年7月17日
 *
 */
public class Menu extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 菜单的链接
	 */
	private String menuUrl;
	/**
	 * 状态：0，正常；1，暂停使用；2，删除
	 */
	private Integer menuStatus;
	/**
	 * 父id
	 */
	private String pid;
	/**
	 * 说明
	 */
	private String menuRemark;
	/**
	 * 类型
	 */
	private Integer menuType;
	
	/**
	 * 子菜单
	 */
	private List<Menu> childMenus;
	/**
	 * 选中状态
	 */
	private boolean isChoose;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public Integer getMenuStatus() {
		return menuStatus;
	}
	public void setMenuStatus(Integer menuStatus) {
		this.menuStatus = menuStatus;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getMenuRemark() {
		return menuRemark;
	}
	public void setMenuRemark(String menuRemark) {
		this.menuRemark = menuRemark;
	}
	public Integer getMenuType() {
		return menuType;
	}
	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}
	public List<Menu> getChildMenus() {
		return childMenus;
	}
	public void setChildMenus(List<Menu> childMenus) {
		this.childMenus = childMenus;
	}
	public boolean isChoose() {
		return isChoose;
	}
	public void setChoose(boolean isChoose) {
		this.isChoose = isChoose;
	}
	
	
}
