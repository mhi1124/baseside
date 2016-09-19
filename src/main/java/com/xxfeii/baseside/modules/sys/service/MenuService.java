package com.xxfeii.baseside.modules.sys.service;

import java.util.List;

import com.xxfeii.baseside.common.service.BaseService;
import com.xxfeii.baseside.modules.sys.entity.Menu;

/**
 * 
 * @ClassName MenuService
 * @Description 菜单服务类
 * @author davi
 * @date 2016年9月8日
 * 
 */
public interface MenuService extends BaseService<Menu> {
	
	/**
	 * 查询用户权限内的菜单
	 * 
	 * @param userId
	 * @return
	 */
	public List<Menu> findMenuByUserId(String userId);
}
