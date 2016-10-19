package com.xxfeii.baseside.modules.sys.model;

/**
 * ztree 节点
 * @author Administrator
 *
 */
public class TreeNode {

	private String id;
	private String pId;
	private String name;
	/**
	 * 是否展开
	 */
	private boolean open;
	/**
	 * 是否选中
	 */
	private boolean checked;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}
