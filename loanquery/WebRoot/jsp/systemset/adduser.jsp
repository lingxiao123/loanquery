<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
			if(!checkpwd()){
				alert("保存失败,请正确填写信息");
				return false;
			}
			if(!checkname()){
				alert("保存失败,请正确填写信息");
				return false;
			}else{
				document.getElementById("str").innerHTML="";
				f.action="addUser.action";
			}
		}
		function checkname() {
			var flag=false;
			var obj=document.getElementById("user_name").value;
			$.ajax({
				url:"getCheckName.action",
				type:"post",
				data:{"name":obj},
				async:false,
				dataType:"json",
				success:function(data){
					if(data.success==true){
						document.getElementById("strs").innerHTML="";
						flag=true;
					}else{
						document.getElementById("strs").innerHTML="该名已存在,请重新输入用户名！";
						flag=false;
					}
				}
			});
			return flag;
		}
		function checkpwd() {
			var flag=false;
			var pwd=document.getElementById("userpwd").value;
			var pwd2=document.getElementById("pwd").value;
			if(pwd!=pwd2){
				document.getElementById("str").innerHTML="两次密码不一致！";
				flag=false;
			}else{
				document.getElementById("str").innerHTML="";
				flag=true;
			}
			return flag;
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
    <li><a href="#">用户管理</a></li>
    <li><a href="#">添加用户</a></li>
    </ul>
    </div>   
    <div class="formbody">  
    <div class="formtitle"><span>基本信息</span></div>  
    <ul class="forminfo">
    <li><label>用户名</label><input id="user_name" name="user.user_name" type="text" onblur="checkname()" class="dfinput" /><i id="strs" style="color: red;"></i></li>
    <li><label>密码</label><input id="userpwd" name="user.user_passWord" type="password" class="dfinput" /><i></i></li>
    <li><label>确认密码</label><input id="pwd" name="role_roleName" onblur="checkpwd()" type="password" class="dfinput" /><i id="str" style="color:red;"></i></li>
    <li><label>类型</label><div class="cityright"><select name="user.user_role" class="select2">
    <c:forEach var="i" items="${list}">
    	<option value="${i.role_id }">${i.role_roleName}</option>
    </c:forEach></select></div></li>
    <li><label>状态</label><div class="cityright"><select name="user.user_status" class="select2"><option value="0">禁止</option><option value="1">启用</option></select></div></li>
    <li><label>&nbsp;</label><input name="" type="submit" class="btn" onclick="addrole();" value="确认保存"/>&nbsp;&nbsp;<input name="" type="button" style="background-color:green;" onclick="javascript:history.go(-1);" class="btn" value="返回"/></li>
    </ul>
    </div>
    </form>
  </body>
</html>
