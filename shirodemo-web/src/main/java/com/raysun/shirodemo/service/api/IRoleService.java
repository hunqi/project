package com.raysun.shirodemo.service.api;

import com.raysun.shirodemo.entity.Role;

public interface IRoleService {

	Role createRole(Role role);

	void correlationPermissions(Long roleId, Long... permissionIds);

	void uncorrelationPermissions(Long roleId, Long... permissionIds);

}
