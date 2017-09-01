/*
 * @Project Name: springmvcdemo
 * @File Name: DataTest2.java
 * @Package Name: com.test.learn.jdk.date
 * @Date: 2017年8月31日下午4:15:54
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.language.Simple;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年8月31日下午4:15:54
 * @see
 */
public class DataTest2 {
	
	private static SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 查询一个时间段的总周数和查询当前时间是第几周
	 * @param start
	 * @param end
	 * @return
	 */
	private int[] selectWeekNum(Date start,Date end,int week,int flag){
		java.util.Calendar now = java.util.Calendar.getInstance();
		java.util.Calendar c_total = java.util.Calendar.getInstance();
		java.util.Calendar c_begin =  java.util.Calendar.getInstance();
		java.util.Calendar c_end = java.util.Calendar.getInstance();
		int count = 0;
		int weekTotal = 0;
		try {
			c_begin.setTime(start);
			c_end.setTime(end);
			now.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(format.format(new Date())));
			int begin = c_begin.get(java.util.Calendar.WEEK_OF_YEAR);
			int over =c_end.get( java.util.Calendar.WEEK_OF_YEAR);
			int z = now.get( java.util.Calendar.WEEK_OF_YEAR);
			if (c_begin.getWeekYear() != c_end.getWeekYear()) {
				Date totalYear = new SimpleDateFormat("yyyy-MM-dd").parse(c_begin.getWeekYear() + "-12-31");
				c_total.setTime(totalYear);
				int x = c_total.get(java.util.Calendar.WEEK_OF_YEAR);
				while(x==1){
					c_total.add(java.util.Calendar.DAY_OF_MONTH, -1);
					x = c_total.get(java.util.Calendar.WEEK_OF_YEAR);
				}
				//如果结束时间刚好是星期天则不加1
				int weekNum  = this.dayForWeek(new SimpleDateFormat("yyyy-MM-dd").format(end));
				int duoyu = 0;
				if(weekNum==7) {
					duoyu = x - begin+1;
				}else{
					duoyu = x - begin + 1+1;
				}
				weekTotal = over + duoyu;
			} else {
				//如果结束时间刚好是星期天则不加1
				int weekNum  = this.dayForWeek(new SimpleDateFormat("yyyy-MM-dd").format(end));
				if(weekNum==7){
					weekTotal = over - begin ;
				}else{
					weekTotal = over - begin + 1;
				}
			}
			if (c_begin.getWeekYear() != now.getWeekYear()) {
				//计算总周数
				int x = c_total.get(java.util.Calendar.WEEK_OF_YEAR);
				while(x==1){
					c_total.add(java.util.Calendar.DAY_OF_MONTH, -1);
					x = c_total.get(java.util.Calendar.WEEK_OF_YEAR);
				}
				int weekNum  = this.dayForWeek(new SimpleDateFormat("yyyy-MM-dd").format(start));
				int duoyu =0;
				if(weekNum==7){
					duoyu = x - begin + 1+1;
				}else{
					duoyu = x - begin + 1;
				}
				count = duoyu + z;
			} else {
				int weekNum  = this.dayForWeek(new SimpleDateFormat("yyyy-MM-dd").format(start));
				if(weekNum==7){
					count = (z - begin + 1)+1;
				}else{
					count = (z - begin + 1);
				}
			}
			if (c_end.getTime().getTime() < now.getTime().getTime() || count < 0) {
				//如果当前时间大于学期结束时间，默然显示第一周
				count = 1;
			}
			//下拉框默认显示的东西可根据实际情况删除或保留
			if (week != 0 && flag == 1) {
				count = week;
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return new int[]{count,weekTotal};
	}

	public static void main(String[] args) throws ParseException {
		String beginDate =  "2017-8-30";
		String endDate = "2017-09-10";
		
		DataTest2 test2 = new DataTest2();
		
		int[] v = test2.selectWeekNum(format.parse(beginDate),format.parse(endDate),1,1);
		System.out.println(Arrays.toString(v));
		
	}
	
	
	/**
	 * 判断当前日期是星期几
	 *
	 * @param pTime 修要判断的时间
	 * @return dayForWeek 判断结果
	 * @Exception 发生异常
	 */
	public int dayForWeek(String pTime) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util. Calendar c =  java.util. Calendar.getInstance();
		c.setTime(format.parse(pTime));
		int dayForWeek = 0;
		if(c.get( java.util. Calendar.DAY_OF_WEEK) == 1){
			dayForWeek = 7;
		}else{
			dayForWeek = c.get( java.util. Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}



	/**
	 * 查询一周的开始时间和结束时间
	 * @param date
	 * @return
	 */
	private Map<String,Date>  onceWeek(Date date){
		java.util.Calendar currentDate = new GregorianCalendar();
		currentDate.setTime(date);
		currentDate.setFirstDayOfWeek( java.util.Calendar.MONDAY);
		currentDate.set( java.util.Calendar.HOUR_OF_DAY, 0);
		currentDate.set( java.util.Calendar.MINUTE, 0);
		currentDate.set( java.util.Calendar.SECOND, 0);
		currentDate.set( java.util.Calendar.DAY_OF_WEEK,  java.util.Calendar.MONDAY);
		Date dBegin = (Date) currentDate.getTime().clone();

		currentDate.setFirstDayOfWeek( java.util.Calendar.MONDAY);
		currentDate.set( java.util.Calendar.HOUR_OF_DAY, 23);
		currentDate.set( java.util.Calendar.MINUTE, 59);
		currentDate.set( java.util.Calendar.SECOND, 59);
		currentDate.set( java.util.Calendar.DAY_OF_WEEK,  java.util.Calendar.SUNDAY);

		Date dEnd =(Date) currentDate.getTime().clone();

		//返回星期天和一个星期的日期和周几
		Map<String,Date> map = new HashMap<>();
		map.put("dBegin",dBegin);
		map.put("dEnd",dEnd);
		return map;
	}

/*
	*//**
	 * 查询一周的时间
	 * @param start
	 * @param end
	 * @param week
	 * @param flag
	 * @return
	 *//*
	private  List< List<ScheduleVO> >  onceWeekList(Date start ,Date end,int week,int flag) {
		List< List<ScheduleVO>> list = new ArrayList<>();
		try {
			int[] total = this.selectWeekNum(start, end,week,flag);
			int weekTotal = total[1];
			java.util.Calendar currentDate = new GregorianCalendar();
			currentDate.setTime(start);
			for (int i = 0; i < weekTotal; i++) {
				Map<String,Date> map = this.onceWeek(currentDate.getTime());
				List<ScheduleVO> lDate = findDates(map.get("dBegin"),map.get("dEnd"));
				currentDate.setTime(map.get("dEnd"));
				currentDate.add(java.util.Calendar.DAY_OF_MONTH, 1);
				list.add(lDate);
			}
		}catch (Exception e){
			System.out.print(e.getMessage());
		}
		return  list;
	}



	*//**
	 *查询当前日期和指定日期为星期几
	 * @param beginDate
	 * @param endDate
	 * @return
	 *//*
	public static List<ScheduleVO> findDates(Date beginDate, Date endDate)
	{
		//数字转中文
		SimpleDateFormat sif = new SimpleDateFormat("yyyy-MM-dd");
		Map<Integer,String> weekNumString = new HashMap<>();
		weekNumString.put(1,"星期一");
		weekNumString.put(2,"星期二");
		weekNumString.put(3,"星期三");
		weekNumString.put(4,"星期四");
		weekNumString.put(5,"星期五");
		weekNumString.put(6,"星期六");
		weekNumString.put(7,"星期天");

		ScheduleVO schedule1 = new ScheduleVO();
		List<ScheduleVO> lDate = new ArrayList<>();
		schedule1.setDate(sif.format(beginDate));
		schedule1.setWeekName("星期一");
		schedule1.setWeek(1);
		lDate.add(schedule1);

		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(beginDate);
		boolean bContinue = true;
		int week = 1;
		while (bContinue) {
			//根据日历的规则，为给定的日历字段添加或减去指定的时间量
			cal.add(java.util.Calendar.DAY_OF_MONTH, 1);
			// 测试此日期是否在指定日期之后
			if (endDate.after(cal.getTime()))
			{
				week++;
				ScheduleVO schedule = new ScheduleVO();
				schedule.setDate(sif.format(cal.getTime()));
				schedule.setWeekName(weekNumString.get(week));
				schedule.setWeek(week);
				lDate.add(schedule);
			}else {
				bContinue = false;
			}
		}
		return lDate;
	}*/
}
