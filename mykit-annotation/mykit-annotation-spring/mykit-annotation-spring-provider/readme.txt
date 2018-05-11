# 作者
Adam Lu(刘亚壮)

# 项目简介
mykit-annotation-spring-provider是mykit架构下基于JDK和Spring的自定义注解插件

# 实现概述
本插件主要实现了三个案例：基于JDK和Spring自定义插件实现模拟用户登录校验；Spring AOP的注解实例；基于JDK和Spring自定义插件实现日志管理

# 具体描述
一、基于JDK和Spring自定义插件实现模拟用户登录校验
	1、io.mykit.annotation.spring.*包下实现了基于JDK和Spring自定义插件实现模拟用户登录校验案例
	2、Spring配置文件为classpath/spring/spring-context-annotation.xml
	3、测试入口为：io.mykit.annotation.spring.provider.AnnotationTest
	4、测试结果如下：
	1)直接运行
		io.mykit.annotation.spring.service.impl.UserServiceImpl@75d4a5c2-------getUserInfo
		访问该接口必须先登录
		
	2)将io.mykit.annotation.spring.service.impl.UserServiceImpl中方法getUserInfo()的@RequiresLogin注解去掉
		io.mykit.annotation.spring.service.impl.UserServiceImpl@13c9d689-------getUserInfo
		不需登录
		[{'id': 1, 'username':'liuyazhuang', 'sex':'mail', 'age':'18', 'address':'chengdu'}]
		
	3)将io.mykit.annotation.spring.advices.LoginAdvices中的boolean标志位isLogin改为true
		io.mykit.annotation.spring.service.impl.UserServiceImpl@75d4a5c2-------getUserInfo
		已登录...
		[{'id': 1, 'username':'liuyazhuang', 'sex':'mail', 'age':'18', 'address':'chengdu'}]
		
二、Spring AOP的注解实例
	1、io.mykit.annotation.spring.aop.*包下实现了Spring AOP的注解实例
	2、Spring的配置文件为classpath/spring/spring-aop-annotation.xml
	3、测试入口为：io.mykit.annotation.spring.aop.provider.AOPAnnotationTest
	4、测试结果如下：
		==========执行前置通知===============
		[mykit-annotation-spring-provider]2018-05-11 15:16:16,068 INFO [main] Interceptor.doBefore(66) | before execution(void io.mykit.annotation.spring.aop.service.impl.UserServiceImpl.addUser())
		执行addUser方法...
		[mykit-annotation-spring-provider]2018-05-11 15:16:16,084 INFO [main] Interceptor.around(52) | around execution(void io.mykit.annotation.spring.aop.service.impl.UserServiceImpl.addUser())	Use time : 17 ms!
		===========执行后置通知==============
		[mykit-annotation-spring-provider]2018-05-11 15:16:16,084 INFO [main] Interceptor.doAfter(74) | after execution(void io.mykit.annotation.spring.aop.service.impl.UserServiceImpl.addUser())
		===========执行后置返回通知==============
		[mykit-annotation-spring-provider]2018-05-11 15:16:16,085 INFO [main] Interceptor.afterReturn(82) | afterReturn execution(void io.mykit.annotation.spring.aop.service.impl.UserServiceImpl.addUser())
	
	
	
		
		

