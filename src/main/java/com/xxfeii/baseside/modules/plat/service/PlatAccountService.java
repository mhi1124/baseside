package com.xxfeii.baseside.modules.plat.service;

import java.util.List;

import com.xxfeii.baseside.common.service.BaseService;
import com.xxfeii.baseside.modules.plat.entity.PlatAccount;

/**
 * 账号对卡号
 * @author Administrator
 *
 */
public interface PlatAccountService extends BaseService<PlatAccount> {

	/**
	 * 获取平台
	 * @return
	 */
	public List<String> getPlatNos();
}
