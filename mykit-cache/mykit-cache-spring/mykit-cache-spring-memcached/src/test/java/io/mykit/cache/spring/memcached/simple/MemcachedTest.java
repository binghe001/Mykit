package io.mykit.cache.spring.memcached.simple;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.mykit.cache.spring.memcached.simple.service.MemcachedService;

/**
 * 测试Memcached
 * @author liuyazhuang
 *
 */
public class MemcachedTest {
	
	private ClassPathXmlApplicationContext context;
	
	@Before
	public void init(){
	    context = new ClassPathXmlApplicationContext("spring/spring-context.xml");
	}
	
	
	@After
	public void release(){
		context = null;
	}
	
	/**
	 * 测试Memcached整合Spring后以Spring注解的形式操作缓存数据
	 */
	@Test
	public void testAnnotation(){
		try {
			MemcachedService memcachedService = (MemcachedService) context.getBean("simpleMemcachedService");
			System.out.println(memcachedService.getValueFromDefaultCache("default_test_memcached"));
			System.out.println(memcachedService.getValueFromInTimeCache("intime_test_memcached1"));
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(memcachedService.getValueFromDefaultCache("default_test_memcached"));
			System.out.println(memcachedService.getValueFromInTimeCache("intime_test_memcached1"));
			
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(memcachedService.getValueFromDefaultCache("default_test_memcached"));
			System.out.println(memcachedService.getValueFromInTimeCache("intime_test_memcached1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
