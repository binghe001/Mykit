package io.mykit.mq.spring.amq.factory;

import java.io.InputStream;
import java.util.Properties;

/**
 * AMQ常量
 * @author liuyazhuang
 *
 */
public class AMQFactory extends BaseFactory{
	

	private volatile static Properties instance;
	
	private AMQFactory(){}
	
	
	static{
		try {
			instance = new Properties();
			InputStream in = AMQFactory.class.getClassLoader().getResourceAsStream("properties/activemq.properties");
			instance.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getAMQInfo(String key) {
		if(instance == null) return null;
		return instance.getProperty(key, "");
	}

}
