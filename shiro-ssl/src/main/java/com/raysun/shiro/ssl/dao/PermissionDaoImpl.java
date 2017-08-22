package com.raysun.shiro.ssl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.raysun.shiro.ssl.entity.Permission;

public class PermissionDaoImpl extends JdbcDaoSupport implements PermissionDao {

	public Permission createPermission(final Permission permission) {
		final String sql = "insert into sys_permissions(permission, description, available) values(?, ?, ?)";
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator(){
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				
				PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id"});
				ps.setString(1,  permission.getPermission());
				ps.setString(2, permission.getDescription());
				ps.setBoolean(3, permission.getAvailable());
				
				return ps;
			}
		}, keyHolder);
		permission.setId(keyHolder.getKey().longValue());
		
		return permission;
	}

	public void deletePermission(Long permissionId) {
		//delete related records first
		String sql = "delete from sys_roles_permission where permission_id=?";
		getJdbcTemplate().update(sql, permissionId);
		
		sql = "delete from sys_permissions where id=?";
		getJdbcTemplate().update(sql, permissionId);		
	}

}
