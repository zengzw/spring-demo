/*
 * @Project Name: springmvcdemo
 * @File Name: Order.java
 * @Package Name: com.test.learn.jdk8.vo
 * @Date: 2018年4月16日上午10:58:50
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8.vo;

import java.util.function.Consumer;
import java.util.function.Predicate;

import com.alibaba.fastjson.JSON;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年4月16日上午10:58:50
 * @see
 */
public class Order {
	private long id;
	private double payment;
	private double freight;

	
	public long getId() {
	
		return id;
	}

	
	public void setId(long id) {
	
		this.id = id;
	}

	
	public double getPayment() {
	
		return payment;
	}

	
	public void setPayment(double payment) {
	
		this.payment = payment;
	}

	
	public double getFreight() {
	
		return freight;
	}

	
	public void setFreight(double freight) {
	
		this.freight = freight;
	}

	// 优惠政策
	public Order discount(Order order, Predicate<Order> predicate, Consumer<Order> consumer) {
		// 满足Predicate的条件，返回true
		if (predicate.test(order)) {
			// 接收订单对象，对订单对象进行处理
			consumer.accept(order);
		}

		return order;
	}
	
	
	public static void main(String[] args) {
		Order order = new Order();
		order.setId(1);
		order.setPayment(506.5);
		order.setFreight(101);
		
		order.discount(order, o->o.getPayment() > 100, o->o.setFreight(10));
		
		System.out.println(JSON.toJSONString(order));
		
	}
}
