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
	private Integer sid;
	
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 菜单的链接
	 */
	private String menuUrl;
	/**
	 * 状态：1，正常；2，暂停使用；3，删除
	 */
	private Integer menuStatus;
	/**
	 * 父id
	 */
	private Integer pid;
	/**
	 * 说明
	 */
	private String menuRemark;
	/**
	 *  类型 1:目录;2:菜单;3:按钮
	 */
	private Integer menuType;
	/**
	 * 菜单的图标
	 */
	private String icon;
	/**
	 * 父菜单的路径
	 */
	private String parentPath;
	/**
	 * 子菜单
	 */
	private List<Menu> childMenus;
	/**
	 * 选中状态
	 */
	private boolean isChoose;
	
	public String getParentPath() {
		return parentPath;
	}
	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
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
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
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
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	@Override
	public String toString() {
		return "Menu [sid=" + sid + ", menuName=" + menuName + ", menuUrl="
				+ menuUrl + ", menuStatus=" + menuStatus + ", pid=" + pid
				+ ", menuRemark=" + menuRemark + ", menuType=" + menuType
				+ ", icon=" + icon + ", parentPath=" + parentPath
				+ ", childMenus=" + childMenus + ", isChoose=" + isChoose + "]";
	}
	
	
}
