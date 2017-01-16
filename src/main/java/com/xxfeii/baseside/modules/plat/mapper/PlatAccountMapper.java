package com.xxfeii.baseside.modules.plat.mapper;

import java.util.List;

import com.xxfeii.baseside.common.mapper.BaseMapper;
import com.xxfeii.baseside.modules.plat.entity.PlatAccount;

/**
 * 账号对卡号 dao层接口类
 * @author Administrator
 *
 */
public interface PlatAccountMapper extends BaseMapper<PlatAccount>{

	/**
	 * 查询平台
	 * @return
	 */
	public List<String> getPlatNos();
}
