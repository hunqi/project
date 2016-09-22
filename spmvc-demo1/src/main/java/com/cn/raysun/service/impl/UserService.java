package com.cn.raysun.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.raysun.persist.entity.User;
import com.cn.raysun.persist.mapper.UserMapper;
import com.cn.raysun.service.IUserService;

@Service("userService")
public class UserService implements IUserService{

	@Resource
	private UserMapper userMapper;
	
	public User getUserById(int userId) {
		return userMapper.selectByPrimaryKey(userId);
	}	
	
}
