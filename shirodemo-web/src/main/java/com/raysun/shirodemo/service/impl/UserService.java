package com.raysun.shirodemo.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raysun.shirodemo.dao.UserDao;
import com.raysun.shirodemo.entity.User;
import com.raysun.shirodemo.service.api.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordHelper passwordHelper;
	
	public User createUser(User user) {
		passwordHelper.encryptPassword(user);
		return userDao.createUser(user);
	}

	public void changePassword(Long userId, String newPassword) {
		User user = userDao.findOne(userId);
		user.setPassword(newPassword);		
		passwordHelper.encryptPassword(user);
		userDao.updateUser(user);
	}

	public void correlationRoles(Long userId, Long... roleIds) {
		userDao.correlationRoles(userId, roleIds);		
	}

	public void uncorrelationRoles(Long userId, Long... roleIds) {
		userDao.uncorrelationRoles(userId, roleIds);
	}

	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	public Set<String> findRoles(String username) {
		return userDao.findRoles(username);
	}

	public Set<String> findPermissions(String username) {
		return userDao.findPermissions(username);
	}
	
}
