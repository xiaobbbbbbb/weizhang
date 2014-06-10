package com.ecarinfo.traffic.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.ecarinfo.common.anno.EcField;
import com.ecarinfo.utils.poi.ExcelHeader;
import com.ecarinfo.utils.poi.ExcelTemplate;
import com.ecarinfo.utils.poi.ExcelUtil;

public class ExcelReader {

	private static final Logger logger = Logger.getLogger(ExcelReader.class);

	private POIFSFileSystem fs;
	private HSSFWorkbook wb;
	private HSSFSheet sheet;
	private HSSFRow row;

	/**
	 * 读取Excel表格表头的内容
	 * 
	 * @param InputStream
	 * @return String 表头内容的数组
	 */
	public String[] readExcelTitle(InputStream is) {
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		row = sheet.getRow(0);
		// 标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		System.out.println("colNum:" + colNum);
		String[] title = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			// title[i] = getStringCellValue(row.getCell((short) i));
			title[i] = getCellFormatValue(row.getCell((short) i));
		}
		return title;
	}

	/**
	 * 读取Excel数据内容
	 * 
	 * @param InputStream
	 * @return Map 包含单元格数据内容的Map对象
	 */
	public Map<Integer, String> readExcelContent(InputStream is) {
		Map<Integer, String> content = new HashMap<Integer, String>();
		String str = "";
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		row = sheet.getRow(0);
		int colNum = row.getPhysicalNumberOfCells();
		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			int j = 0;
			while (j < colNum) {
				// 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
				// 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
				// str += getStringCellValue(row.getCell((short) j)).trim() +
				// "-";
				str += getCellFormatValue(row.getCell((short) j)).trim()
						+ "    ";
				j++;
			}
			content.put(i, str);
			str = "";
		}
		return content;
	}

	/**
	 * 获取单元格数据内容为字符串类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	public String getStringCellValue(HSSFCell cell) {
		
		if(cell==null){
			return "";
		}
		else {
		
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		}
		return  cell.getStringCellValue();
		
	}
	
	/**
	 * 获取单元格数据内容为数字类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	public Integer getIntegerCellValue(HSSFCell cell) {
		Integer rvalue =0;
		if(cell==null){
			return rvalue;
		}
		else {
		
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		}
		if(StringUtils.isNotBlank(cell.getStringCellValue())){
			rvalue =	Integer.parseInt(cell.getStringCellValue());
		}
		return  rvalue;
		
	}

	/**
	 * 获取单元格数据内容为日期类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	public String getDateCellValue(HSSFCell cell) {
		String result = "";
		try {
			int cellType = cell.getCellType();
			if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
				Date date = cell.getDateCellValue();
				result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
						+ "-" + date.getDate();
			} else if (cellType == HSSFCell.CELL_TYPE_STRING) {
				String date = getStringCellValue(cell);
				result = date.replaceAll("[年月]", "-").replace("日", "").trim();
			} else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
				result = "";
			}
		} catch (Exception e) {
			System.out.println("日期格式不正确!");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据HSSFCell类型设置数据
	 * 
	 * @param cell
	 * @return
	 */
	public String getCellFormatValue(HSSFCell cell) {
		String cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case HSSFCell.CELL_TYPE_NUMERIC:
			case HSSFCell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，转化为Data格式

					// 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
					// cellvalue = cell.getDateCellValue().toLocaleString();

					// 方法2：这样子的data格式是不带带时分秒的：2011-10-12
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh24:mm:ss");
					cellvalue = sdf.format(date);

				}
				// 如果是纯数字
				else {
					// 取得当前Cell的数值
					cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			// 如果当前Cell的Type为STRIN
			case HSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			// 默认的Cell值
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;

	}

	public static void main(String[] args) {
		try {
//			// 对读取Excel表格标题测试
//			InputStream is = new FileInputStream("d:\\11\\100001sdfdsf .xls");
//			ExcelReader excelReader = new ExcelReader();
//			ExcelTemplate tm = ExcelTemplate.getInstance();
//			String[] title = excelReader.readExcelTitle(is);
//			System.out.println("获得Excel表格的标题:");
//			for (String s : title) {
//				System.out.print(s + " ");
//			}
//
//			// 对读取Excel表格内容测试
//			InputStream is2 = new FileInputStream("d:\\11\\100001sdfdsf .xls");
//			Map<Integer, String> map = excelReader.readExcelContent(is2);
//			System.out.println("获得Excel表格的内容:");
//			for (int i = 1; i <= map.size(); i++) {
//				System.out.println(map.get(i));
//			}
			ExcelTemplate et = ExcelTemplate.getInstance();
			List<ExcelHeader> headers = new ArrayList<ExcelHeader>();
			List<String> titleNames = new ArrayList<String>();
			titleNames.add("序号");
			titleNames.add("车牌号");
			titleNames.add("车架号");
			
			try {
				for (int i = 0; i < titleNames.size(); i++) {
					headers.add(new ExcelHeader(titleNames.get(i), i, "get" + StringUtils.capitalize(titleNames.get(i)), "java.lang.String"));
				}
			} catch (Exception e) {
				logger.error("获取Excel文件的标题，及内容的头部异常!", e);
			}
			et.createNewRow();
			List<ExcVO> dtos = new ArrayList<ExcelReader.ExcVO>();
			ExcVO vo = new ExcVO();
			vo.setId(1);
			vo.setName("aaa");
			dtos.add(vo);
			String exportPath = "d://a.xls";
			ExcelUtil.exportExcelFromObjects("rules",ExcVO.class, dtos,null,exportPath , "yyyy-MM-dd");
		} catch (Exception e) {
			System.out.println("未找到指定路径的文件!");
			e.printStackTrace();
		}
	}
	
	static class ExcVO {
		
		@EcField("序号")
		private Integer id;
		@EcField("名称")
		private String name;
		private String email;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
	}
}
