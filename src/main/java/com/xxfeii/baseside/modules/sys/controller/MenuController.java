package com.xxfeii.baseside.modules.sys.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xxfeii.baseside.common.controller.BaseController;
import com.xxfeii.baseside.modules.sys.entity.MenuEntity;
import com.xxfeii.baseside.modules.sys.service.MenuService;

@Controller
@Scope("prototype")
@RequestMapping("menu/")
public class MenuController extends BaseController{

	@Resource
	private MenuService menuService;
	
	@RequestMapping("toAddMenu")
	public String toAddMenu(HttpServletRequest req){
		return "/modules/sys/addMenu";
	}
	
	@RequestMapping("addMenu")
	public String addMenu(MenuEntity menu,HttpServletRequest req){
		String menuName = req.getParameter("menuName");
		menuService.insertEntity(menu);
		return "/modules/sys/listMenu";
	}
}
