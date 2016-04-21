<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userid=null;
String username=null;
String pwd=null;
String roleid=null;
String status=null;
String superadmin="";
try{
	if(session==null){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else if(session.getAttribute("user_id").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		userid=session.getAttribute("user_id").toString();
	}
	if(session.getAttribute("user_name").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		username=session.getAttribute("user_name").toString();
	}
	if(session.getAttribute("user_passWord").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		pwd=session.getAttribute("user_passWord").toString();
		System.out.println("pwd="+pwd);
		
	}
	if(session.getAttribute("user_role").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		roleid=session.getAttribute("user_role").toString();
	}
	if(session.getAttribute("user_status").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		status=session.getAttribute("user_status").toString();
	}
	if(session.getAttribute("superadmin").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		superadmin=session.getAttribute("superadmin").toString();
		System.out.println(superadmin);
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
		function updateuser() {
			var f=document.getElementsByTagName("form")[0];
			var username=document.getElementById("username").value;
			var pwd=document.getElementById("userpwd").value;
			if(username==""){
				alert("请输入用户名");
				return;
			}
			if(pwd==""){
				alert("请输入密码！");
				return;
			}else if(!checkname()){
				alert("保存失败,请输入正确的信息！");
				return;
			}else{
				f.action="updateUsers.action";
			}
		}
		function checkname() {
			var flag=false;
			var obj=document.getElementById("username").value;
			$.ajax({
				url:"getCheckNames.action",
				type:"post",
				data:{"name":obj,"id":$("#user_id").val()},
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
    <li><label>用户名</label><input id="username" name="user.user_name" onkeyup="checkname();" type="text" value="<%=username %>" class="dfinput" /><i id="strs" style="color:red;"></i></li>
    <li><label>密码</label><input id="userpwd" name="user.user_passWord" value="<%=pwd %>" type="password" class="dfinput" /><i></i></li>
    <%if(superadmin.contains("true")){ %>
	    <li><label>类型</label><div class="cityright"><select id="user_role" name="user.user_role" class="select2">
	    <c:forEach var="i" items="${list}">
	    	<option value="${i.role_id }">${i.role_roleName}</option>
	    </c:forEach></select></div></li>
    <%}else{ %>
    	<input type="hidden" name="user.user_role" value="<%=roleid%>"/>
    <%} %>
    <li><label>&nbsp;</label><input name="" type="submit" class="btn" onclick="updateuser();" value="确认保存"/>&nbsp;&nbsp;<input name="" type="button" style="background-color:green;" onclick="javascript:history.go(-1);" class="btn" value="返回"/></li>
    </ul>
    <input type="hidden" id="user_id" name="user.user_id" value="<%=userid%>"/>   
    <input type="hidden" name="user.user_status" value="<%=status%>"/>
    
    </div>
    </form>
        <script type="text/javascript">
    	var select=document.getElementById("user_role");
    	for(var i=0; i<select.options.length; i++){  
    	    if(select.options[i].value ==<%=roleid%>){  
    	        select.options[i].selected = true;  
    	        break;  
    	    }  
    	}  
    </script>
  </body>
</html>
