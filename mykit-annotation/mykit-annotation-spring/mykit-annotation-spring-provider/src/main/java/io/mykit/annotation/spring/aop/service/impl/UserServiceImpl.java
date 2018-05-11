package io.mykit.annotation.spring.aop.service.impl;

import org.springframework.stereotype.Service;

import io.mykit.annotation.spring.aop.service.UserService;

@Service("aopUserService")
public class UserServiceImpl implements UserService {
	@Override
	public void addUser() {
		System.out.println("执行addUser方法...");
	}

}
