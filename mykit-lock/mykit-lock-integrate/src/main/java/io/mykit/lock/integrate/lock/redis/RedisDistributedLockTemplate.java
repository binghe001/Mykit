package io.mykit.lock.integrate.lock.redis;

import java.util.concurrent.TimeUnit;

import org.slf4j.LoggerFactory;

import io.mykit.lock.integrate.lock.Callback;
import io.mykit.lock.integrate.lock.DistributedLockTemplate;
import redis.clients.jedis.JedisPool;

/**
 * 基于Redis实现分布式锁模板类
 * @author liuyazhuang
 *
 */
public class RedisDistributedLockTemplate implements DistributedLockTemplate {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(RedisDistributedLockTemplate.class);

    private JedisPool jedisPool;


    public RedisDistributedLockTemplate(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
    
    @Override
    public Object execute(String lockId, int timeout, Callback callback) {
        RedisReentrantLock distributedReentrantLock = null;
        boolean getLock=false;
        try {
            distributedReentrantLock = new RedisReentrantLock(jedisPool,lockId);
            if(distributedReentrantLock.tryLock(new Long(timeout), TimeUnit.MILLISECONDS)){
                getLock=true;
                return callback.onGetLock();
            }else{
                return callback.onTimeout();
            }
        }catch(InterruptedException ex){
            log.error(ex.getMessage(), ex);
            Thread.currentThread().interrupt();
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }finally {
            if(getLock) {
                distributedReentrantLock.unlock();
            }
        }
        return null;
    }
}
