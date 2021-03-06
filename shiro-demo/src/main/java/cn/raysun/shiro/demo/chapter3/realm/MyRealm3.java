package cn.raysun.shiro.demo.chapter3.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm3 extends AuthorizingRealm{
	
	@Override
	public String getName() {
		return "myrealm3";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		//here only support UsernamePasswordToken
		return token instanceof UsernamePasswordToken;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
		String username = (String) token.getPrincipal();
		String password = new String((char[])token.getCredentials());
		
		if(!"xwarrior".equals(username))
			throw new UnknownAccountException();
		if(!"1234".equals(password))
			throw new IncorrectCredentialsException();
		
		return new SimpleAuthenticationInfo("xwarrior@163.com", "1234", getName());
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
