package com.raysun.shiro.ssl.service.api;

import java.util.Set;

import com.raysun.shiro.ssl.entity.User;

public interface IUserService {
	
	User createUser(User user);
	
	void changePassword(Long userId, String newPassword);
	
	void correlationRoles(Long userId, Long ... roleIds);
	
	void uncorrelationRoles(Long userId, Long ... roleIds);
	
	User findByUsername(String username);
	
	Set<String> findRoles(String username);
	
	Set<String> findPermissions(String username);
	
}
