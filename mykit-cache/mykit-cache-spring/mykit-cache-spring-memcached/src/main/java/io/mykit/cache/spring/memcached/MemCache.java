package io.mykit.cache.spring.memcached;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.danga.MemCached.MemCachedClient;

/**
 * Memcached的封装类
 * @author liuyazhuang
 *
 */
public class MemCache {
	private static Logger log = LoggerFactory.getLogger(MemCache.class);

	//当前所用的MemcachedClient为com.danga.MemCached.MemCachedClient
    private static final String MEMCACHED_CLIENT_ALL_NAME_DANGA = "com.danga.MemCached.MemCachedClient";
	private Set<String> keySet = new HashSet<String>();
	private final String name;
	private final int expire;
	private final MemCachedClient memcachedClient;
	private String memcachedClientAllName;
	
	public MemCache(String name, int expire, MemCachedClient memcachedClient) {
		this.name = name;
		this.expire = expire;
		this.memcachedClient = memcachedClient;
		this.memcachedClientAllName = memcachedClient.getClass().getName();
	}

	public Object get(String key) {
		Object value = null;
		try {
			key = this.getKey(key);
			value = memcachedClient.get(key);
		} catch (Exception e) {
			log.warn("获取 Memcached 缓存超时", e);
		}
		return value;
	}

	public void put(String key, Object value) {
		if (value == null)
			return;
		try {
			key = this.getKey(key);
			if(MEMCACHED_CLIENT_ALL_NAME_DANGA.equals(this.memcachedClientAllName)){
				memcachedClient.set(key, value, new Date(System.currentTimeMillis() + expire * 1000));
			}else{
				memcachedClient.set(key, value, expire);
			}
			keySet.add(key);
		}catch (Exception e) {
			log.warn("更新 Memcached 缓存错误", e);
		}
	}

	public void clear() {
		for (String key : keySet) {
			try {
				memcachedClient.delete(this.getKey(key));
			}catch (Exception e) {
				log.warn("删除 Memcached 缓存错误", e);
			}
		}
	}

	public void delete(String key) {
		try {
			key = this.getKey(key);
			memcachedClient.delete(key);
		} catch (Exception e) {
			log.warn("删除 Memcached 缓存被中断", e);
		}
	}

	private String getKey(String key) {
		return name + "_" + key;
	}
}
