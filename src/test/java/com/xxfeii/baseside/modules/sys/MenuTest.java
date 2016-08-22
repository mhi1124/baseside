package com.xxfeii.baseside.modules.sys;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xxfeii.baseside.modules.sys.entity.Menu;
import com.xxfeii.baseside.modules.sys.service.MenuService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/config/spring-common.xml"})
public class MenuTest {

	@Resource
	private MenuService menuService;
	
	@Test
	public void insertEntityTest(){
		Menu t = new Menu();
		t.setId("fdsfdsfdsfd");
		t.setMenuName("test");;
		menuService.insert(t);
	}
}
