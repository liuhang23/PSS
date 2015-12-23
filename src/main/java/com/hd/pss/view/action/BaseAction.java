package com.hd.pss.view.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * 非CURD需求的Action
 *
 */
public abstract class BaseAction extends ActionSupport{
	//日志对象  getClass 表示谁实例化就获得谁的对象
	protected  Logger logger =  LoggerFactory.getLogger(getClass());
	
	//重定向 ：客户端行为 redirectAction (response.sendRedirect()) :前一次的请求对象不会保存，地址栏的URL地址会改变
	public static final String RELOAD = "reload";

	//请求对象
	HttpServletRequest request = ServletActionContext.getRequest();

	//存登陆session里面key
	public static final String LOGIN_IN_SESSION = "loginUser";
	
	//重定向到后台主页
	public static final String  MAIN = "main";
	
	//输出json字符串
	public static final String JSON = "json";
	
	//公共的将值放到值栈中的方法
	public void putContext(String key , Object object){
		ActionContext.getContext().put(key, object);
	}
	
	
	//判断
//	public BaseAction(){
//		if(!"127.0.0.1".equals(request.getRemoteAddr())){
//			logger.debug("访问IP地址为:::::::" + request.getRemoteAddr());
//		}
//	}

}
