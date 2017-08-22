package com.raysun.shiro.rememberme.service.api;

import com.raysun.shiro.rememberme.entity.Role;

public interface IRoleService {

	Role createRole(Role role);

	void correlationPermissions(Long roleId, Long... permissionIds);

	void uncorrelationPermissions(Long roleId, Long... permissionIds);

}
