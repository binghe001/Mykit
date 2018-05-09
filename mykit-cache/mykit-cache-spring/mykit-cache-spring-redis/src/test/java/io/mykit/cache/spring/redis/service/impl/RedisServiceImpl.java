package io.mykit.cache.spring.redis.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import io.mykit.cache.spring.redis.service.RedisService;
import io.mykit.cache.spring.redis.utils.LoadFile;

@Service("redisService")  
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)  
public class RedisServiceImpl implements RedisService {
	
	@Override
	@Cacheable("getRedidInfo")  
	public String getRedidInfo(String key) {
		return LoadFile.getValue(key);
	}

}
