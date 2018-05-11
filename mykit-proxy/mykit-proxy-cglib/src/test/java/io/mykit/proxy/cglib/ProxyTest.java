package io.mykit.proxy.cglib;

import io.mykit.proxy.cglib.handler.CGLIBProxy;
import io.mykit.proxy.cglib.service.impl.TestCGLIBServiceImpl;

/**
 * 测试CGLIB代理
 * @author liuyazhuang
 *
 */
public class ProxyTest {
	
	 public static void main(String[] args) {
         //我们要代理的真实对象
         TestCGLIBServiceImpl testCGLIB = new TestCGLIBServiceImpl();
         testCGLIB.add();
         System.out.println("======================================");
         CGLIBProxy CGLIBproxy = new CGLIBProxy();
         TestCGLIBServiceImpl testCGLIBProxy = (TestCGLIBServiceImpl) CGLIBproxy.createProxyInstance(testCGLIB);
         testCGLIBProxy.add();
      }
}
