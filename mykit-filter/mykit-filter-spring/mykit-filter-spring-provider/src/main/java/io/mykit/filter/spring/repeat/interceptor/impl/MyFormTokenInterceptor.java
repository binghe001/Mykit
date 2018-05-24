package io.mykit.filter.spring.repeat.interceptor.impl;

import javax.servlet.http.HttpServletRequest;

import io.mykit.filter.spring.repeat.interceptor.FormTokenInterceptor;

/**
 * 具体规则的实现,这种方式页面需要添加
 * <input type="hidden" name="formToken" value="${formToken}" />
 * @author liuyazhuang
 *
 */
public class MyFormTokenInterceptor extends FormTokenInterceptor {
	
	@Override
	public boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession(false).getAttribute("formToken");
        if (serverToken == null) {
            return true;
        }
        String clinetToken = request.getParameter("formToken");
        if (clinetToken == null) {
            return true;
        }
        if (!serverToken.equals(clinetToken)) {
            return true;
        }
        return false;
    }
}
