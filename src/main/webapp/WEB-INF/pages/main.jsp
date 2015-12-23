<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入样式 -->
<link href="css/index.css" rel="stylesheet" type="text/css" />
<title>进销存系统-系统主页</title>
</head>
<body>

<div class="container">
		<div class="head">
			<div class="head-left">
				<span style="font-weight: bold; color: #1f4906">欢迎您-</span><br>
					<span style="font-weight: bold;color: #1f4906">${loginUser.name}</span>
			</div>
			<div class="head-right">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="32%">
							<!--修改密码	  -->
							<a href="updatePassword.action" target="main"><img src="images/head-l.gif" border="0"></a>
						</td>
						<td width="26%">
							<!--注销  -->
							<a href="#"><img src="images/head-m.gif" border="0"></a>
						</td>
						<!-- <td width="7%">&nbsp;</td>
						<td width="35%">
							<a href="#"><img src="images/head-r.gif" border="0"></a>
						</td> -->
					</tr>
				</table>

			</div>
		</div>
		<!--"head"end-->

		<div class="content">
			<%@include file="/WEB-INF/pages/left.jsp" %>
			<!--"left"end-->
			<!--默认加载right.action  -->
			<iframe src="right.action"  
					style="width: 848px; float: right; height: 530px"  scrolling="no"  name="main" frameborder="0">
			</iframe>
			<!--"content-right"end-->
		</div>
		<!--"content"end-->
		<div class="footer">
			<div style="margin-top: 5px;">
				<table width="98%" border="0" cellpadding="0" cellspacing="0"
					align="center">
					<tr>
						<td width="82%"><img src="images/icon_1.gif">&nbsp;
							<a class="lanyo" href="www.lanyotech.com">航大信息技术 2009</a></td>
						<td width="18%" valign="middle"><img src="images/icon_2.gif">&nbsp;
							<a class="lanyo" href="#">如有疑问请与技术人员联系</a></td>
					</tr>
				</table>
			</div>

		</div>
		<!--"footer"end-->
	</div>
	<!--"container"end-->

</body>
</html>