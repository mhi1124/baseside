package com.xxfeii.baseside.common.mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName BaseMapper
 * @Description dao层的公共方法
 * @author davi
 * @date 2016年7月17日
 * 
 */
public interface BaseMapper<T> {

	/**
	 * 保存实体类
	 * 
	 * @param t
	 * @return
	 */
	public int insert(T t);
	
	/**
	 * 批量保存
	 * @param t
	 * @return
	 */
	public int insertBatch(List<T> t);
	
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
	public int getCount(Map<String, Object> map);

	/**
	 * 分页查询
	 * 
	 * @param map
	 * @return
	 */
	public List<T> findPage(Map<String, Object> map);
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public int deleteBatchById(List<String> ids);

}
