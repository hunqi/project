package com.raysun.shiro.rememberme.service.api;

import java.util.Set;

import com.raysun.shiro.rememberme.entity.User;

public interface IUserService {
	
	User createUser(User user);
	
	void changePassword(Long userId, String newPassword);
	
	void correlationRoles(Long userId, Long ... roleIds);
	
	void uncorrelationRoles(Long userId, Long ... roleIds);
	
	User findByUsername(String username);
	
	Set<String> findRoles(String username);
	
	Set<String> findPermissions(String username);
	
}
