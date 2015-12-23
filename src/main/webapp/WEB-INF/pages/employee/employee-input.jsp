<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- 页面样式 -->
<link href="css/index.css" rel="stylesheet" type="text/css" />
<!-- 验证框架的文件引入 -->
<link rel="stylesheet" type="text/css" media="screen" href="js/validation/css/screen.css" />
<script src="js/jquery-1.8.2.min.js" type="text/javascript"></script>
<script src="js/validation/jquery.validate.js" type="text/javascript"></script>
<script src="js/validation/messages_cn.js" type="text/javascript"></script>

<script type="text/javascript">
$().ready(function(){ 
	/* 等body加载完毕之后才执行,下面写的代码必须是英文版本 */
	$("#employeeForm").validate({
		/* 定义规则 */
		rules: {
			name : {
				required  : true,
				minlength : 5,
				//添加一个ajax验证，用户名不能重名
				remote : "employee_check.action?id=" + $("#id").val()
			},
			password : {
				required  : true,
				minlength : 5
			},
			email:"email",
			age : {
				digits : true,
				range  :[18,70]
			}
		},	
			messages : {
				name :{
					required  : "名称必须填写",
					remote : "此户名已经存在"
				}
			}
	});
});
</script>
<title>进销存系统-产品编辑界面</title>
</head>
<body>
	<%-- <s:debug /> --%>
	<s:form  id="employeeForm" action="employee_save" theme="simple">
		<s:hidden id="id"  name="id" />
		<s:hidden name="baseQuery.currentPage" />
		<s:hidden name="baseQuery.pageSize" />
		<s:hidden name="baseQuery.name" />
		<s:hidden name="baseQuery.email" />
		<s:hidden name="baseQuery.deptId" />
		<div class="content-right">
		<%@include file="/WEB-INF/pages/common/message.jsp" %>		
			<div class="content-r-pic">
				<div style="margin: 5px auto auto 12px;">
					<img src="images/canping.gif" width="138" height="17" />
				</div>
			</div>
			<div class="content-text">
				<div class="square-order">
					<div style="border: 1px solid #cecece;">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr bgcolor="#FFFFFF">
								<td>&nbsp;</td>
							</tr>
						</table>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr bgcolor="#FFFFFF">
								<td width="13%" height="30" align="center">名称</td>
								<td width="36%"><s:textfield name="name" /></td>
							</tr>
							<s:if test="id==null">
								<tr bgcolor="#FFFFFF">
									<td height="30" align="center">密码</td>
									<td><s:textfield name="password" /></td>
								</tr>
							</s:if>	
							<tr bgcolor="#FFFFFF">
								<td height="30" align="center">邮箱</td>
								<td><s:textfield name="email" /></td>
							</tr>
							<tr bgcolor="#FFFFFF">
								<td height="30" align="center">年龄</td>
								<td><s:textfield name="age" /></td>
							</tr>
							<tr bgcolor="#FFFFFF">
								<td height="30" align="center">部门名称</td>
								<td>
									<s:select  list="#allDepts" listKey="id"  listValue="name"
									   name="department.id" 
									   headerKey = "-1"  headerValue="----请选择----"
									 />
								</td>
							</tr>
							<tr bgcolor="#FFFFFF">
								<td height="30" align="center">当前员工拥有的角色列表</td>
								<td><s:checkboxlist list="#allRoles" name="ids" listKey="id" listValue="name"></s:checkboxlist></td>
							</tr>
						</table>
					</div>
					<div class="order-botton">
						<div style="margin: 1px auto auto 1px;">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td>
										<!-- *此处为保存   image标签 类似于submit  ajax不能使用这个标签-->
										<input type="image" src="images/order_tuo.gif" />
									</td>
									<td>&nbsp;</td>
									<td>
										<a href="#"><img src="images/order_tuo.gif" border="0" /></a></td>
									<td>&nbsp;</td>
									<td><a href="#"><img src="images/order_tuo.gif" border="0" /></a></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<!--"square-order"end-->
			</div>
			<!--"content-text"end-->
			<div class="content-bbg">
				<img src="images/content_bbg.jpg" />
			</div>
		</div>
		<!--"content-right"end-->
	</s:form>
	
	
</body>
</html>

