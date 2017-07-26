package com.raysun.shirodemo.service.impl;

import com.raysun.shirodemo.dao.PermissionDao;
import com.raysun.shirodemo.entity.Permission;
import com.raysun.shirodemo.service.api.IPermissionService;

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
