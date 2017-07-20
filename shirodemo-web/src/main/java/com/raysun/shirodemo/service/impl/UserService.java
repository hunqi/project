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
		// TODO Auto-generated method stub
		return null;
	}

	public void changePassword(Long userId, String newPassword) {
		// TODO Auto-generated method stub
		
	}

	public void correlationRoles(Long userId, Long... roleIds) {
		// TODO Auto-generated method stub
		
	}

	public void uncorrelationRoles(Long userId, Long... roleIds) {
		// TODO Auto-generated method stub
		
	}

	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<String> findRoles(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<String> findPermissions(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
