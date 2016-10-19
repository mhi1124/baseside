package com.xxfeii.baseside.modules.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public void insertTest(){
		Menu t = new Menu();
		t.setMenuName("test");
		menuService.insert(t);
	}
	
	@Test
	public void deleteBatchByIdTest(){
		List<String> ids = new ArrayList<String>();
		ids.add("1");
		ids.add("fdsfdsfdsfd11");
		ids.add("fdsfdsfdsfd");
		menuService.deleteBatchById(ids);
	}
	
	@Test
	public void insertBatchTest(){
		Menu t = new Menu();
		t.setMenuName("test");
		t.setMenuUrl("fdsfdsf");
		List<Menu> menus = new ArrayList<Menu>();
		menus.add(t);
		Menu t1 = new Menu();
		t1.setMenuName("test");
		t1.setMenuUrl("fdfdf");
		menus.add(t1);
		menuService.insertBatch(menus);
	}
	
	@Test
	public void getCountTest(){
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("pageNo", 0);
		parameters.put("pageSize", 10);
		int[] menuStatus = {1,2};
		parameters.put("menuStatus", menuStatus);
		int count = menuService.getCount(parameters);
		System.out.println(count);
	}
	
	@Test
	public void remTest(){
		List<String> a = new ArrayList<String>();
		a.add("aa");
		a.add("bb");
		a.add("cc");
		List<String> b = new ArrayList<>();
		b.add("bb");
		b.add("dd");
		a.removeAll(b);
		for(String s : a){
			System.out.println(s);
		}
	}
	
	@Test
	public void deleteTest(){
		int s = menuService.delete("fdsfdsfdsfd");
		System.out.println(s);
	}
	
	@Test
	public void updateTest(){
		Menu t = new Menu();
		t.setMenuUrl("");
		t.setMenuType(0);
		int s = menuService.update(t);
		System.out.println(s);
	}
	@Test
	public void findPageTest(){
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("pageNo", 0);
		parameters.put("pageSize", 10);
		//int[] menuStatus = {0,1};
		//parameters.put("menuStatus", menuStatus);
		parameters.put("menuName", "test");
		List<Menu> menus = menuService.findPage(parameters);
		System.out.println(menus.size());
	}
	
	@Test
	public void findMenuByUserIdTest(){
		List<Menu> menus = menuService.findMenuByUserId("1");
		System.out.println(menus.size());
	}
	
	@Test
	public void findMenuByIdTest(){
		Menu menu = menuService.findMenuById(1l);
		System.out.println(menu.toString());
	}
}
