package io.mykit.lock.redis.service;

import io.mykit.lock.redis.annotation.CacheLock;
import io.mykit.lock.redis.annotation.LockedComplexObject;
import io.mykit.lock.redis.annotation.LockedObject;
import io.mykit.lock.redis.entity.Goods;

/**
 * 模拟秒杀系统抢购商品
 * @author liuyazhuang
 *
 */
public interface SeckillInterface {
	@CacheLock(lockedPrefix="TEST_PREFIX")
	public void secKill(String arg1,@LockedObject Long arg2);
	
	@CacheLock(lockedPrefix="object_prefix")
	void secKill(@LockedComplexObject(field = "id" ) Goods goods);
}
