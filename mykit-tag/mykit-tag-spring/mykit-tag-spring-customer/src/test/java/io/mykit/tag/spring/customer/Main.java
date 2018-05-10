package io.mykit.tag.spring.customer;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.mykit.tag.spring.customer.beans.entity.User;

/**
 * 测试
 * @author liuyazhuang
 *
 */
@SuppressWarnings("resource")
public class Main {
	public static void main(String[] args) {
		singleElementTag();
		singleObjectTag();
	}

	
	/**
	 * 测试单个元素的自定义tag标签实现
	 */
	private static void singleElementTag() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("lyz-element.xml");
		SimpleDateFormat info = (SimpleDateFormat) context.getBean("dateFormat");
	    System.out.println(info.format(new Date()));
	}
	
	/**
	 * 测试单个对象的自定义tag标签实现
	 */
	private static void singleObjectTag() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("lyz-user.xml");
		User user = (User) context.getBean("user");
		System.out.println(user);
	}
	
	
}
