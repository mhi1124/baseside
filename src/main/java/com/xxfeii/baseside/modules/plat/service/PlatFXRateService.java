package com.xxfeii.baseside.modules.plat.service;

import java.util.List;

import com.xxfeii.baseside.common.service.BaseService;
import com.xxfeii.baseside.modules.plat.entity.PlatFXRate;

public interface PlatFXRateService extends BaseService<PlatFXRate>{

	/**
	 * 查询所有币种
	 * @return
	 */
	public List<String> findCurrency();
	
	/**
	 * 根据币种查询
	 * @param currency
	 * @return
	 */
	public PlatFXRate findExchangeRateByCurrency(String currency);
}
