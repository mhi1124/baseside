package com.xxfeii.baseside.util.shiro;

import org.apache.shiro.SecurityUtils;

import com.xxfeii.baseside.modules.sys.entity.User;


/**
 * 认证工具类
 * @author 
 * @create 2016年11月2日
 */
public class ShiroAuthenticationManager {

	/**
	 * 获取用户
	 * 
	 * @return
	 */
	public static User getUserEntity() {
		return (User) SecurityUtils.getSubject().getPrincipal();
	}
}
