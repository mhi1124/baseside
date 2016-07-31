package com.xxfeii.baseside.common.mapper;

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
	 * @param t
	 * @return
	 */
	public int insertEntity(T t);
}
