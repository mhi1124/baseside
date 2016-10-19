package com.xxfeii.baseside.modules.sys.mapper;

import java.util.List;
import java.util.Map;

import com.xxfeii.baseside.common.mapper.BaseMapper;
import com.xxfeii.baseside.modules.sys.entity.Role;

public interface RoleMapper extends BaseMapper<Role> {

	/**
	 * 通过参数查询角色
	 * @param map
	 * @return
	 */
	public Role findRoleByParam(Map<String,Object> map);
	
	/**
	 * 删除角色下的用户
	 * @param ids
	 * @return
	 */
	public int deleteRoleUser(List<String> ids);
	
	/**
	 * 删除角色下的菜单
	 * @param ids
	 * @return
	 */
	public int deleteRoleMenu(List<String> ids);
	
	/**
	 * 查询该角色下是否有用户
	 * @param ids
	 * @return
	 */
	public int findRoleUserCount(List<String> ids);
	/**
	 * 查询角色下是否有菜单
	 * @param ids
	 * @return
	 */
	public int findRoleMenuCount(List<String> ids);
	
	/**
	 * 批量添加权限
	 * @param parameter
	 * @return
	 */
	public int addRoleMenuBatch(Map<String,Object> parameter);
	
}
