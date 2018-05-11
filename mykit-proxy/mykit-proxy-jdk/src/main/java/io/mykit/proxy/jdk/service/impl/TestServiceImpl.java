package io.mykit.proxy.jdk.service.impl;

import io.mykit.proxy.jdk.service.TestService;

public class TestServiceImpl implements TestService {

	@Override
	public int add() {
		System.out.println("开始执行add...");
		return 0;
	}

}
