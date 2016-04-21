<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String orgId=null;
String loanid=null;
String loanname=null;
String pwd=null;
String phone=null;
String time=null;
String account=null;
String roleid=null;
String status=null;
String role=null;
try{
	if(session==null){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else if(session.getAttribute("loan_orgId").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		orgId=session.getAttribute("loan_orgId").toString();
	}
	if(session.getAttribute("loan_id").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		loanid=session.getAttribute("loan_id").toString();
	}
	if(session.getAttribute("loan_name").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		loanname=session.getAttribute("loan_name").toString();
	}
	if(session.getAttribute("loan_passWord").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		pwd=session.getAttribute("loan_passWord").toString();
	}
	if(session.getAttribute("loan_phoneNumber").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		phone=session.getAttribute("loan_phoneNumber").toString();
	}
	if(session.getAttribute("loan_addTime").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		time=session.getAttribute("loan_addTime").toString();
	}
	if(session.getAttribute("loan_tradAccount").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		account=session.getAttribute("loan_tradAccount").toString();
	}
	if(session.getAttribute("loan_roleId").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		roleid=session.getAttribute("loan_roleId").toString();
	}
	if(session.getAttribute("loan_status").toString()==""){
		response.sendRedirect(request.getContextPath()
				+ "/login.jsp");
	}else{
		status=session.getAttribute("loan_status").toString();
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
		function updateloan() {
			var f=document.getElementsByTagName("form")[0];
			var pwd=document.getElementById("loan_passWord").value;
			var loan_name=document.getElementById("loan_name").value;
			if(pwd==""||pwd==null){
				alert("请输入密码！");
				return false;
			}else if(loan_name==""||loan_name==null){
				alert("请输入姓名！");
				return false;
			}else if(!checkname()){
				alert("保存失败，请输入正确的信息");
				return false;
			}else{
				f.action="updateLoan.action";
			}
		}
		function checkPhone() {
			var flag=false;
			var phone=document.getElementById("loan_phoneNumber").value;
			if(phone==""){
				document.getElementById("str").innerHTML="";
				flag= true; 
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
		function checkname() {
			var flag=false;
			var obj=document.getElementById("loan_name").value;
			$.ajax({
				url:"getCheckNames.action",
				type:"post",
				data:{"name":obj,"id":$("#loan_id").val()},
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
	</script>
  </head> 
  <body>
  	<form action="" method="post">
  	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">基础数据管理</a></li>
    <li><a href="#">修改个代</a></li>
    </ul>
    </div>   
    <div class="formbody">  
    <div class="formtitle"><span>基本信息</span></div>  
    <ul class="forminfo">
    <%if(role=="系统用户"||role=="居间商"){ %>
    	 <li><label><i style="color: red;">*</i>姓名</label><input id="loan_name" onkeyup="checkname();" name="loanLeader.loan_name" value="<%=loanname %>" type="text" class="dfinput" /><i id="strs" style="color: red;"></i></li>
    <%} else if(role=="个代"){%>
     	<li><label><i style="color: red;">*</i>姓名</label><input id="loan_name" readonly="readonly" name="loanLeader.loan_name" value="<%=loanname %>" type="text" class="dfinput" /><i id="strs" style="color: red;"></i></li>
    <%} %>
    <li><label><i style="color: red;">*</i>密码</label><input id="loan_passWord" name="loanLeader.loan_passWord" value="<%=pwd %>" type="password" class="dfinput" /><i></i></li>
    <%if(role=="系统用户"||role=="居间商"){ %>
    	    <li><label><i style="color: red;">*</i>交易账号</label><input id="loan_tradAccount" name="loanLeader.loan_tradAccount" value="<%=account %>" type="text" class="dfinput" /><i></i></li>
    <%} else if(role=="个代"){%>
    		<li><label><i style="color: red;">*</i>交易账号</label><input id="loan_tradAccount" readonly="readonly" name="loanLeader.loan_tradAccount" value="<%=account %>" type="text" class="dfinput" /><i></i></li>
    <%} %>
    <li><label><i style="color: red;"></i>手机号码</label><input id="loan_phoneNumber" name="loanLeader.loan_phoneNumber" onkeyup="checkPhone();" value="<%=phone %>" type="text" class="dfinput" /><i id="str" style="color: red;"></i></li>
    <%if(role=="系统用户"){ %>
    	    <li><label>所属居间</label><div class="cityright"><select id="loan_orgId" name="loanLeader.loan_orgId" class="select2">
    	    <c:forEach var="i" items="${org_list}">
    	    	<option value="${i.org_id}">${i.org_Abbreviation}</option>
    	    </c:forEach>
    	    </select></div></li>
    <%}else {%>
    	    <input type="hidden" name="loanLeader.loan_orgId" value="<%=orgId%>">
    <%} %>
    <li><label>&nbsp;</label><input name="" type="submit" class="btn" onclick="updateloan();" value="确认保存"/>&nbsp;&nbsp;<input name="" type="button" style="background-color:green;" onclick="javascript:history.go(-1);" class="btn" value="返回"/></li>
    </ul>
    <input type="hidden" name="loanLeader.loan_status" value="<%=status%>">
    <input type="hidden" name="loanLeader.loan_addTime" value="<%=time%>">
    <input type="hidden" name="loanLeader.loan_roleId" value="<%=roleid%>">
    <input type="hidden" id="loan_id"  name="loanLeader.loan_id" value="<%=loanid%>">
    </div>
    </form>
    <script type="text/javascript">
    	var select=document.getElementById("loan_orgId");
    	for(var i=0; i<select.options.length; i++){  
    	    if(select.options[i].value ==<%=orgId%>){  
    	        select.options[i].selected = true;  
    	        break;  
    	    }  
    	}  
    </script>
  </body>
</html>
