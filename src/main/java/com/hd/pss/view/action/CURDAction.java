package com.hd.pss.view.action;


import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

/**
 * 
 * 处理domain对象的CURD
 *	注意点 ：接口中所有方法都是abstract的，抽象类就是允许abstract方法存在的，所以不需要实现
 *			抽象类不能被实例化，所以它没必要实现所有的方法
 */
public  abstract class CURDAction<T> extends BaseAction implements ModelDriven<T> , Preparable{
	
	protected Long id;  //从前端获取传入的id

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	//不写代码
	public void prepare() throws Exception {  }
	
	//1.列表
	@Override
	@InputConfig(methodName="execute")
	public String execute() throws Exception{
		list();  		//访问子类的list方法
		return SUCCESS;
	}
	
	//employe_list.action是不能成功访问,为了权限只控制execute方法
	protected abstract  void  list() throws Exception;
	
	//2.跳转到添加页面和修改页面
	@Override
	public String input() throws Exception{
	//CURDAction继承了BaseAction,BaseAction继承了ActionSupport,ActionSupprot实现了Action(Action为接口，定义了INPUT常量="input")	
		return INPUT;  
	}
	
	
	//3.保存方法
	public abstract String save() throws Exception;
	
	//4.删除方法
	public abstract String delete() throws Exception;
	
	//5.action.input方法
	public void prepareInput() throws Exception{
		beforeInput();
	}
	
	//6.action.save方法
	public void prepareSave() throws Exception{
		beforeSave();
	}
	
	protected abstract void beforeInput() throws Exception;
	
	protected abstract void beforeSave() throws Exception;
	
	
	//让子类的去实现
//	public T getModel() {
//		return null;
//	}
	
	
	
	

}
