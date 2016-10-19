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
	
	/**
	 * 根据登陆账户获取用户的菜单
	 * @param accountName
	 * @return
	 */
	public List<Menu> findMenuByAccountName(String accountName);
	
	/**
	 * 删除菜单及所被赋的权限
	 * @param ids
	 * @return
	 */
	public boolean deleteMenuAndRole(List<String> ids);
	
	/**
	 * 通过id查询菜单
	 * @param sid
	 * @return
	 */
	public Menu findMenuById(Long sid);
}
