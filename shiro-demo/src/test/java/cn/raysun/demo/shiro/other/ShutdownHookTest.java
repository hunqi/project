package cn.raysun.demo.shiro.other;

import javax.sql.DataSource;
import com.google.inject.Injector;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ShutdownHookTest {
	
	private static Injector injector;
	
	static {
		// TODO initiate injector
		System.out.println("injector has been initiated.");
	}
	
	public static void main(String[] args) {		
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				DataSource ds = injector.getInstance(DataSource.class);
				((ComboPooledDataSource) ds).close();
				System.out.println("Successfully closed the dataSource.");
			}
		});		
	}
}
