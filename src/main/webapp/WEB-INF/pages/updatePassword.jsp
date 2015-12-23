<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!-- 页面样式 -->
<link rel="stylesheet" type="text/css" media="screen" href="js/validation/css/screen.css" />
<!-- 验证框架的文件引入 -->
<script src="js/jquery-1.8.2.min.js" type="text/javascript"></script>
<script src="js/validation/jquery.validate.js" type="text/javascript"></script>
<script src="js/validation/messages_cn.js" type="text/javascript"></script>
<script type="text/javascript">
$().ready(function() {
	//等body加载完毕之后才执行,下面的写的代码必须英文版本
	$("#updateForm").validate({
		//定义规则
		rules: {
			oldPassword:{
				required:true,
				minlength:5
			},
			newPassword:{
				required:true,
				minlength:5,
				equalNotTo: "#oldPassword"/*确认不相同  */
			},
			confirmPassword:{
				required:true,
				minlength:5,
				equalTo: "#newPassword"/*确认相同  */
			}
		},	
		messages: {
			newPassword:{
				equalNotTo:"新密码不能和旧密码相同"
			},
			confirmPassword:{
				equalTo:"确认密码必须和新密码相同"
			}
		}
	});
});

</script>
<%@include file="/WEB-INF/pages/common/message.jsp" %>
<s:form id="updateForm"  action="updatePassword_update">
	旧密码:<s:textfield id="oldPassword" name="oldPassword" ></s:textfield><br>
	新密码:<s:textfield id="newPassword" name="newPassword" ></s:textfield><br>
	确认新密码<s:textfield id="confirmPassword" name="confirmPassword" ></s:textfield><br>
	<s:submit value="修改密码" />
</s:form>