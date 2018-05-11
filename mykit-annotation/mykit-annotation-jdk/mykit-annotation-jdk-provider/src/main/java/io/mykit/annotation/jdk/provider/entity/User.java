package io.mykit.annotation.jdk.provider.entity;

import io.mykit.annotation.jdk.provider.Gender;
import io.mykit.annotation.jdk.provider.Info;
import io.mykit.annotation.jdk.provider.Gender.GenderEnum;

/**
 * 以注解标识的类
 * @author liuyazhuang
 *
 */
@Info(name="liuyazhuang", age = 18, hobby = {"Java", "C", "Python", "Go"})
public class User {
	
	@Gender(gender = GenderEnum.BOY)
	private String sex;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
