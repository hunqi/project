package com.raysun.shirodemo.dao;

import java.util.Set;

import com.raysun.shirodemo.entity.User;

public interface UserDao {
	
	User createUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(Long userId);
	
	void correlationRoles(Long userId, Long ... roleIds);
	
	void uncorrelationRoles(Long userId, Long ... roleIds);
	
	User findOne(Long userId);
	
	User findByUsername(String username);
	
	Set<String> findRoles(String username);
	
	Set<String> findPermissions(String username);
	
}
