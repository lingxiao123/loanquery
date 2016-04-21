<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/select.css">
	<script type="text/javascript" src="<%=path %>/js/jquery.min.1.8.1.js" ></script>
	<script type="text/javascript" src="<%=path %>/js/select-ui.min.js" ></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		$(document).ready(function(e) {
		    $(".select1").uedSelect({
				width : 345			  
			});
			$(".select2").uedSelect({
				width : 167  
			});
			$(".select3").uedSelect({
				width : 100
			});
		});
	</script>
	<script type="text/javascript">
		function addrole() {
			var f=document.getElementsByTagName("form")[0];
			f.action="addRole.action";
		}
	</script>
  </head> 
  <body>
  	<form action="" method="post">
  	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">系统设置</a></li>
    <li><a href="#">角色管理</a></li>
    <li><a href="#">添加角色</a></li>
    </ul>
    </div>   
    <div class="formbody">  
    <div class="formtitle"><span>基本信息</span></div>  
    <ul class="forminfo">
    <li><label>角色名称</label><input name="role.role_roleName" type="text" class="dfinput" /><i></i></li>
    <li><label>&nbsp;</label><input name="" type="submit" class="btn" onclick="addrole();" value="确认保存"/>&nbsp;&nbsp;<input name="" type="button" style="background-color:green;" onclick="javascript:history.go(-1);" class="btn" value="返回"/></li>
    </ul>
    </div>
    </form>
  </body>
</html>
