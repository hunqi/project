package cn.raysun.demo.shiro.chapter3;

import org.junit.After;
import org.junit.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.Test;

public class AuthenticationStrategyTest {
	
	@After
	public void tearDown(){
		//unbind the subject from the thread to prevent the next test case 
		//from being infected, this is required
		ThreadContext.unbindSubject();
	}
	
	@Test
	public void testAllSuccessfulStrategyWithSuccess(){
		login("classpath:shiro-authenticator-all-success.ini");
		Subject subject = SecurityUtils.getSubject();
		
		//get a list of principals, which contains the information of realm validated successfully
		PrincipalCollection principals = subject.getPrincipals();
		Assert.assertEquals(2, principals.asList().size());
	}
	
	@Test(expected=UnknownAccountException.class)
	public void testAllSuccessfulStrategyWithFailure(){
		login("classpath:shiro-authenticator-all-fail.ini");
	}
	
	@Test
	public void testAtLeastOneSuccessfulStrategy(){
		
		login("classpath:shiro-authenticator-atLeastOne-success.ini");
		Subject subject = SecurityUtils.getSubject();
		
		//get a list of principals, which contains the information of realm validated successfully
		PrincipalCollection principals = subject.getPrincipals();
		Assert.assertEquals(2, principals.asList().size());
		
	}
	
	@Test
	public void testFirstSuccessfulStrategy(){
		login("classpath:shiro-authenticator-first-success.ini");
		Subject subject = SecurityUtils.getSubject();
		
		//get a list of principals, which contains the information of realm validated successfully
		PrincipalCollection principals = subject.getPrincipals();
		Assert.assertEquals(1, principals.asList().size());
	}
	
	private void login(String configFile){
		Factory<org.apache.shiro.mgt.SecurityManager> factory = 
				new IniSecurityManagerFactory(configFile);
		
		org.apache.shiro.mgt.SecurityManager sm = factory.getInstance();
		SecurityUtils.setSecurityManager(sm);
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("xwarrior", "1234");
		
		subject.login(token);
	}
	
}
