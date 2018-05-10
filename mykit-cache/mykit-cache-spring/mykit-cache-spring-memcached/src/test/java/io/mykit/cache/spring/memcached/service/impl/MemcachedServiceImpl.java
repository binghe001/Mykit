package io.mykit.cache.spring.memcached.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import io.mykit.cache.spring.memcached.service.MemcachedService;
import io.mykit.cache.spring.memcached.utils.LoadFile;

@Service("memcachedService")
public class MemcachedServiceImpl implements MemcachedService {

	@Override
	@Cacheable("getValue")  
	public String getValue(String key) {
		return LoadFile.getValue(key);
	}

}
