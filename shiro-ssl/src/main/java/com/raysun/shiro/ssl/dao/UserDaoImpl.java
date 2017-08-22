package com.raysun.shiro.ssl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.raysun.shiro.ssl.entity.User;

public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

	public User createUser(final User user) {
		final String sql = "insert into sys_users(username, password, salt, locked) values (?, ?, ?, ?)";
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator(){

			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id"});
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getSalt());
				ps.setBoolean(4,  user.getLocked());
				
				return ps;
			}
		}, keyHolder);
		
		
		user.setId(keyHolder.getKey().longValue());
		return user;
	}

	public void updateUser(User user) {
		String sql = "update sys_users set username=?, password=?, salt=?, locked=? where id=?";
		getJdbcTemplate().update(sql, user.getUsername(), user.getPassword(), user.getSalt(), user.getLocked(), user.getId());
	}

	public void deleteUser(Long userId) {
		String sql = "delete from sys_users where id=?";
		getJdbcTemplate().update(sql, userId);		
	}

	public void correlationRoles(Long userId, Long... roleIds) {
		if(ArrayUtils.isEmpty(roleIds)) return;
		
		String sql = "insert into sys_users_roles(user_id, role_id) values(?, ?)";
		
		for(Long roleId : roleIds){
			if(!exists(userId, roleId)){
				getJdbcTemplate().update(sql, userId, roleId);
			}
		}
	}
	
	public void uncorrelationRoles(Long userId, Long... roleIds) {
		
		if(ArrayUtils.isEmpty(roleIds)) return;
		
		String sql = "delete from sys_users_roles where user_id=? and role_id=?";
		
		for(Long roleId : roleIds){
			if(exists(userId, roleId)){
				getJdbcTemplate().update(sql, userId, roleId);
			}
		}
	}
	

	private boolean exists(Long userId, Long roleId){
		String sql = "select count(1) from sys_users_roles where user_id=? and role_id=?";
		return getJdbcTemplate().queryForObject(sql, Integer.class, userId, roleId) != 0;
	}

	public User findOne(Long userId) {
		String sql = "select id, username, password, salt, locked from sys_users where id=?";
		
		List<User> userList = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(User.class), userId);	
		
		if(CollectionUtils.isEmpty(userList)) return null;
		
		return userList.get(0);
	}

	public User findByUsername(String username) {
		String sql = "select id, username, password, salt, locked from sys_users where username=?";
		
		List<User> userList = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(User.class), username);	
		
		if(CollectionUtils.isEmpty(userList)) return null;
		
		return userList.get(0);
	}

	public Set<String> findRoles(String username) {
		String sql = "select role from sys_users u, sys_roles r, sys_users_roles ur where u.username=? and u.id=ur.user_id and r.id=ur.role_id";
		return new HashSet(getJdbcTemplate().queryForList(sql, String.class, username));
	}

	public Set<String> findPermissions(String username) {
		String sql = "select permission permission from sys_users u, sys_roles r, sys_permissions p, sys_users_roles ur, sys_roles_permissions rp where u.username=? and u.id=ur.user_id and r.id=ur.role_id and r.id=rp.role_id and p.id=rp.permission_id";
		return new HashSet(getJdbcTemplate().queryForList(sql, String.class, username));
	}
	
}
