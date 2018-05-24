package io.mykit.filter.spring.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.web.bind.annotation.ModelAttribute;


/**
 * 控制器基础类
 * @author liuyazhuang
 *
 */
public class BaseController implements FactoryBean<Object>{

	private HttpServletRequest request;
    private HttpServletResponse response;
    public BaseController controllerProxy =  null;
	
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
	   this.request = request;
	   this.response = response;
	}

    @Override
    public Object getObject() throws Exception {
    	if(controllerProxy == null){
    		controllerProxy = ControllerProxy.getProxy(this);
    	}
        return controllerProxy;
    }
    
    @Override
    public Class<?> getObjectType() {
        return this.getClass();
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
}
