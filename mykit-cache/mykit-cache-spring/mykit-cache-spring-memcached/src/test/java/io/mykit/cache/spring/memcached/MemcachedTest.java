package io.mykit.cache.spring.memcached;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.mykit.cache.spring.memcached.sync.ConcurrentMemcachedClient;

/**
 * 测试Memcached
 * @author liuyazhuang
 *
 */
public class MemcachedTest {
	
	private ConcurrentMemcachedClient concurrentMemcachedClient;
	
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
}
