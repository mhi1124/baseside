package com.xxfeii.baseside.util.shiro;

import org.apache.shiro.SecurityUtils;

import com.xxfeii.baseside.modules.sys.entity.User;

public class ShiroUtil {

	/**
     * 获取shiro注册的用户
     * @return
     */
    public static User getUser(){
    	return (User) SecurityUtils.getSubject().getPrincipal();
    }
}
