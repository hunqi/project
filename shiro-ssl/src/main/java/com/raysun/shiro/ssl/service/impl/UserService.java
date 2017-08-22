package com.raysun.shiro.ssl.service.impl;

import java.util.Set;

import com.raysun.shiro.ssl.dao.UserDao;
import com.raysun.shiro.ssl.entity.User;
import com.raysun.shiro.ssl.service.api.IUserService;

public class UserService implements IUserService {

	private UserDao userDao;

	private PasswordHelper passwordHelper;
	
	public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
	
	public void setPasswordHelper(PasswordHelper passwordHelper) {
        this.passwordHelper = passwordHelper;
    }

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
