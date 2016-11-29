package cn.raysun.demo.shiro.chapter3;


import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Assert;
import org.junit.Test;

import cn.raysun.demo.shiro.BaseTest;

public class PermissionTest extends BaseTest{

	@Test
	public void testIsPermitted(){
		
		login("classpath:shiro-permission.ini", "warrior", "1234");
		
		//assert having permission : user:create
		Assert.assertTrue(subject().isPermitted("user:create"));
		Assert.assertTrue(subject().isPermittedAll("user:update", "user:delete", "user:create"));
		Assert.assertFalse(subject().isPermitted("user:view"));
	}
	
	@Test(expected=UnauthorizedException.class)
	public void testCheckPermission(){
		login("classpath:shiro-permission.ini", "warrior", "1234");
		subject().checkPermission("user:create");
		subject().checkPermissions("user:update", "user:delete");
		subject().checkPermission("user:view");
	}
	
}
