package io.mykit.annotation.spring.log.provider;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.mykit.annotation.spring.log.user.service.UserService;

/**
 * 测试日志注解信息
 * @author liuyazhuang
 *
 */
public class LogAnnotationTest {
	
	@SuppressWarnings("resource")
	@Test
	public void testLogAnnotation(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/spring-log-annotation.xml"});
		UserService userService = (UserService) context.getBean("logUserService");
		userService.addUser();
	}
}
