package com.xxfeii.baseside.common.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xxfeii.baseside.common.service.BaseService;
import com.xxfeii.baseside.modules.es.utils.PageUtil;
import com.xxfeii.baseside.modules.sys.entity.Role;
import com.xxfeii.baseside.modules.sys.entity.User;
import com.xxfeii.baseside.modules.sys.exception.AjaxException;
import com.xxfeii.baseside.modules.sys.exception.SystemException;
import com.xxfeii.baseside.modules.sys.utils.Constant;
import com.xxfeii.baseside.modules.sys.utils.PagerUtil;
import com.xxfeii.baseside.modules.sys.utils.dtgrid.Pager;
import com.xxfeii.baseside.util.shiro.ShiroUtil;

/**
 * 
 * @ClassName BaseController
 * @Description
 * @author davi
 * @date 2016年7月17日
 *
 */
public class BaseController {

	public static Logger log = LoggerFactory.getLogger(BaseController.class);
	
	/**
	 * 获取列表
	 * @param gridPager 插件的数据
	 * @param baseService 
	 * @param params 过滤条件
	 * @return
	 */
	public Map<String,Object> getList(String gridPager,BaseService<?> baseService,Map<String,Object> params){
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
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if(null != params){
			paramMap.putAll(params);
		}
		paramMap.putAll(parameters);
		List<?> dataList = baseService.findPage(paramMap);
		int count = baseService.getCount(paramMap);
		PagerUtil pagerUtil = new PagerUtil(count, pager.getPageSize(),pager.getNowPage());
		parameters.clear();
		parameters.put("isSuccess", Boolean.TRUE);
		parameters.put("nowPage", pager.getNowPage());
		parameters.put("pageSize", pager.getPageSize());
		parameters.put("pageCount", pagerUtil.getPageCount());
		parameters.put("recordCount", count);
		parameters.put("startRecord", pagerUtil.getStartRow());
		//列表展示数据
		parameters.put("exhibitDatas", dataList);
		return parameters;
	}
	
	/**
	 * 删除
	 * @param idsString
	 * @param baseService
	 * @return
	 */
	@Transactional
	public Map<String,Object> deleteBatch(String idsString,BaseService<?> baseService){
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			String[] ids = idsString.split(",");
			List<String> list = Arrays.asList(ids);
			int cnt = baseService.deleteBatchById(list);
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
