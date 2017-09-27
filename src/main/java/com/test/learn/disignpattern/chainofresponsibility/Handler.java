/*
 * @Project Name: springmvcdemo
 * @File Name: Handler.java
 * @Package Name: com.test.learn.disignpattern.chainofresponsibility
 * @Date: 2017年9月21日下午2:56:56
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.disignpattern.chainofresponsibility;

/** 
 *  领导请假审批，2天-A审批体，5天-B审批，10：C审批
 * @author zengzw-1220
 * @date 2017年9月21日下午2:56:56
 * @see
 */
public abstract class Handler {
	
	private Handler nextHadler;
	
	public int maxDay;
	
	protected Handler(int maxDay) {
		this.maxDay = maxDay;
	}
	
	public void setNextHadler(Handler handler){
		this.nextHadler = handler;
	}
	
	public void hadleRequest(int day){
		if(day <= maxDay){
			replay(day);
		}else{
			if(nextHadler != null){
				nextHadler.hadleRequest(day);
			}else{
				System.out.println("---没有更高级别的领导了。。。。。。。。");
			}
		}
	}
	
	
	protected abstract void replay(int day);
}
