package com.xxfeii.baseside.modules.sys.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xxfeii.baseside.common.controller.BaseController;
import com.xxfeii.baseside.modules.sys.entity.Menu;
import com.xxfeii.baseside.modules.sys.service.MenuService;
import com.xxfeii.baseside.modules.sys.utils.Constant;
import com.xxfeii.baseside.modules.sys.utils.PagerUtil;
import com.xxfeii.baseside.modules.sys.utils.dtgrid.Pager;

@Controller
@Scope("prototype")
@RequestMapping("menu/")
public class MenuController extends BaseController{

	@Resource
	private MenuService menuService;
	
	@RequestMapping("toAddMenu.html")
	public String toAddMenu(HttpServletRequest req){
		return Constant.BACK_PATH+"modules/sys/addMenu";
	}
	
	@RequestMapping("addMenu.html")
	public String addMenu(Menu menu,HttpServletRequest req){
		String menuName = req.getParameter("menuName");
		menuService.insert(menu);
		return Constant.BACK_PATH+"/modules/sys/listMenu";
	}
	
	/**
	 * 进入菜单列表
	 * @param req
	 * @return
	 */
	@RequestMapping("toMenuList.html")
	public String toMenuList(HttpServletRequest req){
		return Constant.BACK_PATH+"/modules/sys/menuList";
	}
	
	@RequestMapping("menuList.html")
	@ResponseBody
	public Object menuList(String gridPager){
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		Map<String,Object> parameters = null;
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		if (parameters.isEmpty()) {
			parameters.put("pageNo", 0);
			parameters.put("pageSize", 10);
		}else{
			parameters.put("pageNo", pager.getNowPage());
			parameters.put("pageSize", pager.getPageSize());
		}
		List<Menu> menus = menuService.findPage(parameters);
		int count = menuService.getCount(parameters);
		PagerUtil pagerUtil = new PagerUtil(count, 10,1);
		parameters.clear();
		parameters.put("isSuccess", Boolean.TRUE);
		parameters.put("nowPage", pager.getNowPage());
		parameters.put("pageSize", pager.getPageSize());
		parameters.put("pageCount", pagerUtil.getPageCount());
		parameters.put("recordCount", count);
		parameters.put("startRecord", pagerUtil.getStartRow());
		//列表展示数据
		parameters.put("exhibitDatas", menus);
		return parameters;
	}
}
