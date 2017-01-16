package com.xxfeii.baseside.modules.plat.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xxfeii.baseside.common.service.impl.BaseServiceImpl;
import com.xxfeii.baseside.modules.plat.entity.PlatFunds;
import com.xxfeii.baseside.modules.plat.mapper.PlatFundsMapper;
import com.xxfeii.baseside.modules.plat.service.PlatFXRateService;
import com.xxfeii.baseside.modules.plat.service.PlatFundsService;

@Service
public class PlatFundsServiceImpl extends BaseServiceImpl<PlatFunds> implements PlatFundsService {

	@Autowired
	private PlatFundsMapper platFundsMapper;

	@Autowired
	private PlatFXRateService platFXRateService;
	
	@Override
	public PlatFunds findFundsById(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sid", id);
		List<PlatFunds> fs = platFundsMapper.findPage(map);
		PlatFunds pf = null;
		if (!fs.isEmpty()) {
			pf = fs.get(0);
		}
		return pf;
	}
	
	@Transactional
	@Override
	public Object editAccountFunds(PlatFunds accountFunds) {
		Map<String, Object> map = new HashMap<String, Object>();
		//查询汇率计算预计到账金额
		double rmb = platFXRateService.findExchangeRateByCurrency(accountFunds.getDaozhangCurrency()).getRmb();
		double estimateAmount = accountFunds.getDaozhangAmount()*rmb;
		accountFunds.setEstimateAmount(estimateAmount);
		int result = platFundsMapper.update(accountFunds);
		if (result > 0) {
			map.put("success", Boolean.TRUE);
			map.put("data", null);
			map.put("message", "保存成功");
		} else {
			map.put("success", Boolean.FALSE);
			map.put("data", null);
			map.put("message", "保存失败");
		}
		return map;
	}

}
