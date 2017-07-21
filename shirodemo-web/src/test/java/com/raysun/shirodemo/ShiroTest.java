package com.raysun.shirodemo;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.raysun.shirodemo.entity.Permission;
import com.raysun.shirodemo.entity.Role;
import com.raysun.shirodemo.entity.User;
import com.raysun.shirodemo.realm.UserRealm;
import com.raysun.shirodemo.service.api.IPermissionService;
import com.raysun.shirodemo.service.api.IRoleService;
import com.raysun.shirodemo.service.api.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-beans.xml", "classpath:spring-shiro.xml"})
public class ShiroTest {
	
	@Autowired
	private IPermissionService permissionService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserRealm userRealm;
	
	protected JdbcTemplate jdbcTemplate;
	
	@Autowired
	private void setDataSource(DataSource ds){
		jdbcTemplate = new JdbcTemplate(ds);
	}
	
	protected String password = "123";
	
	protected Permission p1;
	protected Permission p2;
	protected Permission p3;
	
	protected Role r1;
	protected Role r2;
	protected Role r3;
	
	protected User u1;
	protected User u2;
	protected User u3;
	protected User u4;
	
	@Before
	public void setUp(){
		jdbcTemplate.update("delete from sys_users");
		jdbcTemplate.update("delete from sys_roles");
		jdbcTemplate.update("delete from sys_permissions");
		jdbcTemplate.update("delete from sys_users_roles");
		jdbcTemplate.update("delete from sys_roles_permissions");
		
		p1 = new Permission("user:create", "用户模块新增", Boolean.TRUE);
		p2 = new Permission("user:update", "用户模块修改", Boolean.TRUE);
		p3 = new Permission("menu:create", "菜单模块新增", Boolean.TRUE);
		permissionService.createPermission(p1);
		permissionService.createPermission(p2);
		permissionService.createPermission(p3);
		
		
		
		
	}
}
