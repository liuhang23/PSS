package com.hd.pss.template;

import java.io.File;
import java.io.FileWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;

public class CreateTemplate {
	
	//1.定义domain的数组:外for
	//String[] domains = {"Dept"};  测试使用Dept
	//String[] domains = {"Department","Resource","Role"};
	//String[] domains = {"Menu"};
	// String[] domains = {"SystemDictionaryType","SystemDictionaryDetail"};
	String[] domains = {"Product","ProductType","Supplier"};
	// 如果生成的文件已经存在就不覆盖
	// true:覆盖,flag:不覆盖
	boolean flag = false;
	
	//2.定义9个模板文件的数组:内for
	String[] templates = { "Service.java", "ServiceImpl.java", "Query.java", "Action.java", "ServiceTest.java",
			"hbm.xml", "Manager.xml", "list.jsp", "input.jsp" };

	//3.定义一些固定的值 ： 规范 ： 前面路径不写/，后面都是以/结尾
	String SRC = "src/main/java/";
	String RESOURCES = "src/main/resources/";
	String TEST = "src/test/java/";
	String WEBAPP = "src/main/webapp/WEB-INF/pages/";
	String PACKAGE = "com/hd/pss/";
	//4.定义9个模板文件的文件路径
	String[] files = { SRC + PACKAGE + "service/", SRC + PACKAGE + "service/Impl/", SRC + PACKAGE + "page/",
			SRC + PACKAGE + "view/action/", TEST + PACKAGE + "service/", RESOURCES + PACKAGE + "domain/", RESOURCES + "manager/",
			WEBAPP, WEBAPP };
	@Test
	public void createCode() throws Exception{
				// 判断templates和files的长度一样
				if (templates.length != files.length) {
					System.out.println("templates和files的长度不一样");
					return;
				}

				VelocityContext context = new VelocityContext();
				for (int i = 0; i < domains.length; i++) {
					// 添加需要替换的值
					context.put("domain", domains[i]);
					// 对domain类,进行首字母小写的处理
					String lowerDomain = domains[i].substring(0, 1).toLowerCase() + domains[i].substring(1);
					context.put("lowerDomain", lowerDomain);
					for (int j = 0; j < templates.length; j++) {
						// 加载9个模板文件
						Template template = Velocity.getTemplate("template/" + templates[j], "UTF-8");
						// 文件路径
						File file = new File(files[j] + domains[i] + templates[j]);
						// 对一些特殊的文件做处理
						if ("Service.java".equals(templates[j])) {
							file = new File(files[j] + "I" + domains[i] + templates[j]);
						} else if ("hbm.xml".equals(templates[j])) {
							file = new File(files[j] + domains[i] + "." + templates[j]);
						} else if ("list.jsp".equals(templates[j])) {
							file = new File(files[j] + lowerDomain + "/" + lowerDomain + ".jsp");
						} else if ("input.jsp".equals(templates[j])) {
							file = new File(files[j] + lowerDomain + "/" + lowerDomain + "-input.jsp");
						}
						// getParentFile 返回此抽象路径名父目录的抽象路径名；如果此路径名没有指定父目录，则返回 null
						// exists   测试此抽象路径名表示的文件或目录是否存在。当且仅当此抽象路径名表示的文件或目录存在时，返回 true；否则返回 false
						// mkdir()  只能在已经存在的目录中创建创建文件夹。 
						// mkdirs() 可以在不存在的目录中创建文件夹。
						File parentFile = file.getParentFile();
						if (!parentFile.exists()) {
							parentFile.mkdirs();
						}
						// 如果生成的文件已经存在是否不覆盖
						// true:覆盖,flag:不覆盖
						// 如果文件已经存在并且不覆盖:不要生成文件   !flag : 判断flag取反后的状态是不是ture
						if (file.exists() && !flag) {
							continue;// 结束当前的循环,进入下一次循环
						}
						System.out.println(file.getAbsolutePath());
						FileWriter writer = new FileWriter(file);
						template.merge(context, writer);

						// 对于文件操作
						writer.close();
					}
				}
				System.out.println("代码已经生成,刷新工程,修改映射文件,运行测试");
	}

}
