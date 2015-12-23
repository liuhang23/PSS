package com.hd.pss.service.Impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.hd.pss.dao.BaseDao;
import com.hd.pss.page.BaseQuery;
import com.hd.pss.page.PageResult;
import com.hd.pss.service.IBaseService;

@SuppressWarnings("unchecked")
public abstract class BaseServiceImpl<T> implements IBaseService<T> {

	private BaseDao<T> baseDao;
	private Class<T> entityClass; //声明
	
	//通过反射获取传入的实体类型
	public BaseServiceImpl(){
		Class clazz = getClass(); //此处获取的是子类字节码（EmployeeServiceImpl），父类声明为抽象类无法实例化 
		// System.out.println("clazz:" + clazz); // com.hd.pss.service.Impl.DepartmentServiceImpl
		Type type = clazz.getGenericSuperclass();
		// System.out.println("getGenericSuperclass():"+type);  //此处获取父类Class---BaseServiceImpl<Employee>
		if(type instanceof ParameterizedType){
			ParameterizedType parameterizedType = (ParameterizedType) type;
			entityClass = (Class<T>)parameterizedType.getActualTypeArguments()[0];
		}
		System.out.println("entityClass:"+entityClass); //Employee.class
	}

	public void save(T t) {
		baseDao.save(t);
		//throw new RuntimeException();
	}

	public void update(T t) {
		baseDao.update(t);
	}

	public void delete(Serializable id) {
		baseDao.delete(entityClass, id);
	}

	public T get(Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public List<T> getAll() {
		return baseDao.getAll(entityClass);
	}
	
	
	
	//按条件分页查询
	public PageResult<T> findPageResult(BaseQuery baseQuery){
		return baseDao.findPageResult(baseQuery);
	}
	//
	public List findByHql(String hql, Object... objects) {
		return baseDao.findByHql(hql, objects);
	}

    //导出execel文件(xsl) : 查询有数据，生成一个xls文件
	public InputStream export(String[] heads, List<String[]> list)  throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		// 1.创建一个xls文件
		WritableWorkbook workbook = Workbook.createWorkbook(outputStream);
		// 2.创建xls文件里面的表
		WritableSheet sheet = workbook.createSheet("sheet1", 0);
		// 3.创建标题内容 :param1:col 列 、param2:row 行
		for (int i = 0; i < heads.length; i++) {
			Label label = new Label(i, 0, heads[i]);
			sheet.addCell(label);
		}
		// 4.处理数据
		for (int i = 0; i < list.size(); i++) {
			String[] strings = list.get(i);// 一行的数据
			for (int j = 0; j < strings.length; j++) {
				Label label = new Label(j, i + 1, strings[j]);// 排除表头已经用了一行
				sheet.addCell(label);
			}
		}
		// 5.关闭流
		workbook.write();
		workbook.close();
		return new ByteArrayInputStream(outputStream.toByteArray());
	}
	
	//超级管理员:导入xls文件:让用户上传一个xls文件(格式必须固定,每列放什么字段的数据必须规定好),解析
	public List<String[]> imp(File file) throws Exception {
		List<String[]> list = new ArrayList<String[]>();
		// 获取一个xls文件
		Workbook workbook = Workbook.getWorkbook(file);
		// 获取xls文件里面的表
		Sheet sheet = workbook.getSheet(0);
		// 获取行/列
		System.out.println("行:" + sheet.getRows());
		System.out.println("列:" + sheet.getColumns());
		for (int i = 1; i < sheet.getRows(); i++) {// 排除表头 i=1表示从第2行开始
			String[] strings = new String[sheet.getColumns()];
			for (int j = 0; j < sheet.getColumns(); j++) {
				Cell cell = sheet.getCell(j, i);
				//System.out.print(cell.getContents() + "\t");
				strings[j] = cell.getContents();
			}
			//System.out.println();
			list.add(strings);
		}
		// 关闭流
		workbook.close();
		return list;
	}

	//setter注入
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	


	
}
