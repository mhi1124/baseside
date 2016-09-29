package com.xxfeii.baseside.modules.sys.service;

import com.xxfeii.baseside.common.service.BaseService;
import com.xxfeii.baseside.modules.sys.entity.User;

/**
 * 用户service
 * @author 80002196
 *
 */
public interface UserService extends BaseService<User>{

	/**
	 * 根据用户账户密码查询账号是否存在
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean getUserCount(String userName,String password);
	
	/**
	 * 查询用户
	 * @param userName
	 * @param password
	 * @return
	 */
	public User findUserByAccountName(String accountName,String password);
}
