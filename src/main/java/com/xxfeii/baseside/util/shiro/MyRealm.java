package com.xxfeii.baseside.util.shiro;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.xxfeii.baseside.modules.sys.entity.Menu;
import com.xxfeii.baseside.modules.sys.entity.User;
import com.xxfeii.baseside.modules.sys.mapper.MenuMapper;
import com.xxfeii.baseside.modules.sys.mapper.UserMapper;



/**
 * 认证&授权
 * @author 
 * @create 2016年11月2日
 */
public class MyRealm extends AuthorizingRealm{

	@Resource
	private MenuMapper menuMapper;
	@Resource
	private UserMapper userMapper;
	
	/**
	 * 授权信息
	 * 只有需要验证权限时才会调用, 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.在配有缓存的情况下，只加载一次.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		User user = ShiroAuthenticationManager.getUserEntity();
		if (user != null) {
			List<Menu> menuList = menuMapper.findMenuByUserId(user.getSid());
			// 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			//根据用户ID查询角色（role），放入到Authorization里。
			// 单角色用户情况
			info.addRole(user.getRole().getRoleName());
			// 多角色用户情况
			// info.setRoles(user.getRolesName());
			// 用户的角色对应的所有权限
			for (Menu menu : menuList) {
				info.addStringPermission(menu.getSourceKey());
			}
			//或者直接查询出所有权限set集合
			//info.setStringPermissions(permissions);
			return info;
		}
		return null;
	}

	/**
	 * 认证信息,认证回调函数,登录时调用
	 * </br>首先根据传入的用户名获取User信息；然后如果user为空，那么抛出没找到帐号异常UnknownAccountException；
	 * </br>如果user找到但锁定了抛出锁定异常LockedAccountException；最后生成AuthenticationInfo信息，
	 * </br>交给间接父类AuthenticatingRealm使用CredentialsMatcher进行判断密码是否匹配，
	 * </br>如果不匹配将抛出密码错误异常IncorrectCredentialsException；
	 * </br>另外如果密码重试次数太多将抛出超出重试次数异常ExcessiveAttemptsException；
	 * </br>在组装SimpleAuthenticationInfo信息时， 需要传入：身份信息（用户名）、凭据（密文密码）、加密盐（username+salt），
	 * </br>CredentialsMatcher使用盐加密传入的明文密码和此处的密文密码进行匹配。
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		SimpleAuthenticationInfo authenticationInfo = null;
		String accountName = (String)token.getPrincipal();
		if(StringUtils.isNotEmpty(accountName)){
			User user = userMapper.findUserByAccountName(accountName, null);
			if (user != null) {
				if ("2".equals(user.getUserStatus())) {
					throw new LockedAccountException(); // 帐号被锁定
				}
				// 从数据库查询出来的账号名和密码,与用户输入的账号和密码对比
				// 当用户执行登录时,在方法处理上要实现subject.login(token);
				// 然后会自动进入这个类进行认证
				// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得shiro自带的不好可以自定义实现
				authenticationInfo = new SimpleAuthenticationInfo(
						user, // 用户对象
						user.getPassword(), // 密码
						ByteSource.Util.bytes(accountName+user.getSalt()),// salt=username+salt
						getName() // realm name
						);
				return authenticationInfo;
			} else {
				throw new UnknownAccountException();// 没找到帐号
			}
		}else{
			throw new UnknownAccountException();// 没找到帐号
		}
	}

}
