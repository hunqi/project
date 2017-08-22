package com.raysun.shiro.rememberme.dao;

import com.raysun.shiro.rememberme.entity.Permission;

public interface PermissionDao {
	Permission createPermission(Permission permission);
	void deletePermission(Long permissionId);
}
