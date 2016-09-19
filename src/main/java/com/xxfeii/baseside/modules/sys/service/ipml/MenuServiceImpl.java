package com.xxfeii.baseside.modules.sys.service.ipml;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xxfeii.baseside.common.service.impl.BaseServiceImpl;
import com.xxfeii.baseside.modules.sys.entity.Menu;
import com.xxfeii.baseside.modules.sys.mapper.MenuMapper;
import com.xxfeii.baseside.modules.sys.service.MenuService;
import com.xxfeii.baseside.modules.sys.utils.TreeUtil;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {

	@Resource
	private MenuMapper menuMapper;

	@Override
	public List<Menu> findMenuByUserId(String userId) {
		List<Menu> ymenus = menuMapper.findMenuByUserId(userId);
		TreeUtil treeUtil = new TreeUtil();
		List<Menu> menus = treeUtil.packageMenu(ymenus);
		return menus;
	}

}
