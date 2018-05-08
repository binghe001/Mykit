package io.mykit.cache.spring.memcached.factory;

import org.springframework.web.context.WebApplicationContext;

import io.mykit.cache.spring.memcached.sync.ConcurrentMemcachedClient;
import io.mykit.cache.spring.proxy.WebAppUtils;

/**
 * ConcurrentMemcachedClient的工厂类
 * 单例类
 * @author liuyazhuang
 *
 */
public final class ConcurrentMemcachedClientFactory {
	
	private ConcurrentMemcachedClientFactory(){}
	
	private volatile static ConcurrentMemcachedClient instance;
	
	public static ConcurrentMemcachedClient getInstance(){
		if(instance == null){
			synchronized (ConcurrentMemcachedClientFactory.class) {
				if(instance == null){
					WebApplicationContext context = WebAppUtils.getInstance();
					if(context != null){
						instance = (ConcurrentMemcachedClient) context.getBean("concurrentMemcachedClient");
					}
				}
			}
		}
		return instance;
	}
}
