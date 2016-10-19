package com.xxfeii.baseside.modules.sys.service.ipml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xxfeii.baseside.common.service.impl.BaseServiceImpl;
import com.xxfeii.baseside.modules.sys.entity.Role;
import com.xxfeii.baseside.modules.sys.exception.SystemException;
import com.xxfeii.baseside.modules.sys.mapper.RoleMapper;
import com.xxfeii.baseside.modules.sys.service.RoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	@Resource
	private RoleMapper roleMapper;
	
	@Override
	public Role findRoleById(String id) throws SystemException{
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("sid", id);
			return roleMapper.findRoleByParam(map);
		} catch (Exception e) {
			throw new SystemException(e.getMessage());
		}
	}

	@Override
	public Role findRoleByName(String roleName) throws SystemException {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("roleName", roleName);
			return roleMapper.findRoleByParam(map);
		} catch (Exception e) {
			throw new SystemException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public boolean deleteRole(List<String> ids) {
		roleMapper.deleteBatchById(ids);
		int rmc = roleMapper.findRoleMenuCount(ids);
		if(rmc>0){
			roleMapper.deleteRoleMenu(ids);
		}
		int ruc = roleMapper.findRoleUserCount(ids);
		if(ruc>0){
			roleMapper.deleteRoleUser(ids);
		}
		return true;
	}

	@Transactional
	@Override
	public boolean addRolePermBatch(String rid, List<String> menuids) throws Exception{
		boolean flag = false;
		try {
			List<String> rids = new ArrayList<>(); 
			rids.add(rid);
			int permCount = roleMapper.findRoleMenuCount(rids);
			boolean delFlag = true;
			if (permCount > 0) {
				int delResult = roleMapper.deleteRoleMenu(rids);
				if (permCount != delResult) {
					delFlag = false;
				}
			}

			if (delFlag) {
				if (menuids.size() > 0) {
					Map<String, Object> parameter = new HashMap<String, Object>();
					parameter.put("rid", rid);
					parameter.put("menuids", menuids);
					int addResult = roleMapper.addRoleMenuBatch(parameter);
					if (addResult == menuids.size()) {
						flag = true;
					}
				} else {
					flag = true;
				}
			}
			return flag;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	

}
