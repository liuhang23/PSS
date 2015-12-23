package com.hd.pss.view.action;

import java.util.Date;

//演示velocity和struts2集成
public class VelocityAction  extends BaseAction{

	//添加数据
	public String getTest(){
		return "天热了" + new Date().toLocaleString();
	}
	
	
	//直接使用父类的方法
	//@Override
	//public String execute() throws Exception{
	//return supe.execute();
	//}
}
