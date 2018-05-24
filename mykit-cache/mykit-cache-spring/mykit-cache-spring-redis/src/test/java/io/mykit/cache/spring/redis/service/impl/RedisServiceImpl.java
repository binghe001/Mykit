package io.mykit.cache.spring.redis.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import io.mykit.cache.spring.annotation.CacheDuration;
import io.mykit.cache.spring.redis.service.RedisService;
import io.mykit.cache.spring.redis.utils.LoadFile;

@Service("redisService")  
public class RedisServiceImpl implements RedisService {
	
	@Override
	@Cacheable("getRedidInfo")  
	@CacheDuration(duration = 100)
	public String getRedidInfo(String key) {
		return LoadFile.getValue(key);
	}

}
