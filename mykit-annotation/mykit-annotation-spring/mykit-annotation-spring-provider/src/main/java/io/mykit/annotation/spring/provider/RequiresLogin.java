package io.mykit.annotation.spring.provider;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义需要登录的注解，如果方法上存在这个注解，则说明这个方法是必须登录后才能访问
 * @author liuyazhuang
 *
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresLogin {

}
