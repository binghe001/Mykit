package io.mykit.cache.spring.memcached.service;

/**
 * 测试Memcached整合Spring注解形式
 * @author liuyazhuang
 *
 */
public interface MemcachedService {
	
	String getValue(String key);
}
