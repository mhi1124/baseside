package com.xxfeii.baseside.common.service.impl;

import java.util.List;
import java.util.Map;

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
	public int insert(T t) {
		return baseMapper.insert(t);
	}

	@Override
	public int update(T t) {
		return baseMapper.update(t);
	}

	@Override
	public int delete(String id) {
		return baseMapper.delete(id);
	}

	@Override
	public int getCount() {
		return baseMapper.getCount();
	}

	@Override
	public List<T> findPage(Map<String, Object> map) {
		return baseMapper.findPage(map);
	}
	

}
