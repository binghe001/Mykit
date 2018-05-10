
# 项目简介
mykit-mq-spring-amq项目是mykit架构下AMQ(ActiveMQ)整合spring的插件具体实现

# 实现原理
基于Spring JMS整合AMQ(ActiveMQ)和spring

# 使用方式
将classpath/properties/activemq.properties文件中的下述内容修改成你自己服务器上ActiveMQ服务的IP地址、用户名和密码：
	activemq_broker_url=failover:(tcp://0.0.0.0:61616)
	activemq_username=xxxxxx
	activemq_password=xxxxxx
	
# 测试方式
1、将classpath/properties/activemq.properties文件中的下述内容修改成你自己服务器上ActiveMQ服务的IP地址、用户名和密码：
	activemq_broker_url=failover:(tcp://0.0.0.0:61616)
	activemq_username=xxxxxx
	activemq_password=xxxxxx

2、运行程序测试入口：io.mykit.mq.spring.amq.AMQTest