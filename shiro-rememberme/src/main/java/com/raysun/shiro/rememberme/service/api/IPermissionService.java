package com.raysun.shiro.rememberme.service.api;

import com.raysun.shiro.rememberme.entity.Permission;

public interface IPermissionService {
	Permission createPermission(Permission permission);

	void deletePermission(Long permissionId);
}
