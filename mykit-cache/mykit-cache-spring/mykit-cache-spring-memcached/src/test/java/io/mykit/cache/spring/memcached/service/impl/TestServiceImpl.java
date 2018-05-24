package io.mykit.cache.spring.memcached.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import io.mykit.cache.spring.memcached.service.TestService;
import io.mykit.cache.spring.memcached.utils.LoadFile;

@Service("testService")
public class TestServiceImpl implements TestService{
	@Override
	@Cacheable(value = "defaultCache")  
//	@CacheDuration(duration = 500)
	public String getValueFromDefaultCache(String key) {
		return LoadFile.getValue(key);
	}

}
