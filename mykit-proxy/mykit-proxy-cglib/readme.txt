# 作者
Adam Lu(刘亚壮)

# 项目简介
mykit-proxy-cglib插件是Mykit架构下基于CGLIB实现的代理插件

# 具体实现
1、自定义了一个未实现接口的类，利用CGLIB的代理机制实现对此类的代理
	被代理的类为：io.mykit.proxy.cglib.service.impl.TestCGLIBServiceImpl
	CGLIB代理实现类为：io.mykit.proxy.cglib.handler.CGLIBProxy
	测试类为：io.mykit.proxy.cglib.ProxyTest
	
2、测试方式
	直接运行io.mykit.proxy.cglib.ProxyTest类即可

3、测试结果
	开始执行add...
	======================================
	前置处理开始 ...
	开始执行add...
	后置处理开始  ...
	 调用结束 ...

