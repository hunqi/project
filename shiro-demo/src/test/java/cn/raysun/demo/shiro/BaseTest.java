package cn.raysun.demo.shiro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Before;

public class BaseTest {
	
	@Before
	public void setUp(){
		try {
			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");
			
			String sql = "drop table if exists users;"
				+ "create table users (id int primary key, "
				+ "username varchar(100), "
				+ "password varchar(100), "
				+ "password_salt varchar(100));"
				+ "insert into users values (1, 'hunqi', '1234', null);"
				+ "insert into users values (2, 'xwarrior', '1234', null);";

			Statement stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@After
	public void tearDown(){
		//unbind subject from the thread, otherwise the next test will be infected
		ThreadContext.unbindSubject();
	}
	
	protected void login(String configFile, String username, String password){
		
		Factory<org.apache.shiro.mgt.SecurityManager> factory = 
				new IniSecurityManagerFactory(configFile);
		
		//set SecurityManager
		org.apache.shiro.mgt.SecurityManager sm = factory.getInstance();
		SecurityUtils.setSecurityManager(sm);
		
		//get subject
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		
		subject.login(token);
	}
	
	protected Subject subject(){
		return SecurityUtils.getSubject();
	}
	
}
