# 作者
Adam Lu(刘亚壮)

# 项目简介
mykit-lock-redis是mykit架构下基于注解和Redis的分布式锁案例具体实现

# 详细结构介绍
1、src/main/java下：
io.mykit.lock.redis.annotation包下为自定义的注解类
io.mykit.lock.redis.client包下为封装的RedisClient类
io.mykit.lock.redis.exception包下为自定义异常类
io.mykit.lock.redis.factory包下为RedisClient的工厂类
io.mykit.lock.redis.interceptor包下为自定义注解的拦截器，用于拦截标注有自定义注解的类
io.mykit.lock.redis.lock包下为RedisLock分布式锁的具体实现
io.mykit.lock.redis.utils包下为相关的工具类

2、src/main/resources下
redis.properties配置文件

3、src/test/java下
io.mykit.lock.redis.service包下为测试业务的接口，这里我们在接口方法上和方法的参数上增加了相关的注解
io.mykit.lock.redis.service.impl包下为测试业务接口的具体实现类
io.mykit.lock.redis包下为测试入口类，直接运行这个类即可测试我们的程序

# 测试说明
为了测试我们的分布式锁业务，这里，我们模拟商品秒杀的场景来测试分布式锁