package cn.raysun.demo.shiro.chapter3;


import java.util.Arrays;

import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Assert;
import org.junit.Test;

import cn.raysun.demo.shiro.BaseTest;

public class RoleTest extends BaseTest {
	
	@Test
	public void testHasRole(){
		
		login("classpath:shiro-role.ini", "warrior", "1234");
		
		Assert.assertTrue(subject().hasRole("role1"));
		Assert.assertTrue(subject().hasAllRoles(Arrays.asList("role1", "role2")));
		boolean[] result = subject().hasRoles(Arrays.asList("role1", "role2", "role3")); 
		Assert.assertEquals(true, result[0]);
		Assert.assertEquals(true, result[1]);
		//Assert.assertEquals(true, result[2]);
	}
	
	@Test(expected=UnauthorizedException.class)
	public void testCheckRole(){
		login("classpath:shiro-role.ini", "warrior", "1234");
		//assert having role : role1 
		subject().checkRole("role1");
		//assert having roles : role1, role3, then throw exception
		subject().checkRoles("role1", "role3");
	}
	
}
