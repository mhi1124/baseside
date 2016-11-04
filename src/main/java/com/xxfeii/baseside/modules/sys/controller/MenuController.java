package com.xxfeii.baseside.modules.sys.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xxfeii.baseside.common.controller.BaseController;
import com.xxfeii.baseside.common.model.Select2Entity;
import com.xxfeii.baseside.modules.es.utils.PageUtil;
import com.xxfeii.baseside.modules.sys.entity.Menu;
import com.xxfeii.baseside.modules.sys.entity.User;
import com.xxfeii.baseside.modules.sys.exception.AjaxException;
import com.xxfeii.baseside.modules.sys.exception.SystemException;
import com.xxfeii.baseside.modules.sys.service.MenuService;
import com.xxfeii.baseside.modules.sys.utils.Constant;
import com.xxfeii.baseside.modules.sys.utils.PagerUtil;
import com.xxfeii.baseside.modules.sys.utils.TreeUtil;
import com.xxfeii.baseside.modules.sys.utils.dtgrid.Pager;
import com.xxfeii.baseside.util.shiro.ShiroUtil;

@Controller
@Scope("prototype")
@RequestMapping("/sys/menu")
public class MenuController extends BaseController{

	@Resource
	private MenuService menuService;
	
	@RequestMapping(value="addMenuUI.html")
	public String toAddMenu(Model model,HttpSession session){
		try {
			User user = ShiroUtil.getUser();
					//(User) session.getAttribute(Constant.USE_RSESSION_ID);
			List<Menu> menus = menuService.findMenuByUserId(user.getSid());
			TreeUtil tu = new TreeUtil();
			List<Select2Entity> smenu = tu.getSelectTree(menus);
			//menus = TreeUtil.(menus);
			model.addAttribute("menus", smenu);
			return Constant.BACK_PATH+"/modules/sys/addMenu";
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("addMenu.html")
	@ResponseBody
	public Object addMenu(Menu menu,HttpServletRequest req){
		Map<String, Object> map = new HashMap<String, Object>();
		
		int result = menuService.insert(menu);
		if(result>0)
		{
			map.put("success", Boolean.TRUE);
			map.put("data", null);
			map.put("message", "添加成功");
		}else
		{
			map.put("success", Boolean.FALSE);
			map.put("data", null);
			map.put("message", "添加失败");
		}
		return map;
		//return Constant.BACK_PATH+"/modules/sys/listMenu";
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
	
	@RequestMapping("deleteBatch.html")
	@ResponseBody
	public Object deleteBatch(String ids){
		Map<String, Object> result = new HashMap<String, Object>();
		String[] mid = ids.split(",");
		List<String> mids = Arrays.asList(mid);
		boolean flag = menuService.deleteMenuAndRole(mids);
		if(flag)
		{
			result.put("success", true);
			result.put("data", null);
			result.put("message", "删除成功");
		}else
		{
			result.put("success", false);
			result.put("data", null);
			result.put("message", "删除失败");
		}
		return result;
	}
	
	/**
	 * 资源列表
	 * @param gridPager
	 * @return
	 */
	@RequestMapping("menuList.html")
	@ResponseBody
	public Object menuList(String gridPager){
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		Map<String,Object> parameters = null;
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		int pageNo = (pager.getNowPage()-1)*pager.getPageSize();
		if(pageNo>pager.getRecordCount()){
			pageNo = 0;
		}
		parameters.put("pageNo", pageNo);
		parameters.put("pageSize", pager.getPageSize());
		List<Menu> menus = menuService.findPage(parameters);
		int count = menuService.getCount(parameters);
		PagerUtil pagerUtil = new PagerUtil(count, pager.getPageSize(),pager.getNowPage());
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
	
	/**
	 * 修改菜单
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request, Long id) {
		try
		{
			Menu menu = menuService.findMenuById(id);
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			
			User user = ShiroUtil.getUser();
			List<Menu> menus = menuService.findMenuByUserId(user.getSid());
			TreeUtil tu = new TreeUtil();
			List<Select2Entity> smenu = tu.getSelectTree(menus);
			
			model.addAttribute("page", page);
			model.addAttribute("menu", menu);
			model.addAttribute("menus", smenu);
			return Constant.BACK_PATH+"/modules/sys/addMenu";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("editMenu.html")
	@ResponseBody
	public Object update(Menu menu)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			if(menu.getMenuType() == 1)
			{
				menu.setIcon(null);
			}
			int result = menuService.update(menu);
			if(result > 0)
			{
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "编辑成功");
			}else
			{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "编辑失败");
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	
	/**
	 * 选择图标
	 * @return
	 */
	@RequestMapping("icon.html")
	public String icon() {
		return Constant.BACK_PATH + "/modules/sys/icon";
	}
	
	
	
}
