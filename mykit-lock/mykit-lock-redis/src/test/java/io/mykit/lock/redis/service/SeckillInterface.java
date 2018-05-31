package io.mykit.lock.redis.service;

import io.mykit.lock.redis.annotation.CacheLock;
import io.mykit.lock.redis.annotation.LockedObject;

/**
 * 模拟秒杀系统抢购商品
 * @author liuyazhuang
 *
 */
public interface SeckillInterface {
	@CacheLock(lockedPrefix="TEST_PREFIX")
	public void secKill(String arg1,@LockedObject Long arg2);
}
