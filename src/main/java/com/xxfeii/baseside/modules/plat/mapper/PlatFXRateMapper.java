package com.xxfeii.baseside.modules.plat.mapper;

import java.util.List;

import com.xxfeii.baseside.common.mapper.BaseMapper;
import com.xxfeii.baseside.modules.plat.entity.PlatFXRate;

/**
 * 汇率mapper
 * @author Administrator
 *
 */
public interface PlatFXRateMapper extends BaseMapper<PlatFXRate>{
	/**
	 * 查询币种
	 * @return
	 */
	public List<String> findCurrency();
	
	public PlatFXRate findExchangeRateByCurrency(String currency);
}
