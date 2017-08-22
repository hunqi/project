package com.raysun.shiro.ssl.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.raysun.shiro.ssl.entity.User;
import com.raysun.shiro.ssl.service.impl.UserService;

public class UserRealm extends AuthorizingRealm {

	private UserService userService;
	
	public void setUserService(UserService userService) {
        this.userService = userService;
    }
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		
		SimpleAuthorizationInfo authorInfo = new SimpleAuthorizationInfo();
		authorInfo.setRoles(userService.findRoles(username));
		authorInfo.setStringPermissions(userService.findPermissions(username));
		
		return authorInfo;
	}
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		
		User user = userService.findByUsername(username);
				
		if(user == null){
			throw new UnknownAccountException();
		}		
		
		if(Boolean.TRUE.equals(user.getLocked())){
			throw new LockedAccountException();
		}
		
		SimpleAuthenticationInfo authenInfo = new SimpleAuthenticationInfo(
				user.getUsername(),
				user.getPassword(),
				ByteSource.Util.bytes(user.getSalt()), 
				getName()	//realm name
				);
		
		return authenInfo;
	}
	
	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}
	
	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}
	
	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}
	
	public void clearAllCachedAuthorizationInfo(){
		getAuthorizationCache().clear();
	}
	
	public void clearAllCachedAuthenticationInfo(){
		getAuthenticationCache().clear();
		
	}
	
	public void clearAllCache(){
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}
	
}
