/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.test.springmvc.model;

import java.io.Serializable;

/**
 * 挑选商品ItemVo
 * 
 * @author zengzw
 * @date 2015-7-22
 */
public class ProductChoiceItemVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5924927308173314546L;


	/**
	 * ID
	 */
	private Integer id;


	/**
	 * 图片
	 */
	private String image;


	/**
	 * 描述
	 */
	private String desc;


	/**
	 * 价格
	 */
	private Integer price;
	

	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}
	


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}



}
