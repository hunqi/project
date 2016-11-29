package cn.raysun.demo.shiro.chapter4;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

import cn.raysun.demo.shiro.BaseTest;

public class ConfigurationCreateTest extends BaseTest {

	@Test
	public void test() {
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro-config.ini");

		org.apache.shiro.mgt.SecurityManager securityManager = factory
				.getInstance();

		// 将SecurityManager设置到SecurityUtils 方便全局使用
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("xwarrior", "1234");
		subject.login(token);

		Assert.assertTrue(subject.isAuthenticated());

	}

}
