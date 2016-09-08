package com.xxfeii.baseside.common.entity;

import java.io.Serializable;

/**
 * 
 * @ClassName BaseEntity
 * @Description 实体的公共类
 * @author davi
 * @date 2016年7月17日
 *
 */
public class BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
