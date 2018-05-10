package io.mykit.cache.spring.memcached.utils;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 加载文件
 * @author liuyazhuang
 *
 */
public class LoadFile {
	
	private static final Logger logger = LoggerFactory.getLogger(LoadFile.class);
	
	private static Properties properties = null;
	static{
		try {
			InputStream in = LoadFile.class.getResourceAsStream("/test_memcached.properties");
			properties = new Properties();
			properties.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getValue(String key){
		logger.info("从文件获取数据，传入的key是：" + key);
		return properties.getProperty(key, "");
	}
}
