/*
 * @Project Name: springmvcdemo
 * @File Name: HashCodeEntity.java
 * @Package Name: com.test.learn.jdk7
 * @Date: 2018年5月4日上午9:06:39
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk7;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年5月4日上午9:06:39
 * @see
 */
public class HashCodeEntity {
	
	private int id;
	
	private String name;

	
	public HashCodeEntity(int id,String name) {
		this.id = id;
		this.name = name;
	}
	
	
	public int getId() {
	
		return id;
	}

	
	public void setId(int id) {
	
		this.id = id;
	}

	
	public String getName() {
	
		return name;
	}

	
	public void setName(String name) {
	
		this.name = name;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


/*	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HashCodeEntity other = (HashCodeEntity) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	*/
}
