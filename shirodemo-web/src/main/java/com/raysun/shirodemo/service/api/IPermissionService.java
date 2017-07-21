package com.raysun.shirodemo.service.api;

import com.raysun.shirodemo.entity.Permission;

public interface IPermissionService {
	Permission createPermission(Permission permission);

	void deletePermission(Long permissionId);
}
