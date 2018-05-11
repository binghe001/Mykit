package io.mykit.proxy.jdk;

import io.mykit.proxy.jdk.handler.JDKDynamicProxy;
import io.mykit.proxy.jdk.service.TestService;
import io.mykit.proxy.jdk.service.impl.TestServiceImpl;

/**
 * 动态代理测试
 * 
 * @author liuyazhuang
 *
 */
public class ProxyTest {
	
	public static void main(String[] args) {
		// 我们要代理的真实对象
		TestService testService = new TestServiceImpl();
		testService.add();// 不是用代理
		System.out.println("===================================");
		JDKDynamicProxy JDKDynamicProxyTarget = new JDKDynamicProxy();
		TestService testServiceProxy = (TestService) JDKDynamicProxyTarget.newProxy(testService);
		// 执行代理类的方法
		testServiceProxy.add();
	}
}
