/*
 * @Project Name: springmvcdemo
 * @File Name: Hosting.java
 * @Package Name: com.test.learn.jdk8.vo
 * @Date: 2018年1月31日上午10:27:37
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8.vo;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年1月31日上午10:27:37
 * @see
 */
public class Hosting {
	
	private int Id;
    private String name;
    private long websites;

    public Hosting(int id, String name, long websites) {
        Id = id;
        this.name = name;
        this.websites = websites;
    }

	
	public int getId() {
	
		return Id;
	}

	
	public void setId(int id) {
	
		Id = id;
	}

	
	public String getName() {
	
		return name;
	}

	
	public void setName(String name) {
	
		this.name = name;
	}

	
	public long getWebsites() {
	
		return websites;
	}

	
	public void setWebsites(long websites) {
	
		this.websites = websites;
	}


	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + Id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (websites ^ (websites >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hosting other = (Hosting) obj;
		if (Id != other.Id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (websites != other.websites)
			return false;
		return true;
	}
	

}
