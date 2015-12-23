package com.hd.pss.velocity;

import java.io.StringWriter;
import java.util.Date;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;

import sun.print.resources.serviceui;

//Velocity
public class HelloWorldTest {
	
	 @Test
	 public void hello() throws Exception{
		 //实例化一个上下文
		 VelocityContext context = new VelocityContext();
		 //添加数据
		 context.put("text", "velocity模板"+new Date().toLocaleString());
		 //获取模板对象
		 //Template template = Velocity.getTemplate("template/hello.vm","UTF-8");
		 Template template = Velocity.getTemplate("template/hello.html","UTF-8");
		 //准备流
		 StringWriter writer = new StringWriter();
		 //把模板和数据的上下文合并 ： 流
		 template.merge(context,writer);
		 //输出
		 System.out.println(writer.toString());
	 }
	
	
	
}
