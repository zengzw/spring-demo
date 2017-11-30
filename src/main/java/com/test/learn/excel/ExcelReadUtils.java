/*
 * @Project Name: springmvcdemo
 * @File Name: ExcelReadUtils.java
 * @Package Name: com.test.learn.excel
 * @Date: 2017年11月30日上午10:44:09
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.test.springmvc.model.User;

/**
 * 读取Excel，转行为对象
 * 
 * @author zengzw-1220
 * @date 2017年11月30日上午10:44:09
 * @see
 */
public class ExcelReadUtils {

	private static final Logger logger = LoggerFactory.getLogger(ExcelReadUtils.class);
	//成功
	public static final Integer STATUS_OK = Integer.valueOf(1);
	//失败
	public static final Integer STATUS_NO = Integer.valueOf(0);
	/**
	 * 私有化构造器
	 */
	private ExcelReadUtils(){
	}
	/**
	 * 获取excel文件中的数据对象
	 *
	 * @param is                        excel
	 * @param excelColumnNames          excel中每个字段的英文名(应该与pojo对象的字段名一致,顺序与excel一致)
	 * @return                          excel每行是list一条记录，map是对应的"字段名-->值"
	 * @throws Exception
	 */
	public static List<Map<String, String>> getImportData(File file, List<String> excelColumnNames) throws Exception {
		logger.debug("InputStream:{}", file);
		if (file == null) {
			return Collections.emptyList();
		}

		Workbook workbook = null;
		try {
			FileInputStream is = new FileInputStream(file);
			if(file.getName().endsWith("xls")){
				workbook = new HSSFWorkbook(is);
			}else{
				workbook = new XSSFWorkbook(is);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.debug("workbook:{}", workbook);
		if (workbook == null) {
			return Collections.emptyList();
		}

		//第一个sheet
		Sheet sheet = workbook.getSheetAt(0);
		//行数
		int rowCounts = sheet.getLastRowNum();
		logger.debug("rowCounts:{}", rowCounts);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(rowCounts - 1);
		//双重for循环取出数据
		for(int i = 1; i <=rowCounts; i++){
			Map<String, String> params = new HashMap<String, String>();

			Row row = sheet.getRow(i);
			int lastCellNum = row.getLastCellNum(); //最后一列
			//i,j i:行 j:列
			for(int j = 0; j < excelColumnNames.size(); j++){
				Cell cell = row.getCell(j);
				System.out.println(cell.toString());
				params.put(excelColumnNames.get(j), cell.toString());
			}
			list.add(params);
		}	
		return list;
	}
	/**
	 * 获取导入数据为对象的List	
	 *
	 * @param data
	 * @param clazz
	 * @param excelColumnNames
	 * @param checkExcel
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> makeData(List<Map<String, String>> data, Class<T> clazz, List<String> excelColumnNames, CheckExcel checkExcel) throws Exception {
		if(data == null || data.isEmpty() || clazz == null ) {
			return Collections.EMPTY_LIST;
		}
		List<T> result = new ArrayList<T>(data.size());
		for(Map<String, String> d : data) {
			if(checkExcel != null && !checkExcel.check(d)) {
				continue;
			}
			
			T entity = clazz.newInstance();
			for(String column : excelColumnNames) {
				BeanUtils.setProperty(entity, column, d.get(column));
			}
			result.add(entity);
		}
		return result;
	}



	public static void main(String[] args) throws Exception {
		File file = new File("E:/20171130101942050.xls");
		List<String> listFiled  = Arrays.asList("id","name","nickName");
		List<Map<String, String>> data = getImportData(file,listFiled);

		System.out.println(JSON.toJSONString(data));
		List<User> list = makeData(data, User.class, listFiled, null);
		System.out.println(list.get(0).getName());
		System.out.println(JSON.toJSONString(list));

	}
}
