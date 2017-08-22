package com.raysun.shiro.rememberme.entity;

import java.io.Serializable;

public class Permission implements Serializable {

	private Long id;
	// 权限标识, 如user:create
	private String permission;
	private String description;
	private Boolean available = Boolean.FALSE;

	public Permission() {
	}

	public Permission(String permission, String description, Boolean available) {
		this.permission = permission;
		this.description = description;
		this.available = available;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Permission role = (Permission) o;

		if (id != null ? !id.equals(role.id) : role.id != null)
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", permission=" + permission
				+ ", description=" + description + ", available=" + available
				+ "]";
	}

}
