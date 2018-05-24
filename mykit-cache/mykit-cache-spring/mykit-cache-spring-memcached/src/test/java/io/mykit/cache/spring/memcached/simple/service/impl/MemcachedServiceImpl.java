package io.mykit.cache.spring.memcached.simple.service.impl;

import org.springframework.stereotype.Service;

import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;

import io.mykit.cache.spring.memcached.simple.service.MemcachedService;
import io.mykit.cache.spring.memcached.utils.LoadFile;

@Service("simpleMemcachedService")
public class MemcachedServiceImpl implements MemcachedService {

	@Override
	@ReadThroughSingleCache(namespace="defaultCache", expiration = 0)
	public String getValueFromDefaultCache(@ParameterValueKeyProvider String key) {
		return LoadFile.getValue(key);
	}

	@Override
	@ReadThroughSingleCache(namespace="intimeCache", expiration = 5)
	public String getValueFromInTimeCache(@ParameterValueKeyProvider String key) {
		return LoadFile.getValue(key);
	}

}
