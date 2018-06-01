package io.mykit.lock.integrate.lock.zk;

import org.apache.curator.framework.CuratorFramework;
import org.slf4j.LoggerFactory;

import io.mykit.lock.integrate.lock.Callback;
import io.mykit.lock.integrate.lock.DistributedLockTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 基于Redis实现分布式锁模板类
 * @author liuyazhuang
 */
public class ZkDistributedLockTemplate implements DistributedLockTemplate {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ZkDistributedLockTemplate.class);

    private CuratorFramework client;


    public ZkDistributedLockTemplate(CuratorFramework client) {
        this.client = client;
    }



    private boolean tryLock(ZkReentrantLock distributedReentrantLock,Long timeout) throws Exception {
        return distributedReentrantLock.tryLock(timeout, TimeUnit.MILLISECONDS);
    }

    public Object execute(String lockId, int timeout, Callback callback) {
        ZkReentrantLock distributedReentrantLock = null;
        boolean getLock=false;
        try {
            distributedReentrantLock = new ZkReentrantLock(client,lockId);
            if(tryLock(distributedReentrantLock,new Long(timeout))){
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
            if(getLock){
                distributedReentrantLock.unlock();
            }
        }
        return null;
    }
}
