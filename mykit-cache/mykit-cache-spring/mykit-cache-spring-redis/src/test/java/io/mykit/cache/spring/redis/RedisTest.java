package io.mykit.cache.spring.redis;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import io.mykit.cache.spring.redis.service.RedisService;

/**
 * 测试Redis缓存
 * @author liuyazhuang
 *
 */
@Component
public class RedisTest {
	
	private final Logger logger = LoggerFactory.getLogger(RedisTest.class);
	
	private ClassPathXmlApplicationContext context;
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext("spring/spring-context.xml");
		
	}
	
	@Test
	public void testRedis() throws Exception{
		RedisService redisService = (RedisService) context.getBean("redisService");
		while (true) {
			String result = redisService.getRedidInfo("redis_test");
			logger.info(result);
			Thread.sleep(1000);
		}
	}
}
