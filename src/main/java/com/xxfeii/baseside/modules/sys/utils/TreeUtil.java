package com.xxfeii.baseside.modules.sys.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.xxfeii.baseside.modules.sys.entity.Menu;

/**
 * 
 * @ClassName TreeUtil
 * @Description 树形结构工具类
 * @author davi
 * @date 2016年9月12日
 *
 */
public class TreeUtil {

	
	/**
	 * 组装菜单
	 * @param menus
	 * @return
	 */
	public static List<Menu> packageMenu(List<Menu> menus){
		List<Menu> tmenus = new ArrayList<Menu>();
		for(Menu menu : menus){
			if(null == menu.getPid()){
				packageMenu(menu,menus);
				tmenus.add(menu);
			}
		}
		return tmenus;
	}
	
	/**
	 * 迭代封装菜单
	 * @param menu
	 * @param menus
	 */
	private static void packageMenu(Menu menu,List<Menu> menus){
		List<Menu> childMenus = getChildList(menu,menus);
		menu.setChildMenus(childMenus);
		for(Menu childMenu : childMenus){
			if(hasChild(childMenu,menus)){
				packageMenu(childMenu,menus);
			}
		}
	}
	
	/**
	 * 获取菜单的子菜单
	 * @param menu 该菜单
	 * @param menus 所有的菜单
	 * @return 菜单的子菜单
	 */
	private static List<Menu> getChildList(Menu menu,List<Menu> menus){
		List<Menu> childMenus = new ArrayList<Menu>();
		Menu m = null;
		for(int i=0,size=menus.size();i<size;i++){
			m = menus.get(i);
			if(null != m.getPid() && m.getPid().equals(menu.getSid())){
				childMenus.add(m);
			}
		}
		return childMenus;
	}
	
	/**
	 * 是否有子菜单
	 * @param menu
	 * @param menus
	 * @return 
	 */
	private static boolean hasChild(Menu menu,List<Menu> menus){
		return getChildList(menu,menus).size() > 0 ? true:false;
	}
}
