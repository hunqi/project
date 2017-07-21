package com.raysun.shirodemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raysun.shirodemo.dao.PermissionDao;
import com.raysun.shirodemo.entity.Permission;
import com.raysun.shirodemo.service.api.IPermissionService;

@Service
public class PermissionService implements IPermissionService {

	@Autowired
	private PermissionDao permissionDao;
	
	public Permission createPermission(Permission permission) {
		return permissionDao.createPermission(permission);
	}

	public void deletePermission(Long permissionId) {
		permissionDao.deletePermission(permissionId);
	}

}
