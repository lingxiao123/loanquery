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
		function addpurview() {
			var f=document.getElementsByTagName("form")[0];
			var modulename=document.getElementById("modulename").value;
			var menuname=document.getElementById("menuname").value;
			var menuurl=document.getElementById("menuurl").value;
			if(modulename==""){
				alert("请输入模块名称");
				return;
			}else if(menuname==""){
				alert("请输入菜单名称");
				return;
			}else if(menuurl==""){
				alert("请输入路径");
				return;
			}else{
				f.action="addPurview.action";
			}
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
    <li><a href="#">权限信息</a></li>
    <li><a href="#">添加权限</a></li>
    </ul>
    </div>   
    <div class="formbody">  
    <div class="formtitle"><span>基本信息</span></div>  
    <ul class="forminfo">
    <li><label>模块名称</label><input id="modulename" name="purview.p_modulename" type="text" class="dfinput" /><i></i></li>
    <li><label>菜单名称</label><input id="menuname" name="purview.p_menuname"  class="dfinput" /><i></i></li>
    <li><label>菜单路径</label><input id="menuurl" name="purview.p_menuurl" class="dfinput" /><i id="str"></i></li>
    <li><label>按钮类型</label><input id="p_btntype" name="purview.p_btntype" class="dfinput" /></li>
    <li><label>权限类型</label><div class="cityright"><select name="purview.p_type" class="select2"><option value="菜单">菜单</option><option value="功能">功能</option></select></div></li>
    <li><label>&nbsp;</label><input name="" type="submit" class="btn" onclick="addpurview();" value="确认保存"/>&nbsp;&nbsp;<input name="" type="button" style="background-color:green;" onclick="javascript:history.go(-1);" class="btn" value="返回"/></li>
    </ul>
    </div>
    </form>
  </body>
</html>
