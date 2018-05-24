package io.mykit.filter.spring.repeat.interceptor;
import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import io.mykit.filter.spring.repeat.annotation.FormToken;

/**
 * 基于Token形式的防重复提交拦截器
 * @author liuyazhuang
 *
 */
public abstract class FormTokenInterceptor extends HandlerInterceptorAdapter {
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            FormToken annotation = method.getAnnotation(FormToken.class);
            if (annotation != null) {
                boolean needSaveSession = annotation.save();
                if (needSaveSession) {
                    request.getSession(false).setAttribute("formToken", UUID.randomUUID().toString());
                }
                boolean needRemoveSession = annotation.remove();
                if (needRemoveSession) {
                    if (isRepeatSubmit(request)) {
                        return false;
                    }
                    request.getSession(false).removeAttribute("formToken");
                }
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }

    /**
     * 具体规则交由子类实现
     * @param request
     * @return
     */
    public abstract boolean isRepeatSubmit(HttpServletRequest request);
}