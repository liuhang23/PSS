package com.hd.pss.jxl;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.junit.Test;

/**
 * java操作execle表格 HelloWorldJxl<BR>
 * 创建人:航大 <BR>
 * 时间：2015年11月3日-上午10:26:05 <BR>
 * 
 * @version 1.0.0
 *
 */
public class HelloWorldJxl {

	// 测试创建demo
	@Test
	public void createDemo() throws Exception {
		// 1.创建一个xls文件
		WritableWorkbook workbook = Workbook.createWorkbook(new File("demo.xls"));// 相对路径
		// 2.创建xls文件里面的表
		WritableSheet sheet = workbook.createSheet("sheet1", 0);
		// 3.创建标题内容
		// param1:col 列
		// param2:row 行
		Label label = new Label(0, 2, "A label record"); // 创建文本类型
		sheet.addCell(label);
		// 4.创建内容
		Number number = new Number(3, 4, 3.1459); // 创建数字,jxl中的number
		sheet.addCell(number);
		// 5.写入文件
		workbook.write();
		// 6.关闭流
		workbook.close();
	}

	@Test
	public void read() throws Exception {
		// 获取一个xls文件
		Workbook workbook = Workbook.getWorkbook(new File("read.xls"));
		// 获取xls文件里面的表
		Sheet sheet = workbook.getSheet(0);
		// 获取行/列
		System.out.println("行:" + sheet.getRows());
		System.out.println("列:" + sheet.getColumns());
		for (int i = 0; i < sheet.getRows(); i++) {
			for (int j = 0; j < sheet.getColumns(); j++) {
				//获取表格中的数据
				Cell cell = sheet.getCell(j, i); //先放列，再放行
				// \t==tab   \r\n换行
				System.out.print(cell.getContents() + "\t");
			}
			System.out.println();
		}
		// 关闭流
		workbook.close();
	}

	
}
