package io.mykit.lock.integrate.lock.redis;

import java.util.concurrent.TimeUnit;

import io.mykit.lock.integrate.lock.Callback;
import redis.clients.jedis.JedisPool;

/**
 * 测试
 * @author liuyazhuang
 */
public class SimpleTest {

    public static void main(String[] args) throws Exception {
        JedisPool jedisPool=new JedisPool("127.0.0.1",6379);//实际应用时可通过spring注入
        RedisReentrantLock lock=new RedisReentrantLock(jedisPool,"订单流水号");
        try {
            if (lock.tryLock(5000L, TimeUnit.MILLISECONDS)) {
                //TODO 获得锁后要做的事
            }else{
                //TODO 获得锁超时后要做的事
            }
        }finally {
            lock.unlock();
        }
    }

    public static void test1(){
        JedisPool jedisPool=new JedisPool("127.0.0.1",6379);//实际应用时可通过spring注入
        final RedisDistributedLockTemplate template=new RedisDistributedLockTemplate(jedisPool);//本类多线程安全,可通过spring注入
        template.execute("订单流水号", 5000, new Callback() {
            @Override
            public Object onGetLock() throws InterruptedException {
                //TODO 获得锁后要做的事
                return null;
            }

            @Override
            public Object onTimeout() throws InterruptedException {
                //TODO 获得锁超时后要做的事
                return null;
            }
        });
    }
}
