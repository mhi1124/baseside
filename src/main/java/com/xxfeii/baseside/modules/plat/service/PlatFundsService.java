package com.xxfeii.baseside.modules.plat.service;

import com.xxfeii.baseside.common.service.BaseService;
import com.xxfeii.baseside.modules.plat.entity.PlatFunds;

public interface PlatFundsService extends BaseService<PlatFunds> {

	public PlatFunds findFundsById(Integer id);

	public Object editAccountFunds(PlatFunds accountFunds);

	
}
