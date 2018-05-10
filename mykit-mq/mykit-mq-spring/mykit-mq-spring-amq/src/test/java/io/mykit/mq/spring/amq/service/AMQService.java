package io.mykit.mq.spring.amq.service;

/**
 * 测试发送消息
 * @author liuyazhuang
 *
 */
public interface AMQService {
	
	/**
	 * 发送队列消息
	 * @param message
	 */
	void sendQueueMessage(String message);
	
	/**
	 * 发送订阅消息
	 * @param message
	 */
	void sendTopicMessage(String message);
}
