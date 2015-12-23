package com.hd.pss.jxl;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hd.pss.domain.Department;
import com.hd.pss.domain.Employee;
import com.hd.pss.service.BaseServiceTest;
import com.hd.pss.service.IDepartmentService;
import com.hd.pss.service.IEmployeeService;
import com.sun.corba.se.spi.orb.StringPair;

/**
 * 
 * 导出员工的列表到exl 
 * EmployeeJxl<BR>
 * 创建人:航大 <BR>
 * 时间：2015年11月3日-上午10:51:47 <BR>
 * 
 * @version 1.0.0
 *
 */
// 继承BaseServiceTest就可以注入员工的Service
public class EmployeeJxl extends BaseServiceTest {
	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IDepartmentService departmentService;

	@Test
	public void CreatEmployeeJxl() throws Exception {
		// 1.创建一个xls文件
		WritableWorkbook workbook = Workbook.createWorkbook(new File("employee.xls"));// 相对路径
		// 2.创建xls文件里面的表
		WritableSheet sheet = workbook.createSheet("sheet1", 0);
		// 3.定义标题的内容
		String[] heads = {"编号","用户名","密码","email","年龄","部门名称"};
		for(int i = 0;i<heads.length;i++){
			// param1:col 列
			// param2:row 行
			Label label = new Label(i, 0, heads[i]); // 创建文本类型
			sheet.addCell(label);
		}
		// 4.处理数据,排除表头已经用了一行 
		List<Employee> employees = employeeService.getAll();
		for(int i=0;i<employees.size();i++){
			//把employee对象变成一行的数据
			Employee employee = employees.get(i);
			Label label = new Label(0,i+1,employee.getId().toString());
			sheet.addCell(label);
			label = new Label(1, i + 1, employee.getName());
			sheet.addCell(label);
			label = new Label(2, i + 1, employee.getPassword());
			sheet.addCell(label);
			label = new Label(3, i + 1, employee.getEmail());
			sheet.addCell(label);
			label = new Label(4, i + 1, employee.getAge().toString());
			sheet.addCell(label);
			Department department = departmentService.get(employee.getDepartment().getId());
			label = new Label(5, i + 1, department.getName());
			sheet.addCell(label);
			
		}
		// 5.写入文件
		workbook.write();
		// 6.关闭流
		workbook.close();
	}
	
	@Test
	public void readEmployee() throws Exception {
		List<String[]> list = employeeService.imp(new File("employee.xls"));
		for (String[] strings : list) {
			System.out.println(Arrays.toString(strings));
			// strings 变成Employee对象,调用save,注意name不能重复
		}
	}
	

}
