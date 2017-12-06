/*
 * @Project Name: springmvcdemo
 * @File Name: ExcelTest.java
 * @Package Name: com.test.learn.excel
 * @Date: 2017年11月30日上午9:59:14
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.ReflectionUtils;

import com.google.common.reflect.Reflection;
import com.test.learn.excel.ExcelUtil.ExportMonitor;
import com.test.springmvc.model.User;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年11月30日上午9:59:14
 * @see
 */
public class ExcelTest extends ExportMonitor {

	public static void main(String[] args) {
		
		String[] fields = null;
		String[] titles = null;
		fields = new String[]{"id", "name", "nickName"};
		titles = new String[]{"订单编号", "供应商订单编号", "服务供应商"};
		String head = "-------head-----";

		List<User> list = new ArrayList<>();
		User user = new User();
		user.setId(1);
		user.setName("name");
		user.setNickName("张三");
		user.setDateTime(new Date());
		list.add(user);
		
		
		User user2 = new User();
		user2.setId(2);
		user2.setName("name2asa阿什顿发撒打发斯蒂芬是非得失的方式");
		user2.setNickName("李四");
		list.add(user2);
		
		ExcelTest excelTest = new ExcelTest();
		
//		File file = ExcelUtil.export (head, fields, titles, list, null);
//		ExcelUtil.exportExcel(excelTest, head, fields, titles, list, null);
		ExcelUtil.export(list, null);
		
	}

	@Override
	public void before(int paramInt) {
		System.out.println("---before:"+paramInt);
		
	}

	@Override
	public void doProgress(int paramInt, Exception paramException) {
		System.out.println("doPorcess:"+paramInt);
		
	}

	@Override
	public void finish(int paramInt1, int paramInt2, Exception paramException) {
		System.out.println("---finish:"+paramInt1 +" pa:"+paramInt2);
		
	}
}
