package io.mykit.mq.spring.amq;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.mykit.mq.spring.amq.service.AMQService;

/**
 * 测试发送队列消息和订阅消息
 * @author liuyazhuang
 *
 */
public class AMQTest {
	
	private final Logger logger = LoggerFactory.getLogger(AMQTest.class);
	
	private AMQService amqService;
	@Before
	public void init(){
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-context.xml");
		amqService = (AMQService) context.getBean("amqService");
	}
	
	@Test
	public void testSendAMQMesaage(){
		logger.info("发送消息开始...");
		//发送队列消息
		amqService.sendQueueMessage("Hello Queue");
		//发送订阅消息
		amqService.sendTopicMessage("Hello Topic");
		logger.info("发送消息结束...");
	}
}
