package io.mykit.annotation.spring.aop.provider;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.mykit.annotation.spring.aop.service.UserService;

/**
 * 测试 Spring AOP注解
 * @author liuyazhuang
 *
 */
public class AOPAnnotationTest {
	
	@SuppressWarnings("resource")
	@Test
	public void testAOPAnnotation(){
		//启动Spring容器        
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"spring/spring-aop-annotation.xml"});
        UserService userService = (UserService) ctx.getBean("aopUserService");
        userService.addUser();
	}
}
