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
import com.xxfeii.baseside.modules.sys.entity.Role;
import com.xxfeii.baseside.modules.sys.entity.User;
import com.xxfeii.baseside.modules.sys.service.UserService;
import com.xxfeii.baseside.modules.sys.utils.Constant;
import com.xxfeii.baseside.modules.sys.utils.PagerUtil;
import com.xxfeii.baseside.modules.sys.utils.dtgrid.Pager;

/**
 * 用户controller
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("user/")
public class UserController extends BaseController{

	@Resource
	private UserService userService;
	
	@RequestMapping("userListUI.html")
	public String userListUI(HttpServletRequest req){
		return Constant.BACK_PATH+"/modules/sys/userList";
	}
	
	@RequestMapping(value="userList.html")
	@ResponseBody
	public Object userList(String gridPager){
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
	
}
