package com.xxfeii.baseside.modules.sys.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.xxfeii.baseside.modules.sys.entity.Role;
import com.xxfeii.baseside.modules.sys.entity.User;
import com.xxfeii.baseside.modules.sys.exception.AjaxException;
import com.xxfeii.baseside.modules.sys.exception.SystemException;
import com.xxfeii.baseside.modules.sys.service.RoleService;
import com.xxfeii.baseside.modules.sys.service.UserService;
import com.xxfeii.baseside.modules.sys.utils.Constant;
import com.xxfeii.baseside.modules.sys.utils.PagerUtil;
import com.xxfeii.baseside.modules.sys.utils.dtgrid.Pager;
import com.xxfeii.baseside.util.shiro.ShiroEndecryptUtil;
import com.xxfeii.baseside.util.shiro.ShiroUtil;

/**
 * 用户controller
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/sys/user")
public class UserController extends BaseController{

	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	
	@RequestMapping("userListUI.html")
	public String userListUI(HttpServletRequest req){
		return Constant.BACK_PATH+"/modules/sys/userList";
	}
	
	/**
	 * 用户列表
	 * @param gridPager
	 * @param session
	 * @return
	 */
	@RequestMapping(value="userList.html")
	@ResponseBody
	public Object userList(String gridPager,HttpSession session){
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		User user = ShiroUtil.getUser(); 
				//(User) session.getAttribute(Constant.USE_RSESSION_ID);
		String creatorName = null;
		if(null != user){
			String roleKey = user.getRole().getRoleKey();
			//如果不是管理员或超级管理员就只能查看自己添加的用户
			if(!("administrator".equals(roleKey)||"admin".equals(roleKey))){
				creatorName = user.getAccountName();
			}
		}
		Map<String,Object> parameters = null;
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		int pageNo = (pager.getNowPage()-1)*pager.getPageSize();
		if(pageNo>pager.getRecordCount()){
			pageNo = 0;
		}
		parameters.put("creatorName", creatorName);
		parameters.put("pageNo", pageNo);
		parameters.put("pageSize", pager.getPageSize());
		List<User> roles = userService.findPage(parameters);
		int count = userService.getCount(parameters);
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
	 * 添加用户页面
	 * @param req
	 * @return
	 */
	@RequestMapping("addUserUI.html")
	public String addRoleUI(HttpServletRequest req){
		User user = ShiroUtil.getUser();
		String roleKey = user.getRole().getRoleKey();
		List<Role> roles = null;
		if(roleKey.equals("administrator")){
			roles = roleService.findPage(null);
		}else if(roleKey.equals("admin")){
			Map<String,Object> map = new HashMap<>();
			map.put("roleKey", "administrator");
			roles = roleService.findPage(map);
		}
		req.setAttribute("roleList", roles);
		return Constant.BACK_PATH+"/modules/sys/addUser";
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping("addUser.html")
	@ResponseBody
	public Object add(User user,HttpServletRequest req){
		String accountName = user.getAccountName();
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(accountName)){
			user.setSid(UUIDGenerator.getUUID());
			user.setCreateTime(new Date());
			user.setUserStatus(1);
			User loginUser = ShiroUtil.getUser();
			user.setCreatorName(loginUser.getAccountName());
			ShiroEndecryptUtil.md5User(user, ShiroEndecryptUtil.shiro_hashIterations);
			int result = userService.insert(user);
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
	 * 校验用户名
	 * @param accountName
	 * @return
	 */
	@RequestMapping("validateAccountName.html")
	@ResponseBody
	public Object validateAccountName(String accountName){
		try
		{
			User user = userService.findUserByAccountName(accountName, null);
			if(user == null)
			{
				return true;
			}else
			{
				return false;
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
	
	/**
	 * 添加用户页面
	 * @param req
	 * @return
	 */
	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest req, String id){
		try
		{
			User user = userService.findUserById(id);
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(req.getParameter("page")));
			page.setPageSize(Integer.valueOf(req.getParameter("rows")));
			page.setOrderByColumn(req.getParameter("sidx"));
			page.setOrderByType(req.getParameter("sord"));
			String roleKey = user.getRole().getRoleKey();
			List<Role> roles = null;
			if(roleKey.equals("administrator")){
				roles = roleService.findPage(null);
			}else if(roleKey.equals("admin")){
				Map<String,Object> map = new HashMap<>();
				map.put("roleKey", "administrator");
				roles = roleService.findPage(map);
			}
			model.addAttribute("roleList", roles);
			model.addAttribute("page", page);
			model.addAttribute("user", user);
			
			return Constant.BACK_PATH+"/modules/sys/addUser";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("/editUser.html")
	@ResponseBody
	public Object editUser(User user){
		String accountName = user.getAccountName();
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(accountName)){
			user.setUpdateTime(new Date());
			//User loginUser = ShiroUtil.getUser();
			//user.setCreatorName(loginUser.getAccountName());
			ShiroEndecryptUtil.md5User(user, ShiroEndecryptUtil.shiro_hashIterations);
			int result = userService.update(user);
			if(result>0)
			{
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "修改成功");
			}else
			{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "修改失败");
			}
		}else{
			map.put("success", Boolean.FALSE);
			map.put("data", null);
			map.put("message", "修改失败");
		}
		return map;
	}
	
	@RequestMapping("deleteBatch.html")
	@ResponseBody
	public Object deleteBatch(String ids){
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			String[] userIds = ids.split(",");
			List<String> list = Arrays.asList(userIds);;
			int cnt = userService.deleteBatchById(list);
			if(cnt == list.size())
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
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return result;
	}
}
