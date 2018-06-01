package io.mykit.lock.integrate.lock;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁接口
 * @author liuyazhuang
 */
public interface DistributedReentrantLock {
	
    /**
     * 尝试获取锁
     * @param timeout 超时时间
     * @param unit 时间单位
     * @return
     * @throws InterruptedException
     */
    boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException;

    /**
     * 释放锁
     */
    void unlock();
}
