package com.xxfeii.baseside.modules.sys.service.ipml;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xxfeii.baseside.common.service.impl.BaseServiceImpl;
import com.xxfeii.baseside.modules.sys.entity.User;
import com.xxfeii.baseside.modules.sys.mapper.UserMapper;
import com.xxfeii.baseside.modules.sys.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Resource
	private UserMapper userMapper;
	
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


}
