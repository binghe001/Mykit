package io.mykit.lock.redis.entity;

import java.io.Serializable;

public class Goods implements Serializable{

	private static final long serialVersionUID = -9075266061414274191L;
	
	private Long id = 0L;
	
	private Integer count = 0;
	

	public Goods() {
		super();
	}


	public Goods(Long id, Integer count) {
		super();
		this.id = id;
		this.count = count;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Integer getCount() {
		return count;
	}


	public void setCount(Integer count) {
		this.count = count;
	}


	@Override
	public String toString() {
		return "Goods [id=" + id + ", count=" + count + "]";
	}
	

}
