package com.xxfeii.baseside.modules.plat.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxfeii.baseside.common.controller.BaseController;
import com.xxfeii.baseside.common.utils.DateUtil;
import com.xxfeii.baseside.modules.plat.entity.PlatFunds;
import com.xxfeii.baseside.modules.plat.entity.PlatAccount;
import com.xxfeii.baseside.modules.plat.model.SelectModel;
import com.xxfeii.baseside.modules.plat.service.PlatFundsService;
import com.xxfeii.baseside.modules.plat.service.PlatFXRateService;
import com.xxfeii.baseside.modules.plat.service.PlatAccountService;
import com.xxfeii.baseside.modules.sys.utils.Constant;

@Controller
@Scope("prototype")
@RequestMapping("/plat")
public class PlatFundsController extends BaseController {

	@Autowired
	private PlatFundsService platFundsService;
	
	@Autowired
	private PlatAccountService platAccountService;
	
	@Autowired
	private PlatFXRateService platFXRateService;
	
	@RequestMapping("/accountFundsListUI.html")
	public String accountFundsListUI(HttpServletRequest req) {
		Date endTime = new Date();
		Date creatTime = DateUtil.addMonth(endTime, -3);
		req.setAttribute("startTime", DateUtil.dateToString(creatTime,DateUtil.formatPatternDate));
		req.setAttribute("endTime", DateUtil.dateToString(endTime,DateUtil.formatPatternDate));
		return Constant.BACK_PATH + "/modules/plat/accountFundsList";
	}

	/**
	 * 账号资金详情列表
	 * 
	 * @param gridPager
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/accountFundsList.html")
	@ResponseBody
	public Object accountFundsList(String gridPager, HttpSession session) {
		Map<String, Object> parameters = super.getList(gridPager, platFundsService, null);
		return parameters;
	}
	
	/**
	 * 添加提现记录
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("addAccountFundsUI.html")
	public String addAccountCardUI(HttpServletRequest req) {
		List<PlatAccount> accounts = platAccountService.findPage(null);
		req.setAttribute("accounts", accounts);
		req.setAttribute("platNos", platAccountService.getPlatNos());
		req.setAttribute("currencyList", platFXRateService.findCurrency());
		return Constant.BACK_PATH + "/modules/plat/addFunds";
	}
	
	/**
	 * 添加用户
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping("addAccountFunds.html")
	@ResponseBody
	public Object addAccountFunds(PlatFunds accountFunds, HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		accountFunds.setCreatTime(new Date());
		int result = platFundsService.insert(accountFunds);
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
	
	/**
	 * 查询平台下的账号
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/getAccounts.html")
	@ResponseBody
	public Object getAccounts(HttpServletRequest req){
		String platNo = req.getParameter("platNo");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("platNo", platNo);
		List<PlatAccount> accounts = platAccountService.findPage(params);
		List<SelectModel> select = new ArrayList<>();
		for(PlatAccount account : accounts){
			SelectModel sm = new SelectModel();
			String pa = account.getPlatAccount();
			sm.setValue(pa);
			sm.setText(pa);
			select.add(sm);
		}
		return select;
	}
	
	/**
	 * 按钮点击P卡转出
	 * @param idsString
	 * @return
	 */
	@RequestMapping("/amountToPcarBatch.html")
	@ResponseBody
	public Object amountToPcarBatch(String ids,String realAmounts){
		Map<String, Object> result = new HashMap<String, Object>();
		/*try
		{
			String[] idss = ids.split(",");
			String[] realAmountss = realAmounts.split(",");
			boolean r= accountFundsService.amountToPcar(idss,realAmountss);
			if(r)
			{
				result.put("success", true);
				result.put("data", null);
				result.put("message", "转出成功");
			}else
			{
				result.put("success", false);
				result.put("data", null);
				result.put("message", "转出失败");
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}*/
		return result;
	}
	
	//实际到账页面
	@RequestMapping("/editFundsUI.html")
	public String editFundsUI(Model model,Integer id){
		PlatFunds accountFunds =platFundsService.findFundsById(id);
		model.addAttribute("funds", accountFunds);
		model.addAttribute("currencyList", platFXRateService.findCurrency());
		return Constant.BACK_PATH + "/modules/plat/editFunds";
	}
	
	//保存实际到账
	@RequestMapping("/editAccountFunds.html")
	@ResponseBody
	public Object editAccountFunds(PlatFunds accountFunds){
		return platFundsService.editAccountFunds(accountFunds);
	}
	
	/**
	 * 按钮点击P卡转出
	 * @param idsString
	 * @return
	 */
	@RequestMapping("/amountToBankBatch.html")
	@ResponseBody
	public Object amountToBankBatch(String ids,String realAmounts){
		Map<String, Object> result = new HashMap<String, Object>();
		/*try
		{
			String[] idss = ids.split(",");
			String[] realAmountss = realAmounts.split(",");
			boolean r= platFundsService.amountToBack(idss,realAmountss);
			if(r)
			{
				result.put("success", true);
				result.put("data", null);
				result.put("message", "转出成功");
			}else
			{
				result.put("success", false);
				result.put("data", null);
				result.put("message", "转出失败");
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}*/
		return result;
			
	}
	
	@RequestMapping("deleteFundsBatch.html")
	@ResponseBody
	public Object deleteFundsBatch(String ids){
		return super.deleteBatch(ids, platFundsService);
	}
}
