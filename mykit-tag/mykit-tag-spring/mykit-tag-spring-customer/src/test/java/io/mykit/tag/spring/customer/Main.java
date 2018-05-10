package io.mykit.tag.spring.customer;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试
 * @author liuyazhuang
 *
 */
@SuppressWarnings("resource")
public class Main {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("lyz-element.xml");
		 SimpleDateFormat info = (SimpleDateFormat) context.getBean("dateFormat");
	     System.out.println(info.format(new Date()));
	}
}
