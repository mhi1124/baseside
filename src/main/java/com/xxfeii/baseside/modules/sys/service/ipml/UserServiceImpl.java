package com.xxfeii.baseside.modules.sys.service.ipml;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xxfeii.baseside.common.service.impl.BaseServiceImpl;
import com.xxfeii.baseside.modules.sys.entity.User;
import com.xxfeii.baseside.modules.sys.mapper.RoleMapper;
import com.xxfeii.baseside.modules.sys.mapper.UserMapper;
import com.xxfeii.baseside.modules.sys.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Resource
	private UserMapper userMapper;
	
	@Resource
	private RoleMapper roleMapper;
	
	@Override
	public boolean getUserCount(String accountName, String password) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userStatus", 1);
		map.put("accountName", accountName);
		map.put("password", password);
		int count = super.getCount(map);
		if(count > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public User findUserByAccountName(String accountName, String password) {
		return userMapper.findUserByAccountName(accountName, password);
	}

	@Transactional
	@Override 
	public int insert(User user){
		int i = userMapper.insert(user);
		if(i>0){
			roleMapper.addRoleUser(user.getSid(),user.getRole().getSid());
		}
		return i;
	}

	@Override
	public User findUserById(String id) {
		return userMapper.findUserBySid(id);
	}

}
