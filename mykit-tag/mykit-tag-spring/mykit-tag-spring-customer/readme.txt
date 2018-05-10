# 作者
Adam Lu(刘亚壮)

# 项目简介
mykit-tag-spring-customer项目是mykit架构中mykit-tag模块中自定义Spring标签的实现插件

# 实现步骤
	1、单元素实现方式
		1)创建io.mykit.tag.spring.customer.element.parser.SimpleDateFormatBeanDefinitionParser类继承org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser
		     覆写getBeanClass()和doParse()方法
		    @Override
			protected Class<?> getBeanClass(Element element) {
		        return SimpleDateFormat.class;
		    }
		    
		    @Override
		    protected void doParse(Element element, BeanDefinitionBuilder bean) {
		        String pattern = element.getAttribute("pattern");
		        bean.addConstructorArgValue(pattern);
		
		        // this however is an optional property
		        String lenient = element.getAttribute("lenient");
		        if (StringUtils.hasText(lenient)) {
		            bean.addPropertyValue("lenient", Boolean.valueOf(lenient));
		        }
		    }
		    
		2)创建io.mykit.tag.spring.customer.support.MyNamespaceHandler类继承org.springframework.beans.factory.xml.NamespaceHandlerSupport
		     覆写init()方法
		   @Override
		   public void init() {
		       registerBeanDefinitionParser("dateformat", new SimpleDateFormatBeanDefinitionParser());
		   }	
		   
		3)在classpath目录下新建 lyz-element.xsd文件，具体参见文件内容
		
		4)在classpath目录下新建lyz-element.xml文件，具体参见文件内容
		
		5)在classpath目录下新建META-INF目录
			在META-INF目录下新建文件spring.handlers文件，内容如下：
				http\://www.lyz.com/schema/lyz=io.mykit.tag.spring.customer.support.MyNamespaceHandler
				
			在META-INF目录下新建文件spring.schemas文件，内容如下：
				http\://www.lyz.com/schema/lyz.xsd=lyz-element.xsd
		
		6)创建程序的测试入口：
			io.mykit.tag.spring.customer.Main
	
	2、单个对象的自定义标签实现
		实现步骤和单元素实现方式的步骤相同，具体可参见项目本身
		
	3、项目测试
		直接运行程序的测试入口io.mykit.tag.spring.customer.Main即可打印测试结果如下：
		[mykit-tag-spring-customer]2018-05-10 15:47:35,978 INFO [main] AbstractApplicationContext.prepareRefresh(510) | Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@484b61fc: startup date [Thu May 10 15:47:35 CST 2018]; root of context hierarchy
		[mykit-tag-spring-customer]2018-05-10 15:47:36,017 INFO [main] XmlBeanDefinitionReader.loadBeanDefinitions(316) | Loading XML bean definitions from class path resource [lyz-element.xml]
		2018-05-10 15:47:36
		[mykit-tag-spring-customer]2018-05-10 15:47:36,200 INFO [main] AbstractApplicationContext.prepareRefresh(510) | Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@59e5ddf: startup date [Thu May 10 15:47:36 CST 2018]; root of context hierarchy
		[mykit-tag-spring-customer]2018-05-10 15:47:36,201 INFO [main] XmlBeanDefinitionReader.loadBeanDefinitions(316) | Loading XML bean definitions from class path resource [lyz-user.xml]
		User [name=liuyazhuang, sex=sex]
			
			
		   
		   
		   
		   
		
		