package io.mykit.cache.spring.redis.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import io.mykit.cache.spring.annotation.CacheDuration;
import io.mykit.cache.spring.redis.service.RedisService;
import io.mykit.cache.spring.redis.utils.LoadFile;

@Service("redisService")  
public class RedisServiceImpl implements RedisService {
	
	@Override
	@Cacheable(value="test11", key="#key")
	@CacheDuration(duration = 10)
	public String getRedidInfo(String key) {
		return LoadFile.getValue(key);
	}

}
