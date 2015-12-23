<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!--分页点击事件  -->
<script type="text/javascript">
	  /*分页的函数  */
	  function go(no){
		 // document.getElementById("pageNo").value = no;	
		 // document.forms[0].submit(); 
		  $("#pageNo").val(no);	
		  $("#domainForm").submit();
		  
	  }
	  /*表单提交的函数  */
	  function updateDomain(url,id){ 
		  //document.forms[0].action = url+"_input.action?id="+id;
		  //document.forms[0].submit();
		  //document.forms[0].action = url+".action";
		  $("#domainForm").attr("action",url+"_input.action?id="+id);
		  $("#domainForm").submit();
		  $("#domainForm").attr("action",url+".action"); 
	  }
	  /*文件导出xls */
	  function downDomain(url){
		  $("#domainForm").attr("action",url+"_download.action");
		  $("#domainForm").submit();
		  $("#domainForm").attr("action",url+".action");  //完成下载后需要重置
	  }
	  
	  //ajax的删除:发出一个ajax删除请求  src(指超链接)
	  function deleteDomain(src,url,idValue){
		  $.get(url,{id:idValue},function(data){
			  //如果传回的字符串是json对象，就转换
			  if(data instanceof Object){
				  //alert("data Loaded:" +data);
				  if(data.success == true){
					  $("#domainForm").submit();
					  /* if($("#itemTable tr").size() == 2){ //删除后只剩下一行数据+表头
					  		$("#domainForm").submit();
					  }else{ //直接js操作
						   //src= a  a-->td-->tr
						   //src http://localhost:8080/pss/employee_delete.action?id=28
						   $(src).closest("tr").remove(); 	
						   //修改总的记录数 span ,td ,tr --->html()
						   $("#totalCount").html($("#totalCount").html()-1);
					  } */
				  }else{  
					  alert(data.msg);
				  }
			  }else{
				  //没有权限
				  alert(data);
			  }
				  
		  });
	  }
</script>


<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>&nbsp;</td>
		<td>共${pageResult.totalCount}条记录</td>
		<%-- <td>共<span id="totalCount">${pageResult.totalCount}</span>条记录</td> --%>
		<td>当前第<span style="color: red;">${pageResult.currentPage}</span>/${pageResult.totalPage }
		</td>
		<td>每页${pageResult.pageSize}条记录</td>
		<td><s:if test="pageResult.currentPage == 1">
				<td>首页</td>
				<td>上一页</td>
			</s:if> <s:else>
				<td><a href="#" onclick="go(1)">首页</a></td>
				<td><a href="#" onclick="go(${pageResult.currentPage-1})">上一页</a></td>
			</s:else> <s:if test="pageResult.currentPage == pageResult.totalPage">
				<td>下一页</td>
				<td>末页</td>
			</s:if> <s:else>
				<td><a href="#" onclick="go(${pageResult.currentPage + 1})">下一页</a></td>
				<td><a href="#" onclick="go(${pageResult.totalPage})">末页</a></td>
			</s:else></td>
		<td><s:textfield id="pageNo" name="baseQuery.currentPage"
				value="%{pageResult.currentPage}" size="2" /> <s:submit value="go" />
			<s:select list="{5,10,20}" name="baseQuery.pageSize"
				onchange="document.forms[0].submit();" /></td>
	</tr>
</table>
