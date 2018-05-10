package io.mykit.tag.spring.customer.beans.entity;

import java.io.Serializable;

/**
 * 以对象的形式创建自定义标签
 * @author liuyazhuang
 *
 */
public class User implements Serializable{

	private static final long serialVersionUID = -9114073048613344384L;
	private String name;
	private String sex;
	
	public User() {
		super();
	}

	public User(String name, String sex) {
		super();
		this.name = name;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", sex=" + sex + "]";
	}
	
}
