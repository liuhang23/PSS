<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
	<meta charset="UTF-8">
	<title>进销存系统</title>
	<link rel="stylesheet" type="text/css" href="css/adminLogin.css">
	<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript">
		//密码修改成功后，跳转到登陆页，保证为全页面
		if(top != window){
			top.location.href = window.location.href;
		}
	</script>
	
</head>
<body>
<s:form action="login_check">
	<div class="header">
    	<div class="header-inner">
    		<!-- <img src="../images/logo.png" alt="logo" class="fl_lt" /> -->
            <h2 class="fl_lt">进销存管理平台</h2>
      		<div class="fl_rt welcome">您好，欢迎使用本系统!</div>
        </div>
    </div><!--end header-->
    
    <div class="middle">
    	<div class="fl_lt">
        	<img src="images/adminLogin.png" class="img" />
        </div>
        <div class="fl_lt land-box">
        	<div>
            	<div class="land-text">登录名：</div>
            	<input type="text" name="name" id="land_name" size="18" value="roleAdmin"/>
            </div>
            <div>
                <div class="land-text mar-top">密码：</div>
                <input type="password" name="password" id="password" size="18"  value="roleAdmin"/>
            </div>
            <div class="mar-top">
            	<button id="landButton">登录</button>
            </div>
            <div class="mar-top"><input type="checkbox" checked />&nbsp;我同意并已阅读《进销存服务协议》</div>
        </div><!--end .land-Box-->
        <div style="clear:both"></div>
    </div><!--.middle-->

    <div class="footer">
        <div class="footer-link">
            <ul>
                <li>关于我们</li>
                <li>法律声明</li>
                <li>政策法规</li>
                <li>服务公告</li>
                <li>友情链接</li>
            </ul>
        </div>
    </div>
</s:form>    
</body>
</html>
