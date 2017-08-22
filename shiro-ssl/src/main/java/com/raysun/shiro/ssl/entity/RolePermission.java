package com.raysun.shiro.ssl.entity;

import java.io.Serializable;

public class RolePermission implements Serializable {
	
	private Long roleId;
	private Long permissionId;
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	
	@Override
	public int hashCode() {
		int result = roleId != null ? roleId.hashCode() : 0;
		result = 31*result + (permissionId != null ? permissionId.hashCode() : 0);
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		
		RolePermission rolePermission = (RolePermission)o;
		
		if(permissionId != null ? !permissionId.equals(rolePermission.permissionId) : rolePermission.permissionId != null) return false;
		if(roleId != null ? !roleId.equals(rolePermission.roleId) : rolePermission.roleId != null) return false;
		
		return true;
	}
	@Override
	public String toString() {
		return "RolePermission [roleId=" + roleId + ", permissionId="
				+ permissionId + "]";
	}
	
}
