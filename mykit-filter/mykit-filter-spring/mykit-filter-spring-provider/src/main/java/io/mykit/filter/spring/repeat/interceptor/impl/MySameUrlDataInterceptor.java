package io.mykit.filter.spring.repeat.interceptor.impl;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import io.mykit.filter.spring.repeat.interceptor.SameUrlDataInterceptor;
/**
 * 插件提供的默认的防重复提交规则<br/>
 *  大家如果直接使用此规则，则在自己项目中的springmvc的配置文件中引入springmvc-interceptor.xml<br>
 *   <import resource="classpath:springmvc-interceptor.xml"/>
 *   同时，在需要防重复提交的方法上加上@SameUrlData注解<br/>
 *  大家如果不使用此规则，则需要创建自定义规则类并继承SameUrlDataInterceptor，实现repeatDataValidator方法<br/>
 *  在自己项目中的springmvc的配置文件中加入如下配置：<br/>
 *   <mvc:interceptors>
	 	<mvc:interceptor>
	         <mvc:mapping path="/**"/>
	         <bean class="你自己定义的防重复提交规则类"/>
	 	</mvc:interceptor>
	 </mvc:interceptors>
 *  同时，在需要防重复提交的方法上加上@SameUrlData注解<br/>
 * 
 * @author liuyazhuang
 *
 */
@Component
public class MySameUrlDataInterceptor extends SameUrlDataInterceptor {
	
	private static final String REPEAT_DATA = "repeatData";

	@Override
	public boolean repeatDataValidator(HttpServletRequest request) {
		String params = JSONObject.toJSONString(request.getParameterMap());
		String url = request.getRequestURI();
		Map<String, String> map = new HashMap<String, String>();
		map.put(url, params);
		String nowUrlParams = map.toString();

		Object preUrlParams = request.getSession().getAttribute(REPEAT_DATA);
		// 如果上一个数据为null,表示还没有访问页面
		if (preUrlParams == null) {
			request.getSession().setAttribute(REPEAT_DATA, nowUrlParams);
			return false;
		} else {// 否则，已经访问过页面
			// 如果上次url+数据和本次url+数据相同，则表示城府添加数据
			if (preUrlParams.toString().equals(nowUrlParams)) {
				return true;
			} else {// 如果上次 url+数据 和本次url加数据不同，则不是重复提交
				request.getSession().setAttribute(REPEAT_DATA, nowUrlParams);
				return false;
			}
		}
	}

}
