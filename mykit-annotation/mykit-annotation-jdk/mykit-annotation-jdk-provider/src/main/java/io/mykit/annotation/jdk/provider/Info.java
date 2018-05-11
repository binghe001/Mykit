package io.mykit.annotation.jdk.provider;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义基本信息Info注解
 * @author liuyazhuang
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Info {
	String name() default "liuyazhuang";
	int age() default 18;
	String[] hobby() default {"basketball", "football"};
}
