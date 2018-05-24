
#	一、项目简介
mykit-filter-spring-provider项目是Mykit架构下基于Spring的过滤插件

#	二、防重复提交具体说明
io.mykit.filter.spring.repeat.*包下为防重复提交业务
1、io.mykit.filter.spring.repeat.annotation.SameUrlData：为自定义注解，用来标识哪个方法需要防重复提交
2、io.mykit.filter.spring.repeat.interceptor.SameUrlDataInterceptor：抽象类为防重复提交拦截器，但只对使用了注解@SameUrlData标注的方法进行拦截，具体拦截规则交由子类实现；
3、io.mykit.filter.spring.repeat.interceptor.impl.MySameUrlDataInterceptor：插件提供的默认防重复提交拦截规则
4、springmvc-interceptor.xml：插件提供的默认拦截规则配置

#	三、防重复提交具体使用说明
 1、大家如果直接使用此默认规则类io.mykit.filter.spring.repeat.interceptor.impl.MySameUrlDataInterceptor进行防重复提交拦截，则在自己项目中的springmvc的配置文件中引入springmvc-interceptor.xml
	<import resource="classpath:springmvc-interceptor.xml"/>
  同时，在需要防重复提交的方法上加上@SameUrlData注解
  
 2、大家如果不使用此规则，则需要创建自定义规则类并继承SameUrlDataInterceptor，实现repeatDataValidator方法，在自己项目中的springmvc的配置文件中加入如下配置：
    <mvc:interceptors>
	 	<mvc:interceptor>
	         <mvc:mapping path="/**"/>
	         <bean class="你自己定义的防重复提交规则类"/>
	 	</mvc:interceptor>
	</mvc:interceptors>
 同时，在需要防重复提交的方法上加上@SameUrlData注解