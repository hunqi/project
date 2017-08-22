package com.raysun.shiro.rememberme.service.impl;

import com.raysun.shiro.rememberme.dao.PermissionDao;
import com.raysun.shiro.rememberme.entity.Permission;
import com.raysun.shiro.rememberme.service.api.IPermissionService;

public class PermissionService implements IPermissionService {

	private PermissionDao permissionDao;
	
	public void setPermissionDao(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }
	
	public Permission createPermission(Permission permission) {
		return permissionDao.createPermission(permission);
	}

	public void deletePermission(Long permissionId) {
		permissionDao.deletePermission(permissionId);
	}

}
