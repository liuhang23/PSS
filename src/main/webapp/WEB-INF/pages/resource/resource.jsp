<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.8.2.min.js" type="text/javascript"></script>
<title>Resource列表页</title>
</head>
<body>
	<s:form id="domainForm" action="resource">
		<div class="content-right">
			<div class="content-r-pic">
				<div style="margin: 8px auto auto 12px;">
					<img src="images/ping.gif" width="138" height="17" />
				</div>
			</div>
			<div class="content-text">
				<%@include file="/WEB-INF/pages/common/message.jsp" %>
				<div class="square-o-top">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						style="font-size: 14px; font-weight: bold; font-family:"黑体";">
						<tr>
							<td height="24">&nbsp;</td>
						</tr>
						<tr>
							<td height="30">名称:</td>
							<td><s:textfield name="baseQuery.name" size="18" /></td>
							<td>xx名称:</td>
							<td>
								<%--       <s:select list="#allDepts" name="baseQuery.deptId" listKey="id" listValue="name" headerKey="-1" headerValue="--请选择--"/> --%>
							</td>
							<td width="85"><input type="image" src="images/can_b_01.gif" /></td>
							<td width="136"><a href="resource_input.action"><img
									src="images/can_b_02.gif" border="0" /></a></td>
						</tr>
					</table>
				</div>
				<!--"square-o-top"end-->
				<div class="square-order">
					<table id="itemTable" width="100%" border="1" cellpadding="0"
						cellspacing="0">
						<tr align="center"
							style="background: url(images/table_bg.gif) repeat-x;">
							<td width="13%" height="30">编号</td>
							<td width="16%">名称</td>
							<td width="16%">方法</td>
							<td width="16%">操作</td>
						</tr>
						<s:iterator value="pageResult.rows">
							<tr align="center" bgcolor="#FFFFFF">
							  <td height="28">${id}</td>
								<td>${name}</td>
								<td>${method}</td>
								<td><img src="images/icon_3.gif" /> 
									<span style="line-height: 12px; text-align: center;">
									 <a class="xiu" href="#" onclick="updateDomain('resource',${id});">修改</a>
									</span>
									 <img src="images/icon_04.gif" /> 
									 <span style="line-height: 12px; text-align: center;">
									 	 <a class="xiu" href="#" onclick="deleteDomain(this,'resource_delete.action',${id});">删除</a>
									 </span>
							   </td>
							</tr>
						</s:iterator>
					</table>
				</div>
				<!--"square-order"end-->
			</div>
			<!--"content-text"end-->
			<div class="content-bbg">
				<%@include file="/WEB-INF/pages/common/page.jsp" %>
			</div>
		</div>
		<!--"content-right"end-->
	</s:form>
</body>
</html>

