package com.cn.raysun.mybatis;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cn.raysun.persist.entity.User;
import com.cn.raysun.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mybatis.xml"})
public class MyBatisTest {
	
	private static final Logger logger = LoggerFactory.getLogger(MyBatisTest.class);
	
//	@Resource
//	@Autowired
	@Inject
	private IUserService userService;
	
	@Test
	public void test1(){
		User user = userService.getUserById(1);
		logger.info("user={}", user);
	}
	
}
