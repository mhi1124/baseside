package com.xxfeii.baseside.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.xxfeii.baseside.common.mapper.BaseMapper;
import com.xxfeii.baseside.common.service.BaseService;

/**
 * 
 * @ClassName BaseServiceImpl
 * @Description 基础服务类，记录公共的增删改查
 * @author davi
 * @date 2016年7月17日
 *
 */
public class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	private BaseMapper<T> baseMapper;
	
	@Override
	public int insertEntity(T t) {
		return baseMapper.insertEntity(t);
	}

}
