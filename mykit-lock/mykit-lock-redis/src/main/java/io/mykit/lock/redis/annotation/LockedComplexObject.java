package io.mykit.lock.redis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解标识含有成员变量的复杂对象
 * @author liuyazhuang
 *
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockedComplexObject {
	
	//含有成员变量的复杂对象中需要加锁的成员变量，如一个商品对象的商品ID
	String field() default "";

}
