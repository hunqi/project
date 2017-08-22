package com.raysun.shiro.ssl.service.api;

import com.raysun.shiro.ssl.entity.Permission;

public interface IPermissionService {
	Permission createPermission(Permission permission);

	void deletePermission(Long permissionId);
}
