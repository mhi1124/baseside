package com.xxfeii.baseside.common.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName BaseService
 * @Description
 * @author davi
 * @date 2016年7月17日
 *
 */
public interface BaseService<T> {

	/**
	 * 保存实体类
	 * 
	 * @param t
	 * @return
	 */
	public int insert(T t);
	
	/**
	 * 修改
	 * 
	 * @param t
	 * @return
	 */
	public int update(T t);
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public int delete(String id);
	
	/**
	 * 获取数量
	 * 
	 * @return
	 */
	public int getCount();

	/**
	 * 分页查询
	 * 
	 * @param map
	 * @return
	 */
	public List<T> findPage(Map<String, Object> map);
}
