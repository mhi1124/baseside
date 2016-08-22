package com.xxfeii.baseside.modules.sys.service.ipml;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xxfeii.baseside.common.service.impl.BaseServiceImpl;
import com.xxfeii.baseside.modules.sys.entity.Menu;
import com.xxfeii.baseside.modules.sys.mapper.MenuMapper;
import com.xxfeii.baseside.modules.sys.service.MenuService;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {

	@Resource
	private MenuMapper menuMapper;

}
