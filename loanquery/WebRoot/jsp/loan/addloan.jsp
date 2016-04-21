<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
	String time=df.format(new Date());// new Date()为获取当前系统时间
	String orgId=null;
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	//if(session.getAttribute("orgId").toString()==""){
		//response.sendRedirect(request.getContextPath()
			//+ "/login.jsp");
	//}else{
		//orgId=session.getAttribute("orgId").toString();
	//}
	String role=null;
	try{
		if(session==null){
			System.out.println("aa");
			response.sendRedirect(request.getContextPath()
					+ "/login.jsp");
		}else if(session.getAttribute("orgId").toString()!=""){
			orgId=session.getAttribute("orgId").toString();
		}
		if(session.getAttribute("role").toString()==""){
			System.out.println("bb");
			response.sendRedirect(request.getContextPath()
					+ "/login.jsp");
		}else{
			role=session.getAttribute("role").toString();
		}
	}catch(Exception e){
		System.out.println("cc");
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
	<link rel="stylesheet" type="text/css" href="<%=path %>/js/autocomplete/css/ui-lightness/jquery-ui-1.10.4.custom.min.css">
	<script type="text/javascript" src="<%=path %>/js/jquery.min.1.8.1.js" ></script>
	<script type="text/javascript" src="<%=path %>/js/select-ui.min.js" ></script>
	<script type="text/javascript" src="<%=path %>/js/autocomplete/js/jquery-ui-1.10.4.custom.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		$(function(){
            $("#loan_orgAbbreviation").autocomplete({
                source: function (request, response) {
                    $.ajax({
                        url: "AutocompleteOrg.action",  //要查询的Action
	                    type: "POST",  //Post提交
	                    dataType: "json", //json格式数据，默认是text
	                    data: { keyword: request.term }, //参数，不知道为什么？请指点
	                    success: function (data) {
	                        response($.map(data.list, function (item) {
	                            return { label: item.text, value: item.value }; //将返回的jsonresult的属性赋值给autocomplete item
	                        }));
	                    }
	                });
	            },
            	focus: function (event, ui) {
            	    $("#loan_orgAbbreviation").val(ui.item.label); //选中item的文本
            	    return false;
            	},
            	select: function (event, ui) {
            	    $("#loan_orgAbbreviation").val(ui.item.label);  //选中item的文本
            	    $("#loan_orgId").val(ui.item.value); //选中item的值
            	    return false;
            	}
            });
		});
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
	
		function addloan() {
			var f=document.getElementsByTagName("form")[0];
			var pwd=document.getElementById("loan_passWord").value;
			var loan_name=document.getElementById("loan_name").value;
			var orgid=document.getElementById("loan_orgId").value;
			if(orgid==""||orgid==null){
				alert("请输入所属单位！");
				return false;
			}
			if(pwd==""||pwd==null){
				alert("请输入密码！");
				return false;
			}else if(loan_name==""||loan_name==null){
				alert("请输入姓名！");
				return false;
			}else if(!checkname()){
				alert("保存失败,请正确填写信息");
				return false;
			}else{
				f.action="addLoan.action";
			}
		}
		function checkname() {
			var flag=false;
			var obj=document.getElementById("loan_name").value;
			$.ajax({
				url:"getCheckName.action",
				type:"post",
				data:{"name":obj},
				async:false,
				dataType:"json",
				success:function(data){
					if(data.success==true){
						document.getElementById("str").innerHTML="";
						flag= true;
					}else{
						document.getElementById("str").innerHTML="该名已存在,请重新输入姓名！";
						flag= false;
					}
				}
			});	
			return flag;
		}
		function checkPhone() {
			var flag=false;
			var phone=document.getElementById("loan_phoneNumber").value;
			if(phone==""){
				document.getElementById("strs").innerHTML="";
				flag= true;
			}else{
				var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
				if(!myreg.test(phone)) 
				{ 
				    document.getElementById("strs").innerHTML="请输入有效的手机号码！";
				    flag= false; 
				}else{
					document.getElementById("strs").innerHTML="";
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
    <li><a href="#">基础数据管理</a></li>
    <li><a href="#">添加个代</a></li>
    </ul>
    </div>   
    <div class="formbody">  
    <div class="formtitle"><span>基本信息</span></div>  
    <ul class="forminfo">
    <li><label><i style="color: red;">*</i>姓名</label><input id="loan_name" name="loanLeader.loan_name" onblur="checkname()" type="text" class="dfinput" /><i id="str" style="color: red;"></i></li>
    <li><label><i style="color: red;">*</i>密码</label><input id="loan_passWord" name="loanLeader.loan_passWord" type="password" class="dfinput" /><i></i></li>
    <li><label><i style="color: red;">*</i>交易账号</label><input id="loan_tradAccount" name="loanLeader.loan_tradAccount" type="text" class="dfinput" /><i></i></li>
    <li><label><i style="color: red;"></i>手机号码</label><input id="loan_phoneNumber" name="loanLeader.loan_phoneNumber" onkeyup="checkPhone();" type="text" class="dfinput" /><i id="strs" style="color: red;"></i></li>
    <%if(role=="系统用户"){ %>
    	   <li><label><i style="color: red;">*</i>所属居间</label><input id="loan_orgAbbreviation" name="loanLeader.orgAbbreviation" type="text" class="dfinput" /><input type="hidden" id="loan_orgId" name="loanLeader.loan_orgId"><i></i></li>
    <%}%>
    <%if(role=="居间商"){  %>
    	<input type="hidden" id="loan_orgId" name="loanLeader.loan_orgId" value="<%=orgId%>"> 
    <%} %>
    <li><label>状态</label><div class="cityright"><select name="loanLeader.loan_status" class="select2"><option value="1">启用</option><option value="0">禁用</option></select></div></li>
    <li><label>添加时间</label><input name="loanLeader.loan_addTime" value="<%=time %>" type="text" class="dfinput" /><i></i></li>
    <li><label>&nbsp;</label><input name="" type="submit" class="btn" onclick="addloan();" value="确认保存"/>&nbsp;&nbsp;<input name="" type="button" style="background-color:green;" onclick="javascript:history.go(-1);" class="btn" value="返回"/></li>
    </ul>
    </div>
    </form>
  </body>
</html>
