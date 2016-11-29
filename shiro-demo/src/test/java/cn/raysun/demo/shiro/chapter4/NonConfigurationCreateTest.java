package cn.raysun.demo.shiro.chapter4;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import cn.raysun.demo.shiro.BaseTest;

import com.alibaba.druid.pool.DruidDataSource;

public class NonConfigurationCreateTest extends BaseTest {

	@Test
	public void test() throws Exception {
		DefaultSecurityManager securityManager = new DefaultSecurityManager();

		// set authenticator
		ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
		authenticator
				.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
		securityManager.setAuthenticator(authenticator);

		// set authorizer
		ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
		authorizer.setPermissionResolver(new WildcardPermissionResolver());
		securityManager.setAuthorizer(authorizer);

		// set H2 data source
		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName("org.h2.Driver");
		ds.setUrl("jdbc:h2:mem:test");

		// set JDBC Realm
		JdbcRealm jdbcRealm = new JdbcRealm();
		jdbcRealm.setDataSource(ds);
		jdbcRealm.setPermissionsLookupEnabled(true);
		securityManager.setRealms(Arrays.asList((Realm) jdbcRealm));

		SecurityUtils.setSecurityManager(securityManager);

		// get subject
		Subject subject = SecurityUtils.getSubject();

		// set token
		UsernamePasswordToken token = new UsernamePasswordToken("hunqi", "1234");
		subject.login(token);

		Assert.assertTrue(subject.isAuthenticated());
	}

}
