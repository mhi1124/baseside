package com.xxfeii.baseside.modules.plat.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.xxfeii.baseside.common.controller.BaseController;
import com.xxfeii.baseside.common.utils.DateUtil;
import com.xxfeii.baseside.modules.es.utils.PageUtil;
import com.xxfeii.baseside.modules.plat.entity.PlatAccount;
import com.xxfeii.baseside.modules.plat.service.PlatAccountService;
import com.xxfeii.baseside.modules.sys.exception.AjaxException;
import com.xxfeii.baseside.modules.sys.exception.SystemException;
import com.xxfeii.baseside.modules.sys.utils.Constant;

/**
 * 账号对卡号
 * 
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/plat")
public class PlatAccountController extends BaseController {

	@Resource
	private PlatAccountService platAccountService;

	@RequestMapping("/accountListUI.html")
	public String accountListUI(HttpServletRequest req) {
		Date endTime = new Date();
		Date creatTime = DateUtil.addMonth(endTime, -1);
		req.setAttribute("startTime", DateUtil.dateToString(creatTime,DateUtil.formatPatternDate));
		req.setAttribute("endTime", DateUtil.dateToString(endTime,DateUtil.formatPatternDate));
		return Constant.BACK_PATH + "/modules/plat/accountList";
	}

	/**
	 * 账号列表
	 * 
	 * @param gridPager
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/accountList.html")
	@ResponseBody
	public Object accountCardList(String gridPager, HttpSession session) {
		return super.getList(gridPager, platAccountService, null);
	}

	/**
	 * 添加账号
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("addAccountUI.html")
	public String addAccountCardUI(HttpServletRequest req) {
		List<String> platList = getPlatList();
		req.setAttribute("platList", platList);
		return Constant.BACK_PATH + "/modules/plat/addAccount";
	}

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping("addAccount.html")
	@ResponseBody
	public Object addAccountCard(PlatAccount platAccount, HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = platAccountService.insert(platAccount);
		if (result > 0) {
			map.put("success", Boolean.TRUE);
			map.put("data", null);
			map.put("message", "添加成功");
		} else {
			map.put("success", Boolean.FALSE);
			map.put("data", null);
			map.put("message", "添加失败");
		}
		return map;
	}
	
	@RequestMapping("deleteBatch.html")
	@ResponseBody
	public Object deleteBatch(String ids){
		return super.deleteBatch(ids, platAccountService);
	}

	/**
	 * 修改
	 * @param req
	 * @return
	 */
	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest req, String id){
		try
		{
			Map<String,Object> map = new HashMap<>();
			map.put("sid", id);
			List<PlatAccount> accountCards = platAccountService.findPage(map);
			PlatAccount platAccount = new PlatAccount(); 
			if(null != accountCards){
				platAccount = accountCards.get(0);
			}
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(req.getParameter("page")));
			page.setPageSize(Integer.valueOf(req.getParameter("rows")));
			page.setOrderByColumn(req.getParameter("sidx"));
			page.setOrderByType(req.getParameter("sord"));
			
			List<String> platList = getPlatList();
			model.addAttribute("platList", platList);
			model.addAttribute("page", page);
			model.addAttribute("platAccount", platAccount);
			
			return Constant.BACK_PATH+"/modules/plat/addAccount";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("/editAccount.html")
	@ResponseBody
	public Object editAccountCard(PlatAccount platAccount){
		Map<String, Object> map = new HashMap<String, Object>();
			int result = platAccountService.update(platAccount);
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
		return map;
	}
	
	/**
	 * 校验用户名
	 * @param accountName
	 * @return
	 */
	@RequestMapping("validateAccount.html")
	@ResponseBody
	public Object validateAccount(String platAccount){
		try
		{
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("platAccount", platAccount);
			int count = platAccountService.getCount(map);
			if(count == 0)
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
	 * 获取所有平台
	 */
	private List<String> getPlatList(){
		List<String> platList = new ArrayList<String>();
		platList.add("AM");
		platList.add("SMT");
		platList.add("EBAY");
		platList.add("lazada");
		platList.add("wish");
		return platList;
	}
}
