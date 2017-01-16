package com.xxfeii.baseside.modules.plat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxfeii.baseside.common.service.impl.BaseServiceImpl;
import com.xxfeii.baseside.modules.plat.entity.PlatAccount;
import com.xxfeii.baseside.modules.plat.mapper.PlatAccountMapper;
import com.xxfeii.baseside.modules.plat.service.PlatAccountService;

@Service
public class PlatAccountServiceImpl extends BaseServiceImpl<PlatAccount> implements PlatAccountService {

	@Autowired
	private PlatAccountMapper platAccountMapper;
	
	@Override
	public List<String> getPlatNos() {
		return platAccountMapper.getPlatNos();
	}

}
