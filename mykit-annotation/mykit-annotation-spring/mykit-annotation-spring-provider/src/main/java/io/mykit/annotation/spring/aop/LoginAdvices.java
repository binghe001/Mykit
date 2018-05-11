package io.mykit.annotation.spring.aop;

import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import io.mykit.annotation.spring.provider.RequiresLogin;

/**
 * 利用切面编程的JoinPoint解析注解信息
 * 
 * @author liuyazhuang
 *
 */
public class LoginAdvices {
	
	/**
	 * 解析注解信息，模拟检验登录状态
	 * @param joinPoint
	 * @throws Exception
	 */
	public void before(JoinPoint joinPoint) throws Exception {

		Object target = joinPoint.getTarget();
		String methodName = joinPoint.getSignature().getName();

		System.out.println(target + "-------" + methodName);
		Method method = target.getClass().getMethod(methodName);
		boolean annotationPresent = method.isAnnotationPresent(RequiresLogin.class);
		if (annotationPresent) {
			// 用户必须登录
			boolean isLogin = true;
			if (!isLogin) {
				System.out.println("访问该接口必须先登录");
				throw new Exception("访问该接口必须先登录");
			} else {
				System.out.println("已登录...");
			}
		}else{
			System.out.println("不需登录");
		}
	}
}