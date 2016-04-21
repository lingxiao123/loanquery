<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String loanid=null;
	String time=null;
	String status=null;
	String name=null;
	String lmid=null;
	String account=null;
	String phone=null;
	String role=null;
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	try{
		if(session==null){
			response.sendRedirect(request.getContextPath()
					+ "/login.jsp");
		}else if(session.getAttribute("lm_loanid").toString()==""){
			response.sendRedirect(request.getContextPath()
					+ "/login.jsp");
		}else{
			loanid=session.getAttribute("lm_loanid").toString();
		}
		if(session.getAttribute("lm_addTime").toString()==""){
			response.sendRedirect(request.getContextPath()
					+ "/login.jsp");
		}else{
			time=session.getAttribute("lm_addTime").toString();
		}
		if(session.getAttribute("lm_status").toString()==""){
			response.sendRedirect(request.getContextPath()
					+ "/login.jsp");
		}else{
			status=session.getAttribute("lm_status").toString();
		}
		if(session.getAttribute("lm_name").toString()==""){
			response.sendRedirect(request.getContextPath()
					+ "/login.jsp");
		}else{
			name=session.getAttribute("lm_name").toString();
		}
		if(session.getAttribute("lm_id").toString()==""){
			response.sendRedirect(request.getContextPath()
					+ "/login.jsp");
		}else{
			lmid=session.getAttribute("lm_id").toString();
		}
		if(session.getAttribute("lm_tradAccount").toString()==""){
			response.sendRedirect(request.getContextPath()
					+ "/login.jsp");
		}else{
			account=session.getAttribute("lm_tradAccount").toString();
		}
		if(session.getAttribute("lm_phoneNumber").toString()==""){
			response.sendRedirect(request.getContextPath()
					+ "/login.jsp");
		}else{
			phone=session.getAttribute("lm_phoneNumber").toString();
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
		function updateloanmember() {
			var f=document.getElementsByTagName("form")[0];
			var lm_name=document.getElementById("lm_name").value;
			var lm_tradAccount=document.getElementById("lm_tradAccount").value;
			if(lm_name==""||lm_name==null){
				alert("请输入姓名！");
				return false;
			}else if(lm_tradAccount==""||lm_tradAccount==null){
				alert("请输入交易帐号！");
				return false;
			}else if(!checkPhone()){
				alert("保存失败,请输入正确的信息");
				return false;
			}else if(!checkTradAccount()){
				alert("保存失败,请输入正确的信息");
				return false;
			}else{
				f.action="updateLoanmember.action";
			}
		}
		function checkPhone() {
			var flag=false;
			var phone=document.getElementById("lm_phoneNumber").value;
			if(phone==""){
				flag="true";
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
		function checkTradAccount() {
			var flag=false;
			$.ajax({
				url:"getCheckAccounts.action",
				type:"post",
				data:{"tradAccount":$("#lm_tradAccount").val(),"id":$("#lm_id").val()},
				async:false,
				dataType:"json",
				success:function(data){
					if(data.success==true){
						document.getElementById("strs").innerHTML="";
						flag= true;
					}else{
						document.getElementById("strs").innerHTML="该交易账号已存在,请重新交易账号！";
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
    <li><a href="#">修改终端客户</a></li>
    </ul>
    </div>   
    <div class="formbody">  
    <div class="formtitle"><span>添加个代成员</span></div>  
    <ul class="forminfo">
    <li><label><i style="color: red;">*</i>成员姓名</label><input id="lm_name" name="loanMember.lm_name" type="text" value="<%=name%>" class="dfinput" /><i></i></li>
    <li><label><i style="color: red;">*</i>交易账号</label><input id="lm_tradAccount" onkeyup="checkTradAccount();" name="loanMember.lm_tradAccount" type="text" value="<%=account %>" class="dfinput" /><i id="strs" style="color: red;"></i></li>
    <li><label><i style="color: red;">*</i>手机号码</label><input id="lm_phoneNumber" name="loanMember.lm_phoneNumber" onkeyup="checkPhone();" type="text" value="<%=phone %>" class="dfinput" /><i id="str" style="color: red;"></i></li>
    <%if(role=="系统用户"){ %>
    	    <li><label>所属主管</label><div class="cityright"><select id="lm_loanid" name="loanMember.lm_loanid" class="select2">
    	    <c:forEach var="i" items="${loan_list}">
    	    	<option value="${i.loan_id}">${i.loan_name}</option>
    	    </c:forEach>
    	    </select></div></li>
    <%}%>
    <%if(role=="居间商"){ %>
    	    <li><label>所属主管</label><div class="cityright"><select id="lm_loanid"  name="loanMember.lm_loanid" class="select2">
    	    <c:forEach var="i" items="${loan_list}">
    	    	<option value="${i.loan_id}">${i.loan_name}</option>
    	    </c:forEach>
    	    </select></div></li>
    <%}else if(role=="个代") {%>
    	  <input type="hidden" name="loanMember.lm_loanid" value="<%=loanid%>"/>
    <%} %>
    <li><label>&nbsp;</label><input name="" type="submit" class="btn" onclick="updateloanmember();" value="确认保存"/>&nbsp;&nbsp;<input name="" type="button" style="background-color:green;" onclick="javascript:history.go(-1);" class="btn" value="返回"/></li>
    </ul>
    <input type="hidden" name="loanMember.lm_status" value="<%=status%>">
    <input type="hidden" name="loanMember.lm_addTime" value="<%=time%>">
    <input type="hidden" id="lm_id" name="loanMember.lm_id" value="<%=lmid%>">
    </div>
    </form>
    <script type="text/javascript">
    	var select=document.getElementById("lm_loanid");
    	for(var i=0; i<select.options.length; i++){  
    	    if(select.options[i].value ==<%=loanid%>){  
    	        select.options[i].selected = true;  
    	        break;  
    	    }  
    	}  
    </script>
  </body>
</html>
