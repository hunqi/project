package com.raysun.shiro.rememberme.service.impl;

import com.raysun.shiro.rememberme.dao.RoleDao;
import com.raysun.shiro.rememberme.entity.Role;
import com.raysun.shiro.rememberme.service.api.IRoleService;

public class RoleService implements IRoleService {

	private RoleDao roleDao;

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

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
