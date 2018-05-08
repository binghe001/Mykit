package io.mykit.cache.spring.memcached.sync;

import java.util.Date;

import com.danga.MemCached.MemCachedClient;

/**
 * 线程安全的MemCachedClient
 * @author liuyazhuang
 */
public class ConcurrentMemcachedClient{
	
	private MemCachedClient memCachedClient;
	
	public ConcurrentMemcachedClient() {
		
	}

	public ConcurrentMemcachedClient(MemCachedClient memCachedClient) {
		this.memCachedClient = memCachedClient;
	}

	public void setMemCachedClient(MemCachedClient memCachedClient) {
		this.memCachedClient = memCachedClient;
	}

	public synchronized void setDefaultEncoding(String defaultEncoding) {
		memCachedClient.setDefaultEncoding(defaultEncoding);
	}

	public synchronized boolean keyExists(String key) {
		return memCachedClient.keyExists(key);
	}

	public synchronized boolean delete(String key) {
		if(memCachedClient.keyExists(key)){
			Object obj = memCachedClient.get(key);
			if(obj != null){
				return memCachedClient.delete(key);
			}
		}
		return false;
	}

	public synchronized boolean set(String key, Object value) {
		return memCachedClient.set(key, value);
	}

	public synchronized boolean set(String key, Object value, Integer hashCode) {
		return memCachedClient.set(key, value, hashCode);
	}

	public synchronized boolean set(String key, Object value, Date expiry) {
		return memCachedClient.set(key, value, expiry);
	}

	public synchronized boolean set(String key, Object value, Date expiry, Integer hashCode) {
		return memCachedClient.set(key, value, expiry, hashCode);
	}

	public synchronized boolean add(String key, Object value) {
		return memCachedClient.add(key, value);
	}

	public synchronized boolean add(String key, Object value, Integer hashCode) {
		return memCachedClient.add(key, value, hashCode);
	}

	public synchronized  boolean add(String key, Object value, Date expiry) {
		return memCachedClient.add(key, value, expiry);
	}

	public synchronized boolean add(String key, Object value, Date expiry, Integer hashCode) {
		return memCachedClient.add(key, value, expiry, hashCode);
	}

	public synchronized boolean replace(String key, Object value) {
		return memCachedClient.replace(key, value);
	}

	public synchronized boolean replace(String key, Object value, Integer hashCode) {
		return memCachedClient.replace(key, value, hashCode);
	}

	public synchronized boolean replace(String key, Object value, Date expiry) {
		return memCachedClient.replace(key, value, expiry);
	}

	public synchronized boolean replace(String key, Object value, Date expiry, Integer hashCode) {
		return memCachedClient.replace(key, value, expiry, hashCode);
	}

	public synchronized Object get(String key) {
		return memCachedClient.get(key);
	}

	public synchronized Object get(String key, Integer hashCode) {
		return memCachedClient.get(key, hashCode);
	}

	public synchronized Object get(String key, Integer hashCode, boolean asString) {
		return memCachedClient.get(key, hashCode, asString);
	}

	public synchronized boolean append(String key, Object value, Integer hashCode) {
		return memCachedClient.append(key, value, hashCode);
	}

	public synchronized boolean append(String key, Object value) {
		return memCachedClient.append(key, value);
	}
}
