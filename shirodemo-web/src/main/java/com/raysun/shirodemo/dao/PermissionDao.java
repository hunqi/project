package com.raysun.shirodemo.dao;

import com.raysun.shirodemo.entity.Permission;

public interface PermissionDao {
	Permission createPermission(Permission permission);
	void deletePermission(Long permissionId);
}
