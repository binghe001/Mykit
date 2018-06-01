package io.mykit.lock.integrate.limit.redis;

/**
 * 限制规则
 * @author liuyazhuang
 */
public class LimitRule {

    /**
     * 单位时间
     */
    private int seconds;

    /**
     * 单位时间内限制的访问次数
     */
    private int limitCount;

    private int lockCount;

    private int lockTime;


    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(int limitCount) {
        this.limitCount = limitCount;
    }

    public int getLockCount() {
        return lockCount;
    }

    public void setLockCount(int lockCount) {
        this.lockCount = lockCount;
    }

    public int getLockTime() {
        return lockTime;
    }

    public void setLockTime(int lockTime) {
        this.lockTime = lockTime;
    }

    public boolean enableLimitLock(){
        return getLockTime()>0&&getLockCount()>0;
    }
}
