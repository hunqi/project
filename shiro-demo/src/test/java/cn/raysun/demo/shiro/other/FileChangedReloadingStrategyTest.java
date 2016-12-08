package cn.raysun.demo.shiro.other;

import java.io.File;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

/**
 * {@code FileChangedReloadingStrategy}的测试类
 * @author ray.sun
 * 
 */
public class FileChangedReloadingStrategyTest {
	
	//加载文件变更的频率
	private static final long RELOAD_PERIOD = 10L;
	private static PropertiesConfiguration config;
	
	public static void main(String[] args) {
		
		//获得当前类运行的classpath路径
		String currentClassPath = ClassLoader.getSystemResource("").getPath();
		initialize(currentClassPath + "reloadstrategy-test.properties");
		
		while(true){
			try {
				System.out.println("user=" + config.getString("user.loginName"));
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	//初始化配置文件和文件加载策略
	private static void initialize(String filePath){
		try {
			//strategy
			FileChangedReloadingStrategy fileChangedReloadingStrategy = new FileChangedReloadingStrategy();
			//设置扫描文件的最小时间间隔
			fileChangedReloadingStrategy.setRefreshDelay(RELOAD_PERIOD);
			
			//set reloading strategy
			config = new PropertiesConfiguration(new File(filePath));
			config.setReloadingStrategy(fileChangedReloadingStrategy);
			
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}		
	}
	
}
