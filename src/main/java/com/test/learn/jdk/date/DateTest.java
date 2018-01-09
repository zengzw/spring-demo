/*
 * @Project Name: springmvcdemo
 * @File Name: DateTest.java
 * @Package Name: com.test.learn.jdk.date
 * @Date: 2017年8月31日下午3:37:07
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年8月31日下午3:37:07
 * @see
 */
public class DateTest {

	public static SimpleDateFormat year_format = new SimpleDateFormat("yyyyMMdd");


	/**
	 *  
	 * @date 2017年9月1日上午11:43:08
	 * @author zengzw-1220
	 * @since 1.0.0 
	 * @param beginDateStr
	 * 					开始时间
	 * @param endDateStr
	 * 					结束时间
	 * @throws ParseException
	 */
	public static void categoryDay(String beginDateStr,String endDateStr) throws ParseException{
		int weekCount = 0; //总共多少个周
		Date beginDate = year_format.parse(beginDateStr);
		Date endDate = year_format.parse(endDateStr);

		Calendar beginCal = Calendar.getInstance();
		beginCal.setTime(beginDate);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);

		
		//获取年份
		int beginYear = beginCal.get(Calendar.YEAR);
		int endYear = endCal.get(Calendar.YEAR);
		
		
		//获取日期月份
		int beginMonth = beginCal.get(Calendar.MONTH)+1;
		int endMonth = endCal.get(Calendar.MONTH)+1;

		//获取日期天
		int beginDay = beginCal.get(Calendar.DAY_OF_MONTH);
		int endDay = endCal.get(Calendar.DAY_OF_MONTH);

		//不是跨年的逻辑
		if(beginYear == endYear){
			
		} //跨年的逻辑
		else{
			for(int i = beginYear; i <= endYear; i++){
				System.out.println(i);
			}
		}

		//月份相同的话，不需要计算当前月的某一天是周几。否则得根据月份来判断当前天是属于周几。
		if(beginMonth == endMonth){
			//重新设置一个新的日期，避免修改之前的日期
			Calendar temp = Calendar.getInstance();
			temp.setTime(beginDate);

			//获取当前天数是属于是星期几
			for(int i = beginDay; i<= endDay; i++){
				temp.set(Calendar.DAY_OF_MONTH,i);
				//获取周
				int currentWeek = temp.get(Calendar.DAY_OF_WEEK);
				if(currentWeek > 1){
					currentWeek--;

					//计算最后天数不是周日的，也是算新的一周。
					if(i == endDay ){
						weekCount++;
					};
				}else{
					currentWeek = 7;
					weekCount++;

				}

				System.out.println("天:"+i +" :周" + currentWeek);
			}

		}else{
			//重新设置一个新的日期，避免修改之前的日期
			Calendar temp = Calendar.getInstance();
			temp.setTime(beginDate);
			//获取到这个月最后一天
			int beginLastDayOfCurrentMonth = beginCal.getActualMaximum(Calendar.DAY_OF_MONTH);

			for(int i = beginDay; i<=beginLastDayOfCurrentMonth; i++){
				temp.set(Calendar.DAY_OF_MONTH,i);

				//获取周
				int currentWeek = temp.get(Calendar.DAY_OF_WEEK);
				if(currentWeek > 1){
					currentWeek--;

					//计算最后天数不是周日的，也是算新的一周。
					if(i == endDay ){
						weekCount++;
					};

				}else{
					currentWeek = 7;
					weekCount++;

				}
				System.out.println("跨日期-->天："+year_format.format(temp.getTime())+"  周："+currentWeek);
			}


			System.out.println("---------");
			//结束日期的周几
			temp.setTime(endDate);
			for(int i = 1; i<=endDay; i++){
				temp.set(Calendar.DAY_OF_MONTH,i);

				//获取当前日期星期几
				int currentWeek = temp.get(Calendar.DAY_OF_WEEK);
				if(currentWeek > 1){
					currentWeek--;

					//计算最后天数不是周日的，也是算新的一周。
					if(i == endDay ){
						weekCount++;
					};

				}else{
					currentWeek = 7;
					weekCount++;
				}

				System.out.println("跨日期-->天："+year_format.format(temp.getTime())+"  周："+currentWeek);
			}

		}


		System.out.println("总共有："+weekCount +" 周");

	}



	public static void main(String[] args) throws ParseException {
		String beginDateStr = "20180108";
		String endDateStr = "20190108";


		categoryDay(beginDateStr, endDateStr);
		/*String input = "20170923";
		String format = "yyyyMMdd";

		SimpleDateFormat df = new SimpleDateFormat(format);
		Date date = df.parse(input);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int WEEK_OF_YEAR = cal.get(Calendar.WEEK_OF_YEAR);
		int week = cal.get(Calendar.WEEK_OF_MONTH);
		int weekDay = cal.get(Calendar.WEDNESDAY);
		int DayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int DAY_OF_WEEK_IN_MONTH = cal.get(Calendar.WEDNESDAY);

		System.out.println("WEEK_OF_YEAR:"+WEEK_OF_YEAR); //一年中，第几周
		System.out.println("WEEK_OF_MONTH:"+week); //这个月的第几周
		System.out.println("WEDNESDAY:"+weekDay);  //同上
		System.out.println("DAY_OF_WEEK:"+DayOfWeek); //星期几（1：周日2：周一，所以值-1）
		System.out.println("DAY_OF_WEEK_IN_MONTH:"+DAY_OF_WEEK_IN_MONTH); //这个月的第几周

//		System.out.println(cal.getFirstDayOfWeek());
		System.out.println(cal.getActualMaximum(Calendar.DAY_OF_MONTH));

		//跨月份

		//同月份
		 */		
	}
}
