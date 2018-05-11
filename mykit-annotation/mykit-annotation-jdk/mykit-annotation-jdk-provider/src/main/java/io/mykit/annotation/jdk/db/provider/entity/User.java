package io.mykit.annotation.jdk.db.provider.entity;

import io.mykit.annotation.jdk.db.provider.Column;
import io.mykit.annotation.jdk.db.provider.Table;

/**
 * 自定义使用注解的实体
 * @author liuyazhuang
 *
 */
@Table("t_user")
public class User {
	
	@Column("id")
	private String id;
	
	@Column("name")
	private String name;

	public User() {
		super();
	}

	public User(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
}
