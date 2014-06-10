package com.ecarinfo.traffic.utils;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import com.ecarinfo.traffic.persist.po.OrgInfo;

public class HSSFUtil {
	

	private static CellStyle initStyle(HSSFWorkbook wb){
		// 生成一个样式
		CellStyle style = wb.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		// 生成一个字体
		Font font = wb.createFont();
		// font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 10);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		return style;
	}
	
	/**
	 * 创建单元格
	 * @param row
	 *            行
	 * @param column
	 *            列位置
	 * @param value
	 *            值
	 * @param style
	 *            样式
	 */
	private static void createCell(HSSFRow row, int column, Object value,CellStyle style) {
		HSSFCell cell = row.createCell(column);
		cell.setCellValue(String.valueOf(value));
		cell.setCellStyle(style);
	}
	
	/**
	 * 导出模板
	 * @param response
	 * @param orgInfo
	 * @throws IOException
	 */
	public static void createTemplate(HttpServletResponse response,OrgInfo orgInfo) throws IOException{
		HSSFWorkbook wb  = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();
		sheet.setDefaultColumnWidth(25);
		sheet.setDefaultRowHeight((short)20);
		HSSFRow row = sheet.createRow((short) 0);
		CellStyle style =HSSFUtil.initStyle(wb);
		int column = 0;
		createCell(row,column,"车牌号",style);
		column++;
		createCell(row,column,"车辆类型(1大型车，2小型车)",style);
		column++;
		createCell(row, column, "查询类型(1按次，2包年)", style);
		column++;
		if(orgInfo.getCarFrameLen()!=null&&orgInfo.getCarFrameLen()>0){
			createCell(row,column,"车架号(后"+orgInfo.getCarFrameLen()+"位)",style);
			column++;
		}
		if(orgInfo.getCarEngineLen()!=null&&orgInfo.getCarEngineLen()>0){
			createCell(row,column,"发动机号(后"+orgInfo.getCarEngineLen()+"位)",style);
			column++;
		}
		if(orgInfo.getCarCertificateLen()!=null&&orgInfo.getCarCertificateLen()>0){
			createCell(row,column,"证书号(后"+orgInfo.getCarCertificateLen()+"位)",style);
			column++;
		}
		response.setContentType("application/ms-excel;charset=UTF-8");
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String("车辆导入模板".getBytes("GBK"), "ISO-8859-1")
				+ ".xls");
		OutputStream out = response.getOutputStream();
		wb.write(out);
		out.flush();
		out.close();
	}
}
