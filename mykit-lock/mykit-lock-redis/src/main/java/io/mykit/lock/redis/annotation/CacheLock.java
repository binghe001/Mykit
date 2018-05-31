package io.mykit.lock.redis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义锁注解
 * @author liuyazhuang
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLock {
	
	//redis 锁key的前缀
	String lockedPrefix() default "";
	
	//锁时间
	long timeOut() default 2000;
	
	//key在redis里存在的时间，1000S
	int expireTime() default 100000;
}
