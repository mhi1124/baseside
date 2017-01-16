package com.xxfeii.baseside.modules.plat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxfeii.baseside.common.service.impl.BaseServiceImpl;
import com.xxfeii.baseside.modules.plat.entity.PlatFXRate;
import com.xxfeii.baseside.modules.plat.mapper.PlatFXRateMapper;
import com.xxfeii.baseside.modules.plat.service.PlatFXRateService;

@Service
public class PlatFXRateServiceImpl extends BaseServiceImpl<PlatFXRate> implements PlatFXRateService {

	@Autowired
	private PlatFXRateMapper exchangeRateMapper;

	@Override
	public List<String> findCurrency() {
		return exchangeRateMapper.findCurrency();
	}

	@Override
	public PlatFXRate findExchangeRateByCurrency(String currency) {
		return exchangeRateMapper.findExchangeRateByCurrency(currency);
	}

}
