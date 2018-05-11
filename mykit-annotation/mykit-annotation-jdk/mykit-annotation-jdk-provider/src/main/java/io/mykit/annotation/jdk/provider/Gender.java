package io.mykit.annotation.jdk.provider;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义性别注解
 * @author liuyazhuang
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Gender {
	
	public enum GenderEnum{BOY, GIRL}
	
	GenderEnum gender() default GenderEnum.BOY;
}
