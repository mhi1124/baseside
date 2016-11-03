package com.xxfeii.baseside.util.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * 限制登录次数，如果连续5次输错用户名或密码，锁定10分钟 未实现
 * @author 
 * @create 2016年11月2日
 */
public class LimitRetryHashedMatcher extends HashedCredentialsMatcher{

}
