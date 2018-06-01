package io.mykit.lock.integrate.lock.redis;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

import io.mykit.lock.integrate.lock.Callback;
import redis.clients.jedis.JedisPool;

/**
 * 测试基于Redis实现的分布式锁模板类
 * @author liuyazhuang
 */
public class RedisReentrantLockTemplateTest {

    @Test
    public void testTry() throws InterruptedException {
        JedisPool jp=new JedisPool("127.0.0.1",6379);
        final RedisDistributedLockTemplate template=new RedisDistributedLockTemplate(jp);

        int size=100;
        final CountDownLatch startCountDownLatch = new CountDownLatch(1);
        final CountDownLatch endDownLatch=new CountDownLatch(size);
        for (int i =0;i<size;i++){
            new Thread() {
                public void run() {
                    try {
                        startCountDownLatch.await();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    final int sleepTime=ThreadLocalRandom.current().nextInt(5)*1000;
                    template.execute("test",5000, new Callback() {
                        public Object onGetLock() throws InterruptedException {
                            System.out.println(Thread.currentThread().getName() + ":getLock");
                            Thread.currentThread().sleep(sleepTime);
                            System.out.println(Thread.currentThread().getName() + ":sleeped:"+sleepTime);
                            endDownLatch.countDown();
                            return null;
                        }
                        public Object onTimeout() throws InterruptedException {
                            System.out.println(Thread.currentThread().getName() + ":timeout");
                            endDownLatch.countDown();
                            return null;
                        }
                    });
                }
            }.start();
        }
        startCountDownLatch.countDown();
        endDownLatch.await();
    }
}
