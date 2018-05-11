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
	
三、	基于JDK和Spring自定义插件实现日志管理
	1、io.mykit.annotation.spring.log.*包下实现了Spring自定义插件实现日志管理功能
		注：实际开发过程中，我们可以将日志信息存储到数据库或者文件系统，这里我只是打印出了相关的信息
	2、Spring的配置文件为classpath/spring/spring-log-annotation.xml
	3、测试入口为：io.mykit.annotation.spring.log.provider.LogAnnotationTest
	4、测试结果如下：
		==========开始执行controller环绕通知===============
		==========执行controller前置通知===============
		[mykit-annotation-spring-provider]2018-05-11 16:52:09,059 INFO [main] SystemLogAspect.doBefore(59) | before execution(void io.mykit.annotation.spring.log.user.service.impl.UserServiceImpl.addUser())
		[mykit-annotation-spring-provider]2018-05-11 16:52:09,073 INFO [main] UserServiceImpl.addUser(16) | 执行了添加用户的操作
		[mykit-annotation-spring-provider]2018-05-11 16:52:09,074 INFO [main] SystemLogAspect.around(72) | around execution(void io.mykit.annotation.spring.log.user.service.impl.UserServiceImpl.addUser())	Use time : 17 ms!
		==========结束执行controller环绕通知===============
		=====controller后置通知开始=====
		请求方法:io.mykit.annotation.spring.log.user.service.impl.UserServiceImpl.addUser().add操作
		方法描述:添加用户
		请求人:刘亚壮
		请求IP:127.0.0.1
		[mykit-annotation-spring-provider]2018-05-11 16:52:09,419 INFO [main] SystemLogServiceImpl.insert(26) | insert===>>>SystemLog [id=2e8abed5-6f00-4af7-9832-7c2b2f0cbd37, description=添加用户, method=io.mykit.annotation.spring.log.user.service.impl.UserServiceImpl.addUser().add操作, logType=0, requestIp=127.0.0.1, exceptioncode=null, exceptionDetail=null, params=null, createBy=刘亚壮, createDate=Fri May 11 16:52:09 CST 2018]
		=====controller后置通知结束=====
		=====执行controller后置返回通知=====
		[mykit-annotation-spring-provider]2018-05-11 16:52:09,419 INFO [main] SystemLogAspect.afterReturn(155) | afterReturn execution(void io.mykit.annotation.spring.log.user.service.impl.UserServiceImpl.addUser())
	
		
# 备注：		

一：简介
下面列举开发中常见的注解
    @Override：用于标识该方法继承自超类, 当父类的方法被删除或修改了，编译器会提示错误信息(我们最经常看到的toString()方法上总能看到这货)
    @Deprecated：表示该类或者该方法已经不推荐使用，已经过期了，如果用户还是要使用，会生成编译的警告
    @SuppressWarnings：用于忽略的编译器警告信息
    Junit测试：@Test
    Spring的一些注解：@Controller、@RequestMapping、@RequestParam、@ResponseBody、@Service、@Component、@Repository、@Resource、@Autowire
    Java验证的注解：@NotNull、@Email

下面看一下注解Override.java的庐山真面目

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Override {

}

二：注解基本知识
1. 注解数据类型
注解是写在.java文件中，使用@interface作为关键字, 所以注解也是Java的一种数据类型，从广泛的定义来说，Class、Interface、Enum、Annotation都属于Class类型。
2. 元注解
在创建注解的时候，需要使用一些注解来描述自己创建的注解，就是写在@interface上面的那些注解，这些注解被称为元注解，如在Override中看到的@Target、@Retention等。下面列出一些元注解
@Documented: 用于标记在生成javadoc时是否将注解包含进去，可以看到这个注解和@Override一样，注解中空空如也，什么东西都没有

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.ANNOTATION_TYPE)
    public @interface Documented {

    }

@Target：用于定义注解可以在什么地方使用，默认可以在任何地方使用，也可以指定使用的范围，开发中将注解用在类上(如@Controller)、字段上(如@Autowire)、方法上(如@RequestMapping)、方法的参数上(如@RequestParam)等比较常见。
        TYPE : 类、接口或enum声明
        FIELD: 域(属性)声明
        METHOD: 方法声明
        PARAMETER: 参数声明
        CONSTRUCTOR: 构造方法声明
        LOCAL_VARIABLE:局部变量声明
        ANNOTATION_TYPE:注释类型声明
        PACKAGE: 包声明

    Target.java
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.ANNOTATION_TYPE)
    public @interface Target {
        /**
         * Returns an array of the kinds of elements an annotation type
         * can be applied to.
         * @return an array of the kinds of elements an annotation type
         * can be applied to
         */
        ElementType[] value();
    }

    public enum ElementType {
        /** Class, interface (including annotation type), or enum declaration */
        TYPE,

        /** Field declaration (includes enum constants) */
        FIELD,

        /** Method declaration */
        METHOD,

        /** Formal parameter declaration */
        PARAMETER,

        /** Constructor declaration */
        CONSTRUCTOR,

        /** Local variable declaration */
        LOCAL_VARIABLE,

        /** Annotation type declaration */
        ANNOTATION_TYPE,

        /** Package declaration */
        PACKAGE,

        /** Type parameter declaration */
        TYPE_PARAMETER,

        /** Use of a type */
        TYPE_USE
    }

