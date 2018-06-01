# 作者简介: 
Adam Lu(刘亚壮)，高级软件架构师，Java编程专家，开源分布式消息引擎Mysum发起者、首席架构师及开发者，Android开源消息组件Android-MQ独立作者，国内知名开源分布式数据库中间件Mycat核心架构师、开发者，精通Java, C, C++, Python, Hadoop大数据生态体系，熟悉MySQL、Redis内核，Android底层架构。多年来致力于分布式系统架构、微服务、分布式数据库、大数据技术的研究，曾主导过众多分布式系统、微服务及大数据项目的架构设计、研发和实施落地。在高并发、高可用、高可扩展性、高可维护性和大数据等领域拥有丰富的经验。对Hadoop、Spark、Storm等大数据框架源码进行过深度分析并具有丰富的实战经验。

# 作者联系方式
QQ：2711098650

# mykit-lock-integrate
基于redis和zookeeper分布式工具集-包括:分布式锁实现,分布式速率限制器,分布式ID生成器等.

##基于Redis实现的分布式锁(可重入)
~~~ java
public static void main(String[] args){
    JedisPool jedisPool=new JedisPool("127.0.0.1",6379);//实际应用时可通过spring注入
    final RedisDistributedLockTemplate template=new RedisDistributedLockTemplate(jedisPool);//本类线程安全,可通过spring注入
    template.execute("订单流水号", 5000, new Callback() {//获取锁超时时间为5秒
        @Override
        public Object onGetLock() throws InterruptedException {
            //TODO 获得锁后要做的事
            return null;
        }

        @Override
        public Object onTimeout() throws InterruptedException {
            //TODO 获得锁超时后要做的事
            return null;
        }
    });
}
~~~ 
~~~ java
public static void main(String[] args) throws Exception {
    JedisPool jedisPool=new JedisPool("127.0.0.1",6379);//实际应用时可通过spring注入
    RedisReentrantLock lock=new RedisReentrantLock(jedisPool,"订单流水号");
    try {
        if (lock.tryLock(5000L, TimeUnit.MILLISECONDS)) {//获取锁超时时间为5秒
            //TODO 获得锁后要做的事
        }else{
            //TODO 获得锁超时后要做的事
        }
    }finally {
        lock.unlock();
    }
}
~~~
[测试本实现的可靠性见测试用例](https://github.com/yujiasun/Distributed-Kit/blob/master/src/test/java/com/distributed/lock/redis/RedisReentrantLockTemplateTest.java)

##基于Zookeeper实现的分布式锁( 可重入 )
~~~ java
public static void main(String[] args){
    RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
    CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);
    client.start();

    final ZkDistributedLockTemplate template=new ZkDistributedLockTemplate(client);//本类多线程安全,可通过spring注入
    template.execute("订单流水号", 5000, new Callback() {//获取锁超时时间为5秒
        @Override
        public Object onGetLock() throws InterruptedException {
            //TODO 获得锁后要做的事
            return null;
        }

        @Override
        public Object onTimeout() throws InterruptedException {
            //TODO 获得锁超时后要做的事
            return null;
        }
    });
}
~~~
[测试本实现的可靠性见测试用例](https://github.com/yujiasun/Distributed-Kit/blob/master/src/test/java/com/distributed/lock/zk/ZkReentrantLockTemplateTest.java)

##基于Redis实现的分布式速率限制器

限制的资源,可以是ip,用户id,订单id,手机号,等等.
* 例如限制一个手机号每分钟只能发1条短信.
* 例如限制一个手机号每10秒钟只能发起1次表单提交请求.
* 例如限制一个ip地址每秒钟只能访问10次特定的资源.

~~~ java
public class AccessSpeedLimitTest {
    @Test
    public void test1() throws InterruptedException {
        JedisPool jp=new JedisPool("127.0.0.1",6379);
        AccessSpeedLimit accessSpeedLimit=new AccessSpeedLimit(jp);
        SimpleDateFormat sdf=new SimpleDateFormat(" mm:ss");
        while(true){
            //10.0.0.1这个ip每1秒钟最多访问5次if块内代码
            if(accessSpeedLimit.tryAccess("10.0.0.1", 1,5)){
                System.out.println("yes"+sdf.format(new Date()));
            }else{
                System.out.println("no"+sdf.format(new Date()));
            }
            Thread.sleep(100);
        }
    }

    @Test
    public void test2() throws InterruptedException {
        JedisPool jp=new JedisPool("127.0.0.1",6379);
        final RedisDistributedLockTemplate template=new RedisDistributedLockTemplate(jp);
        LimitRule limitRule=new LimitRule();
        limitRule.setSeconds(1);
        limitRule.setLimitCount(5);
        limitRule.setLockCount(7);
        limitRule.setLockTime(2);
        AccessSpeedLimit accessSpeedLimit=new AccessSpeedLimit(jp);
        SimpleDateFormat sdf=new SimpleDateFormat(" mm:ss");
        while(true){
            //10.0.0.1这个ip每1秒钟最多访问5次if块内代码.1秒超过10次后,锁定2秒,2秒内无法访问.
            if(accessSpeedLimit.tryAccess("10.0.0.1",limitRule)){
                System.out.println("yes"+sdf.format(new Date()));
            }else{
                System.out.println("no"+sdf.format(new Date()));
            }
            Thread.sleep(100);
        }
    }
}
~~~

