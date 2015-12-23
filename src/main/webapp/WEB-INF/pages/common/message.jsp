<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<s:if test="hasFieldErrors()">
<!-- 有转换异常出现 -->
<span style="color:red"><s:fielderror /></span>
</s:if>

<s:if test="hasActionErrors()">
<!-- action里面出现异常出现 -->
<span style="color:red"><s:actionerror/></span>
</s:if>

<s:if test="hasActionMessages()">
<!-- 有提示信息出现 -->
<span style="color:blue"><s:actionmessage /></span>
</s:if>
