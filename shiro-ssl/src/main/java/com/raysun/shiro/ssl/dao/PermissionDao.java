package com.raysun.shiro.ssl.dao;

import com.raysun.shiro.ssl.entity.Permission;

public interface PermissionDao {
	Permission createPermission(Permission permission);
	void deletePermission(Long permissionId);
}
