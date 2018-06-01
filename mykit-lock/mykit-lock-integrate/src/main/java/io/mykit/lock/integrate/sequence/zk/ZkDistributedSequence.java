package io.mykit.lock.integrate.sequence.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.LoggerFactory;

import io.mykit.lock.integrate.lock.zk.ZkReentrantLockCleanerTask;
import io.mykit.lock.integrate.sequence.DistributedSequence;

/**
 * 分布式序列具体实现
 * @author liuyazhuang
 */
public class ZkDistributedSequence implements DistributedSequence {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ZkReentrantLockCleanerTask.class);

    private CuratorFramework client;
     /**
     * Curator RetryPolicy maxRetries
     */
    private int maxRetries=3;
    /**
     * Curator RetryPolicy baseSleepTimeMs
     */
    private final int baseSleepTimeMs=1000;

    public ZkDistributedSequence(String zookeeperAddress){
        try{
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(baseSleepTimeMs, maxRetries);
            client = CuratorFrameworkFactory.newClient(zookeeperAddress, retryPolicy);
            client.start();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }catch (Throwable ex){
            ex.printStackTrace();
            log.error(ex.getMessage(),ex);
        }
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public int getBaseSleepTimeMs() {
        return baseSleepTimeMs;
    }

    public Long sequence(String sequenceName) {
        try {
            int value=client.setData().withVersion(-1).forPath("/"+sequenceName,"".getBytes()).getVersion();
            return new Long(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
