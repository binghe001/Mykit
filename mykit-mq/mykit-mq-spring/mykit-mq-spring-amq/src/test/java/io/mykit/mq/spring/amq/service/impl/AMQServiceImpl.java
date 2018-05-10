package io.mykit.mq.spring.amq.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import io.mykit.mq.spring.amq.factory.AMQFactory;
import io.mykit.mq.spring.amq.sender.QueueSender;
import io.mykit.mq.spring.amq.sender.TopicSender;
import io.mykit.mq.spring.amq.service.AMQService;

@Service("amqService")
public class AMQServiceImpl implements AMQService {
	
	@Resource
	private QueueSender queueSender;
	@Resource
	private TopicSender topicSender;
	
	@Override
	public void sendQueueMessage(String message) {
		queueSender.send(AMQFactory.getAMQInfo(AMQFactory.TEST_SEND_AMQ_QUEUE), message);
	}

	@Override
	public void sendTopicMessage(String message) {
		topicSender.send(AMQFactory.getAMQInfo(AMQFactory.TEST_SEND_AMQ_TOPIC), message);
	}

}
