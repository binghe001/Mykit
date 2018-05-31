package io.mykit.lock.redis.exception;

/**
 * 自定义锁异常
 * @author liuyazhuang
 *
 */
public class CacheLockException extends Throwable {
	private static final long serialVersionUID = -2065392593996460639L;
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public CacheLockException(String msg) {
		this.msg = msg;
	}

	public CacheLockException() {
	}

}
