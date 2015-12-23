<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" href="js/ztree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/ztree/jquery.ztree.core-3.4.js"></script>
<!-- 引入ztree插件 -->
<script type="text/javascript">
	var setting = {
		async : {
			enable : true,
			url : "left.action",
			autoParam : [ "id", "name=n", "level=lv" ],//动态提交参数,变化
			otherParam : {
				"otherParam" : "zTreeAsyncTest"
			},//额外提交的参数:固定值
			dataFilter : filter
		}
	};

	function filter(treeId, parentNode, childNodes) {
		if (!childNodes)
			return null;
		for (var i = 0, l = childNodes.length; i < l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}

	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting);
	});
</script>
<div class="left">
	<div style="margin-left: 2px;">
		<img src="images/left-top.gif" width="162" height="25">
	</div>
	<div class="left-bottom">
		<div class="zTreeDemoBackground left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
	</div>
	<!--"left-bottom"end-->
</div>
<!--"left"end-->
