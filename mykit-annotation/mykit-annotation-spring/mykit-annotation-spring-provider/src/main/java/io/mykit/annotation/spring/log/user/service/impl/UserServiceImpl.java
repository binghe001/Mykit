package io.mykit.annotation.spring.log.user.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.mykit.annotation.spring.log.provider.Log;
import io.mykit.annotation.spring.log.user.service.UserService;

@Service("logUserService")
public class UserServiceImpl implements UserService {
	private final Logger logger  = LoggerFactory.getLogger(UserServiceImpl.class);
	@Override
	@Log(operationType="add操作", operationName = "添加用户")
	public void addUser() {
		logger.info("执行了添加用户的操作");
	}
}
