package com.xxfeii.baseside.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional(readOnly = true, propagation=Propagation.REQUIRED)
	@Override
	public int insert(T t) {
		return baseMapper.insert(t);
	}

	@Transactional(readOnly = true, propagation=Propagation.REQUIRED)
	@Override
	public int update(T t) {
		return baseMapper.update(t);
	}

	@Transactional(readOnly = true, propagation=Propagation.REQUIRED)
	@Override
	public int delete(String id) {
		return baseMapper.delete(id);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return baseMapper.getCount(map);
	}

	@Override
	public List<T> findPage(Map<String, Object> map) {
		return baseMapper.findPage(map);
	}

	@Transactional(readOnly = true, propagation=Propagation.REQUIRED) 
	@Override
	public int deleteBatchById(List<String> ids) {
		try {
			return baseMapper.deleteBatchById(ids);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Transactional(readOnly = true, propagation=Propagation.REQUIRED) 
	@Override
	public int insertBatch(List<T> t) {
		try {
			return baseMapper.insertBatch(t);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	

}
