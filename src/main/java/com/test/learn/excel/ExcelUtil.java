package com.test.learn.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 生成Excel
 * 
 * @author zengzw-1220
 * @date 2017年11月30日下午4:55:06
 */
public class ExcelUtil
{
	@SuppressWarnings("rawtypes")
	private static void exportExcelBySheet(ExportMonitor monitor, int[] count, HSSFWorkbook wb, String sheetName, HSSFCellStyle headStyle, HSSFCellStyle titleStyle, HSSFCellStyle dataStyle, String head, String[] fields, String[] titles, List<?> dataList){
		try
		{
			if (monitor != null) {
				monitor.doProgress(count[0], null);
			}
			HSSFSheet sheet = wb.createSheet(sheetName);

			sheet.setDefaultRowHeight((short)500);

			int startRowIndex = 0;
			if (StringUtils.isNotEmpty(head))
			{
				HSSFRow row1 = sheet.createRow(startRowIndex);

				sheet.addMergedRegion(new CellRangeAddress(startRowIndex, startRowIndex, 0, fields.length - 1));
				row1.setHeightInPoints(25.0F);
				HSSFCell cell1 = row1.createCell((short)0, CellType.STRING);

				cell1.setCellStyle(headStyle);
				cell1.setCellValue(head);
				startRowIndex++;
			}
			HSSFRow titleRow = sheet.createRow(startRowIndex);
			for (int i = 0; i < fields.length; i++)
			{
				String field = fields[i];
				String title = titles[i];
				if (title == null) {
					title = field;
				}
				HSSFCell cell = titleRow.createCell((short)i, CellType.STRING);
				cell.setCellStyle(titleStyle);
				cell.setCellValue(title);
			}
			for (Object data : dataList) {
				if (data != null) {
					if ((data instanceof Map))
					{
						Map map = (Map)data;
						if (map.isEmpty()) {}
					}
					else
					{
						count[0] += 1;
						if (monitor != null) {
							monitor.doProgress(count[0], null);
						}
						HSSFRow row = sheet.createRow(++startRowIndex);
						row.setHeight((short)400);
						for (int i = 0; i < titles.length; i++)
						{
							String field = fields[i];
							try
							{
								Object value = PropertyUtils.getProperty(data, field);
								if (value == null) {
									value = "";
								}
								HSSFCell cell = row.createCell((short)i, CellType.STRING);
								if ((value instanceof BigDecimal))
								{
									dataStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
									cell.setCellStyle(dataStyle);
									cell.setCellValue(((BigDecimal)value).doubleValue());
								}
								else
								{
									cell.setCellStyle(dataStyle);
									cell.setCellValue(value.toString().trim());
								}
							}
							catch (Exception e)
							{
								e.printStackTrace();
							}
						}
					}
				}
			}
			for (int i = 0; i < titles.length; i++)
			{
				if (monitor != null) {
					monitor.doProgress(count[0], null);
				}
				sheet.autoSizeColumn(i);
			}
		}
		catch (Exception e)
		{
			if (monitor != null) {
				monitor.doProgress(count[0], e);
			}
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static synchronized List<File> exportExcel(ExportMonitor monitor, String head, String[] fields, String[] titles, List<?> dataList, File saveDir)
	{
		if (saveDir == null)
		{
			String tempDir = System.getProperty("user.dir");
			saveDir = new File(tempDir, "temp");
		}
		if (!saveDir.exists()) {
			saveDir.mkdirs();
		}
		if (monitor != null) {
			monitor.before(dataList.size());
		}
		int[] exportCount = new int[1];

		List<File> xlsFileList = new ArrayList();

		int maxSize = 60000;
		int xlsCount = dataList.size() / maxSize;
		if (dataList.size() % maxSize >= 0) {
			xlsCount++;
		}
		try
		{
			DateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String now = format.format(new Date());
			for (int i = 0; i < xlsCount; i++)
			{
				int from = i * maxSize;
				int to = Math.min(from + maxSize, dataList.size());
				List list = new ArrayList(dataList.subList(from, to));

				String fileName = now + "_" + (i + 1) + ".xls";
				if (xlsCount == 1) {
					fileName = now + ".xls";
				}
				File file = new File(saveDir, fileName);

				FileOutputStream fos = new FileOutputStream(file);
				export2Excel(exportCount, monitor, fos, head, fields, titles, list);

				xlsFileList.add(file);
			}
			if (monitor != null) {
				monitor.finish(dataList.size(), exportCount[0], null);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (monitor != null) {
				monitor.finish(dataList.size(), exportCount[0], e);
			}
		}
		finally
		{
			dataList.clear();
		}
		return xlsFileList;
	}

	public static synchronized File mergeExportExcel(ExportMonitor monitor, String head, List<String[]> fields, List<String[]> titles, Map<String, List<?>> list, File saveDir, String[] sheetNames)
	{
		if (saveDir == null)
		{
			String tempDir = System.getProperty("user.dir");
			saveDir = new File(tempDir, "temp");
		}
		if (!saveDir.exists()) {
			saveDir.mkdirs();
		}
		if (monitor != null) {
			monitor.before(list.size());
		}
		int[] exportCount = new int[1];
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String now = format.format(new Date());
		String fileName = now + ".xls";
		File file = new File(saveDir, fileName);
		try
		{
			FileOutputStream fos = new FileOutputStream(file);
			mergeExport2Excel(exportCount, monitor, fos, head, fields, titles, list, sheetNames);
			if (monitor != null) {
				monitor.finish(list.size(), 1, null);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (monitor != null) {
				monitor.finish(list.size(), 1, e);
			}
		}
		finally
		{
			list.clear();
		}
		return file;
	}

	@SuppressWarnings("rawtypes")
	private static synchronized void export2Excel(int[] exportCount, ExportMonitor monitor, OutputStream excelOut, String head, String[] fields, String[] titles, List<?> list)
	{
		if ((titles == null) || (titles.length == 0) || (fields == null) || (fields.length == 0) || (list == null)) {
			return;
		}
		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFCellStyle headStyle = createHeadStyle(wb);

		HSSFCellStyle titleStyle = createTitleStyle(wb);

		HSSFCellStyle dataStyle = createDataStyle(wb);
		HSSFDataFormat format = wb.createDataFormat();
		short textFormat = format.getFormat("@");
		dataStyle.setDataFormat(textFormat);

		int maxSize = 65000;
		int sheetCount = list.size() / maxSize;
		if (list.size() % maxSize > 0) {
			sheetCount++;
		}
		sheetCount = sheetCount == 0 ? 1 : sheetCount;
		for (int i = 0; i < sheetCount; i++)
		{
			int from = i * maxSize;
			int to = Math.min(from + maxSize, list.size());
			List row = list.subList(from, to);
			exportExcelBySheet(monitor, exportCount, wb, head + (i + 1), headStyle, titleStyle, dataStyle, head, fields, titles, row);
		}
		try
		{
			wb.write(excelOut);
			excelOut.flush(); return;
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			list.clear();
			try
			{
				excelOut.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private static synchronized void mergeExport2Excel(int[] exportCount, ExportMonitor monitor, OutputStream excelOut, String head, List<String[]> fileds, List<String[]> titles, Map<String, List<?>> list, String[] sheetNames)
	{
		if ((fileds == null) || (fileds.size() == 0) || (titles == null) || (titles.size() == 0) || (list == null)) {
			return;
		}
		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFCellStyle headStyle = createHeadStyle(wb);

		HSSFCellStyle titleStyle = createTitleStyle(wb);

		HSSFCellStyle dataStyle = createDataStyle(wb);
		HSSFDataFormat format = wb.createDataFormat();
		short textFormat = format.getFormat("@");
		dataStyle.setDataFormat(textFormat);

		int maxSize = 65000;
		int sheetCount = 0;
		for (int i = 0; i < list.size(); i++)
		{
			List<?> data = (List)list.get(String.valueOf(i));
			sheetCount = data.size() / maxSize + (data.size() % maxSize > 0 ? 1 : 0);
			sheetCount = sheetCount == 0 ? 1 : sheetCount;
			String sheetName = (sheetNames != null) && (sheetNames.length >= i) ? sheetNames[i] : "Sheet";
			head = (sheetNames != null) && (sheetNames.length >= i) ? sheetNames[i] : head;
			for (int j = 0; j < sheetCount; j++)
			{
				int from = j * maxSize;
				int to = Math.min(from + maxSize, data.size());
				List row = data.subList(from, to);
				exportExcelBySheet(monitor, exportCount, wb, sheetName, headStyle, titleStyle, dataStyle, head, (String[])fileds.get(i), (String[])titles.get(i), row);
			}
		}
		try
		{
			wb.write(excelOut);
			excelOut.flush(); return;
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			list.clear();
			try
			{
				excelOut.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("deprecation")
	private static HSSFCellStyle createHeadStyle(HSSFWorkbook wb)
	{
		HSSFCellStyle headStyle = wb.createCellStyle();
		headStyle.setVerticalAlignment((short)1);
		headStyle.setAlignment((short)2);

		HSSFFont font = wb.createFont();

		font.setFontHeightInPoints((short)20);
		font.setFontName("宋体");
		font.setBoldweight((short)700);
		headStyle.setFont(font);
		return headStyle;
	}

	@SuppressWarnings("deprecation")
	private static HSSFCellStyle createTitleStyle(HSSFWorkbook wb)
	{
		HSSFCellStyle titleStyle = wb.createCellStyle();
		titleStyle.setVerticalAlignment((short)1);
		titleStyle.setAlignment((short)2);

		HSSFFont font = wb.createFont();

		font.setFontHeightInPoints((short)15);
		font.setFontName("宋体");

		titleStyle.setFont(font);
		titleStyle.setWrapText(false);

		titleStyle.setBorderBottom((short)1);
		titleStyle.setBorderLeft((short)1);
		titleStyle.setBorderRight((short)1);
		titleStyle.setBorderTop((short)1);

		return titleStyle;
	}

	@SuppressWarnings("deprecation")
	private static HSSFCellStyle createDataStyle(HSSFWorkbook wb)
	{
		HSSFCellStyle dataStyle = wb.createCellStyle();
		dataStyle.setVerticalAlignment((short)1);
		dataStyle.setAlignment((short)2);

		HSSFFont font = wb.createFont();

		font.setFontHeightInPoints((short)12);
		font.setFontName("宋体");

		dataStyle.setFont(font);
		dataStyle.setWrapText(true);

		dataStyle.setBorderBottom((short)1);
		dataStyle.setBorderLeft((short)1);
		dataStyle.setBorderRight((short)1);
		dataStyle.setBorderTop((short)1);

		return dataStyle;
	}

	/**
	 * 
	 *  到处Excel
	 *  
	 * @date 2017年11月30日上午10:14:25
	 * @author zengzw-1220
	 * @since 1.0.0 
	 * @param head  表头
	 * @param fields 数据源字段 与 DateList 对象中匹配
	 * @param titles  excel 标题
	 * @param dataList  数据源 List对象
	 * @param saveDir  保持目录
	 * @return File 文件
	 */
	public static File export(String head, String[] fields, String[] titles, List<?> dataList, File saveDir)
	{
		List<File> xlsFiles = exportExcel(null, head, fields, titles, dataList, saveDir);
		if (xlsFiles.size() > 1)
		{
			File zipFile = new File(saveDir.getParentFile(), "batchExport.zip");
			return zipFile;
		}
		if (xlsFiles.size() == 1) {
			return (File)xlsFiles.get(0);
		}
		return null;
	}

	public static File mergeExport(ExportMonitor monitor, String head, List<String[]> fields, List<String[]> titles, Map<String, List<?>> list, File saveDir, String[] sheetNames)
	{
		return mergeExportExcel(monitor, head, fields, titles, list, saveDir, sheetNames);
	}

	public static abstract class ExportMonitor
	{
		public abstract void before(int paramInt);

		public abstract void doProgress(int paramInt, Exception paramException);

		public abstract void finish(int paramInt1, int paramInt2, Exception paramException);
	}
}