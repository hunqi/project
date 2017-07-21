package com.raysun.shirodemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raysun.shirodemo.dao.RoleDao;
import com.raysun.shirodemo.entity.Role;
import com.raysun.shirodemo.service.api.IRoleService;

@Service
public class RoleService implements IRoleService {

	@Autowired
	private RoleDao roleDao;
	
	public Role createRole(Role role) {
		return roleDao.createRole(role);
	}

	public void correlationPermissions(Long roleId, Long... permissionIds) {
		roleDao.correlationPermissions(roleId, permissionIds);
	}

	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		roleDao.uncorrelationPermissions(roleId, permissionIds);
	}

}
