package com.xxfeii.baseside.modules.sys.mapper;

import java.util.List;

import com.xxfeii.baseside.common.mapper.BaseMapper;
import com.xxfeii.baseside.modules.sys.entity.Menu;

/**
 * 
 * @ClassName MeunMapper
 * @Description 菜单的dao层
 * @author davi
 * @date 2016年7月17日
 *
 */
public interface MenuMapper extends BaseMapper<Menu>{

	/**
	 * 查询用户权限内的菜单
	 * 
	 * @param userId
	 * @return
	 */
	public List<Menu> findMenuByUserId(String userId);
	
	/**
	 * 根据登陆账户获取用户的菜单
	 * @param accountName
	 * @return
	 */
	public List<Menu> findMenuByAccountName(String accountName);
}
