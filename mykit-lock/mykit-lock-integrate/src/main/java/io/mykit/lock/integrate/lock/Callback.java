package io.mykit.lock.integrate.lock;

/**
 * 回调接口
 * @author liuyazhuang
 *
 */
public interface Callback {
	
	/**
	 * 获得锁
	 * @return
	 * @throws InterruptedException
	 */
	Object onGetLock() throws InterruptedException;

    /**
     * 超时
     * @return
     * @throws InterruptedException
     */
    Object onTimeout() throws InterruptedException;
}