@Inherited：允许子类继承父类中的注解，可以通过反射获取到父类的注解

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.ANNOTATION_TYPE)
    public @interface Inherited {

    }

@Constraint：用于校验属性值是否合法

    @Documented
    @Target({ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Constraint {
        Class<? extends ConstraintValidator<?, ?>>[] validatedBy();
    }

@Retention：注解的声明周期，用于定义注解的存活阶段，可以存活在源码级别、编译级别(字节码级别)、运行时级别
        SOURCE：源码级别，注解只存在源码中，一般用于和编译器交互，用于检测代码。如@Override, @SuppressWarings。
        CLASS：字节码级别，注解存在于源码和字节码文件中，主要用于编译时生成额外的文件，如XML，Java文件等，但运行时无法获得。 如mybatis生成实体和映射文件，这个级别需要添加JVM加载时候的代理（javaagent），使用代理来动态修改字节码文件。

        RUNTIME：运行时级别，注解存在于源码、字节码、java虚拟机中，主要用于运行时，可以使用反射获取相关的信息。

        @Documented
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.ANNOTATION_TYPE)
        public @interface Retention {
            /**
             * Returns the retention policy.
             * @return the retention policy
             */
            RetentionPolicy value();
        }

3. 注解的内容
在上面的注解源码中可以看到有的注解中没有任何内容，有的注解的有内容，看似像方法。
注解的内容的语法格式： 数据类型 属性名() default 默认值，数据类型用于描述属性的数据类型，默认值是说当没有给属性赋值时使用默认值，一般String使用空字符串”“作为默认值，数组一般使用空数组{ }作为默认值.
下面看一下SpringMVC中的RequestMapping的注解的声明

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface RequestMapping {
    String name() default "";

    @AliasFor("path")
    String[] value() default {};

    @AliasFor("value")
    String[] path() default {};

    RequestMethod[] method() default {};
    String[] params() default {};
    String[] headers() default {};
    String[] consumes() default {};
    String[] produces() default {};
}


使用SpringMVC中的RequestMapping注解
@RequestMapping(value = "/list", method = RequestMethod.POST,  produces = {"application/json;charset=UTF-8;"})
	public String list(){

}

4. 注解的使用场景
可以通过注解的声明周期来分析注解的使用场景：
    SOURCE源码级别：给编译器使用，如@Override、@Deprecated 等， 这部分开发者应该使用的场景不多
    CLASS：字节码级别，这部分也很少见到
    RUNTIME：运行时级别，这个是最多的，几乎开发者使用到的注解都是运行时级别，运行时注解常用的有以下几种情况
        注解中没有任何属性的，空的注解，这部分注解通常起到一个标注的作用，如@Test、@Before、@After，通过获取这些标记注解在逻辑上做一些特殊的处理
        可以使用约束注解@Constraint来对属性值进行校验，如@Email, @NotNull等
        可以通过在注解中使用属性来配置一些参数，然后可以使用反射获取这些参数，这些注解没有其他特殊的功能，只是简单的代替xml配置的方式来配置一些参数。使用注解来配置参数这在Spring boot中得到了热捧，如@Configuration

关于配置方式xml vs annotation, 一般使用xml配置一些和业务关系不太紧密的配置，使用注解配置一些和业务密切相关的参数。
三：注解和反射基本API
// 获取某个类型的注解
public <A extends Annotation> A getAnnotation(Class<A> annotationClass);
// 获取所有注解(包括父类中被Inherited修饰的注解)
public Annotation[] getAnnotations(); 
// 获取声明的注解(但是不包括父类中被Inherited修饰的注解)
public Annotation[] getDeclaredAnnotations();
// 判断某个对象上是否被某个注解进行标注
public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass)

// 获取某个类声明的所有字段
public Field[] getDeclaredFields() throws SecurityException;
// 获取某个方法
public Method getMethod(String name, Class<?>... parameterTypes);


