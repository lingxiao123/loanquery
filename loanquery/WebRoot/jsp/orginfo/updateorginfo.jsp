<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String fullname=null;
String abb=null;
String pwd=null;
String phone=null;
String orgid=null;
String time=null;
String status=null;
String roleid=null;
String role=null;
try{
	if(session==null){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else if(session.getAttribute("org_FullName").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		fullname=session.getAttribute("org_FullName").toString();
	}
	if(session.getAttribute("org_id").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		orgid=session.getAttribute("org_id").toString();
	}
	if(session.getAttribute("org_Abbreviation").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		abb=session.getAttribute("org_Abbreviation").toString();
	}
	if(session.getAttribute("org_PassWord").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		pwd=session.getAttribute("org_PassWord").toString();
	}
	if(session.getAttribute("org_PhoneNumber").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		phone=session.getAttribute("org_PhoneNumber").toString();
	}
	if(session.getAttribute("org_AddTime").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		time=session.getAttribute("org_AddTime").toString();
	}
	if(session.getAttribute("org_Status").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		status=session.getAttribute("org_Status").toString();
	}
	if(session.getAttribute("org_RoleId").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		roleid=session.getAttribute("org_RoleId").toString();
	}
	if(session.getAttribute("role").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		role=session.getAttribute("role").toString();
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
		function updateorginfo() {
			var f=document.getElementsByTagName("form")[0];
			var pwd=document.getElementById("pwd").value;
			var org_FullName=document.getElementById("org_FullName").value;
			var org_Abbreviation=document.getElementById("org_Abbreviation").value;
			var org_Phone=document.getElementById("org_PhoneNumber").value;
			if(pwd==""||pwd==null){
				alert("请输入密码！");
				return;
			}else if(org_FullName==""||org_FullName==null){
				alert("请输入居间全称！");
				return;
			}else if(org_Abbreviation==""||org_Abbreviation==null){
				alert("请输入居间简称！");
				return;
			}else if(!checkname()){
				alert("保存失败,请输入正确的信息");
				return;
			}else{
				f.action="UpdateOrgInfo.action";
			}
		}
		function checkname() {
			var flag=false;
			var obj=document.getElementById("org_Abbreviation").value;
			$.ajax({
				url:"getCheckNames.action",
				type:"post",
				data:{"name":obj,"id":$("#org_id").val()},
				async:false,
				dataType:"json",
				success:function(data){
					if(data.success==true){
						document.getElementById("strs").innerHTML="";
						flag= true;
					}else{
						document.getElementById("strs").innerHTML="该名已存在,请重新输入姓名！";
						flag= false;
					}
				}
			});	
			return flag;
		}
		function checkPhone() {
			var flag=false;
			var phone=document.getElementById("org_PhoneNumber").value;
			if(phone==""){
				document.getElementById("str").innerHTML="";
				flag=true;
			}else{
				var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
				if(!myreg.test(phone)) 
				{ 
				    document.getElementById("str").innerHTML="请输入有效的手机号码！";
				    flag= false; 
				}else{
					document.getElementById("str").innerHTML="";
					flag= true; 
				} 
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
    <li><a href="#">居间管理</a></li>
    <li><a href="#">添加居间</a></li>
    </ul>
    </div>   
    <div class="formbody">  
    <div class="formtitle"><span>基本信息</span></div>  
    <ul class="forminfo">
    <%if(role=="系统用户") {%>
    	    <li><label><i style="color: red;">*</i>居间全称</label><input id="org_FullName" name="orgInfo.org_FullName" type="text" value="<%=fullname %>" class="dfinput" /><i></i></li>
    		<li><label><i style="color: red;">*</i>居间简称</label><input id="org_Abbreviation" onkeyup="checkname()"   name="orgInfo.org_Abbreviation" value="<%=abb %>"  type="text" class="dfinput" /><i id="strs" style="color: red;"></i></li>
    <%}else if(role=="居间商"){ %>
    	    <li><label><i style="color: red;">*</i>居间全称</label><input id="org_FullName" readonly="readonly" style="background-color:#EAEAEA;" name="orgInfo.org_FullName" type="text" value="<%=fullname %>" class="dfinput" /><i></i></li>
    		<li><label><i style="color: red;">*</i>居间简称</label><input id="org_Abbreviation" readonly="readonly"  style="background-color:#EAEAEA;" name="orgInfo.org_Abbreviation" value="<%=abb %>"  type="text" class="dfinput" /><i ></i></li>
    <%} %>

    <li><label><i style="color: red;">*</i>密码</label><input id="pwd" name="orgInfo.org_PassWord" type="password" value="<%=pwd %>"  class="dfinput" /><i></i></li>
    <li><label><i style="color: red;"></i>手机号码</label><input id="org_PhoneNumber" name="orgInfo.org_PhoneNumber" value="<%=phone %>"  onkeyup="checkPhone()" type="text" class="dfinput" /><input type="hidden" id="org_id" name="orgInfo.org_id" value="<%=orgid%>"/> <i id="str" style="color: red;"></i></li>
    <li><label>&nbsp;</label><input name="" type="submit" class="btn" onclick="updateorginfo();" value="确认保存"/>&nbsp;&nbsp;<input name="" type="button" style="background-color:green;" onclick="javascript:history.go(-1);" class="btn" value="返回"/></li>
    </ul>
    <input type="hidden" name="orgInfo.org_Status" value="<%=status %>">
    <input type="hidden" name="orgInfo.org_AddTime" value="<%=time %>">
    <input type="hidden" name="orgInfo.org_RoleId" value="<%=roleid%>">
    </div>
    </form>
  </body>
</html>
