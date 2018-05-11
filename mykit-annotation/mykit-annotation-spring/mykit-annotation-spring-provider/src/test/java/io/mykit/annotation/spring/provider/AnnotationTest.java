package io.mykit.annotation.spring.provider;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.mykit.annotation.spring.service.UserService;

/**
 * 测试
 * @author liuyazhuang
 *
 */
public class AnnotationTest {
	
	
	/**
	 * 测试自定义注解实现登录验证
	 */
	@Test
	public void testAnnotation(){
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/spring-context-annotation.xml"});
		UserService userService = (UserService) context.getBean("userService");
		System.out.println(userService.getUserInfo());
	}
	
}
