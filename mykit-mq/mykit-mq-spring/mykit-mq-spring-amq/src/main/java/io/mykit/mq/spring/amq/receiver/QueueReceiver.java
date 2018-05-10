package io.mykit.mq.spring.amq.receiver;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 消息接收
 * @author liuyazhuang
 *
 */
@Component
public class QueueReceiver implements MessageListener {
	
	private final Logger logger = LoggerFactory.getLogger(QueueReceiver.class);
	
	@Override
	public void onMessage(Message message) {
		try {
			if(message != null){
				 String messageContent = ((TextMessage)message).getText();
				 logger.info(messageContent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
