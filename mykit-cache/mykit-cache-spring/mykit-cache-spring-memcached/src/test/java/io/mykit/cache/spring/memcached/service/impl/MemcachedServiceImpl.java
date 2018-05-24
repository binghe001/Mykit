package io.mykit.cache.spring.memcached.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import io.mykit.cache.spring.memcached.service.MemcachedService;
import io.mykit.cache.spring.memcached.utils.LoadFile;

@Service("memcachedService")
public class MemcachedServiceImpl implements MemcachedService {

	@Override
	@Cacheable(value = "defaultCache")  
//	@CacheDuration(duration = 500)
	public String getValueFromDefaultCache(String key) {
		return LoadFile.getValue(key);
	}

	@Override
	@Cacheable(value = "inTimeCache")  
//	@CacheDuration(duration = 10)
	public String getValueFromInTimeCache(String key) {
		return LoadFile.getValue(key);
	}

}
