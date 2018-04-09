/*
 * @Project Name: springmvcdemo
 * @File Name: CalendarTest1.java
 * @Package Name: com.test.learn.calendar
 * @Date: 2018年3月16日上午10:19:16
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年3月16日上午10:19:16
 * @see
 */
public class CalendarTest1 {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 
	 * 获取一时间范围内的所有日期
	 * 
	 * @date 2018年3月16日上午10:25:08
	 * @author zengzw-1220
	 * @since 1.0.0 
	 * @param startDateStr
	 * @param endDateStr
	 * @throws Exception 
	 */
	public static ScheduleDayEntity listDate(String startDateStr,String endDateStr) throws Exception{

		Date startDate = sdf.parse(startDateStr);
		Date endDate = sdf.parse(endDateStr);

		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);

		List<ScheduleEntity> listReuslt = new ArrayList<>();
		
		//循环变量时间段的日期，遵循周期规律，开始日期一直往上加。
		int count = 1;
		if(startCalendar.before(endCalendar)){
			while(startCalendar.getTime().before(endCalendar.getTime())){
				int weekCode = startCalendar.get(Calendar.DAY_OF_WEEK);
				weekCode--;
				if(weekCode == 0){
					weekCode = 7;
				}
				System.out.println((count++)+ ":"+sdf.format(startCalendar.getTime()) +" week:"+(weekCode));

				ScheduleEntity entity = new ScheduleEntity();
				entity.setDateStr(sdf.format(startCalendar.getTime()));
				entity.setWeekCode(weekCode);
				listReuslt.add(entity);

				startCalendar.add(Calendar.DAY_OF_MONTH, 1);
			}
		}else if(startCalendar.compareTo(endCalendar) == 0){
		
			ScheduleEntity entity = new ScheduleEntity();
			entity.setDateStr(sdf.format(startCalendar.getTime()));
			int weekCode = startCalendar.get(Calendar.DAY_OF_WEEK);
			entity.setWeekCode((weekCode-1));
			
			listReuslt.add(entity);
			
		}else{
			throw new Exception("开始日期，必须大于结束日期");
		}
		
		ScheduleDayEntity scheduleDayEntity = new ScheduleDayEntity();
		scheduleDayEntity.setCountDay(count);
		scheduleDayEntity.setScheduleEntity(listReuslt);

		return scheduleDayEntity;
	}
	
	

	public static void main(String[] args) throws Exception {
		ScheduleDayEntity scheduleDayEntity = listDate("2017-12-16", "2018-1-16");
		System.out.println(JSON.toJSONString(scheduleDayEntity));
	}
}

class ScheduleDayEntity{
	private List<ScheduleEntity> scheduleEntity;
	
	
	
	public List<ScheduleEntity> getScheduleEntity() {
	
		return scheduleEntity;
	}


	
	public void setScheduleEntity(List<ScheduleEntity> scheduleEntity) {
	
		this.scheduleEntity = scheduleEntity;
	}


	public int getCountDay() {
	
		return countDay;
	}

	
	public void setCountDay(int countDay) {
	
		this.countDay = countDay;
	}

	private int countDay;
	
}

class ScheduleEntity{

	int weekCode;

	String dateStr;


	public int getWeekCode() {

		return weekCode;
	}


	public void setWeekCode(int weekCode) {

		this.weekCode = weekCode;
	}


	public String getDateStr() {

		return dateStr;
	}


	public void setDateStr(String dateStr) {

		this.dateStr = dateStr;
	}


}
