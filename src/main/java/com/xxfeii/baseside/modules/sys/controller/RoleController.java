package com.xxfeii.baseside.modules.sys.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xxfeii.baseside.common.controller.BaseController;
import com.xxfeii.baseside.common.utils.UUIDGenerator;
import com.xxfeii.baseside.modules.es.utils.PageUtil;
import com.xxfeii.baseside.modules.sys.entity.Menu;
import com.xxfeii.baseside.modules.sys.entity.Role;
import com.xxfeii.baseside.modules.sys.exception.AjaxException;
import com.xxfeii.baseside.modules.sys.model.TreeNode;
import com.xxfeii.baseside.modules.sys.service.MenuService;
import com.xxfeii.baseside.modules.sys.service.RoleService;
import com.xxfeii.baseside.modules.sys.utils.Constant;
import com.xxfeii.baseside.modules.sys.utils.PagerUtil;
import com.xxfeii.baseside.modules.sys.utils.dtgrid.Pager;

/**
 * 角色controller
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/sys/role")
public class RoleController extends BaseController{

	@Resource
	private RoleService roleService;
	@Resource
	private MenuService menuService;
	
	/**
	 * 进入角色列表
	 * @param req
	 * @return
	 */
	@RequestMapping("roleListUI.html")
	public String roleListUI(HttpServletRequest req){
		return Constant.BACK_PATH+"/modules/sys/roleList";
	}
	
	@RequestMapping(value="roleList.html")
	@ResponseBody
	public Object roleList(String gridPager){
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
		List<Role> roles = roleService.findPage(parameters);
		int count = roleService.getCount(parameters);
		PagerUtil pagerUtil = new PagerUtil(count, pager.getPageSize(),pager.getNowPage());
		parameters.clear();
		parameters.put("isSuccess", Boolean.TRUE);
		parameters.put("nowPage", pager.getNowPage());
		parameters.put("pageSize", pager.getPageSize());
		parameters.put("pageCount", pagerUtil.getPageCount());
		parameters.put("recordCount", count);
		parameters.put("startRecord", pagerUtil.getStartRow());
		//列表展示数据
		parameters.put("exhibitDatas", roles);
		return parameters;
	}
	
	/**
	 * 校验角色名
	 * @return
	 */
	@RequestMapping("validateRoleName.html")
	@ResponseBody
	public Object validateRoleName(String roleName){
		try {
			Role role = roleService.findRoleByName(roleName);
			if(null != role){
				return false;
			}else{
				return true;
			}
		} catch (Exception e) {
			throw new AjaxException(e.getMessage());
		}
	}
	
	@RequestMapping("addRoleUI.html")
	public String addRoleUI(){
		return Constant.BACK_PATH+"/modules/sys/addRole";
	}
	
	@RequestMapping("addRole.html")
	@ResponseBody
	public Object add(Role role,HttpServletRequest req){
		String roleName = role.getRoleName();
		String roleKey = role.getRoleKey();
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(roleName) && StringUtils.isNotEmpty(roleKey)){
			role.setSid(UUIDGenerator.getUUID());
			role.setCreateTime(new Date());
			role.setStatus(1);
			int result = roleService.insert(role);
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
		}else{
			map.put("success", Boolean.FALSE);
			map.put("data", null);
			map.put("message", "添加失败");
		}
		return map;
	}
	
	/**
	 * 进入修改页面
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("editUI.html")
	public String editRole(Model model, HttpServletRequest request, String id){
		if(StringUtils.isNotEmpty(id)){
			Role role = roleService.findRoleById(id);
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			model.addAttribute("page", page);
			model.addAttribute("role", role);
		}
		return Constant.BACK_PATH+"/modules/sys/addRole";
	}
	
	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	@RequestMapping("editRole.html")
	@ResponseBody
	public Object update(Role role)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			role.setUpdateTime(new Date());
			int result = roleService.update(role);
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
			map.put("success", false);
			map.put("data", null);
			map.put("message", "系统错误");
			log.error(e.getMessage());
		}
		return map;
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("deleteBatch.html")
	@ResponseBody
	public Object deleteBatch(String ids){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String[] mid = ids.split(",");
			List<String> mids = Arrays.asList(mid);
			boolean flag = roleService.deleteRole(mids);
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
		} catch (Exception e) {
			result.put("success", false);
			result.put("data", null);
			result.put("message", "系统错误");
			log.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 进入分配权限页面
	 */
	@RequestMapping("assortUI.html")
	public String assortUI(Model model, HttpServletRequest request,String id){
		Role role = roleService.findRoleById(id);
		Map<String,Object> parameters = new HashMap<String,Object>();
		int[] menuStatus = {1,2};
		parameters.put("menuStatus", menuStatus);
		List<Menu> allMenus = menuService.findPage(parameters);
		List<Menu> menus = new ArrayList<Menu>();
		menus.addAll(role.getMenuList());
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		for(Menu am : allMenus){
			boolean ischoose = false;
			for(Menu m : menus){
				if(am.getMenuName().equals(m.getMenuName())){
					ischoose = true;
					menus.remove(m);
					break;
				}
			}
			am.setChoose(ischoose);
			TreeNode treeNode = new TreeNode();
			treeNode.setId(am.getSid()+"");
			treeNode.setName(am.getMenuName());
			treeNode.setOpen(true);
			treeNode.setpId(am.getPid()+"");
			treeNode.setChecked(ischoose);
			treeNodes.add(treeNode);
		}
		PageUtil page = new PageUtil();
		page.setPageNum(Integer.valueOf(request.getParameter("page")));
		page.setPageSize(Integer.valueOf(request.getParameter("rows")));
		page.setOrderByColumn(request.getParameter("sidx"));
		page.setOrderByType(request.getParameter("sord"));
		model.addAttribute("page", page);
		model.addAttribute("role", role);
		model.addAttribute("allMenus", allMenus);
		model.addAttribute("treeNodes", JSON.toJSON(treeNodes));
		return Constant.BACK_PATH+"/modules/sys/assort";
	}
	
	/**
	 * 保存权限
	 * @param roleId
	 * @param menuIds
	 * @return
	 */
	@RequestMapping("assort.html")
	@ResponseBody
	public Object assort(String roleId, String menuIds){
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			List<String> listMenu = new ArrayList<String>();
			if(StringUtils.isNotBlank(menuIds))
			{
				listMenu = Arrays.asList(menuIds.split(","));
			}
			boolean result = roleService.addRolePermBatch(roleId, listMenu);
			if(result)
			{
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "授权成功");
			}else
			{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "授权失败");
			}
		}catch(Exception e)
		{
			map.put("success", Boolean.FALSE);
			map.put("data", null);
			map.put("message", "授权失败");
			throw new AjaxException(e);
		}
		return map;
	}
	
}
