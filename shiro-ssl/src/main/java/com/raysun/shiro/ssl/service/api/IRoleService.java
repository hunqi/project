package com.raysun.shiro.ssl.service.api;

import com.raysun.shiro.ssl.entity.Role;

public interface IRoleService {

	Role createRole(Role role);

	void correlationPermissions(Long roleId, Long... permissionIds);

	void uncorrelationPermissions(Long roleId, Long... permissionIds);

}
