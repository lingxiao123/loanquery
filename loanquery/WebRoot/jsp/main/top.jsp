<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String username=null;
try{
	if(session==null){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else if(session.getAttribute("username").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		username=session.getAttribute("username").toString();
	}
}catch(Exception e){
	response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=path %>/css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=path %>/js/jquery.js" ></script>
	<script type="text/javascript">
	
	</script>
	<style type="text/css">
		body {margin: 0px;padding: 0px;}
	</style>
  </head>
  <body style="background:url(<%=path %>/images/topbg.jpg) repeat-x;">
  	<div class="topleft">
    <a href="main.html" target="_parent"><img src="images/mcjs_logo.png" title="系统首页" /></a>
    </div>
                   
    <div class="topright">    
    <ul>
    <li><a href="#"><%=username %></a></li>
    <li><a href="login.jsp" target="_parent">退出</a></li>
    </ul>    
    </div>
  </body>
</html>
