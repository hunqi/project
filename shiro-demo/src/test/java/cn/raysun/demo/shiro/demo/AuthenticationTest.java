package cn.raysun.demo.shiro.demo;

import org.junit.After;
import org.junit.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.Test;

public class AuthenticationTest {
	
		@After
		public void tearDown(){
			//unbind the subject from the thread to prevent the next test case 
			//from being infected, this is required
			ThreadContext.unbindSubject();
		}
	
		/**
		 * basic realm test
		 */
		@Test
		public void test1(){
			
			Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
			org.apache.shiro.mgt.SecurityManager sm = factory.getInstance();
			SecurityUtils.setSecurityManager(sm);
			
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken("xwarrior", "1234");
			
			try {
				subject.login(token);
			} catch (AuthenticationException e) {
				System.out.println(String.format("%s didn't logged in.", "xwarrior"));
			}
			//assert the user xwarrior has logged on
			Assert.assertEquals(true, subject.isAuthenticated());
			
			//logout
			subject.logout();
		}
		
		/**
		 * test customized realm
		 */
		@Test
		public void test2(){
			
			Factory<org.apache.shiro.mgt.SecurityManager> factory = 
					new IniSecurityManagerFactory("classpath:shiro-realm.ini");
			org.apache.shiro.mgt.SecurityManager sm = factory.getInstance();
			SecurityUtils.setSecurityManager(sm);
			
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken("xwarrior", "1234");
			
			try {
				subject.login(token);
			} catch (AuthenticationException e) {
				e.printStackTrace();
			}
			
			Assert.assertEquals(true, subject.isAuthenticated());
			
			subject.logout();			
		}
		
		/**
		 * test multiple realms
		 */
		@Test
		public void test3(){
			
			Factory<org.apache.shiro.mgt.SecurityManager> factory = 
					new IniSecurityManagerFactory("classpath:shiro-multi-realm.ini");
			
			org.apache.shiro.mgt.SecurityManager sm = factory.getInstance();
			SecurityUtils.setSecurityManager(sm);
			
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken("hunqi", "1212");
			
			try {
				subject.login(token);
			} catch (AuthenticationException e) {
				e.printStackTrace();
			}
			
			Assert.assertEquals(true, subject.isAuthenticated());
			subject.logout();
		}
		
		/**
		 * test jdbc realm
		 */
		@Test
		public void test4(){
			
			Factory<org.apache.shiro.mgt.SecurityManager> factory = 
					new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
			org.apache.shiro.mgt.SecurityManager sm = factory.getInstance();
			SecurityUtils.setSecurityManager(sm);
			
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken("xwarrior", "123456");
			
			subject.login(token);
			
			Assert.assertEquals(true, subject.isAuthenticated());
			subject.logout();
		}
	
}
