package io.mykit.filter.spring.repeat.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import io.mykit.filter.spring.repeat.annotation.SameUrlData;

/**
 * 防止重复提交拦截器
 * @author liuyazhuang
 *
 */
@Component
public abstract class SameUrlDataInterceptor extends HandlerInterceptorAdapter {
	
	private final Logger logger  = LoggerFactory.getLogger(SameUrlDataInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info(SameUrlDataInterceptor.class.getName() + "====>>>preHandle");
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			SameUrlData annotation = method.getAnnotation(SameUrlData.class);
			if(annotation != null){
				return !repeatDataValidator(request);
			}
			return true;
		}else{
			return super.preHandle(request, response, handler);
		}
	}
	
	/**
	 * 验证同一个url数据是否相同提交  ,相同返回true，不相同返回false
	 * 具体交由子类实现具体的预防重复提交的规则
	 * @param httpServletRequest
	 * @return
	 */
	public abstract boolean repeatDataValidator(HttpServletRequest request);
}
