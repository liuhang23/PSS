package com.hd.pss.interceptor;


import java.util.List;

import com.hd.pss.domain.Employee;
import com.hd.pss.service.IEmployeeService;
import com.hd.pss.view.action.BaseAction;
import com.hd.pss.view.action.LoginAction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthInterceptor extends AbstractInterceptor{
	
	private IEmployeeService employeeService;
	
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		Object action = invocation.getAction();
		System.out.println("AuthInterceptor:"+action);
		//1.排除一些不能拦截的LoginActin,验证码Action
		if(action instanceof LoginAction){
			return invocation.invoke();  // 进入下一个拦截器
		}
		
		//2.拦截登录 :  获取session
		//Employee employee = (Employee)ServletActionContext.getRequest().getSession().getAttribute(BaseAction.LOGIN_IN_SESSION);
		Employee employee = (Employee) invocation.getInvocationContext().getSession().get(BaseAction.LOGIN_IN_SESSION);
		if(employee == null){
			// <global-results>
			//       <result name="login">/WEB-INF/pages/login.jsp</result>
		    // </global-results>
			return Action.LOGIN;  //跳转到登陆
		}
		
		//3.拦截权限
		//问题1:哪些action的方法要拦截:在资源管理的方法出现的就要拦截
		List<String> allResourceMethods = employeeService.getAllResourceMethods();
		String className = action.getClass().getName(); //获得请求的类名
		String methodName =  invocation.getProxy().getMethod(); //获取请求的方法名
		String classNameALL = className + ".ALL";
		String classMethodName = className + "." + methodName;
		System.out.println("classNameALL:" + classNameALL);
		System.out.println("classMethodName:" + classMethodName);
		if(allResourceMethods.contains(classNameALL) || allResourceMethods.contains(classMethodName)){
			System.out.println("需要拦截"); 
			// 问题2:怎样处理需要拦截的action的方法:看当前登录的用户是否具有这个资源
			List<String> list  = employeeService.findResourceMethodByLogin(employee.getId());
			if(list.contains(classNameALL) || list.contains(classMethodName)){
				System.out.println("有权访问");
			}else{
				System.out.println("无权访问");
//				<global-results>
//				<result name="auth">/WEB-INF/pages/auth.jsp</result>
//				</global-results>
				return "auth";
			}
		}else{
			System.out.println("不需要拦截");
		}
		
		return invocation.invoke();  // 进入下一个拦截器
	}

}
