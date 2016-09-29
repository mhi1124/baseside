package com.xxfeii.baseside.modules.sys.mapper;

import org.apache.ibatis.annotations.Param;

import com.xxfeii.baseside.common.mapper.BaseMapper;
import com.xxfeii.baseside.modules.sys.entity.User;

public interface UserMapper extends BaseMapper<User> {

	/**
	 * 查询用户
	 * @param accountName 账号
	 * @param password 密码
	 * @return
	 */
	public User findUserByAccountName(@Param("accountName")String accountName,@Param("password")String password);
}
