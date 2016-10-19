package com.xxfeii.baseside.modules.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.xxfeii.baseside.modules.sys.service.RoleService;

public class RoleServiceTest extends BaseTest{

	@Resource
	private RoleService roleService;
	
	@Test
	public void getCountTest(){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Integer> st = new ArrayList<>();
		st.add(1);
		st.add(2);
		map.put("roleName", "管理员");
		map.put("status", st);
		int count = roleService.getCount(map);
		System.out.println(count);
	}
	
	
}
