package io.mykit.cache.spring.memcached.simple.service;

/**
 * 测试Memcached整合Spring注解形式
 * @author liuyazhuang
 *
 */
public interface MemcachedService {
	
	String getValueFromDefaultCache(String key);
	String getValueFromInTimeCache(String key);
}
