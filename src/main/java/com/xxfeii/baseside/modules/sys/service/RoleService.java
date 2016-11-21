package com.xxfeii.baseside.modules.sys.service;

import java.util.List;

import com.xxfeii.baseside.common.service.BaseService;
import com.xxfeii.baseside.modules.sys.entity.Role;
import com.xxfeii.baseside.modules.sys.entity.User;
import com.xxfeii.baseside.modules.sys.exception.SystemException;

/**
 * 角色服务类
 * @author Administrator
 *
 */
public interface RoleService extends BaseService<Role>{

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public Role findRoleById(String id) throws SystemException;
	
	/**
	 * 通过角色名称查询
	 * @param roleName
	 * @return
	 * @throws SystemException
	 */
	public Role findRoleByName(String roleName) throws SystemException;
	
	/**
	 * 删除角色及角色的菜单
	 * @param ids
	 * @return
	 */
	public boolean deleteRole(List<String> ids);
	
	/**
	 * 保存角色菜单 
	 * @param rid
	 * @param menuids
	 * @return
	 */
	public boolean addRolePermBatch(String rid,List<String> menuids) throws SystemException;
	
	/**
	 * 添加用户角色
	 * @param user
	 * @return
	 * @throws SystemException
	 */
	public Integer addRoleUser(User user)throws SystemException;
}
