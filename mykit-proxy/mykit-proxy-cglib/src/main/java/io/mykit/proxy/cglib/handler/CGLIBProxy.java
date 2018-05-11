package io.mykit.proxy.cglib.handler;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 基于CGLIB实现
 * @author liuyazhuang
 *
 */
public class CGLIBProxy implements MethodInterceptor {

	private Object targetObject;// 被代理的目标对象

	/**
	 * 构造代理对象
	 * @param targetObject 传递的真实对象
	 * @return 代理对象
	 */
	public Object createProxyInstance(Object targetObject) {
		this.targetObject = targetObject;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(targetObject.getClass());// 设置代理目标
		enhancer.setCallback(this);// 设置回调
		return enhancer.create();
	}

	/**
	 * 在代理实例上处理方法调用并返回结果
	 * @param object ： 代理类
	 * @param method  ：被代理的方法
	 * @param args ：该方法的参数数组
	 * @param methodProxy : 方法代理
	 */
	@Override
	public Object intercept(Object object, Method method, Object[] args, MethodProxy methodproxy) throws Throwable {
		Object result = null;
		try {
			System.out.println("前置处理开始 ...");
			result = methodproxy.invoke(targetObject, args);// 执行目标对象的方法
			System.out.println("后置处理开始  ...");
		} catch (Exception e) {
			System.out.println(" 异常处理 ...");
		} finally {
			System.out.println(" 调用结束 ...");
		}
		return result;
	}
}