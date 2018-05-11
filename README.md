# 作者简介: 
Adam Lu(刘亚壮)，高级软件架构师，Java编程专家，开源分布式消息引擎Mysum发起者、首席架构师及开发者，Android开源消息组件Android-MQ独立作者，国内知名开源分布式数据库中间件Mycat核心架构师、开发者，精通Java, C, C++, Python, Hadoop大数据生态体系，熟悉MySQL、Redis内核，Android底层架构。多年来致力于分布式系统架构、微服务、分布式数据库、大数据技术的研究，曾主导过众多分布式系统、微服务及大数据项目的架构设计、研发和实施落地。在高并发、高可用、高可扩展性、高可维护性和大数据等领域拥有丰富的经验。对Hadoop、Spark、Storm等大数据框架源码进行过深度分析并具有丰富的实战经验。

# 作者联系方式
QQ：2711098650

# 项目简述
一个通用的开源工具集，集Web、Restful服务、分布式服务、分布式数据库、分布式事务、大数据、数据分析、云计算、人工智能、深度学习等为一体的通用解决方案，持续更新中。。。

# 项目结构简述
## 整体架构
*	mykit：mykit整体架构

## mykit架构
*	mykit-bigdata：mykit架构中大数据相关的插件
*	mykit-cache：mykit架构中与缓存相关的插件
*	mykit-container：mykit架构中的容器插件
*	mykit-db:mykit架构中的db插件
*	mykit-filter：mykit架构中的过滤器插件
*	mykit-media：mykit架构中的多媒体插件
*	mykit-monitor：mykit架构中的监控插件
*	mykit-netty：mykit架构中的netty插件
*	mykit-rpc:mykit架构中的rpc插件
*	mykit-search：mykit中的搜索引擎插件
*	mykit-spider：mykit架构中的网络爬虫插件
*	mykit-tools：mykit架构中的通用工具插件
*	mykit-web：mykit架构中的web插件
*	mykit-mq：mykit架构中的消息引擎插件
*	mykit-tag：mykit架构中的标签插件
*	mykit-annotation：mykit架构中的注解插件
*	mykit-proxy：mykit架构中的代理插件



## mykit-bigdata架构
*	mykit-hadoop：mykit-bigdata大数据架构中与Hadoop相关的插件
*	mykit-spark：mykit-bigdata大数据架构中与spark相关的插件
*	mykit-storm：mykit-bigdata大数据架构中与storm相关的插件

## mykit-cache架构
*	mykit-cache-spring：mykit-cache缓存架构中与spring相关的插件

### mykit-cache-spring架构
*	mykit-cache-spring-memcached：mykit-cache-spring架构中与memcached相关的插件
*	mykit-cache-spring-redis：mykit-cache-spring架构中与redis相关的插件


## mykit-tools架构
*	mykit-tools-common：mykit-tools架构中的通用工具集插件

### mykit-tools-common架构
*	mykit-tools-ip：mykit-tools-common架构中与IP相关的插件

## mykit-tag架构
*	mykit-tag-spring：mykit-tag架构中与spring相关的插件

### mykit-tag-spring架构
*	mykit-tag-spring-customer：mykit-tag-spring架构中自定义spring标签插件

## mykit-mq架构
*	mykit-mq-spring：mykit-mq架构下mq与Spring整合的插件

### mykit-mq-spring架构
*	mykit-mq-spring-amq：mykit-mq-spring架构下AMQ(ActiveMQ)和Spring整合的具体实现插件


## mykit-annotation架构
*	mykit-annotation-jdk：mykit-annotation架构下基于JDK的自定义注解插件

### mykit-annotation-jdk架构
*	mykit-annotation-jdk-provider:mykit-annotation-jdk架构下基于JDK实现自定义插件的具体实现

##	mykit-annotation-spring架构
*	mykit-annotation-spring-provider:mykit-annotation-spring架构下基于JDK和Spring实现自定义插件的具体实现

## mykit-proxy架构
*	mykit-proxy-cglib：mykit-proxy架构下基于cglib实现的代理插件
*	mykit-proxy-jdk：mykit-proxy架构下基于jdk实现的动态代理插件




