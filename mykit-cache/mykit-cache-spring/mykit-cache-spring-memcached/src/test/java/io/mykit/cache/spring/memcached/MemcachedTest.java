package io.mykit.cache.spring.memcached;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.mykit.cache.spring.memcached.service.MemcachedService;
import io.mykit.cache.spring.memcached.sync.ConcurrentMemcachedClient;

/**
 * 测试Memcached
 * @author liuyazhuang
 *
 */
public class MemcachedTest {
	
	private ConcurrentMemcachedClient concurrentMemcachedClient;
	private ClassPathXmlApplicationContext context;
	
	@Before
	public void init(){
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-context.xml");
		concurrentMemcachedClient = (ConcurrentMemcachedClient) context.getBean("concurrentMemcachedClient");
	}
	
	@Test
	public void testMemcached(){
		concurrentMemcachedClient.set("test_memcached", "test_memcached");
		String value = (String) concurrentMemcachedClient.get("test_memcached");
		System.out.println(value);
		boolean flag = concurrentMemcachedClient.delete("test_memcached");
		if(flag){
			System.out.println("删除成功");
		}else{
			System.out.println("删除失败");
		}
	}
	
	@After
	public void release(){
		concurrentMemcachedClient = null;
	}
	
	/**
	 * 测试Memcached整合Spring后以Spring注解的形式操作缓存数据
	 */
	@Test
	public void testAnnotation(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-context.xml");
		MemcachedService memcachedService = (MemcachedService) context.getBean("memcachedService");
		System.out.println(memcachedService.getValue("test_memcached"));
	}
}
