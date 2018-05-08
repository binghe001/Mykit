package io.mykit.cache.spring.proxy;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 获取WebApplicationContext对象
 * 本类中，调用getInstance()方法前，要确保servletContext不为空
 * @author liuyazhuang
 *
 */
public final class WebAppUtils{
	private WebAppUtils(){}
	private volatile static WebApplicationContext wac;
	private volatile static ServletContext servletContext;
	
	public static void setServletContext(ServletContext servletContext) {
		if(WebAppUtils.servletContext == null){
			synchronized (WebAppUtils.class) {
				if(WebAppUtils.servletContext == null){
					WebAppUtils.servletContext = servletContext;
				}
			}
		}
	}
	
	public static ServletContext getServletContext() {
		return WebAppUtils.servletContext;
	}

	public static WebApplicationContext getInstance(){
		if(wac == null){
			synchronized (WebAppUtils.class) {
				if(wac == null){
					if(servletContext != null){
						  wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
					}
				}
			}
		}
		return wac;
	}
}
