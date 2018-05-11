package io.mykit.annotation.jdk.provider.parser;

import java.lang.reflect.Field;

import io.mykit.annotation.jdk.provider.Gender;
import io.mykit.annotation.jdk.provider.Info;

/**
 * 解析Info注解和Gender注解
 * @author liuyazhuang
 *
 */
public class AnnotationProcessor {
	/**
	 * 解析clazz实例中的指定注解
	 * @param clazz：要传入的clazz对象
	 */
	public static void parseAnnotation(Class<?> clazz){
		//clazz是以Info注解修饰
		if(clazz.isAnnotationPresent(Info.class)){
			//获取Info注解
			Info annotation = clazz.getAnnotation(Info.class);
			System.out.println(annotation);
			System.out.println(annotation.name());
			System.out.println(annotation.age());
			String[] bobby = annotation.hobby();
			for(String str : bobby){
				System.out.println(str);
			}
		}
		Field[] fields = clazz.getDeclaredFields();
		if(fields != null && fields.length > 0){
			for(Field field : fields){
				if(field.isAnnotationPresent(Gender.class)){
					Gender annotation = field.getAnnotation(Gender.class);
					System.out.println(annotation);
					System.out.println(annotation.gender());
				}
			}
		}
	}
}
